<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理 - Caesar - 经理登录</title>
    <link rel="stylesheet" href="/resources/css/apply.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Manager/managerLogin.js"></script>
</head>
<body>
<div class="login_header">
    <div class="wrap clear">
        <div class="header-left">
            <a class="header-logo" title="Caesar">
                Caesar
                <%--  <img src="resources/img/bird.png" alt="Caesar" width="120" height="90">--%>
            </a>
            <div class="header_title">经理登录</div>
        </div>
    </div>
</div>
<div class="content">
    <div class="apply_box clear">
        <form id="manager_form" action="#" onsubmit="return false" method="post" style="display:block;">
            <div class="in" style="margin-top:120px;">
                <span class="left">用户名：</span>
                <input type="text" required="required"  name="admin" id="admin"/>
            </div>
            <div class="in" style="margin-top:30px;">
                <span class="left">密码：</span>
                <input type="password" required="required"  name="password" id="password"/>
            </div>
            <div class="bottom_login">
                <input type="button" value="登录" onclick="manager_login()">
            </div>
        </form>
    </div>

</div>
<div class="apply_bottom">
    <div class="clear" style="width: 1000px; height: 30px; margin-left:260px; border-bottom: 1px solid #999;"></div>
</div>
</body>
</html>