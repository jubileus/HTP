package com.bjtu.model.bo;

public class ListFileEntity {
	//文件id
	private String id;
	//页面上显示的文件名称
	private String name;
	//文件图标路径
	private String img;
	//文件大小，单位是MB
	private String size;
	//文件上传日期
	private String date;
	//文件下载操作
	private String download_file;
	//文件分享操作
	private String share_file;
	//文件删除操作
	private String delete_file;
	//文件重命名操作
	private String rename_file;
	//页面上文件名图标标签的id
	private String img_id;
	//删除文件超链接的id
	private String deleteFile_id;
	//文件复选框id
	private String check_id;
	//文件分享超链接id
	private String shareFile_id;
	//下载文件超链接的id
	private String downloadFile_id;
	//文件名div的id
	private String name_td_id;
	//重命名文件超链接的id
	private String renameFile_id;
	
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDownload_file() {
		return download_file;
	}
	public void setDownload_file(String download_file) {
		this.download_file = download_file;
	}
	public String getShare_file() {
		return share_file;
	}
	public void setShare_file(String share_file) {
		this.share_file = share_file;
	}
	public String getDelete_file() {
		return delete_file;
	}
	public void setDelete_file(String delete_file) {
		this.delete_file = delete_file;
	}
	public String getRename_file() {
		return rename_file;
	}
	public void setRename_file(String rename_file) {
		this.rename_file = rename_file;
	}
	public String getImg_id() {
		return img_id;
	}
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	public String getDeleteFile_id() {
		return deleteFile_id;
	}
	public void setDeleteFile_id(String deleteFile_id) {
		this.deleteFile_id = deleteFile_id;
	}
	public String getCheck_id() {
		return check_id;
	}
	public void setCheck_id(String check_id) {
		this.check_id = check_id;
	}
	public String getShareFile_id() {
		return shareFile_id;
	}
	public void setShareFile_id(String shareFile_id) {
		this.shareFile_id = shareFile_id;
	}
	public String getDownloadFile_id() {
		return downloadFile_id;
	}
	public void setDownloadFile_id(String downloadFile_id) {
		this.downloadFile_id = downloadFile_id;
	}
	public String getName_td_id() {
		return name_td_id;
	}
	public void setName_td_id(String name_td_id) {
		this.name_td_id = name_td_id;
	}
	public String getRenameFile_id() {
		return renameFile_id;
	}
	public void setRenameFile_id(String renameFile_id) {
		this.renameFile_id = renameFile_id;
	}
	
}
