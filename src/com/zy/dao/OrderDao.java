package com.zy.dao;

import com.zy.entity.Order;

public interface OrderDao {
	
	public Order selectOrderByCId(int cid);
	
	public Order selectOrderById(int id);
	
	public int insertOrder(Order order);
	
	public int updateOrder(Order order);

}
