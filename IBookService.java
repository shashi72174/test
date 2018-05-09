package com.service;

import java.util.List;

import cm.entities.*;


public interface IBookService {
	
	public List<Book> findAll(Integer pageNum,Integer recordsPerPage)  throws Exception ;
	
	public Book getBook(Integer bookId)  throws Exception ; 
	
	public boolean deleteBook(Integer bookId) throws Exception;

	public boolean save(Book book);
	
	public String exportData();
	
}
