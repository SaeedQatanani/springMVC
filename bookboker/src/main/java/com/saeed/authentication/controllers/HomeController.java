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

import com.saeed.authentication.models.Book;
import com.saeed.authentication.models.LoginUser;
import com.saeed.authentication.models.User;
import com.saeed.authentication.services.BookService;
import com.saeed.authentication.services.UserService;

@Controller
public class HomeController {
    
     @Autowired
     private UserService userServ;
     @Autowired
     private BookService bookServ;
    
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    	if (session.getAttribute("user_id")!=null) {
    		return "redirect:/shelves";
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
        return "redirect:/shelves";
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
        return "redirect:/shelves";
    }
    @GetMapping("/shelves")
    public String shelves(Model model, HttpSession session) {
    	if (session.getAttribute("user_id")!=null) {
    		Long userId = (Long) session.getAttribute("user_id");
    		User currentUser = userServ.findUserById(userId);
    		List<Book> allBooks = bookServ.allBooks();
    		model.addAttribute("currentUser", currentUser);
    		model.addAttribute("allBooks", allBooks);
    		return "shelves.jsp";
    	}
    	return "redirect:/";
    }
    @GetMapping("/books/new")
    public String addBook(Model model, HttpSession session, @ModelAttribute("book") Book book) {
    	Long userId = (Long) session.getAttribute("user_id");
		User user = userServ.findUserById(userId);
		model.addAttribute("user", user);
    	return "newbook.jsp";
    }
    @PostMapping("/books/new")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
    	if (result.hasErrors()) {
    		return "newbook.jsp";
    	}
    	bookServ.createBook(book);
    	return "redirect:/shelves";
    }
    @GetMapping("/books/{id}")
    public String showBook(@PathVariable("id") Long id, Model model, HttpSession session) {
    	Long user_id = (Long) session.getAttribute("user_id");
    	Book book = bookServ.findBookById(id);
    	model.addAttribute("book", book);
    	model.addAttribute("user_id", user_id);
    	return "showbook.jsp";
    }
	 @GetMapping("/books/{id}/edit")
	    public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
			Book book = bookServ.findBookById(id);
		 	if (session.getAttribute("user_id")==null || session.getAttribute("user_id")!=book.getUser().getId()) {
	    		return "redirect:/";
	    	}
		 	Long userId = (Long) session.getAttribute("user_id");
			User user = userServ.findUserById(userId);
			User bUser = book.getBorrower();
			model.addAttribute("bUser", bUser);
			model.addAttribute("user", user);
	        model.addAttribute("book", book);
	        return "editbook.jsp";
	    }
	    
	 @PutMapping("/books/{id}")
	    public String update(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session) {
		 	if (session.getAttribute("user_id")==null || session.getAttribute("user_id")!=book.getUser().getId()) {
	    		return "redirect:/";
	    	}
	        if (result.hasErrors()) {
	            return "editbook.jsp";
	        }
	        bookServ.updateBook(book);
	        return "redirect:/shelves";
	  }
	  @DeleteMapping("/books/{id}")
	    public String destroy(@PathVariable("id") Long id, HttpSession session) {
		  Book book = bookServ.findBookById(id);
		  if (session.getAttribute("user_id")==null || session.getAttribute("user_id")!=book.getUser().getId()) {
	    		return "redirect:/";
	    	}
		  bookServ.deleteBook(id);
	      return "redirect:/shelves";
	    }
	  @GetMapping("/logout")
	  public String logout(HttpSession session) {
		  session.invalidate();
		  return "redirect:/";
	  }
	  @GetMapping("/bookmarket")
	  public String bookMarket(Model model, HttpSession session) {
		  if (session.getAttribute("user_id")!=null) {
	    		Long userId = (Long) session.getAttribute("user_id");
	    		User currentUser = userServ.findUserById(userId);
	    		List<Book> allBooks = bookServ.allBooks();
	    		List<Book> restBooks = bookServ.restBooks();
	    		model.addAttribute("currentUser", currentUser);
	    		model.addAttribute("allBooks", allBooks);
	    		model.addAttribute("restBooks", restBooks);
	    		return "bookmarket.jsp";
		  }
		  return "redirect:/";
	    }
	  @GetMapping("/bookmarket/borrow/{id}")
	  public String borrow(@PathVariable("id") Long id, HttpSession session) {
		  Book book = bookServ.findBookById(id);
		  Long userId = (Long) session.getAttribute("user_id");
  		  User currentUser = userServ.findUserById(userId);
  		  book.setBorrower(currentUser);
  		  bookServ.updateBook(book);
		  return "redirect:/bookmarket";
	  }
	  @GetMapping("/bookmarket/return/{id}")
	  public String unBorrow(@PathVariable("id") Long id, HttpSession session) {
		  Book book = bookServ.findBookById(id);
  		  book.setBorrower(null);
  		  bookServ.updateBook(book);
		  return "redirect:/bookmarket";
	  }
}
