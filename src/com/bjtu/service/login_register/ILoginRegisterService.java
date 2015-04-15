package com.bjtu.service.login_register;

import com.bjtu.model.pojo.Tb_user;

public interface ILoginRegisterService {
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：用户注册
	 * @param user
	 */
    public void register(Tb_user user);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：用户登录
	 * @param user
	 */
    public Tb_user login(Tb_user user);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：检测用户注册所使用的邮箱是否已存在
	 * @param user
	 */
    public int ifEmailExist(Tb_user user);
    
    
}
