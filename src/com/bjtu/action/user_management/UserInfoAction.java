package com.bjtu.action.user_management;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.UserEntity;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.login_register.ILoginRegisterService;
import com.bjtu.util.common.DataFormatUtil;
import com.bjtu.util.common.MD5Util;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class UserInfoAction extends ActionSupport{
	
	private UserEntity ue;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        if(user==null){
        	//ÓÃ»§Î´µÇÂ¼
        	return ERROR;
        }
        ue=new UserEntity();
		ue.setEmail(user.getEmail());
		ue.setNickname(user.getNickname());
		ue.setTotal_storage(String.valueOf(DataFormatUtil.doubleRound(user.getTotal_storage()/1024, 2, 1)));
		ue.setUsed_storage(String.valueOf(DataFormatUtil.doubleRound(user.getUsed_storage()/1024, 2, 1)));
        
		return SUCCESS;
	}

	public UserEntity getUe() {
		return ue;
	}

	public void setUe(UserEntity ue) {
		this.ue = ue;
	}
	
}
