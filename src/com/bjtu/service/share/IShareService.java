package com.bjtu.service.share;

import com.bjtu.model.pojo.Tb_share;

public interface IShareService {

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：插入分享数据
	 * @param share
	 */
	public void insert(Tb_share share);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：根据id查询分享数据
	 * @param id
	 */
    public Tb_share getById(String id);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：根据id删除一条分享数据
	 * @param id
	 */
    public void delete(String id);
}
