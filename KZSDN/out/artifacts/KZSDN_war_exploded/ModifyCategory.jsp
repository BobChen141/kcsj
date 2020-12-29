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
                    修改分类专栏
                </h1>
            </div>
            <form role="form" method="post" id="form" action="modifycategory">

                <div class="form-group">
                    <input type="hidden" id="username7" name="username7" value="${username7}">
                    <input type="hidden" id="getid" name="getid" value="${id}">
                    <input type="hidden" id="musername" name="musername" value="${item}">
                    <label for="category_id1">专栏名</label><input type="text" class="form-control" id="category_id1" name="category_id1" placeholder="请输入要修改的分栏名" />
                </div>
                <button type="submit" class="btn btn-default" onclick="check()">确 认</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>

</script>