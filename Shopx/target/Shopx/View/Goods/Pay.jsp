<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>支付页面</title>
<link rel="stylesheet" type="text/css" href="../../Resours/css/bootstrap.min.css">
</head>
<body>
<h1>您需要支付：${pay}元</h1>
<span>${paymsg}</span>
<form action="${pageContext.request.contextPath }/GoodsServlet?action=pay&money=${pay}" method="post">
	<input type="password"  placeholder="请输入密码进行支付" name="pwd">
	<input type="submit" value="支付" class="btn btn-success btn-sm ">
</form>
</body>
</html>