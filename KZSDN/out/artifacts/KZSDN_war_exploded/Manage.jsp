<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/10/7
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理界面</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <style>
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
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h2 class="text-center">
                管理员界面
            </h2>
            <h3>
                管理用户
            </h3>
            <table class="table">
                <thead>
                <tr>
                    <th>
                        用户名
                    </th>
                    <th>
                        密码
                    </th>
                    <th>
                        邮箱
                    </th>
                    <th>
                        姓名
                    </th>
                    <th>
                        操作1
                    </th>
                    <th>
                        操作2
                    </th>
                </tr>
                </thead>
                <c:forEach items="${users}" var="item">
                <tbody>
                <tr>
                    <td>
                        ${item.username}
                    </td>
                    <td>
                            ${item.password}
                    </td>
                    <td>
                            ${item.email}
                    </td>
                    <td>
                        ${item.name}
                    </td>
                    <td>
                        <form role="form" method="post" id="form" action="deleteuser">
                            <input type="hidden" value="${item.username}" id="dusername" name="dusername">
                        <input type="submit" value="删除" id="s" name="s">
                        </form>

                    </td>
                    <td>
                        <form role="form" method="post" id="form2" action="muser">
                            <input type="hidden" value="${item.username}" id="musername" name="musername">
                            <input type="submit" value="修改" id="sb" name="sb">
                        </form>
                    </td>
                </tr>
                </tbody>
                </c:forEach>

            </table>
            <h3>
                管理博客
            </h3>
            <table class="table">
                <thead>
                <tr>
                    <th>
                        博客ID
                    </th>
                    <th>
                        博客标题
                    </th>
                    <th>
                        作者
                    </th>
                    <th>
                        发布时间
                    </th>
                    <th>
                        操作1
                    </th>
                    <th>
                        操作2
                    </th>
                    <th>
                        操作3
                    </th>
                    <th>
                        操作4
                    </th>
                </tr>
                </thead>
                <c:forEach items="${manageblogs}" var="item">
                    <c:if test="${item.statement==1}">
                    <tbody>
                    <tr>
                        <td>
                                ${item.id}
                        </td>
                        <td>
                                ${item.title}
                        </td>
                        <td>
                                ${item.author}
                        </td>
                        <td>
                                ${item.time}
                        </td>
                        <td>
                            <form role="form" method="post" id="form3" action="dblog">
                                <input type="hidden" value="${item.id}" id="dblogid" name="dblogid">
                                <input type="submit" value="删除" id="ssss" name="ssss">
                            </form>
                        </td>
                        <td>
                            <form role="form" method="post" id="form4" action="checkblog">
                                <input type="hidden" value="${item.id}" id="check_id" name="check_id">
                                <input type="submit" value="查看" id="ssss1" name="ssss1">
                            </form>
                        </td>
                        <td>
                            <form role="form" method="post" id="form5" action="menublogs">
                                <input type="hidden" value="${item.id}" id="syblogid" name="syblogid">
                                <input type="submit" value="首页推荐" id="ssss2" name="ssss2">
                            </form>
                        </td>
                        <td>
                            <form role="form" method="post" id="form6" action="deletemenublog">
                                <input type="hidden" value="${item.id}" id="syblogid1" name="syblogid1">
                                <input type="submit" value="取消首页推荐" id="ssss3" name="ssss3">
                            </form>
                        </td>
                    </tr>
                    </tbody>
                    </c:if>
                </c:forEach>
            </table>
            <h3>
                待审核博客
            </h3>
            <table class="table">
                <thead>
                <tr>
                    <th>
                        博客ID
                    </th>
                    <th>
                        博客标题
                    </th>
                    <th>
                        作者
                    </th>
                    <th>
                        发布时间
                    </th>
                    <th>
                        操作1
                    </th>
                    <th>
                        操作2
                    </th>
                    <th>
                        操作3
                    </th>
                </tr>
                </thead>
                <c:forEach items="${manageblogs}" var="item">
                    <c:if test="${item.statement==0}">
                    <tbody>
                    <tr>
                        <td>
                                ${item.id}
                        </td>
                        <td>
                                ${item.title}
                        </td>
                        <td>
                                ${item.author}
                        </td>
                        <td>
                                ${item.time}
                        </td>
                        <td>
                            <form role="form" method="post" id="form10" action="deleteexamblog">
                                <input type="hidden" value="${item.id}" id="sdblogid" name="sdblogid">
                                <input type="submit" value="删除" id="ss" name="ss">
                            </form>
                        </td>
                        <td>
                            <form role="form" method="post" id="form11" action="checkexamblogblog">
                                <input type="hidden" value="${item.id}" id="scheck_id" name="scheck_id">
                                <input type="submit" value="查看" id="ss1" name="ss1">
                            </form>
                        </td>
                        <td>
                            <form role="form" method="post" id="form12" action="examblog">
                                <input type="hidden" value="${item.id}" id="scheck_id2" name="scheck_id2">
                                <input type="submit" value="通过审核" id="ss2" name="ss2">
                            </form>
                        </td>
                    </tr>
                    </tbody>
                    </c:if>
                </c:forEach>

            </table>
            <h3>
                管理首页分类
            </h3>
            <input type="button" value="增加分类" id="addcategory" name="addcategory">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        分类名
                    </th>
                    <th>
                        操作1
                    </th>
                </tr>
                </thead>
                <c:forEach items="${allcolumns}" var="item">
                <tbody>
                <tr>
                    <td>
                            ${item}
                    </td>
                    <td>
                        <form role="form" method="post" id="form101" action="deletemenucolumn">
                            <input type="hidden" value="${item}" id="deletecolumn" name="deletecolumn">
                            <input type="submit" value="删除" id="sss" name="sss">
                        </form>
                    </td>
                </tr>
                </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<!-- 模态框 -->
<div id="myModal" class="modal" style="position: absolute; z-index: 10">
    <form role="form" method="post" id="form111" action="addmenucategory">
        <input type="hidden" value="${username}" id="username" name="username">
        <div class="modal-content">
            <div class="modal-header">
                <h2>新增首页分类</h2>
                <span id="closeBtn" class="close">×</span>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="category_id"><font >分栏名</font></label>
                    <input form="form111" type="text" class="form-control" id="category_id" name="category_id" placeholder="请输入要增加的分栏名字" />
                </div>
            </div>
            <div class="modal-footer">
            </div>
            <div class="form-group">
                <input form="form111" type="button" value="确 认" id="check" name="check"></input>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<script>
    (function() {
        /*建立模态框对象*/
        var modalBox = {};
        /*获取模态框*/
        modalBox.modal = document.getElementById("myModal");
        /*获得trigger按钮*/
        modalBox.triggerBtn = document.getElementById("addcategory");
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
        var title = $("#category_id").val();

        if(isEmpty(title)){
            alert("内容不能为空！");
            return;
        }
        document.getElementById("form111").submit();

    });

    function isEmpty(str) {
        if(str == null || str.trim()== ""){
            return true;
        }
        return false;
    }
</script>