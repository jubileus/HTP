package com.bjtu.model.bo;

public class UserEntity {
	//����
	private String id;
	//����
	private String email;
	//�ǳ�
	private String nickname;
	//ͷ��·��
	private String img;
	//�ܴ洢�ռ�
	private String total_storage;
	//��ʹ�ô洢�ռ�
	private String used_storage;
	//ע��ʱ��
	private String register_time;
	//�޸Ľ�ɫ״̬�Ĳ������������ƣ�role=0ʱstatus_operation=���role=1ʱstatus_operation=ͣ��
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
