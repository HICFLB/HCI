$(function() {
    $.ajax({
        type:"POST",
        url:"/getFinishedUnbalancedPerform",
        success:function (data){
            var list = $.parseJSON(data);
            console.log(list);
            if(list.length>0){
                $('.loop_pane').empty();
                for(var i=0;i<list.length;i++){
                var price = 0;
                var balance_price = 0;
                $.ajax({
                    type:"POST",
                    url:"/getPerformanceFinance",
                    async:false,
                    data:{"performanceID":list[i].performanceID},
                    success:function (data){
                        price = data;
                        balance_price = parseFloat(data)*0.95;
                    },
                    error:function () {
                        alert("Sth Wrong！");
                    }
                });
                $('.loop_pane').append("<div class='list_item'>"+
                    "<div class='performance_info'>"+
                    "<a class='por_title'>演出名称:"+list[i].performanceName+"</a>"+
                    "<p class='por_price'>会员支付金额："+price+"</p>"+
                    "<p class='por_venue'>场馆："+list[i].venueName+"</p>"+
                    "<p class='por_balance'>结算后金额："+balance_price.toFixed(2)+"</p>"+
                    "</div>"+
                    "<div class='balance_btn'><a href='#'  onclick='balance_perform("+price+","+list[i].venueID+","+list[i].performanceID+")'>结算</a></div>"+
                    "</div>");
            }
            }else{
                $('.loop_pane').append("<p style='margin-left:50px;'>暂时没有需要结算的演出！</p>");
            }
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
});

function balance_perform(totalPrice,venueID,performanceID) {
    $.ajax({
        type:"POST",
        url:"/balanceToVenue",
        async:false,
        data:{"venueID":venueID,"totalPrice":totalPrice,"performanceID":performanceID},
        success:function (data){
            alert("结算成功");
            location.reload();
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
}
