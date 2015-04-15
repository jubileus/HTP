package com.bjtu.service.admin_management;

import java.util.List;

import com.bjtu.model.pojo.Tb_user;

public interface IAdminManagementService {
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ��������й���Ա��ɫ���û���Ϣ
	 */
    public List<Tb_user> getAllAdmin();
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ���ӹ���Ա
	 * @param user
	 */
    public void addAdmin(Tb_user user);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ�ɾ������Ա
	 * @param id
	 */
    public void deleteAdmin(String id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ������ǳ�ģ��ƥ����ҹ���Ա
	 * @param user,get_all
	 */
    public List<Tb_user> searchAdmin(Tb_user user,boolean get_all);
    
}
