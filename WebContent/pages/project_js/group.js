//加载群组分享内容
function loadGroupShareData(){
	var index=$('#index').val();
	var group_id=$('#group_id').val();
	var url = "GroupSharePageAction.action?index="+index+"&&group_id="+group_id;
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//清空已有数据
			$("#showTable").empty();
			//刷新列表
			refreshData(data);
			
			$('#total_page').val(data.total_page);
		} 
	})
}

//删除分享
function deleteShare(share){
	var share_id=share.split("_")[1];
	var url="DeleteGroupShareAction.action?share_id="+share_id;
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			if(data.msg==1){
				//成功删除
				$("#index").val(1);
				$("#a_index").html(1);
				loadGroupShareData();
			}else{
				//权限不足，不能删除
				alert("权限不足，不能删除");
			}
		} 
	})
}

//文件下载
function downloadFile(file){
	var file_id=file.split("_")[1];
	$("#download_id").val(file_id);
	$("#download_form").submit();
}

//先前翻页
function previousPage(){
	var index=$('#index').val();
	if(trim(index).length>0){
		if(index==1){
			alert("已经是第一页");
		}else{
			var new_index=parseInt(index)-1;
			$('#index').val(new_index);
			$("#a_index").html(new_index);
			loadGroupShareData();
		}
	}else{
		alert("请先选择群组");
	}
}

//先前翻页
function nextPage(){
	var total_page=$('#total_page').val();
	var index=$('#index').val();
	if(trim(index).length>0){
		if(index==total_page){
			alert("已经是最后一页");
		}else{
			var new_index=parseInt(index)+1;
			$('#index').val(new_index);
			$("#a_index").html(new_index);
			loadGroupShareData();
		}
	}else{
		alert("请先选择群组");
	}
}

//刷新列表内容
function refreshData(data){
	$.each(data.share_list,function(i,value){ 
		var _tr = $("<li class='"+value.li_class+"'><a class='user' href='#'>" +
				"<img class='img-responsive avatar_' style='width:30px;height:30px;' src='/HTP/pages/img/default_img.png' >" +
				"<span class='user-name'>"+value.nickname+"</span></a>" +
				"<div class='reply-content-box'><span class='reply-time'>"+value.date+"</span>" +
				"<div class='reply-content pr'><span class='arrow'>&nbsp;</span>"+value.name+"<a href='#' onclick='javascript:downloadFile(this.id)' id='file_"+value.file_id+"'>下载</a>&nbsp;&nbsp;" +
				"<a href='#' id='share_"+value.id+"' onclick='javascript:deleteShare(this.id)'>删除</a>" +
				"</div></div></li>"); 
		$("#showTable").append(_tr); 
	}) 
}

//添加群组
function createGroup(){
	var group_name=$('#group_name').val();
	group_name=encodeURI(encodeURI(group_name));
	var url = "AddGroupAction.action?group_name="+group_name; 
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			if(data.msg==0){
				//同名群组已存在
				$('#group_name').focus();
				alert("该名称群组已存在，请更换名称");
			}else{
				//刷新群组列表
				refreshTree();
				
				//关闭添加菜单
				closeCreateGroup();
			}
		} 
	})
}

//打开添加群组成员菜单
function openAddGroupMemberMenu(){
	//获取群组列表
	getGroupList();
}

//打开删除群组菜单
function openDeleteGroupMenu(){
	//获取群组列表
	getGroupListForDelete();
}

//删除群组
function deleteGroup(){
	var group_id=$('#group_delete').val();
	if(group_id=="not_select"){
		alert("请选择群组");
	}else{
		var url = "DeleteGroupAction.action?group_id="+group_id;
		$.ajax({ 
			type:'get', 
			url:url, 
			async: false,   
			dataType: 'json', 
			success:function(){
				//刷新群组列表
				refreshTree();
				//关闭删除群组菜单
				closeDeleteGroup();
			} 
		})
	}
}

//添加群组成员
function addGroupMember(){
	var group_select=$('#group_select').val();
	var member_email=$('#member_email').val();
	var url = "AddMemberAction.action?member_email="+member_email+"&&group_select="+group_select;
	
	if(group_select=="not_select"){
		alert("请选择群组");
	}else{
		$.ajax({ 
			type:'get', 
			url:url, 
			dataType: 'json', 
			success:function(data){ 
				if(data.msg==-1){
					//不能添加自己
					$('#member_email').focus();
					alert("不能添加自己");
				}else if(data.msg==0){
					//用户不存在
					$('#member_email').focus();
					alert("该用户不存在");
				}else{
					if(data.msg==1){
						$('#member_email').focus();
						alert("该用户已在组内");
					}else{
						//刷新群组列表
						refreshTree();
						
						//关闭添加菜单
						closeAddMember();
					}
				}
			} 
		})
	}
}

//获取群组列表
function getGroupList(){
	var url = "GetGroupListByCreatorAction.action";
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//清空旧列表
			$("#select_to_add_div").empty();
			//拼接列表内容
			var _tr = "<select id='group_select' style='height: 30px' name='group_select'>"+
			"<option value='not_select'>---请选择群组---</option>";
			if(data.msg==1){
				$.each(data.group_list,function(i,value){ 
					_tr =_tr + "<option value='"+value.id+"'>"+value.name+"</option>";
				})
			}
			_tr =_tr +"</select>";
			var str=$(_tr);
			//加载新列表
			$("#select_to_add_div").append(str); 
		} 
	})
}

//删除群组时获取群组列表
function getGroupListForDelete(){
	var url = "GetGroupListByCreatorAction.action";
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//清空旧列表
			$("#select_to_delete_div").empty();
			//拼接列表内容
			var _tr = "<select id='group_delete' style='height: 30px' name='group_delete'>"+
			"<option value='not_select'>---请选择群组---</option>";
			if(data.msg==1){
				$.each(data.group_list,function(i,value){ 
					_tr =_tr + "<option value='"+value.id+"'>"+value.name+"</option>";
				})
			}
			_tr =_tr +"</select>";
			var str=$(_tr);
			//加载新列表
			$("#select_to_delete_div").append(str); 
		} 
	})
}

//删除群组成员
function deleteMember(){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var treeNode = treeObj.getCheckedNodes(true);
	var str_id="";
	for (x in treeNode) {
	    if (!treeNode[x].isParent) {
	    	//拼接要删除的成员id字符串
	    	str_id+="_"+treeNode[x].user_id
	    }
	}
	var url = "DeleteMemberAction.action?member_id_list="+str_id;
	$.ajax({ 
		type:'get', 
		url:url, 
		async: false,   
		dataType: 'json', 
		success:function(){
			//刷新群组列表
			refreshTree();
		} 
	})
}

//关闭添加群组菜单
function closeCreateGroup(){
	$('#group_name').val("");
    $('#close_create_group').click();
}

//关闭添加群组成员菜单
function closeAddMember(){
	$('#member_email').val("");
	$('#close_add_member').click();
}

//关闭删除群组菜单
function closeDeleteGroup(){
	$('#close_delete_group').click();
}