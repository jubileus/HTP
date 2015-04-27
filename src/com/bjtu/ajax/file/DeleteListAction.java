package com.bjtu.ajax.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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
public class DeleteListAction extends ActionSupport{
	private String id_list;
	
	private IFileService file_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
		
		StringTokenizer st=new StringTokenizer(id_list,"_");
		String id;
		Tb_file file;
		//初始化被删除文件总大小
		double total_delete_size=0;
		while(st.hasMoreElements()){
			id=st.nextToken();
			file=file_service.getById(id);
			//更新被删除文件总大小
			total_delete_size+=file.getSize_mb();
			if(file.getIs_folder()==1){
				//删除文件夹
				HDFSUtil.deleteFolderOrFile(file.getPath()+file.getHdfs_name());
			}else{
				//删除文件
				HDFSUtil.deleteFolderOrFile(file.getPath()+file.getHdfs_name()+"."+file.getPostfix());
			}
			//删除文件数据
			file_service.deleteFolderOrFile(id);
		}
		//更新用户已使用空间大小
		user.setUsed_storage(user.getUsed_storage()-total_delete_size);
        file_service.updateUsedStorage(user);
		
        
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getId_list() {
		return id_list;
	}

	public void setId_list(String id_list) {
		this.id_list = id_list;
	}

	@JSON(serialize=false)
	public IFileService getFile_service() {
		return file_service;
	}

	public void setFile_service(IFileService file_service) {
		this.file_service = file_service;
	}
}
