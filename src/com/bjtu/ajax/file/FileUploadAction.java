package com.bjtu.ajax.file;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.bo.PathEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.file.IFileService;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.FileUtil;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class FileUploadAction extends ActionSupport{
	private File data;
	private String hdfs_name;
	private String file_name;
	private int index;
	
	private IFileService file_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        //切分后缀并拼接path
        int index_of_dot=file_name.lastIndexOf(".");
        String postfix=file_name.substring(index_of_dot+1, file_name.length());
		String path=FileUtil.TEMPPATH+user.getId()+FileUtil.DEVIDE+hdfs_name+"_"+index+"."+postfix;
		
		//将File转换为byte[]
		byte[] buffer=FileUtil.getBytes(data);
		//在已建立的空文件中追加内容
		RandomAccessFile dst=new RandomAccessFile(path, "rw");
//		dst.seek(dst.length());
		dst.write(buffer);
		dst.close();
		return SUCCESS;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@JSON(serialize=false)
	public File getData() {
		return data;
	}

	public void setData(File data) {
		this.data = data;
	}

	@JSON(serialize=false)
	public String getHdfs_name() {
		return hdfs_name;
	}

	public void setHdfs_name(String hdfs_name) {
		this.hdfs_name = hdfs_name;
	}

	@JSON(serialize=false)
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	@JSON(serialize=false)
	public IFileService getFile_service() {
		return file_service;
	}

	public void setFile_service(IFileService file_service) {
		this.file_service = file_service;
	}
}
