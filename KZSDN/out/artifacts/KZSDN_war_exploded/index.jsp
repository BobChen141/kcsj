<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/9/8
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<% String path=request.getContextPath();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>登录界面</title>
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
<div class="container" >
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <font color="#fff8dc">KZSDN登录页面</font>
                </h1>
            </div>
            <form role="form" method="post" id="form" action="login">
                <div class="form-group">
                    <label for="username"><font color="#ff69b4">Username</font></label><input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名" />
                </div>
                <div class="form-group">
                    <label for="password"><font color="#ff69b4">Password</font></label><input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" />
                </div>
                <button type="button" id="registerButton" name="registerButton" onclick="registerverify()">注 册</button>
                <input type="submit" onclick="loginverify()" value="登 录"></input>
                <button type="button" id="regetpwd" name="regetpwd" onclick="regetpassword()">忘记密码</button>
                ${error}${tishi1}
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>

  function loginverify() {
    var username = document.getElementById('username').value;//获取信息为null
    var password = document.getElementById('password').value;
    if(username=='') {
      alert('用户名不能为空!');
      return;
    }
    if(password=='')
    {
      alert('密码不能为空!');
      return;
    }
    //调用后端setvlet，将数据进行传递
    //document.getElementById("form").submit();
    //console.log(document.getElementById("form"));
  }
</script>
<script type="text/javascript">
  function registerverify(){
    window.location = "Register.jsp";
  }
  function regetpassword() {
      window.location="RegetPassword.jsp";
  }
</script>