package com.bjtu.service.share;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bjtu.dao.idao.IFileDao;
import com.bjtu.dao.idao.IShareDao;
import com.bjtu.model.bo.ShareEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_share;

public class ShareServiceImp implements IShareService{
	
	private IShareDao share_dao;
	private IFileDao file_dao;
	public IShareDao getShare_dao() {
		return share_dao;
	}
	public void setShare_dao(IShareDao share_dao) {
		this.share_dao = share_dao;
	}
	public IFileDao getFile_dao() {
		return file_dao;
	}
	public void setFile_dao(IFileDao file_dao) {
		this.file_dao = file_dao;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：插入分享数据
	 * @param share
	 */
	@Override
	public void insert(Tb_share share) {
		share_dao.insert(share);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：根据id查询分享数据
	 * @param id
	 */
	@Override
	public Tb_share getById(String id) {
		return share_dao.getById(id);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-26
	 * 功能：根据id删除一条分享数据
	 * @param id
	 */
	@Override
	public void delete(String id) {
		share_dao.delete(id);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据id获取文件显示名称
	 * @param id
	 */
	@Override
	public String getFileShowName(String id){
		Tb_file file=file_dao.getById(id);
		return file.getShow_name();
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：根据id获取文件后缀
	 * @param id
	 */
	@Override
	public String getFilePostfix(String id) {
		Tb_file file=file_dao.getById(id);
		return file.getPostfix();
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：查询符合条件数据的总页数
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param file_name:模糊查询文件名称
	 */
	@Override
	public int getPageCount(String user_id, int num, String file_name) {
		return share_dao.getPageCount(user_id, num, file_name);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：查询指定页数中的符合条件的数据
	 * @param user_id:用户id
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param file_name:模糊查询文件名称
	 */
	@Override
	public List getPageData(String user_id, int num, int index, String file_name) {
		return share_dao.getPageData(user_id, num, index, file_name);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-05-5
	 * 功能：将Tb_share的List转化为ShareEntity的List
	 * @param share_list
	 * @param img_map
	 */
	@Override
	public List<ShareEntity> convertToShareEntity(List<Tb_share> share_list,
			HashMap<String, String> img_map) {
		
		List<ShareEntity> rs=new ArrayList<ShareEntity>();
		Tb_share tmp;
		ShareEntity se;
		if(share_list!=null&&share_list.size()>0){
        	for(int i=0;i<share_list.size();i++){
        		tmp=share_list.get(i);
        		se=new ShareEntity();
        		se.setId(tmp.getId());
        		se.setDate(tmp.getShare_date());
        		se.setName(tmp.getShow_name()+"."+tmp.getPostfix());
        		String img=img_map.get(tmp.getPostfix());
        		if(img==null){
        			//系统中没有此类型文件，显示默认图标
        			img="/HTP/pages/img/icon/icon_file.png";
        		}
        		se.setImg(img);
        		switch (tmp.getType()) {
					case 1:
						//公开分享
						se.setStatus("公开");
						se.setSpan_class("label-success");
						break;
					case 2:
						//私密分享
						se.setStatus("私人");
						se.setSpan_class("label-info");
						break;
					default:
						//群组分享
						se.setStatus("群组");
						se.setSpan_class("label-warning");
						break;
				}
        		rs.add(se);
        	}
    	}
		
		return rs;
	}
	
}
