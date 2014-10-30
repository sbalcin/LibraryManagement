package com.snn.library.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.snn.library.beans.Book;
import com.snn.library.utility.DBConfig;

@Service("bookService")
public class BookServiceImpl implements BookService {
	ApplicationContext ctx;
	MongoOperations mongoOperation;

	public BookServiceImpl() {
		ctx = new AnnotationConfigApplicationContext(DBConfig.class);
		mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	}

	public List<Book> getAllBooks() {
		return mongoOperation.findAll(Book.class);
	}

	public Book getBookById(String id) {
		return findBookById(id);
	}

	public void addBook(Book book) {
		mongoOperation.save(book);
	}

	public void deleteBookById(String id) {
		Book found = findBookById(id);
		if (found != null) {
			mongoOperation.remove(found);
		}
	}

	public void updateBook(Book book) {
		Book found = findBookById(book.getId());
		found.setName(book.getName());
		found.setAuthor(book.getAuthor());
		found.setQuantity(book.getQuantity());
		found.setPopular(book.getPopular());
		
		mongoOperation.save(found);
	}

	private Book findBookById(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		Book book = mongoOperation.findOne(query, Book.class);
		return book;
	}
}
