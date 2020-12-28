<%@ page language="java"  pageEncoding="utf-8"%>
<%@ page import="com.shop.pojo.Goods" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商品展示</title>
		<link rel="stylesheet" type="text/css" href="../../Resours/css/Goods.css"/>
		<link rel="stylesheet" type="text/css" href="../../Resours/css/bootstrap.min.css">
	</head>
	<body>
	
	<c:forEach items="${list}" var="g"> 

		<div id="foods">
			<!-- <a href="${pageContext.request.contextPath }/GoodsServlet?action=addgoods&g_id=${g.g_id }" target="_blank" onclick="add()"> -->
			<span style="visibility: hidden">${g.g_id }</span>
			<img id="img" src="../../Resours/images/Goods/${g.g_category }/${g.g_picture}" class="img-thumbnail">
			<br />
			<span id="describe">商品描述：${g.g_describe }</span>
			<br />
			<span id="price">价格：${g.g_price }元</span>
			<br>
			<button onclick="add(this)" class="btn btn-success btn-sm">添加到购物车</button>

			<button onclick="gotoSc(this)" class="btn btn-success btn-sm">去购物车</button>
		</div>

	</c:forEach> 
		
	</body>
	<script type="text/javascript"
			src="../../Resours/js/jquery-3.3.1.min.js"></script>
		<script>
		function add(btn){
			var gid = $(btn).parent().children("span:first-child").text();
			
			 /*发送ajax请求*/
            $.ajax({
				method:'get',
				data:{'g_id': gid},
				url:'/Shopx//GoodsServlet?action=addgoods',
				success:function(data){
					alert(data);
				},
				dataType:'json'
            });
		}
		function gotoSc(){
			/*发送ajax请求*/
			$.ajax({
				method:'get',
				url:'/Shopx/GoodsServlet?action=checkinsc',
				success:function(data){
					if(data === 0){//没登录
						alert("进入购物车前请先登录");
					}else if(data === 1){//已登录
						window.location.href = "ShopCart.html";
					}
				},
				dataType:'json'
			});
		}
		
		</script>
</html>

