package com.bjtu.action.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.file.IFileService;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class AllFileInListIndexAction extends ActionSupport{
	private int total_page;
	private int index;
	private String path;
	
	private IFileService file_service;
	
	@Override
	public String execute() throws Exception {
		if(path==null){
			//设定默认目录问用户根目录
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpSession session = request.getSession();
	        Tb_user user=(Tb_user)session.getAttribute("user");
	        if(user==null){
	        	//用户未登录
	        	return ERROR;
	        }
	        path="/user/hadoop/user/"+user.getId()+"/file/";
		}
        
        //获取总页数
  		total_page=file_service.getPageCount(100, path, null);
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@JSON(serialize=false)
	public IFileService getFile_service() {
		return file_service;
	}

	public void setFile_service(IFileService file_service) {
		this.file_service = file_service;
	}
	
}
