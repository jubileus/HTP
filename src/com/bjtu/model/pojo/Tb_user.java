package com.bjtu.model.pojo;

public class Tb_user {
	//主键
	private String id;
	//邮箱
	private String email;
	//密码
	private String password;
	//昵称
	private String nickname;
	//头像路径
	private String img;
	//总存储空间
	private double total_storage;
	//已使用存储空间
	private double used_storage;
	//注册时间
	private String register_time;
	//角色：0=普通用户，1=管理员
	private int role;
	//角色：0=停用，1=正常
	private int status;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public double getTotal_storage() {
		return total_storage;
	}
	public void setTotal_storage(double total_storage) {
		this.total_storage = total_storage;
	}
	public double getUsed_storage() {
		return used_storage;
	}
	public void setUsed_storage(double used_storage) {
		this.used_storage = used_storage;
	}
	public String getRegister_time() {
		return register_time;
	}
	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
