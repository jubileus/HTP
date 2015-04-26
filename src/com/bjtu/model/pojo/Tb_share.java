package com.bjtu.model.pojo;

public class Tb_share {
	private String id;           //主键
	private String creator_id;   //分享者id
	private String file_id;      //文件id
	
	private int type;            //分享类型:1=公开分享，2=私密分享，3=群组分享
	
	private String share_date;   //分享日期
	private String timestamp;    //时间戳
	
	private String group_id;     //群组id(当不是群组分享时，值为"")
	private String share_code;   //私密分享时的提取码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getShare_date() {
		return share_date;
	}

	public void setShare_date(String share_date) {
		this.share_date = share_date;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getShare_code() {
		return share_code;
	}

	public void setShare_code(String share_code) {
		this.share_code = share_code;
	}
	
}
