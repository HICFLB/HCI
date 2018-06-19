//界面初始化
$(function() {
    $('#all').addClass("active");
    $('#melodrama').removeClass("active");
    $('#concert').removeClass("active");
    $('#drama').removeClass("active");
    $('#dance').removeClass("active");
    $('#PE').removeClass("active");
    initList(0);
});

;(function ($) {
    $.fn.spinner = function (opts) {
        return this.each(function () {
            var defaults = {value:0, min:0}
            var options = $.extend(defaults, opts)
            var keyCodes = {up:38, down:40}
            var container = $('<div></div>')
            container.addClass('spinner')
            var textField = $(this).addClass('value').attr('maxlength', '2').val(options.value)
                .bind('keyup paste change', function (e) {
                    var field = $(this)
                    if (e.keyCode == keyCodes.up) changeValue(1)
                    else if (e.keyCode == keyCodes.down) changeValue(-1)
                    else if (getValue(field) != container.data('lastValidValue')) validateAndTrigger(field)
                })
            textField.wrap(container)

            var increaseButton = $('<button class="increase">+</button>').click(function () { changeValue(1) })
            var decreaseButton = $('<button class="decrease">-</button>').click(function () { changeValue(-1) })

            validate(textField)
            container.data('lastValidValue', options.value)
            textField.before(decreaseButton)
            textField.after(increaseButton)

            function changeValue(delta) {
                textField.val(getValue() + delta)
                validateAndTrigger(textField)
            }

            function validateAndTrigger(field) {
                clearTimeout(container.data('timeout'))
                var value = validate(field)
                if (!isInvalid(value)) {
                    textField.trigger('update', [field, value])
                }
            }

            function validate(field) {
                var value = getValue()
                if (value <= options.min) decreaseButton.attr('disabled', 'disabled')
                else decreaseButton.removeAttr('disabled')
                field.toggleClass('invalid', isInvalid(value)).toggleClass('passive', value === 0)

                if (isInvalid(value)) {
                    var timeout = setTimeout(function () {
                        textField.val(container.data('lastValidValue'))
                        validate(field)
                    }, 500)
                    container.data('timeout', timeout)
                } else {
                    container.data('lastValidValue', value)
                }
                return value
            }

            function isInvalid(value) { return isNaN(+value) || value < options.min; }

            function getValue(field) {
                field = field || textField;
                return parseInt(field.val() || 0, 10)
            }
        })
    }
})(jQuery)

function choiceType(type) {
    if(type.text=="全部") {
        $('#all').addClass("active");
        $('#melodrama').removeClass("active");
        $('#concert').removeClass("active");
        $('#drama').removeClass("active");
        $('#dance').removeClass("active");
        $('#PE').removeClass("active");
        initList(0);
    }else if(type.text=="音乐剧"){
        $('#all').removeClass("active");
        $('#melodrama').addClass("active");
        $('#concert').removeClass("active");
        $('#drama').removeClass("active");
        $('#dance').removeClass("active");
        $('#PE').removeClass("active");
        initList(1);
    }else if(type.text=="演唱会"){
        $('#all').removeClass("active");
        $('#melodrama').removeClass("active");
        $('#concert').addClass("active");
        $('#drama').removeClass("active");
        $('#dance').removeClass("active");
        $('#PE').removeClass("active");
        initList(2);
    }else if(type.text=="话剧歌剧"){
        $('#all').removeClass("active");
        $('#melodrama').removeClass("active");
        $('#concert').removeClass("active");
        $('#drama').addClass("active");
        $('#dance').removeClass("active");
        $('#PE').removeClass("active");
        initList(3);
    }else if(type.text=="舞蹈表演"){
        $('#all').removeClass("active");
        $('#melodrama').removeClass("active");
        $('#concert').removeClass("active");
        $('#drama').removeClass("active");
        $('#dance').addClass("active");
        $('#PE').removeClass("active");
        initList(4);
    }else if(type.text=="体育比赛"){
        $('#all').removeClass("active");
        $('#melodrama').removeClass("active");
        $('#concert').removeClass("active");
        $('#drama').removeClass("active");
        $('#dance').removeClass("active");
        $('#PE').addClass("active");
        initList(5);
    }
}

