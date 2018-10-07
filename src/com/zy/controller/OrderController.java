package com.zy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zy.entity.Book;
import com.zy.entity.Order;
import com.zy.entity.OrderItem;
import com.zy.entity.User;
import com.zy.service.BookService;
import com.zy.service.OrderItemService;
import com.zy.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private BookService bookService;
	
	/*增加订单，包括两种情况：
	 * 1、订单不存在，增加订单
	 * 2、订单已存在，更新订单
	 * */
	@RequestMapping("/addOrder")
	public String addOrder(HttpSession session, int bid, int quantity) {
		User user = (User) session.getAttribute("user");
		//根据book的id查询得到相应的book对象
		Book book = bookService.selectBookById(bid);
		//根据用户id查询相应的订单order对象
		Order order = orderService.selectOrderByCId(user.getId());
		//若该用户购物车无订单信息，则新增订单order
		if(order==null) {
			order = new Order();
			order.setUser(user);
			if(orderService.insertOrder(order, book, quantity)>0) {
				return "/addsuccess.jsp";
			}
		}
		//若该用户购物差已有订单信息，则更新订单
		else {
			if(orderService.updateOrder(order, book, quantity)>0) {
				return "/addsuccess.jsp";
			}
		}
		return "/addfail.jsp";
	}
	
	/*显示用户购物车中的订单详情*/
	@RequestMapping("/showOrder")
	public String showOrder(HttpSession session, ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		Order order = orderService.selectOrderByCId(user.getId());
		if(order == null) {
			return "empty";
		}
		List<OrderItem> orderitemList = orderItemService.selectOrderItemByOId(order.getId());
		modelMap.addAttribute("orderitemList", orderitemList);
		return "/shoppingcart.jsp";
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
