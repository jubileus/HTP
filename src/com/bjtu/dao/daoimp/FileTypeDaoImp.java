package com.bjtu.dao.daoimp;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.dao.idao.IFileTypeDao;
import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.util.dao.DaoUtil;

public class FileTypeDaoImp implements IFileTypeDao{
	
	private DaoUtil dao_util;		
	public DaoUtil getDao_util() {
		return dao_util;
	}
	public void setDao_util(DaoUtil dao_util) {
		this.dao_util = dao_util;
	}


	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：插入文件类型
	 * @param file_type
	 */
	@Override
	public void insert(Tb_file_type file_type) {
		dao_util.insert(file_type);
	}

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：分页查询文件类型
	 * @param index,num,postfix
	 */
	@Override
	public List getByPage(int index, int num,String postfix) {
		List<Tb_file_type> rs=new ArrayList<Tb_file_type>();
		
		if(postfix!=null&&!postfix.equals("")){
			//模糊查询特定条件的数据
			rs=dao_util.getList(index, num, "postfix", postfix, Tb_file_type.class,"timestamp");
		}else{
			//查询所有
			rs=dao_util.getList( index, num,Tb_file_type.class,"timestamp");
		}
		
		return rs;
	}

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：查询文件类型总数
	 * @param num,postfix
	 */
	@Override
	public int getPageNum(int num,String postfix) {
		String hql="from Tb_file_type";
		int total=0;
		
		if(postfix!=null&&!postfix.equals("")){
			//模糊查询特定条件的数据
			total=dao_util.count("postfix", postfix, Tb_file_type.class);
		}else{
			//查询所有
			total=dao_util.count(hql);
		}
		
		int rs=1;
		if(total>num){
			if(total%num==0){
				rs=total/num;
			}else{
				rs=total/num+1;
			}
		}
		return rs;
	}
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：根据后缀获取文件类型信息
	 * @param file_type
	 */
	@Override
	public Tb_file_type getFileTypeByPostfix(Tb_file_type file_type) {
		String hql = "from Tb_file_type where postfix=?";
		Object[] objects = new Object[1];
		objects[0] = file_type.getPostfix();
		return (Tb_file_type)dao_util.getSingle(hql, objects);
	}
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：删除文件类型
	 * @param id
	 */
	@Override
	public void delete(String id) {
		String hql = "from Tb_file_type where id=?";
		Object[] objects = new Object[1];
		objects[0] = id;
		Tb_file_type file_type= (Tb_file_type)dao_util.getSingle(hql, objects);
		dao_util.delete(file_type);
	}
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-14
	 * 功能：获取全部文件类型
	 */
	@Override
	public List getAll() {
		String hql = "from Tb_file_type";
		Object[] objects = new Object[0];
		return dao_util.getList(hql, objects);
	}
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-04-29
	 * 功能：根据category获取文件类型
	 */
	@Override
	public List getByCategory(int category) {
		String hql = "from Tb_file_type where category=?";
		Object[] objects = new Object[1];
		objects[0] = category;
		return dao_util.getList(hql, objects);
	}

}
