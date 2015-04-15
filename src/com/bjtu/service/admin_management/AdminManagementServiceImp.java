package com.bjtu.service.admin_management;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.dao.idao.IUserDao;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.admin_management.IAdminManagementService;

public class AdminManagementServiceImp implements IAdminManagementService{

	private IUserDao user_dao;
	public IUserDao getUser_dao() {
		return user_dao;
	}
	public void setUser_dao(IUserDao user_dao) {
		this.user_dao = user_dao;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：查找所有管理员角色的用户信息
	 */
	@Override
	public List<Tb_user> getAllAdmin() {
		Tb_user user=new Tb_user();
		user.setRole(1);
		return user_dao.getByRole(user);
	}
	
	 /**
		 * @author 刘庶
		 * 编写日期：2015-4-13
		 * 功能：添加管理员
		 * @param user
		 */
	@Override
	public void addAdmin(Tb_user user) {
		user_dao.insert(user);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：删除管理员
	 * @param id
	 */
	@Override
	public void deleteAdmin(String id) {
		user_dao.delete(id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：根据昵称模糊匹配查找管理员
	 * @param user,get_all
	 */
	@Override
	public List<Tb_user> searchAdmin(Tb_user user,boolean get_all) {
		//查询所有管理员
		Tb_user search_user=new Tb_user();
		search_user.setRole(1);
		List<Tb_user> user_list=user_dao.getByRole(search_user);
		
		if(get_all){
			//筛选字符为空，故get_all标志位为true，显示所有管理员
			return user_list;
		}else{
			//get_all标志位为false，将昵称中包含指定内容的管理员筛选出来
			List<Tb_user> rs=new ArrayList<Tb_user>();
			Tb_user tmp;
			for(int i=0;i<user_list.size();i++){
				tmp=user_list.get(i);
				if(tmp.getNickname().contains(user.getNickname())){
					rs.add(tmp);
				}
			}
			
			return rs;
		}
	}
	
}
