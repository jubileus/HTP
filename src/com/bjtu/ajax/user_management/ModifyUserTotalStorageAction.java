package com.bjtu.ajax.user_management;

import java.util.List;

import com.bjtu.model.bo.UserEntity;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.user_management.IUserManagementService;
import com.bjtu.util.common.DataFormatUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ModifyUserTotalStorageAction extends ActionSupport{
	private String id;
	private float total_storage;
	private int total_page;
	private int page_index;
	private String nickname;
	private List<UserEntity> user_list;
	
	private IUserManagementService user_management_service;
	
	@Override
	public String execute() throws Exception {
		//修改用户总存储空间
		Tb_user user=new Tb_user();
		user.setId(id);
		user.setTotal_storage(Float.parseFloat(String.valueOf(DataFormatUtil.doubleRound(total_storage*1024, 0, 1))));
		user_management_service.modifyTotalStorage(user);
		//获取总页数
		total_page=user_management_service.getUserTypePageCount(10, nickname);
		//获取对应页面的数据
		List<Tb_user> tmp_list=user_management_service.getUserTypeByPage(page_index, 10, nickname);
		user_list=user_management_service.convertToUserEntityList(tmp_list);
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

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public int getPage_index() {
		return page_index;
	}

	public void setPage_index(int page_index) {
		this.page_index = page_index;
	}

	@JSON(serialize=false)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<UserEntity> getUser_list() {
		return user_list;
	}

	public void setUser_list(List<UserEntity> user_list) {
		this.user_list = user_list;
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
