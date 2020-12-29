<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/9/20
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人信息</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>


</head>
<body style="background-image: url(images/background2.jpg)">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3><a href="Menu.jsp"><font color= #00ffff>返回主页</font></a></h3>
                    <h3 class="panel-title">
                        用户资料卡
                    </h3>
                </div>
                <div class="panel-body">
                    头像
                    <img src="${user1.touxiang}"  width="200" height="200">
                </div>
                <div class="panel-body">
                    用户名：${user1.username}
                </div>
                <div class="panel-body">
                    姓名：${user1.name}
                </div>
                <div class="panel-body">
                    性别：${user1.gender}
                </div>
                <div class="panel-body">
                    生日：${user1.birth}
                </div>
                <div class="panel-body">
                    联系方式：${user1.phone}
                </div>
                <div class="panel-body">
                    简介：${user1.introduction}
                </div>
                <div class="panel-footer">
                    <form role="form" method="post" id="form" action="userbloglist">
                    <input type="hidden" id="getusername" name="getusername" value="${user1.username}">
                    <input type="submit" value="他的博客">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
