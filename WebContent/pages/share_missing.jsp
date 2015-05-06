<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	            <h6 style="text-transform: inherit">您所查找的文件被删除</h6>
	            <div class="img-box">
	                <span class="ltt-icon"><i class="gallery-check" id="check3"></i></span>
	                <img src="/HTP/pages/img/icon/icon_file.png"/>
	                <p class="title">文件已丢失</p>
	            </div>
	            <div class="action">
	                <a class="btn-glow primary signup" href="javascript:window.opener=null;window.open('','_self');window.close();">关闭</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- scripts -->
	<script src="/HTP/pages/js/jquery-latest.js"></script>
	<script src="/HTP/pages/js/bootstrap.min.js"></script>
	<script src="/HTP/pages/js/theme.js"></script>
	
	</body>
</html>