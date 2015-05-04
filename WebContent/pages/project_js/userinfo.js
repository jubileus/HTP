//修改用户昵称
function modifyNickname(){
	var nickname=$("#nickname").val();
	if(trim(nickname).length>0){
		//新昵称不为空，可以修改
		var url="ModifyNicknameAction.action?nickname="+nickname; 
		$.ajax({ 
			type:'get', 
			url:url, 
			dataType: 'json', 
			success:function(){ 
				//修改后，保存新昵称到隐藏域
    			$("#old_nickname").val(trim(nickname));
			} 
		})
	}else{
		//新昵称为空，显示旧昵称
		alert("昵称不能为空");
		showOldNickname();
	}
}
        	
//修改昵称失败时，显示原有昵称
function showOldNickname(){
	$("#nickname").val($("#old_nickname").val());
}

//修改密码
function modifyPassword(){
	var old_password=$("#old_password").val();
	var password=$("#password").val();
	var password_confirm=$("#password_confirm").val();
	
	var varify=password.match(/^\w+$/); 
	if(trim(old_password).length>0){
		if(trim(password).length>0){
			if(password==password_confirm){
				if(trim(password).length<6){
			    	alert("密码不能少于6个字符");
			    	return false;
			    }else{
			    	if(varify==null) {
			    		alert("密码只能由数字，字母或下划线组成");
				    }else{
				    	//格式正确
				    	var url="ModifyPasswordAction.action?password="+password+"&&old_password="+old_password; 
				    	$.ajax({ 
							type:'get', 
							url:url, 
							dataType: 'json', 
							success:function(data){ 
				    			if(data.msg=="wrong"){
				    				alert("旧密码错误");
				    			}else{
				    				alert("修改成功");
				    				$("#old_password").val("");
				    				$("#password").val("");
				    				$("#password_confirm").val("");
				    				$("#close_modify_password").click();
				    			}
							} 
						})
				    }  
			    }
			}else{
				//确认密码与密码不一致
				alert("确认密码错误");
			}
		}else{
			//新密码为空
			alert("新密码不可为空");
		}
	}else{
		//旧密码为空
		alert("旧密码不可为空");
	}
}