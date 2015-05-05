package com.bjtu.model.bo;

public class TreeNode {
	private int id;       //节点id
	private int pId;      //父节点id
	private String name;     //节点名称
	private String group_id;    //群组id,当节点为组员是该值为""
	private String user_id;  //用户id,父节点为""
	private boolean open;	 //是否在页面展开
	
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
