package com.bjtu.ajax.group;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_group;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.group.IGroupService;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class GetGroupListByCreatorAction extends ActionSupport{
	private List<Tb_group> group_list;
	private IGroupService group_service;
	private int msg;//1：群组不为空，0：群组为空

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        
        group_list=group_service.getGroupByCreatorId(user.getId());
        if(group_list!=null&&group_list.size()>0){
        	msg=1;
        }else{
        	msg=0;
        }
		return SUCCESS;
	}
	
	public int getMsg() {
		return msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	public List<Tb_group> getGroup_list() {
		return group_list;
	}

	public void setGroup_list(List<Tb_group> group_list) {
		this.group_list = group_list;
	}

	@JSON(serialize=false)
	public IGroupService getGroup_service() {
		return group_service;
	}

	public void setGroup_service(IGroupService group_service) {
		this.group_service = group_service;
	}

}
