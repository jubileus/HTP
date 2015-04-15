//跳转至管理员列表页面
function getAdminList(){
	$('#admin_list_form').submit();
}

//跳转至文件类型管理页面
function getFileTypeList(){
	$('#file_type_list_form').submit();
}

//跳转至用户管理页面
function getUserList(){
	$('#user_list_form').submit();
}

//检测是否在删除自己
function ifDeleteMyself(delete_id){
	var my_id=$('#my_id').val();
	if(delete_id==my_id){
		return true;
	}else{
		return false;
	}
}

//刷新管理员列表
function refreshAdminList(data){
	$("#showTable").empty();
	$.each(data.admin_list,function(i,value){ 
		var _tr = $("<tr><td><img src='/HTP/pages/img/default_img.png' width='40px' height='40px' class='img-circle avatar hidden-phone' /></td>"+
				"<td>"+value.nickname+"</td><td>"+value.register_time+"</td><td class='align-right'>"+value.email+
				"</td><td class='align-right'><a id='"+value.id+"' href='#' onclick='javascript:deleteAdmin(this.id)'>删除</a></td></tr>"); 
		$("#showTable").append(_tr); 
	}) 
}

//删除管理员
function deleteAdmin(delete_id){
	if(!ifDeleteMyself(delete_id)){
		//合法操作
		var url = "AdminDeleteAction.action?id="+delete_id; 
		$.ajax({ 
			type:'get', 
			url:url, 
			dataType: 'json', 
			success:function(data){ 
				//刷新管理员列表
				refreshAdminList(data);
			} 
		})
	}else{
		//管理员删除自己，停止该操作
		alert("您不可以删除自己")
	}
}

//查找包含指定昵称的管理员
function searchAdmin(){
	var search_nickname=$('#search_nickname').val();
	if(trim(search_nickname).length>0){
		//查询的昵称不为空
		var url = "AdminSearchAction.action?nickname="+trim(search_nickname)+"&&get_all=false"; 
	}else{
		//查询结果为空，显示所有管理员
		var url = "AdminSearchAction.action?nickname="+trim(search_nickname)+"&&get_all=true"; 
	}
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//刷新管理员列表
			refreshAdminList(data);
			//清空搜索框
			$('#search_nickname').val("");
		} 
	})
}

//添加管理员
function addAdmin(){
	if(validateEmail()){
		var email=$('#email').val();
		var nickname=$('#nickname').val();
		var password=$('#password').val();
		var url = "ValidateEmailAction.action?email="+email; 
		$.ajax({ 
			type:'get', 
			url:url, 
			dataType: 'json', 
			success:function(data){ 
				var email_msg=document.getElementById('email_msg');
				if(data.exist==0){
					if(validateNickname()&&validatePassword()&&validateConfirmPassword()){
						//开始添加管理员
						var add_url = "AdminAddAction.action?email="+email+"&&nickname="+nickname+"&&password="+password;
						$.ajax({ 
							type:'get', 
							url:add_url, 
							dataType: 'json',
							success:function(data){ 
								//刷新管理员列表
								refreshAdminList(data);
								//清空输入框内容和提示框内容
								$('#email').val("");
								$('#nickname').val("");
								$('#password').val("");
								$('#password_confirm').val("");
								document.getElementById('email_msg').innerHTML="";
								document.getElementById('nickname_msg').innerHTML="";
								document.getElementById('password_msg').innerHTML="";
								document.getElementById('password_confirm_msg').innerHTML="";
							} 
						})	
					}
				}else{
					email_msg.innerHTML="<font color='red'>邮箱已存在，请改用其他邮箱</font>";
				}
			} 
		})		
	}else{
		var email_msg=document.getElementById('email_msg');
		email_msg.innerHTML="<font color='red'>邮箱格式不正确</font>";
		return false;
	}
}
