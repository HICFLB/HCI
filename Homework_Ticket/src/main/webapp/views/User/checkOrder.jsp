<%--
  Created by IntelliJ IDEA.
  User: asus1
  Date: 2018/3/6
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看订单 - Caesar</title>
    <link rel="stylesheet" href="/resources/css/check.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/User/checkOrder.js"></script>
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
<%--            <div class="no_user" style="display: none;">
                <a href="register.jsp">
                    注册
                </a>
                <a href="login.jsp" style="margin-right:25px;">
                    登录
                </a>
            </div>--%>
            <div class="user_info" style="display:block;">
                <a class="user_name" title="${sessionScope.user.getEmail()}">${sessionScope.user.getUserName()}</a>
                <a class="vip">VIP${sessionScope.user.getLevel()}</a>
                <a href="purchase.jsp" class="level" title="${sessionScope.user.getLevel()}">
                    网上购票
                </a>
                <span class="line">|</span>
                <a href="profile.jsp">
                    个人信息
                </a>
                <span class="line">|</span>
                <a href="exchange.jsp">
                    积分兑换
                </a>
                <span class="line">|</span>
                <a href="checkOrder.jsp" style="color: #3498db;">
                    查看订单
                </a>
                <span class="line">|</span>
                <a href="/userLogout">
                    登出
                </a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="list_pane">
        <div class="filter clear">
            <div class="filter_name" style="width: 90px;">订单状态：</div>
            <div class="filter-con">
                <a id="no_pay" href="#"  onclick="choiceType(this)" class="active" class="" href="">未支付订单</a>
                <span class="line">|</span>
                <a id="no_execute" class="" href="#" onclick="choiceType(this)">未执行订单</a>
                <span class="line">|</span>
                <a id="finished" class="" href="#" onclick="choiceType(this)">已完成订单</a>
                <span class="line">|</span>
                <a id="unSubscribe" class="" href="#" onclick="choiceType(this)">已退订订单</a>
                <span class="line">|</span>
                <a id="cancel" class="" href="#" onclick="choiceType(this)">已取消订单</a>
                <span class="line">|</span>
            </div>
        </div>
        <div class="order_list">
            <div class="message"></div>
            <table class="table table-bordered table-striped ">
                <thead>
                <tr>
                    <th> 订单状态 </th>
                    <th> 演出名称 </th>
                    <th> 预订时间 </th>
                    <th> 座位情况 </th>
                    <th> 座位编号 </th>
                    <th> 订单金额(元) </th>
                    <th class="op" style="display: none;"> 操作 </th>
                </tr>
                </thead>
                <tbody id="order_info">

                </tbody>
            </table>

        </div>
    </div>

</div>
<div class="main_bottom">
</div>
</body>
</html>