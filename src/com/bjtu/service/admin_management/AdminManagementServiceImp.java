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
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ��������й���Ա��ɫ���û���Ϣ
	 */
	@Override
	public List<Tb_user> getAllAdmin() {
		Tb_user user=new Tb_user();
		user.setRole(1);
		return user_dao.getByRole(user);
	}
	
	 /**
		 * @author ����
		 * ��д���ڣ�2015-4-13
		 * ���ܣ���ӹ���Ա
		 * @param user
		 */
	@Override
	public void addAdmin(Tb_user user) {
		user_dao.insert(user);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ�ɾ������Ա
	 * @param id
	 */
	@Override
	public void deleteAdmin(String id) {
		user_dao.delete(id);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ������ǳ�ģ��ƥ����ҹ���Ա
	 * @param user,get_all
	 */
	@Override
	public List<Tb_user> searchAdmin(Tb_user user,boolean get_all) {
		//��ѯ���й���Ա
		Tb_user search_user=new Tb_user();
		search_user.setRole(1);
		List<Tb_user> user_list=user_dao.getByRole(search_user);
		
		if(get_all){
			//ɸѡ�ַ�Ϊ�գ���get_all��־λΪtrue����ʾ���й���Ա
			return user_list;
		}else{
			//get_all��־λΪfalse�����ǳ��а���ָ�����ݵĹ���Աɸѡ����
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
