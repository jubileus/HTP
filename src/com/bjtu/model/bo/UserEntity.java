package com.bjtu.model.bo;

public class UserEntity {
	//主键
	private String id;
	//邮箱
	private String email;
	//昵称
	private String nickname;
	//头像路径
	private String img;
	//总存储空间
	private String total_storage;
	//已使用存储空间
	private String used_storage;
	//注册时间
	private String register_time;
	//修改角色状态的操作的中文名称：role=0时status_operation=激活，role=1时status_operation=停用
	private String status_name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTotal_storage() {
		return total_storage;
	}
	public void setTotal_storage(String total_storage) {
		this.total_storage = total_storage;
	}
	public String getUsed_storage() {
		return used_storage;
	}
	public void setUsed_storage(String used_storage) {
		this.used_storage = used_storage;
	}
	public String getRegister_time() {
		return register_time;
	}
	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
}
