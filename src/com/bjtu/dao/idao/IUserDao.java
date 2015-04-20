package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_user;

public interface IUserDao {
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-03-27
	 * 功能：插入用户
	 * @param user
	 */
	public void insert(Tb_user user);
		
	/**
	 * @author 苏国伟
	 * 编写日期：2015-03-27
	 * 功能：根据id查询用户
	 * @param id
	 */
    public Tb_user getById(String id);
		
	/**
	 * @author 苏国伟
	 * 编写日期：2015-03-27
	 * 功能：根据email,password查询用户
	 * @param user
	 */
    public Tb_user getByEmailPassword(Tb_user user);
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-03-27
	 * 功能：根据email查询用户
	 * @param user
	 */
    public Tb_user getByEmail(Tb_user user);
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-13
	 * 功能：根据role查询用户
	 * @param user
	 */
    public List getByRole(Tb_user user);
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-13
	 * 功能：删除用户
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-15
	 * 功能：分页查询用户信息
	 * @param index,num,nickname
	 */
	public List getByPage(int index,int num,String nickname);
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-15
	 * 功能：查询用户信息总数
	 * @param num,nickname
	 */
	public int getPageNum(int num,String nickname);
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-15
	 * 功能：更新用户状态
	 * @param user
	 */
	public void updateStatus(Tb_user user);
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-15
	 * 功能：更新用户总存储空间
	 * @param user
	 */
	public void updateTotalStorage(Tb_user user);
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-20
	 * 功能：更新用户已用存储空间
	 * @param user
	 */
	public void updateUsedStorage(Tb_user user);
}
