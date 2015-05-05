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
public class AddGroupAction extends ActionSupport{
	private String group_name;
	private IGroupService group_service;
	private int msg;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        group_name=URLDecoder.decode(group_name, "UTF-8");
        //判断该用户是否建立过同名群组
        Tb_group group=new Tb_group();
        group.setCreator_id(user.getId());
        group.setName(group_name);
        if(group_service.ifExist(group)){
        	//群组已存在，需要更换名字
        	msg=0;
        }else{
        	//群组不存在，可以建立
        	group.setTimestamp(String.valueOf(System.currentTimeMillis()));
        	group.setId(StringUtil.getUUID());
        	//向数据库插入数据
        	group_service.insert(group);
        	//将创始人加入群组
        	Tb_member member=new Tb_member();
    		member.setUser_id(user.getId());
    		member.setGroup_id(group.getId());
    		member.setId(StringUtil.getUUID());
    		member.setNickname(user.getNickname());
    		group_service.insertMember(member);
    		
        	msg=1;
        }
    	
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
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
