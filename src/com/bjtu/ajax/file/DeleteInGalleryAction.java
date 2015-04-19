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
		//��ȡ�ļ�����
		Tb_file file=file_service.getById(id);
		
		//��HDFS��ɾ������
		FileSystem fs=HDFSUtil.openFileSystem();
    	HDFSUtil.deleteFolderOrFile(fs,file.getPath()+file.getHdfs_name());
    	HDFSUtil.closeFileSystem(fs);
		//ɾ���ļ�����
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
