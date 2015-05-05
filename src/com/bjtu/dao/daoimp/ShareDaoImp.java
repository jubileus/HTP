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
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-26
	 * ���ܣ������������
	 * @param share
	 */
	@Override
	public void insert(Tb_share share) {
		share_dao_util.save(share);
	}

	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����id��ѯ��������
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
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����idɾ��һ����������
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
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param file_name:ģ����ѯ�ļ�����
	 */
	@Override
	public int getPageCount(String user_id, int num,String file_name) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	//ָ���û�id
    	criteria=Criteria.where("creator_id").is(user_id);
    	query.addCriteria(criteria);
    	//�ж��Ƿ���ģ����ѯ
    	if(file_name!=null){
    		//MonogoDB û��like���������ʹ��������ʽ���like����������ģ����ѯ
    		criteria=Criteria.where("show_name").regex(".*?"+file_name+".*");
    		query.addCriteria(criteria);
    	}
    	//����������ת��Ϊҳ��
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
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 * @param file_name:ģ����ѯ�ļ�����
	 */
	@Override
	public List getPageData(String user_id, int num, int index,String file_name) {
		Criteria criteria;
		Query query;
		query=new Query();
		//ָ���û�id
		criteria=Criteria.where("creator_id").is(user_id);
		query.limit(num);
		query.skip((index-1)*num);
		query.with(new Sort(new Sort.Order(Direction.DESC, "timestamp")));
    	query.addCriteria(criteria);
    	//�ж��Ƿ���ģ����ѯ
    	if(file_name!=null){
    		//MonogoDB û��like���������ʹ��������ʽ���like����������ģ����ѯ
    		criteria=Criteria.where("show_name").regex(".*?"+file_name+".*");
    		query.addCriteria(criteria);
    	}
		
		return share_dao_util.find(query);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ��޸���ʾ����
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
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����creator_idɾ����������
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
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����group_idɾ����������
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
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param group_id:Ⱥ��id
	 * @param num:ÿҳ��ʾ����
	 */
	@Override
	public int getPageCount(String group_id, int num) {
		Query query;
		Criteria criteria;
    	query=new Query();
    	//ָ��Ⱥ��id
    	criteria=Criteria.where("group_id").is(group_id);
    	query.addCriteria(criteria);
    	//����������ת��Ϊҳ��
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
	 * @author ���ĳ�
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param group_id:Ⱥ��id
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 */
	@Override
	public List getPageData(String group_id, int num,int index) {
		Criteria criteria;
		Query query;
		query=new Query();
		//ָ��Ⱥ��id
		criteria=Criteria.where("group_id").is(group_id);
		query.limit(num);
		query.skip((index-1)*num);
		query.with(new Sort(new Sort.Order(Direction.DESC, "timestamp")));
    	query.addCriteria(criteria);
		
		return share_dao_util.find(query);
	}

}
