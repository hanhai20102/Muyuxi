<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>java</title>
    <link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/toastr.min.css" rel="stylesheet">
    <link rel="stylesheet" href="static/css/style.css"/>
    <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="static/js/jquery-3.2.1.min.js"></script>
    <script src="static/js/toastr.min.js"></script>
    <style type="text/css">
        body{
            background-color: white;
        }
        .navbar-nav > li > div {
            font-family: Consolas;
            font-size: 15px;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img alt="KoalaShoppingCenter" src="${pageContext.request.contextPath}/static/image/couple.png">
                </a>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mynavbar" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">考拉购物中心</a>
            </div>

            <div class="collapse navbar-collapse" id="mynavbar">
                <p class="navbar-text">ShoppingCenterByMuyuxi</p>
                <ul class="nav navbar-nav">
                    <c:if test="${sessionScope.user == null}">
                    <li><div>Please,<a href="view/login.jsp" class="quit">[Login]</a></div></li>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                        <c:if test="${sessionScope.user.userType == 0}">
                            <li><div>${sessionScope.user.userName},你好<a href="${pageContext.request.contextPath}/quit" class="quit">[Quit]</a></div></li>
                        </c:if>
                        <c:if test="${sessionScope.user.userType == 1}">
                            <li><div>${sessionScope.user.userName},你好<a href="${pageContext.request.contextPath}/quit" class="quit">[Quit]</a></div></li>
                        </c:if>
                    </c:if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li id="main" class="active btn-style" onclick="changeBack(main)"><button class="btn-nav" type="button" onclick="location.href='index.jsp'">首页</button></li>
                    <c:if test="${sessionScope.user.userType == 0}">
                        <li id="finance" onclick="changeBack(finance)"><button class="btn-nav" type="button" onclick="location.href='view/finance.jsp'">财务</button></li>
                        <li id="shoppingCart" onclick="changeBack(shoppingCart)"><button class="btn-nav" type="button" onclick="location.href='view/shoppingCart.jsp'">购物车</button></li>
                    </c:if>
                    <c:if test="${sessionScope.user.userType == 1}">
                        <li id="publish"><button class="btn-nav" type="button" onclick="location.href='view/publish.jsp'">发布</button></li>
                    </c:if>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
</nav>

<div class="container" style="margin-top: 6%">
    <ul id="myTab" class="nav nav-tabs" style="margin-left: 13%;margin-right: auto; position: relative">
        <li id="allContents">
            <a href="" data-toggle="tab" style="text-align: center">
                所有内容
            </a>
        </li>
        <c:if test="${sessionScope.user.userType == 0}">
            <li id="unPurchased"><a href="#ios" data-toggle="tab" style="text-align: center">未购买的内容</a></li>
        </c:if>
    </ul>

    <div class="n-plist">
        <div id="showCommodity" class="row">

        </div>
    </div>

</div>

    <footer class="n-foot">
        <p>版权所有：沐雨昔<a href="https://github.com/hanhai20102">Java开发工程师(Web方向)</a></p>
    </footer>

<script type="text/javascript">
    toastr.options.positionClass = 'toast-center-center';
    var userArr = new Array(2);
    window.onload =  function startPage() {
        userInfo();
        getAllCommoditys(userArr[0],userArr[1]);
        $("#allContents").addClass("active");
    }

    $("#allContents").click(function () {
        getAllCommoditys(userArr[0],userArr[1]);
    });

    $("#unPurchased").click(function () {
        getUnPurchasedCommoditys(userArr[0]);
        $("#allContents").removeClass("active");
        $(this).addClass("active");

    });
    function userInfo() {
        var user =  "${sessionScope.user}";
        var userID = null;
        var userType = null;
        if(user != ""){
            userID ="${sessionScope.user.userID}";
            userType = "${sessionScope.user.userType}";
        }
        userArr[0] = userID;
        userArr[1] = userType;
    }

</script>
<script type="text/javascript" src="static/js/index.js"></script>
</body>
</html>