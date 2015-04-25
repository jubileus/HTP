package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_group;

public interface IGroupDao {
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����Ⱥ��
	 * @param group
	 */
	public void insert(Tb_group group);
		
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����id��ѯȺ��
	 * @param id
	 */
    public Tb_group getById(String id);
    
    /**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�ɾ��Ⱥ��
	 * @param id
	 */
    public void delete(String id);
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ���ȡȺ����Ϣ
	 * @param creator_id
	 */
	public List getGroup(String creator_id);
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����creator_id��name��ȡ����
	 * @param group
	 */
	public Tb_group getByCreator_id_Name(Tb_group group);
}
