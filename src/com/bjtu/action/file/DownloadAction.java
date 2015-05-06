package com.bjtu.action.file;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.file.IFileService;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.bjtu.util.common.HDFSUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class DownloadAction extends ActionSupport{
	private String download_id;
	private String fileName;
	
	private IFileService file_service;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public InputStream getInputStream() {
		Tb_file file=file_service.getById(download_id);
		String path=file.getPath();
		fileName=file.getHdfs_name()+"."+file.getPostfix();
		return HDFSUtil.download(path, fileName);
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDownload_id() {
		return download_id;
	}

	public void setDownload_id(String download_id) {
		this.download_id = download_id;
	}

	public IFileService getFile_service() {
		return file_service;
	}

	public void setFile_service(IFileService file_service) {
		this.file_service = file_service;
	}
	
}
