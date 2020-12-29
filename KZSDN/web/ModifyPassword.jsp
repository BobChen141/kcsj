<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/9/17
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改密码</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body >
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    修改密码
                </h1>
            </div>
            <form role="form" method="post" id="form" action="modifypwd">
                <div class="form-group">
                    <label for="username">Username（不可修改）</label><input type="text" class="form-control" id="username" name="username" value="${user.username}" readonly="readonly" />
                </div>
                <div class="form-group">
                    <label for="password">Password</label><input type="password" class="form-control" id="password3" name="password3" placeholder="请输入原密码" />
                </div>
                <div class="form-group">
                    <label for="password">NewPassword</label><input type="password" class="form-control" id="password" name="password" placeholder="请输入要修改的密码" />
                </div>
                <div class="form-group">
                    <label for="password">RePassword</label><input type="password" class="form-control" id="password2" name="password2" placeholder="再次输入确认修改密码" />
                </div>
                <button type="button" class="btn btn-default" onclick="check()">确 认</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>
    function check() {
        var pwd1=document.getElementById('password').value;
        var pwd2=document.getElementById('password2').value;
        var pwd3=document.getElementById('password3').value;
        if(pwd3!=${user.password}){
            alert('原密码错误！');
            return;
        }
        if(pwd1!=pwd2){
            alert('两次密码不一致!');
            return;
        }
        $("#form").submit();
    }
</script>