package com.zy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zy.entity.Book;
import com.zy.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	/*¸ù¾Ýid²éÑ¯book*/
	@RequestMapping("/selectBookById")
	public String selectBookById(int id, ModelMap modelMap) {
		Book book = bookService.selectBookById(id);
		if(book!=null) {
			modelMap.addAttribute("book", book);
			return "/book.jsp";
		}
		return "/showerror.jsp";
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}	

}
