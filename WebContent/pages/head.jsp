<%@page import="com.bjtu.model.pojo.Tb_user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HTP网盘</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!-- my own css -->
    <link href="/HTP/pages/css/ltt.css" rel="stylesheet" />

	
    <!-- bootstrap -->
	<link href="/HTP/pages/css/bootstrap/bootstrap.css" rel="stylesheet" /> 
    <link href="/HTP/pages/css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="/HTP/pages/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="/HTP/pages/css/layout.css" />
    <link rel="stylesheet" type="text/css" href="/HTP/pages/css/elements.css" />
    <link rel="stylesheet" type="text/css" href="/HTP/pages/css/icons.css" />

    <!-- libraries -->
    <link href="/HTP/pages/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="/HTP/pages/css/compiled/gallery.css" type="text/css" media="screen" />
	    <!-- this page specific styles -->
    <link rel="stylesheet" href="/HTP/pages/css/compiled/tables.css" type="text/css" media="screen" />


    <!-- open sans font -->
    <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />
	
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

    <!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <button type="button" class="btn btn-navbar visible-phone" id="menu-toggler">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="brand" href="index.html"><img src="/HTP/pages/img/logo.png" /></a>
            <!--产品logo图片-->
            <ul class="nav pull-right">
                <li class="hidden-phone">
                    <input class="search" onblur="javascript:searchData()" id="search_name" name="search_name" type="text" />
                    <!--内有默认文字-->
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle hidden-phone" data-toggle="dropdown">
                        <%
                    	Tb_user user=(Tb_user)session.getAttribute("user");
                    	if(user==null){
	               		%>	
	               			<jsp:forward page="/pages/login.jsp" />
	               		<%
	                    	}else{
	                    %>
	                    	<%=user.getNickname() %>
	                    <%
	                    	}
	                    %>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">个人资料</a></li>
                        <li><a href="LogoutAction.action">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!-- end navbar -->
	
      <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            <li class="active">
                <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
                <a href="AllFileInGalleryIndexAction.action">
                    <i class="icon-folder-open"></i>
                    <span>全部文件</span>
                </a>
            </li>            
            <li>
                <a href="chart-showcase.html">
                    <i class="icon-folder-close"></i>
                    <span>文档</span>
                </a>
            </li>
            <li>
                <a href="chart-showcase.html">
                    <i class="icon-picture"></i>
                    <span>图片</span>
                </a>
            </li>
            <li>
                <a href="chart-showcase.html">
                    <i class="icon-film"></i>
                    <span>视频</span>
                </a>
            </li>
            <li>
                <a href="chart-showcase.html">
                    <i class="icon-music"></i>
                    <span>音频</span>
                </a>
            </li>
            <li>
                <a href="htp_myShare.html">
                    <i class="icon-external-link"></i>
                    <span>我的分享</span>
                </a>
            </li>
            <li>
                <a href="htp_group.html">
                    <i class="icon-group"></i>
                    <span>我的群组</span>
                </a>
            </li>
        </ul>
    </div>
    <!-- end sidebar -->