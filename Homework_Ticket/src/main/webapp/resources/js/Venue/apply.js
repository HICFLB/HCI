function apply() {
    if($('#venue').val()==""||$('#location').val()==""||$('#contact').val()==""||$('#volume').val()==""||$('#password').val()=="") {
        alert("信息填写不完整！");
    }else{
        $.ajax({
            type: "post",
            url: "/apply" ,//url
            data: $('#apply_form').serialize(),
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if(result=="VenueExist"){
                    alert("该场馆已申请注册过!");
                } else if(result=="Fail"){
                    alert("申请注册失败！");
                } else {
                    alert("申请注册成功，场馆识别码为："+result+" 您可以用该识别码登录以获知申请结果，申请结果将在24小时内反馈。");
                    location.reload();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // 状态码
                console.log(XMLHttpRequest.status);
                // 状态
                console.log(XMLHttpRequest.readyState);
                // 错误信息
                console.log(textStatus);

                alert("Sth Wrong!");
            }
        });
    }
}
