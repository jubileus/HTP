package com.bjtu.model.bo;

public class FileTypeEntity {
	//主键
	private String id;
	//后缀
	private String postfix;
	//图片路径
	private String img;
	//当前文件类型名称
	private String category_name;
	
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
}
