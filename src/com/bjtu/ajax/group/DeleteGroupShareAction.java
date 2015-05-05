package com.bjtu.ajax.group;

import java.net.URLDecoder;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_group;
import com.bjtu.model.pojo.Tb_member;
import com.bjtu.model.pojo.Tb_share;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.group.IGroupService;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class DeleteGroupShareAction extends ActionSupport{
	private String share_id;
	private IGroupService group_service;
	private int msg;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        
        Tb_share share=group_service.getShareById(share_id);
        String creator_id=share.getCreator_id();//文件分享者id
        Tb_group group=group_service.getById(share.getGroup_id());
        String admin_id=group.getCreator_id();//群创始者id
        if(user.getId().equals(admin_id)||user.getId().equals(creator_id)){
        	//是文件分享者或者群创始者
        	group_service.deleteShare(share_id);
        	msg=1;
        }else{
        	//权限不足，不能删除
        	msg=0;
        }
        
		return SUCCESS;
	}

	public String getShare_id() {
		return share_id;
	}

	public void setShare_id(String share_id) {
		this.share_id = share_id;
	}

	public int getMsg() {
		return msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	@JSON(serialize=false)
	public IGroupService getGroup_service() {
		return group_service;
	}

	public void setGroup_service(IGroupService group_service) {
		this.group_service = group_service;
	}
}
