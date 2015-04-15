package com.bjtu.ajax.user_management;

import java.util.List;

import com.bjtu.model.bo.UserEntity;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.user_management.IUserManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ModifyUserStatusAction extends ActionSupport{
	private String id;
	private int total_page;
	private int page_index;
	private String nickname;
	private List<UserEntity> user_list;
	
	private IUserManagementService user_management_service;
	
	@Override
	public String execute() throws Exception {
		//�޸��û�״̬
		Tb_user user=new Tb_user();
		user.setId(id);
		user_management_service.modifyStatus(user);
		//��ȡ��ҳ��
		total_page=user_management_service.getUserTypePageCount(10, nickname);
		//��ȡ��Ӧҳ�������
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
