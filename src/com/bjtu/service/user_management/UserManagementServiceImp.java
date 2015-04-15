package com.bjtu.service.user_management;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.dao.idao.IUserDao;
import com.bjtu.model.bo.UserEntity;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.util.common.DataFormatUtil;

public class UserManagementServiceImp implements IUserManagementService{
	
	private IUserDao user_dao;
	public IUserDao getUser_dao() {
		return user_dao;
	}
	public void setUser_dao(IUserDao user_dao) {
		this.user_dao = user_dao;
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-4-15
	 * 功能：分页查询用户信息
	 * @param index,num,nickname
	 */
	@Override
	public List getUserTypeByPage(int index, int num, String nickname) {
		return user_dao.getByPage(index, num, nickname);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-4-15
	 * 功能：查询用户信息总数
	 * @param num,nickname
	 */
	@Override
	public int getUserTypePageCount(int num, String nickname) {
		return user_dao.getPageNum(num, nickname);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-15
	 * 功能：将Tb_user对象的List转化为UserEntity对象的List加以返回
	 * @param user_list
	 */
	@Override
	public List convertToUserEntityList(List<Tb_user> user_list) {
		List<UserEntity> rs=new ArrayList<UserEntity>();
		Tb_user tmp;
		UserEntity ue;
		if(user_list!=null&&user_list.size()>0){
			for(int i=0;i<user_list.size();i++){
				tmp=user_list.get(i);
				ue=new UserEntity();
				ue.setEmail(tmp.getEmail());
				ue.setId(tmp.getId());
				ue.setImg(tmp.getImg());
				ue.setNickname(tmp.getNickname());
				ue.setRegister_time(tmp.getRegister_time());
				if(tmp.getStatus()==0){
					//停用状态
					ue.setStatus_name("激活");
				}else{
					//激活状态
					ue.setStatus_name("停用");
				}
				ue.setTotal_storage(String.valueOf(DataFormatUtil.doubleRound(tmp.getTotal_storage()/1024, 2, 1)));
				ue.setUsed_storage(String.valueOf(DataFormatUtil.doubleRound(tmp.getUsed_storage()/1024, 2, 1)));
				rs.add(ue);
			}
		}
		
		return rs;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-15
	 * 功能：更新用户状态
	 * @param user
	 */
	@Override
	public void modifyStatus(Tb_user user) {
		user_dao.updateStatus(user);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-15
	 * 功能：更新总存储空间
	 * @param user
	 */
	@Override
	public void modifyTotalStorage(Tb_user user) {
		user_dao.updateTotalStorage(user);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-15
	 * 功能：根据id获得用户信息
	 * @param id
	 */
	@Override
	public Tb_user getUserById(String id) {
		return user_dao.getById(id);
	}

}
