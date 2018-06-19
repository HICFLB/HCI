<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人信息 - Caesar</title>

    <link rel="stylesheet" href="/resources/css/exchange.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/User/exchange.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
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
                <a href="purchase.jsp" class="level" title="${sessionScope.user.getLevel()}">
                    网上购票
                </a>
                <span class="line">|</span>
                <a href="profile.jsp">
                    个人信息
                </a>
                <span class="line">|</span>
                <a href="exchange.jsp"  style="color: #3498db;" >
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
    <div class="exchange_pane">
        <div class="credit_info">
            <p>您当前的积分为：</p>
            <p id="user_credit" title="消费一元获得十积分">${sessionScope.user.getCredit()}</p>
        </div>
        <div class="discount_info">
            <p>您当前拥有的消费券：</p>
            <p class="discount" style="margin-left:20px;">￥5元兑换券 </p>
            <p>&nbsp;&nbsp;×&nbsp;&nbsp;${sessionScope.user.getFiveOff()}</p>
            <p class="discount" style="margin-left:20px;">￥10元兑换券 </p>
            <p>&nbsp;&nbsp;×&nbsp;&nbsp;${sessionScope.user.getTenOff()}</p>
            <p class="discount" style="margin-left:20px;">￥50元兑换券 </p>
            <p>&nbsp;&nbsp;×&nbsp;&nbsp;${sessionScope.user.getFiftyOff()}</p>
            <p class="discount" style="margin-left:20px;">￥100元兑换券 </p>
            <p>&nbsp;&nbsp;×&nbsp;&nbsp;${sessionScope.user.getHundredOff()}</p>
        </div>
        <br>
        <div class="discount_pane">
            <div class="coupon01">
                <div class="coupon-left">
                    <div class="coupon-inner">
                        <div class="coupon-money">
                            <span>¥</span>
                            <span class="sum">5.00</span>
                        </div>
                        <div class="coupon-condition">
                            <p>满50.00可用（花费500积分）</p>
                        </div>
                    </div>
                </div>
                <div class="coupon-light"></div>
                <div class="coupon-right">
                    <div class="coupon-inner">
                        <div class="coupon-time">
                            永久有效<br>
                            <a class="exchange" href="#" onclick="exchange_credit(500)">兑换</a>
                        </div>
                        <i class="coupon-circle top"></i>
                        <i class="coupon-circle bottom"></i>
                    </div>
                </div>

            </div>
            <div class="coupon02">
                <div class="coupon-left">
                    <div class="coupon-inner">
                        <div class="coupon-money">
                            <span>¥</span>
                            <span class="sum">10.00</span>
                        </div>
                        <div class="coupon-condition">
                            <p>满100.00可用（花费1000积分）</p>
                        </div>
                    </div>
                </div>
                <div class="coupon-light"></div>
                <div class="coupon-right">
                    <div class="coupon-inner">
                        <div class="coupon-time">
                            永久有效<br>
                            <a class="exchange" onclick="exchange_credit(1000)">兑换</a>
                        </div>
                        <i class="coupon-circle top"></i>
                        <i class="coupon-circle bottom"></i>
                    </div>
                </div>

            </div>
            <br>
            <div class="coupon03">
                <div class="coupon-left">
                    <div class="coupon-inner">
                        <div class="coupon-money">
                            <span>¥</span>
                            <span class="sum">50.00</span>
                        </div>
                        <div class="coupon-condition">
                            <p>满500.00可用（花费5000积分）</p>
                        </div>
                    </div>
                </div>
                <div class="coupon-light"></div>
                <div class="coupon-right">
                    <div class="coupon-inner">
                        <div class="coupon-time">
                            永久有效<br>
                            <a class="exchange" onclick="exchange_credit(5000)">兑换</a>
                        </div>
                        <i class="coupon-circle top"></i>
                        <i class="coupon-circle bottom"></i>
                    </div>
                </div>
            </div>
            <div class="coupon04">
                <div class="coupon-left">
                    <div class="coupon-inner">
                        <div class="coupon-money">
                            <span>¥</span>
                            <span class="sum">100.00</span>
                        </div>
                        <div class="coupon-condition">
                            <p>满1000.00可用（花费10000积分）</p>
                        </div>
                    </div>
                </div>
                <div class="coupon-light"></div>
                <div class="coupon-right">
                    <div class="coupon-inner">
                        <div class="coupon-time">
                            永久有效<br>
                            <a class="exchange" onclick="exchange_credit(10000)">兑换</a>
                        </div>
                        <i class="coupon-circle top"></i>
                        <i class="coupon-circle bottom"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="main_bottom">
</div>
</body>
</html>