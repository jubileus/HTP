package com.bjtu.service.fileType_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bjtu.dao.idao.IFileTypeDao;
import com.bjtu.model.bo.FileTypeEntity;
import com.bjtu.model.pojo.Tb_file_type;

public class FileTypeManagementServiceImp implements IFileTypeManagementService{

	private IFileTypeDao file_type_dao;
	public IFileTypeDao getFile_type_dao() {
		return file_type_dao;
	}
	public void setFile_type_dao(IFileTypeDao file_type_dao) {
		this.file_type_dao = file_type_dao;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：添加文件类型信息
	 * @param file_type
	 */
	@Override
    public void addFileType(Tb_file_type file_type){
		file_type_dao.insert(file_type);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：分页查询文件类型信息
	 * @param index,num
	 */
	@Override
	public List getFileTypeByPage(int index, int num,String postfix) {
		return file_type_dao.getByPage(index, num,postfix);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：查询文件类型总数
	 * @param num
	 */
	@Override
	public int getFileTypePageCount(int num,String postfix) {
		return file_type_dao.getPageNum(num,postfix);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：根据文档类型对应数字获取该类型中文名称
	 * @param num
	 */
	private String getFileTypeNameByNumber(int num) {
		switch(num) 
		{ 
			case 1: 
				return "文档文件"; 
			case 2: 
				return "图片文件"; 
			case 3: 
				return "视频文件"; 
			case 4: 
				return "音频文件"; 
			default: 
				return "普通文件"; 
		} 
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：将Tb_file_type对象的List转化为FileTypeEntity对象的List加以返回
	 * @param file_type_list
	 */
	@Override
	public List convertToFileTypeEntityList(List<Tb_file_type> file_type_list) {
		//声明结果List
		List<FileTypeEntity> rs=new ArrayList<FileTypeEntity>();
		
		Tb_file_type tmp;
		FileTypeEntity fte;
		if(file_type_list!=null&&file_type_list.size()>0){
			for(int i=0;i<file_type_list.size();i++){
				tmp=file_type_list.get(i);
				fte=new FileTypeEntity();
				//存入id
				fte.setId(tmp.getId());
				//存入图片路径
				fte.setImg(tmp.getImg());
				//存入后缀
				fte.setPostfix(tmp.getPostfix());
				//存入分类对应中文名称
				fte.setCategory_name(getFileTypeNameByNumber(tmp.getCategory()));
				//将实体存入结果List
				rs.add(fte);
			}
		}
		return rs;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-14
	 * 功能：检测后缀是否已存在
	 * @param file_type
	 */
	@Override
	public int ifPostfixExist(Tb_file_type file_type) {
		if(file_type_dao.getFileTypeByPostfix(file_type)==null){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
   	 * @author 刘庶
   	 * 编写日期：2015-4-13
   	 * 功能：删除文件类型
   	 * @param id
   	 */
	@Override
	public void deleteFileType(String id) {
		file_type_dao.delete(id);
	}
	
	/**
   	 * @author 刘庶
   	 * 编写日期：2015-4-13
   	 * 功能：获取id为后缀名，value为图片路径的HashMap
   	 */
	@Override
	public HashMap<String, String> getImgMap() {
		List<Tb_file_type> type_list=file_type_dao.getAll();
		HashMap<String, String> img_map=new HashMap<String, String>();
		Tb_file_type tmp;
		if(type_list!=null&&type_list.size()>0){
			for(int i=0;i<type_list.size();i++){
				tmp=type_list.get(i);
				img_map.put(tmp.getPostfix(), tmp.getImg());
			}
		}
		return img_map;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-29
	 * 功能：根据category获取文件类型
	 */
	@Override
	public List getByCategory(int category) {
		return file_type_dao.getByCategory(category);
	}
	
}
