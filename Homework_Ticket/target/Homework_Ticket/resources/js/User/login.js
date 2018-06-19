function login() {
    if($("#password").val()==""||$("#email").val()==""){
        alert("邮箱或密码不能为空！");
    } else{
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            url: "/login" ,//url
            data: $('#login_form').serialize(),
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if(result=="Success"){
                    window.location.href="purchase.jsp";
                } else if(result == "WrongPassword"){
                    alert("密码不正确，请重新输入！");
                }else if(result == "NoRegister"){
                    alert("该邮箱尚未注册！");
                }else if(result == "IsLogOff") {
                    alert("该账户已注销！")
                }
            },
            error : function() {
                alert("Sth Wrong!");
            }
        });
    }
}
