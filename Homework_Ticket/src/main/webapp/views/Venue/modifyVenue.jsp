<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改场馆信息 - Caesar</title>

    <link rel="stylesheet" href="/resources/css/profile.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Venue/modifyVenue.js"></script>

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
                <a href="apply.jsp">
                    注册
                </a>
                <a href="login.jsp" style="margin-right:25px;">
                    登录
                </a>
            </div>
            <div class="user_info" style="display:block;">
                <a class="user_name" title="${sessionScope.venue.getIdCode()}">${sessionScope.venue.getVenueName()}</a>
                <a href="modifyVenue.jsp"  style="color: #3498db;">
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
    <div class="profile_pane">
        <div class="box-choice">
            <ul>
                <li class="on"><a href="#">场馆信息</a></li>
                <li><a href="#">修改密码</a></li>
            </ul>
        </div>
        <div class="personal_info">
            <form class="info_pane" style="display: block;">
                <div class="in">
                    <span class="left">场馆名称：</span>
                    <input id="venue" type="text" readonly="readonly" value="${sessionScope.venue.getVenueName()}">
                </div>
                <div class="in">
                    <span class="left">场馆地址：</span>
                    <input id="location" readonly="readonly" value="${sessionScope.venue.getLocation()}" type="text">
                </div>
                <div class="in">
                    <span class="left">联系电话：</span>
                    <input id="contact" type="text" readonly="readonly" value="${sessionScope.venue.getContact()}">
                </div>
                <div class="in">
                    <span class="left">场馆容量：</span>
                    <input id="volume" type="text" readonly="readonly" value="${sessionScope.venue.getTotalSeat()}">
                </div>
                <div class="in" style="width:800px;margin-left:200px;">
                    <c:choose>
                        <c:when test="${sessionScope.venue.getModifyState()==0}">
                            <input id="control_btn" style="display: block;" type="button" value="修改信息" onclick="change_info()">
                            <input id="confirm_btn" style="display: none;" type="button" value="申请修改">
                        </c:when>
                        <c:when test="${sessionScope.venue.getModifyState()==3}">
                            <div class="no_checked" style="display: block;color:#555;font-size: 22px;">您之前的修改申请尚未有结果反馈，请耐心等待！</div>
                        </c:when>
                        <c:when test="${sessionScope.venue.getModifyState()==1}">
                            <div class="is_passed" style="display: block;color:#555;font-size: 22px;">您之前的修改申请已经通过！<input type="button" value="返回" onclick="return_info()"></div>
                        </c:when>
                        <c:otherwise>
                            <div class="is_passed" style="display: block;color:#555;font-size: 22px;">您之前的修改申请未通过，请重新修改，未通过原因为：${sessionScope.venue.getRefuseInfo()}<p></p><input type="button" value="返回" onclick="return_info()"></div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>

<%--            <div class="apply_result">
                <div class="no_checked" style="display: block;color:#555;font-size: 22px;text-align: center;">您的修改申请尚未有结果反馈，请耐心等待！</div>
                <div class="is_passed" style="display: none;color:#555;font-size: 22px;">您的修改申请已经通过！<input type="button" value="返回" onclick="return_info()"></div>
                <div class="is_passed" style="display: none;color:#555;font-size: 22px;">您的修改申请未通过，请重新修改，未通过原因为：<p><input type="button" value="返回" onclick="return_info()"></p></div>
            </div>--%>
        </div>
        <div class="change_password" style="display:none;">
            <form>
                <div class="in">
                    <span class="left">旧&nbsp;&nbsp;密&nbsp;&nbsp;码：</span>
                    <input id="old_pwd" type="password" placeholder="请输入旧密码!" value="">
                </div>
                <div class="in">
                    <span class="left">新&nbsp;&nbsp;密&nbsp;&nbsp;码：</span>
                    <input id="new_pwd" type="password" placeholder="请输入新密码！">
                </div>
                <div class="in">
                    <span class="left">确认密码：</span>
                    <input id="confirm_pwd" type="password" placeholder="再次输入新密码！">
                </div>
                <div class="in">
                    <input type="button" value="确认修改" onclick="change_password()">
                </div>
            </form>
        </div>

    </div>

</div>
<div class="main_bottom">
</div>

<script type="text/javascript">
    $('.box-choice ul li').click(function(){
        $('.box-choice ul li').removeClass('on');
        $(this).addClass('on');
        if($(this).find("a").text()==="场馆信息"){
            $('.personal_info').show();
            $('.change_password').hide();
        };
        if($(this).find("a").text()==="修改密码"){
            $('.personal_info').hide();
            $('.change_password').show();
        };
    });
</script>
</body>
</html>