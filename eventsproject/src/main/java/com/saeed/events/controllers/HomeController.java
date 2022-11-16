package com.saeed.events.controllers;

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

import com.saeed.events.models.Event;
import com.saeed.events.models.LoginUser;
import com.saeed.events.models.Message;
import com.saeed.events.models.User;
import com.saeed.events.services.EventService;
import com.saeed.events.services.MessageService;
import com.saeed.events.services.UserService;

@Controller
public class HomeController {
    
     @Autowired
     private UserService userServ;
     @Autowired
     private EventService eventServ;
     @Autowired
     private MessageService messageServ;
     
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    	if (session.getAttribute("user_id")!=null) {
    		return "redirect:/events";
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
        return "redirect:/events";
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
        return "redirect:/events";
    }
    
    @GetMapping("/events")
    public String success(Model model, HttpSession session, @ModelAttribute("event") Event event) {
    	if (session.getAttribute("user_id")!=null) {
    		Long userId = (Long) session.getAttribute("user_id");
    		User currentUser = userServ.findUserById(userId);
    		List<Event> stateEvents = eventServ.eventsInYourState(currentUser.getState());
    		List<Event> notStateEvents = eventServ.eventsNotInYourState(currentUser.getState());
    		for(Event dammyEvent:stateEvents) {
    			dammyEvent.setIsHost(currentUser.getCreatedEvents().contains(dammyEvent));
    			dammyEvent.setIsMember(currentUser.getEvents().contains(dammyEvent));
    		}
    		for(Event dammyEvent2:notStateEvents) {
    			dammyEvent2.setIsHost(currentUser.getCreatedEvents().contains(dammyEvent2));
    			dammyEvent2.setIsMember(currentUser.getEvents().contains(dammyEvent2));
    		}
    		model.addAttribute("currentUser", currentUser);
    		model.addAttribute("stateEvents", stateEvents);
    		model.addAttribute("notStateEvents", notStateEvents);
    		return "events.jsp";
    	}
    	return "redirect:/";
    }
    
    @PostMapping("/events/new")
    public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
    	if (result.hasErrors()) {
    		return "events.jsp";
    	}
    	eventServ.createEvent(event);
    	return "redirect:/events";
    }
    
    @GetMapping("/events/{id}")
    public String showEvent(@PathVariable("id") Long id, Model model, HttpSession session, @ModelAttribute("message") Message message) {
    	if (session.getAttribute("user_id")!=null) {
    		Long user_id = (Long) session.getAttribute("user_id");
    		User user = userServ.findUserById(user_id);
    		Event event = eventServ.findEventById(id);
    		List<User> users = event.getUsers();
    		Integer count = users.size();
    		List<Message> messages = event.getMessages();
    		model.addAttribute("event", event);
    		model.addAttribute("count", count);
    		model.addAttribute("user", user);
    		model.addAttribute("messages", messages);
    		return "showevent.jsp";
    	}
    	return "redirect:/";
    }
    
	 @GetMapping("/events/{id}/edit")
	    public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
			Event event = eventServ.findEventById(id);
			Long userId = (Long) session.getAttribute("user_id");
		 	if (userId==null || userId!=event.getHost().getId()) {
	    		return "redirect:/";
	    	}
			User user = userServ.findUserById(userId);
			model.addAttribute("user", user);
	        model.addAttribute("event", event);
	        return "editevent.jsp";
	    }
	    
	@PutMapping("/events/{id}")
	    public String update(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session) {
		 	if (session.getAttribute("user_id")==null || session.getAttribute("user_id")!=event.getHost().getId()) {
	    		return "redirect:/";
	    	}
	        if (result.hasErrors()) {
	            return "editevent.jsp";
	        }
	        eventServ.updateEvent(event);
	        return "redirect:/";
	  }
	 
	@GetMapping("/events/{id}/delete")
	    public String destroy(@PathVariable("id") Long id, HttpSession session) {
		  Event event = eventServ.findEventById(id);
		  if (session.getAttribute("user_id")==null || session.getAttribute("user_id")!=event.getHost().getId()) {
	    		return "redirect:/";
	    	}
		  eventServ.deleteEvent(id);
	      return "redirect:/";
	    }
	
	 @GetMapping("/events/{id}/join")
	  public String join(@PathVariable("id") Long id, HttpSession session) {
		  if (session.getAttribute("user_id")==null) {
			  return "redirect:/";
		  }
			Event event = eventServ.findEventById(id);
		 	Long userId = (Long) session.getAttribute("user_id");
			User user = userServ.findUserById(userId);
			List<User> users = event.getUsers();
			users.add(user);
			event.setUsers(users);
			eventServ.updateEvent(event);
	        return "redirect:/";
	    }
	 @GetMapping("/events/{id}/leave")
	  public String leave(@PathVariable("id") Long id, HttpSession session) {
		  if (session.getAttribute("user_id")==null) {
			  return "redirect:/";
		  }
			Event event = eventServ.findEventById(id);
		 	Long userId = (Long) session.getAttribute("user_id");
			User user = userServ.findUserById(userId);
			List<User> users = event.getUsers();
			users.remove(user);
			event.setUsers(users);
			eventServ.updateEvent(event);
	        return "redirect:/";
	    }
	
	 @PostMapping("/events/{eventId}/messages")
	    public String createMessage(@PathVariable("eventId") Long eventId, Model model, HttpSession session, @Valid @ModelAttribute("message") Message message, BindingResult result) {
	    	if (result.hasErrors()) {
	    		Event event = eventServ.findEventById(eventId);
			  	Long user_Id = (Long) session.getAttribute("user_id");
			  	List<Message> messages = event.getMessages();
	    		model.addAttribute("event", event);
	    		model.addAttribute("user_Id", user_Id);
	    		model.addAttribute("messages", messages);
	    		return "showevent.jsp";
	    	}
	    	messageServ.createMessage(message);
	    	return "redirect:/events/{eventId}";
	    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
}
