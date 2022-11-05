package com.saeed.mvc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
}
