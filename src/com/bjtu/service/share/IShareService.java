package com.bjtu.service.share;

import com.bjtu.model.pojo.Tb_share;

public interface IShareService {

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ������������
	 * @param share
	 */
	public void insert(Tb_share share);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����id��ѯ��������
	 * @param id
	 */
    public Tb_share getById(String id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����idɾ��һ����������
	 * @param id
	 */
    public void delete(String id);
}
