package com.bjtu.service.share;

import java.util.HashMap;
import java.util.List;

import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.bo.ShareEntity;
import com.bjtu.model.pojo.Tb_file;
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
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据id获取文件显示名称
	 * @param id
	 */
    public String getFileShowName(String id);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据id获取文件后缀
	 * @param id
	 */
    public String getFilePostfix(String id);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：查询符合条件数据的总页数
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param file_name:模糊查询文件名称
	 */
    public int getPageCount(String user_id,int num,String file_name);

    /**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：查询指定页数中的符合条件的数据
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param file_name:模糊查询文件名称
	 */
    public List getPageData(String user_id,int num,int index,String file_name);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：将Tb_share的List转化为ShareEntity的List
	 * @param share_list
	 * @param img_map
	 */
    public List<ShareEntity> convertToShareEntity(List<Tb_share> share_list,HashMap<String, String> img_map);
}
