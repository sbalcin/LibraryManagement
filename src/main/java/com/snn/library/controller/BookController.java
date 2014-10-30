package com.snn.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snn.library.beans.Book;
import com.snn.library.service.BookService;
import com.snn.library.utility.CaptchaError;
import com.snn.library.utility.Common;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping("bookslist.json")
	public @ResponseBody List<Book> getBookList() {
		return bookService.getAllBooks();
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public @ResponseBody void addBook(@RequestBody Book book) {
		if (Common.checkCaptcha(book))
			bookService.addBook(book);
		else
			throw new CaptchaError();
	}

	@RequestMapping(value = "/updateBook", method = RequestMethod.PUT)
	public @ResponseBody void updateBook(@RequestBody Book book) {
		bookService.updateBook(book);
	}

	@RequestMapping(value = "/removeBook/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void removeBook(@PathVariable("id") String id) {
		bookService.deleteBookById(id);
	}

	@RequestMapping("/layout")
	public String getBookPartialPage(ModelMap modelMap) {
		return "books/layout";
	}
}
