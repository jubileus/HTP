package com.bjtu.ajax.file;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.ListFileEntity;
import com.bjtu.model.bo.PathEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.file.IFileService;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class AllFileInListAction extends ActionSupport{
	private int total_page;
	private int index;
	private String path;
	private String search_name;
	private List<ListFileEntity> file_list;
	private List<PathEntity> path_list;
	private int path_num;
	
	private IFileService file_service;
	private IFileTypeManagementService fileType_management_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        search_name=URLDecoder.decode(search_name, "UTF-8");
        //获取总页数
		total_page=file_service.getPageCount(100, path, search_name);
        //获取页面数据
        List<Tb_file> tmp_list=file_service.getPageData(100, index, path, search_name);
        
        //获取包含文件图标信息的HashMap
        HashMap<String, String> img_map=fileType_management_service.getImgMap();
        
        //将Tb_file转化为GalleryFileEntity
        file_list=file_service.convertToListFileEntity(tmp_list, img_map);
        //设置路径条信息
        path_list=file_service.getPathList(user.getId(),path);
        path_num=path_list.size();
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
	public String getSearch_name() {
		return search_name;
	}

	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}

	public List<ListFileEntity> getFile_list() {
		return file_list;
	}

	public void setFile_list(List<ListFileEntity> file_list) {
		this.file_list = file_list;
	}

	public List<PathEntity> getPath_list() {
		return path_list;
	}

	public void setPath_list(List<PathEntity> path_list) {
		this.path_list = path_list;
	}

	public int getPath_num() {
		return path_num;
	}

	public void setPath_num(int path_num) {
		this.path_num = path_num;
	}

	@JSON(serialize=false)
	public IFileTypeManagementService getFileType_management_service() {
		return fileType_management_service;
	}

	public void setFileType_management_service(
			IFileTypeManagementService fileType_management_service) {
		this.fileType_management_service = fileType_management_service;
	}

	@JSON(serialize=false)
	public IFileService getFile_service() {
		return file_service;
	}

	public void setFile_service(IFileService file_service) {
		this.file_service = file_service;
	}
	
}
