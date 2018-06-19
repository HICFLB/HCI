<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>场馆申请 - Caesar</title>
    <link rel="stylesheet" href="/resources/css/apply.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Venue/apply.js"></script>
</head>
<body>
<div class="login_header">
    <div class="wrap clear">
        <a class="header_back" href="/views/Venue/login.jsp">场馆登录</a>
        <div class="header-left">
            <a class="header-logo" title="Caesar">
                Caesar
                <%--  <img src="resources/img/bird.png" alt="Caesar" width="120" height="90">--%>
            </a>
            <div class="header_title">场馆申请</div>
        </div>
        <div class="header_right">
            <div class="user_info" style="display: none;">
                <a class="venue_name">Flutter</a>
                <a href="index.jsp">
                    场馆登录
                </a>
                <span class="line">|</span>
                <a href="modifyVenue.jsp">
                    修改信息
                </a>
                <span class="line">|</span>
                <a href="publish.jsp">
                    发布演出
                </a>
                <span class="line">|</span>
                <a href="./views/User/exch">
                    现场检票
                </a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="apply_box clear">
        <form id="apply_form" action="#" onsubmit="return false" method="post" style="display:block;">
            <div class="in" style="margin-top:80px;">
                <span class="left">场馆名称：</span>
                <input class="venue" type="text" required="required"  name="venue" id="venue"/>
            </div>
            <div class="in">
                <span class="left">场馆地址：</span>
                <input class="location" type="text" required="required"  name="location" id="location"/>
            </div>
            <div class="in">
                <span class="left">联系电话：</span>
                <input class="contact" type="text" required="required"  name="contact" id="contact"/>
            </div>
            <div class="in">
                <span class="left">场馆容量：</span>
                <input class="volume" type="text" required="required"  name="volume" id="volume"/>
            </div>
            <div class="in">
                <span class="left">登录密码：</span>
                <input class="password" type="password" required="required" placeholder="用于登录"  name="password" id="password"/>
            </div>
            <hr>
            <div class="bottom">
                <input type="button" value="申请" onclick="apply()">
            </div>
        </form>
    </div>

</div>
<div class="apply_bottom">
    <div class="clear" style="width: 1000px; height: 30px; margin-left:260px; border-bottom: 1px solid #999;"></div>
</div>
</body>
</html>