package com.saeed.belt.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.saeed.belt.models.LoginUser;
import com.saeed.belt.models.Team;
import com.saeed.belt.models.User;
import com.saeed.belt.services.TeamService;
import com.saeed.belt.services.UserService;


@Controller
public class MainController {
	@Autowired
	private UserService userServ;
	@Autowired TeamService teamServ;
    
   @GetMapping("/")
   public String index(Model model, HttpSession session) {
   	if (session.getAttribute("user_id")!=null) {
   		return "redirect:/home";
   	}
       model.addAttribute("newUser", new User());
       model.addAttribute("newLogin", new LoginUser());
       return "login.jsp";
   }
   
   @PostMapping("/register")
   public String register(@Valid @ModelAttribute("newUser") User newUser, 
           BindingResult result, Model model, HttpSession session) {
       User regUser = userServ.register(newUser, result);
       if(result.hasErrors()) {
           model.addAttribute("newLogin", new LoginUser());
           return "login.jsp";
       }        
       session.setAttribute("user_id", regUser.getId());
       return "redirect:/home";
   }
   
   @PostMapping("/login")
   public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
           BindingResult result, Model model, HttpSession session) {
       User logUser = userServ.login(newLogin, result);
       if(result.hasErrors()) {
           model.addAttribute("newUser", new User());
           return "login.jsp";
       }
       session.setAttribute("user_id", logUser.getId());   
       return "redirect:/home";
   }
   
   @GetMapping("/home")
   public String success(Model model, HttpSession session) {
   	if (session.getAttribute("user_id")!=null) {
   		Long userId = (Long) session.getAttribute("user_id");
   		User currentUser = userServ.findUserById(userId);
   		List<Team> allTeams = teamServ.allTeams();
   		model.addAttribute("currentUser", currentUser);
   		model.addAttribute("allTeams", allTeams);
   		return "home.jsp";
   	}
   	return "redirect:/";
   }
   
   @GetMapping("/teams/new")
   public String newTeam(Model model, HttpSession session, @ModelAttribute("team") Team team) {
	   if (session.getAttribute("user_id")==null) {
   		return "redirect:/";
   	}
	   Long userId = (Long) session.getAttribute("user_id");
	   User currentUser = userServ.findUserById(userId);
	   model.addAttribute("currentUser", currentUser);
	   return "newteam.jsp";
   }
   
   @PostMapping("/teams/new")
   public String createTeam(@Valid @ModelAttribute("team") Team team, BindingResult result) {
   	if (result.hasErrors()) {
   		return "newteam.jsp";
   	}
   	teamServ.createTeam(team, result);
   	if (result.hasErrors()) {
   		return "newteam.jsp";
   	}
   	return "redirect:/";
   }
   
   @GetMapping("/teams/{teamId}")
   public String showTeam(@PathVariable("teamId") Long id, Model model, HttpSession session) {
   	if (session.getAttribute("user_id")!=null) {
   		Long user_id = (Long) session.getAttribute("user_id");
   		User user = userServ.findUserById(user_id);
   		Team team = teamServ.findTeamById(id);
   		List<User> otherUsers = userServ.usersNotInTeam(team);
   		model.addAttribute("otherUsers", otherUsers);
   		model.addAttribute("team", team);
   		model.addAttribute("user", user);
   		return "showteam.jsp";
   	}
   	return "redirect:/";
   }
   
	 @GetMapping("/teams/{id}/edit")
	    public String editTeam(@PathVariable("id") Long id, Model model, HttpSession session) {
		 	Team team = teamServ.findTeamById(id);
			Long userId = (Long) session.getAttribute("user_id");
		 	if (userId==null || userId!=team.getUser().getId()) {
	    		return "redirect:/";
	    	}
			User user = userServ.findUserById(userId);
			model.addAttribute("user", user);
	        model.addAttribute("team", team);
	        return "editteam.jsp";
	    }
	    
	@PutMapping("/teams/{id}")
	    public String updateTeam(@Valid @ModelAttribute("team") Team team, BindingResult result, HttpSession session) {
		 	if (session.getAttribute("user_id")==null || session.getAttribute("user_id")!=team.getUser().getId()) {
	    		return "redirect:/";
	    	}
	        if (result.hasErrors()) {
	            return "editteam.jsp";
	        }
	        teamServ.updateTeam(team, result);
	        if (result.hasErrors()) {
	            return "editteam.jsp";
	        }
	        return "redirect:/";
	  }
	 
	@GetMapping("/teams/{id}/delete")
	    public String destroy(@PathVariable("id") Long id, HttpSession session) {
		  Team team = teamServ.findTeamById(id);
		  if (session.getAttribute("user_id")==null || session.getAttribute("user_id")!=team.getUser().getId()) {
	    		return "redirect:/";
	    	}
		  teamServ.deleteTeam(id);
	      return "redirect:/";
	    }
	@PostMapping("/teams/addUser")
	public String addPlayer(@RequestParam("team") Long idt, @RequestParam("user") Long idu, RedirectAttributes redirectAttributes) {
		Team team = teamServ.findTeamById(idt);
		User user = userServ.findUserById(idu);
		List<User> teamPlayers = team.getUsers();
		if(teamPlayers.size()>8) {
			redirectAttributes.addFlashAttribute("error", "The Team is Full!");
			return "redirect:/teams/"+idt;
		}
		teamPlayers.add(user);
		team.setUsers(teamPlayers);
		teamServ.updateTeam(team);
		return "redirect:/";
	}
   
   @GetMapping("/logout")
   public String logout(HttpSession session) {
   	session.invalidate();
   	return "redirect:/";
   }
	
}
