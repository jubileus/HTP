package com.bjtu.ajax.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.GroupShareEntity;
import com.bjtu.model.pojo.Tb_share;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.group.IGroupService;
import com.bjtu.service.share.IShareService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class GroupSharePageAction extends ActionSupport{

	private String group_id;
	private int total_page;
	private int index;
	private List<GroupShareEntity> share_list;
	
	private IGroupService group_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        //获取总页数
        total_page=group_service.getPageCount(group_id, 10);
        //获取页面数据
        List<Tb_share> tmp_list=group_service.getPageData(group_id, 10, index);
        //将Tb_share转化为GroupShareEntity
        share_list=group_service.convertToGroupShareEntity(tmp_list, user.getId());
        
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

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<GroupShareEntity> getShare_list() {
		return share_list;
	}

	public void setShare_list(List<GroupShareEntity> share_list) {
		this.share_list = share_list;
	}
}
