package com.bjtu.service.admin_management;

import java.util.List;

import com.bjtu.model.pojo.Tb_user;

public interface IAdminManagementService {
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：查找所有管理员角色的用户信息
	 */
    public List<Tb_user> getAllAdmin();
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：添加管理员
	 * @param user
	 */
    public void addAdmin(Tb_user user);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：删除管理员
	 * @param id
	 */
    public void deleteAdmin(String id);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：根据昵称模糊匹配查找管理员
	 * @param user,get_all
	 */
    public List<Tb_user> searchAdmin(Tb_user user,boolean get_all);
    
}
