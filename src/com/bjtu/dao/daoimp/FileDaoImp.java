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
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ������ļ�����
	 * @param file
	 */
	@Override
	public void insert(Tb_file file) {
		file_dao_util.save(file);
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�����id��ѯ�ļ�
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
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param num:ÿҳ��ʾ����
	 * @param path:�ļ�·��
	 * @param file_name:�ļ���ʾ����
	 */
	@Override
	public int getPageCount(int num, String path, String file_name) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	//ָ���ļ�����Ŀ¼
    	criteria=Criteria.where("path").is(path);
    	query.addCriteria(criteria);
    	//�ж��Ƿ���ģ����ѯ
    	if(file_name!=null){
    		//MonogoDB û��like���������ʹ��������ʽ���like����������ģ����ѯ
    		criteria=Criteria.where("show_name").regex(".*?"+file_name+".*");
    		query.addCriteria(criteria);
    	}
    	//����������ת��Ϊҳ��
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
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 * @param path:�ļ�·��
	 * @param file_name:�ļ���ʾ����
	 */
	@Override
	public List getPageData(int num, int index, String path, String file_name) {
		Criteria criteria;
		Query query;
		query=new Query();
		//ָ���ļ�����Ŀ¼
		criteria=Criteria.where("path").is(path);
		query.limit(num);
		query.skip((index-1)*num);
		query.with(new Sort(new Sort.Order(Direction.DESC, "is_folder")));
		query.with(new Sort(new Sort.Order(Direction.DESC, "timestamp")));
    	query.addCriteria(criteria);
    	//�ж��Ƿ���ģ����ѯ
    	if(file_name!=null){
    		//MonogoDB û��like���������ʹ��������ʽ���like����������ģ����ѯ
    		criteria=Criteria.where("show_name").regex(".*?"+file_name+".*");
    		query.addCriteria(criteria);
    	}
		
		return file_dao_util.find(query);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�����user_id��hdfs_name��ѯ�ļ�
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
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�����user_id��show_name��postfix��ѯ�ļ�
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
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�����user_id��show_name��postfix��ѯ�ļ�����List
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
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ��޸��ļ�show_name
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
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�ɾ��һ�����������
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
    		//ɾ�����ļ��к����ļ�
    		query=new Query();
    		criteria=Criteria.where("user_id").is(file.getUser_id());
    		query.addCriteria(criteria);
    		String delete_path=file.getPath()+file.getHdfs_name()+"/";
    		criteria=Criteria.where("path").regex(delete_path+".*");
    		query.addCriteria(criteria);
    		file_dao_util.delete(query);
    		//ɾ������
    		query=new Query();
    		criteria=Criteria.where("id").is(id);
    		query.addCriteria(criteria);
    		file_dao_util.delete(query);
    	}else{
    		//ɾ���ļ�
    		query=new Query();
    		criteria=Criteria.where("id").is(id);
    		query.addCriteria(criteria);
    		file_dao_util.delete(query);
    	}
	}

}
