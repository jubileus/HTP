package com.bjtu.service.fileType_management;

import java.util.HashMap;
import java.util.List;

import com.bjtu.model.pojo.Tb_file_type;

public interface IFileTypeManagementService {
	
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：添加文件类型信息
	 * @param file_type
	 */
    public void addFileType(Tb_file_type file_type);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：分页查询文件类型信息
	 * @param index,num,postfix
	 */
    public List getFileTypeByPage(int index,int num,String postfix);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：查询文件类型总数
	 * @param num,postfix
	 */
    public int getFileTypePageCount(int num,String postfix);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-13
	 * 功能：将Tb_file_type对象的List转化为FileTypeEntity对象的List加以返回
	 * @param file_type_list
	 */
    public List convertToFileTypeEntityList(List<Tb_file_type> file_type_list);
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-4-14
	 * 功能：检测后缀是否已存在
	 * @param file_type
	 */
    public int ifPostfixExist(Tb_file_type file_type);
    
    /**
   	 * @author 刘庶
   	 * 编写日期：2015-4-13
   	 * 功能：删除文件类型
   	 * @param id
   	 */
    public void deleteFileType(String id);
    
    /**
   	 * @author 刘庶
   	 * 编写日期：2015-4-13
   	 * 功能：获取id为后缀名，value为图片路径的HashMap
   	 */
    public HashMap<String, String> getImgMap();
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-29
	 * 功能：根据category获取文件类型
	 */
	public List getByCategory(int category);
}
