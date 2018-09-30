<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Book Store</title>

<style type="text/css">
.image1{
	width:150px;
}

.book1:link,.book1:visited{
	display:block;
	background-color:#FF2832;
	color:#ffffff;
	font-weight:bold;
	width:120px;
	text-align:center;
	text-decoration:none;
	padding:4px;
}
.book1:hover,.book1:active{
	background-color:#ff6347;
}

</style>

</head>
<body>
<table width="90%" height="600px" border="0" align="center">
	<tr height="10%"><td colspan="2">
		<jsp:include page="header1.jsp" flush="true" />
	</td></tr>
	<tr><td>
	
	<!-- 通过EL表达式获取 -->
	<%-- 	<table width="90%" border="0" align="center">
			<tr align="left">
				<td rowspan="10" ><img alt="${book.name}" src="images/${book.image}" class="image1"></td>
				<td><h2>${book.name}</h2></td>
				<td align="left"><h2>(${book.intro})</h2></td>
			</tr>
			<tr align="left">
				<td><span>作者：${book.author}</span></td>
				<td><span>出版社：${book.publisher}</span></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr align="left">
				<td><span>价格：${book.price}</span></td>
				<td><span>库存数量：${book.amount}</span></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr ><td colspan="2">服务:&nbsp;&nbsp;由“Book&nbsp;Store”发货，并提供售后服务。 今日19:45前完成下单，预计后天可送达</td></tr>
			<tr><td><br/></td></tr>
			<tr>
				<td align="right"><a href="addCart.action?book_id=${book.id}&book_quantity=1" target="_blank" class="book1">加入购物车</a></td>
				<td align="left"><a href="" target="_blank" class="book1">立即购买</a></td>
			</tr>
		</table> --%>
		
		<!-- 通过OGNL表达式获取 -->
		<table width="90%" border="0" align="center">
			<tr align="left">
				<td rowspan="10" ><img alt='<s:property value="book.name"/>' src='images/<s:property value="book.image"/>' class="image1"></td>
				<td><h2><s:property value="book.name"/></h2></td>
				<td align="left"><h2>(<s:property value="book.intro"/>)</h2></td>
			</tr>
			<tr align="left">
				<td><span>作者：<s:property value="book.author"/></span></td>
				<td><span>出版社：<s:property value="book.publisher"/></span></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr align="left">
				<td><span>价格：<s:property value="book.price"/></span></td>
				<td><span>库存数量：<s:property value="book.amount"/></span></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr ><td colspan="2">服务:&nbsp;&nbsp;由“Book&nbsp;Store”发货，并提供售后服务。 今日19:45前完成下单，预计后天可送达</td></tr>
			<tr><td><br/></td></tr>
			<tr>
				<td align="right"><a href='addOrder.action?bid=<s:property value="book.id"/>&quantity=1' target="_blank" class="book1">加入购物车</a></td>
				<td align="left"><a href="" target="_blank" class="book1">立即购买</a></td>
			</tr>
		</table>
		
	</td></tr>
	<tr height="5%"><td colspan="2">
		<jsp:include page="footer.jsp" flush="true" />
	</td></tr>
</table>
</body>
</html>