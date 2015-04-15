<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="admin_head.jsp" %>
    <!-- main container -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>用户列表</h3>
                    <div class="span10 pull-right">
	                    <input type="text" id="search_nickname" class="span5 search" placeholder="请输入昵称..." /> 
	                    <button type="button" onclick="javascript:searchNicknameInUserManagement()" class="btn btn-primary">查询</button>
	                    <!-- custom popup filter -->
	                     
	                    <input type="button" class="btn-flat warning pull-right" onclick="javascript:getAdminList()" style="margin-left: 30px" data-toggle="modal" value="管理员列表" />
	                    <input type="button" class="btn-flat info pull-right" onclick="javascript:getFileTypeList()" data-toggle="modal" value="管理文件类型" />
	                    <form id="admin_list_form" method="post" action="AdminListAction.action"></form>
	                    <form id="file_type_list_form" method="post" action="FileTypeListAction.action">
	                    	<input type="hidden" id="total_page" name="total_page" value="<s:property value="total_page" />">
	                    	<input type="hidden" id="tmp_storage" name="tmp_storage" value="">
	                    </form>
                    </div>
                </div>
                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                           		<th class="span4 sortable"></th>
                                <th class="span4 sortable">昵称</th>
                                <th class="span3 sortable">注册日期</th>
                                <th class="span2 sortable">云盘空间</th>
                                <th class="span2 sortable">已用空间</th>
                                <th class="span3 sortable align-right">注册邮箱</th>
                                <th class="span3 sortable align-right">操作</th>
                            </tr>
                        </thead>
                        <tbody id="showTable">
                            <!-- row -->
                            <s:iterator value="user_list" id="user_list">
	                            <tr>
	                                <td><img src="/HTP/pages/img/default_img.png" style="width: 40px;height: 40px;" class="img-circle avatar hidden-phone" /></td>
	                                <td><s:property value="nickname" /></td>
	                                <td><s:property value="register_time" /></td>
	                                <td><input class="span5 inline-input" type="text" id="<s:property value="id" />" maxlength="6" onfocus="javascript:saveTempTotalStorage(this.value)" onblur="javascript:validateTotalStorage(this.value,this.id)" value="<s:property value="total_storage" />" /></td>
	                                <td><s:property value="used_storage" /></td>
	                                <td class="align-right"><s:property value="email" /></td>
	                                <td class="align-right"><a id="<s:property value="id" />" onclick="javascript:modifyUserStatus(this.id)" href="#"><s:property value="status_name" /></a></td>
	                            </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div class="pagination pull-right">
                    <ul>
                        <li>
                            <a href="javascript:getFirstPageForUser()">first</a>
                        </li>
                        <li>
                            <a href="javascript:getPreviousPageForUser()">←</a>
                        </li>
                        <li>
                            <a id="page_index" href="#">1</a>
                        </li>
                        <li>
                            <a href="javascript:getNextPageForUser()">→</a>
                        </li>
                        <li>
                            <a href="javascript:getLastPageForUser()">last</a>
                        </li>
                    </ul>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <!-- end main container -->
<%@ include file="admin_bottom.jsp" %>