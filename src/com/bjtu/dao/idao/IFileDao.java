package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_file;

public interface IFileDao {
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：插入文件数据
	 * @param file
	 */
	public void insert(Tb_file file);
		
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：根据id查询文件
	 * @param id
	 */
    public Tb_file getById(String id);
		
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：查询符合条件数据的总页数
	 * @param num:每页显示数量
	 * @param path:文件路径
	 * @param file_name:文件显示名称
	 */
    public int getPageCount(int num,String path,String file_name);
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：查询指定页数中的符合条件的数据
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param path:文件路径
	 * @param file_name:文件显示名称
	 */
    public List getPageData(int num,int index,String path,String file_name);
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：根据user_id和hdfs_name查询文件
	 * @param user_id
	 * @param hdfs_name
	 */
    public Tb_file getByUserIdAndHDFSName(String user_id,String hdfs_name);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：根据path，user_id，show_name和postfix查询文件
	 * @param file
	 */
    public Tb_file getByUserId_ShowName_Postfix(Tb_file file);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：根据path，user_id，show_name和postfix查询文件副本List
	 * @param file
	 */
    public List<Tb_file> getListByUserId_ShowName_Postfix(Tb_file file);
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：修改文件show_name
	 * @param file
	 */
    public void modifyShowName(Tb_file file);
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：删除一条或多条数据
	 * @param id
	 */
    public void delete(String id);
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-21
	 * 功能：修改文件is_complete
	 * @param file
	 */
    public void modifyIs_complete(Tb_file file);
}
