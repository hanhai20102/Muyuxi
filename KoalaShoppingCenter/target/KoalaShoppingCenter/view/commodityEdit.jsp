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
    <script src="../static/js/ajaxfileupload.js"></script>
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
    <div class="row">
        <div class="col-md-12 title" style="border-bottom: 1px solid #d9d9d9">
            <h3>内容编辑</h3>
        </div>
    </div>

    <div class="publish-doc">

        <form class="form-horizontal">
            <div class="form-group">
                <label for="title" class="col-sm-2 control-label">标题：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="title" placeholder="2-80字符" name="title" datatype="*2-80" errormsg="请输入2-80位字符">
                </div>
            </div>
            <div class="form-group">
                <label for="abstract" class="col-sm-2 control-label">摘要：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="abstract" placeholder="2-140字符" name="abstractMsg" datatype="*2-140" errormsg="请输入2-140位字符" >
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">图片：</label>
                <div class=" fmipt" id="uploadType">
                    <input id="radio1" name="pic" type="radio" value="url" checked=""> 图片地址
                    <input id="radio2" name="pic" type="radio" value="file"> 本地上传
                </div>
            </div>

            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload" style="">
                    <input id="imageForUrl" class="u-ipt ipt" name="picture" placeholder="图片地址" >
                </div>
                <div class="fmipt" id="fileUpload" style="display: none;">
                    <input class="u-ipt ipt" name="file"  type="file" id="fileUp"  style="display: inline-block">
                    <button class="btn btn-success" id="btn-upload">上传</button>
                </div>
            </div>


            <div class="form-group">
                <label for="text" class="col-sm-2 control-label">正文：</label>
                <div class="col-sm-10">
                    <textarea id="text" placeholder="2-1000个字符" rows="10" name="text" datatype="*2-1000" errormsg="请输入2-1000位字符"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="price">价格：</label>
                <div class="input-group">
                    <div class="input-group-addon" >￥</div>
                    <input type="text" class="from-inputPrice" id="price" name="currentPrice" onkeyup="value=value.replace(/[^\d\.]/g,'')">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-success" id="btn_save">保存</button>
                </div>
            </div>
        </form>
        <span class="n-public imgShow">
            <img width="100%" height="auto" src="" alt="" id="imgShow">
        </span>

    </div>
</div>

<script type="text/javascript">
    toastr.options.positionClass = 'toast-center-center';
    var data = window.location.href.split("?")[1].split("=")[1];   //商品ID
    //先显示编辑框中的东西
    window.onload = function editCommodityShow() {
        var uri = "/KoalaShoppingCenter/seller/queryCommodityInfo/";
        //根据userType发送请求
        $.ajax({
            url:uri+data,
            type:"GET",
            success:function (data) {
                if(data.code == 0){
                    fillForm(data.res.commodityInfo);
                } else {
                    toastr.error(data.message);
                }
            }
        });
    }


    //表单数据填充
    function fillForm(result) {
        $("#title").val(result.title);
        $("#abstract").val(result.abstractMsg);
        $("#imageForUrl").attr("value",result.picture);
        $("#text").val(result.text);
        $("#imgShow").attr("src",result.picture);
        $("#price").val(result.currentPrice);
    }



    //为保存按钮绑定click事件
    $("#btn_save").click(function () {
        if(!validate_input("#title",2,80,"标题")||!validate_input("#abstract",2,140,"摘要")|| !validate_input("#text",2,1000,"正文")){
            return false;
        }
        $.ajax({
            url: "/KoalaShoppingCenter/seller/updateCommodityInfo",
            type:"PUT",
            data: $(".form-horizontal").serialize()+"&commodityID="+data,
            success:function (result) {
                if(result.code == 0){
                    toastr.success("修改成功");
                    setTimeout("location.href = '../index.jsp'",1000);

                } else {
                    toastr.error("修改失败,"+result.message);
                }
            }
        });
    });

</script>

<script src="../static/js/publish.js"></script>
</body>
</html>