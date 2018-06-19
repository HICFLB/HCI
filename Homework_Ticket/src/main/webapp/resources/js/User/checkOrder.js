//界面初始化
$(function() {
    initList(0,"未支付");
});

function choiceType(type) {
    if(type.text=="未支付订单") {
        $('#no_pay').addClass("active");
        $('#no_execute').removeClass("active");
        $('#finished').removeClass("active");
        $('#unSubscribe').removeClass("active");
        $('#cancel').removeClass("active");
        initList(0,"未支付");
    }else if(type.text=="未执行订单"){
        $('#no_pay').removeClass("active");
        $('#no_execute').addClass("active");
        $('#finished').removeClass("active");
        $('#unSubscribe').removeClass("active");
        $('#cancel').removeClass("active");
        initList(1,"未执行");
    }else if(type.text=="已完成订单"){
        $('#no_pay').removeClass("active");
        $('#no_execute').removeClass("active");
        $('#finished').addClass("active");
        $('#unSubscribe').removeClass("active");
        $('#cancel').removeClass("active");
        initList(2,"已完成");
    }else if(type.text=="已退订订单"){
        $('#no_pay').removeClass("active");
        $('#no_execute').removeClass("active");
        $('#finished').removeClass("active");
        $('#unSubscribe').addClass("active");
        $('#cancel').removeClass("active");
        initList(3,"已退订");
    }else if(type.text=="已取消订单"){
        $('#no_pay').removeClass("active");
        $('#no_execute').removeClass("active");
        $('#finished').removeClass("active");
        $('#unSubscribe').removeClass("active");
        $('#cancel').addClass("active");
        initList(4,"已取消");
    }
}

function initList(type,info) {
    var email = $('.user_name').attr("title");
    $.ajax({
        type:"POST",
        url:"/getOrderListByType",
        data:{"type":type,"email":email},
        success:function (data){
            var list = $.parseJSON(data);
            if(list.length>0) {
                $('.table').show();
                $('.message').empty();
                setList(list,type,info);
            }
            else{
                $('#order_info').empty();
                $('.message').empty();
                if(type==0){
                    $('.table').hide();
                    $(".message").append("<p style='margin-left:400px;'>您暂时没有未支付订单！</p>");
                }

                if(type==1){
                    $('.table').hide();
                    $(".message").append("<p style='margin-left:400px;'>您暂时没有未执行订单！</p>");
                }
                if(type==2){
                    $('.table').hide();
                    $(".message").append("<p style='margin-left:400px;'>您暂时没有已完成订单！</p>");
                }
                if(type==3){
                    $('.table').hide();
                    $(".message").append("<p style='margin-left:400px;'>您暂时没有已退订订单！</p>");
                }
                if(type==4){
                    $('.table').hide();
                    $(".message").append("<p style='margin-left:400px;'>您暂时没有已取消订单！</p>");
                }
            }
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
}

function setList(list,type,info) {
    if(list.length>0) {
        $('#order_info').empty();
        if(type == 0){
            for(var i=0; i<list.length;i++) {
                $('.op').show();
                $('#order_info').append("<tr>"+
                    "<td name='orderID' title='"+list[i].orderID+"'>"+info+"</td>"+
                    "<td>"+ list[i].performanceName+"</td>"+
                    "<td>"+ list[i].orderTime+"</td>"+
                    "<td>"+ list[i].situation+"</td>"+
                    "<td>"+ list[i].ticketNo+"</td>"+
                    "<td name='orderPrice'>"+list[i].price+"</td>"+
                    "<td><a class='pay_rightNow btn_table' onclick='pay(this)'>立即支付</a></td>"+
                    "</tr>");
            }
        }else if(type == 1){
            for(var i=0; i<list.length;i++) {
                $('.op').show();
                $('#order_info').append("<tr>"+
                    "<td name='orderID' title='"+list[i].orderID+"'>"+info+"</td>"+
                    "<td>"+ list[i].performanceName+"</td>"+
                    "<td>"+ list[i].orderTime+"</td>"+
                    "<td>"+ list[i].situation+"</td>"+
                    "<td>"+ list[i].ticketNo+"</td>"+
                    "<td name='orderPrice'>"+list[i].price+"</td>"+
                    "<td><a class='pay_rightNow btn_table' onclick='cancel(this)'>退订</a></td>"+
                    "</tr>");
            }
        } else{
            for(var i=0; i<list.length;i++) {
                $('.op').hide();
                $('#order_info').append("<tr>"+
                    "<td name='orderID' title='"+list[i].orderID+"'>"+info+"</td>"+
                    "<td>"+ list[i].performanceName+"</td>"+
                    "<td>"+ list[i].orderTime+"</td>"+
                    "<td>"+ list[i].situation+"</td>"+
                    "<td>"+ list[i].ticketNo+"</td>"+
                    "<td>"+list[i].price+"</td>"+
                    "</tr>");
            }
        }

    }
}

function pay(item) {
    var email = $('.user_name').attr("title");
    var orderID = $(item).parent().parent().parent().find("td[name='orderID']").attr("title");
    var orderPrice = $(item).parent().parent().parent().find("td[name='orderPrice']").html();
    if(confirm("确认支付")===true){
        $.ajax({
            type:"POST",
            url:"/payRightNow",
            data:{"orderID":orderID,"email":email,"orderPrice":orderPrice},
            success:function (data){
                if(data=="Success"){
                    alert("支付成功！");
                    location.reload();
                }else if(data=="NotEnough"){
                    alert("您的余额不足！");
                }else {
                    alert("支付失败！");
                }
            },
            error:function () {
                alert("Sth Wrong！");
            }
        });
    }
}

function cancel(item) {
    var orderID = $(item).parent().parent().find("td[name='orderID']").attr("title");
    if(confirm("确认退订，若离演出时间不足24小时扣除20%费用！")===true){
        $.ajax({
            type:"POST",
            url:"/cancelOrder",
            data:{"orderID":orderID},
            success:function (data){
                if(data!="Fail"){
                    alert(data);
                    location.reload();
                }else {
                    alert("退订失败！");
                }
            },
            error:function () {
                alert("Sth Wrong！");
            }
        });
    }
}