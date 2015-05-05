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
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ������������
	 * @param share
	 */
	@Override
	public void insert(Tb_share share) {
		share_dao.insert(share);
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����id��ѯ��������
	 * @param id
	 */
	@Override
	public Tb_share getById(String id) {
		return share_dao.getById(id);
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����idɾ��һ����������
	 * @param id
	 */
	@Override
	public void delete(String id) {
		share_dao.delete(id);
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����id��ȡ�ļ���ʾ����
	 * @param id
	 */
	@Override
	public String getFileShowName(String id){
		Tb_file file=file_dao.getById(id);
		return file.getShow_name();
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����id��ȡ�ļ���׺
	 * @param id
	 */
	@Override
	public String getFilePostfix(String id) {
		Tb_file file=file_dao.getById(id);
		return file.getPostfix();
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param file_name:ģ����ѯ�ļ�����
	 */
	@Override
	public int getPageCount(String user_id, int num, String file_name) {
		return share_dao.getPageCount(user_id, num, file_name);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 * @param file_name:ģ����ѯ�ļ�����
	 */
	@Override
	public List getPageData(String user_id, int num, int index, String file_name) {
		return share_dao.getPageData(user_id, num, index, file_name);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���Tb_share��Listת��ΪShareEntity��List
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
        			//ϵͳ��û�д������ļ�����ʾĬ��ͼ��
        			img="/HTP/pages/img/icon/icon_file.png";
        		}
        		se.setImg(img);
        		switch (tmp.getType()) {
					case 1:
						//��������
						se.setStatus("����");
						se.setSpan_class("label-success");
						break;
					case 2:
						//˽�ܷ���
						se.setStatus("˽��");
						se.setSpan_class("label-info");
						break;
					default:
						//Ⱥ�����
						se.setStatus("Ⱥ��");
						se.setSpan_class("label-warning");
						break;
				}
        		rs.add(se);
        	}
    	}
		
		return rs;
	}
	
}
