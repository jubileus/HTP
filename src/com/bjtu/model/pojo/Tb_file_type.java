package com.bjtu.model.pojo;

public class Tb_file_type {
	//����
	private String id;
	//��׺
	private String postfix;
	//���ࣺ0=��ͨ�ļ���1=�ĵ��ļ���2=ͼƬ�ļ���3=��Ƶ�ļ���4=��Ƶ�ļ�
	private int category;
	//ͼƬ·��
	private String img;
	//ʱ���
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
