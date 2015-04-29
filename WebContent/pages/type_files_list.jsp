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
                        <li><a href="#">个人资料</a></li>
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
        <!-- settings changer -->
        <div class="ltt-container-fluid">
		
			<div class="row-fluid ltt-actionbar" >
				  <button type="button" hidden="true" class="btn btn-primary" id="btn_upload"> <span class="icon-upload-alt"></span> 上传 </button>
				  <button type="button" hidden="true" id="upload_menu" data-toggle="modal" href="#fileModal">上传菜单 </button>	
				  <button type="button" hidden="true" id="share_menu" data-toggle="modal" href="#shareModal">分享菜单 </button>
				  <button type="button" class="btn btn-default" onclick="javascript:deleteCheckedFile()"> <span class="icon-trash"></span> 删除 </button>
				  <button type="button" hidden="true" class="btn btn-default" onclick="javascript:addFolder()"> <span class="icon-plus"></span> 新建文件夹 </button>
				  <button type="button" onclick="javascript:doRefresh()" class="btn btn-default"><span class="icon-refresh"></span></button>
				  
				  <div class="btn-group pull-right">
					  <button type="button" class="btn btn-default" onclick="javascript:changeToGallery()"> <span class="icon-sitemap"></span> </button>
					  <button type="button" class="btn btn-default active"> <span class="icon-reorder"></span> </button>
				  </div>
			</div>	
			
			<!--share Modal -->
			<div class="modal hide fade" id="shareModal" >			
			  <div class="modal-dialog">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" id="share_menu_close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">分享文件</h4>
				  </div>
				  <div class="modal-body">
				  <!--modal body -->
					   <div>		
							<div role="navigation" >
								<ul class="nav nav-tabs">
								  <li role="presentation" class="active" id="openShare"><a href="javascript:void(0);" onclick="change('openShare')">公开分享</a></li>
								  <li role="presentation" id="privShare"><a href="javascript:void(0);" onclick="change('privShare')">私密分享</a></li>
								  <li role="presentation" id="groupShare"><a href="javascript:void(0);" onclick="change('groupShare')">群组分享</a></li>
								</ul>
							</div>
							<div class="share_display" id="div_openShare">
								<span>复制链接发送给您的好友吧！</span><br /><br />
								<textarea id="public_share" class="form-control" rows="3" ></textarea><br />
								<button type="button" onclick="javascript:public_share()" class="btn btn-primary btn-sm" >生成链接</button><hr/>
							</div>	
							<div class="share_display" id="div_privShare" style="display:none;">
								<span>复制链接发送给您的好友吧！</span><br /><br />
								<textarea id="private_share" class="form-control" rows="3" ></textarea><br />
								<form class="form-inline">
									<div class="form-group">
										<label for="exampleInputName2">提取码：</label>
										<input type="text" class="form-control" id="share_code" value="" style="width:60px;" />
									</div>
								</form>
								<button type="button" onclick="javascript:private_share()" class="btn btn-primary btn-sm" >生成链接</button><hr/>
							</div>		
							<div class="share_display" id="div_groupShare" style="display:none;">
								<div class="panel panel-default">
								  <div class="panel-heading">
									<h3 class="panel-title">选择群组</h3>
								  </div>
								  <div id="group_list" class="panel-body">
								  </div>
								</div>
								<button onclick="javascript:group_share()" type="button" class="btn btn-primary btn-sm" >确认分享</button>
							</div>
						</div>
				  <!--end modal body -->				  
				</div>
			  </div>
			</div>
			</div>
			<!--end share modal-->	
			
			<div class="row-fluid ltt-pathbar">
				<div id="path_content">
					
				</div>
				<input type="hidden" id="total_page" name="total_page" value="<s:property value="total_page" />" />
				<input type="hidden" id="index" name="index" value="<s:property value="index" />" />
				<input type="hidden" id="old_name" name="old_name" value="" />
				<input type="hidden" id="postfix" name="postfix" value="" />
				<input type="hidden" id="share_id" name="share_id" value="" />
				<form id="download_form" method="post" action="DownloadAction.action">
					<input type="hidden" id="download_id" name="download_id" />
				</form>
				<form id="gallery_form" method="post" action="TypeFileInGalleryIndexAction.action">
					<input type="hidden" id="category" name="category" value="<s:property value="category" />" />
				</form>
			</div>			
			
            <div class="row-fluid ltt-table">
				<div class="container-fluid">
	                <div class="table-wrapper products-table section" style="margin-left:-10px;">
						<div class="row-fluid">
	                        <table class="table table-hover">
	                            <tbody id="showTable">
	                            </tbody>
	                        </table>
	                    </div>
					</div>
				</div>
   			</div>
   		</div>
	</div>
    <!-- end main container -->
    
	<!-- this page scripts -->
    <script src="/HTP/pages/js/jquery-latest.js"></script>
    <script src="/HTP/pages/js/bootstrap.min.js"></script>
    <script src="/HTP/pages/js/theme.js"></script>
	
	<script src="/HTP/pages/project_js/common.js"></script>
	<script src="/HTP/pages/project_js/type_files_list.js"></script>
	
    <script type="text/javascript">
		$(document).ready(function() {
			loadListData();
		});
    </script>
	</body>
</html>