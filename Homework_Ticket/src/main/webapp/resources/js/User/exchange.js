function exchange_credit(credit) {
    var email = $('.user_name').attr("title");

    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        url: "/creditExchange" ,//url
        data:{"email":email,"credit":credit},
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if(result=="Success"){
                alert("兑换成功！！");
                location.reload();
            } else{
                alert("积分不足！！");
            }
        },
        error : function() {
            alert("Sth Wrong!");
        }
    });
}
