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
     * 通过条件查询实体(集合)
     *
     * @param query
     */
    public List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 通过一定的条件查询一个实体
     *
     * @param query
     * @return
     */
    public T findOne(Query query) {
        return (T) mongoTemplate.findOne(query, this.getEntityClass());
    }
    /**
     * 通过条件查询更新数据
     *
     * @param query
     * @param update
     * @return
     */
    public void update(Query query, Update update) {
        mongoTemplate.upsert(query, update, this.getEntityClass());
    }
    
    /**
     * 通过条件查询更新多条数据
     * @param query
     * @param update 
     */
    public void updateMulti(Query query, Update update) {
        mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    /**
     * 保存一个对象到mongodb
     *
     * @param bean
     * @return
     */
    public T save(T bean) {
        mongoTemplate.save(bean);
        return bean;
    }
    
    /**
     * 批量保存
     * @param list
     * @return
     */
    public List<T> batchSave(List<T> list){
    	mongoTemplate.insertAll(list);
    	return list;
    }

    /**
     * 通过ID获取记录
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return (T) mongoTemplate.findById(id, this.getEntityClass());
    }

    /**
     * 通过ID获取记录,并且指定了集合名(表的意思)
     *
     * @param id
     * @param collectionName 集合名
     * @return
     */
    public T get(String id, String collectionName) {
        return (T) mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }
    
    public long count(Query query){
    	return mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * 删除记录
     *
     * @param query
     */
	public void delete(Query query) {
        mongoTemplate.remove(query, this.getEntityClass());
    }
    
    
    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    protected abstract Class getEntityClass();
    
    /**
     * 获得mongoTemplate
     * 
     * @return
     */
    protected MongoTemplate getMongoTemplate(){
    	return this.mongoTemplate;
    }

}
