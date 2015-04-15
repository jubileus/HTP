<%@page import="com.bjtu.model.pojo.Tb_user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <meta name="generator" content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
        <title>HTP网盘-管理员-用户列表</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
        <link rel="stylesheet" href="/HTP/pages/css/compiled/user-list.css" type="text/css" media="screen" />
        <!-- open sans font -->
        <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
        rel='stylesheet' type='text/css' />
        <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    <!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <a class="brand" href="#">
                <img src="/HTP/pages/img/logo.png" />
            </a>
            <ul class="nav pull-right">
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
						<li>
                            <a href="LogoutAction.action">退出</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!-- end navbar -->