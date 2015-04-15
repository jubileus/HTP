//去除两端空格
function trim(ui){
    var   notValid=/(^\s)|(\s$)/;
    while(notValid.test(ui)){
        ui=ui.replace(notValid,"");
    }
    return ui;
}

//验证邮箱格式
function validateEmail(){
	var email = $('#email').val();
	if (email != '') {//判断
	    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	    if (!reg.test(email)) {
	        return false;      
	    }else{
	        return true;
	    }
	}
}

//验证昵称格式
function validateNickname(){
	var nickname=$('#nickname').val();
	var nickname_msg=document.getElementById('nickname_msg');
	if(trim(nickname).length==0){
		nickname_msg.innerHTML="<font color='red'>昵称不可为空</font>";
		return false;
	}else{
		nickname_msg.innerHTML="<font color='green'>格式正确</font>";
		return true;
	}
}

//验证密码格式（判断密码是否是一个由 0-9 / A-Z / a-z 和下划线组成的字符串 ）
function validatePassword(){ 
	var password=$('#password').val();
    var result=password.match(/^\w+$/); 
    var password_msg=document.getElementById('password_msg');
    if(trim(password).length<6){
    	password_msg.innerHTML="<font color='red'>密码不可少于6个字符</font>";
    	return false;
    }else{
    	if(result==null) {
    		password_msg.innerHTML="<font color='red'>密码只能由数字，字母或下划线组成</font>";
    		return false;
	    }else{
	    	password_msg.innerHTML="<font color='green'>格式正确</font>";
	    	return true;
	    }  
    }	       
} 

//验证确认密码是否与密码一致
function validateConfirmPassword() {
	var password=$('#password').val();
	var password_confirm=$('#password_confirm').val();
	var password_confirm_msg=document.getElementById('password_confirm_msg');
	if(password==password_confirm){
		password_confirm_msg.innerHTML="<font color='green'>格式正确</font>";	
		return true;
	}else{
		password_confirm_msg.innerHTML="<font color='red'>确认密码与密码不一致</font>";
		return false;
	}
}

//验证邮箱是否已经被人注册
function ifEmailExist(){
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