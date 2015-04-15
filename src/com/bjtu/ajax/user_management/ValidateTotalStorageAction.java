package com.bjtu.ajax.user_management;

import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.user_management.IUserManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ValidateTotalStorageAction extends ActionSupport{
	private String id;
	private float total_storage;
	private int correct;
	
	private IUserManagementService user_management_service;
	
	@Override
	public String execute() throws Exception {
		Tb_user user=user_management_service.getUserById(id);
		if(total_storage<(user.getUsed_storage()/1024)){
			correct=0;
		}else{
			correct=1;
		}
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JSON(serialize=false)
	public float getTotal_storage() {
		return total_storage;
	}

	public void setTotal_storage(float total_storage) {
		this.total_storage = total_storage;
	}
	
	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	@JSON(serialize=false)
	public IUserManagementService getUser_management_service() {
		return user_management_service;
	}

	public void setUser_management_service(
			IUserManagementService user_management_service) {
		this.user_management_service = user_management_service;
	}

}
