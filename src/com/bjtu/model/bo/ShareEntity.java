package com.bjtu.model.bo;

public class ShareEntity {

	private String id;    		//分享id
	private String name;   		//文件名称
	private String img;    		//文件图标
	private String date;   		//分享日期
	private String status; 		//文件分享类型
	private String span_class;  //文件类型对应的span的class
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSpan_class() {
		return span_class;
	}
	public void setSpan_class(String span_class) {
		this.span_class = span_class;
	}
	
	
}
