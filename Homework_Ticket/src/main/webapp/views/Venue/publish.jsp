<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>发布演出 - Caesar</title>

    <link rel="stylesheet" href="/resources/css/publish.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/jquery-labelauty.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Venue/publish.js"></script>
    <script src="https://cdn.bootcss.com/labelauty/1.1.4/jquery-labelauty.min.js"></script>

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
                <a href="publish.jsp" style="color: #3498db;">
                    发布演出
                </a>
                <span class="line">|</span>
                <a href="purchaseOnSpot.jsp">
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
    <div class="publish_pane">
        <form class="perform_info">
            <p style="float: left;">演出名称：<input type="text" requirede="required" name="perform_name" id="perform_name"></p>
            <p style="float: right; margin-right:70px;">演出时间：<input type="text" placeholder="请输入开始时间" requirede="required" name="perform_time" id="begin_time">&nbsp;-&nbsp;<input placeholder="格式yyyy-MM-dd HH:mm:ss" type="text" requirede="required" name="perform_time" id="end_time"></p>
            <br>
            <br>
            <br>
            <div>
            <p style="float:left;">演出类型：</p>
                <ul id="choice_type">
                <li><input type="radio" name="radio" value="0" data-labelauty="音乐剧"></li>
                <li><input type="radio" name="radio" value="1" data-labelauty="演唱会"></li>
                <li><input type="radio" name="radio" value="2" data-labelauty="话剧戏剧"></li>
                <li><input type="radio" name="radio" value="3" data-labelauty="舞蹈表演"></li>
                <li><input type="radio" name="radio" value="4" data-labelauty="体育比赛"></li>
            </ul>
            </div>

            <div>座位安排：共&nbsp;<input onkeyup="value=value.replace(/[^\d]/g,'')" id="row_num" class="row">&nbsp;行&nbsp;&nbsp;<input onkeyup="value=value.replace(/[^\d]/g,'')" id="column_num" class="column">&nbsp;&nbsp;列 <p style="float:right;margin-right:70px;margin-top:0px;">总票数（容量）:&nbsp;<input id="total_num"></p></div>
            <br>
            <p style="float: left;">简单描述：<input style="width: 770px;" id="description"></p>
            <br>
            <br>
            <br>
            <div style="float:left; margin-top:20px;">座位种类及票价：<input type="text" id="loop_num" oninput="set_tickets(this)" placeholder="请输入拟定座位种类数"></div>
            <div class="loop_body" style="margin-top:70px;">
<%--                <div class="item_pane">
                    <p style="float:left;margin-left:20px;">种类名称：<input  style="width:120px;" type="text" requirede="required" name="seat_type_name" ></p>
                    <p style="float: right; margin-right:15px;">票价：<input style="width:120px;" type="text" requirede="required" name="seat_type_price">&nbsp;元</p>
                    <p style="float: right; margin-right:60px;">行:&nbsp;<input  class="row">-<input class="row">  列:&nbsp;<input class="column">-<input class="column"></p>
                </div>--%>
            </div>
            <div class="bottom">
                <input type="button" value="确认发布" onclick="publish_performance()">
            </div>
        </form>
    </div>

</div>
<div class="main_bottom">
</div>
<script>
    $(function(){
        $(':input').labelauty();
    });
</script>
</body>
</html>