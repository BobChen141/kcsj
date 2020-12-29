<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/10/12
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理分类专栏</title>
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

            <h3><a href="Menu.jsp"><font color= #00ffff>返回主页</font></a></h3>
            <h2 class="text-center">
                管理分类专栏
            </h2>
            <div>

                    <input  style="border-color: red;color: red;background-color: white" type="submit" id="addcategory" name="addcategory" value="新增专栏">
            </div>
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>
                            类别
                        </th>
                        <th>
                            操作1
                        </th>
                        <th>
                            操作2
                        </th>
                    </tr>
                    </thead>
                    <c:forEach items="${columns}" var="item">
                            <tbody>
                            <tr>
                                <td>
                                        ${item.category}
                                </td>
                                <td>
                                    <form role="form" method="post" id="form4" action="deletecategory">
                                        <input type="hidden" value="${item.username}" id="username1" name="username1">
                                        <input type="hidden" value="${item.category}" id="dusername" name="dusername">
                                        <input style="background-color: orangered"  type="submit" value="删除" id="delete_bt" name="delete_bt">
                                    </form>

                                </td>
                                <td>
                                    <form role="form" method="post" id="form999" action="tomodifyc">
                                        <input type="hidden" value="${item.id}" id="getid" name="getid">
                                        <input type="hidden" value="${item.username}" id="username7" name="username7">
                                        <input  type="hidden" value="${item.category}" id="musername" name="musername">
                                        <input style="background-color: #2196F3"  type="submit" value="修改" id="modify_bt" name="modify_bt">
                                    </form>
                                </td>
                            </tr>
                            </tbody>
                    </c:forEach>
                </table>
            </div>

        </div>
    </div>
</div>
<!-- 模态框 -->
<div id="myModal" class="modal" style="position: absolute; z-index: 10">
    <form role="form" method="post" id="form" action="addcategory">
        <input type="hidden" value="${username}" id="username" name="username">
    <div class="modal-content">
        <div class="modal-header">
            <h2>新增专栏</h2>
            <span id="closeBtn" class="close">×</span>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <label for="category_id"><font >分栏名</font></label>
                <input form="form" type="text" class="form-control" id="category_id" name="category_id" placeholder="请输入要增加的分栏名字" />
            </div>
        </div>
        <div class="modal-footer">
        </div>
        <div class="form-group">
            <input form="form" type="button" value="确 认" id="check" name="check"></input>
        </div>
    </div>
    </form>
</div>
<!-- 模态框
<div id="myModal1" class="modal" style="position: absolute; z-index: 10">
    <form role="form" method="post" id="form1" action="modifycategory">
        <input form="form1" type="hidden" value="${username}" id="username7" name="username7">
        <div class="modal-content">
            <div class="modal-header">
                <h2>新增专栏</h2>
                <span id="closeBtn1" class="close">×</span>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="category_id1"><font >分栏名</font></label>
                    <input form="form1" type="text" class="form-control" id="category_id1" name="category_id1" placeholder="请输入要修改的分栏名字" />
                </div>
            </div>
            <div class="modal-footer">
            </div>
            <div class="form-group">
                <input form="form1" type="button" value="确 认" id="check1" name="check1"></input>
            </div>
        </div>
    </form>
</div>-->
<script type="text/javascript" src="modalBox.js"></script>
</body>
</html>

<script type="text/javascript">
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
    // $("#modify_bt").click(function () {
    //     /*建立模态框对象*/
    //     var modalBox1 = {};
    //     /*获取模态框*/
    //     modalBox1.modal1 = document.getElementById("myModal1");
    //     /*获得trigger按钮*/
    //     modalBox1.triggerBtn1 = document.getElementById("modify_bt");
    //     /*获得关闭按钮*/
    //     modalBox1.closeBtn1 = document.getElementById("closeBtn1");
    //     /*模态框显示*/
    //     modalBox1.show = function() {
    //         console.log(this.modal1);
    //         this.modal1.style.display = "block";
    //     }
    //     /*模态框关闭*/
    //     modalBox1.close = function() {
    //         this.modal1.style.display = "none";
    //     }
    //     /*当用户点击模态框内容之外的区域，模态框也会关闭*/
    //     modalBox1.outsideClick = function() {
    //         var modal1 = this.modal1;
    //         window.onclick = function(event) {
    //             if(event.target == modal1) {
    //                 modal1.style.display = "none";
    //             }
    //         }
    //     }
    //     /*模态框初始化*/
    //     modalBox1.init = function() {
    //         var that = this;
    //         this.triggerBtn1.onclick = function() {
    //             that.show();
    //         }
    //         this.closeBtn1.onclick = function() {
    //             that.close();
    //         }
    //         this.outsideClick();
    //     }
    //     modalBox1.init();
    // });
    $("#check").click(function () {
        var title = $("#category_id").val();

        if(isEmpty(title)){
            alert("内容不能为空！");
            return;
        }
        document.getElementById("form").submit();

    });

    $("#check1").click(function () {
        var title = $("#category_id1").val();

        if(isEmpty(title)){
            alert("内容不能为空！");
            return;
        }
        document.getElementById("form1").submit();

    });
    function isEmpty(str) {
        if(str == null || str.trim()== ""){
            return true;
        }
        return false;
    }
</script>