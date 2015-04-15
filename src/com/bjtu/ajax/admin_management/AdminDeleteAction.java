package com.bjtu.ajax.admin_management;

import java.util.List;

import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.admin_management.IAdminManagementService;
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.MD5Util;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class AdminDeleteAction extends ActionSupport{
	private String id;
    private List<Tb_user> admin_list;
	
    private IAdminManagementService admin_management_service;
	
	@Override
	public String execute() throws Exception {
		//删除指定的管理员信息
		admin_management_service.deleteAdmin(id);
		
		//查询添加后的管理员列表
		admin_list=admin_management_service.getAllAdmin();
        return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
