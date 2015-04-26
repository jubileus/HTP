package com.bjtu.util.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author 刘庶
 * 编写日期：2015-03-27
 * 功能：dao层工具类,适用于hibernate  
 */

public class DaoUtil extends HibernateDaoSupport{
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：将对象插入数据库
	 * @param obj：要插入数据库的对象
	 */
	public void insert(Object obj) {
		this.getHibernateTemplate().save(obj);			
	}		
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：将对象插入数据库(用于事务处理)
	 * @param obj：要插入数据库的对象
	 * @param session：session对象
	 */
	public void insert(Object obj,Session session) {
		session.save(obj);		
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：将对象从数据库中删除
	 * @param obj：要从数据库删除的对象
	 */
	public void delete(Object obj) {
		this.getHibernateTemplate().delete(obj);		
	}	
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：将多个对象从数据库删除
	 * @param 
	 */
	public void delete(Collection entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}	
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：将对象从数据库中删除(用于事务处理)
	 * @param obj：要从数据库删除的对象
	 * @param session：session对象
	 */
	public void delete(Object obj,Session session) {
		session.delete(obj);		
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：更新对象
	 * @param obj：要在数据库中更新的对象
	 */
	public void update(Object obj) {
		this.getHibernateTemplate().update(obj);	
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：更新对象(用于事务处理)
	 * @param obj：要在数据库中更新的对象
	 * @param session：session对象
	 */
	public void update(Object obj,Session session) {
		session.update(obj);	
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句、参数数组获取单个对象
	 * @param hql：hql语句
	 * @param args：hql语句参数组
	 */
	public Object getSingle(String hql, Object[] args) {
		List<Object> rs=new ArrayList<Object>();
		rs=this.getHibernateTemplate().find(hql,args);
        if(rs.isEmpty()){
            return null;
        }else{
        	return (Object) rs.get(0);
        }
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据id获取单个对象
	 * @param id：主键
	 * @param classes：对象class
	 */
	public Object getSingle(Class classes,Serializable id) {
		Object rs=new Object();
		Session session =this.getHibernateTemplate().getSessionFactory().openSession();
		try {
			rs=session.get(classes,id);
	        if(rs==null){
	            return null;
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return rs;       
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句分页获取对象List
	 * @param hql：hql语句
	 * @param index：页号
	 * @param num：每页显示数量
	 */
	
	public List getList(String hql,int index,int num) {
		List rs=new ArrayList();
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Query query=session.createQuery(hql);
		
		query.setFirstResult((index-1)*num);
		query.setMaxResults(num);
		rs=query.list();
        if(rs.isEmpty()){
        	session.close();
            return null;
        }else{
        	session.close();
        	return rs;
        }
	}	
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句分页获取对象List
	 * @param index：页号
	 * @param num：每页显示数量
	 * @param target_class： 查询的表对应的Class
	 */
	public List getList(int index,int num,Class target_class) {
		List rs=new ArrayList();
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria=session.createCriteria(target_class);
		criteria.setMaxResults(num);
		criteria.setFirstResult((index-1)*num);
		rs=criteria.list();
        if(rs.isEmpty()){
        	session.close();
            return null;
        }else{
        	session.close();
        	return rs;
        }
	}	
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句分页获取对象List
	 * @param index：页号
	 * @param num：每页显示数量
	 * @param target_class： 查询的表对应的Class
	 * @param equal_name：参数名称
	 * @param equal_value：参数数值
	 */
	public List getList(int index,int num,Class target_class,String equal_name,String equal_value) {
		List rs=new ArrayList();
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria=session.createCriteria(target_class);
		criteria.add(Expression.eq(equal_name, new Integer(Integer.parseInt(equal_value))));
		criteria.setMaxResults(num);
		criteria.setFirstResult((index-1)*num);
		rs=criteria.list();
        if(rs.isEmpty()){
        	session.close();
            return null;
        }else{
        	session.close();
        	return rs;
        }
	}	
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句分页获取对象List
	 * @param index：页号
	 * @param num：每页显示数量
	 * @param target_class： 查询的表对应的Class
	 * @param order_param：排序字段
	 */
	public List getList(int index,int num,Class target_class,String order_param) {
		List rs=new ArrayList();
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria=session.createCriteria(target_class);
		criteria.setMaxResults(num);
		criteria.setFirstResult((index-1)*num);
		criteria.addOrder(Order.desc(order_param));
		rs=criteria.list();
        if(rs.isEmpty()){
        	session.close();
            return null;
        }else{
        	session.close();
        	return rs;
        }
	}	
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句分页获取对象List，用于模糊查询
	 * @param hql：hql语句
	 * @param index：页号
	 * @param num：每页显示数量
	 * @param param_name 模糊查询的参数列明
	 * @param param_value 模糊查询的参数数值
	 * @param target_class 模糊查询的表对应的Class
	 * @param order_param：排序字段
	 */
	public List getList(int index,int num,String param_name,String param_value,Class target_class) {
		List rs=new ArrayList();
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria=session.createCriteria(target_class);
		criteria.add(Restrictions.like(param_name, "%"+param_value+"%"));
		criteria.setMaxResults(num);
		criteria.setFirstResult((index-1)*num);
		rs=criteria.list();
        if(rs.isEmpty()){
        	session.close();
            return null;
        }else{
        	session.close();
        	return rs;
        }
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句分页获取对象List，用于模糊查询
	 * @param hql：hql语句
	 * @param index：页号
	 * @param num：每页显示数量
	 * @param param_name 模糊查询的参数列明
	 * @param param_value 模糊查询的参数数值
	 * @param target_class 模糊查询的表对应的Class
	 * @param order_param：排序字段
	 * @param equal_name：参数名称
	 * @param equal_value：参数数值
	 */
	public List getList(int index,int num,String param_name,String param_value,Class target_class,String equal_name,String equal_value) {
		List rs=new ArrayList();
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria=session.createCriteria(target_class);
		criteria.add(Restrictions.like(param_name, "%"+param_value+"%"));
		criteria.add(Expression.eq(equal_name, new Integer(Integer.parseInt(equal_value))));
		criteria.setMaxResults(num);
		criteria.setFirstResult((index-1)*num);
		rs=criteria.list();
        if(rs.isEmpty()){
        	session.close();
            return null;
        }else{
        	session.close();
        	return rs;
        }
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句分页获取对象List，用于模糊查询
	 * @param hql：hql语句
	 * @param index：页号
	 * @param num：每页显示数量
	 * @param param_name 模糊查询的参数列明
	 * @param param_value 模糊查询的参数数值
	 * @param target_class 模糊查询的表对应的Class
	 */
	public List getList(int index,int num,String param_name,String param_value,Class target_class,String order_param) {
		List rs=new ArrayList();
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria=session.createCriteria(target_class);
		criteria.add(Restrictions.like(param_name, "%"+param_value+"%"));
		criteria.addOrder(Order.desc(order_param));
		criteria.setMaxResults(num);
		criteria.setFirstResult((index-1)*num);
		rs=criteria.list();
        if(rs.isEmpty()){
        	session.close();
            return null;
        }else{
        	session.close();
        	return rs;
        }
	}	
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句获取全部对象数量
	 * @param hql：hql语句
	 */
	public int count(String hql) {
		int rs;
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Query query=session.createQuery(hql);
		
		rs=query.list().size();
		session.close();
    	return rs;
	}	
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句获取全部对象数量，用于模糊查询
	 * @param hql：hql语句
	 */
	public int count(String param_name,String param_value,Class target_class) {
		int rs;
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria=session.createCriteria(target_class);
		criteria.add(Restrictions.like(param_name, "%"+param_value+"%"));
		
		rs=criteria.list().size();
		session.close();
    	return rs;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句获取全部对象数量，用于模糊查询
	 * @param hql：hql语句
	 * @param equal_name：参数名称
	 * @param equal_value：参数数值
	 */
	public int count(String param_name,String param_value,Class target_class,String equal_name,String equal_value) {
		int rs;
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria=session.createCriteria(target_class);
		criteria.add(Restrictions.like(param_name, "%"+param_value+"%"));
		criteria.add(Expression.eq(equal_name,  new Integer(Integer.parseInt(equal_value))));
		rs=criteria.list().size();
		session.close();
    	return rs;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：获取事务对象
	 */
	public Session getSessionObject(){
		return this.getHibernateTemplate().getSessionFactory().openSession();
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-03-27
	 * 功能：根据hql语句分页获取对象List
	 * @param hql：hql语句
	 * @param index：页号
	 * @param num：每页显示数量
	 */
	
	public List getList(String hql,Object[] args) {
		List<Object> rs=new ArrayList<Object>();
		rs=this.getHibernateTemplate().find(hql,args);
        if(rs.isEmpty()){
            return null;
        }else{
        	return rs;
        }
	}
	
}
