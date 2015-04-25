package com.bjtu.dao.daoimp;

import java.util.List;

import com.bjtu.dao.idao.IGroupDao;
import com.bjtu.model.pojo.Tb_group;
import com.bjtu.util.dao.DaoUtil;

public class GroupDaoImp implements IGroupDao{

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
	 * ���ܣ�����Ⱥ��
	 * @param group
	 */
	@Override
	public void insert(Tb_group group) {
		dao_util.insert(group);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����id��ѯȺ��
	 * @param id
	 */
	@Override
	public Tb_group getById(String id) {
		String hql = "from Tb_group where id=?";
		Object[] objects = new Object[1];
		objects[0] = id;
		return (Tb_group)dao_util.getSingle(hql, objects);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�ɾ��Ⱥ��
	 * @param id
	 */
	@Override
	public void delete(String id) {
		String hql = "from Tb_group where id=?";
		Object[] objects = new Object[1];
		objects[0] = id;
		Tb_group group= (Tb_group)dao_util.getSingle(hql, objects);
		dao_util.delete(group);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ���ȡȺ����Ϣ
	 * @param creator_id
	 */
	@Override
	public List getGroup(String creator_id) {
		String hql = "from Tb_group where creator_id = ?";
		Object[] objects = new Object[1];
		objects[0]=creator_id;
		
		return dao_util.getList(hql, objects);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-25
	 * ���ܣ�����creator_id��name��ȡ����
	 * @param group
	 */
	@Override
	public Tb_group getByCreator_id_Name(Tb_group group) {
		String hql = "from Tb_group where creator_id = ? and name=?";
		Object[] objects = new Object[2];
		objects[0]=group.getCreator_id();
		objects[1]=group.getName();
		return (Tb_group)dao_util.getSingle(hql, objects);
	}

}
