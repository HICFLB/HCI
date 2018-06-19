<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>统计信息 - Caesar</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/statisticsVenue.css">
    <link rel="stylesheet" href="/resources/css/jquery-labelauty.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Venue/statisticsVenue.js"></script>

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
                <a href="checkTickets.jsp">
                    检票登记
                </a>
                <span class="line">|</span>
                <a href="statisticsVenue.jsp" style="color: #3498db;">
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
    <div class="statistics_pane">
        <div class="base_info">
            <p>共发布演出(次)：</p>
            <p id="performanceNum"></p>
            <p style="margin-left:400px;">演出应获收入总金额(元)：</p>
            <p id="finance"></p>
            <br>
        </div>
        <div class="perform_info">
            <div class="list_item">
                <div class="performance_info">
                    <a class="por_title">百老汇音乐剧《猫》</a>
                    <p class="por_price">总票数：</p>
                    <p class="por_type">类型：</p>
                    <p class="por_state">状态：</p>
                    <br>
                    <p class="por_time">预订数目：</p>
                    <p class="por_location">退订数目：</p>
                    <p class="por_book">预订收入：</p>
                    <p class="por_unsubscribe">退订收入：</p>
                </div>
            </div>
        </div>


    </div>
</div>
<div class="main_bottom">
</div>
</body>
</html>