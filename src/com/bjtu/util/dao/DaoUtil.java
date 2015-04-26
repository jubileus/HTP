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
 * @author ����
 * ��д���ڣ�2015-03-27
 * ���ܣ�dao�㹤����,������hibernate  
 */

public class DaoUtil extends HibernateDaoSupport{
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�������������ݿ�
	 * @param obj��Ҫ�������ݿ�Ķ���
	 */
	public void insert(Object obj) {
		this.getHibernateTemplate().save(obj);			
	}		
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�������������ݿ�(����������)
	 * @param obj��Ҫ�������ݿ�Ķ���
	 * @param session��session����
	 */
	public void insert(Object obj,Session session) {
		session.save(obj);		
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����������ݿ���ɾ��
	 * @param obj��Ҫ�����ݿ�ɾ���Ķ���
	 */
	public void delete(Object obj) {
		this.getHibernateTemplate().delete(obj);		
	}	
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ��������������ݿ�ɾ��
	 * @param 
	 */
	public void delete(Collection entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}	
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����������ݿ���ɾ��(����������)
	 * @param obj��Ҫ�����ݿ�ɾ���Ķ���
	 * @param session��session����
	 */
	public void delete(Object obj,Session session) {
		session.delete(obj);		
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ����¶���
	 * @param obj��Ҫ�����ݿ��и��µĶ���
	 */
	public void update(Object obj) {
		this.getHibernateTemplate().update(obj);	
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ����¶���(����������)
	 * @param obj��Ҫ�����ݿ��и��µĶ���
	 * @param session��session����
	 */
	public void update(Object obj,Session session) {
		session.update(obj);	
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql��䡢���������ȡ��������
	 * @param hql��hql���
	 * @param args��hql��������
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����id��ȡ��������
	 * @param id������
	 * @param classes������class
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ҳ��ȡ����List
	 * @param hql��hql���
	 * @param index��ҳ��
	 * @param num��ÿҳ��ʾ����
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ҳ��ȡ����List
	 * @param index��ҳ��
	 * @param num��ÿҳ��ʾ����
	 * @param target_class�� ��ѯ�ı��Ӧ��Class
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ҳ��ȡ����List
	 * @param index��ҳ��
	 * @param num��ÿҳ��ʾ����
	 * @param target_class�� ��ѯ�ı��Ӧ��Class
	 * @param equal_name����������
	 * @param equal_value��������ֵ
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ҳ��ȡ����List
	 * @param index��ҳ��
	 * @param num��ÿҳ��ʾ����
	 * @param target_class�� ��ѯ�ı��Ӧ��Class
	 * @param order_param�������ֶ�
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ҳ��ȡ����List������ģ����ѯ
	 * @param hql��hql���
	 * @param index��ҳ��
	 * @param num��ÿҳ��ʾ����
	 * @param param_name ģ����ѯ�Ĳ�������
	 * @param param_value ģ����ѯ�Ĳ�����ֵ
	 * @param target_class ģ����ѯ�ı��Ӧ��Class
	 * @param order_param�������ֶ�
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ҳ��ȡ����List������ģ����ѯ
	 * @param hql��hql���
	 * @param index��ҳ��
	 * @param num��ÿҳ��ʾ����
	 * @param param_name ģ����ѯ�Ĳ�������
	 * @param param_value ģ����ѯ�Ĳ�����ֵ
	 * @param target_class ģ����ѯ�ı��Ӧ��Class
	 * @param order_param�������ֶ�
	 * @param equal_name����������
	 * @param equal_value��������ֵ
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ҳ��ȡ����List������ģ����ѯ
	 * @param hql��hql���
	 * @param index��ҳ��
	 * @param num��ÿҳ��ʾ����
	 * @param param_name ģ����ѯ�Ĳ�������
	 * @param param_value ģ����ѯ�Ĳ�����ֵ
	 * @param target_class ģ����ѯ�ı��Ӧ��Class
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ȡȫ����������
	 * @param hql��hql���
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ȡȫ����������������ģ����ѯ
	 * @param hql��hql���
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ȡȫ����������������ģ����ѯ
	 * @param hql��hql���
	 * @param equal_name����������
	 * @param equal_value��������ֵ
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
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ���ȡ�������
	 */
	public Session getSessionObject(){
		return this.getHibernateTemplate().getSessionFactory().openSession();
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����hql����ҳ��ȡ����List
	 * @param hql��hql���
	 * @param index��ҳ��
	 * @param num��ÿҳ��ʾ����
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
