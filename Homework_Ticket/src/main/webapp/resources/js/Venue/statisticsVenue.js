$(function() {
    var venueID = $('.user_name').attr("title");
    $.ajax({
        type:"POST",
        url:"/getPerformancesByVenue",
        data:{"venueID":venueID},
        success:function (data){
            var list = $.parseJSON(data);
            console.log(list);
            $('#performanceNum').html(list.length);
            if(list.length==0){
                $('#performanceNum').html(0);
                $('.perform_info').empty();
                $('.perform_info').append("<p style='margin-left:50px;'>暂时没有发布过任何演出</p>")
            }else{
                $('.perform_info').empty();
                for(var i=0;i<list.length;i++){
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
                    var time1 = Date.parse(new Date(list[i].beginTime));
                    var time2 = Date.parse(new Date());
                    var time3 = Date.parse(new Date(list[i].endTime));
                    var minus1 = parseInt((time2 - time1)/1000/60);
                    var minus2 = parseInt((time2 - time3)/1000/60);
                    var state="";
                    if(minus1<=0){
                        state="售票中";
                    }else {
                        if (minus2 <= 0) {
                            state = "演出中";
                        }else{
                            if(list[i].isBalanced==1)
                                state = "已结束(已结算)";
                            else
                                state = "已结束(未结算)"
                        }
                    }
                    var numOfBook = 0;
                    var numOfUnsubscribe = 0;
                    var priceOfBook = 0;
                    var priceOfUnsubscribe = 0;
                    $.ajax({
                        type:"POST",
                        url:"/getPerformanceStatistics",
                        async:false,
                        data:{"performanceID":list[i].performanceID},
                        success:function (data){
                            var obj = $.parseJSON(data);
                            numOfBook = obj[0];
                            numOfUnsubscribe = obj[1];
                            priceOfBook = obj[2];
                            priceOfUnsubscribe = obj[3];
                        },
                        error:function () {
                            alert("Sth Wrong！");
                        }
                    });
                    $('.perform_info').append("<div class='list_item'>"+
                    "<div class='performance_info'>"+
                    "<a class='por_title'>"+list[i].performanceName+"</a>"+
                    "<p class='por_price'>总票数："+list[i].totalTickets+"</p>"+
                    "<p class='por_type'>类型："+info+"</p>"+
                    "<p class='por_state'>状态："+state+"</p>"+
                    "<br>"+
                    "<p class='por_time'>预订数目："+numOfBook+"</p>"+
                    "<p class='por_location'>退订数目："+numOfUnsubscribe+"</p>"+
                    "<p class='por_book'>预订收入："+priceOfBook.toFixed(2)+"元</p>"+
                    "<p class='por_unsubscribe'>退订收入："+priceOfUnsubscribe.toFixed(2)+"元</p>"+
                    "</div></div>");
                }
            }

        },
        error:function () {
            alert("Sth Wrong！");
        }
    });

    $.ajax({
       type:"POST",
       url:"/getPerformancesPriceByVenue",
       data:{"venueID":venueID},
       success:function (data) {
           $('#finance').html(data);
       },
       error:function () {
           alert("Sth Wrong!");
       }
    });
});