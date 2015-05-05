package com.bjtu.service.file;

import java.util.HashMap;
import java.util.List;

import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.bo.ListFileEntity;
import com.bjtu.model.bo.PathEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.model.pojo.Tb_share;
import com.bjtu.model.pojo.Tb_user;

public interface IFileService {

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：插入文件数据
	 * @param user
	 */
	public void insert(Tb_file file);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：根据id查询文件
	 * @param id
	 */
	public Tb_file getById(String id);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询符合条件数据的总页数
	 * @param num:每页显示数量
	 * @param path:文件路径
	 * @param file_name:文件显示名称
	 */
    public int getPageCount(int num,String path,String file_name);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询指定页数中的符合条件的数据
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param path:文件路径
	 * @param file_name:文件显示名称
	 */
	public List<Tb_file> getPageData(int num, int index, String path, String file_name);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：将Tb_file的List转化为GalleryFileEntity的List
	 * @param file_list
	 * @param img_map
	 */
	public List<GalleryFileEntity> convertToGalleryFileEntity(List<Tb_file> file_list,HashMap<String, String> img_map);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：获取用户id和路径实体List
	 * @param user_id
	 * @param path
	 */
	public List<PathEntity> getPathList(String user_id,String path);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询文件名称是否存在同名文件
	 * @param file
	 */
	public boolean ifNewFileExist(Tb_file file);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询文件名称是否存在同名文件,存在则返回已有同名文件编号最大值，不存在则返回0
	 * @param file
	 */
	public int getNewFileNum(Tb_file file);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：修改文件show_name
	 * @param file
	 */
    public void modifyShowName(Tb_file file);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：删除文件或文件夹
	 * @param id
	 */
    public void deleteFolderOrFile(String id);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-20
	 * 功能：更新用户已用存储空间
	 * @param user
	 */
	public void updateUsedStorage(Tb_user user);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-21
	 * 功能：修改文件is_complete
	 * @param file
	 */
    public void modifyIs_complete(Tb_file file);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-29
	 * 功能：将Tb_file的List转化为ListFileEntity的List
	 * @param file_list
	 * @param img_map
	 */
	public List<ListFileEntity> convertToListFileEntity(List<Tb_file> file_list,HashMap<String, String> img_map);
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-29
	 * 功能：查询符合条件数据的总页数
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param category：文件分类
	 * @param file_name:文件显示名称
	 */
	public int getPageCount(String user_id,int num, int category, String file_name);
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-29
	 * 功能：查询指定页数中的符合条件的数据
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param category：文件分类
	 * @param file_name:文件显示名称
	 */
	public List getPageData(String user_id,int num, int index, int category,String file_name);
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：修改分享的显示名称
	 * @param share
	 */
    public void modifyShowName(Tb_share share);
}
