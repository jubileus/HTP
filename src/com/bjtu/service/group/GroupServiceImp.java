package com.bjtu.service.group;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.dao.idao.IGroupDao;
import com.bjtu.dao.idao.IMemberDao;
import com.bjtu.dao.idao.IShareDao;
import com.bjtu.dao.idao.IUserDao;
import com.bjtu.model.bo.GroupShareEntity;
import com.bjtu.model.pojo.Tb_group;
import com.bjtu.model.pojo.Tb_member;
import com.bjtu.model.pojo.Tb_share;
import com.bjtu.model.pojo.Tb_user;

public class GroupServiceImp implements IGroupService{
	
	private IGroupDao group_dao;
	private IMemberDao member_dao;
	private IUserDao user_dao;
	private IShareDao share_dao;
	public IGroupDao getGroup_dao() {
		return group_dao;
	}
	public void setGroup_dao(IGroupDao group_dao) {
		this.group_dao = group_dao;
	}
	public IMemberDao getMember_dao() {
		return member_dao;
	}
	public void setMember_dao(IMemberDao member_dao) {
		this.member_dao = member_dao;
	}
	public IUserDao getUser_dao() {
		return user_dao;
	}
	public void setUser_dao(IUserDao user_dao) {
		this.user_dao = user_dao;
	}
	public IShareDao getShare_dao() {
		return share_dao;
	}
	public void setShare_dao(IShareDao share_dao) {
		this.share_dao = share_dao;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：插入群组
	 * @param group
	 */
	@Override
	public void insert(Tb_group group) {
		group_dao.insert(group);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：根据id查询群组
	 * @param id
	 */
	@Override
	public Tb_group getById(String id) {
		return group_dao.getById(id);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：删除群组
	 * @param id
	 */
	@Override
	public void delete(String id) {
		group_dao.delete(id);		
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：获取群组信息
	 * @param user_id
	 */
	@Override
	public List getGroup(String user_id) {
		List<Tb_member> member_list=member_dao.getByUserId(user_id);
		List<Tb_group> rs=new ArrayList<Tb_group>();
		Tb_group group;
		Tb_member member;
		if(member_list!=null&&member_list.size()>0){
			for(int i=0;i<member_list.size();i++){
				member=member_list.get(i);
				group=group_dao.getById(member.getGroup_id());
				rs.add(group);
			}
		}
		
		return rs;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：判断同名群组是否存在
	 * @param group
	 */
	@Override
	public boolean ifExist(Tb_group group) {
		if(group_dao.getByCreator_id_Name(group)!=null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：判断待添加的群组成员是否存在
	 * @param user
	 */
	@Override
	public Tb_user ifMemberExist(Tb_user user) {
		return user_dao.getByEmail(user);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：判断待添加的群组成员是否已经在本群组中
	 * @param member
	 */
	@Override
	public Tb_member ifMemberInGroup(Tb_member member){
		return member_dao.getByGroupId_UserId(member);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：根据group_id获取组员信息
	 * @param group_id
	 */
	@Override
	public List getMemberByGroupId(String group_id) {
		return member_dao.getMember(group_id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-25
	 * 功能：插入群组成员
	 * @param member
	 */
	@Override
	public void insertMember(Tb_member member) {
		member_dao.insert(member);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：删除单个群组成员
	 * @param id
	 */
	@Override
	public void deleteSingleMember(String id) {
		member_dao.delete(id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：根据group_id删除群组成员
	 * @param group_id
	 */
	@Override
	public void deleteMembers(String group_id) {
		member_dao.deleteByGroupId(group_id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据id查询组员
	 * @param id
	 */
	@Override
	public Tb_member getMemberById(String id) {
		return member_dao.getById(id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据creator_id删除分享
	 * @param creator_id
	 */
	@Override
	public void deleteByCreatorId(String creator_id) {
		share_dao.deleteByCreatorId(creator_id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据group_id删除分享
	 * @param group_id
	 */
	@Override
	public void deleteByGroupId(String group_id) {
		share_dao.deleteByGroupId(group_id);		
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据creator_id获取群组信息
	 * @param creator_id
	 */
	@Override
	public List getGroupByCreatorId(String creator_id) {
		return group_dao.getGroup(creator_id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：查询符合条件数据的总页数
	 * @param group_id:群组id
	 * @param num:每页显示数量
	 */
	@Override
	public int getPageCount(String group_id, int num) {
		return share_dao.getPageCount(group_id, num);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：查询指定页数中的符合条件的数据
	 * @param group_id:群组id
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 */
	@Override
	public List getPageData(String group_id, int num, int index) {
		return share_dao.getPageData(group_id, num, index);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：将Tb_share的List转化为GroupShareEntity的List
	 * @param share_list
	 * @param user_id
	 */
	@Override
	public List<GroupShareEntity> convertToGroupShareEntity(
			List<Tb_share> share_list, String user_id) {
		
		List<GroupShareEntity> rs=new ArrayList<GroupShareEntity>();
		Tb_share tmp;
		GroupShareEntity gse;
		if(share_list!=null&&share_list.size()>0){
        	for(int i=0;i<share_list.size();i++){
        		tmp=share_list.get(i);
        		gse=new GroupShareEntity();
        		gse.setDate(tmp.getShare_date());
        		gse.setId(tmp.getId());
        		gse.setFile_id(tmp.getFile_id());
        		gse.setName(tmp.getShow_name()+"."+tmp.getPostfix());
        		Tb_user user=user_dao.getById(tmp.getCreator_id());
        		gse.setNickname(user.getNickname());
        		if(tmp.getCreator_id().equals(user_id)){
        			gse.setLi_class("odd");
        		}else{
        			gse.setLi_class("even");
        		}
        		rs.add(gse);
        	}
    	}
		
		return rs;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据id获取分享
	 * @param id
	 */
	@Override
	public Tb_share getShareById(String id) {
		return share_dao.getById(id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据id删除单个分享
	 * @param id
	 */
	@Override
	public void deleteShare(String id) {
		share_dao.delete(id);
	}

	
}
