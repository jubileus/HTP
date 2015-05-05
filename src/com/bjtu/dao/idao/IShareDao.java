package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_share;

public interface IShareDao {

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-26
	 * 功能：插入分享数据
	 * @param share
	 */
	public void insert(Tb_share share);
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-26
	 * 功能：根据id查询分享数据
	 * @param id
	 */
    public Tb_share getById(String id);
    
    /**
	 * @author 宫文超
	 * 编写日期：2015-04-26
	 * 功能：根据id删除一条分享数据
	 * @param id
	 */
    public void delete(String id);
    
    /**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：查询符合条件数据的总页数
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param file_name:模糊查询文件名称
	 */
    public int getPageCount(String user_id,int num,String file_name);

    /**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：查询指定页数中的符合条件的数据
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param file_name:模糊查询文件名称
	 */
    public List getPageData(String user_id,int num,int index,String file_name);
    
    /**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：修改显示名称
	 * @param share
	 */
	public void modifyShowname(Tb_share share);
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：根据creator_id删除分享数据
	 * @param creator_id
	 */
    public void deleteByCreatorId(String creator_id);
    
    /**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：根据group_id删除分享数据
	 * @param group_id
	 */
    public void deleteByGroupId(String group_id);
}
