package com.bjtu.model.bo;

public class TreeNode {
	private int id;       //�ڵ�id
	private int pId;      //���ڵ�id
	private String name;     //�ڵ�����
	private String click;    //�������
	private String user_id;  //�û�id,���ڵ�Ϊ""
	private boolean open;	 //�Ƿ���ҳ��չ��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
}
