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
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：插入群组成员
	 * @param member
	 */
	@Override
	public void insert(Tb_member member) {
		dao_util.insert(member);
	}

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：根据id查询群组成员
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
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：删除群组成员
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
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：获取群组成员列表
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
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：根据group_id和user_id获取群组成员
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
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：根据group_id删除群组成员
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
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：根据user_id查询群组成员
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
