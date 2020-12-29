<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/10/2
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的博客</title>
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
            position: relative;
            left: 120px;
            top:130px;
            width: 120px;
            height: 500px;
            float:left;
            background:  rgba(255, 255, 255, 0.5);
        }
        .leftbtn ul {list-style-type: none;margin: 0;padding: 0; position: absolute;top:20px;left: 0px;}
        .leftbtn a {display: block;background-color: #2c2e2e;
            color: #d3d8d8;width: 120px;text-align: center;padding: 4px;text-decoration: none;
            font-size: 21px;font-family: cursive;<!--草书 -->
        }
        .leftbtn li{
            white-space: nowrap; cursor: pointer;/*设置鼠标箭头手势*/
        }
    </style>
</head>
<body style="background-image: url(images/background2.jpg)">
<div class="container">
    <div class="row clearfix">
        <h3>
            <font color="#ff69b4">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp${user.username}的博客</font>
        </h3><br>
        <div class="col-md-3 column">
            <img src="${user.touxiang}" style="height: 120px;width: 120px">
            <div class="leftbtn">
                <ul>
                    <li><a target="_blank" href="biji.jsp">笔记</a></li>
                    <li><a target="_blank" href="yuanchuang.jsp">原创</a></li>
                    <li><a target="_blank" href="zhuanzai.jsp">转载</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-9 column" style="position: relative">
            <c:forEach items="${blogs2}" var="item">
                <div class="blog" >
                    <div>
                        <span><p>标题：${item.title}</p></span>
                        <span><p>标签：${item.label}&nbsp${item.label2}&nbsp${item.label3}</p></span>
                    </div>
                    <div>
                        <div class="item_left"><span><p>${item.origin}</p></span></div>
                        <div class="item_left"><span><p>${item.time}</p></span></div>
                        <form role="form" method="post" id="form1" action="checkblog">
                            <input type="hidden" value="${item.id}" id="check_id" name="check_id">
                            <div class="item_right"><span><input type="submit" id="checkbt" name="checkbt" value="查看"></span></div>
                        </form>
                        <form role="form" method="post" id="form3" action="modifyblog">
                            <input type="hidden" value="${item.id}" id="modify_id" name="modify_id">
                            <div class="item_right"><span><input type="submit" id="modifybt" name="modifybt" value="编辑"></span></div>
                        </form>
                        <form role="form" method="post" id="form2" action="deleteblog">
                            <input type="hidden" value="${item.id}" id="delete_id" name="delete_id">
                            <div class="item_right"><span><input type="submit" id="deletebt" name="deletebt" value="删除"> </span></div>
                        </form>

                    </div>
                </div>
            </c:forEach>

        </div>

    </div>
</div>
</body>
</html>
<script>
    function delet(){


    }
    function check(id) {

    }
</script>