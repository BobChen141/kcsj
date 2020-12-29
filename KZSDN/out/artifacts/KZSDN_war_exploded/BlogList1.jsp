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
<body style="background-image: url(images/background2.jpg);background-attachment: fixed">
<div class="container">
    <div class="row clearfix">
        <h3><a href="Menu.jsp"><font color= #00ffff>返回主页</font></a></h3>
        <h3>
            <font color="#ff69b4">&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp${user1.username}的博客</font>
        </h3><br>
        <div class="col-md-3 column">
            <img src="${user1.touxiang}" style="height: 120px;width: 120px">
            <div class="leftbtn">
                <ul>
                    <li><a target="_blank" href="yuanchuang.jsp">原创</a></li>
                    <li><a target="_blank" href="zhuanzai.jsp">转载</a></li>
                    <div>
                        <c:forEach items="${categorys}" var="item">
                            <form role="form" method="post" id="form777" action="showcategory1">
                                <input type="hidden" value="${user1.username}" id="username1" name="username">
                                <input type="hidden" value="${item.category}" id="clickcategory" name="clickcategory">
                                <input type="submit" style="width: 120px" value="${item.category}" id="item_btn" name="item_btn">
                            </form>
                        </c:forEach>
                        <form role="form" method="post" id="form7" action="showcategory1">
                            <input type="hidden" value="${user1.username}" id="username3" name="username">
                            <input type="hidden" value="sb" id="clickcategory1" name="clickcategory">
                            <input type="submit" style="width: 120px" value="全部" id="item_btn1" name="item_btn">
                        </form>
                    </div>
                </ul>
            </div>
        </div>
        <div class="col-md-9 column" style="position: relative">
            <input type="button"value="hhh">
            <c:if test="${checkstr=='sb'}">
                <c:forEach items="${blog}" var="item">
                    <div class="blog" >
                        <div>
                            <span><p>标题：${item.title}</p></span>
                            <span><p>作者：${item.author}</p></span>
                            <span><p>标签：${item.label}&nbsp${item.label2}&nbsp${item.label3}</p></span>
                        </div>
                        <div>
                            <div class="item_left"><span><p>${item.origin}</p></span></div>
                            <div class="item_left"><span><p>${item.time}</p></span></div>
                            <form role="form" method="post" id="form1" action="checkblog">
                                <input type="hidden" value="${item.id}" id="check_id" name="check_id">
                                <div class="item_right"><span><input type="submit" id="checkbt" name="checkbt" value="查看"></span></div>
                            </form>

                        </div>
                    </div>
                    <hr>
                </c:forEach>
            </c:if>
            <c:if test="${checkstr!='sb'}">

                <c:forEach items="${blogs}" var="item">
                    <c:if test="${item.category==checkstr}">
                        <div class="blog" >
                            <div>
                                <span><p>标题：${item.title}</p></span>
                                <span><p>作者：${item.author}</p></span>
                                <span><p>标签：${item.label}&nbsp${item.label2}&nbsp${item.label3}</p></span>
                            </div>
                            <div>
                                <div class="item_left"><span><p>${item.origin}</p></span></div>
                                <div class="item_left"><span><p>${item.time}</p></span></div>
                                <form role="form" method="post" id="form11" action="checkblog1">
                                    <input type="hidden" value="${item.id}" id="check_id1" name="check_id1">
                                    <div class="item_right"><span><input type="submit" id="checkbt1" name="checkbt1" value="查看"></span></div>
                                </form>
                            </div>
                        </div>
                        <hr>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
    </div>



    <%-- <c:if test="${checkstr=='sb'}">   --%>
    <div>
        <form role="form" method="post" id="form211" action="lastpage1"></form>
        <form role="form" method="post" id="form2112" action="nextpage1">  </form>
        <input form="form211" type="hidden" id="username" name="username" value="${user1.username}">
        <input form="form2112" type="hidden" id="username2" name="username2" value="${user1.username}">
        <ul class="pagination pagination-lg" style="position: absolute;left: 660px">
            <input form="form211" type="submit" value="&lt;"style="width: 50px">
            <input type="submit" form="form2112" value="&gt;"style="width: 50px">
        </ul>

    </div>
    <%-- </c:if>--%>

</div>
</body>
</html>
<script>
    function delet(){


    }
    function check(id) {

    }
</script>