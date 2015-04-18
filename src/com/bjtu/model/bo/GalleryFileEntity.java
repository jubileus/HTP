package com.bjtu.model.bo;

public class GalleryFileEntity {
	//页面上显示的文件名称
	private String name;
	//文件图标路径
	private String img;
	//页面上文件外层标签的id
	private String file_id;
	//页面上文件勾选框标签的id
	private String check_id;
	//页面上文件名称显示区域外层标签的id
	private String file_name_id;
	//页面上文件名称显示区域标签的id
	private String show_name_id;
	//页面上文件名图标标签的id
	private String img_id;
	//文件右击菜单的class
	private String right_class;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getCheck_id() {
		return check_id;
	}
	public void setCheck_id(String check_id) {
		this.check_id = check_id;
	}
	public String getFile_name_id() {
		return file_name_id;
	}
	public void setFile_name_id(String file_name_id) {
		this.file_name_id = file_name_id;
	}
	public String getShow_name_id() {
		return show_name_id;
	}
	public void setShow_name_id(String show_name_id) {
		this.show_name_id = show_name_id;
	}
	public String getImg_id() {
		return img_id;
	}
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	public String getRight_class() {
		return right_class;
	}
	public void setRight_class(String right_class) {
		this.right_class = right_class;
	}
	
}
