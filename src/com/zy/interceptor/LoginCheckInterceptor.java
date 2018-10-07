package com.zy.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.zy.entity.User;

public class LoginCheckInterceptor implements HandlerInterceptor {
	private List<String> allowedPass;
	
	public void setAllowedPass(List<String> allowedPass) {
		this.allowedPass = allowedPass;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURL().toString();
		for(String item:allowedPass) {
			if(url.endsWith(item)) {
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("user");
				System.out.println("user:"+user);
				if(user!=null) {
					return true;
				}
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return false;
			}
		}
		return true;

	}

}
