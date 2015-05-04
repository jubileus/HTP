package com.bjtu.service.user_management;

import java.util.List;

import com.bjtu.model.pojo.Tb_user;

public interface IUserManagementService {

	/**
	 * @author ����
	 * ��д���ڣ�2015-4-15
	 * ���ܣ���ҳ��ѯ�û���Ϣ
	 * @param index,num,nickname
	 */
    public List getUserTypeByPage(int index,int num,String nickname);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-15
	 * ���ܣ���ѯ�û���Ϣ����
	 * @param num,nickname
	 */
    public int getUserTypePageCount(int num,String nickname);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-15
	 * ���ܣ���Tb_user�����Listת��ΪUserEntity�����List���Է���
	 * @param user_list
	 */
    public List convertToUserEntityList(List<Tb_user> user_list);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-04-15
	 * ���ܣ������û�״̬
	 * @param user
	 */
	public void modifyStatus(Tb_user user);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-15
	 * ���ܣ������ܴ洢�ռ�
	 * @param user
	 */
	public void modifyTotalStorage(Tb_user user);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-15
	 * ���ܣ�����id����û���Ϣ
	 * @param id
	 */
	public Tb_user getUserById(String id);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-05-4
	 * ���ܣ������û��ǳ�
	 * @param user
	 */
	public void modifyNickname(Tb_user user);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-05-4
	 * ���ܣ������û�����
	 * @param user
	 */
	public void modifyPassword(Tb_user user);
}
