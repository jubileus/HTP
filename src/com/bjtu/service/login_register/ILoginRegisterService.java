package com.bjtu.service.login_register;

import com.bjtu.model.pojo.Tb_user;

public interface ILoginRegisterService {
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ��û�ע��
	 * @param user
	 */
    public void register(Tb_user user);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ��û���¼
	 * @param user
	 */
    public Tb_user login(Tb_user user);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����û�ע����ʹ�õ������Ƿ��Ѵ���
	 * @param user
	 */
    public int ifEmailExist(Tb_user user);
    
    
}
