function venue_login() {
    var venueID = $('#venueID').val();
    var password = $('#password').val();
    if(venueID==""||password==""){
        alert("识别码或密码不能为空！");
    }else{
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            url: "/venueLogin" ,//url
            data: $('#venue_form').serialize(),
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if(result=="Success"){
                    window.location.href="publish.jsp";
                } else if(result == "WrongPassword"){
                    alert("密码不正确，请重新输入！");
                }else if(result == "NoApply"){
                    alert("该邮箱尚未注册！");
                }else if(result == "NotPassed") {
                    alert("您申请的场馆没有通过！")
                }else if(result == "NotChecked") {
                    alert("您的申请尚未审核，请稍后尝试登录。");
                }
            },
            error : function() {
                alert("Sth Wrong!");
            }
        });
    }

}
