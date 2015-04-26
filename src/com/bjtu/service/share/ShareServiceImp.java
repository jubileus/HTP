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
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：插入分享数据
	 * @param share
	 */
	@Override
	public void insert(Tb_share share) {
		share_dao.insert(share);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：根据id查询分享数据
	 * @param id
	 */
	@Override
	public Tb_share getById(String id) {
		return share_dao.getById(id);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：根据id删除一条分享数据
	 * @param id
	 */
	@Override
	public void delete(String id) {
		share_dao.delete(id);
	}

}
