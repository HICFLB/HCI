//界面初始化
$(function() {
    initList(0);
});

function initList(type) {
    $.ajax({
        type:"POST",
        url:"/getApplyListByType",
        data:{"type":type},
        success:function (data){
            var list = $.parseJSON(data);
            if(list.length>0) {
                setList(list,type);
            }
            else{
                $('#venue_info').empty();
                if(type==0)
                    $("#venue_info").append("<tr><td>还没有场馆申请注册！</td></tr>");
                if(type==1)
                    $("#venue_info").append("<tr><td>暂时没有场馆申请注册！</td></tr>");
                if(type==2)
                    $("#venue_info").append("<tr><td>暂时没有审核通过的场馆记录！</td></tr>");
                if(type==3)
                    $("#venue_info").append("<tr><td>暂时没有审核失败的场馆记录！</td></tr>");
            }
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
}

function setList(list,type) {
    if(list.length>0) {
        $('#venue_info').empty();
        for(var i=0; i<list.length;i++) {
            if(type==1||(type==0&&list[i].isChecked==0)){
                $('#venue_info').append("<tr>"+
                    "<td name='venueID'>"+list[i].idCode+"</td>"+
                    "<td>"+list[i].venueName+"</td>"+
                    "<td>"+list[i].applyTime+"</td>"+
                    "<td>"+list[i].location+"</td>"+
                    "<td>"+list[i].contact+"</td>"+
                    "<td>"+list[i].totalSeat+"</td>"+
                    "<td> <div class='btn_group'> " +
                    "<button title='通过该申请' id='pass'onclick='checkApply(this,1)' class='btn_apply'> " +
                    "<i class='fa fa-check' aria-hidden='true'></i>"+
                    "</button>"+
                    "<button title='拒绝该申请' id='refuse'onclick='checkApply(this,0)' class='btn_apply' style='margin-left: 10px'>"+
                    "<i class='fa fa-times' aria-hidden='true'></i>"+
                    "</button>"+
                    "</div>"+
                    "</td>" +
                    "</tr>");
            }else {
                if(type==2||(type==0&&list[i].isPassed==1)){
                    $('#venue_info').append("<tr>"+
                        "<td name='venueID'>"+list[i].idCode+"</td>"+
                        "<td>"+list[i].venueName+"</td>"+
                        "<td>"+list[i].applyTime+"</td>"+
                        "<td>"+list[i].location+"</td>"+
                        "<td>"+list[i].contact+"</td>"+
                        "<td>"+list[i].totalSeat+"</td>"+
                        "<td>审核通过</td></tr>");
                }else if(type==3||(type==0&&list[i].isPassed==0&&list[i].isChecked==1)){
                    $('#venue_info').append("<tr>"+
                        "<td name='venueID'>"+list[i].idCode+"</td>"+
                        "<td>"+list[i].venueName+"</td>"+
                        "<td>"+list[i].applyTime+"</td>"+
                        "<td>"+list[i].location+"</td>"+
                        "<td>"+list[i].contact+"</td>"+
                        "<td>"+list[i].totalSeat+"</td>"+
                        "<td>拒绝申请</td></tr>");
                }
            }
        }
    }
    else{
        $('#venue_info').empty();
        $("#venue_info").append("<tr><td>还没有场馆申请注册！</td></tr>");
    }
}

function choiceType(type) {
    if(type.text=="全部") {
        $('#all').addClass("active");
        $('#no_check').removeClass("active");
        $('#check_pass').removeClass("active");
        $('#check_refuse').removeClass("active");
        initList(0);
    }else if(type.text=="尚未审核"){
        $('#all').removeClass("active");
        $('#no_check').addClass("active");
        $('#check_pass').removeClass("active");
        $('#check_refuse').removeClass("active");
        initList(1);
    }else if(type.text=="审核通过"){
        $('#all').removeClass("active");
        $('#no_check').removeClass("active");
        $('#check_pass').addClass("active");
        $('#check_refuse').removeClass("active");
        initList(2);
    }else if(type.text=="拒绝申请"){
        $('#all').removeClass("active");
        $('#no_check').removeClass("active");
        $('#check_pass').removeClass("active");
        $('#check_refuse').addClass("active");
        initList(3);
    }

}

function checkApply(item,state) {
    var venueID = $(item).parent().parent().parent().find("td[name='venueID']").html();
    $.ajax({
        type:"POST",
        url:"/checkApply",
        data:{"venueID":venueID,"state":state},
        success:function (data){
            if(data=="Success"&&state==1){
                alert("该申请已通过！");
                location.reload();
            }else if(data=="Success"&&state==0){
                alert("已拒绝该申请！");
                location.reload();
            }else{
                alert("操作失败！");
            }
        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
}


