<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
        . { font-family: "Adobe 仿宋 Std R"}
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
                    <li><div>Please,<a href="login.jsp" class="quit">[Login]</a></div></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><button class="btn-nav" type="button" onclick="location.href='../index.jsp'">首页</button></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
        </div>
    </nav>
    <div class="container">
        <form class="m-form m-form-ht n-login" id="loginForm" onsubmit="return false;" autocomplete="off">
            <div class="fmitem">
                <label class="fmlab">用户名：</label>
                <div class="fmipt">
                    <input id="userName_input" class="u-ipt" name="userName" autofocus/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">密码：</label>
                <div class="fmipt">
                    <input id="password_input" class="u-ipt" type="password" name="password"/>
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button id="login_btn" type="button" class="btn btn-success" style="margin-right: 15px" >登 录</button>
                    <button type="reset" class="btn btn btn-danger" style="margin-left: 15px">重 置</button>
                </div>
            </div>
        </form>
        <div class="n-foot">
            <p>版权所有：沐雨昔<a href="https://github.com/hanhai20102">Java开发工程师(Web方向)</a></p>
        </div>
    </div>
    <script type="text/javascript" src="../static/js/md5.js"></script>
    <script type="text/javascript">
        toastr.options.positionClass = 'toast-center-center';

        $("#login_btn").click(function () {
            var userName = $("#userName_input").val();
            var password = md5($("#password_input").val());
            $.ajax({
                url:"${APP_PATH}/KoalaShoppingCenter/login",
                type:"GET",
                data:"userName="+userName+"&password="+password,
                success:function (result) {
                    if(result.code == 0){
                        toastr.success("登陆成功");
                        setTimeout("location.href = '../index.jsp'",1000);
                    }else {
                        var errorMsg = result.res.errorField;
                        if(errorMsg == undefined) {
                            toastr.error(result.message);
                        } else {
                            if(undefined != errorMsg.userName){
                                toastr.warning(errorMsg.userName);
                            }
                            if(undefined != errorMsg.password){
                                toastr.warning(errorMsg.password);
                            }
                        }
                    }
                }

            });
        });
    </script>

</body>
</html>