function initList(type) {
    $.ajax({
        type:"POST",
        url:"/getPerformListByType",
        data:{"type":type},
        success:function (data){
            var list = $.parseJSON(data);
            if(list.length>0) {
                setList(list);
            }
            else{
                $('#perform_info').empty();
                if(type==0)
                    $("#perform_info").append("<p style='margin-left:80px;'>暂时没有任何演出！</p>");
                if(type==1)
                    $("#perform_info").append("<p style='margin-left:80px;'>暂时没有任何音乐剧！</p>");
                if(type==2)
                    $("#perform_info").append("<p style='margin-left:80px;'>暂时没有任何演唱会！</p>");
                if(type==3)
                    $("#perform_info").append("<p style='margin-left:80px;'>暂时没有任何话剧戏剧！</p>");
                if(type==4)
                    $("#perform_info").append("<p style='margin-left:80px;'>暂时没有任何舞蹈表演！</p>");
                if(type==5)
                    $("#perform_info").append("<p style='margin-left:80px;'>暂时没有任何体育比赛！</p>");
            }
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
}

function setList(list) {
    if(list.length>0) {
        $('#perform_info').empty();
        for(var i=0; i<list.length;i++) {
            var info;
            if(list[i].performanceType===0)
                info = "音乐剧";
            if(list[i].performanceType===1)
                info = "演唱会";
            if(list[i].performanceType===2)
                info = "话剧歌剧";
            if(list[i].performanceType===3)
                info = "舞蹈表演";
            if(list[i].performanceType===4)
                info = "体育比赛";
            $('#perform_info').append("<div class='list_item'>"+
                "<div class='performance_info'>"+
                "<div class='perform_img'>"+
                "<img src='/resources/img/war.png'>"+
                "</div><div class='info'>"+
                "<a class='por_title'>"+list[i].performanceName+"</a>"+
                "<p class='por_price'>票价<span class='rmb'>￥</span><span class='num'>"+list[i].minPrice+" ~"+ list[i].maxPrice+"</span></p>"+
                "<p class='por_type'>类型："+info+"</p>"+
                "<p class='por_time'>时间："+list[i].beginTime+" - "+ list[i].endTime+"</p>"+
                "<p class='por_location'>场馆："+list[i].venueName+"</p>"+
                "<p class='por_loc'>地点："+ list[i].location+"</p>"+
                "</div></div> "+
                "<div class='purchase_btn'><a href='#' title='"+list[i].performanceID+"' onclick='toPurchase("+JSON.stringify(list[i])+")'>立即购票</a></div>"+
                "</div>");
        }
    }
}

var selected_perform;
function toPurchase(perform) {
    selected_perform = perform;
    $('.list_pane').hide();
    $('.purchase_pane').show();
    console.log(perform);
    $('#title').html(perform.performanceName);
    $('#title').attr("title",perform.performanceID);
    $('#venue').html("场　　馆： " + perform.venueName);
    $('#location').html("地　　点： " + perform.location);
    $('#descrip').html("描　　述： "+perform.description);
    $('#perform_time').html("演出时间："+perform.beginTime+" ~ "+perform.endTime);
    var typeMap = perform.seatSituation.split(";");
    for(var i=0;i<typeMap.length-1;i++){
        var seatType = typeMap[i].split("+")[0];
        var seatNum = typeMap[i].split("+")[1];
        var seatPrice = typeMap[i].split("+")[2];
        $('.type_choice').append("<li><a href='#' onclick='addPriceItem("+JSON.stringify(seatType)+","+JSON.stringify(seatNum)+","+JSON.stringify(seatPrice)+","+i+")'>\""+seatType+"\" \""+seatPrice+"\"元</a></li>");
    }
}

function addPriceItem(seatType, seatNum, seatPrice, i) {
    if ($('#item' + i).length <= 0) {
    $('.transform').show();
    $('.item_pane').append("<li class='item' title='"+seatType+"' id='item" + i + "'>" +
        "<div class='item_div'>" +
        "<p style='margin-top:10px;' class='txt_price'>" + seatPrice + "元</p>" +
        "<div class='count'>" +
        "<input type='text' title='"+seatPrice+"' class='spinnerExample" + i + "'/>" +
        "</div>" +
        "</div>" +
        "<a class='del' href='#' onclick='deleteItem(" + i + ")'><span class='fa fa-trash-o'></span>删除</a>" +
        "</li>");

    $('.spinnerExample' + i).spinner({});
    }else{
        $('#item'+i).remove();
        if($('.item_pane li').length<=0){
            $('.transform').hide();
        }
    }
}

function deleteItem(i) {
    $('#item'+i).remove();
    if($('.item_pane li').length<=0){
        $('.transform').hide();
    }
}

function buyRightNow() {
    var performanceID = $('#title').attr("title");
    var email = $('.user_name').attr("title");
    if($('.item_pane li').length<=0){
        alert("请先选择票价及张数！");
    }else{
        var buy_type = new Array();
        var buy_num = new Array();
        var buy_price = new Array();
        var i = 0;
        var total_price = 0;
        var total_num = 0;
        $('.item_pane li').each(function(){
            var id = this.getAttribute("id");
            buy_type[i] = $('#'+id).attr("title");
            buy_num[i]=$('#'+id+" input").val();
            buy_price[i]=$('#'+id+" input").attr("title");
            total_price = total_price + parseInt(buy_num[i]) * parseFloat(buy_price[i]);
            total_num = total_num + parseInt(buy_num[i]);
            i=i+1;
        });
        var init_price = total_price;
        if(init_price===0){
            alert("请选择购票数量！");
        }else if(total_num>20){
            alert("您最多只能购买20张票！");
        }else{
            $.ajax({
                type:"POST",
                url:"/getUserInfo",
                data:{"email":email},
                success:function (data){
                    var user = $.parseJSON(data)[0];
                    if(user.level==0){
                        total_price = total_price*0.9;
                    }else if(user.level==1){
                        total_price = total_price*0.88;
                    }else if(user.level==2){
                        total_price = total_price*0.85;
                    }
                    var final_price = 0;
                    var discount = -1;
                    if(total_price>=50&&total_price<100&&user.fiveOff>0){
                        discount = 0;
                        $('.buy_use').show();
                        $('.buy_no_use').show();
                        $('.buy_confirm').hide();
                        $('#discount_cost').show();
                        $('#discount_cost').html("您有一张满50减5优惠券可以使用，使用后价格为"+(total_price-5)+",您是否使用？");
                        final_price = total_price-5;
                    }else if(total_price>=100&&total_price<500&&user.tenOff>0){
                        discount = 1;
                        $('.buy_use').show();
                        $('.buy_no_use').show();
                        $('.buy_confirm').hide();
                        $('#discount_cost').show();
                        $('#discount_cost').html("您有一张满100减10优惠券可以使用，使用后价格为"+(total_price-10)+",您是否使用？");
                        final_price = total_price-10;
                    }else if(total_price>=500&&total_price<1000&&user.fiftyOff>0){
                        discount = 2;
                        $('.buy_use').show();
                        $('.buy_no_use').show();
                        $('.buy_confirm').hide();
                        $('#discount_cost').show();
                        $('#discount_cost').html("您有一张满500减50优惠券可以使用，使用后价格为"+(total_price-50)+",您是否使用？");
                        final_price = total_price-50;
                    }else if(total_price>=1000&&user.hundredOff>0){
                        discount = 3;
                        $('.buy_use').show();
                        $('.buy_no_use').show();
                        $('.buy_confirm').hide();
                        $('#discount_cost').show();
                        $('#discount_cost').html("您有一张满1000减100优惠券可以使用，使用后价格为"+(total_price-100)+",您是否使用？");
                        final_price = total_price-100;
                    }else{
                        $('.buy_use').hide();
                        $('.buy_no_use').hide();
                        $('.buy_confirm').show();
                        $('#discount_cost').hide();
                    }
                    $('#original_cost').html("原价："+init_price+"元");
                    $('#VIP_cost').html("会员价："+total_price+"元");
                    $("#myModal").modal("show");
                    $('.buy_use').click(function () {
                        purchase_right_now(email, performanceID, buy_type, buy_num, final_price, discount);
                    });
                    $('.buy_no_use').click(function(){
                        purchase_right_now(email, performanceID, buy_type, buy_num, total_price, -1)
                    });
                    $('.buy_confirm').click(function () {
                        purchase_right_now(email, performanceID, buy_type, buy_num, total_price, -1);
                    })
                },
                error:function () {
                    alert("Sth Wrong！")
                }
            });
        }
    }
}

function purchase_right_now(email, performanceID, buy_type, buy_num, order_price, use_discount) {
    $.ajax({
        type:"POST",
        url:"/purchaseRightNow",
        data:{"email":email, "performanceID":performanceID, "buy_type":buy_type, "buy_num":buy_num ,"order_price":order_price, "use_discount":use_discount},
        success:function (data){
            if(data!=-1&&data!=-2){
                if(confirm("生成订单成功，是否立即支付？")===true){
                    $.ajax({
                        type:"POST",
                        url:"/payRightNow",
                        data:{"orderID":data,"email":email,"orderPrice":order_price},
                        success:function (data){
                            if(data==="Success"){
                                alert("支付成功！");
                                location.reload();
                            }else if(data==="NotEnough"){
                                alert("您的余额不足！");
                            }else {
                                alert("支付失败！");
                            }
                        },
                        error:function () {
                            alert("Sth Wrong！");
                        }
                    });
                }else{
                    location.reload();
                }
            }else {
                if(data==-2){
                    alert("生成订单失败:配票失败，没有足够的票！");
                    location.reload();
                }else{
                    alert("生成订单失败!");
                }
            }
        },
        error:function () {
            alert("Sth Wrong！")
        }
    });
}

function selected_online() {
    $('#selected').show();
    $('#no_selected').hide();
    var row_num = selected_perform.rowNum;
    var column_num = selected_perform.columnNum;
    map = [];
    for(var i=0;i<row_num;i++){
        map[i]="";
        for(var j=0;j<column_num;j++){
            map[i]+="c";
        }
    }
    var $cart = $('#seats_chose'), //座位区
        $tickets_num = $('#tickets_num'), //票数
        $total_price = $('#total_price'); //票价总额
    var performanceID = $('#title').attr("title");
    var tickets;
    $.ajax({
        type:"POST",
        url:"/getTicketsByPerformance",
        data:{"performanceID":performanceID},
        success:function (data){
            tickets = $.parseJSON(data);
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
    var firstSeatLabel = 1;
    var sc = $('#seat_area').seatCharts({
        map: map,
        naming: {//设置行列等信息
            top: true, //不显示顶部横坐标（行）
            left:true,
            getLabel: function() { //返回座位信息
                return firstSeatLabel++;
            }

        },
        legend: {//定义图例
            node: $('#legend'),
            items: [
                ['c', 'available', '可选座'],
                ['c', 'unavailable', '已售出']

            ]
        },
        click: function() {

            if (this.status() == 'available') { //若为可选座状态，添加座位

                if($("#seats_chose li").length==6){
                    alert("您最多只能选择购买6张票！");
                    return 'available';
                }else{
                    $('<li>' + (this.settings.row + 1) + '排' + this.settings.column + '座:' + this.settings.label +'号'+'</li>')
                        .attr('id', 'cart-item-' + this.settings.id)
                        .data('seatId', this.settings.id)
                        .appendTo($cart);
                    for(var j=0;j<tickets.length;j++){
                        if(this.settings.label==tickets[j].noOfSeats){
                            $('#cart-item-'+this.settings.id).attr('title',tickets[j].singleCost);
                        }
                    }
                    $tickets_num.text(sc.find('selected').length + 1); //统计选票数量
                    $total_price.text(getTotalPrice(sc,tickets)+ parseFloat($('#cart-item-'+this.settings.id).attr("title")));//计算票价总金额
                    $('#credit_price').html(getCreditPrice($total_price.text()));
                    return 'selected';

                }

            } else if (this.status() == 'selected') { //若为选中状态
                $tickets_num.text(sc.find('selected').length - 1);//更新票数量
                for(var k=0;k<tickets.length;k++){
                    if(this.settings.label==tickets[k].noOfSeats){
                        $('#cart-item-'+this.settings.id).attr('title',tickets[k].singleCost);
                    }
                }
                $total_price.text(getTotalPrice(sc,tickets)-parseFloat($('#cart-item-'+this.settings.id).attr("title")));//更新票价总金额
                $('#credit_price').html(getCreditPrice($total_price.text()));
                $('#cart-item-' + this.settings.id).remove();//删除已预订座位
                return 'available';
            } else if (this.status() == 'unavailable') { //若为已售出状态
                return 'unavailable';

            } else {
                return this.style();

            }

        }
    });
    var occupied = new Array();
    $.ajax({
        type:"POST",
        url:"/getTicketsByPerformance",
        async:false,
        data:{"performanceID":performanceID},
        success:function (data){
            var ticketList = $.parseJSON(data);
            for(var l=0;l<ticketList.length;l++){
                if(ticketList[l].isOccupied===1){
                    var row = Math.ceil(parseInt(ticketList[l].noOfSeats)/parseInt(column_num));
                    var column = parseInt(ticketList[l].noOfSeats)%parseInt(column_num);
                    var occupy = ""+row+"_"+column;
                    occupied.push(occupy);
                }
            }

        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
/*    alert(occupied);*/
    sc.get(occupied).status('unavailable');
}

function getTotalPrice(sc,tickets) { //计算票价总额
    var total = 0;
    sc.find('selected').each(function() {
        for(var k=0;k<tickets.length;k++){
            if(this.settings.label==tickets[k].noOfSeats){
                total = total + parseFloat(tickets[k].singleCost);
            }
        }

    });
    return total;
}

function getCreditPrice(totalPrice) {
    var level = $('.level').attr("title");
    var credit_cost = 0;
    if(level==0){
        credit_cost = totalPrice*0.9;
    }else if(level==1){
        credit_cost = totalPrice*0.88;
    }else if(level==2){
        credit_cost = totalPrice*0.85;
    }
    return credit_cost.toFixed(2);
}

function back() {
    $('#selected').hide();
    $('#no_selected').show();
}

function buy_selected() {
    var email = $('.user_name').attr("title");
    var total_price = $('#credit_price').text();//获得会员价
    $.ajax({
        type: "POST",
        url: "/getUserInfo",
        data: {"email": email},
        success: function (data) {
            var user = $.parseJSON(data)[0];
            var final_price = 0;
            var discount = -1;
            if (total_price >= 50 && total_price < 100 && user.fiveOff > 0) {
                discount = 0;
                $('.buy_use').show();
                $('.buy_no_use').show();
                $('.buy_confirm').hide();
                $('#discount_cost').show();
                $('#discount_cost').html("您有一张满50减5优惠券可以使用，使用后价格为" + (total_price - 5) + ",您是否使用？");
                final_price = total_price - 5;
            } else if (total_price >= 100 && total_price < 500 && user.tenOff > 0) {
                discount = 1;
                $('.buy_use').show();
                $('.buy_no_use').show();
                $('.buy_confirm').hide();
                $('#discount_cost').show();
                $('#discount_cost').html("您有一张满100减10优惠券可以使用，使用后价格为" + (total_price - 10) + ",您是否使用？");
                final_price = total_price - 10;
            } else if (total_price >= 500 && total_price < 1000 && user.fiftyOff > 0) {
                discount = 2;
                $('.buy_use').show();
                $('.buy_no_use').show();
                $('.buy_confirm').hide();
                $('#discount_cost').show();
                $('#discount_cost').html("您有一张满500减50优惠券可以使用，使用后价格为" + (total_price - 50) + ",您是否使用？");
                final_price = total_price - 50;
            } else if (total_price >= 1000 && user.hundredOff > 0) {
                discount = 3;
                $('.buy_use').show();
                $('.buy_no_use').show();
                $('.buy_confirm').hide();
                $('#discount_cost').show();
                $('#discount_cost').html("您有一张满1000减100优惠券可以使用，使用后价格为" + (total_price - 100) + ",您是否使用？");
                final_price = total_price - 100;
            } else {
                $('.buy_use').hide();
                $('.buy_no_use').hide();
                $('.buy_confirm').show();
                $('#discount_cost').hide();
            }
            $('#original_cost').html("原价：" + $('#total_price').text() + "元");
            $('#VIP_cost').html("会员价：" + total_price + "元");
            $("#myModal").modal("show");
            var performanceID = $('#title').attr("title");
            $('.buy_use').click(function () {
                purchase_selected(email, performanceID,final_price, discount);
            });
            $('.buy_no_use').click(function () {
                purchase_selected(email, performanceID, total_price, -1)
            });
            $('.buy_confirm').click(function () {
                purchase_selected(email, performanceID, total_price, -1);
            })
        }
    });
}

function purchase_selected(email, performanceID, order_price, use_discount) {
    var situation = "";
    var seatNo = "";
    $('#seats_chose li').each(function(){
        var id = this.getAttribute("id");
        var select = $('#'+id).text();
        situation = situation + select+";";
        seatNo = seatNo + select.split(":")[1].replace(/[^0-9]/ig,"")+";";
    });
    $.ajax({
        type:"POST",
        url:"/purchaseSelected",
        data:{"email":email, "performanceID":performanceID, "situation":situation ,"seatNo":seatNo,"order_price":order_price, "use_discount":use_discount},
        success:function (data){
            if(data!=-1){
                if(confirm("生成订单成功，是否立即支付？")===true){
                    $.ajax({
                        type:"POST",
                        url:"/payRightNow",
                        data:{"orderID":data,"email":email,"orderPrice":order_price},
                        success:function (data){
                            if(data==="Success"){
                                alert("支付成功！");
                                location.reload();
                            }else if(data==="NotEnough"){
                                alert("您的余额不足！");
                            }else {
                                alert("支付失败！");
                            }
                        },
                        error:function () {
                            alert("Sth Wrong！");
                        }
                    });
                }else{
                    location.reload();
                }
            }else{
                alert("生成订单失败！");
            }
        },
        error:function () {
            alert("Sth Wrong！")
        }
    });
}