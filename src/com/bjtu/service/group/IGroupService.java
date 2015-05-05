package com.bjtu.service.group;

import java.util.List;

import com.bjtu.model.bo.GroupShareEntity;
import com.bjtu.model.pojo.Tb_group;
import com.bjtu.model.pojo.Tb_member;
import com.bjtu.model.pojo.Tb_share;
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
	 * @param user_id
	 */
	public List getGroup(String user_id);
	
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
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����id��ѯ��Ա
	 * @param id
	 */
    public Tb_member getMemberById(String id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����creator_idɾ������
	 * @param creator_id
	 */
    public void deleteByCreatorId(String creator_id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����group_idɾ������
	 * @param group_id
	 */
    public void deleteByGroupId(String group_id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����creator_id��ȡȺ����Ϣ
	 * @param creator_id
	 */
	public List getGroupByCreatorId(String creator_id);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param group_id:Ⱥ��id
	 * @param num:ÿҳ��ʾ����
	 */
    public int getPageCount(String group_id,int num);

    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param group_id:Ⱥ��id
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 */
    public List getPageData(String group_id,int num,int index);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���Tb_share��Listת��ΪGroupShareEntity��List
	 * @param share_list
	 * @param user_id
	 */
    public List<GroupShareEntity> convertToGroupShareEntity(List<Tb_share> share_list,String user_id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����id��ȡ����
	 * @param id
	 */
    public Tb_share getShareById(String id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����idɾ����������
	 * @param id
	 */
    public void deleteShare(String id);
}
