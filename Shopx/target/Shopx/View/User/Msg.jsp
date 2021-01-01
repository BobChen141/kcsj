<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>用户管理</title>
		<style type="text/css">
			span{
				text-align: center;
				display: block;
				font-size: 40px;
				}
		</style>
	</head>
	
	<body>
	<%--
		登录的后的提示页面
	 --%>
		<span>${msg}</span>
	</body>
</html>