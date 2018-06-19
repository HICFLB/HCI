//界面初始化
$(function() {
    $('.list_pane').show();
    $('.check_pane').hide();
    showPerformance();
});

function showPerformance() {
    var venueID = $('.user_name').attr("title");
    $.ajax({
        type:"POST",
        url:"/getPerformListByVenue",
        data:{"venueID":venueID},
        success:function (data){
            var list = $.parseJSON(data);
            if(list.length>0) {
                //$('#perform_info').empty();
                for(var i=0; i<list.length; i++){
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
                    console.log(list[i].beginTime);
                    $('#perform_info').append(
                        "<div class='list_item'>"+
                            "<div class='performance_info'>"+
                                "<a class='por_title'>"+list[i].performanceName+"</a>"+
                                "<p class='por_price'>票价：<span class='rmb'>￥</span><span class='num'>"+list[i].minPrice+" ~"+ list[i].maxPrice+"</span></p>"+
                                "<p class='por_type'>类型："+info+"</p>"+
                                "<br>"+
                                "<p class='por_time'>时间："+ list[i].beginTime.substring(0,19)+" ~"+ list[i].endTime.substring(0,19)+"</p>"+
                            "</div>"+
                            "<div class='purchase_btn'><a title='"+list[i].performanceID+"' onclick='toCheckTicket("+JSON.stringify(list[i])+")'>检票</a></div>"+
                        "</div>");
                }
            }
            else{
                $('#perform_info').empty();
                $('#perform_info').append("<p style='margin-left:200px;'>24小时内暂时没有正在售票的演出或尚未发布演出！</p>");
            }
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
}

function toCheckTicket(perform) {
    $('.list_pane').hide();
    $('.check_pane').show();
    $('#per_name').html(perform.performanceName);
    if(perform.performanceType===0)
        $('#type').html("音乐剧");
    if(perform.performanceType===1)
        $('#type').html("演唱会");
    if(perform.performanceType===2)
        $('#type').html("话剧歌剧");
    if(perform.performanceType===3)
        $('#type').html("舞蹈表演");
    if(perform.performanceType===4)
        $('#type').html("体育比赛");
    $('#time').html("演出时间: "+perform.beginTime.substring(0,19)+" ~ "+perform.endTime.substring(0,19));
    $('#description').html(perform.description);
    $('#totalTickets').html(perform.totalTickets);
    var soldTickets = perform.totalTickets - perform.residueNum;
    $('#soldTickets').html(soldTickets);
    $.ajax({
        type:"POST",
        url:"/getCheckedNum",
        async:false,
        data:{"performanceID":perform.performanceID},
        success:function (data){
            if(data>=0)
                $('#checkedTickets').html(data);
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });

}

function returnList() {
    $('.list_pane').show();
    $('.check_pane').hide();

}

function checkTicket() {
    var ticketID = $('#ticketID').val();
    $.ajax({
        type:"POST",
        url:"/checkTickets",
        data:{"ticketID":ticketID},
        success:function (data){
            if(data==="Success"){
                alert("检票成功！");
                location.reload();
            }else if(data==="NotExits"){
                alert("输入票不存在！");
            }else if(data==="AlreadyChecked"){
                alert("该票已经经过检票！");
            }
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
}