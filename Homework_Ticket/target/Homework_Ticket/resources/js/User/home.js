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
                "<img src='./resources/img/war.png'>"+
                "</div><div class='info'>"+
                "<a class='por_title'>"+list[i].performanceName+"</a>"+
                "<p class='por_price'>票价<span class='rmb'>￥</span><span class='num'>"+list[i].minPrice+" ~"+ list[i].maxPrice+"</span></p>"+
                "<p class='por_type'>类型："+info+"</p>"+
                "<p class='por_time'>时间："+list[i].beginTime+" - "+ list[i].endTime+"</p>"+
                "<p class='por_location'>场馆："+list[i].venueName+"</p>"+
                "<p class='por_loc'>地点："+ list[i].location+"</p>"+
                "</div></div> "+
                "<div class='purchase_btn'><a href='#' title='"+list[i].performanceID+"' onclick='please_login()'>立即购票</a></div>"+
                "</div>");
        }
    }
}

function please_login() {
    alert("请您先登录！");
}