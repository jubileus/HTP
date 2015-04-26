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
public class DeleteMemberAction extends ActionSupport{
	private String member_id_list;
	private IGroupService group_service;

	@Override
	public String execute() throws Exception {
        StringTokenizer st=new StringTokenizer(member_id_list, "_");
        String member_id;
        while(st.hasMoreElements()){
        	//循环删除成员
        	member_id=st.nextToken();
        	group_service.deleteSingleMember(member_id);
        	//删除成员发布的分析
        	//暂时留空
        }
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getMember_id_list() {
		return member_id_list;
	}

	public void setMember_id_list(String member_id_list) {
		this.member_id_list = member_id_list;
	}

	@JSON(serialize=false)
	public IGroupService getGroup_service() {
		return group_service;
	}

	public void setGroup_service(IGroupService group_service) {
		this.group_service = group_service;
	}
}
