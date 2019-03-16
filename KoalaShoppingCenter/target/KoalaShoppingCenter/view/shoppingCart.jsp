<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>java</title>
    <script src="../static/js/jquery-3.2.1.min.js"></script>
    <link href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/toastr.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../static/css/style.css"/>
    <script src="../static/js/toastr.min.js"></script>
    <script src="../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <style type="text/css">
        .navbar-nav > li > div {
            font-family: Consolas;
            font-size: 15px;
        }
        . distance{
            margin-right: auto;
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
                        <li><div>Please,<a href="login.jsp">[Login]</a></div></li>
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
                    <li><button class="btn-nav" type="button" onclick="location.href='../index.jsp'">首页</button></li>
                    <c:if test="${sessionScope.user.userType == 0}">
                        <li><button class="btn-nav" type="button" onclick="location.href='finance.jsp'">财务</button></li>
                        <li class="active btn-style"><button class="btn-nav" type="button" onclick="location.href='shoppingCart.jsp'">购物车</button></li>
                    </c:if>
                    <c:if test="${sessionScope.user.userType == 1}">
                        <li><button class="btn-nav" type="button" onclick="location.href='publish.jsp'">发布</button></li>
                    </c:if>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
</nav>

<%--购物车中显示的内容--%>

<%--标题--%>
<div class="container showForBody">
    <div class="row">
        <div class="col-md-12 title">
            <h3>已添加到购物车的内容:</h3>
        </div>
    </div>
    <%--表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover table-bg" id="shoppingCartContent">
                <thead class="table-head">
                    <tr>
                        <th>内容名称</th>
                        <th>数量</th>
                        <th>价格</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-2 col-md-offset-3">
            <button type="button" id="btn-submit" class="btn btn-success">购买</button>
        </div>

        <div class="col-md-2 col-md-offset-3">
            <button type="button" id="btn-quit" class="btn btn-danger">退出</button>
        </div>


    </div>
</div>

<script type="text/javascript">
    toastr.options.positionClass = 'toast-center-center';
    window.onload = function queryShoppingCart() {
        var userID = "${sessionScope.user.userID}";
        queryShoppingCartItem(userID);
    }

    $("#btn-quit").click(function () {
        window.history.back(-1);
    });

    /*执行购买的逻辑*/
    $("#btn-submit").click(function () {
        buy();
    });

</script>

<script src="../static/js/shoppingCart.js"></script>
</body>
</html>