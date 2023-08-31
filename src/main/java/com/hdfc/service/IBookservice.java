package com.hdfc.service;

import java.util.List;

import com.hdfc.entity.Book;
import com.hdfc.exception.BookNotFoundExp;



public interface IBookservice {

	public Book addBook(Book b);
	
	public Book updateBook(long id,Book b) throws BookNotFoundExp;
	
	public List<Book> getAllBook();
	
	public void delete(long id) throws BookNotFoundExp;
	
	public Book getByID (long id) throws BookNotFoundExp;
	
}
