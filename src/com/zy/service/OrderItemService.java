package com.zy.service;

import java.util.List;
import java.util.Map;

import com.zy.entity.Book;
import com.zy.entity.Order;
import com.zy.entity.OrderItem;

public interface OrderItemService {
	
	public List<OrderItem> selectOrderItemByOId(int oid);
	
	public OrderItem selectOrderItemById(Map<String, Integer> idmap);
	
	public int insertOrderItem(Order order, Book book, int quantity, float price);
	
	public int updateOrderItem(Order order, Book book, int quantity, float price);

}
