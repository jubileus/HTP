﻿//切换成List视图
function changeToList(){
	$('#list_form').submit();
}

//加载Gallery样式的数据
function loadGalleryData(){
	var index=$('#index').val();
	var path=$('#path').val();
	var search_name=$('#search_name').val();
	var total_page=$('#total_page').val();
	search_name=encodeURI(encodeURI(search_name));
	var url = "AllFileInGalleryAction.action?index="+index+"&&search_name="+search_name+"&&path="+path; 
	var page_max=parseInt(total_page)+1;
	//查看是否已经加载完数据
	if(index<page_max){
		//尚未加载完所有页的数据
		$.ajax({ 
			type:'get', 
			url:url, 
			dataType: 'json', 
			success:function(data){ 
				//如果是加载第一页，则清空已有数据
				if(index==1){
					$("#showTable").empty();
				}
				//刷新刷新用户信息列表
				refreshGallery(data);
				//刷新路径条
				refreshPath(data);
				
				//绑定右击菜单
				bindRightMenu();
				
				//填写总页数和当前页数
				$('#total_page').val(data.total_page);
				var new_index=parseInt(index)+1;
				$('#index').val(new_index);
			} 
		})
	}
}

//模糊查询
function searchData(){
	var search_name=$('#search_name').val();
	//重置页数为1
	$('#index').val(1);
	//重置总页数为1，以加载新数据
	$('#total_page').val(1);
	
	loadGalleryData();
}

//点击刷新按钮刷新数据
function doRefresh(){
	//重置页数为1
	$('#index').val(1);
	//重新加载数据
	loadGalleryData();
}

//刷新路径条
function refreshPath(data){
	$("#path_content").empty();
	var _tr = "<ol class='breadcrumb'>"+
		"<li><input type='checkbox' id='check_all' onclick='javascript:check_all(this.id)'></li>"; 
	var path_num=data.path_num;
	var num=0;
	$.each(data.path_list,function(i,value){ 
		num++;
		//显示根目录
		if(num==path_num){
			//是最后一层
			_tr =_tr + "<li class='active'>"+value.name+"</li>"; 
		}else{
			//不是最后一层
			_tr =_tr + "<li><a href='#' id='"+value.path+"' onclick='javascript:jumpTo(this.id)'>"+value.name+"</a></li>"; 
		}
	}) 
	_tr+="</ol>";
	var str=$(_tr);
	$("#path_content").append(str); 
}

//通过路径条进行跳转
function jumpTo(path){
	//重置页数为1
	$('#index').val(1);
	//设置path
	$('#path').val(path);
	//重新加载数据
	loadGalleryData();
}

//刷新Gallery样式的数据
function refreshGallery(data){
	$.each(data.file_list,function(i,value){ 
		var _tr = $("<div class='"+value.right_class+" span1 img-container' id='"+value.file_id+"'>"+
				"<div class='img-box'>"+
				"<span class='ltt-icon' >"+
				"<i class='gallery-check' onclick='javascript:check_item(this.id)' id='"+value.check_id+"'></i>"+
				"</span>"+
				"<img style='width:60px;height:60px;' id='"+value.img_id+"' src='"+value.img+"' onclick='javascript:intoFolder(this.id)' />"+
				"<div id='"+value.file_name_id+"'>"+
				"<div id='"+value.show_name_id+"'>"+value.name+"</div>"+
				"</div>"+
				"</div>"+
				"</div>"); 
		$("#showTable").append(_tr); 
	}) 
}

//查看文件夹中的内容
function intoFolder(id){
	if(id.split("_")[0]!="not"){
		//重置页数为1
		$('#index').val(1);
		var path=$('#path').val();
		$('#path').val(path+id+"/");
		loadGalleryData();
	}
}

//检测滚动条是否到达底端
$(window).scroll(function(){
	var scrollTop = $(this).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(this).height();
	if(scrollTop + windowHeight == scrollHeight){
		//到达底端，如果有没有加载的数据，就加载下一页数据
		loadGalleryData();
	}
});

//文件下载
function downloadFile(file){
	var file_id=file.id.split("_")[1];
	$("#download_id").val(file_id);
	$("#download_form").submit();
}

//打开分享菜单
function openShareMenu(file){
	//记录本分享的文件id
	$("#share_id").val(file.id.split("_")[1]);
	//清空旧数据
	$("#public_share").html("");
	$("#private_share").html("");
	$("#share_code").val("");
	getGroupList();
	//打开上传菜单
	$("#share_menu").click();
}

