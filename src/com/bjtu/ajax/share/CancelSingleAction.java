package com.bjtu.ajax.share;

import com.bjtu.service.share.IShareService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class CancelSingleAction extends ActionSupport{
	private String share_id; 
	
	private IShareService share_service;
	
	@Override
	public String execute() throws Exception {
		share_service.delete(share_id);
		return SUCCESS;
	}

	@JSON(serialize=false)
	public String getShare_id() {
		return share_id;
	}

	public void setShare_id(String share_id) {
		this.share_id = share_id;
	}

	@JSON(serialize=false)
	public IShareService getShare_service() {
		return share_service;
	}

	public void setShare_service(IShareService share_service) {
		this.share_service = share_service;
	}
	
}
