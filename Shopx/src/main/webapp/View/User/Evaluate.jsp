<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>评价页面</title>
		<style type="text/css">
			
		</style>
		<link rel="stylesheet" type="text/css" href="../../Resours/css/bootstrap.min.css">
	</head>
	<body>
	<button onclick="evl()" class="btn btn-primary btn-sm">我要评论</button>
	<ul class="list-group">
	<c:forEach items="${evalist}" var="e">
				  <li class="list-group-item">评论用户：${e.name }</li>
				  <li class="list-group-item">评论时间：${e.etime }</li>
				  <li class="list-group-item">评论内容：${e.econtent }</li>
				 <li></li>
	</c:forEach>
	</ul>
	</body>
	<script src="../../Resours/js/jquery-3.3.1.min.js"></script>
		<script>
		function evl(){
			 /*发送ajax请求*/
            $.ajax({
				method:'get',
				url:'/Shopx/UserServlet?action=checkinel',
				success:function(data){
					if(data === 0){//没登录
						alert("发表评论前请先登录");
					}else if(data === 1){//已登录
						window.location.href = "writeEval.html";
					}
				},
				dataType:'json'
            });
		}
		</script>
	
</html>