package com.bjtu.ajax.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.fs.FileSystem;
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
public class DeleteInGalleryAction extends ActionSupport{
	private String id;
	
	private IFileService file_service;
	
	@Override
	public String execute() throws Exception {
		//获取文件对象
		Tb_file file=file_service.getById(id);
		
		//更新用户已使用空间大小
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        user.setUsed_storage(user.getUsed_storage()-file.getSize_mb());
        file_service.updateUsedStorage(user);
		
		//在HDFS中删除数据
		FileSystem fs=HDFSUtil.openFileSystem();
		if(file.getIs_folder()==1){
			//删除文件夹
			HDFSUtil.deleteFolderOrFile(fs,file.getPath()+file.getHdfs_name());
		}else{
			//删除文件
			HDFSUtil.deleteFolderOrFile(fs,file.getPath()+file.getHdfs_name()+"."+file.getPostfix());
		}
    	HDFSUtil.closeFileSystem(fs);
		//删除文件数据
		file_service.deleteFolderOrFile(id);
        
		return SUCCESS;
	}

	@JSON(serialize=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JSON(serialize=false)
	public IFileService getFile_service() {
		return file_service;
	}

	public void setFile_service(IFileService file_service) {
		this.file_service = file_service;
	}
}
