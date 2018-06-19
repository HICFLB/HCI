<%@ page import="edu.nju.onlineTicket.model.User" %>
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
    <title>网上购票 - Caesar</title>
    <link rel="stylesheet" href="/resources/css/homepage.css">
    <link rel="stylesheet" href="/resources/css/purchase.css">
    <link rel="stylesheet" href="/resources/css/jquery.spinner.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/User/purchase.js"></script>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/User/modal.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/User/jquery.seat-charts.min.js"></script>
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
            <div class="no_user" style="display: none;">
                <a href="./views/User/register.jsp">
                    注册
                </a>
                <a href="./views/User/login.jsp" style="margin-right:25px;">
                    登录
                </a>
            </div>
            <div class="user_info" style="display: block;">

                <a class="user_name" title="${sessionScope.user.getEmail()}">${sessionScope.user.getUserName()}</a>
                <a class="vip">VIP${sessionScope.user.getLevel()}</a>
                <a href="purchase.jsp" style="color: #3498db;" class="level" title="${sessionScope.user.getLevel()}">
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
                <a href="checkOrder.jsp">
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
    <div class="list_pane" style="display: block;">
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
    <div class="purchase_pane" style="display: none;">
        <div class="title">
            <h2 id="title" title="">万有音乐系】麦斯米兰“Sea of Silence”寂静如海 2018巡回演唱会-昆明站</h2>
        </div>

        <p class="des" id="venue">场　　馆： 昆明胜利堂剧院</p>
        <p class="des" id="location">地　　点： 昆明胜利堂剧院</p>
        <p class="des" id="perform_time">演出时间：2018-03-14 08:00:00 - 2018-03-14 15:00:00</p>
        <p class="des" id="descrip">描　　述： 昆明胜利堂剧院</p>
        <div class="gPane"  style="display: block;" id="no_selected">
            <div class="choice_ticket">
                <p class="">选择票价：</p>
                <div class="ticket_box">
                    <ul class="type_choice">
                    </ul>
                </div>
            </div>
            <br>
            <div class="selected_pane" >
                <div class="transform" style="display:none;">
                <p>您选择了：</p>
                <div class="selected_tickets">
                    <ul class="item_pane">
<%--                        <li class="item">
                            <div class="item_div">
                                <p style="margin-top:10px;" class="txt_price">"100"元</p>
                                <div class="count">
                                    <input type="text" class="spinnerExample"/>
                                </div>
                            </div>
                            <a class="del" href="#"><span class="fa fa-trash-o"></span>删除</a>
                        </li>--%>
                    </ul>
                </div>

            </div>
            </div>
            <div class="deta_btn">
                <a class="btn_seat" onclick="selected_online()">在线选座</a>
                <a class="buy_now_btn" onclick="buyRightNow()">立即购票</a>
            </div>


        </div>
        <div id="selected" style="display: none;">
            <!---左边座位列表----->

            <div id="seat_area">

            </div>

            <!---右边选座信息----->

            <div class="booking_area">

                <p>座位：</p>

                <ul id="seats_chose"></ul>

                <p>票  数：<span id="tickets_num">0</span></p>

                <p>总  价：<b>￥<span id="total_price">0</span></b></p>
                <p>会员价：<b>￥<span id="credit_price">0</span></b></p>
                <input type="button" class="btn" onclick="buy_selected()" value="确定购买"/>
                <a class="back" onclick="back()">返回</a>
                <div id="legend"></div>

            </div>
        </div>
    </div>
</div>

<div class="main_bottom">
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" aria-hidden="true" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    确认订单价格
                </h4>
            </div>
            <div class="modal-body">
                <p id="original_cost" style="text-align: left;color:#555;">原价：</p>
                <p id="VIP_cost">会员价：</p>
                <p id="discount_cost">您有满减优惠券，使用后价格为，您是否使用？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary buy_use">
                    使用
                </button>
                <button type="button" class="btn btn-primary buy_no_use">
                    不使用
                </button>
                <button type="button" class="btn btn-primary buy_confirm" style="display: none;">
                    确认购买
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>