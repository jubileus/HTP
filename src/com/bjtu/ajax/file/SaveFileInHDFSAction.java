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
import org.apache.hadoop.fs.Path;
import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.bo.PathEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.file.IFileService;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.FileUtil;
import com.bjtu.util.common.HDFSUtil;
import com.bjtu.util.common.StringUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class SaveFileInHDFSAction extends ActionSupport{
	private String hdfs_name;
	private String file_name;
	private String path;
	private int total;
	private String file_id;
	private long shard_size;
	
	private IFileService file_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        //将文件存入HDFS
        SaveToHDfS(user);
        
        //更改文件状态为上传完毕
        Tb_file file=file_service.getById(file_id);
        file.setIs_complete(1);
        file_service.modifyIs_complete(file);
        
        //删除临时文件
        FileUtil.delFolder(FileUtil.TEMPPATH+user.getId());
        
		return SUCCESS;
	}
	
	private void SaveToHDfS(Tb_user user){
		try {
			//将文件存入HDFS
	        FileSystem fs=HDFSUtil.openFileSystem("/user/hadoop/user/"+user.getId()+"/");
	        //建立空白文件
	        int index_of_dot=file_name.lastIndexOf(".");
	        String postfix=file_name.substring(index_of_dot+1, file_name.length());
	        HDFSUtil.createNewFile(path+hdfs_name+"."+postfix, fs);
	        FSDataOutputStream dst=fs.create(new Path(path+hdfs_name+"."+postfix));
	        RandomAccessFile tmp;
	        byte[] buffer;
	        //循环写入文件
	        for(int i=1;i<=total;i++){
	        	tmp=new RandomAccessFile(FileUtil.TEMPPATH+user.getId()+FileUtil.DEVIDE+hdfs_name+"_"+i+"."+postfix, "r");
	        	buffer=new byte[(int)tmp.length()];
	        	tmp.read(buffer);
	        	dst.write(buffer);
	        	tmp.close();
	        }
	        dst.close();
	        fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//将文件存入HDFS
	private void SaveFileIntoHDFS(Tb_user user){
		try {
			//将文件存入HDFS
	        FileSystem fs=HDFSUtil.openFileSystem("/user/hadoop/user/"+user.getId()+"/");
	        //建立空白文件
	        int index_of_dot=file_name.lastIndexOf(".");
	        String postfix=file_name.substring(index_of_dot+1, file_name.length());
	        HDFSUtil.createNewFile(path+hdfs_name+"."+postfix, fs);
	        FSDataOutputStream dst=fs.create(new Path(path+hdfs_name+"."+postfix));
	        //导入数据
	        RandomAccessFile src=new RandomAccessFile(FileUtil.TEMPPATH+user.getId()+FileUtil.DEVIDE+hdfs_name+"."+postfix, "rw");
	        long total_size=src.length();
	        long last_size=total_size%shard_size;
	        if(last_size==0){
	        	//循环total次正好读完文件
	        	for(int i=0;i<total;i++){
	        		byte[] buffer=new byte[(int)shard_size];
	        		src.read(buffer);
	        		HDFSUtil.appendFile(dst, buffer);
	        	}
	        }else{
	        	//循环total次剩余last_size个byte
	        	for(int i=0;i<total-1;i++){
	        		byte[] buffer=new byte[(int)shard_size];
	        		src.read(buffer);
	        		HDFSUtil.appendFile(dst, buffer);
	        	}
	        	byte[] buffer=new byte[(int)last_size];
	        	src.read(buffer);
	    		HDFSUtil.appendFile(dst, buffer);
	        }
	        dst.close();
	        src.close();
	        
	        //关闭FileSystem
	        fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@JSON(serialize=false)
	public long getShard_size() {
		return shard_size;
	}

	public void setShard_size(long shard_size) {
		this.shard_size = shard_size;
	}

	@JSON(serialize=false)
	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	@JSON(serialize=false)
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(serialize=false)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
