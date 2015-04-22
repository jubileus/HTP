<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

	<!-- main container -->
    <div class="content">
        <!-- settings changer -->
        <div class="ltt-container-fluid">
		
			<div class="row-fluid ltt-actionbar" >
				  <input type="file" id="file" name="file" onchange="javascript:file_upload()" style="opacity:0;width:70px;height:30px;position:absolute;z-index:10;"/>
				  <button type="button" class="btn btn-primary" id="btn_upload"> <span class="icon-upload-alt"></span> 上传 </button>
				  <button type="button" hidden="true" id="upload_menu" data-toggle="modal" href="#fileModal">上传菜单 </button>	
				  <button type="button" class="btn btn-default" onclick="javascript:deleteCheckedFile()"> <span class="icon-trash"></span> 删除 </button>
				  <button type="button" class="btn btn-default" onclick="javascript:addFolder()"> <span class="icon-plus"></span> 新建文件夹 </button>
				  <button type="button" onclick="javascript:doRefresh()" class="btn btn-default"><span class="icon-refresh"></span></button>
				  
				  <div class="btn-group pull-right">
					  <button type="button" class="btn btn-default active"> <span class="icon-sitemap"></span> </button>
					  <button type="button" class="btn btn-default" onclick="window.location.href='htp_allfilesl.html';"> <span class="icon-reorder"></span> </button>
				  </div>
			</div>	
			
			<!--share Modal -->
			<div class="modal hide fade" id="fileModal" >			
			  <div class="modal-dialog">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">文件上传中，请稍等片刻</h4>
				  </div>
				  <div id="progress_msg" class="field-box">
						<h4 style="margin: 30px;">文件正在上传中，已完成0%</h4>
				  </div>
				  <!--end modal body -->
				  <div class="modal-footer">
					 <button type="button" id="close_upload_menu" data-dismiss="modal" class="btn btn-primary">关闭</button>
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

		//文件分片上传方法
		function file_upload(){
			var path=$('#path').val();
			var file=$("#file")[0].files[0]; //文件对象
			var file_name=file.name;//文件原名称
			var size = file.size;//文件大小，单位为Byte
			var size_mb=size/1048576;//文件大小，单位为MB
			var shardSize = 10485760   //以10MB为一个分片
		    var shardCount = Math.ceil(size / shardSize);  //总片数
		    //将文件大小和名称发送至服务器，如果可使用空间充足则返回文件的在HDFS上的名称
			var url = "IfFileCanUploadAction.action?file_name="+file_name+"&&size_mb="+size_mb+"&&path="+path; 
			var msg;//文件上传预操作结果
			var file_id;//文件id
			$.ajax({ 
				type:'get', 
				url:url, 
				async: false,   //同步
				dataType: 'json', 
				success:function(data){ 
					msg=data.msg;
					file_id=data.file_id;
				} 
			});
		    if(msg=="no_space"){
		    	alert("空间不足，无法上传");
		    }else{
		    	//显示正在上传
		    	$("#upload_menu").click();
		    	var count=0;
		    	for(var i = 0;i < shardCount;++i){
		            //计算每一片的起始与结束位置
		            var start = i * shardSize;
		            var end = Math.min(size, start + shardSize);
		            //构造一个表单，FormData是HTML5新增的
		            var form = new FormData();
		            form.append("data", file.slice(start,end));  //slice方法用于切出文件的一部分
		            form.append("file_name", file_name);
		            form.append("hdfs_name", msg);  //总片数
		            form.append("index", i + 1);//当前片数
		            //Ajax提交
		            $.ajax({
		                url: "FileUploadAction.action",
		                type: "POST",
		                data: form,
		                async: true,        //异步
		                processData: false,  //很重要，告诉jquery不要对form进行处理
		                contentType: false,  //很重要，指定为false才能形成正确的Content-Type
		                success: function(data){
		                	count++;
		                	var progress=(Number(100*count/shardCount)).toFixed(1);
		                	$("#progress_msg").html("<h4 style='margin: 30px;'>文件正在上传中，已完成 "+progress+" %</h4>'");
		                	if(count==shardCount){
		                		//文件全部上传完毕，开始合并，并导入至HDFS
		                		$("#progress_msg").html("<h4 style='margin: 30px;'>文件正在处理中</h4>'");
		                		$.ajax({ 
		    	    				type:'get', 
		    	    				url:"SaveFileInHDFSAction.action?hdfs_name="+msg+"&&file_name="+file_name+"&&path="+path+
		    	    						"&&total="+shardCount+"&&file_id="+file_id+"&&shard_size="+shardSize, 
		    	    				dataType: 'json', 
		    	    				success:function(data){ 
		    	    					//上传成功，关闭上传进度页面
				        		    	$('#close_upload_menu').click();
		    	    					//文件传输至HDFS
		    	    					//重置页数为1
		    	    					$('#index').val(1);
		    	    					//重置总页数为1，以加载新数据
		    	    					$('#total_page').val(1);
		    	    					
		    	    					loadGalleryData();
		    	    				} 
		    	    			});
		                	}
		                }
		            }); 
		        }
		    }
		    
			//清空文件上传框内容
			var file_input=$("#file");
			file_input.after(file_input.clone().val("")); 
			file_input.remove(); 
		}
    </script>
	</body>
</html>