package com.saeed.authentication.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.saeed.authentication.models.LoginUser;
import com.saeed.authentication.models.User;
import com.saeed.authentication.services.UserService;

@Controller
public class HomeController {
    
     @Autowired
     private UserService userServ;
    
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    	if (session.getAttribute("user_id")!=null) {
    		return "redirect:/success";
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
        return "redirect:/success";
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
        return "redirect:/success";
    }
    @GetMapping("/success")
    public String success(Model model, HttpSession session) {
    	if (session.getAttribute("user_id")!=null) {
    		Long userId = (Long) session.getAttribute("user_id");
    		User currentUser = userServ.findUserById(userId);
    		model.addAttribute("currentUser", currentUser);
    		return "success.jsp";
    	}
    	return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
}
