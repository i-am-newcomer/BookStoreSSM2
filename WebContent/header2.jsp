<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<table width="100%" height="80px" border="1" align="center" rules="none" bgcolor="#333333">
	<tr>
		<td width="20%" align="center"><span style="color:#ffffff;font-size:30px;">BOOK STORE</span></td>
		
		<!-- 通过EL表达式获取  -->
		<td width="40%" align="left" valign="bottom"><span style="color:#ffffff;font-size:24px;">欢迎${param.subTitle}</span></td>
		<!-- 通过OGNL表达式获取，ognl表达式在这里对登录页面不起作用，对注册页面显示正常  -->
<%-- 		<td width="40%" align="left" valign="bottom"><span style="color:#ffffff;font-size:24px;">欢迎<s:property value='#parameters.subTitle'/></span></td> --%>
		
		<td width="10%" align="center"><a href="default.jsp" style="text-decoration:none;color:#ffffff;">首页</a></td>
		<td width="10%" align="center"><a href="register.jsp" style="text-decoration:none;color:#ffffff;">注册</a></td>
		<td width="10%" align="center"><a href="beforeLogin.action" style="text-decoration:none;color:#ffffff;">登录</a></td>
		<td width="10%" align="center"><a href="logout.action" style="text-decoration:none;color:#ffffff;">退出</a></td>
	</tr>
</table>
<br/><br/>