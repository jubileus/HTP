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
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-18
	 * ���ܣ������ļ�����
	 * @param file
	 */
	@Override
	public void insert(Tb_file file) {
		file_dao_util.save(file);
	}

	/**
	 * @author �չ�ΰ
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
    	criteria=Criteria.where("path").is(path).and("is_complete").is(1);
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
		criteria=Criteria.where("path").is(path).and("is_complete").is(1);
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
	 * @author �չ�ΰ
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
	 * @author �չ�ΰ
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
	 * @author �չ�ΰ
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
	 * @author �չ�ΰ
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
	 * @author �չ�ΰ
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
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-21
	 * ���ܣ��޸��ļ�is_complete
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
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-29
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param category���ļ�����
	 * @param file_name:�ļ���ʾ����
	 */
	@Override
	public int getPageCount(String user_id,int num, int category, String file_name) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	
    	//����category��ȡ��Ӧ��Criteria
    	criteria=getCriteriaByCategory(user_id, category);
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
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-29
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 * @param category���ļ�����
	 * @param file_name:�ļ���ʾ����
	 */
	@Override
	public List getPageData(String user_id,int num, int index, int category,String file_name) {
		Criteria criteria;
		Query query;
		query=new Query();
		
		criteria=getCriteriaByCategory(user_id, category);
		query.addCriteria(criteria);
		
		//ָ���ļ�����Ŀ¼
		criteria=new Criteria();
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
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-29
	 * ���ܣ�����category������Ҫ��Criteria
	 * @param user_id:�û�id
	 * @param category���ļ�����
	 */
	private Criteria getCriteriaByCategory(String user_id,int category){
		Criteria criteria;
		criteria=Criteria.where("is_complete").is(1).and("user_id").is(user_id);
		//������Ҫ���ļ���׺
		switch (category) {
		case 1:
			//�ĵ��ļ�
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
			//ͼƬ�ļ�
			criteria.orOperator(
					Criteria.where("postfix").is("bmp"),
					Criteria.where("postfix").is("jpg"),
					Criteria.where("postfix").is("jpeg"),
					Criteria.where("postfix").is("png"),
					Criteria.where("postfix").is("gif")
					);
			break;
		case 3:
			//��Ƶ�ļ�
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
			//��Ƶ�ļ�
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
