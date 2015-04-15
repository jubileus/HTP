<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="admin_head.jsp" %>
    <!-- main container -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>文件类型列表</h3>
                    <div class="span10 pull-right">
                   		<input type="text" id="search_postfix" class="span5 search" placeholder="请输入后缀..." /> 
	                    <button type="button" onclick="javascript:searchFileType()" class="btn btn-primary">查询</button>
	                    <!-- custom popup filter -->
	                     
	                    <input type="button" class="btn-flat warning pull-right" onclick="javascript:getAdminList()" style="margin-left: 30px" data-toggle="modal" value="管理员列表" />
	                    <input type="button" class="btn-flat info pull-right" onclick="javascript:getUserList()" style="margin-left: 30px" data-toggle="modal" value="用户列表" />
	                    <input type="button" class="btn-flat success pull-right" data-toggle="modal" data-target="#newUser" value="+ 新建文件类型" />
                    	<form id="admin_list_form" method="post" action="AdminListAction.action">
                    		<input type="hidden" id="total_page" name="total_page" value="<s:property value="total_page" />">
                    	</form>
                    	<form id="user_list_form" method="post" action="UserListAction.action"></form>
                    </div>
                    <!-- newUser modal-->
                    <div class="modal fade" id="newUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                    aria-hidden="true">
                        <div class="modal-content">
                        	<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
								<h4 class="modal-title" id="myModalLabel">新建文件类型</h4>
							</div>
                            
							<div class="modal-body">
                                <div class="field-box">
									<input type="text" onblur="javascript:ifPostfixExist()" maxlength="10" placeholder="后缀" id="postfix" name="postfix" />
									<label id="postfix_msg" class="control-label"></label> 
								</div>
								<div class="field-box">
									<select id="img_type" name="img_type" onchange="javascript:showIcon(this.value)" style="height: 30px">
										<option value="icon_file.png">文件通用图标</option>
										<option value="icon_folder.png">文件夹图标</option>
										<option value="icon_image.png">图片图标</option>
										<option value="icon_music.png">音乐图标</option>
										<option value="icon_pdf.png">PDF图标</option>
										<option value="icon_ppt.png">PPT图标</option>
										<option value="icon_txt.png">记事本文件</option>
										<option value="icon_video.png">视频图标</option>
										<option value="icon_word.png">WORD图标</option>
										<option value="icon_xls.png">EXCEL图标</option>
										<option value="icon_zip.png">压缩包图标</option>
									</select>
								</div>
								<div id="icon_preview" class="field-box">
									<br>
									<span class="span3">图标预览：</span>
									<img src="/HTP/pages/img/icon/icon_file.png" style="width: 40px;height: 40px;" /> 
								</div>
                            </div>
							<div class="modal-footer">
								<button type="button" onclick="javascript:addFileType()" data-dismiss="modal" class="btn btn-primary">添加</button>
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
                                <th class="span4 sortable">文件图标</th>
                                <th class="span2 sortable">后缀</th>
                                <th class="span3 sortable">类别</th>
                                <th class="span3 sortable">操作</th>
                            </tr>
                        </thead>
                        <tbody id="showTable">
                            <!-- row -->
                            <s:iterator value="file_type_list" id="file_type_list">
	                            <tr>
	                                <td>
		                                <img src="<s:property value="img" />" style="width: 40px;height: 40px;" /> 
	                                </td>
	                                <td class="align-center"><s:property value="postfix" /></td>
	                                <td class="align-center"><s:property value="category_name" /></td>
	                                <td class="align-center">
	                                    <a id="<s:property value="id" />" href="#" onclick="javascript:deleteFileType(this.id)">删除</a>
	                                </td>
	                            </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div class="pagination pull-right">
                    <ul>
                        <li>
                            <a href="javascript:getFirstPageForFileType()">first</a>
                        </li>
                        <li>
                            <a href="javascript:getPreviousPageForFileType()">←</a>
                        </li>
                        <li>
                            <a id="page_index" href="#">1</a>
                        </li>
                        <li>
                            <a href="javascript:getNextPageForFileType()">→</a>
                        </li>
                        <li>
                            <a href="javascript:getLastPageForFileType()">last</a>
                        </li>
                    </ul>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <!-- end main container -->
<%@ include file="admin_bottom.jsp" %>