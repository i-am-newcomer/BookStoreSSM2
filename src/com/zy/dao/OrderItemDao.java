package com.zy.dao;

import java.util.List;
import java.util.Map;

import com.zy.entity.OrderItem;

public interface OrderItemDao {
	
	public List<OrderItem> selectOrderItemByOId(int oid);
	
	public OrderItem selectOrderItemById(Map<String, Integer> idmap);
	
	public int insertOrderItem(OrderItem orderitem);
	
	public int updateOrderItem(OrderItem orderitem);
	

}
