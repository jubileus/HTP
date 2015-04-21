package com.bjtu.ajax.file;

import java.io.RandomAccessFile;
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
import com.bjtu.util.common.DataFormatUtil;
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.FileUtil;
import com.bjtu.util.common.HDFSUtil;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class IfFileCanUploadAction extends ActionSupport{
	private String file_name;
	private String path;
	private double size_mb;
	private String file_id;
	private IFileService file_service;
	
	private String msg;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        //检测是否有剩余空间进行上传
    	size_mb=DataFormatUtil.doubleRound(size_mb, 2, 1);//对数据大小保留两位小数
    	
        if(user.getUsed_storage()+size_mb>user.getTotal_storage()){//无空间进行上传
        	msg="no_space";
        }else{//有空间进行上传
        	//检测路径下文件名称是否重复
            Tb_file file=new Tb_file();
            file.setPath(path);
            file.setUser_id(user.getId());
            //切分名称和后缀
            int index_of_dot=file_name.lastIndexOf(".");
            String show_name=file_name.substring(0, index_of_dot);
            String postfix=file_name.substring(index_of_dot+1, file_name.length());
            file.setShow_name(show_name);
            file.setPostfix(postfix);
            if(file_service.ifNewFileExist(file)){
            	//文件名重复，获取新编号
            	int new_num=file_service.getNewFileNum(file);
            	file.setShow_name(show_name+"("+new_num+")");
            }
            String timestamp=String.valueOf(System.currentTimeMillis());
            file.setCreate_time(DateUtil.getCurrentDateTime());
            file.setHdfs_name(timestamp);
            file_id=StringUtil.getUUID();
            file.setId(file_id);
            file.setIs_complete(0);
            file.setIs_folder(0);
            file.setSize_mb(size_mb);
            file.setTimestamp(timestamp);
            //向数据库中插入文件数据
            file_service.insert(file);
            
            msg=timestamp;
            
            //如果尚未建立，则建立文件暂存目录
    		FileUtil.createFolder(FileUtil.TEMPPATH+user.getId());
    		//建立空文件
    		RandomAccessFile merge=new RandomAccessFile(FileUtil.TEMPPATH+user.getId()+FileUtil.DEVIDE+
    				file.getHdfs_name()+"."+file.getPostfix(),"rw");
    		merge.close();
        }
        
		return SUCCESS;
	}
	
	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	@JSON(serialize=false)	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	@JSON(serialize=false)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@JSON(serialize=false)
	public double getSize_mb() {
		return size_mb;
	}

	public void setSize_mb(double size_mb) {
		this.size_mb = size_mb;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@JSON(serialize=false)
	public IFileService getFile_service() {
		return file_service;
	}

	public void setFile_service(IFileService file_service) {
		this.file_service = file_service;
	}
}
