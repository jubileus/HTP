package com.bjtu.ajax.login_register;

import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.login_register.ILoginRegisterService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ValidateEmailAction extends ActionSupport{
	private String email;
	private int exist;
	private ILoginRegisterService login_register_service;
	
	@Override
	public String execute() throws Exception {
		Tb_user user=new Tb_user();
		user.setEmail(email);
		exist= login_register_service.ifEmailExist(user);
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getExist() {
		return exist;
	}
	
	public void setExist(int exist) {
		this.exist = exist;
	}
	
	@JSON(serialize=false)
	public ILoginRegisterService getLogin_register_service() {
		return login_register_service;
	}

	public void setLogin_register_service(
			ILoginRegisterService login_register_service) {
		this.login_register_service = login_register_service;
	}
}
