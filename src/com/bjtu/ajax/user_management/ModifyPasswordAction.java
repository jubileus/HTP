package com.bjtu.ajax.user_management;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.UserEntity;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.user_management.IUserManagementService;
import com.bjtu.util.common.DataFormatUtil;
import com.bjtu.util.common.MD5Util;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ModifyPasswordAction extends ActionSupport{
	private String old_password;
	private String password;
	private String msg;
	
	private IUserManagementService user_management_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        if(user==null){
        	//ÓÃ»§Î´µÇÂ¼
        	return ERROR;
        }
        
        if(MD5Util.MD5(old_password).equals(user.getPassword())){
        	//¾ÉÃÜÂëÕýÈ·£¬¿ªÊ¼ÐÞ¸ÄÃÜÂë
        	user.setPassword(MD5Util.MD5(password));
    		user_management_service.modifyPassword(user);
    		
    		session.setAttribute("user", user);
        }else{
        	//¾ÉÃÜÂë´íÎó
        	msg="wrong";
        }
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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
