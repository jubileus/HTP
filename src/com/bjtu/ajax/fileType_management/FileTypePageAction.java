package com.bjtu.ajax.fileType_management;

import java.util.List;

import com.bjtu.model.bo.FileTypeEntity;
import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class FileTypePageAction extends ActionSupport{
	private int total_page;
	private int page_index;
	private String postfix;
	private List<FileTypeEntity> file_type_list;
	
	private IFileTypeManagementService fileType_management_service;

	@Override
	public String execute() throws Exception {
		//获取总页数
		total_page=fileType_management_service.getFileTypePageCount(10,postfix);
		//获取对应页面的数据
		List<Tb_file_type> tmp_list=fileType_management_service.getFileTypeByPage(page_index, 10,postfix);
		file_type_list=fileType_management_service.convertToFileTypeEntityList(tmp_list);
		
		return SUCCESS;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public int getPage_index() {
		return page_index;
	}

	public void setPage_index(int page_index) {
		this.page_index = page_index;
	}

	public List<FileTypeEntity> getFile_type_list() {
		return file_type_list;
	}

	public void setFile_type_list(List<FileTypeEntity> file_type_list) {
		this.file_type_list = file_type_list;
	}

	@JSON(serialize=false)
	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
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
