package com.bjtu.model.pojo;

public class Tb_file_type {
	//主键
	private String id;
	//后缀
	private String postfix;
	//分类：0=普通文件，1=文档文件，2=图片文件，3=视频文件，4=音频文件
	private int category;
	//图片路径
	private String img;
	//时间戳
	private String timestamp;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPostfix() {
		return postfix;
	}
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
