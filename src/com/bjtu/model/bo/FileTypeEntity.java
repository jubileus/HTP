package com.bjtu.model.bo;

public class FileTypeEntity {
	//����
	private String id;
	//��׺
	private String postfix;
	//ͼƬ·��
	private String img;
	//��ǰ�ļ���������
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
