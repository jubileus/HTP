package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_member;

public interface IMemberDao {
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����Ⱥ���Ա
	 * @param member
	 */
	public void insert(Tb_member member);
		
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����id��ѯȺ���Ա
	 * @param id
	 */
    public Tb_member getById(String id);
    
    /**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�ɾ��Ⱥ���Ա
	 * @param id
	 */
    public void delete(String id);
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ���ȡȺ���Ա�б�
	 * @param group_id
	 */
	public List getMember(String group_id);
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����group_id��user_id��ȡȺ���Ա
	 * @param member
	 */
	public Tb_member getByGroupId_UserId(Tb_member member);
}
