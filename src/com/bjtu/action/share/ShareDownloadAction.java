package com.bjtu.action.share;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_share;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.fileType_management.IFileTypeManagementService;
import com.bjtu.service.share.IShareService;
import com.bjtu.util.share.ShareParam;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class ShareDownloadAction extends ActionSupport{
	private String show_name;
	private String img;
	private String file_id;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String getShow_name() {
		return show_name;
	}

	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
}
