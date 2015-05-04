package com.bjtu.ajax.user_management;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.UserEntity;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.user_management.IUserManagementService;
import com.bjtu.util.common.DataFormatUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ModifyNicknameAction extends ActionSupport{
	private String nickname;
	
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
        user.setNickname(nickname);
		
		user_management_service.modifyNickname(user);
		
		session.setAttribute("user", user);
		return SUCCESS;
	}

	@JSON(serialize=false)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
