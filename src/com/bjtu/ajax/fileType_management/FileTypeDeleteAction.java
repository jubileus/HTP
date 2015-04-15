package com.bjtu.ajax.fileType_management;

import java.util.List;

import com.bjtu.model.bo.FileTypeEntity;
import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class FileTypeDeleteAction extends ActionSupport{
	private String id;
	private int total_page;
	private int page_index;
	private List<FileTypeEntity> file_type_list;
	
	private String postfix;
	private String img_type;
	
	private IFileTypeManagementService fileType_management_service;

	@Override
	public String execute() throws Exception {
		//删除指定对象
		fileType_management_service.deleteFileType(id);
		
		//查询添加后的文件类型列表
		//获取总页数
		total_page=fileType_management_service.getFileTypePageCount(10,null);
		//获取对应页面的数据
		List<Tb_file_type> tmp_list=fileType_management_service.getFileTypeByPage(1, 10,null);
		file_type_list=fileType_management_service.convertToFileTypeEntityList(tmp_list);
		
		return SUCCESS;
	}

	@JSON(serialize=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public String getImg_type() {
		return img_type;
	}

	public void setImg_type(String img_type) {
		this.img_type = img_type;
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
