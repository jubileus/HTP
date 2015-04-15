package com.bjtu.action.user_management;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.model.bo.UserEntity;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.user_management.IUserManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class UserListAction extends ActionSupport{
	private List<UserEntity> user_list;
	private int total_page;

	private IUserManagementService user_management_service;
	
	@Override
	public String execute() throws Exception {
		//获取前10条内容
		List<Tb_user> tmp_list=user_management_service.getUserTypeByPage(1, 10, null);
		//将Tb_user的List转化为UserEntity的List
		user_list=user_management_service.convertToUserEntityList(tmp_list);
		//获取总页数
		total_page=user_management_service.getUserTypePageCount(10, null);
		
        return SUCCESS;
	}
	

	public List<UserEntity> getUser_list() {
		return user_list;
	}

	public void setUser_list(List<UserEntity> user_list) {
		this.user_list = user_list;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public IUserManagementService getUser_management_service() {
		return user_management_service;
	}

	public void setUser_management_service(
			IUserManagementService user_management_service) {
		this.user_management_service = user_management_service;
	}
	
}
