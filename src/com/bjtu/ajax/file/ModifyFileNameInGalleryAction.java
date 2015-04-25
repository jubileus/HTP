package com.bjtu.ajax.file;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.bo.PathEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.file.IFileService;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ModifyFileNameInGalleryAction extends ActionSupport{
	private String new_name;
	private String file_id;
	
	private IFileService file_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        new_name=URLDecoder.decode(new_name, "UTF-8");
        Tb_file file=file_service.getById(file_id);
        file.setShow_name(new_name);
        if(file_service.ifNewFileExist(file)){
        	//文件名重复，获取新编号
        	int new_num=file_service.getNewFileNum(file);
        	file.setShow_name(new_name+"("+new_num+")");
        	file_service.modifyShowName(file);
        }else{
        	//文件名不重复，直接修改
        	file_service.modifyShowName(file);
        }
        
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	@JSON(serialize=false)
	public String getNew_name() {
		return new_name;
	}

	public void setNew_name(String new_name) {
		this.new_name = new_name;
	}

	@JSON(serialize=false)
	public IFileService getFile_service() {
		return file_service;
	}

	public void setFile_service(IFileService file_service) {
		this.file_service = file_service;
	}
}
