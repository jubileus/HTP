package com.bjtu.dao.daoimp;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.dao.idao.IFileTypeDao;
import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.util.dao.DaoUtil;

public class FileTypeDaoImp implements IFileTypeDao{
	
	private DaoUtil dao_util;		
	public DaoUtil getDao_util() {
		return dao_util;
	}
	public void setDao_util(DaoUtil dao_util) {
		this.dao_util = dao_util;
	}


	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ������ļ�����
	 * @param file_type
	 */
	@Override
	public void insert(Tb_file_type file_type) {
		dao_util.insert(file_type);
	}

	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ���ҳ��ѯ�ļ�����
	 * @param index,num,postfix
	 */
	@Override
	public List getByPage(int index, int num,String postfix) {
		List<Tb_file_type> rs=new ArrayList<Tb_file_type>();
		
		if(postfix!=null&&!postfix.equals("")){
			//ģ����ѯ�ض�����������
			rs=dao_util.getList(index, num, "postfix", postfix, Tb_file_type.class,"timestamp");
		}else{
			//��ѯ����
			rs=dao_util.getList( index, num,Tb_file_type.class,"timestamp");
		}
		
		return rs;
	}

	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ���ѯ�ļ���������
	 * @param num,postfix
	 */
	@Override
	public int getPageNum(int num,String postfix) {
		String hql="from Tb_file_type";
		int total=0;
		
		if(postfix!=null&&!postfix.equals("")){
			//ģ����ѯ�ض�����������
			total=dao_util.count("postfix", postfix, Tb_file_type.class);
		}else{
			//��ѯ����
			total=dao_util.count(hql);
		}
		
		int rs=1;
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
	 * ��д���ڣ�2015-04-14
	 * ���ܣ����ݺ�׺��ȡ�ļ�������Ϣ
	 * @param file_type
	 */
	@Override
	public Tb_file_type getFileTypeByPostfix(Tb_file_type file_type) {
		String hql = "from Tb_file_type where postfix=?";
		Object[] objects = new Object[1];
		objects[0] = file_type.getPostfix();
		return (Tb_file_type)dao_util.getSingle(hql, objects);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ�ɾ���ļ�����
	 * @param id
	 */
	@Override
	public void delete(String id) {
		String hql = "from Tb_file_type where id=?";
		Object[] objects = new Object[1];
		objects[0] = id;
		Tb_file_type file_type= (Tb_file_type)dao_util.getSingle(hql, objects);
		dao_util.delete(file_type);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-14
	 * ���ܣ���ȡȫ���ļ�����
	 */
	@Override
	public List getAll() {
		String hql = "from Tb_file_type";
		Object[] objects = new Object[0];
		return dao_util.getList(hql, objects);
	}
	
	/**
	 * @author ���ĳ�
	 * ��д���ڣ�2015-04-29
	 * ���ܣ�����category��ȡ�ļ�����
	 */
	@Override
	public List getByCategory(int category) {
		String hql = "from Tb_file_type where category=?";
		Object[] objects = new Object[1];
		objects[0] = category;
		return dao_util.getList(hql, objects);
	}

}
