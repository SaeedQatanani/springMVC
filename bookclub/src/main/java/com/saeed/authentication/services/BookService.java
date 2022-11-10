package com.saeed.authentication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saeed.authentication.models.Book;
import com.saeed.authentication.repositories.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;
    
    public List<Book> allBooks(){
    	return bookRepo.findAll();
    }
    public Book findBookById(Long id) {
    	Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}
		else {
			return null;
		}
    }

    public Book createBook(Book b) {
    	return bookRepo.save(b);
    }
    public Book updateBook(Book b) {
    	return bookRepo.save(b);
    }
    public void deleteBook(Long id) {
    	bookRepo.deleteById(id);
    }

   
}
