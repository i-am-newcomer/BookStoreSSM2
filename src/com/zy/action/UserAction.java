package com.zy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.zy.entity.User;
import com.zy.service.UserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction {
	
	@Autowired
	private UserService userService;
	private User user;
	private String confirm_pwd;
	private String logName;
	private String logPwd;
	
	/*用户注册*/
	public String register() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String result = userService.register(user, confirm_pwd);
		//注册成功
		if(result.equals("success")) {
			return Action.SUCCESS;
		}
		//注册失败
		request.setAttribute("register_result", result);
		return Action.ERROR;
	}
	
	/*检查用户是否已经登录，若已登录则提示已登录，若没有则转到登录页面*/
	public String beforeLogin() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		user = (User)session.getAttribute("user");
		if(user!=null) {
			return Action.SUCCESS;
		}
		return Action.LOGIN;
	}
	
	/*用户登录*/
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Object result = userService.login(logName, logPwd);
		//若result是user类型，则表示登录成功，返回"success"并将user信息保存在会话属性中
		if(result.getClass().isInstance(new User())) {
			user = (User)result;
			session.setAttribute("user", user);
			return Action.SUCCESS;
		}
		//若result不是user类型，则表示登陆失败，返回到登录页面并将错误信息result保存在请求属性中
		request.setAttribute("login_result", (String)result);
		return Action.LOGIN;
	}
	
	/*退出登录*/
	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("user", null);
		return Action.LOGIN;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirm_pwd() {
		return confirm_pwd;
	}

	public void setConfirm_pwd(String confirm_pwd) {
		this.confirm_pwd = confirm_pwd;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogPwd() {
		return logPwd;
	}

	public void setLogPwd(String logPwd) {
		this.logPwd = logPwd;
	}
	

}
