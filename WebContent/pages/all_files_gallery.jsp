<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

	<!-- main container -->
    <div class="content">
        <!-- settings changer -->
        <div class="ltt-container-fluid">
		
			<div class="row-fluid ltt-actionbar" >
				  <input name="file" type="file" style="opacity:0;width:70px;height:30px;position:absolute;z-index:10;"/>
				  <button type="button" class="btn btn-primary" id="btn_upload"> <span class="icon-upload-alt"></span> 上传 </button>	
				  <button type="button" class="btn btn-default" onclick="javascript:deleteCheckedFile()"> <span class="icon-trash"></span> 删除 </button>
				  <button type="button" class="btn btn-default" onclick="javascript:addFolder()"> <span class="icon-plus"></span> 新建文件夹 </button>
				  <button type="button" onclick="javascript:doRefresh()" class="btn btn-default"><span class="icon-refresh"></span></button>
				  
				  <div class="btn-group pull-right">
					  <button type="button" class="btn btn-default active"> <span class="icon-sitemap"></span> </button>
					  <button type="button" class="btn btn-default" onclick="window.location.href='htp_allfilesl.html';"> <span class="icon-reorder"></span> </button>
				  </div>
			</div>	
						
			<!--share Modal -->
			<div class="modal hide fade" id="shareModal" >			
			  <div class="modal-dialog">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">分享文件 Thinking in Java</h4>
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
								<textarea class="form-control" rows="3" >http://share.weiyun.com/fcd49f7158c5778310f986f157494193</textarea><br />
								<button type="button" class="btn btn-primary btn-sm" >复制链接</button><hr/>
							</div>	

							
							<div class="share_display" id="div_privShare" style="display:none;">
								<span>复制链接发送给您的好友吧！</span><br /><br />
								<textarea class="form-control" rows="3" >http://share.weiyun.com/fcd49f7158c5778310f986f157494193</textarea><br />
								<form class="form-inline">
									<div class="form-group">
										<label for="exampleInputName2">提取码：</label>
										<input type="text" class="form-control" id="exampleInputName2" value="QW88Q" style="width:60px;" />
									</div>
								</form>
								<button type="button" class="btn btn-primary btn-sm" >复制链接</button><hr/>
							</div>		
							
							<div class="share_display" id="div_groupShare" style="display:none;">
								<div class="panel panel-default">
								  <div class="panel-heading">
									<h3 class="panel-title">选择群组</h3>
								  </div>
								  <div class="panel-body">
									<div class="checkbox">
										<label>
											<input type="checkbox"> 实训_云计算
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox"> 中特xxx
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox"> 小组小组
										</label>
									</div>
								  </div>
								</div>
								<button type="button" class="btn btn-primary btn-sm" >确认分享</button>
							</div>
				
						</div>
				  <!--end modal body -->				  
				</div>
			  </div>
			</div>
			</div>
			<!--end share modal-->		

			<div class="contextMenu" id="file_menu">
		      <ul>
		        <li id="download">下载/Download</li>
		        <li id="share">分享/Share</li>
		        <li id="rename">重命名/Rename</li>
		        <li id="delete">删除/Delete</li>
		      </ul>
    		</div>
    		<div class="contextMenu" id="folder_menu">
		      <ul>
		        <li id="delete">删除/Delete</li>
		        <li id="rename">重命名/Rename</li>
		      </ul>
    		</div>
			
			<div class="row-fluid ltt-pathbar">
				<div id="path_content">
					
				</div>
				<input type="hidden" id="total_page" name="total_page" value="<s:property value="total_page" />" />
				<input type="hidden" id="index" name="index" value="<s:property value="index" />" />
				<input type="hidden" id="path" name="path" value="<s:property value="path" />" />
				<input type="hidden" id="old_name" name="old_name" value="" />
				<input type="hidden" id="postfix" name="postfix" value="" />
			</div>			
			
            <div id="pad-wrapper" class="gallery" >
                <!-- gallery wrapper -->
                <div class="gallery-wrapper" >
                    <div id="showTable" class="gallery-row">
                        
            		</div>
        		</div>
			</div>
   		</div>
	</div>
    <!-- end main container -->
    
	<!--file Modal -->
	<div class="modal hide fade" id="fileModal" style="width:900px;height:600px;" >			
	  <div class="modal-dialog" >
		<div class="modal-content" id="show-file" style="height:600px;"  >
	  </div>
	</div>
	</div>
	<!--end file modal-->	
	
	<!-- this page scripts -->
    <script src="/HTP/pages/js/jquery-latest.js"></script>
    <script src="/HTP/pages/js/bootstrap.min.js"></script>
    <script src="/HTP/pages/js/theme.js"></script>
	<script type="text/javascript" src="/HTP/pages/right_menu/jquery.contextmenu.r2.js"></script>
	
	<script src="/HTP/pages/project_js/common.js"></script>
	<script src="/HTP/pages/project_js/all_files_gallery.js"></script>
	
    <script type="text/javascript">
		$(document).ready(function() {
			loadGalleryData();
		});
		
    </script>
	</body>
</html>