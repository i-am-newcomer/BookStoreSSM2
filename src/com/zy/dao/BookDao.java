package com.zy.dao;

import com.zy.entity.Book;

public interface BookDao {
	
	public Book selectBookById(int id);
	
	public int updateBookAmount(Book book);
	
	public int updateBookPrice(Book book);

	public int updateBookSalesVolume(Book book);
}
