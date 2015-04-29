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
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ�����ļ�������Ϣ
	 * @param file_type
	 */
	@Override
    public void addFileType(Tb_file_type file_type){
		file_type_dao.insert(file_type);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ���ҳ��ѯ�ļ�������Ϣ
	 * @param index,num
	 */
	@Override
	public List getFileTypeByPage(int index, int num,String postfix) {
		return file_type_dao.getByPage(index, num,postfix);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ���ѯ�ļ���������
	 * @param num
	 */
	@Override
	public int getFileTypePageCount(int num,String postfix) {
		return file_type_dao.getPageNum(num,postfix);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ������ĵ����Ͷ�Ӧ���ֻ�ȡ��������������
	 * @param num
	 */
	private String getFileTypeNameByNumber(int num) {
		switch(num) 
		{ 
			case 1: 
				return "�ĵ��ļ�"; 
			case 2: 
				return "ͼƬ�ļ�"; 
			case 3: 
				return "��Ƶ�ļ�"; 
			case 4: 
				return "��Ƶ�ļ�"; 
			default: 
				return "��ͨ�ļ�"; 
		} 
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ���Tb_file_type�����Listת��ΪFileTypeEntity�����List���Է���
	 * @param file_type_list
	 */
	@Override
	public List convertToFileTypeEntityList(List<Tb_file_type> file_type_list) {
		//�������List
		List<FileTypeEntity> rs=new ArrayList<FileTypeEntity>();
		
		Tb_file_type tmp;
		FileTypeEntity fte;
		if(file_type_list!=null&&file_type_list.size()>0){
			for(int i=0;i<file_type_list.size();i++){
				tmp=file_type_list.get(i);
				fte=new FileTypeEntity();
				//����id
				fte.setId(tmp.getId());
				//����ͼƬ·��
				fte.setImg(tmp.getImg());
				//�����׺
				fte.setPostfix(tmp.getPostfix());
				//��������Ӧ��������
				fte.setCategory_name(getFileTypeNameByNumber(tmp.getCategory()));
				//��ʵ�������List
				rs.add(fte);
			}
		}
		return rs;
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-4-14
	 * ���ܣ�����׺�Ƿ��Ѵ���
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
   	 * @author ����
   	 * ��д���ڣ�2015-4-13
   	 * ���ܣ�ɾ���ļ�����
   	 * @param id
   	 */
	@Override
	public void deleteFileType(String id) {
		file_type_dao.delete(id);
	}
	
	/**
   	 * @author ����
   	 * ��д���ڣ�2015-4-13
   	 * ���ܣ���ȡidΪ��׺����valueΪͼƬ·����HashMap
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
	 * @author ����
	 * ��д���ڣ�2015-04-29
	 * ���ܣ�����category��ȡ�ļ�����
	 */
	@Override
	public List getByCategory(int category) {
		return file_type_dao.getByCategory(category);
	}
	
}
