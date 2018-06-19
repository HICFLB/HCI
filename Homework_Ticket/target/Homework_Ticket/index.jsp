<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页 - Caesar</title>
    <link rel="stylesheet" href="resources/css/homepage.css">
    <link rel="stylesheet" href="resources/css/purchase.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="resources/js/User/home.js"></script>
</head>
<body>
<div class="home_header">
    <div class="wrap clear">

        <div class="header_left">
            <a class="header_logo" title="Caesar">
                Caesar
               <%-- <img src="resources/img/bird.png" alt="Caesar" width="120" height="90">--%>
            </a>
        </div>
        <div class="header_right">
            <div class="no_user" style="display: block;">
            <a href="./views/User/register.jsp">
                会员注册
            </a>
            <a href="./views/User/login.jsp" style="margin-right:25px;">
                会员登录
            </a>
            </div>
            <div class="user_info" style="display: none;">
                <a class="user_name">Flutter</a>
                <a href="index.jsp">
                    首页
                </a>
                <span class="line">|</span>
                <a href="./views/User/profile.jsp">
                    个人信息
                </a>
                <span class="line">|</span>
                <a href="./views/User/exch">
                    积分兑换
                </a>
                <span class="line">|</span>
                <a href="./views/User/checkOrder.jsp">
                    查看订单
                </a>
                <span class="line">|</span>
                <a href="#">
                    登出
                </a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="list_pane">
        <div class="filter clear">
            <div class="filter_name">类型：</div>
            <div class="filter_all">
                <a href="#" onclick="choiceType(this)" id="all" class="active">全部</a>
                <span class="line">|</span>
            </div>
            <div class="filter-con">
                <a class="" onclick="choiceType(this)" href="#" id="melodrama">音乐剧</a>
                <span class="line">|</span>
                <a class="" onclick="choiceType(this)" href="#" id="concert">演唱会</a>
                <span class="line">|</span>
                <a class="" onclick="choiceType(this)" href="#" id="drama">话剧歌剧</a>
                <span class="line">|</span>
                <a class="" onclick="choiceType(this)" href="#" id="dance">舞蹈表演</a>
                <span class="line">|</span>
                <a class="" onclick="choiceType(this)" href="#" id="PE">体育比赛</a>
                <span class="line">|</span>
            </div>
        </div>

        <%--留着以后改进用的 现在暂时以列表形式显示--%>
        <div class="listprod" id="perform_info">

            <div class="list_item">

                <div class="performance_info">
                    <a class="por_title">百老汇音乐剧《猫》</a>
                    <p class="por_price">票价<span class="rmb">￥</span><span class="num">320~1200</span></p>
                    <p class="por_type">类型：</p>
                    <br>
                    <p class="por_time">时间：2018.04.18-2018.04.24</p>
                    <p class="por_location">场馆：南京奥体中心</p>
                </div>
                <div class="purchase_btn"><a href="#" title="" >立即购票</a></div>
            </div>
        </div>
    </div>

</div>
<div class="main_bottom">
</div>
</body>
</html>