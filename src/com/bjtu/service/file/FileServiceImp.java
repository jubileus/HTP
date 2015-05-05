package com.bjtu.service.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.bjtu.dao.idao.IFileDao;
import com.bjtu.dao.idao.IShareDao;
import com.bjtu.dao.idao.IUserDao;
import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.bo.ListFileEntity;
import com.bjtu.model.bo.PathEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_file_type;
import com.bjtu.model.pojo.Tb_share;
import com.bjtu.model.pojo.Tb_user;

public class FileServiceImp implements IFileService{
	
	private IUserDao user_dao;
	private IFileDao file_dao;
	private IShareDao share_dao;
	public IFileDao getFile_dao() {
		return file_dao;
	}
	public void setFile_dao(IFileDao file_dao) {
		this.file_dao = file_dao;
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
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ������ļ�����
	 * @param user
	 */
	@Override
	public void insert(Tb_file file) {
		file_dao.insert(file);
	}

	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�����id��ѯ�ļ�
	 * @param id
	 */
	@Override
	public Tb_file getById(String id) {
		return file_dao.getById(id);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param num:ÿҳ��ʾ����
	 * @param path:�ļ�·��
	 * @param file_name:�ļ���ʾ����
	 */
	@Override
	public int getPageCount(int num, String path, String file_name) {
		return file_dao.getPageCount(num, path, file_name);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 * @param path:�ļ�·��
	 * @param file_name:�ļ���ʾ����
	 */
	@Override
	public List<Tb_file> getPageData(int num, int index, String path, String file_name) {
		return (List<Tb_file>)file_dao.getPageData(num, index, path, file_name);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���Tb_file��Listת��ΪGalleryFileEntity��List
	 * @param file_list
	 */
	@Override
	public List<GalleryFileEntity> convertToGalleryFileEntity(
			List<Tb_file> file_list,HashMap<String, String> img_map) {
		
		List<GalleryFileEntity> rs=new ArrayList<GalleryFileEntity>();
		
		Tb_file tmp;
        GalleryFileEntity gae;
        if(file_list!=null&&file_list.size()>0){
        	for(int i=0;i<file_list.size();i++){
        		tmp=file_list.get(i);
        		gae=new GalleryFileEntity();
        		gae.setCheck_id("check_"+tmp.getId());
        		gae.setFile_id("file_"+tmp.getId());
        		gae.setFile_name_id("file_name_"+tmp.getId());
        		gae.setShow_name_id("name_"+tmp.getId());
        		String img=img_map.get(tmp.getPostfix());
        		if(img==null){
        			//ϵͳ��û�д������ļ�����ʾĬ��ͼ��
        			img="/HTP/pages/img/icon/icon_file.png";
        		}
        		gae.setImg(img);
        		if(tmp.getIs_folder()==1){
        			//���ļ��У���ƴ�Ӻ�׺��
        			gae.setName(tmp.getShow_name());
        			gae.setImg_id(tmp.getHdfs_name());
        			gae.setRight_class("folder_item");
        		}else{
        			//�����ļ��У�ƴ�Ӻ�׺��
        			gae.setName(tmp.getShow_name()+"."+tmp.getPostfix());
        			gae.setImg_id("not_"+tmp.getHdfs_name());
        			gae.setRight_class("file_item");
        		}
        		
        		rs.add(gae);
        	}
        }
		return rs;
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ȡ�û�id��·��ʵ��List
	 * @param user_id
	 * @param path
	 */
	public List<PathEntity> getPathList(String user_id,String path) {
		//�û���Ŀ¼����Ϊ56���ַ�
		int root_length=56;
		//ÿһ��Ŀ¼����Ϊ14���ַ�
		int folder_length=14;
		
		List<PathEntity> rs=new ArrayList<PathEntity>();
		//����Ŀ¼�����List
		PathEntity pe=new PathEntity();
		pe.setName("Home");
		pe.setPath(path.substring(0, root_length));
		rs.add(pe);
		if(path.length()>56){
			//����Ŀ¼����Ϊ1
			int folder_num=0;
			
			//��Ŀ¼���Ǹ�Ŀ¼
			StringTokenizer st=new StringTokenizer(path.substring(root_length),"/");
			while(st.hasMoreElements()){
				folder_num++;
				String hdfs_name=st.nextToken();
				Tb_file file=file_dao.getByUserIdAndHDFSName(user_id, hdfs_name);
				pe=new PathEntity();
				pe.setName(file.getShow_name());
				pe.setPath(path.substring(0, root_length+folder_num*folder_length));
				rs.add(pe);
			}
		}
		return rs;
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯ�ļ������Ƿ����ͬ���ļ�
	 * @param file
	 */
	@Override
	public boolean ifNewFileExist(Tb_file file) {
		if(file_dao.getByUserId_ShowName_Postfix(file)==null){
			//û��ͬ���ļ�
			return false;
		}else{
			return true;
		}
	}

	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ���ѯ�ļ������Ƿ����ͬ���ļ�,�����򷵻�����ͬ���ļ�������ֵ���������򷵻�0
	 * @param file
	 */
	@Override
	public int getNewFileNum(Tb_file file) {
		int rs=0;
		List<Tb_file> file_list=file_dao.getListByUserId_ShowName_Postfix(file);
		//����Ŀǰ���ı��
		String tmp;
		int start_index;
		String num;
		int current_num;
		if(file_list!=null&&file_list.size()>0){
			for(int i=0;i<file_list.size();i++){
				tmp= file_list.get(i).getShow_name();
				start_index=tmp.lastIndexOf("(");
				num=tmp.substring(start_index+1, tmp.length()-1);
				try {
					current_num=Integer.parseInt(num);
					if(current_num>rs){
						rs=current_num;
					}
				} catch (Exception e) {
					//����ת��Ϊ���֣�������Ҫ������
				}
			}
		}
		//���������+1
		return rs+1;
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ��޸��ļ�show_name
	 * @param file
	 */
	@Override
	public void modifyShowName(Tb_file file) {
		file_dao.modifyShowName(file);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-18
	 * ���ܣ�ɾ���ļ����ļ���
	 * @param id
	 */
	@Override
	public void deleteFolderOrFile(String id) {
		file_dao.delete(id);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-20
	 * ���ܣ������û����ô洢�ռ�
	 * @param user
	 */
	@Override
	public void updateUsedStorage(Tb_user user) {
		user_dao.updateUsedStorage(user);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-21
	 * ���ܣ��޸��ļ�is_complete
	 * @param file
	 */
	@Override
	public void modifyIs_complete(Tb_file file) {
		file_dao.modifyIs_complete(file);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-29
	 * ���ܣ���Tb_file��Listת��ΪListFileEntity��List
	 * @param file_list
	 * @param img_map
	 */
	@Override
	public List<ListFileEntity> convertToListFileEntity(
			List<Tb_file> file_list, HashMap<String, String> img_map) {

		List<ListFileEntity> rs=new ArrayList<ListFileEntity>();
		
		Tb_file tmp;
        ListFileEntity lfe;
        if(file_list!=null&&file_list.size()>0){
        	for(int i=0;i<file_list.size();i++){
        		tmp=file_list.get(i);
        		lfe=new ListFileEntity();
        		lfe.setDate(tmp.getCreate_time());
        		lfe.setId(tmp.getId());
        		lfe.setDeleteFile_id("delete_"+tmp.getId());
        		lfe.setCheck_id("check_"+tmp.getId());
        		lfe.setShareFile_id("share_"+tmp.getId());
        		lfe.setDownloadFile_id("download_"+tmp.getId());
        		lfe.setName_td_id("name_"+tmp.getId());
        		lfe.setRenameFile_id("rename_"+tmp.getId());
        		String img=img_map.get(tmp.getPostfix());
        		if(img==null){
        			//ϵͳ��û�д������ļ�����ʾĬ��ͼ��
        			img="/HTP/pages/img/icon/icon_file.png";
        		}
        		lfe.setImg(img);
        		if(tmp.getIs_folder()==1){
        			//���ļ��У���ƴ�Ӻ�׺��
        			lfe.setName(tmp.getShow_name());
        			lfe.setDelete_file("ɾ��");
        			lfe.setDownload_file("");
        			lfe.setRename_file("������");
        			lfe.setShare_file("");
        			lfe.setSize("");
        			lfe.setImg_id(tmp.getHdfs_name());
        		}else{
        			//�����ļ��У�ƴ�Ӻ�׺��
        			lfe.setName(tmp.getShow_name()+"."+tmp.getPostfix());
        			lfe.setDelete_file("ɾ��");
        			lfe.setDownload_file("����");
        			lfe.setRename_file("������");
        			lfe.setShare_file("����");
        			lfe.setSize(String.valueOf(tmp.getSize_mb())+" MB");
        			lfe.setImg_id("not_"+tmp.getHdfs_name());
        		}
        		
        		rs.add(lfe);
        	}
        }
		return rs;
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-29
	 * ���ܣ���ѯ�����������ݵ���ҳ��
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param category���ļ�����
	 * @param file_name:�ļ���ʾ����
	 */
	@Override
	public int getPageCount(String user_id, int num, int category,
			String file_name) {
		return file_dao.getPageCount(user_id, num, category, file_name);
	}
	
	/**
	 * @author �չ�ΰ
	 * ��д���ڣ�2015-04-29
	 * ���ܣ���ѯָ��ҳ���еķ�������������
	 * @param user_id:�û�id
	 * @param num:ÿҳ��ʾ����
	 * @param index:׼����ȡ�����ݵĶ�Ӧҳ��
	 * @param category���ļ�����
	 * @param file_name:�ļ���ʾ����
	 */
	@Override
	public List getPageData(String user_id, int num, int index, int category,
			String file_name) {
		return file_dao.getPageData(user_id, num, index, category, file_name);
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-05-5
	 * ���ܣ��޸ķ������ʾ����
	 * @param share
	 */
	@Override
	public void modifyShowName(Tb_share share) {
		share_dao.modifyShowname(share);
	}
	
}
