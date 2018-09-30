package com.zy.service;

import com.zy.entity.Book;
import com.zy.entity.Order;

public interface OrderService {
	
	public Order selectOrderByCId(int cid);
	
	public Order selectOrderById(int id);
	
	public int insertOrder(Order order,Book book, int quantity);
	
	public int updateOrder(Order order, Book book, int quantity);


}
