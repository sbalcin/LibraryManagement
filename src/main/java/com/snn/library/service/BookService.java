package com.snn.library.service;

import com.snn.library.beans.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();

    public Book getBookById(String id);

    public void addBook(Book book);

    public void deleteBookById(String id);

    public void updateBook(Book book);
}
