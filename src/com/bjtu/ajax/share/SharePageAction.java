package com.bjtu.ajax.share;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.ShareEntity;
import com.bjtu.model.pojo.Tb_share;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.bjtu.service.share.IShareService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class SharePageAction extends ActionSupport{
	private int total_page;
	private int index;
	private String search_name;
	private List<ShareEntity> share_list;
	
	private IShareService share_service;
	private IFileTypeManagementService fileType_management_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        search_name=URLDecoder.decode(search_name, "UTF-8");
        
        //获取总页数
        total_page=share_service.getPageCount(user.getId(), 50, search_name);
        //获取页面数据
        List<Tb_share> tmp_list=share_service.getPageData(user.getId(), 50, index, search_name);
        //获取包含文件图标信息的HashMap
        HashMap<String, String> img_map=fileType_management_service.getImgMap();
        //将Tb_share转化为ShareEntity
        share_list=share_service.convertToShareEntity(tmp_list, img_map);
        
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

	@JSON(serialize=false)
	public String getSearch_name() {
		return search_name;
	}

	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}

	public List<ShareEntity> getShare_list() {
		return share_list;
	}

	public void setShare_list(List<ShareEntity> share_list) {
		this.share_list = share_list;
	}

	@JSON(serialize=false)
	public IShareService getShare_service() {
		return share_service;
	}

	public void setShare_service(IShareService share_service) {
		this.share_service = share_service;
	}
	
	@JSON(serialize=false)
	public IFileTypeManagementService getFileType_management_service() {
		return fileType_management_service;
	}

	public void setFileType_management_service(
			IFileTypeManagementService fileType_management_service) {
		this.fileType_management_service = fileType_management_service;
	}
	
}
