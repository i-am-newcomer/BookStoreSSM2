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
	
	/*�û�ע��*/
	@RequestMapping("/register")
	public String register(HttpServletRequest request, User user, String confirm_pwd, String in_checkcode) {
		String checkcode = (String)request.getSession().getAttribute("checkcode");
		String result = userService.register(user, confirm_pwd, in_checkcode, checkcode);
		//ע��ɹ�
		if(result.equals("success")) {
			return "/register_success.jsp";
		}
		//ע��ʧ��
		request.setAttribute("register_result", result);
		return "/register.jsp";
	}
	
	/*����û��Ƿ��Ѿ���¼�����ѵ�¼����ʾ�ѵ�¼����û����ת����¼ҳ��*/
	@RequestMapping("/beforeLogin")
	public String beforeLogin(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user!=null) {
			return "/login_success.jsp";
		}
		return "/login.jsp";
	}
	
	/*�û���¼*/
	@RequestMapping("/login")
	public String login(HttpServletRequest request, String logName, String logPwd, String in_checkcode) {
		HttpSession session = request.getSession();
		String checkcode = (String)session.getAttribute("checkcode");
		Object result = userService.login(logName, logPwd, in_checkcode, checkcode);
		//��result��user���ͣ����ʾ��¼�ɹ�������"success"����user��Ϣ�����ڻỰ������
		if(result.getClass().isInstance(new User())) {
			User user = (User)result;
			session.setAttribute("user", user);
			return "/login_success.jsp";
		}
		//��result����user���ͣ����ʾ��½ʧ�ܣ����ص���¼ҳ�沢��������Ϣresult����������������
		request.setAttribute("login_result", (String)result);
		return "/login.jsp";
	}
	
	/*�˳���¼*/
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		return "/login.jsp";
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