//公开分享
function public_share(){
	var file_id=$("#share_id").val();
	var url = "PublicShareAction.action?file_id="+file_id;
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			$("#public_share").html(data.share_link);
		} 
	})
}

//私密分享
function private_share(){
	var file_id=$("#share_id").val();
	var url = "PrivateShareAction.action?file_id="+file_id;
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			$("#private_share").html(data.share_link);
			$("#share_code").val(data.share_code);
		} 
	})
}

//分享至群组
function group_share(){
	var checkbox=document.getElementsByName("group_share");  
	var group_id_list="";
    for(var i=0;i<checkbox.length;i++){
        if(checkbox[i].checked){
            group_id_list+="_"+checkbox[i].value;
        }
    }    
    var checked_num=$("input[name='group_share']:checked").length;
    if(checked_num==0){
    	//尚未选择任何群组
    	alert("尚未选择任何群组");
    }else{
    	var file_id=$("#share_id").val();
    	var url = "GroupShareAction.action?file_id="+file_id+"&&group_id_list="+group_id_list;
    	$.ajax({ 
    		type:'get', 
    		url:url, 
    		dataType: 'json', 
    		success:function(){ 
    			alert("已成功分享");
    			$("#share_menu_close").click();
    		} 
    	})
    }
}

//获取群组列表
function getGroupList(){
	var url = "GetGroupListAction.action";
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//清空旧列表
			$("#group_list").empty();
			//拼接列表内容
			var _tr = "";
			if(data.msg==1){
				$.each(data.group_list,function(i,value){ 
					_tr=_tr+"<div class='checkbox'><label>"+
					"<input type='checkbox' name='group_share' value='"+value.id+"' />"+value.name+
					"</label></div>";
				})
			}
			var str=$(_tr);
			//加载新列表
			$("#group_list").append(str); 
		} 
	})
}

//绑定右击菜单
function bindRightMenu(){
	//文件右击菜单
	$('div.file_item').contextMenu('file_menu', {
	      bindings: {
	        'download': function(t) {
	        	downloadFile(t);
	        },
	        'share': function(t) {
	        	openShareMenu(t);
	        },
	        'rename': function(t) {
	        	rename_start(t);
	        },
	        'delete': function(t) {
	        	deleteFolderOrFile(t);
	        }
	      }
	    });

	//文件夹右击菜单
	$('div.folder_item').contextMenu('folder_menu', {
	      bindings: {
	        'delete': function(t) {
	        	deleteFolderOrFile(t);
	        },
	        'rename': function(t) {
	        	rename_start(t);
	        }
	      }
	});
}

//用于查看那些文件被选中
function deleteCheckedFile(){
	var id_list="_";
	$('i[id^="check_"]').each(function () {
		var div_id="#file_"+this.id.split("_")[1];
		if($(div_id).hasClass("ui-selected")){
			//文件或文件夹被选中，开始删除
			var file_id=this.id.split("_")[1];
			id_list=id_list+file_id+"_";
		}
	});
	var url = "DeleteListAction.action?id_list="+id_list;
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//成功删除
			//重置页数为1
			$('#index').val(1);
			//重新加载数据
			loadGalleryData();
		} 
	})
}

//删除文件或文件夹操作
function deleteFolderOrFile(file){
	var file_id=file.id.split("_")[1];
	var url = "DeleteAction.action?id="+file_id;
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//成功删除
			
			//重置页数为1
			$('#index').val(1);
			//重新加载数据
			loadGalleryData();
		} 
	})
}

//全选按钮
function check_all(check_button_id){
	if ($("#"+check_button_id).is(":checked")){
		//执行全选操作
		$('i[id^="check_"]').each(function () {
        	var file_id="#file_"+this.id.split("_")[1];
        	if($(file_id).hasClass("ui-selected")){
        		//已经被选中
        	}else{
        		//尚未被选中
        		$("#"+this.id).removeClass("gallery-check");	
    			$(file_id).addClass("ui-selected");
    			$("#"+this.id).parent().removeClass("ltt-icon");
    			$("#"+this.id).parent().addClass("ltt-icon-checked");
        	}
        });
	}else{
		//取消全选操作
		$('i[id^="check_"]').each(function () {
        	var file_id="#file_"+this.id.split("_")[1];
        	if($(file_id).hasClass("ui-selected")){
        		//已经被选中
        		$(file_id).removeClass("ui-selected");
    			$("#"+this.id).addClass("gallery-check");	
    			$("#"+this.id).parent().addClass("ltt-icon");
    			$("#"+this.id).parent().removeClass("ltt-icon-checked");		
        	}else{
        		//尚未被选中
        	}
        });
	}
}

