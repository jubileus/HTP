<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head_css.jsp" %>
<!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
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
                        <li><a href="UserInfoAction.action">个人资料</a></li>
                        <li><a href="LogoutAction.action">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!-- end navbar -->
<%@ include file="head_sidebar.jsp" %>

<!-- main container -->
        <div class="content">
	        <div class="settings-wrapper" id="pad-wrapper">
	            <!-- avatar column -->
	            <!-- edit form column -->
	            <!-- changePwd modal-->
                <div class="modal fade" id="changePwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" id="close_modify_password" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                            <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                        </div>
                        <div class="modal-body">
                            <div class="field-box">
                            <label class="span3">原密码：</label> 
                            <input id="old_password" maxlength="50" type="password" /></div>
                            <div class="field-box">
                            <label class="span3">输入新密码：</label> 
                            <input id="password" maxlength="50" type="password" /></div>
                            <div class="field-box">
                            <label class="span3">确认新密码：</label> 
                            <input id="password_confirm" maxlength="50" type="password" /></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" onclick="javascript:modifyPassword()" class="btn btn-primary">确认</button>
                        </div>
                    </div>
                </div>
                <!-- end changePwd modal -->
	            
	            
	            <div class="span7 personal-info">
	                <h5 class="personal-title">个人资料</h5>
	                <div class="span7 avatar-box">
	                    <img src="/HTP/pages/img/default_img.png" width="136" height="136" class="avatar img-circle" />
	                </div>
	                <table>
	                	<tr>
	                		<td>邮箱：</td>
	                		<td><s:property value="ue.email" /></td>
	                	</tr>
	                	<tr>
	                		<td>已用空间：</td>
	                		<td><s:property value="ue.total_storage" /> GB</td>
	                	</tr>
	                	<tr>
	                		<td>可用空间：</td>
	                		<td><s:property value="ue.used_storage" /> GB</td>
	                	</tr>
	                	<tr>
	                		<td>昵称：</td>
	                		<td><input type="text" onclick="javascript:saveOldNickname()" onblur="javascript:modifyNickname()" id="nickname" value="<s:property value="ue.nickname" />" /></td>
	                	</tr>
	                </table>
	                
	                <div class="span6 field-box actions">
	                	<input type="hidden" id="old_nickname" value="<s:property value="ue.nickname" />" /> 
		                <input type="button" class="btn-glow primary" data-toggle="modal" data-target="#changePwd" value="修改密码" /> 
	            	</div>
	        	</div>
	        </div>
        <!-- end main container -->
        <!-- scripts -->
        <script src="/HTP/pages/js/jquery-latest.js"></script> 
        <script src="/HTP/pages/js/bootstrap.min.js"></script> 
        <script src="/HTP/pages/js/theme.js"></script>
        <script src="/HTP/pages/project_js/common.js"></script>
        <script src="/HTP/pages/project_js/userinfo.js"></script>
        </div>
    </body>
</html>