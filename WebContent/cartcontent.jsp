<%@page import="com.zy.service.BookService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<style type="text/css">
.cart_input{
	width:30px;
	height:30px;
	text-align:center;
	text-vertical-align:middle;
	border:1px solid #dcdcdc;
	border-width:0 1px;
	background-color:#ffffff;
	font-weight:bold;
	padding:0;
	float:left;

}

.cart_image{
	width:80px;
}

.cart_price{
	font-size:14px;
	color:#969696;
}

.cart_totalprice{
	font-size:14px;
	color:#ff2832;
}

a.check_on, a.check_on:hover{
	border-color:#ff2832;
	background:#ff2832;
}
a.checknow{
	display:inline-block;
	width:12px;
	height:12px；
	border:1px solid #bcbcbc;
	text-decoration:none;
	border-radius:3px;
	font-size:0;
	line-height:0;
	overflow:hidden;
	margin:0 10px 0 0;
	vertical-align:text-bottom;
}
.cart_select:link, .cart_select:visited{
	color:#323232;
}


/* .cart_select:link, .cart_select:visited{
	display:inline-block;
	width:12px;
	height:12px；
	border:1px solid #bcbcbc;
	text-decoration:none;
	border-radius:3px;
	font-size:0;
	line-height:0;
	overflow:hidden;
	margin:0 10px 0 0;
	vertical-align:text-bottom;
}
.cart_select:hover, .cart_select:active{
	border-color:#ff2832;
	background:#ff2832;
}
 */
.cart_name:link, .cart_name:visited{
	text-decoration:none;
	color:#323232;
	font-size:14px;
}
.cart_name:hover, .cart_name:active{
	text-decoration:underline;
	color:#ff2832;
	font-size:16px;
}

.cart_quantity:link, .cart_quantity:visited{
	display:block;
	height:32px;
	width:32px;
	background-color:#f4f4f4;
	color:#323232;
	text-decoration:none;
	float:left;
}
.cart_quantity:hover, .cart_quantity:active{
	color:#ff2832;
}

.cart_delete:link, .cart_delete:visited{
	text-decoration:none;
	color:#323232;
	font-size:14px;
}
.cart_delete:hover, .cart_delete:active{
	text-decoration:underline;
	color:#ff2832;
	font-size:14px;
}

</style>

<table width="100%" height="600px" rules="none" border="0" align="center">
	<tr align="center">
		<td align="left">
			<a href="" class="cart_select">选中</a>
			<span class="cart_column">全选</span>
		</td>
		<td colspan="2"><span class="cart_column">商品信息</span></td>
		<td ><span class="cart_column">单价（元）</span></td>
		<td align="left" ><span class="cart_column">数量</span></td>
		<td ><span class="cart_column">金额（元）</span></td>
		<td ><span class="cart_column">操作</span></td>
	</tr>
	
	<!-- 通过EL表达式获取  -->	
	<c:forEach items="${orderitemList}" var="orderitem">
		<tr align="center">
			<td width="5%" ><a href="" class="cart_select checknow check_on">选中</a></td>
			<td width="15%" ><img alt="${orderitem.book.name }" src="images/${orderitem.book.image }" class="cart_image"></td>
			<td align="left" width="40%" ><a href="selectBookById.action?id=${orderitem.book.id}" class="cart_name">${orderitem.book.name}(${orderitem.book.intro})</a></td>
			<td width="10%" ><span class="cart_price">${orderitem.book.price }</span></td>
			<td width="10%">
				<a href="" class="cart_quantity">-</a>
				<input type="text" name="cart_book_quantity" value="${orderitem.quantity}" class="cart_input">
				<a href="" class="cart_quantity">+</a>
			</td>
			<td width="10%" ><span class="cart_totalprice">${orderitem.price}</span></td>
			<td width="10%"><a href="" class="cart_delete">删除</a></td>
		</tr>
	</c:forEach>
	
	
		
	<!-- 通过OGNL表达式获取，但是在计算总价格时，ognl表示式无法像EL表达式一样通过算术运算符直接计算得到 -->
<%-- 	<s:if test="cartMap!=null">
		<s:iterator value="cartMap" var="cart">
			<tr align="center">
				<td width="5%" ><a href="" class="cart_select checknow check_on">选中</a></td>
				<td width="15%" ><img alt="<s:property value='#cart.value.name'/>" src="images/<s:property value='#cart.value.image'/>" class="cart_image"></td>
				<td align="left" width="40%" ><a href="showbook?book_id=<s:property value='#cart.value.id'/>" class="cart_name"><s:property value='#cart.value.name'/>(<s:property value='#cart.value.intro'/>)</a></td>
				<td width="10%" ><span class="cart_price"><s:property value='#cart.value.price'/></span></td>
				<td width="10%">
					<a href="" class="cart_quantity">-</a>
					<input type="text" name="cart_book_quantity" value="<s:property value='#cart.key.bookquantity'/>" class="cart_input">
					<a href="" class="cart_quantity">+</a>
				</td>
				<td width="10%" ><span class="cart_totalprice">0 </span></td>
				<td width="10%"><a href="" class="cart_delete">删除</a></td>
			</tr>
		</s:iterator>
	</s:if>
 --%>
</table>
