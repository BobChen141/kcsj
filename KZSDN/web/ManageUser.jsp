<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/9/20
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改个人信息</title>
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
                    <font color="blue">修改个人信息</font>
                </h1>
            </div>
            <form role="form" method="post" id="form" action="mresetuser">
                <div class="form-group">
                    <label for="username">用户名</label><input type="text" class="form-control" id="username" name="username" value="${user1.username}" readonly="readonly" placeholder="不可修改"/>
                </div>
                <div class="form-group">
                    <label for="introduction">密码</label><input type="text" class="form-control" id="password" name="password" value="${user1.password}" />
                </div>
                <div class="form-group">
                    <label for="introduction">邮箱</label><input type="text" class="form-control" id="email" name="email" value="${user1.email}" />
                </div>
                <div class="form-group">
                    <label for="introduction">简介</label><input type="text" class="form-control" id="introduction" name="introduction" value="${user1.introduction}" />
                </div>
                <button type="submit" class="btn btn-default" >确认修改</button>${error}
                <span id="msg1" style="font-size:12px;color:red"></span>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function checkall() {
        var name = document.getElementById('name').value;
        var gender = document.getElementById('gender').value;
        var birth = document.getElementById('birth').value;
        var phone = document.getElementById('phone').value;
        var introduction = document.getElementById('introduction').value;
        var reg =/^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/
        var reg2=/^(13[0-9]|14[0-9]|15[0-9]|166|17[0-9]|18[0-9]|19[8|9])\d{8}$/
        if(gender!="男"&&gender!="女"){
            alert('请填写正确性别（男或女）!');
            return;
        }
        if(!reg.test(birth)){
            alert('请填写正确日期格式!');
            return;
        }
        if(!reg2.test(phone)){
            alert('请填写正确手机号码！');
            return;
        }
        document.getElementById("form").submit();
    }
</script>
<script>
    function f_open() {
        document.getElementById("btn_file").click();
        //把所选文件上传到项目，再把在项目中对应的地址存到数据库
    }
    function upload() {
        var username=document.getElementById("username").value;
        var request = ajaxFunction(); //创建xmlHttpRequest对象
        var image=document.getElementById("uploadFile").files[0];
        var data = new FormData();
        data.append("image",image);
        //发送请求，参数分别为：发送方式   发送地址   是否异步传输
        request.open("post", "/proupload" , false);
        request.send(data);
        var responseText = request.responseText;
        console.log(responseText);

        document.getElementById("user_image").src = responseText;
        document.getElementById("touxiang").value= responseText;

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