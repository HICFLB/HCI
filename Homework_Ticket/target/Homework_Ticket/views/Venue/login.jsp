<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>场馆登录 - Caesar</title>
    <link rel="stylesheet" href="/resources/css/apply.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Venue/venueLogin.js"></script>
</head>
<body>
<div class="login_header">
    <div class="wrap clear">
        <a class="header_back" href="apply.jsp">场馆登录</a>
        <div class="header-left">
            <a class="header-logo" title="Caesar">
                Caesar
                <%--  <img src="resources/img/bird.png" alt="Caesar" width="120" height="90">--%>
            </a>
            <div class="header_title">场馆申请</div>
        </div>
        <div class="header_right">
            <div class="user_info" style="display: none;">
                <a class="user_name">Flutter</a>
                <a href="index.jsp">
                    场馆登录
                </a>
                <span class="line">|</span>
                <a href="modifyVenue.jsp">
                    修改信息
                </a>
                <span class="line">|</span>
                <a href="./views/User/exch">
                    发布演出
                </a>
                <span class="line">|</span>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="apply_box clear">
        <form id="venue_form" action="#" onsubmit="return false" method="post" style="display:block;">
            <div class="in" style="margin-top:120px;">
                <span class="left">场馆识别码：</span>
                <input class="venueID" type="text" required="required"  name="venueID" id="venueID"/>
            </div>
            <div class="in" style="margin-top:30px;">
                <span class="left">登&nbsp;录&nbsp;密&nbsp;码：</span>
                <input class="password" type="password" required="required"  name="password" id="password"/>
            </div>
            <div class="bottom_login">
                <input type="button" value="登录" onclick="venue_login()">
            </div>
        </form>
    </div>

</div>
<div class="apply_bottom">
    <div class="clear" style="width: 1000px; height: 30px; margin-left:260px; border-bottom: 1px solid #999;"></div>
</div>
</body>
</html>