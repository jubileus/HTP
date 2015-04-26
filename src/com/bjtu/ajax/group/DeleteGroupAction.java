package com.bjtu.ajax.group;

import java.net.URLDecoder;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_group;
import com.bjtu.model.pojo.Tb_member;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.group.IGroupService;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class DeleteGroupAction extends ActionSupport{
	private String group_id;
	private IGroupService group_service;

	@Override
	public String execute() throws Exception {
        //ɾ����Ա����
		group_service.deleteMembers(group_id);
		//ɾ��Ⱥ������
		group_service.delete(group_id);
		//ɾ���������з���
		//��ʱ����
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	@JSON(serialize=false)
	public IGroupService getGroup_service() {
		return group_service;
	}

	public void setGroup_service(IGroupService group_service) {
		this.group_service = group_service;
	}
}
