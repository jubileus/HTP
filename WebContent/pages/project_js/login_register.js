//检验注册信息
function validateRegister(){
	if(validateEmail()){
		var email = $('#email').val();
		var url = "ValidateEmailAction.action?email="+email; 
		$.ajax({ 
			type:'get', 
			url:url, 
			dataType: 'json', 
			success:function(data){ 
				var email_msg=document.getElementById('email_msg');
				if(data.exist==0){
					email_msg.innerHTML="<font color='green'>邮箱可以使用</font>";					
					if(validateNickname()&&validatePassword()&&validateConfirmPassword()){
						$('#register_form').submit();
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

//登录时验证邮箱格式
function validateEmailForLogin(){
	var email_msg=document.getElementById('email_msg');
	if(validateEmail()){
		email_msg.innerHTML="<font color='green'>格式正确</font>";
		return true;
	}else{
		email_msg.innerHTML="<font color='red'>邮箱格式不正确</font>";
		return false;
	}
}

//检验登录信息
function validateLogin(){
	if(validateEmailForLogin()&&validatePassword()){
		$('#login_form').submit();
	}
}