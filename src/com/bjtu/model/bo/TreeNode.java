package com.bjtu.model.bo;

public class TreeNode {
	private int id;       //�ڵ�id
	private int pId;      //���ڵ�id
	private String name;     //�ڵ�����
	private String group_id;    //Ⱥ��id,���ڵ�Ϊ��Ա�Ǹ�ֵΪ""
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
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
}
