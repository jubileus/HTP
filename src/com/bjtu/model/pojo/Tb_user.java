package com.bjtu.model.pojo;

public class Tb_user {
	//����
	private String id;
	//����
	private String email;
	//����
	private String password;
	//�ǳ�
	private String nickname;
	//ͷ��·��
	private String img;
	//�ܴ洢�ռ�
	private double total_storage;
	//��ʹ�ô洢�ռ�
	private double used_storage;
	//ע��ʱ��
	private String register_time;
	//��ɫ��0=��ͨ�û���1=����Ա
	private int role;
	//��ɫ��0=ͣ�ã�1=����
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
