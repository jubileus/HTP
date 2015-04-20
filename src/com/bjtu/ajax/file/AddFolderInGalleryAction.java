package com.bjtu.ajax.file;

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
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.HDFSUtil;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class AddFolderInGalleryAction extends ActionSupport{
	private String path;
	
	private IFileService file_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        //插入文件夹数据
        String timestamp=String.valueOf(System.currentTimeMillis());
        Tb_file file=new Tb_file();
        file.setCreate_time(DateUtil.getCurrentDateTime());
        file.setHdfs_name(timestamp);
        file.setId(StringUtil.getUUID());
    	file.setIs_complete(1);
    	file.setIs_folder(1);
    	file.setPath(path);
    	file.setPostfix("folder");
    	file.setTimestamp(timestamp);
    	file.setUser_id(user.getId());
    	file.setShow_name("新建文件夹");
    	file.setMd5("");
    	file.setSize_b("0");
    	file.setSize_mb(0);
        if(file_service.ifNewFileExist(file)){
        	//文件名重复，获取新编号
        	int new_num=file_service.getNewFileNum(file);
        	file.setShow_name(file.getShow_name()+"("+new_num+")");
        }
    	//执行插入操作
    	file_service.insert(file);
        //在HDFS中建立文件夹
    	HDFSUtil.createFolder(path+file.getHdfs_name()); 
    	
		return SUCCESS;
	}

	@JSON(serialize=false)
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
