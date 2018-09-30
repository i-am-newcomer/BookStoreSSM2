package com.zy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.dao.OrderItemDao;
import com.zy.entity.Book;
import com.zy.entity.Order;
import com.zy.entity.OrderItem;
import com.zy.service.OrderItemService;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}
	

	@Override
	public List<OrderItem> selectOrderItemByOId(int oid) {
		return orderItemDao.selectOrderItemByOId(oid);
	}
	
	@Override
	public OrderItem selectOrderItemById(Map<String, Integer> idmap) {
		return orderItemDao.selectOrderItemById(idmap);
	}

	/*orderitems表中插入新的订单详情项*/
	@Override
	public int insertOrderItem(Order order, Book book, int quantity, float price) {
		OrderItem orderitem = new OrderItem();
		int item = 0;
		orderitem.setOrder(order);
		orderitem.setBook(book);
		orderitem.setQuantity(quantity);
		orderitem.setPrice(price);
		//根据order的id查询得到相应的订单详情列表，若列表为空，则将item列置值1，若不为空，则将其置为相应列表中的最大值+1
		List<OrderItem> itemList = selectOrderItemByOId(order.getId());
		if(itemList.size()>0) {
			item = itemList.get(itemList.size()-1).getItem() + 1;
		}
		else {
			item = 1;
		}
		orderitem.setItem(item);
		return orderItemDao.insertOrderItem(orderitem);
	}

	/*更新orderitems表中已有的订单详情项*/
	@Override
	public int updateOrderItem(Order order, Book book, int quantity, float price) {
		//根据book的id以及order的id查询得到相应的orderitem项，若为空，则执行插入操作，若不为空，则执行更新操作
		HashMap<String, Integer> idmap = new HashMap<>();
		idmap.put("oid", order.getId());
		idmap.put("bid", book.getId());
		OrderItem orderitem = selectOrderItemById(idmap);
		if(orderitem==null) {
			return insertOrderItem(order, book, quantity, price);
		}
		//更新后的价格
		float update_price = orderitem.getPrice() + price;
		//更新后的数量
		int update_quantity = orderitem.getQuantity() + quantity;
		orderitem.setPrice(update_price);
		orderitem.setQuantity(update_quantity);
		return orderItemDao.updateOrderItem(orderitem);
	}

}
