package com.bjtu.service.group;

import java.util.List;

import com.bjtu.model.pojo.Tb_group;
import com.bjtu.model.pojo.Tb_member;
import com.bjtu.model.pojo.Tb_user;

public interface IGroupService {
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����Ⱥ��
	 * @param group
	 */
	public void insert(Tb_group group);
		
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����id��ѯȺ��
	 * @param id
	 */
    public Tb_group getById(String id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�ɾ��Ⱥ��
	 * @param id
	 */
    public void delete(String id);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ���ȡȺ����Ϣ
	 * @param creator_id
	 */
	public List getGroup(String creator_id);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ��ж�ͬ��Ⱥ���Ƿ����
	 * @param group
	 */
	public boolean ifExist(Tb_group group);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ��жϴ���ӵ�Ⱥ���Ա�Ƿ����
	 * @param user
	 */
	public Tb_user ifMemberExist(Tb_user user);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ��жϴ���ӵ�Ⱥ���Ա�Ƿ��Ѿ��ڱ�Ⱥ����
	 * @param member
	 */
	public Tb_member ifMemberInGroup(Tb_member member);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����group_id��ȡ��Ա��Ϣ
	 * @param group_id
	 */
	public List getMemberByGroupId(String group_id);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����Ⱥ���Ա
	 * @param member
	 */
	public void insertMember(Tb_member member);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�ɾ������Ⱥ���Ա
	 * @param id
	 */
	public void deleteSingleMember(String id);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����group_idɾ��Ⱥ���Ա
	 * @param group_id
	 */
	public void deleteMembers(String group_id);
}