//文件重命名
function rename_start(file){
	var file_name_id="#file_name_"+file.id.split("_")[1];
	var name_id="#name_"+file.id.split("_")[1];
	var new_name_id="new_name_"+file.id.split("_")[1];
	//暂存旧名称
	var last_index=$(name_id).html().lastIndexOf("."); 
	var name_length=$(name_id).html().length;
	if(last_index==-1){
		//是文件夹
		$("#old_name").val($(name_id).html());
		$("#postfix").val("folder");
	}else{
		//是文件
		$("#old_name").val($(name_id).html().substring(0,last_index));
		$("#postfix").val($(name_id).html().substring(last_index+1,name_length));
	}
	
	//显示输入框
	var show_name=$(name_id).html().split(".")[0];
	var _str=$("<input style='width:55px;height:20px;' onblur='javascript:rename_over(this.id)' value='"+show_name+"' type='text' id='"+new_name_id+"' name='"+new_name_id+"' />");
	$(file_name_id).empty();
	$(file_name_id).append(_str); 
	$("#"+new_name_id).focus();
}
	
//完成重命名
function rename_over(new_name_id){
	var file_id=new_name_id.split("_")[2];
	var new_name=$("#new_name_"+file_id).val();
	var old_name=$("#old_name").val();
	var postfix=$("#postfix").val();
	if(trim(new_name).length==0){
		alert("名称不可为空");
		//删除输入框并显示旧名称
		show_old_name(file_id);
	}else{
		if(new_name.indexOf(".")==-1){
			//文件名不包含'.'，是合法的
			if(old_name==new_name){
				//未进行修改
				//删除输入框并显示旧名称
				show_old_name(file_id);
			}else{
				//进行了修改
				//清空模糊查询框数据
				$('#search_name').val("");
				//重置页数为1
				$('#index').val(1);
				new_name=encodeURI(encodeURI(new_name));
				var url = "ModifyFileNameInGalleryAction.action?new_name="+new_name+"&&file_id="+file_id;
				$.ajax({ 
					type:'get', 
					url:url, 
					dataType: 'json', 
					success:function(data){ 
						//成功修改
						loadGalleryData();
					} 
				})
			}
		}else{
			//文件名包含'.'，不合法
			alert("文件名不可包含 . ");
			//删除输入框并显示旧名称
			show_old_name(file_id);
		}
	}
}

//文件名修改失败时显示旧名称
function show_old_name(file_id){
	var old_name=$("#old_name").val();
	var postfix=$("#postfix").val();
	var file_name_id="#file_name_"+file_id;
	var name_id="name_"+file_id;
	$(file_name_id).empty();
	var _str;
	if(postfix=="folder"){
		//是文件夹
		_str=$("<div id='"+name_id+"'>"+old_name+"</div>");
	}else{
		//是文件
		_str=$("<div id='"+name_id+"'>"+old_name+"."+postfix+"</div>");
	}
	$(file_name_id).append(_str);
}

//添加文件夹
function addFolder(){
	var path=$('#path').val();
	var url = "AddFolderAction.action?path="+path;
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//成功建立
			//重置页数为1
			$('#index').val(1);
			loadGalleryData(); 
		} 
	})
}

//文件图标的选择或取消选择操作
function check_item(id){
	var check_id="#"+id;
	var file_id="#file_"+id.split("_")[1];
	if($(file_id).hasClass("ui-selected")){
		$(file_id).removeClass("ui-selected");
		$(check_id).addClass("gallery-check");	
		$(check_id).parent().addClass("ltt-icon");
		$(check_id).parent().removeClass("ltt-icon-checked");		
	}else{
		$(check_id).removeClass("gallery-check");	
		$(file_id).addClass("ui-selected");
		$(check_id).parent().removeClass("ltt-icon");
		$(check_id).parent().addClass("ltt-icon-checked");		
	}		  
}

//切换分享菜单的页面
function change(type){
	$("li").removeClass("active");
	$(".share_display").css("display","none"); 
	$("#"+type).addClass("active"); 
	$("#div_"+type).css("display","block"); 
};
