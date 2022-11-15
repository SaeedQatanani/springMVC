package com.saeed.authentication.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.saeed.authentication.models.LoginUser;
import com.saeed.authentication.models.Project;
import com.saeed.authentication.models.Task;
import com.saeed.authentication.models.User;
import com.saeed.authentication.services.ProjectService;
import com.saeed.authentication.services.TaskService;
import com.saeed.authentication.services.UserService;

@Controller
public class HomeController {
    
     @Autowired
     private UserService userServ;
     @Autowired
     private ProjectService projectServ;
     @Autowired
     private TaskService taskServ;
    
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    	if (session.getAttribute("user_id")!=null) {
    		return "redirect:/dashboard";
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
        return "redirect:/dashboard";
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
        return "redirect:/dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
    	if (session.getAttribute("user_id")!=null) {
    		Long userId = (Long) session.getAttribute("user_id");
    		User currentUser = userServ.findUserById(userId);
    		List<Project> allProjects = projectServ.allProjectsNotContaining(currentUser);
    		List<Project> yourLeadingProjects = projectServ.allProjectsLeading(currentUser);
    		List<Project> yourPartOfProjects = projectServ.allProjectsContaining(currentUser);
    		
    		List<Project> yourProjects = yourLeadingProjects;
    		yourProjects.addAll(yourPartOfProjects);
    		model.addAttribute("currentUser", currentUser);
    		model.addAttribute("allProjects", allProjects);
    		model.addAttribute("yourProjects", yourProjects);
    		model.addAttribute("yourLeadingProjects", yourLeadingProjects);
    		model.addAttribute("yourPartOfProjects", yourPartOfProjects);
    		return "dashboard.jsp";
    	}
    	return "redirect:/";
    }
    @GetMapping("/projects/new")
    public String addProject(Model model, HttpSession session, @ModelAttribute("project") Project project) {
    	if (session.getAttribute("user_id")!=null) {
    		Long userId = (Long) session.getAttribute("user_id");
    		User user = userServ.findUserById(userId);
    		model.addAttribute("user", user);
    		return "newproject.jsp";
    	}
    	return "redirect:/";
    }
    @PostMapping("/projects/new")
    public String createProject(@Valid @ModelAttribute("project") Project project, BindingResult result) {
    	if (result.hasErrors()) {
    		return "newproject.jsp";
    	}
    	projectServ.createProject(project);
    	return "redirect:/dashboard";
    }
    @GetMapping("/projects/{id}")
    public String showProject(@PathVariable("id") Long id, Model model, HttpSession session) {
    	if (session.getAttribute("user_id")!=null) {
    		Long user_id = (Long) session.getAttribute("user_id");
    		Project project = projectServ.findProjectById(id);
    		model.addAttribute("project", project);
    		model.addAttribute("user_id", user_id);
    		return "showproject.jsp";
    	}
    	return "redirect:/";
    }
	 @GetMapping("/projects/{id}/edit")
	    public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
			Project project = projectServ.findProjectById(id);
			Long userId = (Long) session.getAttribute("user_id");
		 	if (userId==null || userId!=project.getTeamLead().getId()) {
	    		return "redirect:/";
	    	}
			User user = userServ.findUserById(userId);
			List<User> members = project.getUsers();
			model.addAttribute("user", user);
	        model.addAttribute("project", project);
	        model.addAttribute("members", members);
	        return "editproject.jsp";
	    }
	    
	 @PutMapping("/projects/{id}")
	    public String update(@Valid @ModelAttribute("project") Project project, BindingResult result, HttpSession session) {
		 	if (session.getAttribute("user_id")==null || session.getAttribute("user_id")!=project.getTeamLead().getId()) {
	    		return "redirect:/";
	    	}
	        if (result.hasErrors()) {
	            return "editproject.jsp";
	        }
	        projectServ.updateProject(project);
	        return "redirect:/dashboard";
	  }
	  @DeleteMapping("/projects/{id}")
	    public String destroy(@PathVariable("id") Long id, HttpSession session) {
		  Project project = projectServ.findProjectById(id);
		  if (session.getAttribute("user_id")==null || session.getAttribute("user_id")!=project.getTeamLead().getId()) {
	    		return "redirect:/";
	    	}
		  projectServ.deleteProject(id);
	      return "redirect:/dashboard";
	    }
	  @GetMapping("/logout")
	  public String logout(HttpSession session) {
		  session.invalidate();
		  return "redirect:/";
	  }
	  @GetMapping("/projects/join/{id}")
	  public String join(@PathVariable("id") Long id, HttpSession session) {
		  if (session.getAttribute("user_id")==null) {
			  return "redirect:/";
		  }
			Project project = projectServ.findProjectById(id);
		 	Long userId = (Long) session.getAttribute("user_id");
			User user = userServ.findUserById(userId);
			List<User> users = project.getUsers();
			users.add(user);
			project.setUsers(users);
			projectServ.updateProject(project);
	        return "redirect:/dashboard";
	    }
	  @GetMapping("/projects/leave/{id}")
	  public String leave(@PathVariable("id") Long id, HttpSession session) {
		  if (session.getAttribute("user_id")==null) {
			  return "redirect:/";
		  }
			Project project = projectServ.findProjectById(id);
		 	Long userId = (Long) session.getAttribute("user_id");
			User user = userServ.findUserById(userId);
			List<User> users = project.getUsers();
			users.remove(user);
			projectServ.updateProject(project);
	        return "redirect:/dashboard";
	    }
	  @GetMapping("/projects/{id}/tasks")
	  public String task(@PathVariable("id") Long id, Model model, HttpSession session, @ModelAttribute("task") Task task) {
		  	Project project = projectServ.findProjectById(id);
		  	Long userId = (Long) session.getAttribute("user_id");
		  	User user = userServ.findUserById(userId);
	    	if (project.getTeamLead().equals(user) || project.getUsers().contains(user)) {
	    		List<Task> tasks = project.getTasks();
	    		model.addAttribute("project", project);
	    		model.addAttribute("user", user);
	    		model.addAttribute("tasks", tasks);
	    		return "task.jsp";
	    	}
	    	return "redirect:/";
	  }
	    @PostMapping("/projects/{id}/tasks")
	    public String createTask(@PathVariable("id") Long id, Model model, HttpSession session, @Valid @ModelAttribute("task") Task task, BindingResult result) {
	    	if (result.hasErrors()) {
	    		Project project = projectServ.findProjectById(id);
			  	Long userId = (Long) session.getAttribute("user_id");
			  	User user = userServ.findUserById(userId);
			  	List<Task> tasks = project.getTasks();
	    		model.addAttribute("project", project);
	    		model.addAttribute("user", user);
	    		model.addAttribute("tasks", tasks);
	    		return "task.jsp";
	    	}
	    	taskServ.createTask(task);
	    	return "redirect:/projects/{id}/tasks";
	    }
}
