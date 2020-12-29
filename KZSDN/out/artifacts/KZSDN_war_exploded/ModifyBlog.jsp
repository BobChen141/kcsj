<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/9/23
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="UTF-8">
    <title>创作中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"href="css/editormd.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/editormd.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <style>
        .okbtn{
            position: fixed;
            left:720px;
            top:710px;
            width: 100px;
            height: 50px;
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
<body>
<div>
    <form role="form" method="post" name="form" id="form" action="mblog">
    </form>
    <input form="form" id="username" name="username" value="${user.username}" type="hidden">
</div>
<div>
    <div class="row">
        <div class="col-lg-5"></div>
        <div class="col-lg-7">
            <h4>创作中心</h4>${error}
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-8">
            <input form="form" value="${mblog.id}" id="id" name="id" type="hidden">

            <input form="form" type="text" value="${mblog.title}" id="ttle" name="ttle" placeholder="请输入文章标题" style="left:300px ;height: 35px;width: 700px"  >
            <input type="button" form="form" id="triggerBtn" style="background-color: red" value="确认修改">
        </div>
    </div>
</div>
<div id="my-editormd" >
    <textarea form="form" id="my-editormd-markdown-doc" name="my-editormd-markdown-doc" style="display:none;">${mblog.content}</textarea>
    <!-- 注意：name属性的值-->
    <textarea form="form" id="my-editormd-html-code" name="my-editormd-html-code" style="display:none;">${mblog.code}</textarea>

</div>
<!-- 模态框 -->
<div id="myModal" class="modal" style="position: absolute; z-index: 10">
    <div class="modal-content">
        <div class="modal-header">
            <h2>发布文章</h2>
            <span id="closeBtn" class="close">×</span>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <label for="label"><font >标签</font></label><input value="${mblog.label}" form="form" type="text" class="form-control" id="label" name="label" placeholder="请输入文章标签" />
                <input form="form" type="text" class="form-control" id="label2" name="label2" placeholder="请输入文章标签" />
                <input form="form" type="text" class="form-control" id="label3" name="label3" placeholder="请输入文章标签" />
            </div>
            <div class="form-group">
                <label for="category"><font >分类专栏</font></label>
                <select form="form" id="category" name="category">
                    <option value="${mblog.category}">${mblog.category}</option>
                    <option value="算法">算法</option>
                    <option value="笔记">笔记</option>
                </select>

            </div>
            <div class="form-group">
                <label for="origin"><font >文章类型</font></label>
                <select form="form" id="origin" name="origin">
                    <option value="${mblog.origin}">${mblog.origin}</option>
                    <option value="原创">原创</option>
                    <option value="转载">转载</option>
                </select>

            </div>
        </div>
        <div class="modal-footer">

        </div>
        <div class="form-group">
            <input form="form" type="button"  value="确 认" id="check" name="check"></input>
        </div>
    </div>
</div>
<script type="text/javascript" src="modalBox.js"></script>
</body>
</html>
<script type="text/javascript">
    $(function() {
        editormd("my-editormd", {//注意1：这里的就是上面的DIV的id属性值
            width   : "90%",
            height  : 640,
            syncScrolling : "single",
            path    : "lib/",//注意2：你的路径
            saveHTMLToTextarea : true,//注意3：这个配置，方便post提交表单

            imageUpload : true, //必须
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "/postblog",


        });
    });
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
        var title = $("#ttle").val();
        var content=$("#my-editormd-markdown-doc").val();
        var label=$("#label").val();
        var category=$("#category").val();
        var origin=$("#origin").val();
        if(isEmpty(title)){
            alert("标题不能为空！");
            return;
        }
        if(isEmpty(content)){
            alert("内容不能为空！");
            return;
        }
        if(isEmpty(label)){
            alert("标签不能为空！");
            return;
        }
        if(isEmpty(category)||category=="0"){
            alert("分类专栏不能为空！");
            return;
        }
        if(isEmpty(origin||origin=="0")){
            alert("文章类型不能为空！");
            return;
        }
        document.getElementById("form").submit();

    });
    function isEmpty(str) {
        if(str == null || str.trim()== ""){
            return true;
        }
        return false;
    }
</script>

