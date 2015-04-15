package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_file_type;

public interface IFileTypeDao {
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ������ļ�����
	 * @param file_type
	 */
	public void insert(Tb_file_type file_type);
		
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ���ҳ��ѯ�ļ�����
	 * @param index,num,postfix
	 */
	public List getByPage(int index,int num,String postfix);
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ���ѯ�ļ���������
	 * @param num,postfix
	 */
	public int getPageNum(int num,String postfix);
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ����ݺ�׺��ȡ�ļ�������Ϣ
	 * @param file_type
	 */
	public Tb_file_type getFileTypeByPostfix(Tb_file_type file_type);
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ�ɾ���ļ�����
	 * @param id
	 */
	public void delete(String id);
}
