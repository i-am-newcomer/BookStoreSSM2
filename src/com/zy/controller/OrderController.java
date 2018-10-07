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
	
	/*���Ӷ������������������
	 * 1�����������ڣ����Ӷ���
	 * 2�������Ѵ��ڣ����¶���
	 * */
	@RequestMapping("/addOrder")
	public String addOrder(HttpSession session, int bid, int quantity) {
		User user = (User) session.getAttribute("user");
		//����book��id��ѯ�õ���Ӧ��book����
		Book book = bookService.selectBookById(bid);
		//�����û�id��ѯ��Ӧ�Ķ���order����
		Order order = orderService.selectOrderByCId(user.getId());
		//�����û����ﳵ�޶�����Ϣ������������order
		if(order==null) {
			order = new Order();
			order.setUser(user);
			if(orderService.insertOrder(order, book, quantity)>0) {
				return "/addsuccess.jsp";
			}
		}
		//�����û���������ж�����Ϣ������¶���
		else {
			if(orderService.updateOrder(order, book, quantity)>0) {
				return "/addsuccess.jsp";
			}
		}
		return "/addfail.jsp";
	}
	
	/*��ʾ�û����ﳵ�еĶ�������*/
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
