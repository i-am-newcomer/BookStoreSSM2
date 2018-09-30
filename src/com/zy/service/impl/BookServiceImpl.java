package com.zy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.dao.BookDao;
import com.zy.entity.Book;
import com.zy.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	@Override
	public Book selectBookById(int id) {
		return bookDao.selectBookById(id);
	}

	@Override
	public int updateBookAmount(Book book) {
		return 0;
	}

	@Override
	public int updateBookPrice(Book book) {
		return 0;
	}

	@Override
	public int updateBookSalesVolume(Book book) {
		return 0;
	}

}
