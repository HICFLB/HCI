<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>统计信息 - Caesar - 后台管理</title>
    <link rel="stylesheet" href="/resources/css/examine.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/statisticsManager.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
    <script src="https://cdn.bootcss.com/echarts/4.0.4/echarts.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Manager/statisticsManager.js"></script>
</head>
<body>
<div class="home_header">
    <div class="wrap clear">

        <div class="header-left">
            <a class="header-logo" title="Caesar">
                Caesar
                <%-- <img src="resources/img/bird.png" alt="Caesar" width="120" height="90">--%>
            </a>
            <div class="header_title">统计信息</div>
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
                <a href="statisticsManager.jsp" style="color: #3498db;">
                    统计
                </a>
                <span class="line">|</span>
                <a href="balance.jsp">
                    结算
                </a>
            </div>
        </div>
    </div>
</div>
<div class="content">
<div class="statistics_pane">
    <div class="filter clear">
        <div class="filter_name">类型：</div>
        <div class="filter-con">
            <a id="no_pay" href="#"  onclick="choiceType(this)" class="active" class="" href="">场馆统计信息</a>
            <span class="line">|</span>
            <a id="no_execute" class="" href="#" onclick="choiceType(this)">会员统计信息</a>
            <span class="line">|</span>
            <a id="finished" class="" href="#" onclick="choiceType(this)">财务信息</a>
            <span class="line">|</span>
        </div>
    </div>
    <div class="statistics_venue" style="display: block;">
        <div class="base_info">
            <p>场馆总数：</p>
            <p id="venueNum"></p>
            <p>演出总数：</p>
            <p id="performanceNum"></p>
            <br>
        </div>
        <%--场馆的统计信息包括的内容是各场馆发布数目的饼状图 各场馆已结算赚了多少钱柱状图还有预订退订4个图？？？--%>
        <div>
            <div id="venue_pie" style="width:400px;height: 400px;"></div>
            <div id="venue_bar" style="width:400px;height: 400px;"></div>
<%--            <p class="pie_title">各场馆发布演出数饼状图</p>--%>
        </div>
    </div>
    <div class="statistics_user" style="display: none;">
        <%--用户的统计信息包括用户的个数，用户的等级分布 大概就是1级有几个 2级有几个 3级有几个 然后各自的花费--%>
            <div>
                <div id="user_pie" style="width:400px;height: 400px;"></div>
                <div id="user_bar" style="width:400px;height: 400px;"></div>
            </div>
    </div>
    <div class="statistics_finance" style="display: none;">
        <div class="base_info">
            <p>获得总金额(以下数据只计算已经结算的收入)：</p>
            <p id="total_finance"></p>
            <br>
        </div>
        <div>
            <%--一个是收入来源 线上线下 另一个是统计各场馆提供的金额--%>
            <div id="finance_pie" style="width:400px;height: 400px;"></div>
            <div id="finance_bar" style="width:400px;height: 400px;"></div>
        </div>
    </div>
</div>
</div>
<div class="main_bottom">
    <div class="clear" style="width: 1000px; height: 30px; margin-left:260px; border-bottom: 1px solid #999;"></div>
</div>
</body>
</html>