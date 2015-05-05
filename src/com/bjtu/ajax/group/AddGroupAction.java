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
        //�жϸ��û��Ƿ�����ͬ��Ⱥ��
        Tb_group group=new Tb_group();
        group.setCreator_id(user.getId());
        group.setName(group_name);
        if(group_service.ifExist(group)){
        	//Ⱥ���Ѵ��ڣ���Ҫ��������
        	msg=0;
        }else{
        	//Ⱥ�鲻���ڣ����Խ���
        	group.setTimestamp(String.valueOf(System.currentTimeMillis()));
        	group.setId(StringUtil.getUUID());
        	//�����ݿ��������
        	group_service.insert(group);
        	//����ʼ�˼���Ⱥ��
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
