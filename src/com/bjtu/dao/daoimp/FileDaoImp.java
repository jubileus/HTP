package com.bjtu.dao.daoimp;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bjtu.dao.idao.IFileDao;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_file_type;
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
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：插入文件数据
	 * @param file
	 */
	@Override
	public void insert(Tb_file file) {
		file_dao_util.save(file);
	}

	/**
	 * @author 苏国伟
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
    	criteria=Criteria.where("path").is(path).and("is_complete").is(1);
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
		criteria=Criteria.where("path").is(path).and("is_complete").is(1);
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
	 * @author 苏国伟
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
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：根据user_id和show_name和postfix查询文件
	 * @param file
	 */
	@Override
	public Tb_file getByUserId_ShowName_Postfix(Tb_file file) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	criteria=Criteria.where("user_id").is(file.getUser_id()).and("show_name").is(file.getShow_name()).
    			and("postfix").is(file.getPostfix()).and("path").is(file.getPath());;
    	query.addCriteria(criteria);
		return file_dao_util.findOne(query);
	}
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：根据user_id和show_name和postfix查询文件副本List
	 * @param file
	 */
    public List<Tb_file> getListByUserId_ShowName_Postfix(Tb_file file){
		Query query;
		Criteria criteria;
    	query=new Query();
    	criteria=Criteria.where("user_id").is(file.getUser_id()).and("postfix").is(file.getPostfix()).and("path").is(file.getPath());
    	query.addCriteria(criteria);
    	criteria=Criteria.where("show_name").regex(file.getShow_name()+"[(]+"+"[0-9]+"+"[)]$");
		query.addCriteria(criteria);
		return file_dao_util.find(query);
	}
    
    /**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：修改文件show_name
	 * @param file
	 */
	@Override
	public void modifyShowName(Tb_file file) {
		Query query = new Query(); 
		Criteria criteria=Criteria.where("id").is(file.getId());
        query.addCriteria(criteria);  
        Update update = new Update();  
        update.set("show_name", file.getShow_name());
        file_dao_util.update(query, update);
	}
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-18
	 * 功能：删除一条或多条数据
	 * @param id
	 */
	@Override
	public void delete(String id) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	criteria=Criteria.where("id").is(id);
    	query.addCriteria(criteria);
    	Tb_file file=file_dao_util.findOne(query);
    	
    	if(file.getIs_folder()==1){
    		//删除子文件夹和子文件
    		query=new Query();
    		criteria=Criteria.where("user_id").is(file.getUser_id());
    		query.addCriteria(criteria);
    		String delete_path=file.getPath()+file.getHdfs_name()+"/";
    		criteria=Criteria.where("path").regex(delete_path+".*");
    		query.addCriteria(criteria);
    		file_dao_util.delete(query);
    		//删除自身
    		query=new Query();
    		criteria=Criteria.where("id").is(id);
    		query.addCriteria(criteria);
    		file_dao_util.delete(query);
    	}else{
    		//删除文件
    		query=new Query();
    		criteria=Criteria.where("id").is(id);
    		query.addCriteria(criteria);
    		file_dao_util.delete(query);
    	}
	}
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-21
	 * 功能：修改文件is_complete
	 * @param file
	 */
	@Override
	public void modifyIs_complete(Tb_file file) {
		Query query = new Query(); 
		Criteria criteria=Criteria.where("id").is(file.getId());
        query.addCriteria(criteria);  
        Update update = new Update();  
        update.set("is_complete", file.getIs_complete());
        file_dao_util.update(query, update);
	}
	
	/**
	 * @author 苏国伟
	 * 编写日期：2015-04-29
	 * 功能：查询符合条件数据的总页数
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param category：文件分类
	 * @param file_name:文件显示名称
	 */
	@Override
	public int getPageCount(String user_id,int num, int category, String file_name) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	
    	//根据category获取对应的Criteria
    	criteria=getCriteriaByCategory(user_id, category);
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
	 * @author 苏国伟
	 * 编写日期：2015-04-29
	 * 功能：查询指定页数中的符合条件的数据
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param category：文件分类
	 * @param file_name:文件显示名称
	 */
	@Override
	public List getPageData(String user_id,int num, int index, int category,String file_name) {
		Criteria criteria;
		Query query;
		query=new Query();
		
		criteria=getCriteriaByCategory(user_id, category);
		query.addCriteria(criteria);
		
		//指定文件所在目录
		criteria=new Criteria();
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
	 * @author 苏国伟
	 * 编写日期：2015-04-29
	 * 功能：根据category返回需要的Criteria
	 * @param user_id:用户id
	 * @param category：文件分类
	 */
	private Criteria getCriteriaByCategory(String user_id,int category){
		Criteria criteria;
		criteria=Criteria.where("is_complete").is(1).and("user_id").is(user_id);
		//读入需要的文件后缀
		switch (category) {
		case 1:
			//文档文件
			criteria.orOperator(
					Criteria.where("postfix").is("txt"),
					Criteria.where("postfix").is("doc"),
					Criteria.where("postfix").is("docx"),
					Criteria.where("postfix").is("xls"),
					Criteria.where("postfix").is("xlsx"),
					Criteria.where("postfix").is("ppt"),
					Criteria.where("postfix").is("pptx"),
					Criteria.where("postfix").is("pdf")
					);
			break;
		case 2:
			//图片文件
			criteria.orOperator(
					Criteria.where("postfix").is("bmp"),
					Criteria.where("postfix").is("jpg"),
					Criteria.where("postfix").is("jpeg"),
					Criteria.where("postfix").is("png"),
					Criteria.where("postfix").is("gif")
					);
			break;
		case 3:
			//视频文件
			criteria.orOperator(
					Criteria.where("postfix").is("avi"),
					Criteria.where("postfix").is("mov"),
					Criteria.where("postfix").is("mpg"),
					Criteria.where("postfix").is("mpeg"),
					Criteria.where("postfix").is("ram"),
					Criteria.where("postfix").is("qt"),
					Criteria.where("postfix").is("mp4")
					);
			break;
		case 4:
			//音频文件
			criteria.orOperator(
					Criteria.where("postfix").is("mp3"),
					Criteria.where("postfix").is("wav")
					);
			break;
		default:
			break;
		}
    	
    	return criteria;
	}
}
