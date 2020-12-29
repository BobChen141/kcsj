<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/9/14
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KZSDN主界面</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <style>
        .blog{
            background:  rgba(255, 255, 255, 0.5);
            padding: 14px;
            border-left: 6px solid #ccc!important;
            border-color: yellow;
            overflow: hidden;
        }
        .item_left{
            float: left;
            margin-right: 10px;
        }
        .item_right{
            float: right;
            margin-left: 10px;
        }
        .leftbtn{
            position: fixed;
            left: 70px;
            width: 200px;
            height: 200px;
            float:left;
            background:  rgba(255, 255, 255, 0.5);
        }
        .leftbtn ul {list-style-type: none;margin: 0;padding: 0; position: absolute;top:20px;left: 5px;}
        .leftbtn a {display: block;background-color: #2c2e2e;
            color: #d3d8d8;width: 180px;text-align: center;padding: 4px;text-decoration: none;
            font-size: 21px;font-family: cursive;<!--草书 -->
        }
        .leftbtn li{
            white-space: nowrap; cursor: pointer;/*设置鼠标箭头手势*/
        }

    </style>

</head>
<body  style="background-image: url(images/background2.jpg);background-attachment: fixed">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h1 class="text-center">
                <font color="#8a2be2">KZSDN</font>
            </h1>
            <form role="form" method="post" id="form" action="Blog.jsp">
                <div align="left" style="position: relative;top:55px">
                    <input type="submit" name="writeblog" id="writeblog" style="width:100px;height:60px" value="写博客"></input>
                </div>
            </form>
            <ul class="nav nav-tabs">
                <li class="dropdown pull-right">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle"><font color="#ff1493">${username}的个人中心</font><strong class="caret"></strong></a>
                    <ul class="dropdown-menu">
                        <form role="form" method="post" id="form1" action="personalData">
                            <input type="submit" name="personaldata" id="personaldata" value="个人信息"></input>
                        </form>
                        <form role="form" method="post" id="form2" action="modifyData">
                            <input type="submit" name="modifydata" id="modifydata" value="编辑个人信息"></input>
                        </form>
                        <form role="form" method="post" id="form3" action="ModifyPassword.jsp">
                            <input type="submit" name="modifypwd" id="modifypwd" value="修改密码"></input>
                        </form>
                        <form role="form" method="post" id="form4" action="bloglist">
                            <input type="hidden" id="username" name="username" value="${username}">
                            <input type="submit" name="moblog" id="mybolg" value="我的博客"></input>
                        </form>
                        <form role="form" method="post" id="form5" action="collections">
                            <input type="hidden" id="username1" name="username1" value="${username}">
                            <input type="submit" name="collection" id="collection" value="我的收藏"></input>
                        </form>
                        <form role="form" method="post" id="form8" action="column">
                            <input type="hidden" id="username2" name="username2" value="${username}">
                            <input type="submit" name="column" id="column" value="管理分类专栏"></input>
                        </form>
                        <a href="index.jsp">退出</a>
                    </ul>
                </li>
            </ul>
            <div>
                <h3><font color="#ff69b4">博主</font></h3>
                <hr>
                <c:forEach items="${users}" var="item">
                        <div class="blog" style="position: relative">
                            <div>
                                <span><p>用户名：${item.username}</p></span>
                                <img src="${item.touxiang}"style="width: 70px;height: 70px">
                            </div>
                            <div>
                                <form role="form" method="post" id="form7" action="checkuser">
                                    <input type="hidden" value="${item.username}" id="check_id" name="check_id">
                                    <div class="item_right"><span><input type="submit" id="checkbt" name="checkbt" value="查看"></span></div>
                                </form>
                            </div>
                        </div>
                        <hr>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="modalBox.js"></script>
</body>
</html>
<script>
    function search() {
        var username=document.getElementById("searchtext").value;
        var request = ajaxFunction(); //创建xmlHttpRequest对象
        //发送请求，参数分别为：发送方式   发送地址   是否异步传输
        request.open("GET", "search?searchtext=" + searchtext, true);
        request.send();

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