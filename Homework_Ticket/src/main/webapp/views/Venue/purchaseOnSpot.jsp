<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>现场购票 - Caesar</title>

    <link rel="stylesheet" href="/resources/css/homepage.css">
    <link rel="stylesheet" href="/resources/css/purchase.css">
    <link rel="stylesheet" href="/resources/css/jquery.spinner.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Venue/purchaseOnSpot.js"></script>
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
            <div class="user_info" style="display:block;">
                <a class="user_name" title="${sessionScope.venue.getIdCode()}">${sessionScope.venue.getVenueName()}</a>
                <a href="modifyVenue.jsp" >
                    修改信息
                </a>
                <span class="line">|</span>
                <a href="publish.jsp">
                    发布演出
                </a>
                <span class="line">|</span>
                <a href="purchaseOnSpot.jsp"  style="color: #3498db;">
                    现场购票
                </a>
                <span class="line">|</span>
                <a href="checkTickets.jsp">
                    检票登记
                </a>
                <span class="line">|</span>
                <a href="statisticsVenue.jsp">
                    统计信息
                </a>
                <span class="line">|</span>
                <a href="/venueLogout">
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
        <div id="selected" style="display: block;">
            <!---左边座位列表----->
            <div id="seat_area">
            </div>
            <!---右边选座信息----->
            <div class="booking_area">
                <p>座位：</p>
                <ul id="seats_chose"></ul>
                <p>票  数：<span id="tickets_num">0</span></p>
                <p>总  价：<b>￥<span id="total_price">0</span></b></p>
                <input type="text" class="user_email">
                <br>
                <br>
                <a href="#" class="confirm_email" onclick="confirm_user()">验证会员并支付</a>
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