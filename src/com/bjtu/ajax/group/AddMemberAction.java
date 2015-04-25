package com.bjtu.ajax.group;

import java.net.URLDecoder;

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
public class AddMemberAction extends ActionSupport{
	private String member_email;
	private String group_select;
	private IGroupService group_service;
	private int msg;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user myself=(Tb_user)session.getAttribute("user");
        //判断用户是否存在
		Tb_user user=new Tb_user();
        user.setEmail(member_email);
        Tb_user new_member=group_service.ifMemberExist(user);
        if(myself.getEmail().equals(member_email)){
        	//不能添加自己
        	msg=-1;
        }else{
        	if(new_member!=null){
            	//用户存在，开始判断用户是否已经在组内
            	Tb_member member=new Tb_member();
            	member.setGroup_id(group_select);
            	member.setUser_id(new_member.getId());
            	member=group_service.ifMemberInGroup(member);
            	if(member!=null){
            		//用户已经在组内
            		msg=1;
            	}else{
            		//用户不在组内，开始添加
            		member=new Tb_member();
            		member.setUser_id(new_member.getId());
            		member.setGroup_id(group_select);
            		member.setId(StringUtil.getUUID());
            		member.setNickname(new_member.getNickname());
            		group_service.insertMember(member);
            		msg=2;
            	}
            }else{
            	//不存在，不可添加
            	msg=0;
            }
        }
        
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getGroup_select() {
		return group_select;
	}

	public void setGroup_select(String group_select) {
		this.group_select = group_select;
	}

	@JSON(serialize=false)
	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	@JSON(serialize=false)
	public IGroupService getGroup_service() {
		return group_service;
	}

	public void setGroup_service(IGroupService group_service) {
		this.group_service = group_service;
	}

	public int getMsg() {
		return msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	
}
