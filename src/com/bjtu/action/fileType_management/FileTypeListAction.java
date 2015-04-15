package com.bjtu.action.fileType_management;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.model.bo.FileTypeEntity;
import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class FileTypeListAction extends ActionSupport{
	private List<FileTypeEntity> file_type_list;
	private int total_page;
	
	private IFileTypeManagementService fileType_management_service;
	
	@Override
	public String execute() throws Exception {
		//获取前10条内容
		List<Tb_file_type> tmp_list=fileType_management_service.getFileTypeByPage(1, 10,null);
		//将Tb_file_type的List转化为FileTypeEntity的List
		file_type_list=fileType_management_service.convertToFileTypeEntityList(tmp_list);
		
		//获取总页数
		total_page=fileType_management_service.getFileTypePageCount(10,null);
		
        return SUCCESS;
	}

	public List<FileTypeEntity> getFile_type_list() {
		return file_type_list;
	}

	public void setFile_type_list(List<FileTypeEntity> file_type_list) {
		this.file_type_list = file_type_list;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public IFileTypeManagementService getFileType_management_service() {
		return fileType_management_service;
	}

	public void setFileType_management_service(
			IFileTypeManagementService fileType_management_service) {
		this.fileType_management_service = fileType_management_service;
	}
	
}
