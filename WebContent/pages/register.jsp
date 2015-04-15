<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>HTP网盘-注册</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="/HTP/pages/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/HTP/pages/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
    <link href="/HTP/pages/css/templatemo_style.css" rel="stylesheet" type="text/css">
</head>
<body class="templatemo-bg-gray">
<h1 class="margin-bottom-15">欢迎加入HTP云盘！</h1>

<div class="container">
    <div class="col-xs-3"></div>
    <div class="col-xs-6">
        <form class="form-horizontal templatemo-create-account templatemo-container" role="form"
              action="RegisterAction.action" id="register_form" method="post">
            <div class="form-inner">
                <div class="form-group">
                    <div class="col-xs-12">                                   
                        <input type="text" class="form-control" onblur="javascript:ifEmailExist()" name="email" id="email" placeholder="邮箱"><br>
                        <label for="email" id="email_msg" class="control-label"></label> 
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">                       
                        <input type="text" class="form-control" onblur="javascript:validateNickname()" maxlength="50" name="nickname" id="nickname" placeholder="昵称"><br>
                        <label for="nickname" id="nickname_msg" class="control-label"></label>
                    </div>

                </div>
                <div class="form-group">
                    <div class="col-xs-12">                        
                        <input type="password" class="form-control" onblur="javascript:validatePassword()" maxlength="50" name="password" id="password" placeholder="密码"><br>
                        <label for="password" id="password_msg" class="control-label"></label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">                        
                        <input type="password" class="form-control" onblur="javascript:validateConfirmPassword()" maxlength="50" name="password_confirm" id="password_confirm" placeholder="确认密码"><br>
						<label for="password_confirm" id="password_confirm_msg" class="control-label"></label>                    
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-12">
                        <input type="button" onclick="javascript:validateRegister()" value="注册" class="btn btn-info">
                        <a href="/HTP/pages/login.jsp" class="pull-right">已有账户？直接登录</a>
                    </div>
                </div>
            </div>
    </form>
</div>
<div class="col-xs-3"></div>
</div>
<script type="text/javascript" src="/HTP/pages/project_js/login_register.js"></script>
<script type="text/javascript" src="/HTP/pages/project_js/common.js"></script>
<script type="text/javascript" src="/HTP/pages/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/HTP/pages/js/bootstrap.min.js"></script>
</body>
</html>