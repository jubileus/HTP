package com.bjtu.dao.daoimp;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.dao.idao.IUserDao;
import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.util.dao.DaoUtil;

public class UserDaoImp implements IUserDao{

	private DaoUtil dao_util;		
	public DaoUtil getDao_util() {
		return dao_util;
	}
	public void setDao_util(DaoUtil dao_util) {
		this.dao_util = dao_util;
	}

	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-03-27
	 * ���ܣ������û�
	 * @param user
	 */
	@Override
	public void insert(Tb_user user) {
		dao_util.insert(user);
	}

	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����id��ѯ�û�
	 * @param id
	 */
	@Override
	public Tb_user getById(String id) {
		String hql = "from Tb_user where id=?";
		Object[] objects = new Object[1];
		objects[0] = id;
		return (Tb_user)dao_util.getSingle(hql, objects);
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����email,password��ѯ�û�
	 * @param user
	 */
	@Override
	public Tb_user getByEmailPassword(Tb_user user) {
		String hql = "from Tb_user where email=? and password=?";
		Object[] objects = new Object[2];
		objects[0] = user.getEmail();
		objects[1] = user.getPassword();
		return (Tb_user)dao_util.getSingle(hql, objects);
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-03-27
	 * ���ܣ�����email��ѯ�û�
	 * @param user
	 */
	@Override
	public Tb_user getByEmail(Tb_user user) {
		String hql = "from Tb_user where email=?";
		Object[] objects = new Object[1];
		objects[0] = user.getEmail();
		return (Tb_user)dao_util.getSingle(hql, objects);
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-13
	 * ���ܣ�����role��ѯ�û�
	 * @param user
	 */
	@Override
	public List getByRole(Tb_user user) {
		String hql = "from Tb_user where role=?";
		Object[] objects = new Object[1];
		objects[0] = user.getRole();
		return dao_util.getList(hql, objects);
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-13
	 * ���ܣ�ɾ���û�
	 * @param id
	 */
	@Override
	public void delete(String id) {
		String hql = "from Tb_user where id=?";
		Object[] objects = new Object[1];
		objects[0] = id;
		Tb_user user= (Tb_user)dao_util.getSingle(hql, objects);
		dao_util.delete(user);
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-15
	 * ���ܣ���ҳ��ѯ�û���Ϣ
	 * @param index,num,nickname
	 */
	@Override
	public List getByPage(int index, int num, String nickname) {
		List<Tb_user> rs=new ArrayList<Tb_user>();
		
		if(nickname!=null&&!nickname.equals("")){
			//ģ����ѯ�ض�����������
			rs=dao_util.getList(index, num, "nickname", nickname, Tb_user.class,"role","0");
		}else{
			//��ѯ����
			rs=dao_util.getList( index, num,Tb_user.class,"role","0");
		}
		
		return rs;
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-15
	 * ���ܣ���ѯ�û���Ϣ����
	 * @param num,nickname
	 */
	@Override
	public int getPageNum(int num, String nickname) {
		String hql="from Tb_user where role=0";
		int total=0;
		
		if(nickname!=null&&!nickname.equals("")){
			//ģ����ѯ�ض�����������
			total=dao_util.count("nickname", nickname, Tb_user.class,"role","0");
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
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-15
	 * ���ܣ������û�״̬
	 * @param user
	 */
	@Override
	public void updateStatus(Tb_user user) {
		String hql = "from Tb_user where id = ?";
		Object[] objects = new Object[1];
		objects[0]=user.getId();
		Tb_user temp=(Tb_user)dao_util.getSingle(hql, objects);
		if(temp.getStatus()==0){
			temp.setStatus(1);
		}else{
			temp.setStatus(0);
		}
		dao_util.update(temp);
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-15
	 * ���ܣ������û��ܴ洢�ռ�
	 * @param user
	 */
	@Override
	public void updateTotalStorage(Tb_user user) {
		String hql = "from Tb_user where id = ?";
		Object[] objects = new Object[1];
		objects[0]=user.getId();
		Tb_user temp=(Tb_user)dao_util.getSingle(hql, objects);
		temp.setTotal_storage(user.getTotal_storage());
		dao_util.update(temp);
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-20
	 * ���ܣ������û����ô洢�ռ�
	 * @param user
	 */
	@Override
	public void updateUsedStorage(Tb_user user) {
		String hql = "from Tb_user where id = ?";
		Object[] objects = new Object[1];
		objects[0]=user.getId();
		Tb_user temp=(Tb_user)dao_util.getSingle(hql, objects);
		temp.setUsed_storage(user.getUsed_storage());
		dao_util.update(temp);
	}
	
	
}
