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
            height: 300px;
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
        /*模态框*/
        .modal {
            display: none; /* 默认隐藏 */
            position: fixed; /* 根据浏览器定位 */
            z-index: 1; /* 放在顶部 */
            left: 0;
            top: 0;
            width: 100%; /* 全宽 */
            height: 100%; /* 全高 */
            overflow: auto; /* 允许滚动 */
            background-color: rgba(0,0,0,0.4); /* 背景色 */
        }
        /*模态框内容*/
        .modal-content {
            display: flex; /*采用flexbox布局*/
            flex-direction: column; /*垂直排列*/
            position: relative;
            background-color: #fefefe;
            margin: 15% auto; /*距顶部15% 水平居中*/
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            animation: topDown 0.4s; /*自定义动画，从模态框内容上到下出现*/
        }
        @keyframes topDown {
            from {top: -300px; opacity: 0}
            to {top: 0; opacity: 1}
        }
        /*模态框头部*/
        .modal-header {
            display: flex; /*采用flexbox布局*/
            flex-direction: row; /*水平布局*/
            align-items: center; /*内容垂直居中*/
            justify-content: space-between;
        }
        /*关闭X 样式*/
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>

</head>
<body  style="background-image: url(images/background2.jpg);background-attachment: fixed">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-2 column">
            <ul>
                <h2>
                 <a target="_blank" href="Menu2.jsp">首页博客分类</a>
                </h2>
            </ul>
        </div>
        <div class="col-md-10 column">
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
                  <div class="form-group">
                      <form role="form" method="post" id="form6" action="search" accept-charset="UTF-8">
                  <input type="text" value="${tcontent}" id="searchtext" name="searchtext" class="form-control" placeholder="搜博客"/>
                  <button type="submit" class="btn btn-default" >搜索</button>
                      </form>
                          <button type="button" id="triggerBtn" class="btn btn-default" >搜索博主</button>
                  </div>
            <hr>
            <div>
                <h3><font color="#ff69b4">首页推荐博客</font></h3>
                <c:forEach items="${menu_blogs}" var="item">
                  <c:if test="${item.statement==1}">
                    <div class="blog" style="position: relative">
                        <div>
                            <span><p>标题：${item.title}</p></span>
                            <span><p>作者：${item.author}</p></span>
                            <span><p>标签：${item.label}&nbsp${item.label2}&nbsp${item.label3}</p></span>
                        </div>
                        <div>
                            <div class="item_left"><span><p>${item.origin}</p></span></div>
                            <div class="item_left"><span><p>${item.time}</p></span></div>
                            <form role="form" method="post" id="form7" action="checkblog1">
                                <input type="hidden" value="${item.id}" id="check_id" name="check_id">
                                <div class="item_right"><span><input type="submit" id="checkbt" name="checkbt" value="查看"></span></div>
                            </form>
                        </div>
                    </div>
                    <hr>
                  </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<!-- 模态框 -->
<div id="myModal" class="modal" style="position: absolute; z-index: 10">
    <form role="form" method="post" id="form123" action="searchuser">
        <div class="modal-content">
            <div class="modal-header">
                <h2>搜索博主</h2>
                <span id="closeBtn" class="close">×</span>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="user"><font >博主用户名</font></label>
                    <input form="form123" type="text" class="form-control" id="user" name="user" placeholder="请输入要搜索博主用户名" />
                </div>
            </div>
            <div class="modal-footer">
            </div>
            <div class="form-group">
                <input form="form123" type="button" value="确 认" id="check" name="check"></input>
            </div>
        </div>
    </form>
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
    (function() {
        /*建立模态框对象*/
        var modalBox = {};
        /*获取模态框*/
        modalBox.modal = document.getElementById("myModal");
        /*获得trigger按钮*/
        modalBox.triggerBtn = document.getElementById("triggerBtn");
        /*获得关闭按钮*/
        modalBox.closeBtn = document.getElementById("closeBtn");
        /*模态框显示*/
        modalBox.show = function() {
            console.log(this.modal);
            this.modal.style.display = "block";
        }
        /*模态框关闭*/
        modalBox.close = function() {
            this.modal.style.display = "none";
        }
        /*当用户点击模态框内容之外的区域，模态框也会关闭*/
        modalBox.outsideClick = function() {
            var modal = this.modal;
            window.onclick = function(event) {
                if(event.target == modal) {
                    modal.style.display = "none";
                }
            }
        }
        /*模态框初始化*/
        modalBox.init = function() {
            var that = this;
            this.triggerBtn.onclick = function() {
                that.show();
            }
            this.closeBtn.onclick = function() {
                that.close();
            }
            this.outsideClick();
        }
        modalBox.init();
    })();
    $("#check").click(function () {
        var title = $("#user").val();

        if(isEmpty(title)){
            alert("内容不能为空！");
            return;
        }
        document.getElementById("form123").submit();

    });
    function isEmpty(str) {
        if(str == null || str.trim()== ""){
            return true;
        }
        return false;
    }
</script>