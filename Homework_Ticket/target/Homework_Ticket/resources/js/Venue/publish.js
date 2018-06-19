/*这里有一个input刷新不为空的问题*/

function set_tickets(type_num) {
    console.log(type_num.value);
    var num = type_num.value;
    $('.loop_body').empty();
    for(var i=0;i<num;i++) {
        $('.loop_body').append("<div class='item_pane'>" +
            "<p style='float:left;margin-left:20px;'>种类名称：" +
            "<input style='width: 120px;' type='text' name='seat_type_name' id='seat_type_name"+i+"'>" +
            "</p>" +
            "<p style='float: right;margin-right:15px;'>票价：" +
            "<input style='width: 120px;' type='text' name='seat_type_price' id='seat_type_price"+i+"'>" +
            "&nbsp;元</p>" +
            "<p style='float:right;margin-right:160px;'>行:&nbsp;<input class='row' id='begin_row"+i+"'>-<input class='row' id='end_row"+i+"'>" +
            "</p>"+
            "</div>");
    }
}

function publish_performance() {
    var venueID = $('.user_name').attr("title");
    var perform_name = $('#perform_name').val();
    var begin_time = $('#begin_time').val();
    var end_time = $('#end_time').val();
    var perform_type = $('#choice_type input[name="radio"]:checked').val();
    var row_num = $('#row_num').val();
    var column_num = $('#column_num').val();
    var total_num = $('#total_num').val();
    var description = $('#description').val();
    var seat_type = new Array();
    var begin_row = new Array();
    var end_row = new Array();
    var seat_price = new Array();
    for(var i=0;i<$('#loop_num').val();i++){
        seat_type[i] = $("#seat_type_name"+i).val();
        begin_row[i] = $('#begin_row'+i).val();
        end_row[i] = $('#end_row'+i).val();
        seat_price[i] = $('#seat_type_price'+i).val();
    }
    if(perform_name===""||begin_time===""||end_time===""||row_num===""||column_num===""||total_num===""||description===""||seat_type.length===0||
    begin_row.length===0||end_row.length===0||seat_price.length===0){
        alert("信息填写不完整！");
    }else {

        console.log(seat_type);
        $.ajax({
            type:"POST",
            url:"/publishPerformance",
            data:{"venueID":venueID, "perform_name":perform_name, "begin_time":begin_time, "end_time":end_time, "perform_type":perform_type,
            "row_num":row_num, "column_num":column_num, "total_num":total_num, "description":description, "seat_type":seat_type,
            "begin_row":begin_row, "end_row":end_row, "seat_price":seat_price},
            success:function (data){
                if(data=="Success") {
                    alert("发布演出成功！！")
                    location.reload();
                } else{
                    alert("发布失败！")
                }
            },
            error:function () {
                alert("Sth Wrong！")
            }
        });
    }
}