<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html class="login-bg">
	<head>
	    <title>HTP网盘</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	    <!-- bootstrap -->
	    <link href="/HTP/pages/css/bootstrap/bootstrap.css" rel="stylesheet"/>
	    <!-- global styles -->
	    <link rel="stylesheet" type="text/css" href="/HTP/pages/css/layout.css"/>
	    <link rel="stylesheet" type="text/css" href="/HTP/pages/css/elements.css"/>
	    <!-- libraries -->
	    <link rel="stylesheet" type="text/css" href="/HTP/pages/css/lib/font-awesome.css"/>
	    <!-- this page specific styles -->
	    <link rel="stylesheet" href="/HTP/pages/css/compiled/signup.css" type="text/css" media="screen"/>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	</head>
	<body>
	<div class="header">
	    <a href="#">
	        <img src="/HTP/pages/img/logo.png" class="logo"/>
	    </a>
	</div>
	<div class="row-fluid login-wrapper">
        <div class="box">
            <div class="content-wrap">
                <h6><s:property value="user_name" /> 给您分享了加密文件</h6>
                <input class="span12" maxlength="4" id="code" name="code" type="text" placeholder="请输入提取码" />
                <div class="action">
                    <a class="btn-glow primary signup" onclick="javascript:verify_code()" href="#">提取</a>
                </div>
            </div>
        </div>
    </div>
    <form method="post" id="verify_form" action="ShareDownloadAction.action">
    	<input id="input_code" name="input_code" type="hidden" value="" />
    	<input id="share_code" name="share_code" type="hidden" value="<s:property value="share_code" />" />
        <input name="show_name" type="hidden" value="<s:property value="show_name" />" />
        <input name="img" type="hidden" value="<s:property value="img" />" />
        <input name="file_id" type="hidden" value="<s:property value="file_id" />" />
    </form>
	
	<!-- scripts -->
	<script src="/HTP/pages/js/jquery-latest.js"></script>
	<script src="/HTP/pages/js/bootstrap.min.js"></script>
	<script src="/HTP/pages/js/theme.js"></script>
	<script src="/HTP/pages/project_js/common.js"></script>
	<script type="text/javascript">
		function verify_code(){
			var code=$("#code").val();
			var share_code=$("#share_code").val();
			if(trim(code).length>0){
				if(share_code==code){
					//提取码正确
					$("#input_code").val(code);
					$("#verify_form").submit();
				}else{
					//提取码错误
					alert("提取码错误");
				}
			}else{
				alert("提取码不能为空");
			}
		}
	</script>
	
	</body>
</html>