package com.bjtu.action.login_register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.login_register.ILoginRegisterService;
import com.bjtu.util.common.MD5Util;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class LoginAction extends ActionSupport{
	private String email;
	private String password;
	private String fail_msg;
	
	private ILoginRegisterService login_register_service;
	
	@Override
	public String execute() throws Exception {
		Tb_user user=new Tb_user();
		user.setEmail(email);
		user.setPassword(MD5Util.MD5(password));
		user=login_register_service.login(user);
		if(user!=null){
			if(user.getStatus()==0){
				//��¼ʧ��
				fail_msg="���û��ѱ�ֹͣʹ��";
				return ERROR;
			}else{
				//��¼�ɹ������û���Ϣ����session
				HttpServletRequest request = ServletActionContext.getRequest();
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);
	            
	            if(user.getRole()==0){
	            	//��ͨ�û���¼
	            	return "user";
	            }else{
	            	//����Ա��¼
	            	return "admin";
	            }
			}
		}else{
			//��¼ʧ��
			fail_msg="�û�����������������µ�¼";
			return ERROR;
		}				
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ILoginRegisterService getLogin_register_service() {
		return login_register_service;
	}

	public void setLogin_register_service(
			ILoginRegisterService login_register_service) {
		this.login_register_service = login_register_service;
	}

	public String getFail_msg() {
		return fail_msg;
	}

	public void setFail_msg(String fail_msg) {
		this.fail_msg = fail_msg;
	}
	
}
