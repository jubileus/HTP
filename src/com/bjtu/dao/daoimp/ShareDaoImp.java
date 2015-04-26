package com.bjtu.dao.daoimp;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bjtu.dao.idao.IShareDao;
import com.bjtu.model.pojo.Tb_share;
import com.bjtu.util.mongodb.ShareDaoUtil;

public class ShareDaoImp implements IShareDao{
	
	private ShareDaoUtil share_dao_util;
	public ShareDaoUtil getShare_dao_util() {
		return share_dao_util;
	}
	public void setShare_dao_util(ShareDaoUtil share_dao_util) {
		this.share_dao_util = share_dao_util;
	}

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-26
	 * 功能：插入分享数据
	 * @param share
	 */
	@Override
	public void insert(Tb_share share) {
		share_dao_util.save(share);
	}

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-26
	 * 功能：根据id查询分享数据
	 * @param id
	 */
	@Override
	public Tb_share getById(String id) {
		Query query = new Query(); 
		Criteria criteria=Criteria.where("id").is(id);
        query.addCriteria(criteria);  
		return (Tb_share)share_dao_util.findOne(query);
	}

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-26
	 * 功能：根据id删除一条分享数据
	 * @param id
	 */
	@Override
	public void delete(String id) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	criteria=Criteria.where("id").is(id);
    	query.addCriteria(criteria);
    	share_dao_util.delete(query);
	}

}
