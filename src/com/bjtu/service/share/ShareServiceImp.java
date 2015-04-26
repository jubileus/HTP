package com.bjtu.service.share;

import com.bjtu.dao.idao.IShareDao;
import com.bjtu.model.pojo.Tb_share;

public class ShareServiceImp implements IShareService{
	
	private IShareDao share_dao;
	public IShareDao getShare_dao() {
		return share_dao;
	}
	public void setShare_dao(IShareDao share_dao) {
		this.share_dao = share_dao;
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ������������
	 * @param share
	 */
	@Override
	public void insert(Tb_share share) {
		share_dao.insert(share);
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����id��ѯ��������
	 * @param id
	 */
	@Override
	public Tb_share getById(String id) {
		return share_dao.getById(id);
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����idɾ��һ����������
	 * @param id
	 */
	@Override
	public void delete(String id) {
		share_dao.delete(id);
	}

}
