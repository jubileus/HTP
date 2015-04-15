package com.bjtu.action.login_register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class LogoutAction extends ActionSupport{
	
	@Override
	public String execute() throws Exception {
		//将存在session中的用户信息对象user移除
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return SUCCESS;
	}
}
