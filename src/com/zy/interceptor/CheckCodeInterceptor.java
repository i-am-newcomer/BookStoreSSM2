package com.zy.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.plugin.Intercepts;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*验证码拦截器，用于用户注册和登录时验证码信息的检查*/
public class CheckCodeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String in_checkcode = request.getParameter("in_checkcode");
		String checkcode =  (String)request.getSession().getAttribute("checkcode");
		System.out.println(in_checkcode);
		if(checkcode.equals(in_checkcode)) {
			return invocation.invoke();
		}
		request.setAttribute("checkcode_result", "验证码输入错误");
		return Action.ERROR;
	}
	

}
