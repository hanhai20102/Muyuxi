<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>提示</title>

    <style type="text/css">
        span {font-family: Monospace;color: red}

        .head404{ width:580px; height:234px; margin:50px auto 0 auto; background:url(https://www.daixiaorui.com/Public/images/head404.png) no-repeat; }

        .txtbg404{ width:499px; height:169px; margin:10px auto 0 auto; background:url(https://www.daixiaorui.com/Public/images/txtbg404.png) no-repeat;}

        .txtbg404 .txtbox{ width:390px; position:relative; top:30px; left:60px;color:#eee; font-size:13px;}

        .txtbg404 .txtbox p {margin:5px 0; line-height:18px;}

        .txtbg404 .txtbox .paddingbox { padding-top:15px;}

        .txtbg404 .txtbox p a { color:#eee; text-decoration:none;}

        .txtbg404 .txtbox p a:hover { color:#FC9D1D; text-decoration:underline;}

    </style>
</head>
<div align="center">
<body>
    <img src="${pageContext.request.contextPath}/static/image/error.jpg"
         width="50px;" height="50px;"/></br>
    抱歉，访问异常，具体信息如下：</br>
    <h2><span>${message}</span></h2></br>

    <div class="txtbox">

        <p class="paddingbox">请点击以下链接继续浏览网页</p>

        <p>》<a style="cursor:pointer" onclick="history.back()">返回上一页面</a></p>

    </div>

    </body>
</div>
</html>
