<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>HTP网盘-登录</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="/HTP/pages/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="/HTP/pages/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/HTP/pages/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="/HTP/pages/css/templatemo_style.css" rel="stylesheet" type="text/css">	
</head>
<body class="templatemo-bg-gray">
	<div class="container">
		<div class="col-xs-12">
			<h1 class="margin-bottom-15">欢迎来到HTP云盘！</h1>
			<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" id="login_form" role="form" action="LoginAction.action" method="post">
		        <div class="form-group">
		          <div class="col-xs-12">		            
		            <div class="control-wrapper">
		            	<label for="fail" id="fail_msg" class="control-label"><s:property value="fail_msg" /></label> 
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-xs-12">		            
		            <div class="control-wrapper">
		            	<input type="text" class="form-control" onblur="javascript:validateEmailForLogin()" name="email" id="email" placeholder="邮箱"><br>
		            	<label for="email" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
		            	<label for="email" id="email_msg" class="control-label"></label> 
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-xs-12">
		          	<div class="control-wrapper">
		            	<input type="password" class="form-control" onblur="javascript:validatePassword()" name="password" id="password" placeholder="密码"><br>
		            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
		            	<label for="password" id="password_msg" class="control-label"></label>
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-xs-12">
		          	<div class="control-wrapper">
						<input type="button" onclick="javascript:validateLogin()" value="登录" class="btn btn-info">
						<a href="/HTP/pages/register.jsp" class="pull-right" style="color: #428bca">注册</a>
					  </div>
                  </div>
                </div>
              </form>
              <div class="text-center">

              </div>
        </div>
    </div>
<script type="text/javascript" src="/HTP/pages/project_js/login_register.js"></script>
<script type="text/javascript" src="/HTP/pages/project_js/common.js"></script>
<script type="text/javascript" src="/HTP/pages/js/jquery-1.4.4.min.js"></script>
</body>
</html>