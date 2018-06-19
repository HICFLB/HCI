function manager_login() {
    var admin = $('#admin').val();
    var password = $('#password').val();
    if(admin==""||password==""){
        alert("用户名或密码不能为空");
    }else{
        if(admin=="admin"&&password=="123456")
            window.location.href="/views/Manager/examine.jsp";
    }
}
