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
public class AdminAddAction extends ActionSupport{
	private String email;
    private String nickname;
    private String password;
    private List<Tb_user> admin_list;
	
	private IAdminManagementService admin_management_service;
	
	@Override
	public String execute() throws Exception {
		//�����ݿ��в������Ա��Ϣ
		Tb_user user=new Tb_user();
		user.setId(StringUtil.getUUID());
		user.setEmail(email);
		user.setNickname(nickname);
		user.setImg("default");
		user.setPassword(MD5Util.MD5(password));
		user.setRegister_time(DateUtil.getCurrentDate());
		//����role=1����ʾ����Ա
		user.setRole(1);
		user.setStatus(1);
		//Ĭ�Ͽռ��С0������Ա����Ҫ����ռ�
		user.setTotal_storage(0);
		user.setUsed_storage(0);
		admin_management_service.addAdmin(user);
		
		//��ѯ��Ӻ�Ĺ���Ա�б�
		admin_list=admin_management_service.getAllAdmin();
        return SUCCESS;
	}

	@JSON(serialize=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JSON(serialize=false)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
