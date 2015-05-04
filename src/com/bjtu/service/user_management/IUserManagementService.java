package com.bjtu.service.user_management;

import java.util.List;

import com.bjtu.model.pojo.Tb_user;

public interface IUserManagementService {

	/**
	 * @author 刘庶
	 * 编写日期：2015-4-15
	 * 功能：分页查询用户信息
	 * @param index,num,nickname
	 */
    public List getUserTypeByPage(int index,int num,String nickname);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-15
	 * 功能：查询用户信息总数
	 * @param num,nickname
	 */
    public int getUserTypePageCount(int num,String nickname);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-15
	 * 功能：将Tb_user对象的List转化为UserEntity对象的List加以返回
	 * @param user_list
	 */
    public List convertToUserEntityList(List<Tb_user> user_list);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-15
	 * 功能：更新用户状态
	 * @param user
	 */
	public void modifyStatus(Tb_user user);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-15
	 * 功能：更新总存储空间
	 * @param user
	 */
	public void modifyTotalStorage(Tb_user user);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-15
	 * 功能：根据id获得用户信息
	 * @param id
	 */
	public Tb_user getUserById(String id);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-4
	 * 功能：更新用户昵称
	 * @param user
	 */
	public void modifyNickname(Tb_user user);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-4
	 * 功能：更新用户密码
	 * @param user
	 */
	public void modifyPassword(Tb_user user);
}
