package com.bjtu.ajax.share;

import java.net.URLDecoder;
import java.util.StringTokenizer;

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
public class GroupShareAction extends ActionSupport{
	private String group_id_list;
	private String file_id;
	
	private IShareService share_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        
        Tb_share share;
        String group_id;
        StringTokenizer st=new StringTokenizer(group_id_list,"_");
        while(st.hasMoreElements()){
        	//循环存入群组分享信息
        	group_id=st.nextToken();
        	share=new Tb_share();
            share.setCreator_id(user.getId());
            share.setFile_id(file_id);
            share.setGroup_id(group_id);
            share.setId(StringUtil.getUUID());
            share.setShare_date(DateUtil.getCurrentDateTime());
            share.setTimestamp(String.valueOf(System.currentTimeMillis()));
            share.setType(3);
            share.setShare_code("");
            share.setShow_name(share_service.getFileShowName(file_id));
            share.setPostfix(share_service.getFilePostfix(file_id));
            share_service.insert(share);
        }
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getGroup_id_list() {
		return group_id_list;
	}

	public void setGroup_id_list(String group_id_list) {
		this.group_id_list = group_id_list;
	}

	@JSON(serialize=false)
	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
	@JSON(serialize=false)
	public IShareService getShare_service() {
		return share_service;
	}

	public void setShare_service(IShareService share_service) {
		this.share_service = share_service;
	}
	
}
