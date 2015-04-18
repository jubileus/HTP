package com.bjtu.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.file.IFileService;
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class TestMongoAction extends ActionSupport{
	private IFileService file_service;
	public IFileService getFile_service() {
		return file_service;
	}
	public void setFile_service(IFileService file_service) {
		this.file_service = file_service;
	}

	@Override
	public String execute() throws Exception {
		insertFileData();
		
		return SUCCESS;
	}
	
	private void insertFileData(){
		//获取用户id
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        String user_id=user.getId();
        
        //插入1条文件夹数据至用户根目录
        Tb_file file;
        int index=0;
        for(int i=0;i<1;i++){
        	String timestamp=String.valueOf(System.currentTimeMillis());
        	file=new Tb_file();
        	file.setCreate_time(DateUtil.getCurrentDateTime());
        	index++;
        	file.setHdfs_name(timestamp);
        	file.setId(StringUtil.getUUID());
        	file.setIs_complete(1);
        	file.setIs_folder(1);
//        	file.setPath("/user/hadoop/user/"+user_id+"/file/");
        	file.setPath("/user/hadoop/user/"+user_id+"/file/1429348039973/");
        	file.setPostfix("folder");
        	file.setShow_name("folder"+index);
        	file.setTimestamp(timestamp);
        	file.setUser_id(user_id);
        	file_service.insert(file);
        }
        //插入170条文件数据至用户根目录
        index=0;
        for(int i=0;i<170;i++){
        	String timestamp=String.valueOf(System.currentTimeMillis());
        	file=new Tb_file();
        	file.setCreate_time(DateUtil.getCurrentDateTime());
        	index++;
        	file.setHdfs_name(timestamp);
        	file.setId(StringUtil.getUUID());
        	file.setIs_complete(1);
        	file.setIs_folder(0);
//        	file.setPath("/user/hadoop/user/"+user_id+"/file/");
        	file.setPath("/user/hadoop/user/"+user_id+"/file/1429348039973/");
        	file.setPostfix("doc");
        	file.setShow_name("word"+index);
        	file.setTimestamp(timestamp);
        	file.setUser_id(user_id);
        	file_service.insert(file);
        }
	}
}
