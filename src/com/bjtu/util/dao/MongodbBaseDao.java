package com.bjtu.util.dao;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public abstract class MongodbBaseDao<T> {
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	protected MongoTemplate	mongoTemplate;
	/**
     * ͨ��������ѯʵ��(����)
     *
     * @param query
     */
    public List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * ͨ��һ����������ѯһ��ʵ��
     *
     * @param query
     * @return
     */
    public T findOne(Query query) {
        return (T) mongoTemplate.findOne(query, this.getEntityClass());
    }
    /**
     * ͨ��������ѯ��������
     *
     * @param query
     * @param update
     * @return
     */
    public void update(Query query, Update update) {
        mongoTemplate.upsert(query, update, this.getEntityClass());
    }
    
    /**
     * ͨ��������ѯ���¶�������
     * @param query
     * @param update 
     */
    public void updateMulti(Query query, Update update) {
        mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    /**
     * ����һ������mongodb
     *
     * @param bean
     * @return
     */
    public T save(T bean) {
        mongoTemplate.save(bean);
        return bean;
    }
    
    /**
     * ��������
     * @param list
     * @return
     */
    public List<T> batchSave(List<T> list){
    	mongoTemplate.insertAll(list);
    	return list;
    }

    /**
     * ͨ��ID��ȡ��¼
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return (T) mongoTemplate.findById(id, this.getEntityClass());
    }

    /**
     * ͨ��ID��ȡ��¼,����ָ���˼�����(�����˼)
     *
     * @param id
     * @param collectionName ������
     * @return
     */
    public T get(String id, String collectionName) {
        return (T) mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }
    
    public long count(Query query){
    	return mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * ɾ����¼
     *
     * @param query
     */
	public void delete(Query query) {
        mongoTemplate.remove(query, this.getEntityClass());
    }
    
    
    /**
     * ��ȡ��Ҫ������ʵ����class
     *
     * @return
     */
    protected abstract Class getEntityClass();
    
    /**
     * ���mongoTemplate
     * 
     * @return
     */
    protected MongoTemplate getMongoTemplate(){
    	return this.mongoTemplate;
    }

}
