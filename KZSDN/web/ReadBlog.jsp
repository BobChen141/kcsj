<%--
  Created by IntelliJ IDEA.
  User: 15R2
  Date: 2020/10/3
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看博客</title>
    <script type="text/javascript" src="https://upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.2.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script src="lib/marked.min.js"></script>
    <script src="lib/prettify.min.js"></script>
    <script src="lib/raphael.min.js"></script>
    <script src="lib/underscore.min.js"></script>
    <script src="lib/sequence-diagram.min.js"></script>
    <script src="lib/flowchart.min.js"></script>
    <script src="lib/jquery.flowchart.min.js"></script>
    <script src="js/editormd.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="https://upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.2.min.js"></script>
</head>
<body >

    <div class="panel-body">
        <!-- 用于显示md编辑器的md格式 -->
        <div id="doc-content">
            <a href="Menu.jsp"><font color= #00ffff>返回主页</font></a>
            <h2 class=""><font color= #ff69b4>${blog.title}</font></h2><p><font color="#7fffd4">来源：${blog.origin} &nbsp &nbsp 分类：${blog.category} &nbsp &nbsp 标签：${blog.label} &nbsp ${blog.label2} &nbsp ${blog.label3}</font></p>
            <form role="form" method="post" id="form1" action="modifyblog">
                <input type="hidden" id="modify_id" name="modify_id" value="${blog.id}">
            <input type="submit" value="编辑博客" > </form><hr>

            <textarea style="display: none;">${blog.content}</textarea>
        </div>
    </div>
</div>
<div>
<div>
    <hr>
</div>


<c:forEach items="${comments}" var="item">
    <c:if test="${item.rate==0}">
        <form role="form" method="post" id="form333" name="form33" action="comment1">
        <div>
            <span><p>${item.username}： ${item.comment}</p></span>
            <font color="blue">${item.time}</font>
            <input type="hidden"  value="${blog.id}" id="cgetid" name="cgetid">
            <input type="hidden" value="${item.commentid}"  name="superid">
            <input type="hidden"  value="${item.username}"  name="supername">
            <button type="button"    class="btn btn-primary"  data-toggle="collapse" data-target="#${item.commentid}" aria-expanded="false" aria-controls="collapseExample">回复</button>
            <c:forEach items="${item.commentlist}" var="item1">
                <span><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${item1.username} 回复 ${item1.supername}： ${item1.comment}</p></span>
                <font color="blue">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${item1.time}</font>
            </c:forEach>
            <div class="collapse" id="${item.commentid}">
                <div class="well">
                    <input type="text"  name="textarea1">
                    <input  type="submit"  value="确认">
                </div>
            </div>
        </div>
        </form>
    </c:if>
</c:forEach>
    <form role="form" method="post" id="form" action="comment"></form>
    <input form="form" type="hidden" name="blog_id" id="blog_id" value="${blog.id}">
    <input form="form" type="hidden" name="articleauthor" class="articleauthor" id="articleauthor" value="${user.username}">
    <textarea style="width: 1200px;height: 100px" form="form" name="remarkEditor"  id="remarkEditor" placeholder="请输入内容" class="textconment" ></textarea>
    <input type="hidden" id="touxiang" name="touxiang" value="${user.touxiang}">
    <button class="submitcomment" id="submitcomment" name="submitcomment" onclick="cz()" onkeypress="cz()">发表评论</button>
    <ul class="items"></ul>
</div>
</div>
</body>
</html>
<script>
    function collect() {
        var username=document.getElementById("username").value;
        var request = ajaxFunction(); //创建xmlHttpRequest对象
        var blogid=document.getElementById("collect_id").value;
        var postStr = "username="+ username +"&blogid="+ blogid ;
        //发送请求，参数分别为：发送方式   发送地址   是否异步传输
        request.open("GET", "collectblog?username=" + username, true);
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
<script type="text/javascript">
    var testEditor;
    $(function() {
        testEditor = editormd.markdownToHTML("doc-content", {//注意：这里是上面DIV的id
            htmlDecode : "style,script,iframe",
            emoji : true,
            taskList : true,
            tex : true, // 默认不解析
            flowChart : true, // 默认不解析
            sequenceDiagram : true, // 默认不解析
            codeFold : true
        });
    });
    var jio = document.getElementsByClassName('textconment')[0];//获得多行文本框内容
    var time = new Date();//获得时间
    var nian = time.getFullYear();//获得年份
    var dt = time.getMonth();//获得月份
    var r = time.getDate();//获得日期
    var shi=time.getHours();
    var fen=time.getMinutes();
    //实现上传
    var items = document.getElementsByClassName('items')[0];//获得ul的节点属性
    function cz(){
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型，传递方式
            dataType: "json",//预期服务器返回的数据类型
            url: "/comment" ,//url，就是form里面的action，不要忘了url前面加/
            data: $('#form').serialize(),//form的id

        });
        //先创建li的节点
        var Li = document.createElement('li');
        //然后插入到ul中
        items.appendChild(Li);
        Li.style.listStyle = "none";
        //创建图片
        // var img = document.createElement('img');
        // Li.appendChild(img);//在li中插入图片
        // var isz = ['images/HeadPic/1601603011511.jpg'];//一个图片的数组
        // var index = Math.floor(Math.random()*2);
        // img.style.width = "50px";
        // img.style.height = "50px";
        // img.style.borderRadius = "50%";
        // var touxiang=$("#touxiang").val();
        // img.setAttribute('src',"touxiang");
        //创建图片右边内容的大盒子
        var hfather = document.createElement('div');
        Li.appendChild(hfather);//插入大盒子
        //实现盒子左浮动
        hfather.style.display = "inline-block";

        //创建标题
        var libt = document.createElement('p');

        var articleauthor = $("#articleauthor").val();//articleauthor是用户名
        hfather.appendChild(libt);
        libt.style.fontSize = "20px";
        libt.style.color = "black";
        libt.style.fontWeight = "bloder";
        libt.style.marginBottom = "5px";

        libt.innerHTML = articleauthor+":"+jio.value;//填充内容
        //创建板块
        //创建时间板块
        var tbk  = document.createElement('span');
        hfather.appendChild(tbk);
        tbk.style.marginLeft = "15px";
        tbk.style.color = "blue";
        tbk.innerHTML = "发表时间:" + nian + "-" + (dt+1) + "-" + r  + "&nbsp;"+shi+":"+fen;
        jio.value = "";//清空评论输入框
    }
   function toComent(check) {

   }

</script>
