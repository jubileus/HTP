package com.bjtu.util.mongodb;

import com.bjtu.model.pojo.Tb_share;
import com.bjtu.util.dao.MongodbBaseDao;

public class ShareDaoUtil extends MongodbBaseDao<Tb_share>{

	@Override
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return Tb_share.class;
	}

}
