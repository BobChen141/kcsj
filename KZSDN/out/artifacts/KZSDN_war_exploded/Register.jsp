<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/9/10
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<% String path=request.getContextPath();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册界面</title>
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
            <div class="page-header">
                <h1>
                    <font color="#fff8dc">KZSDN注册页面</font>
                </h1>
            </div>
            <form role="form" method="post" id="form" action="register">
                <div class="form-group">
                    <label for="username"><font color="#ff69b4">Username></font></label><input type="text" class="form-control" id="username" name="username" value="${username}" placeholder="请输入用户名"/>
                </div>
                <div class="form-group">
                    <label for="password"><font color="#ff69b4">Password</font></label><input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" />
                </div>
                <div class="form-group">
                    <label for="password"><font color="#ff69b4">RePassword</font></label><input type="password" class="form-control" id="password2" name="password2" placeholder="再次输入确认密码" />
                </div>
                <div class="form-group">
                    <label for="email"><font color="#ff69b4">Email</font></label><input type="text" class="form-control" id="email" name="email" placeholder="请输入可用邮箱"/>
                </div>
                <div class="form-group">
                    <label for="yzm"><font color="#ff69b4">验证码</font></label><input type="text" class="form-control" id="YZM" name="YZM" placeholder="请输入收到有效验证码"/>

                    <button type="button" class="btn btn-default" id="getyzm" >获取验证码</button>
                     ${yzmerror}
                </div>
                <button type="submit" class="btn btn-default" >注 册</button>${error}${tishi2}
                <span id="msg1" style="font-size:12px;color:red"></span>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="https://upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.2.min.js"></script>
<script type="text/javascript"></script>
</html>


<script>
    $("#getyzm").click(function () {

        //获取邮箱
        var email = $("#email").val();
        //获取用户昵称密码
        var username = $("#username").val();
        var password = $("#password").val();
        var password2 = $("#password2").val();
        if (isEmpty(username)) {
            $("#msg1").html("用户名不可以为空！");
            return;
        } else {
            $("#msg1").html(" ");
        }
        if (isEmpty(password)) {
            $("#msg1").html("密码不可以为空！");
            return;
        } else {
            $("#msg1").html(" ");
        }
        if (isEmpty(password2)) {
            $("#msg1").html("请再次输入密码！");
            return;
        } else {
            $("#msg1").html(" ");
        }
        if (password === password2) {
            $("#msg1").html(" ");
        } else {
            $("#msg1").html("两次密码不一致！");
            return;
        }
        var reg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
        if (!reg.test($('#email').val())) {
            $("#msg1").html("请输入格式正确的e-mail地址");
            return;
        } else {
            $("#msg1").html(" ");
        }
        sendMCode();
        $("#msg1").html("验证码发送成功");


    });

    function sendMCode() {

        var request = ajaxFunction(); //创建xmlHttpRequest对象
        var address = $('#email').val(); //用户输入的邮箱地址
        var myButton = $("#getyzm"); //获取验证码按钮

        myButton.attr("disabled", true); //使按钮失效
        myButton.css("background-color", "#EAEAEA"); //改变按钮颜色

        //按钮失效倒计时
        var count = 60;
        countTimes();

        function countTimes() {
            myButton.html("重新发送(" + count + "s)");
            count--;
            if (count > 0) {
                setTimeout(countTimes, 1000);
            } else if (count === 0) {
                myButton.html("重新发送");
                myButton.attr("disabled", false); //重新激活按钮
                myButton.css("background-color", "#ff688f");
            }
        }

        //发送请求，参数分别为：发送方式   发送地址   是否异步传输
        request.open("GET", "sendEmail?address=" + address+"&username="+null, true);
        request.send();

    }
    function isEmpty(str) {
        if(str == null || str.trim()== ""){
            return true;
        }
        return false;
    }

//创建xmlHttpRequest对象
    function ajaxFunction() {
        var xmlHttp;
        try {
            xmlHttp = new XMLHttpRequest();
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        return xmlHttp;
    }
</script>