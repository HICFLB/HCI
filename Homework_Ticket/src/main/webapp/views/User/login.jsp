<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>会员登录 - Caesar</title>
    <link rel="stylesheet" href="/resources/css/login.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/User/login.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Common/jquerysession.js"></script>
</head>
<body>
<div class="login_header">
    <div class="wrap clear">
        <a class="header_back" href="/index.jsp">返回首页</a>
        <div class="header-left">
            <a class="header-logo" title="Caesar">
                Caesar
<%--                <img src="resources/img/bird.png" alt="Caesar" width="120" height="90">--%>
            </a>
            <div class="header_title">账户登录</div>
        </div>
    </div>
</div>
<div class="content">
    <div class="login_box clear">
        <form id="login_form" action="#" method="post"  onsubmit="return false">

            <div class="in" style="margin-top:100px;">
                <span class="left" style="color:#555;">邮箱：</span>
                <input type="email" required="required" placeholder="请输入有效邮箱地址" name="email" id="email"/>
            </div>
            <div class="in" style="margin-bottom:80px;">
                <span class="left" style="color:#555;">密码：</span>
                <input type="password" required="required" placeholder="请输入密码" name="password" id="password"/>
            </div>
            <hr>
            <div class="bottom">
                <input type="button" value="登录" onclick="login()">
            </div>
        </form>
    </div>

</div>
<div class="login_bottom">
    <div class="clear" style="width: 1000px; height: 100px; margin-left:260px; border-bottom: 1px solid #999;"></div>
</div>
</body>
</html>