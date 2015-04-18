package com.bjtu.dao.daoimp;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bjtu.dao.idao.IFileDao;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.util.mongodb.FileDaoUtil;

public class FileDaoImp implements IFileDao{
	
	private FileDaoUtil file_dao_util;
	public FileDaoUtil getFile_dao_util() {
		return file_dao_util;
	}
	public void setFile_dao_util(FileDaoUtil file_dao_util) {
		this.file_dao_util = file_dao_util;
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：插入文件数据
	 * @param file
	 */
	@Override
	public void insert(Tb_file file) {
		file_dao_util.save(file);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：根据id查询文件
	 * @param id
	 */
	@Override
	public Tb_file getById(String id) {
		Query query = new Query(); 
		Criteria criteria=Criteria.where("id").is(id);
        query.addCriteria(criteria);  
		return (Tb_file)file_dao_util.findOne(query);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询符合条件数据的总页数
	 * @param num:每页显示数量
	 * @param path:文件路径
	 * @param file_name:文件显示名称
	 */
	@Override
	public int getPageCount(int num, String path, String file_name) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	//指定文件所在目录
    	criteria=Criteria.where("path").is(path);
    	query.addCriteria(criteria);
    	//判读是否是模糊查询
    	if(file_name!=null){
    		//MonogoDB 没有like操作，因此使用正则表达式完成like操作，进行模糊查询
    		criteria=Criteria.where("show_name").regex(".*?"+file_name+".*");
    		query.addCriteria(criteria);
    	}
    	//将数据条数转化为页数
    	int rs=1;
    	int total=(int)file_dao_util.count(query);
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
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询指定页数中的符合条件的数据
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param path:文件路径
	 * @param file_name:文件显示名称
	 */
	@Override
	public List getPageData(int num, int index, String path, String file_name) {
		Criteria criteria;
		Query query;
		query=new Query();
		//指定文件所在目录
		criteria=Criteria.where("path").is(path);
		query.limit(num);
		query.skip((index-1)*num);
		query.with(new Sort(new Sort.Order(Direction.DESC, "is_folder")));
		query.with(new Sort(new Sort.Order(Direction.DESC, "timestamp")));
    	query.addCriteria(criteria);
    	//判读是否是模糊查询
    	if(file_name!=null){
    		//MonogoDB 没有like操作，因此使用正则表达式完成like操作，进行模糊查询
    		criteria=Criteria.where("show_name").regex(".*?"+file_name+".*");
    		query.addCriteria(criteria);
    	}
		
		return file_dao_util.find(query);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：根据user_id和hdfs_name查询文件
	 * @param user_id
	 * @param hdfs_name
	 */
	@Override
	public Tb_file getByUserIdAndHDFSName(String user_id, String hdfs_name) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	criteria=Criteria.where("user_id").is(user_id).and("hdfs_name").is(hdfs_name);
    	query.addCriteria(criteria);
		return file_dao_util.findOne(query);
	}

}
