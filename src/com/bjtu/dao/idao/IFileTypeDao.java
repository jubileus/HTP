package com.bjtu.dao.idao;

import java.util.List;

import com.bjtu.model.pojo.Tb_file_type;

public interface IFileTypeDao {
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：插入文件类型
	 * @param file_type
	 */
	public void insert(Tb_file_type file_type);
		
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：分页查询文件类型
	 * @param index,num,postfix
	 */
	public List getByPage(int index,int num,String postfix);
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：查询文件类型总数
	 * @param num,postfix
	 */
	public int getPageNum(int num,String postfix);
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：根据后缀获取文件类型信息
	 * @param file_type
	 */
	public Tb_file_type getFileTypeByPostfix(Tb_file_type file_type);
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：删除文件类型
	 * @param id
	 */
	public void delete(String id);
}
