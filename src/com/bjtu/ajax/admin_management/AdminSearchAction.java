package com.bjtu.ajax.admin_management;

import java.util.List;

import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.admin_management.IAdminManagementService;
import com.bjtu.service.admin_management.IAdminManagementService;
import com.bjtu.service.login_register.ILoginRegisterService;
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.MD5Util;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class AdminSearchAction extends ActionSupport{
	private String nickname;
	private boolean get_all;
    private List<Tb_user> admin_list;
	
    private IAdminManagementService admin_management_service;
	
	@Override
	public String execute() throws Exception {
		Tb_user user=new Tb_user();
		user.setNickname(nickname);
		//查询符合条件的管理员列表
		admin_list=admin_management_service.searchAdmin(user,get_all);
        return SUCCESS;
	}

	@JSON(serialize=false)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isGet_all() {
		return get_all;
	}
	
	@JSON(serialize=false)
	public void setGet_all(boolean get_all) {
		this.get_all = get_all;
	}

	public List<Tb_user> getAdmin_list() {
		return admin_list;
	}

	public void setAdmin_list(List<Tb_user> admin_list) {
		this.admin_list = admin_list;
	}

	@JSON(serialize=false)
	public IAdminManagementService getAdmin_management_service() {
		return admin_management_service;
	}

	public void setAdmin_management_service(
			IAdminManagementService admin_management_service) {
		this.admin_management_service = admin_management_service;
	}
	
}
