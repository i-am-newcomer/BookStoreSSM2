package com.zy.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.zy.entity.Book;
import com.zy.service.BookService;

@Controller("bookAction")
@Scope("prototype")
public class BookAction {
	private Book book;
	private int id;
	@Autowired
	private BookService bookService;
	
	/*¸ù¾Ýid²éÑ¯book*/
	public String selectBookById() {
		book = bookService.selectBookById(id);
		if(book!=null) {
			return Action.SUCCESS;
		}
		return Action.ERROR;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
