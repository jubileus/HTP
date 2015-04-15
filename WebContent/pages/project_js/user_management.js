//用户列表向后翻页
function getNextPageForUser(){
	var page_index=document.getElementById('page_index').innerHTML;
	var total_page=$('#total_page').val();
	if(page_index==total_page){
		alert("本页已经是最后一页");
	}else{
		var target_page=parseInt(page_index)+1;
		getPageForUser(target_page);
	}
}

//用户列表向前翻页
function getPreviousPageForUser(){
	var page_index=document.getElementById('page_index').innerHTML;
	var total_page=$('#total_page').val();
	if(page_index==1){
		alert("本页已经是第一页");
	}else{
		var target_page=parseInt(page_index)-1;
		getPageForUser(target_page);
	}
}

//跳转至用户列表的第一页
function getFirstPageForUser(){
	getPageForUser(1);
}

//跳转至用户列表的最后一页
function getLastPageForUser(){
	var total_page=$('#total_page').val();
	getPageForUser(total_page);
}

//模糊查询符合条件的数据
function searchNicknameInUserManagement(){
	getPageForUser(1);
}

//刷新用户信息数据
function refreshUserList(data){
	$("#showTable").empty();
	$.each(data.user_list,function(i,value){ 
		var _tr = $("<tr>"+
				"<td><img src='/HTP/pages/img/default_img.png' style='width: 40px;height: 40px;' class='img-circle avatar hidden-phone' /></td>"+
				"<td>"+value.nickname+"</td>"+
				"<td>"+value.register_time+"</td>"+
				"<td><input class='span5 inline-input' id='"+value.id+"' maxlength='6' onfocus='javascript:saveTempTotalStorage(this.value)' onblur='javascript:validateTotalStorage(this.value,this.id)' type='text' value='"+value.total_storage+"' /></td>"+
				"<td>"+value.used_storage+"</td>"+
				"<td class='align-right'>"+value.email+"</td>"+
				"<td class='align-right'><a id='"+value.id+"' onclick='javascript:modifyUserStatus(this.id)' href='#'>"+value.status_name+"</a></td>"); 
		$("#showTable").append(_tr); 
	}) 
}

//获取指定页数的用户信息数据
function getPageForUser(page_index){
	var search_nickname=$('#search_nickname').val();
	var url = "UserPageAction.action?page_index="+page_index+"&&nickname="+trim(search_nickname); 
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//刷新刷新用户信息列表
			refreshUserList(data);
			//填写总页数和当前页数
			$('#total_page').val(data.total_page);
			document.getElementById('page_index').innerHTML=page_index;
		} 
	})
}

//修改用户状态
function modifyUserStatus(modify_id){
	var page_index=document.getElementById('page_index').innerHTML;
	var search_nickname=$('#search_nickname').val();
	var url = "ModifyUserStatusAction.action?id="+modify_id+"&&page_index="+page_index+"&&nickname="+search_nickname; 
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//刷新刷新用户信息列表
			refreshUserList(data);
			//填写总页数和当前页数
			$('#total_page').val(data.total_page);
		} 
	})
}

//暂存原有总存储空间
function saveTempTotalStorage(total_page){
	$('#tmp_storage').val(total_page);
}

//验证总存储空间格式
function validateTotalStorage(total_storage,modify_id){
	if(trim(total_storage).length>0){
		if(!isNaN(total_storage)){
			//格式正确，检测大于已使用空间
			var url = "ValidateTotalStorageAction.action?id="+modify_id+"&&total_storage="+total_storage; 
			$.ajax({ 
				type:'get', 
				url:url, 
				dataType: 'json', 
				success:function(data){ 
					if(data.correct==0){
						alert("不能小于已使用空间");
						//新数值小于已使用空间，显示原总存储空间
						document.getElementById(modify_id).value=$('#tmp_storage').val();
					}else{
						//合法，可以进行修改操作
						var page_index=document.getElementById('page_index').innerHTML;
						var search_nickname=$('#search_nickname').val();
						var modify_url = "ModifyUserTotalStorageAction.action?id="+modify_id+"&&page_index="+page_index+"&&nickname="+
						search_nickname+"&&total_storage="+total_storage; 
						$.ajax({ 
							type:'get', 
							url:modify_url, 
							dataType: 'json', 
							success:function(data){ 
								//刷新用户信息列表
								refreshUserList(data);
								//填写总页数和当前页数
								$('#total_page').val(data.total_page);
							} 
						})
					}
				} 
			})	
		}else{
			alert("必须是数字");
		}
	}else{
		//新数值为空，显示原总存储空间
		document.getElementById(modify_id).value=$('#tmp_storage').val();
	}
}