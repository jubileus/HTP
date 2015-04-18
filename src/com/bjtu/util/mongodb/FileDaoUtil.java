package com.bjtu.util.mongodb;

import com.bjtu.model.pojo.Tb_file;
import com.bjtu.util.dao.MongodbBaseDao;

public class FileDaoUtil extends MongodbBaseDao<Tb_file>{

	@Override
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return Tb_file.class;
	}

}
