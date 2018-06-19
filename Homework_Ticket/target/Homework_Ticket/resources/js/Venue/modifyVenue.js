
//修改场馆登录密码
function change_password() {
    var venueID = $('.user_name').attr("title");
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
            url:"/modifyVenuePassword",
            data:{"password":new_pwd,"venueID":venueID},
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

function change_info() {
    $('#venue').removeAttr("readOnly");
    $('#location').removeAttr("readOnly");
    $('#contact').removeAttr("readOnly");
    $('#volume').removeAttr("readOnly");
    $('#venue').addClass("has_border");
    $('#location').addClass("has_border");
    $('#contact').addClass("has_border");
    $('#volume').addClass("has_border");
    $('#control_btn').hide();
    $('#confirm_btn').show();
    $('#confirm_btn').click(function (){
        if(confirm("您是否要申请修改场馆信息？")==true){
            var venueName = $('#venue').val();
            var place = $('#location').val();
            var contact = $('#contact').val();
            var volume = $('#volume').val();
            var venueID = $('.user_name').attr("title");
            if(venueName==""||place==""||contact==""||volume==""){
                alert("内容不能为空！");
            }else{
                $.ajax({
                    type:"POST",
                    url:"/modifyVenueInfo",
                    data:{"venueID":venueID,"venueName":venueName,"location":place,"contact":contact,"volume":volume},
                    success:function (data){
                        if(data=="Success") {
                            alert("申请修改成功，正在等待审核结果！");
                            location.reload();
                        } else{
                            alert("申请修改失败！")
                        }
                    },
                    error:function () {
                        alert("Sth Wrong！")
                    }
                });
            }
        }
    });
}

function return_info() {
    var venueID = $('.user_name').attr("title");
    $.ajax({
        type:"POST",
        url:"/resetModifyState",
        data:{"venueID":venueID},
        success:function (data){
            if(data=="Success") {
                location.reload();
            } else{
                alert("返回失败！")
            }
        },
        error:function () {
            alert("Sth Wrong！")
        }
    });
}
