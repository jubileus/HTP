package com.bjtu.ajax.share;

import java.util.StringTokenizer;

import com.bjtu.service.share.IShareService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class CancelMultiAction extends ActionSupport{
	private String id_list; 
	
	private IShareService share_service;
	
	@Override
	public String execute() throws Exception {
		String id;
		StringTokenizer st=new StringTokenizer(id_list,"_");
		//循环取消分享
		while(st.hasMoreElements()){
			id=st.nextToken();
			share_service.delete(id);
		}
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
	public IShareService getShare_service() {
		return share_service;
	}

	public void setShare_service(IShareService share_service) {
		this.share_service = share_service;
	}
	
}
