function register() {
    if($(".username").val()==""||$(".password").val()==""||$(".email").val()==""){
        alert("填写信息不完整！")
    } else{
        $.ajax({
            type: "post",
            url: "/register" ,//url
            data: $('#register_form').serialize(),
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if(result=="UserExist"){
                    alert("该邮箱已经注册过!");
                } else if(result=="Success"){
                    $('#register_form').hide();
                    $('.post_email').show();
                    alert("注册成功！");
                } else if(result=="Fail") {
                    alert("注册失败！");
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