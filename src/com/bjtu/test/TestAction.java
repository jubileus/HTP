package com.bjtu.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.admin_management.IAdminManagementService;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.bjtu.service.login_register.ILoginRegisterService;
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.MD5Util;
import com.bjtu.util.common.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{
	private ILoginRegisterService login_register_service;
	private IAdminManagementService admin_management_service;
	private IFileTypeManagementService fileType_management_service;
	
	@Override
	public String execute() throws Exception {
		
		addFileType();
		
		return SUCCESS;
	}
	
	private void addFileType(){
		//0=普通文件，1=文档文件，2=图片文件，3=视频文件，4=音频文件
		String prefix="/HTP/pages/img/icon/";
		
		String[] postfix={
				"folder",
				"zip",
				"rar",
				"txt",
				"doc",
				"docx",
				"xls",
				"xlsx",
				"ppt",
				"pptx",
				"pdf",
				
				"bmp",
				"jpg",
				"jpeg",
				"png",
				"gif",
				
				"avi",
				"mov",
				"mpg",
				"mpeg",
				"ram",
				"qt",
		
				"mp3",
				"wav",
		};
		String[] img={
				"icon_folder.png",
				"icon_zip.png",
				"icon_zip.png",
				"icon_txt.png",
				"icon_word.png",
				"icon_word.png",
				"icon_xls.png",
				"icon_xls.png",
				"icon_ppt.png",
				"icon_ppt.png",
				"icon_pdf.png",
				
				"icon_image.png",
				"icon_image.png",
				"icon_image.png",
				"icon_image.png",
				"icon_image.png",
				
				"icon_video.png",
				"icon_video.png",
				"icon_video.png",
				"icon_video.png",
				"icon_video.png",
				"icon_video.png",
				
				"icon_music.png",
				"icon_music.png"
		};
		int[] category={
				0,
				0,
				0,
				1,
				1,
				1,
				1,
				1,
				1,
				1,
				1,
				
				2,
				2,
				2,
				2,
				2,
				
				3,
				3,
				3,
				3,
				3,
				3,
		
				4,
				4
		};
		
		for(int i=0;i<postfix.length;i++){
			Tb_file_type file_type=new Tb_file_type();
			file_type.setId(StringUtil.getUUID());
			file_type.setPostfix(postfix[i]);
			file_type.setImg(prefix+img[i]);
			file_type.setCategory(category[i]);
			file_type.setTimestamp(String.valueOf(System.currentTimeMillis()));
			fileType_management_service.addFileType(file_type);
		}
	}
	
	private void addTestAdmin(){
		Tb_user user;
		int index;
		for(int i=0;i<10;i++){
			//建立10个测试管理员用户
			user=new Tb_user();
			user.setId(StringUtil.getUUID());
			index=i+1;
			user.setEmail("admin"+index+"@bjtu.edu.cn");
			user.setImg("default");
			user.setNickname("admin"+index);
			user.setPassword(MD5Util.MD5("123456"));
			user.setRegister_time(DateUtil.getCurrentDate());
			user.setRole(1);
			user.setTotal_storage(0);
			user.setUsed_storage(0);
			login_register_service.register(user);
		}
	}
	
	private void addTestUser(){
		Tb_user user;
		int index;
		for(int i=0;i<60;i++){
			//建立60个测试普通用户
			user=new Tb_user();
			user.setId(StringUtil.getUUID());
			index=i+1;
			user.setEmail("user"+index+"@bjtu.edu.cn");
			user.setImg("default");
			user.setNickname("user"+index);
			user.setPassword(MD5Util.MD5("123456"));
			user.setRegister_time(DateUtil.getCurrentDate());
			user.setRole(0);
			user.setTotal_storage(10240);
			user.setUsed_storage(0);
			login_register_service.register(user);
		}
	}

	public ILoginRegisterService getLogin_register_service() {
		return login_register_service;
	}

	public void setLogin_register_service(
			ILoginRegisterService login_register_service) {
		this.login_register_service = login_register_service;
	}

	public IAdminManagementService getAdmin_management_service() {
		return admin_management_service;
	}

	public void setAdmin_management_service(
			IAdminManagementService admin_management_service) {
		this.admin_management_service = admin_management_service;
	}

	public IFileTypeManagementService getFileType_management_service() {
		return fileType_management_service;
	}

	public void setFileType_management_service(
			IFileTypeManagementService fileType_management_service) {
		this.fileType_management_service = fileType_management_service;
	}
	
}
