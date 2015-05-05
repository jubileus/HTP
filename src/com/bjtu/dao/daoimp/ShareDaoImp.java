package com.bjtu.dao.daoimp;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bjtu.dao.idao.IShareDao;
import com.bjtu.model.pojo.Tb_share;
import com.bjtu.util.mongodb.ShareDaoUtil;

public class ShareDaoImp implements IShareDao{
	
	private ShareDaoUtil share_dao_util;
	public ShareDaoUtil getShare_dao_util() {
		return share_dao_util;
	}
	public void setShare_dao_util(ShareDaoUtil share_dao_util) {
		this.share_dao_util = share_dao_util;
	}

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-26
	 * 功能：插入分享数据
	 * @param share
	 */
	@Override
	public void insert(Tb_share share) {
		share_dao_util.save(share);
	}

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-26
	 * 功能：根据id查询分享数据
	 * @param id
	 */
	@Override
	public Tb_share getById(String id) {
		Query query = new Query(); 
		Criteria criteria=Criteria.where("id").is(id);
        query.addCriteria(criteria);  
		return (Tb_share)share_dao_util.findOne(query);
	}

	/**
	 * @author 宫文超
	 * 编写日期：2015-04-26
	 * 功能：根据id删除一条分享数据
	 * @param id
	 */
	@Override
	public void delete(String id) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	criteria=Criteria.where("id").is(id);
    	query.addCriteria(criteria);
    	share_dao_util.delete(query);
	}
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：查询符合条件数据的总页数
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param file_name:模糊查询文件名称
	 */
	@Override
	public int getPageCount(String user_id, int num,String file_name) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	//指定用户id
    	criteria=Criteria.where("creator_id").is(user_id);
    	query.addCriteria(criteria);
    	//判读是否是模糊查询
    	if(file_name!=null){
    		//MonogoDB 没有like操作，因此使用正则表达式完成like操作，进行模糊查询
    		criteria=Criteria.where("show_name").regex(".*?"+file_name+".*");
    		query.addCriteria(criteria);
    	}
    	//将数据条数转化为页数
    	int rs=1;
    	int total=(int)share_dao_util.count(query);
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
	 * 编写日期：2015-05-5
	 * 功能：查询指定页数中的符合条件的数据
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param file_name:模糊查询文件名称
	 */
	@Override
	public List getPageData(String user_id, int num, int index,String file_name) {
		Criteria criteria;
		Query query;
		query=new Query();
		//指定用户id
		criteria=Criteria.where("creator_id").is(user_id);
		query.limit(num);
		query.skip((index-1)*num);
		query.with(new Sort(new Sort.Order(Direction.DESC, "timestamp")));
    	query.addCriteria(criteria);
    	//判读是否是模糊查询
    	if(file_name!=null){
    		//MonogoDB 没有like操作，因此使用正则表达式完成like操作，进行模糊查询
    		criteria=Criteria.where("show_name").regex(".*?"+file_name+".*");
    		query.addCriteria(criteria);
    	}
		
		return share_dao_util.find(query);
	}
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：修改显示名称
	 * @param share
	 */
	@Override
	public void modifyShowname(Tb_share share) {
		Query query = new Query(); 
		Criteria criteria=Criteria.where("file_id").is(share.getFile_id());
        query.addCriteria(criteria);  
        Update update = new Update();  
        update.set("show_name", share.getShow_name());
        share_dao_util.updateMulti(query, update);
	}
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：根据creator_id删除分享数据
	 * @param creator_id
	 */
	@Override
	public void deleteByCreatorId(String creator_id) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	criteria=Criteria.where("creator_id").is(creator_id);
    	query.addCriteria(criteria);
    	share_dao_util.delete(query);		
	}
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：根据group_id删除分享数据
	 * @param group_id
	 */
	@Override
	public void deleteByGroupId(String group_id) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	criteria=Criteria.where("group_id").is(group_id);
    	query.addCriteria(criteria);
    	share_dao_util.delete(query);		
	}
	
	/**
	 * @author 宫文超
	 * 编写日期：2015-05-5
	 * 功能：查询符合条件数据的总页数
	 * @param group_id:群组id
	 * @param num:每页显示数量
	 */
	@Override
	public int getPageCount(String group_id, int num) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	//指定群组id
    	criteria=Criteria.where("group_id").is(group_id);
    	query.addCriteria(criteria);
    	//将数据条数转化为页数
    	int rs=1;
    	int total=(int)share_dao_util.count(query);
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
	 * 编写日期：2015-05-5
	 * 功能：查询指定页数中的符合条件的数据
	 * @param group_id:群组id
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 */
	@Override
	public List getPageData(String group_id, int num,int index) {
		Criteria criteria;
		Query query;
		query=new Query();
		//指定群组id
		criteria=Criteria.where("group_id").is(group_id);
		query.limit(num);
		query.skip((index-1)*num);
		query.with(new Sort(new Sort.Order(Direction.DESC, "timestamp")));
    	query.addCriteria(criteria);
		
		return share_dao_util.find(query);
	}

}
