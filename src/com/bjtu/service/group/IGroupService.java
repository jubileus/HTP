package com.bjtu.service.group;

import java.util.List;

import com.bjtu.model.pojo.Tb_group;
import com.bjtu.model.pojo.Tb_member;
import com.bjtu.model.pojo.Tb_user;

public interface IGroupService {
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：插入群组
	 * @param group
	 */
	public void insert(Tb_group group);
		
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：根据id查询群组
	 * @param id
	 */
    public Tb_group getById(String id);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：删除群组
	 * @param id
	 */
    public void delete(String id);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：获取群组信息
	 * @param creator_id
	 */
	public List getGroup(String creator_id);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：判断同名群组是否存在
	 * @param group
	 */
	public boolean ifExist(Tb_group group);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：判断待添加的群组成员是否存在
	 * @param user
	 */
	public Tb_user ifMemberExist(Tb_user user);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：判断待添加的群组成员是否已经在本群组中
	 * @param member
	 */
	public Tb_member ifMemberInGroup(Tb_member member);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：根据group_id获取组员信息
	 * @param group_id
	 */
	public List getMemberByGroupId(String group_id);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：插入群组成员
	 * @param member
	 */
	public void insertMember(Tb_member member);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：删除单个群组成员
	 * @param id
	 */
	public void deleteSingleMember(String id);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：根据group_id删除群组成员
	 * @param group_id
	 */
	public void deleteMembers(String group_id);
}
