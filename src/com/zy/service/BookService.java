package com.zy.service;

import com.zy.entity.Book;

public interface BookService {

	public Book selectBookById(int id);
	
	public int updateBookAmount(Book book);
	
	public int updateBookPrice(Book book);

	public int updateBookSalesVolume(Book book);

}
