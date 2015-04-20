package com.bjtu.action.login_register;

import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.login_register.ILoginRegisterService;
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.HDFSUtil;
import com.bjtu.util.common.MD5Util;
import com.bjtu.util.common.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class RegisterAction extends ActionSupport{
    private String email;
    private String nickname;
    private String password;
	
	private ILoginRegisterService login_register_service;
	
	@Override
	public String execute() throws Exception {
		//向数据库中插入用户信息
		Tb_user user=new Tb_user();
		user.setId(StringUtil.getUUID());
		user.setEmail(email);
		user.setNickname(nickname);
		user.setImg("default");
		user.setPassword(MD5Util.MD5(password));
		user.setRegister_time(DateUtil.getCurrentDate());
		//设置role=0，表示普通用户
		user.setRole(0);
		user.setStatus(1);
		//默认空间大小10GB=10*1024MB=10240MB
		user.setTotal_storage(10240);
		user.setUsed_storage(0);
		
		login_register_service.register(user);
		
		//在HDFS中建立用户的文件目录
		HDFSUtil.createFolder("/user/hadoop/user/"+user.getId()+"/file"); 
		return SUCCESS;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ILoginRegisterService getLogin_register_service() {
		return login_register_service;
	}

	public void setLogin_register_service(
			ILoginRegisterService login_register_service) {
		this.login_register_service = login_register_service;
	}
	
}
