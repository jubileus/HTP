package com.bjtu.ajax.share;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.pojo.Tb_share;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.share.IShareService;
import com.bjtu.util.common.DateUtil;
import com.bjtu.util.common.StringUtil;
import com.bjtu.util.share.ShareParam;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class PrivateShareAction extends ActionSupport{
	private String file_id;
	private String share_link;
	private String share_code;
	
	private IShareService share_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        Tb_share share=new Tb_share();
        share.setCreator_id(user.getId());
        share.setFile_id(file_id);
        share.setGroup_id("");
        share.setId(StringUtil.getUUID());
        share.setShare_date(DateUtil.getCurrentDateTime());
        share.setTimestamp(String.valueOf(System.currentTimeMillis()));
        share.setType(2);
        share.setShare_code(StringUtil.getRandomPassword());
        share.setShow_name(share_service.getFileShowName(file_id));
        share.setPostfix(share_service.getFilePostfix(file_id));
        share_service.insert(share);
        
        share_link=ShareParam.SHARE_PATH+"HTP/ShareViewAction.action?id="+share.getId();
        share_code=share.getShare_code();
        
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getShare_link() {
		return share_link;
	}

	public void setShare_link(String share_link) {
		this.share_link = share_link;
	}
	
	public String getShare_code() {
		return share_code;
	}

	public void setShare_code(String share_code) {
		this.share_code = share_code;
	}

	@JSON(serialize=false)
	public IShareService getShare_service() {
		return share_service;
	}

	public void setShare_service(IShareService share_service) {
		this.share_service = share_service;
	}
	
}
