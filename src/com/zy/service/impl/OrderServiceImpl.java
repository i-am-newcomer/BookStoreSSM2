package com.zy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.dao.OrderDao;
import com.zy.entity.Book;
import com.zy.entity.Order;
import com.zy.service.OrderItemService;
import com.zy.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemService orderItemService;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}
	

	@Override
	public Order selectOrderByCId(int cid) {
		return orderDao.selectOrderByCId(cid);
	}

	@Override
	public Order selectOrderById(int id) {
		return orderDao.selectOrderById(id);
	}

	/*新增订单*/
	@Override
	public int insertOrder(Order order, Book book, int quantity) {
		//加入购物车中的相应数量书的价格
		float price = book.getPrice()*quantity;
		order.setPrice(price);
		order.setTime(getTimeNow());
		if(orderDao.insertOrder(order)>0) {
			order = orderDao.selectOrderByCId(order.getUser().getId());
			return orderItemService.insertOrderItem(order, book, quantity, price);
		}
		return -1;
	}

	/*更新订单*/
	@Override
	public int updateOrder(Order order, Book book, int quantity) {
		order.setTime(getTimeNow());
		float price = book.getPrice()*quantity;
		order.setPrice(order.getPrice()+price);
		if(orderDao.updateOrder(order)>0) {
			return orderItemService.updateOrderItem(order, book, quantity, price);
		}
		return -1;
	}
	
	/*得到特定格式的当前的具体时间*/
	private String getTimeNow() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timenow = sdf.format(date);
		//System.out.println("timenow:"+timenow);
		return timenow;
	}
	

}
