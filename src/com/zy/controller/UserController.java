package com.zy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zy.entity.User;
import com.zy.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*用户注册*/
	@RequestMapping("/register")
	public String register(HttpServletRequest request, User user, String confirm_pwd, String in_checkcode) {
		String checkcode = (String)request.getSession().getAttribute("checkcode");
		String result = userService.register(user, confirm_pwd, in_checkcode, checkcode);
		//注册成功
		if(result.equals("success")) {
			return "/register_success.jsp";
		}
		//注册失败
		request.setAttribute("register_result", result);
		return "/register.jsp";
	}
	
	/*检查用户是否已经登录，若已登录则提示已登录，若没有则转到登录页面*/
	@RequestMapping("/beforeLogin")
	public String beforeLogin(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user!=null) {
			return "/login_success.jsp";
		}
		return "/login.jsp";
	}
	
	/*用户登录*/
	@RequestMapping("/login")
	public String login(HttpServletRequest request, String logName, String logPwd, String in_checkcode) {
		HttpSession session = request.getSession();
		String checkcode = (String)session.getAttribute("checkcode");
		Object result = userService.login(logName, logPwd, in_checkcode, checkcode);
		//若result是user类型，则表示登录成功，返回"success"并将user信息保存在会话属性中
		if(result.getClass().isInstance(new User())) {
			User user = (User)result;
			session.setAttribute("user", user);
			return "/login_success.jsp";
		}
		//若result不是user类型，则表示登陆失败，返回到登录页面并将错误信息result保存在请求属性中
		request.setAttribute("login_result", (String)result);
		return "/login.jsp";
	}
	
	/*退出登录*/
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		return "/login.jsp";
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
