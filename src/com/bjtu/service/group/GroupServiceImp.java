package com.bjtu.service.group;

import java.util.List;

import com.bjtu.dao.idao.IGroupDao;
import com.bjtu.dao.idao.IMemberDao;
import com.bjtu.dao.idao.IUserDao;
import com.bjtu.model.pojo.Tb_group;
import com.bjtu.model.pojo.Tb_member;
import com.bjtu.model.pojo.Tb_user;

public class GroupServiceImp implements IGroupService{
	
	private IGroupDao group_dao;
	private IMemberDao member_dao;
	private IUserDao user_dao;
	public IGroupDao getGroup_dao() {
		return group_dao;
	}
	public void setGroup_dao(IGroupDao group_dao) {
		this.group_dao = group_dao;
	}
	public IMemberDao getMember_dao() {
		return member_dao;
	}
	public void setMember_dao(IMemberDao member_dao) {
		this.member_dao = member_dao;
	}
	public IUserDao getUser_dao() {
		return user_dao;
	}
	public void setUser_dao(IUserDao user_dao) {
		this.user_dao = user_dao;
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����Ⱥ��
	 * @param group
	 */
	@Override
	public void insert(Tb_group group) {
		group_dao.insert(group);
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����id��ѯȺ��
	 * @param id
	 */
	@Override
	public Tb_group getById(String id) {
		return group_dao.getById(id);
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�ɾ��Ⱥ��
	 * @param id
	 */
	@Override
	public void delete(String id) {
		group_dao.delete(id);		
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ���ȡȺ����Ϣ
	 * @param creator_id
	 */
	@Override
	public List getGroup(String creator_id) {
		return group_dao.getGroup(creator_id);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ��ж�ͬ��Ⱥ���Ƿ����
	 * @param group
	 */
	@Override
	public boolean ifExist(Tb_group group) {
		if(group_dao.getByCreator_id_Name(group)!=null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ��жϴ���ӵ�Ⱥ���Ա�Ƿ����
	 * @param user
	 */
	@Override
	public Tb_user ifMemberExist(Tb_user user) {
		return user_dao.getByEmail(user);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ��жϴ���ӵ�Ⱥ���Ա�Ƿ��Ѿ��ڱ�Ⱥ����
	 * @param member
	 */
	@Override
	public Tb_member ifMemberInGroup(Tb_member member){
		return member_dao.getByGroupId_UserId(member);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����group_id��ȡ��Ա��Ϣ
	 * @param group_id
	 */
	@Override
	public List getMemberByGroupId(String group_id) {
		return member_dao.getMember(group_id);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����Ⱥ���Ա
	 * @param member
	 */
	@Override
	public void insertMember(Tb_member member) {
		member_dao.insert(member);
	}

	
}
