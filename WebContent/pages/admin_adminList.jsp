<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="admin_head.jsp" %>
    <!-- main container -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>管理员列表</h3>
                    <div class="span10 pull-right">
	                    <input type="text" id="search_nickname" name="search_nickname" class="span5 search" placeholder="请输入用户昵称..." /> 
	                    <button type="button" onclick="javascript:searchAdmin()" class="btn btn-primary">查询</button>
	                    <!-- custom popup filter -->
	                     
	                    <!-- styles are located in css/elements.css -->
	                     
	                    <!-- script that enables this dropdown is located in js/theme.js -->
	                    <input type="button" class="btn-flat info pull-right" onclick="javascript:getFileTypeList()" style="margin-left: 30px" data-toggle="modal" value="管理文件类型" />
	                    <input type="button" class="btn-flat warnning pull-right" onclick="javascript:getUserList()" style="margin-left: 30px" data-toggle="modal" value="用户列表" />
	                    <input type="button" class="btn-flat success pull-right" data-toggle="modal" data-target="#newUser" value="+ 新建管理员" />
                   		<form id="file_type_list_form" method="post" action="FileTypeListAction.action"></form>
                   		<form id="user_list_form" method="post" action="UserListAction.action"></form>
                    </div>
                    <!-- newUser modal-->
                    <div class="modal fade" id="newUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                    aria-hidden="true">
                        <div class="modal-content">
                        	<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
								<h4 class="modal-title" id="myModalLabel">新建管理员</h4>
							</div>
                            
							<div class="modal-body">
								<form action="" id="add_admin_form" name="add_admin_form" method="post">
									<div class="field-box">
										<input name="email" onblur="javascript:ifEmailExist()" placeholder="邮箱" id="email" type="text" /><br>
										<label id="email_msg" class="control-label"></label> 
									</div>
									<div class="field-box">
										<input name="nickname" onblur="javascript:validateNickname()" placeholder="昵称" maxlength="50" id="nickname" type="text" /><br>
										<label id="nickname_msg" class="control-label"></label> 
									</div>
									<div class="field-box">
										<input name="password" onblur="javascript:validatePassword()" placeholder="密码" maxlength="50" id="password" type="password" /><br>
										<label id="password_msg" class="control-label"></label> 
									</div>	
									<div class="field-box">
										<input name="password_confirm" onblur="javascript:validateConfirmPassword()" placeholder="确认密码" maxlength="50" id="password_confirm" type="password" /><br>
										<label id="password_confirm_msg" class="control-label"></label> 
									</div>	
									
									<input name="my_id" id="my_id" type="hidden" value="<%=user.getId() %>" />
	                            </form>   
                            </div>
							<div class="modal-footer">
								<button type="button" onclick="javascript:addAdmin()" data-dismiss="modal" class="btn btn-primary">添加</button>
							</div>
						</div>
                        
                    </div>
                    <!-- end newUser modal -->
                </div>
                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th class="span4 sortable"></th>
                                <th class="span4 sortable">昵称</th>
                                <th class="span3 sortable">注册日期</th>
                                <th class="span3 sortable align-right">注册邮箱</th>
                                <th class="span3 sortable align-right">操作</th>
                            </tr>
                        </thead>
                        <tbody id="showTable">
                            <s:iterator value="admin_list" id="admin_list">
                            <tr>
                                <td><img src="/HTP/pages/img/default_img.png" width="40px" height="40px" class="img-circle avatar hidden-phone" /></td>
                                <td><s:property value="nickname" /></td>
                                <td><s:property value="register_time" /></td>
                                <td class="align-right">
                                    <s:property value="email" />
                                </td>
                                <td class="align-right">
                                    <a id="<s:property value="id" />" href="#" onclick="javascript:deleteAdmin(this.id)">删除</a>
                                </td>
                            </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <!-- end main container -->
<%@ include file="admin_bottom.jsp" %>