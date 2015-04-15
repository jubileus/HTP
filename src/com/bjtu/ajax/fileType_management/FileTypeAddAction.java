package com.bjtu.ajax.fileType_management;

import java.util.List;

import com.bjtu.model.bo.FileTypeEntity;
import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class FileTypeAddAction extends ActionSupport{
	private int total_page;
	private int page_index;
	private List<FileTypeEntity> file_type_list;
	
	private String postfix;
	private String img_type;
	
	private IFileTypeManagementService fileType_management_service;

	@Override
	public String execute() throws Exception {
		//向数据库中插入文件类型对象
		Tb_file_type file_type=new Tb_file_type();
		file_type.setId(StringUtil.getUUID());
		file_type.setPostfix(postfix.toLowerCase());
		file_type.setImg("/HTP/pages/img/icon/"+img_type);
		file_type.setCategory(getFileTypeCategory(img_type));
		file_type.setTimestamp(String.valueOf(System.currentTimeMillis()));
		fileType_management_service.addFileType(file_type);
		
		//查询添加后的文件类型列表
		//获取总页数
		total_page=fileType_management_service.getFileTypePageCount(10,null);
		//获取对应页面的数据
		List<Tb_file_type> tmp_list=fileType_management_service.getFileTypeByPage(page_index, 10,null);
		file_type_list=fileType_management_service.convertToFileTypeEntityList(tmp_list);
		
		return SUCCESS;
	}

	//根据图片类型确定文件类型的分类
	private int getFileTypeCategory(String img_type){
		switch (img_type) {
			case "icon_file.png":
				return 0;
			case "icon_folder.png":
				return 0;
			case "icon_image.png":
				return 2;
			case "icon_music.png":
				return 4;
			case "icon_pdf.png":
				return 1;
			case "icon_ppt.png":
				return 1;
			case "icon_txt.png":
				return 1;
			case "icon_video.png":
				return 3;
			case "icon_word.png":
				return 1;
			case "icon_xls.png":
				return 1;
			default:
				return 0;
		}
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
