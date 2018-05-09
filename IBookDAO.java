package com.dao;

import java.util.List;

import cm.entities.*;

public interface IBookDAO {

	public List<Book> findAll(Integer pageNum,Integer recordsPerPage) throws Exception;
	
	public List<Book> findAll() throws Exception;
	
	public Book getBook(Integer bookId) throws Exception;
	
	public boolean deleteBook(Integer bookId) throws Exception;

	public boolean save(Book book);
}
