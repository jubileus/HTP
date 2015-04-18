package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_file;

public interface IFileDao {
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ������ļ�����
	 * @param file
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
    public List getPageData(int num,int index,String path,String file_name);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�����user_id��hdfs_name��ѯ�ļ�
	 * @param user_id
	 * @param hdfs_name
	 */
    public Tb_file getByUserIdAndHDFSName(String user_id,String hdfs_name);
}
