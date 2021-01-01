<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="java.util.*" %>
<%@ page import="com.shop.pojo.Goods" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>结算页面</title>
		<link rel="stylesheet" type="text/css"
	href="../../Resours/css/Shopcart.css" />
	<link rel="stylesheet" type="text/css" href="../../Resours/css/bootstrap.min.css">
	</head>
	<body>
		<div id="head">
			<div id="head_top" >
				<span id="name"></span>
			</div>
		</div>
		<div id="bottom">
			<form action="${pageContext.request.contextPath }/GoodsServlet?action=topay&sums=${sums}" method="post">
				<table id="table" >
					<tr style="height: 50px;">
						<th style="width: 25%;">图片</th>
						<th style="width: 15%;">商品名，描述</th>
						<th style="width: 15%;">数量</th>
						<th style="width: 15%;">价格</th>
						<th style="width: 15%;">小计</th>
					</tr>
					<c:forEach items="${countslist }" var="c">
					<tr style="height:100px;">
						<td style="width: 25%;">
							<img  id="logo"  src="../../Resours/images/Goods/${c.value.g_category }/${c.value.g_picture}" >
						</td>
						<td style="width: 15%;">${c.value.g_describe }</td>
						<td style="width: 15%;">
							<span id="">
								${c.value.count}
							</span>
							</td>
						<td style="width: 15%;">${c.value.g_price }元</td>
						<td style="width: 15%;">${c.key }元</td>
					</tr>
					</c:forEach>
				</table>
				<span id="sum">
					共计${sums}元
				</span>
				<input id="submit" type="submit" value="提交订单" class="btn btn-success btn-sm "/>
			</form>
		</div>
		<script type="text/javascript"
			src="../../Resours/js/jquery-3.3.1.min.js"></script>
		<script>
		function fun2() {
			
            //发送ajax请求get类型
            $.get("/Shopx/GoodsServlet?action=checkname",function (data) {
         	   var span = $("#name");
         	   span.html("用户名："+data);
            },'json');
        	
		}
		fun2();
		</script>
		
	</body>
</html>
