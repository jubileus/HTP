package com.bjtu.model.pojo;

public class Tb_share {
	private String id;           //����
	private String creator_id;   //������id
	private String file_id;      //�ļ�id
	private String show_name;    //�ļ���ʾ����
	private String postfix;      //�ļ���׺
	
	private int type;            //��������:1=��������2=˽�ܷ���3=Ⱥ�����
	
	private String share_date;   //��������
	private String timestamp;    //ʱ���
	
	private String group_id;     //Ⱥ��id(������Ⱥ�����ʱ��ֵΪ"")
	private String share_code;   //˽�ܷ���ʱ����ȡ��

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

	public String getShow_name() {
		return show_name;
	}

	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	
}
