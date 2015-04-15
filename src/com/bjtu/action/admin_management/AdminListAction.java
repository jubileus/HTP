package com.bjtu.action.admin_management;

import java.util.List;

import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.admin_management.IAdminManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class AdminListAction extends ActionSupport{
	private List<Tb_user> admin_list;
	
	private IAdminManagementService admin_management_service;
	
	@Override
	public String execute() throws Exception {
		admin_list=admin_management_service.getAllAdmin();
        return SUCCESS;
	}

	public List<Tb_user> getAdmin_list() {
		return admin_list;
	}

	public void setAdmin_list(List<Tb_user> admin_list) {
		this.admin_list = admin_list;
	}

	public IAdminManagementService getAdmin_management_service() {
		return admin_management_service;
	}

	public void setAdmin_management_service(
			IAdminManagementService admin_management_service) {
		this.admin_management_service = admin_management_service;
	}
	
}
