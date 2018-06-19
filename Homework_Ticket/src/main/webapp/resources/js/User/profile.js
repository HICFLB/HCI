//修改用户名
function change_name() {
    $('#new_name').addClass("has_border");
    $('#new_name').removeAttr("readOnly");
    $('#modify_name').hide();
    $('#confirm_name').show();
    $('#confirm_name').click(function () {
        alert("你执行了几次？");
        var userName = $('#new_name').val();
        var email = $('#mail').val();
        if(userName==""){
            alert("用户名不能为空！");
        }else {
            $.ajax({
                type:"POST",
                url:"/modifyUserName",
                data:{"username":userName,"email":email},
                success:function (data){
                    if(data=="Success") {
                        alert("修改成功！");
                        location.reload();
                    } else{
                        alert("修改失败！")
                    }
                },
                error:function () {
                    alert("Sth Wrong！")
                }
            });
        }
    });
}

//注销用户
function logOff() {
    var msg = "您真的确定要注销账户吗？\n\n请确认！";
    if (confirm(msg)==true){
        var email = $('#mail').val();
        $.ajax({
            type:"POST",
            url:"/cancelUser",
            data:{"email":email},
            success:function (data){
                if(data=="Success") {
                    alert("账户已注销！");
                    window.location.href="/index.jsp";
                } else{
                    alert("注销失败！");
                }
            },
            error:function () {
                alert("Sth Wrong！")
            }
        });
    }else{
        return false;
    }

}

//修改密码
function change_password() {
    var email = $('#mail').val();
    var old_pwd = $('#old_pwd').val();
    var new_pwd = $('#new_pwd').val();
    var confirm_pwd = $('#confirm_pwd').val();
    if(old_pwd==""||new_pwd==""||confirm_pwd=="") {
        alert("输入不能为空！");
    }else if(new_pwd!=confirm_pwd) {
        alert("两次密码不一致，请重新输入！");
    }else{
        $.ajax({
            type:"POST",
            url:"/modifyPassword",
            data:{"password":new_pwd,"email":email},
            success:function (data){
                if(data=="Success") {
                    alert("修改成功！");
                    location.reload();
                } else{
                    alert("修改失败！")
                }
            },
            error:function () {
                alert("Sth Wrong！")
            }
        });
    }
}