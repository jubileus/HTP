package com.bjtu.dao.daoimp;

import java.util.List;

import com.bjtu.dao.idao.IMemberDao;
import com.bjtu.model.pojo.Tb_member;
import com.bjtu.util.dao.DaoUtil;

public class MemberDaoImp implements IMemberDao{
	
	private DaoUtil dao_util;		
	public DaoUtil getDao_util() {
		return dao_util;
	}
	public void setDao_util(DaoUtil dao_util) {
		this.dao_util = dao_util;
	}

	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����Ⱥ���Ա
	 * @param member
	 */
	@Override
	public void insert(Tb_member member) {
		dao_util.insert(member);
	}

	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����id��ѯȺ���Ա
	 * @param id
	 */
	@Override
	public Tb_member getById(String id) {
		String hql = "from Tb_member where id=?";
		Object[] objects = new Object[1];
		objects[0] = id;
		return (Tb_member)dao_util.getSingle(hql, objects);
	}

	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�ɾ��Ⱥ���Ա
	 * @param id
	 */
	@Override
	public void delete(String id) {
		String hql = "from Tb_member where id=?";
		Object[] objects = new Object[1];
		objects[0] = id;
		Tb_member member= (Tb_member)dao_util.getSingle(hql, objects);
		dao_util.delete(member);
	}

	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ���ȡȺ���Ա�б�
	 * @param group_id
	 */
	@Override
	public List getMember(String group_id) {
		String hql = "from Tb_member where group_id = ?";
		Object[] objects = new Object[1];
		objects[0]=group_id;
		
		return dao_util.getList(hql, objects);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����group_id��user_id��ȡȺ���Ա
	 * @param member
	 */
	@Override
	public Tb_member getByGroupId_UserId(Tb_member member) {
		String hql = "from Tb_member where group_id = ? and user_id=?";
		Object[] objects = new Object[2];
		objects[0]=member.getGroup_id();
		objects[1]=member.getUser_id();
		return (Tb_member)dao_util.getSingle(hql, objects);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����group_idɾ��Ⱥ���Ա
	 * @param group_id
	 */
	@Override
	public void deleteByGroupId(String group_id) {
		String hql = "from Tb_member where group_id = ?";
		Object[] objects = new Object[1];
		objects[0]=group_id;
		dao_util.delete(dao_util.getList(hql, objects));
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����user_id��ѯȺ���Ա
	 * @param user_id
	 */
	@Override
	public List getByUserId(String user_id) {
		String hql = "from Tb_member where user_id = ?";
		Object[] objects = new Object[1];
		objects[0]=user_id;
		return dao_util.getList(hql, objects);
	}

}
