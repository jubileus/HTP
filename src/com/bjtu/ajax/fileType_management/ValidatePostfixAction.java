package com.bjtu.ajax.fileType_management;

import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ValidatePostfixAction extends ActionSupport{
	private String postfix;
	private int exist;
	
	private IFileTypeManagementService fileType_management_service;

	@Override
	public String execute() throws Exception {
		Tb_file_type file_type=new Tb_file_type();
		file_type.setPostfix(postfix);
		exist=fileType_management_service.ifPostfixExist(file_type);
		return SUCCESS;
	}

	@JSON(serialize=false)
	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
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
