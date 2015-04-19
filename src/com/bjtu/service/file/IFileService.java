package com.bjtu.service.file;

import java.util.HashMap;
import java.util.List;

import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.bo.PathEntity;
import com.bjtu.model.pojo.Tb_file;

public interface IFileService {

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ������ļ�����
	 * @param user
	 */
	public void insert(Tb_file file);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�����id��ѯ�ļ�
	 * @param id
	 */
	public Tb_file getById(String id);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param num:ÿҳ��ʾ����
	 * @param path:�ļ�·��
	 * @param file_name:�ļ���ʾ����
	 */
    public int getPageCount(int num,String path,String file_name);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 * @param path:�ļ�·��
	 * @param file_name:�ļ���ʾ����
	 */
	public List<Tb_file> getPageData(int num, int index, String path, String file_name);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���Tb_file��Listת��ΪGalleryFileEntity��List
	 * @param file_list
	 * @param img_map
	 */
	public List<GalleryFileEntity> convertToGalleryFileEntity(List<Tb_file> file_list,HashMap<String, String> img_map);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ȡ�û�id��·��ʵ��List
	 * @param user_id
	 * @param path
	 */
	public List<PathEntity> getPathList(String user_id,String path);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯ�ļ������Ƿ����ͬ���ļ�
	 * @param file
	 */
	public boolean ifNewFileExist(Tb_file file);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯ�ļ������Ƿ����ͬ���ļ�,�����򷵻�����ͬ���ļ�������ֵ���������򷵻�0
	 * @param file
	 */
	public int getNewFileNum(Tb_file file);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ��޸��ļ�show_name
	 * @param file
	 */
    public void modifyShowName(Tb_file file);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�ɾ���ļ����ļ���
	 * @param id
	 */
    public void deleteFolderOrFile(String id);
}
