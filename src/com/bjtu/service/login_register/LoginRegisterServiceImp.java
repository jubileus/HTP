package com.bjtu.service.login_register;

import com.bjtu.dao.idao.IUserDao;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.login_register.ILoginRegisterService;

public class LoginRegisterServiceImp implements ILoginRegisterService{

	private IUserDao user_dao;
	public IUserDao getUser_dao() {
		return user_dao;
	}
	public void setUser_dao(IUserDao user_dao) {
		this.user_dao = user_dao;
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ��û�ע��
	 * @param user
	 */
	@Override
	public void register(Tb_user user) {
		user_dao.insert(user);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ��û���¼
	 * @param user
	 */
	@Override
	public Tb_user login(Tb_user user) {
		return user_dao.getByEmailPassword(user);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����û�ע����ʹ�õ������Ƿ��Ѵ���
	 * @param user
	 */
	@Override
	public int ifEmailExist(Tb_user user) {
		if(user_dao.getByEmail(user)==null){
			return 0;
		}else{
			return 1;
		}
	}
		
}
