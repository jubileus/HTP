package com.bjtu.service.share;

import java.util.HashMap;
import java.util.List;

import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.bo.ShareEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_share;

public interface IShareService {

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ������������
	 * @param share
	 */
	public void insert(Tb_share share);
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����id��ѯ��������
	 * @param id
	 */
    public Tb_share getById(String id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-04-26
	 * ���ܣ�����idɾ��һ����������
	 * @param id
	 */
    public void delete(String id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����id��ȡ�ļ���ʾ����
	 * @param id
	 */
    public String getFileShowName(String id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ�����id��ȡ�ļ���׺
	 * @param id
	 */
    public String getFilePostfix(String id);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param file_name:ģ����ѯ�ļ�����
	 */
    public int getPageCount(String user_id,int num,String file_name);

    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 * @param file_name:ģ����ѯ�ļ�����
	 */
    public List getPageData(String user_id,int num,int index,String file_name);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ���Tb_share��Listת��ΪShareEntity��List
	 * @param share_list
	 * @param img_map
	 */
    public List<ShareEntity> convertToShareEntity(List<Tb_share> share_list,HashMap<String, String> img_map);
}
