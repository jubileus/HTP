package com.bjtu.model.bo;

public class GroupShareEntity {

	private String id;    		//分享id
	private String name;   		//文件名称
	private String li_class;    //分享对应li的class
	private String date;   		//分享日期
	private String nickname; 	//分享者昵称
	private String file_id;     //分享的文件id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLi_class() {
		return li_class;
	}
	public void setLi_class(String li_class) {
		this.li_class = li_class;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
}
