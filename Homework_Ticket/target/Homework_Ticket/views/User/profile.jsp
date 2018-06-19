<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人信息 - Caesar</title>

    <link rel="stylesheet" href="/resources/css/profile.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/User/profile.js"></script>

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
            <a href="register.jsp">
                注册
            </a>
            <a href="login.jsp" style="margin-right:25px;">
                登录
            </a>
            </div>
            <div class="user_info" style="display:block;">
                <a class="user_name" title="${sessionScope.user.getEmail()}">${sessionScope.user.getUserName()}</a>
                <a class="vip">VIP${sessionScope.user.getLevel()}</a>
                <a href="purchase.jsp">
                    网上购票
                </a>
                <span class="line">|</span>
                <a href="profile.jsp" style="color: #3498db;">
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
    <div class="profile_pane">
        <div class="box-choice">
            <ul>
                <li class="on"><a href="#">个人信息</a></li>
                <li><a href="#">修改密码</a></li>
            </ul>
        </div>
        <div class="personal_info">
            <form>
                <div class="in">
                    <span class="left">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</span>
                    <input id="mail" type="text" readonly="readonly" value="${sessionScope.user.getEmail()}">
                </div>
                <div class="in">
                    <span class="left">用户名：</span>
                    <input id="new_name" readonly="readonly" value="${sessionScope.user.getUserName()}" type="text">
                    <input id="modify_name" style="display:block; float:right; margin-right:220px;margin-top:20px;" type="button" value="修改" onclick="change_name()">
                    <input id="confirm_name" style="display: none;" type="button" value="确定">
                </div>
                <div class="in">
                    <span class="left">积&nbsp;&nbsp;&nbsp;&nbsp;分：</span>
                    <input type="text" readonly="readonly" value="${sessionScope.user.getCredit()}">
                </div>
                <div class="in">
                    <span class="left">总金额：</span>
                    <input type="text" readonly="readonly" value="${sessionScope.user.getConsumption()}&nbsp;元">
                </div>
                <div class="in">
                    <input type="button" value="注销账户" onclick="logOff()">
                    <p>注：注销账户则不能恢复，请慎重选择！</p>
                </div>
            </form>
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
        if($(this).find("a").text()==="个人信息"){
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