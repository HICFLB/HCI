<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>结算金额 - Caesar - 后台管理</title>
    <link rel="stylesheet" href="/resources/css/balance.css">
    <link rel="stylesheet" href="/resources/css/examine.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Manager/balance.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
</head>
<body>
<div class="home_header">
    <div class="wrap clear">

        <div class="header-left">
            <a class="header-logo" title="Caesar">
                Caesar
                <%-- <img src="resources/img/bird.png" alt="Caesar" width="120" height="90">--%>
            </a>
            <div class="header_title">结算</div>
        </div>
        <div class="header_right">
            <div class="user_info" style="display: block;">
                <a class="user_name">Admin</a>
                <a href="examine.jsp">
                    审核场馆申请
                </a>
                <span class="line">|</span>
                <a href="checkVenueInfo.jsp">
                    审核信息修改
                </a>
                <span class="line">|</span>
                <a href="statisticsManager.jsp">
                    统计
                </a>
                <span class="line">|</span>
                <a href="balance.jsp"  style="color: #3498db;">
                    结算
                </a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="balance_pane">
    <div class="base_info">
<%--        <p>场馆总数：</p>
        <p id="venueNum"></p>
        <p style="margin-left:400px;">会员支付总金额(元)：</p>
        <p id="finance"></p>--%>
        <br>
    </div>
    <div class="perform_info">
        <p style="margin-left:30px; color: #555;">已结束的演出：</p>
        <div class="loop_pane">

        </div>
    </div>
    </div>
</div>
<div class="main_bottom">
    <div class="clear" style="width: 1000px; height: 30px; margin-left:260px; border-bottom: 1px solid #999;"></div>
</div>
</body>
</html>