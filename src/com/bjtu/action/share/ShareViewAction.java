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
public class ShareViewAction extends ActionSupport{
	private String id;
	private String show_name;
	private String img;
	private String file_id;
	private String user_name;
	private String share_code;
	
	private IShareService share_service;
	private IFileTypeManagementService fileType_management_service;
	
	@Override
	public String execute() throws Exception {
		Tb_share share=share_service.getById(id);
		Tb_file file=share_service.getFileById(share.getFile_id());
		if(file==null){
			//文件已经被删除，删除该文件所有分享数据
			share_service.deleteByFileId(share.getFile_id());
			return ERROR;
		}else{
			//获取包含文件图标信息的HashMap
	        HashMap<String, String> img_map=fileType_management_service.getImgMap();
	        show_name=file.getShow_name()+"."+file.getPostfix();
	        file_id=file.getId();
	        img=img_map.get(file.getPostfix());
	        if(img==null){
    			//系统中没有此类型文件，显示默认图标
    			img="/HTP/pages/img/icon/icon_file.png";
    		}
			if(share.getType()==1){//公开分享
		        return SUCCESS;
			}else{//私密分享
				Tb_user user=share_service.getUserById(file.getUser_id());
				user_name=user.getNickname();
				share_code=share.getShare_code();
				return INPUT;
			}
		}
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getShare_code() {
		return share_code;
	}

	public void setShare_code(String share_code) {
		this.share_code = share_code;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IShareService getShare_service() {
		return share_service;
	}

	public void setShare_service(IShareService share_service) {
		this.share_service = share_service;
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

	public IFileTypeManagementService getFileType_management_service() {
		return fileType_management_service;
	}

	public void setFileType_management_service(
			IFileTypeManagementService fileType_management_service) {
		this.fileType_management_service = fileType_management_service;
	}
	
}
