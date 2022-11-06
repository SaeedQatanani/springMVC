package com.saeed.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saeed.mvc.models.Book;
import com.saeed.mvc.services.BookService;

@Controller
public class BookController {
	private final BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @RequestMapping("/books")
    public String index(Model model) {
        List<Book> books = bookService.allBooks();
        model.addAttribute("books", books);
        return "/books/index.jsp";
    }
	@RequestMapping("/books/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBook(id);
		model.addAttribute("book", book);
		return "/books/show.jsp";
	}
	 @GetMapping("/books/new")
	    public String newBook(@ModelAttribute("book") Book book) {
	        return "/books/new.jsp";
	    }
	 @PostMapping("/books")
	    public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
	        if (result.hasErrors()) {
	            return "/books/new.jsp";
	        } else {
	            bookService.createBook(book);
	            return "redirect:/books";
	        }
	    }
	 @GetMapping("/books/{id}/edit")
	    public String edit(@PathVariable("id") Long id, Model model) {
	        Book book = bookService.findBook(id);
	        model.addAttribute("book", book);
	        return "/books/edit.jsp";
	    }
	    
	  @PutMapping("/books/{id}")
	    public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
	        if (result.hasErrors()) {
	            return "/books/edit.jsp";
	        } else {
	            bookService.updateBook(book);
	            return "redirect:/books";
	        }
	  }
	  @DeleteMapping("/books/{id}")
	    public String destroy(@PathVariable("id") Long id) {
	        bookService.deleteBook(id);
	        return "redirect:/books";
	    }
}
