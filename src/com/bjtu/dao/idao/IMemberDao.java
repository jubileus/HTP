package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_member;

public interface IMemberDao {
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：插入群组成员
	 * @param member
	 */
	public void insert(Tb_member member);
		
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：根据id查询群组成员
	 * @param id
	 */
    public Tb_member getById(String id);
    
    /**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：删除群组成员
	 * @param id
	 */
    public void delete(String id);
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：获取群组成员列表
	 * @param group_id
	 */
	public List getMember(String group_id);
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：根据group_id和user_id获取群组成员
	 * @param member
	 */
	public Tb_member getByGroupId_UserId(Tb_member member);
}
