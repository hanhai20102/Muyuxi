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

<%--购买提示对话框--%>
<div class="modal fade" tabindex="-1" role="dialog" id="add2ShoppingCartModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <p>确认加入购物车?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="confirmAdd">确认</button>
            </div>
        </div>
    </div>
</div>


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
                        <li><div>Please,<a href="login.jsp" class="quit">[Login]</a></div></li>
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
                        <li><button class="btn-nav" type="button" onclick="location.href='shoppingCart.jsp'">购物车</button></li>
                    </c:if>
                    <c:if test="${sessionScope.user.userType == 1}">
                        <li><button class="btn-nav" type="button" onclick="location.href='publish.jsp'">发布</button></li>
                    </c:if>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
</nav>

<div class="container showForBody">

    <div id="showContent">
        <div class="goodInfo-top">
            <div class="goodInfo-pic">
                <img style="width:100%;height:auto;" src="" alt="">
            </div>
            <div class="goodInfo-right">
                <h2></h2>
                <p></p>
                <div >
                    <span class="goodInfo-rmb">¥</span>
                    <span class="goodInfo-price"></span>
                </div>
                <div class="goodInfo-num">
                    <div id="amountForLabel">购买数量：</div>
                    <ul class='count'>
                        <li>
                            <span class='num-reduce'>-</span>
                        </li>
                        <li>
                            <input type='text' class='input-num' value="1"/>
                        </li>
                        <li>
                            <span class='num-plus'>+</span>
                        </li>
                    </ul>
                </div>
                <c:if test="${sessionScope.user != null}">
                <div>
                    <button type="button" class="btn btn-danger"></button>
                    <span id="tips"></span>
                </div>
                </c:if>
            </div>
        </div>
        <div style="clear:both;"></div>
        <div class="goodInfo-mid">
            <h2>详细信息</h2>
        </div>
        <div class="goodInfo-bot"></div>
    </div>


</div>






<script type="text/javascript">
    toastr.options.positionClass = 'toast-center-center';
    var data = window.location.href.split("?")[1].split("=")[1];   //商品ID


    window.onload =  function startPage() {
        //获取index.jsp传入的commodityID
        console.log(data);
        var userType = "${sessionScope.user.userType}";
        var uri = "/KoalaShoppingCenter/seller/queryCommodityInfo/";
        if(userType == 0){
            uri = "/KoalaShoppingCenter/buyer/queryCommodityInfo/";
        }
        //根据userType发送请求
        //发送商品详情的请求
        $.ajax({
            url:uri+data,
            type:"GET",
            success:function (data) {
                if(data.code == 0){
                    fillPage(data.res.commodityInfo,userType);
                } else {
                    toastr.error(data.message);
                }
            }
        });

    }

    function fillPage(data,userType) {
        $(".goodInfo-pic img").attr("src",data.picture);  //显示图片
        $(".goodInfo-right h2").html(data.title);  //标题
        $(".goodInfo-right p").html(data.abstractMsg); //摘要
        $(".goodInfo-price").html(data.currentPrice);
        //按钮以及价格的显示
        if(userType == 1) {   //显示编辑按钮以及卖出的数量
            $("#amountForLabel").html("售出数量:");
            $(".input-num").val(data.soldAmount);
            $(".input-num").attr("disabled","disabled");
            $(".btn-danger").html("编辑");
        } else if(userType == 0){
            console.log("购买数量");
            console.log(data.amount);
            if( data.amount == null) {
                //未购买
                $(".btn-danger").html("加入购物车");
            } else {
                var btn =  $(".btn-danger");
                btn.attr("disabled","disabled");
                $(".btn-danger").html("已购买");
                $("#tips").html("当时购买价格：¥"+data.boughtPrice);
            }
        }
        $(".goodInfo-bot").html(data.text);  //显示商品详情
    }



    //为按钮绑定单击事件
    $(".btn-danger").click(function () {
        var btn_value = $(this).html();
        if(btn_value == "编辑"){   //执行商家编辑逻辑
            edit();
        } else if(btn_value == "加入购物车") {  //执行加入购物车逻辑
            $("#add2ShoppingCartModal").modal({
                backdrop:"static"
            });
        }
    });

    $("#confirmAdd").click(function () {
        addToShoppingCart();
    });

    //插入 购买的数量 购买价格 商品ID
    function addToShoppingCart() {
        var amount = $(".input-num").val();
        $.ajax({
            url:"/KoalaShoppingCenter/buyer/insertToShoppingCart",
            type:"POST",
            data:"commodityID="+data+"&amount="+amount,
            success:function(result) {
                $("#add2ShoppingCartModal").modal('hide');
                if(result.code == 0){
                    toastr.success("加入购物车成功");
                    setTimeout("location.href = '../index.jsp'",1000);
                } else {
                    toastr.error(result.message);
                }
            }
        });
    }

    //商家编辑按钮
    //需要title,abstractMsg,picture,text
    function edit() {
        //页面跳转
        location.href = "commodityEdit.jsp?commodityID="+data;
    }


</script>

<script src="../static/js/shoppingCart.js"></script>
</body>
</html>