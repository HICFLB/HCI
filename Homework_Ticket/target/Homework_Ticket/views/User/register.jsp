<%--
  Created by IntelliJ IDEA.
  User: asus1
  Date: 2018/3/5
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员注册 - Caesar</title>
    <link rel="stylesheet" href="/resources/css/login.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/User/register.js"></script>
</head>
<body>
<div class="login_header">
    <div class="wrap clear">
        <a class="header_back" href="/index.jsp">返回首页</a>
        <div class="header-left">
            <a class="header-logo" title="Caesar">
                Caesar
              <%--  <img src="resources/img/bird.png" alt="Caesar" width="120" height="90">--%>
            </a>
            <div class="header_title">账户注册</div>
        </div>
    </div>
</div>
<div class="content">
    <div class="login_box clear">
        <form id="register_form" action="#" onsubmit="return false" method="post" style="display:block;">
            <div class="in" style="margin-top:80px;">
                <span class="left">邮  箱：</span>
                <input class="email" type="email" required="required" placeholder="请输入有效邮箱地址" name="email" id="email"/>
            </div>
            <div class="in">
                <span class="left">用户名：</span>
                <input class="username" type="text" required="required" placeholder="请输入用户名" name="username" id="username"/>
            </div>
            <div class="in">
                <span class="left">密  码：</span>
                <input class="password" type="password" required="required" placeholder="请输入密码" name="password"/>
            </div>
            <hr>
            <div class="bottom">
                <input type="button" value="注册" onclick="register()">
            </div>
        </form>
        <div class="post_email" style="display:none;">
            已经向您发送邮件验证，请注意接收邮件！
        </div>
    </div>

</div>
<div class="login_bottom">
    <div class="clear" style="width: 1000px; height: 100px; margin-left:260px; border-bottom: 1px solid #999;"></div>
</div>
</body>
</html>