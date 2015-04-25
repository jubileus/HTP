package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_group;

public interface IGroupDao {
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：插入群组
	 * @param group
	 */
	public void insert(Tb_group group);
		
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：根据id查询群组
	 * @param id
	 */
    public Tb_group getById(String id);
    
    /**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：删除群组
	 * @param id
	 */
    public void delete(String id);
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：获取群组信息
	 * @param creator_id
	 */
	public List getGroup(String creator_id);
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-25
	 * 功能：根据creator_id和name获取对象
	 * @param group
	 */
	public Tb_group getByCreator_id_Name(Tb_group group);
}
