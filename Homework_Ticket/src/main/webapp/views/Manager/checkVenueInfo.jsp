<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>审核场馆申请 - Caesar - 后台管理</title>
    <link rel="stylesheet" href="/resources/css/examine.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script charset="UTF-8" language="javascript" type="text/javascript" src="/resources/js/Manager/checkVenueInfo.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
</head>
<body>
<div class="home_header">
    <div class="wrap clear">

        <div class="header-left">
            <a class="header-logo" title="Caesar">
                Caesar
                <%-- <img src="resources/img/bird.png" alt="Caesar" width="120" height="90">--%>
            </a>
            <div class="header_title">审核场馆申请</div>
        </div>
        <div class="header_right">
            <div class="user_info" style="display: block;">
                <a class="user_name">Admin</a>
                <a href="examine.jsp">
                    审核场馆申请
                </a>
                <span class="line">|</span>
                <a href="examine.jsp" style="color: #3498db;">
                    审核信息修改
                </a>
                <span class="line">|</span>
                <a href="statisticsManager.jsp">
                    统计
                </a>
                <span class="line">|</span>
                <a href="balance.jsp">
                    结算
                </a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="wrap_pane">
        <div class="filter clear">
            <div class="filter_name">类型：</div>
            <div class="filter_all">
                <a id="all"  href="#"  onclick="choice_type(this)" class="active">全部</a>
                <span class="line">|</span>
            </div>
            <div class="filter-con">
                <a id="no_check" href="#" onclick="choice_type(this)" >尚未审核</a>
                <span class="line">|</span>
                <a id="check_pass"  href="#" onclick="choice_type(this)">审核通过</a>
                <span class="line">|</span>
                <a id="check_refuse"  href="#" onclick="choice_type(this)">拒绝申请</a>
            </div>


        </div>
        <div class="apply_box" style="display: block;">
            <table class="bordered">
                <thead>
                <tr>
                    <th>识别码</th>
                    <th>场馆名称</th>
                    <th>修改时间</th>
                    <th>场馆地址</th>
                    <th>联系方式</th>
                    <th>场馆容量</th>
                    <th>操作/状态</th>
                </tr>
                </thead>
                <tbody id="venue_info">

                </tbody>
            </table>
            <div class="footer">
                <ul class="pagination" id="pagination0"></ul>
            </div>
        </div>
        <div class="no_apply">

        </div>
    </div>

</div>
<div class="main_bottom">
    <div class="clear" style="width: 1000px; height: 30px; margin-left:260px; border-bottom: 1px solid #999;"></div>
</div>
</body>
</html>