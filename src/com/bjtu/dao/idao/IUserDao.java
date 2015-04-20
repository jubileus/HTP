package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_user;

public interface IUserDao {
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-03-27
	 * ���ܣ������û�
	 * @param user
	 */
	public void insert(Tb_user user);
		
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����id��ѯ�û�
	 * @param id
	 */
    public Tb_user getById(String id);
		
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����email,password��ѯ�û�
	 * @param user
	 */
    public Tb_user getByEmailPassword(Tb_user user);
    
    /**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����email��ѯ�û�
	 * @param user
	 */
    public Tb_user getByEmail(Tb_user user);
    
    /**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-13
	 * ���ܣ�����role��ѯ�û�
	 * @param user
	 */
    public List getByRole(Tb_user user);
    
    /**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-13
	 * ���ܣ�ɾ���û�
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-15
	 * ���ܣ���ҳ��ѯ�û���Ϣ
	 * @param index,num,nickname
	 */
	public List getByPage(int index,int num,String nickname);
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-15
	 * ���ܣ���ѯ�û���Ϣ����
	 * @param num,nickname
	 */
	public int getPageNum(int num,String nickname);
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-15
	 * ���ܣ������û�״̬
	 * @param user
	 */
	public void updateStatus(Tb_user user);
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-15
	 * ���ܣ������û��ܴ洢�ռ�
	 * @param user
	 */
	public void updateTotalStorage(Tb_user user);
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-20
	 * ���ܣ������û����ô洢�ռ�
	 * @param user
	 */
	public void updateUsedStorage(Tb_user user);
}
