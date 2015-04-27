//刷新List样式的数据
function refreshList(data){
	$.each(data.file_list,function(i,value){ 
		var _tr = $("<tr><td><input id='"+value.check_id+"' type='checkbox' name='fileCheck'/>" +
				"<img id='"+value.img_id+"' onclick='javascript:intoFolder(this.id)' style='width:30px;height:30px;' src='"+value.img+"' /></td>"+
				"<td id='"+value.name_td_id+"'>"+value.name+"</td>"+
				"<td>"+value.size+"</td>"+
				"<td>"+value.date+"</td>"+
				"<td><a id='"+value.downloadFile_id+"' onclick='javascript:downloadFile(this.id)' href='#'>"+value.download_file+"</a></td>"+
				"<td><a id='"+value.shareFile_id+"' onclick='javascript:openShareMenu(this.id)' href='#'>"+value.share_file+"</a></td>"+
				"<td><a id='"+value.deleteFile_id+"' onclick='javascript:deleteFolderOrFile(this.id)' href='#'>"+value.delete_file+"</a></td>"+
				"<td><a id='"+value.renameFile_id+"' onclick='javascript:rename_start(this.id)' href='#'>"+value.rename_file+"</a></td>"); 
		$("#showTable").append(_tr); 
	}) 
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
	_tr+"</ol>";
	var str=$(_tr);
	$("#path_content").append(str); 
}

//加载List样式的数据
function loadListData(){
	var index=$('#index').val();
	var path=$('#path').val();
	var search_name=$('#search_name').val();
	var total_page=$('#total_page').val();
	
	search_name=encodeURI(encodeURI(search_name));
	var url = "AllFileInListAction.action?index="+index+"&&search_name="+search_name+"&&path="+path; 
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
				refreshList(data);
				//刷新路径条
				refreshPath(data);
				
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
	
	loadListData();
}

//查看文件夹中的内容
function intoFolder(id){
	if(id.split("_")[0]!="not"){
		//重置页数为1
		$('#index').val(1);
		var path=$('#path').val();
		$('#path').val(path+id+"/");
		loadListData();
	}
}

//点击刷新按钮刷新数据
function doRefresh(){
	//重置页数为1
	$('#index').val(1);
	//重新加载数据
	loadListData();
}

//通过路径条进行跳转
function jumpTo(path){
	//重置页数为1
	$('#index').val(1);
	//设置path
	$('#path').val(path);
	//重新加载数据
	loadListData();
}

//检测滚动条是否到达底端
$(window).scroll(function(){
	var scrollTop = $(this).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(this).height();
	if(scrollTop + windowHeight == scrollHeight){
		//到达底端，如果有没有加载的数据，就加载下一页数据
		loadListData();
	}
});

//全选或反选操作
function check_all(check_button_id){
	if ($("#"+check_button_id).is(":checked")){
		//执行全选操作
		$("input[name='fileCheck']").each(function(){
		      this.checked=true;  
		});
	}else{
		//取消全选操作
		$("input[name='fileCheck']").each(function(){
		      this.checked=false;  
		});
	}
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
			loadListData(); 
		} 
	})
}

//删除文件或文件夹操作
function deleteFolderOrFile(file){
	var file_id=file.split("_")[1];
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
			loadListData();
		} 
	})
}

//用于查看那些文件被选中
function deleteCheckedFile(){
	var id_list="_";
	$("input[name='fileCheck']").each(function(){
		if(this.checked==true){
			//文件被选中
			var file_id=this.id.split("_")[1];
			id_list+=file_id+"_";
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
			loadListData();
		} 
	})
}

//切换成Gallery视图
function changeToGallery(){
	$('#gallery_form').submit();
}

//文件名修改失败时显示旧名称
function show_old_name(file_id){
	var old_name=$("#old_name").val();
	var postfix=$("#postfix").val();
	var file_name_id="#name_"+file_id;
	$(file_name_id).empty();
	var _str;
	if(postfix=="folder"){
		//是文件夹
		_str=old_name;
	}else{
		//是文件
		_str=old_name+"."+postfix;
	}
	$(file_name_id).append(_str);
}

//文件重命名
function rename_start(file){
	var old_name=$("#name_"+file.split("_")[1]).html();
	var new_name_id="new_name_"+file.split("_")[1];
	//暂存旧名称
	var last_index=old_name.lastIndexOf("."); 
	var name_length=old_name.length;
	if(last_index==-1){
		//是文件夹
		$("#old_name").val(old_name);
		$("#postfix").val("folder");
	}else{
		//是文件
		$("#old_name").val(old_name.substring(0,last_index));
		$("#postfix").val(old_name.substring(last_index+1,name_length));
	}
	
	//显示输入框
	var show_name=old_name.split(".")[0];
	var _str=$("<input style='width:100px;height:20px;' onblur='javascript:rename_over(this.id)' value='"+show_name+"' type='text' id='"+new_name_id+"' name='"+new_name_id+"' />");
	$("#name_"+file.split("_")[1]).empty();
	$("#name_"+file.split("_")[1]).append(_str); 
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
						loadListData();
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

//打开分享菜单
function openShareMenu(file){
	//记录本分享的文件id
	$("#share_id").val(file.split("_")[1]);
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

//文件下载
function downloadFile(file){
	var file_id=file.split("_")[1];
	$("#download_id").val(file_id);
	$("#download_form").submit();
}

//切换分享菜单的页面
function change(type){
	$("li").removeClass("active");
	$(".share_display").css("display","none"); 
	$("#"+type).addClass("active"); 
	$("#div_"+type).css("display","block"); 
};