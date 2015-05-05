package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_share;

public interface IShareDao {

	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-26
	 * ���ܣ������������
	 * @param share
	 */
	public void insert(Tb_share share);
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����id��ѯ��������
	 * @param id
	 */
    public Tb_share getById(String id);
    
    /**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����idɾ��һ����������
	 * @param id
	 */
    public void delete(String id);
    
    /**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param file_name:ģ����ѯ�ļ�����
	 */
    public int getPageCount(String user_id,int num,String file_name);

    /**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 * @param file_name:ģ����ѯ�ļ�����
	 */
    public List getPageData(String user_id,int num,int index,String file_name);
    
    /**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ��޸���ʾ����
	 * @param share
	 */
	public void modifyShowname(Tb_share share);
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����creator_idɾ����������
	 * @param creator_id
	 */
    public void deleteByCreatorId(String creator_id);
    
    /**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����group_idɾ����������
	 * @param group_id
	 */
    public void deleteByGroupId(String group_id);
}
