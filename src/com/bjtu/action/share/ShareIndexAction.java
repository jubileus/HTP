package com.bjtu.action.share;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.share.IShareService;
import com.bjtu.util.share.ShareParam;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ShareIndexAction extends ActionSupport{
	private int total_page;
	private int index;
	private String share_path;
	
	private IShareService share_service;
	
	@Override
	public String execute() throws Exception {
		//检测是否登录
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        if(user==null){
        	//用户未登录
        	return ERROR;
        }
		//给分享路径赋值
		share_path=ShareParam.SHARE_PATH+"HTP/ShareViewAction.action?id=";
		//查找总页数
		total_page=share_service.getPageCount(user.getId(), 50, null);
		//设置初始要获取的页数为第一页
        index=1;
		return SUCCESS;
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

	public String getShare_path() {
		return share_path;
	}

	public void setShare_path(String share_path) {
		this.share_path = share_path;
	}

	public IShareService getShare_service() {
		return share_service;
	}

	public void setShare_service(IShareService share_service) {
		this.share_service = share_service;
	}
	
}
