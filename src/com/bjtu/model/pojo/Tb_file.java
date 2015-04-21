package com.bjtu.model.pojo;

public class Tb_file {
	//主键
	private String id;
	//创建用户对应id
	private String user_id;
	//文件显示名称
	private String show_name;
	//文件在HDFS中的名称
	private String hdfs_name;
	//文件名后缀
	private String postfix;
	//文件在HDFS中的路径
	private String path;
	//是否是文件夹：0=不是文件夹，1=是文件夹
	private int is_folder;
	//是否上传完毕：0=上传未完毕，1=上传完毕
	private int is_complete;
	//创建时间
	private String create_time;
	//时间戳
	private String timestamp;
	//文件的大小，单位是MB
	private double size_mb;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getShow_name() {
		return show_name;
	}
	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}
	public String getHdfs_name() {
		return hdfs_name;
	}
	public void setHdfs_name(String hdfs_name) {
		this.hdfs_name = hdfs_name;
	}
	public String getPostfix() {
		return postfix;
	}
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getIs_folder() {
		return is_folder;
	}
	public void setIs_folder(int is_folder) {
		this.is_folder = is_folder;
	}
	public int getIs_complete() {
		return is_complete;
	}
	public void setIs_complete(int is_complete) {
		this.is_complete = is_complete;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public double getSize_mb() {
		return size_mb;
	}
	public void setSize_mb(double size_mb) {
		this.size_mb = size_mb;
	}
	
}
