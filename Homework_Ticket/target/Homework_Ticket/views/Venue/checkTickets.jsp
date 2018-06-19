<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>检票登记 - Caesar</title>

    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/checkTickets.css">
    <link rel="stylesheet" href="/resources/css/homepage.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Venue/checkTickets.js"></script>

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
                <a href="purchaseOnSpot.jsp">
                    现场购票
                </a>
                <span class="line">|</span>
                <a href="checkTickets.jsp"  style="color: #3498db;">
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
        <p class="introduction_info">24小时内开始的演出：</p>
        <div class="listprod" id="perform_info">

        </div>
    </div>
    <div class="check_pane" style="display: none;">
        <div class="title">
            <h2 id="per_name">演出名称</h2>
            <a id="type">类型</a>
            <p id="time"></p>
        </div>
        <div class="per_info">
            <p>简单描述：<a id="description" style="color:#555;">东方闪电分开了叫叫看</a></p>
            <p>总票数：<a id="totalTickets" style="color:#555;">555</a></p>
            <P>卖出票数：<a id="soldTickets" style="color:#555;">480</a></P>
            <p>已检票数：<a id="checkedTickets" style="color:#555;">200</a></p>
            <br>
            <br>
            <a href="#" class="return" onclick="returnList()">点击返回</a>
        </div>
        <div class="op_info">
            <input name="ticketID" id="ticketID" type="text" placeholder="请输入票号" style="width:220px; height: 30px;">
            <br>
            <input class="btn_check" type="button" value="检票" onclick="checkTicket()">
        </div>
    </div>

</div>
<div class="main_bottom">
</div>

</body>
</html>