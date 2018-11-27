package com.magpie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.magpie.BookService;
import com.magpie.model.Book;
import com.magpie.model.GoodreadsResponse;
import com.magpie.repository.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String getAllBooks(Model model) {
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);
		return "bookList";
	}
	
	/*@RequestMapping(value = "/books/{category}", method = RequestMethod.GET)
	public String getBooksByCategory(@PathVariable String category, Model model){
		List<Book> books = bookService.getBooksByCategory(category);
		model.addAttribute("books", books);
		return "bookList";
	}*/
	
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public String getBookDetails(@PathVariable String id, Model model) {
		Book book = bookService.getBook(id);
		GoodreadsResponse goodreadsResponse = bookService.getBookDetails(book.getTitle());
		model.addAttribute("book", book);
		model.addAttribute("bookImage", goodreadsResponse.getImageUrl());
		model.addAttribute("bookDescription", goodreadsResponse.getDescription());
		return "bookDetails";
	}
}
