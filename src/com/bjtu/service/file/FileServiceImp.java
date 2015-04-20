package com.bjtu.service.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.bjtu.dao.idao.IFileDao;
import com.bjtu.dao.idao.IUserDao;
import com.bjtu.model.bo.GalleryFileEntity;
import com.bjtu.model.bo.PathEntity;
import com.bjtu.model.pojo.Tb_file;
import com.bjtu.model.pojo.Tb_user;

public class FileServiceImp implements IFileService{
	
	private IUserDao user_dao;
	private IFileDao file_dao;
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
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：插入文件数据
	 * @param user
	 */
	@Override
	public void insert(Tb_file file) {
		file_dao.insert(file);
	}

	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：根据id查询文件
	 * @param id
	 */
	@Override
	public Tb_file getById(String id) {
		return file_dao.getById(id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询符合条件数据的总页数
	 * @param num:每页显示数量
	 * @param path:文件路径
	 * @param file_name:文件显示名称
	 */
	@Override
	public int getPageCount(int num, String path, String file_name) {
		return file_dao.getPageCount(num, path, file_name);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询指定页数中的符合条件的数据
	 * @param num:每页显示数量
	 * @param index:准备获取的数据的对应页数
	 * @param path:文件路径
	 * @param file_name:文件显示名称
	 */
	@Override
	public List<Tb_file> getPageData(int num, int index, String path, String file_name) {
		return (List<Tb_file>)file_dao.getPageData(num, index, path, file_name);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：将Tb_file的List转化为GalleryFileEntity的List
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
        			//系统中没有此类型文件，显示默认图标
        			img="/HTP/pages/img/icon/icon_file.png";
        		}
        		gae.setImg(img);
        		if(tmp.getIs_folder()==1){
        			//是文件夹，不拼接后缀名
        			gae.setName(tmp.getShow_name());
        			gae.setImg_id(tmp.getHdfs_name());
        			gae.setRight_class("folder_item");
        		}else{
        			//不是文件夹，拼接后缀名
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
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：获取用户id和路径实体List
	 * @param user_id
	 * @param path
	 */
	public List<PathEntity> getPathList(String user_id,String path) {
		//用户根目录长度为56个字符
		int root_length=56;
		//每一层目录长度为14个字符
		int folder_length=14;
		
		List<PathEntity> rs=new ArrayList<PathEntity>();
		//将根目录添加入List
		PathEntity pe=new PathEntity();
		pe.setName("Home");
		pe.setPath(path.substring(0, root_length));
		rs.add(pe);
		if(path.length()>56){
			//设置目录层数为1
			int folder_num=0;
			
			//该目录不是根目录
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
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询文件名称是否存在同名文件
	 * @param file
	 */
	@Override
	public boolean ifNewFileExist(Tb_file file) {
		if(file_dao.getByUserId_ShowName_Postfix(file)==null){
			//没有同名文件
			return false;
		}else{
			return true;
		}
	}

	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：查询文件名称是否存在同名文件,存在则返回已有同名文件编号最大值，不存在则返回0
	 * @param file
	 */
	@Override
	public int getNewFileNum(Tb_file file) {
		int rs=0;
		List<Tb_file> file_list=file_dao.getListByUserId_ShowName_Postfix(file);
		//查找目前最大的标号
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
					//不能转换为数字，则不是需要的数据
				}
			}
		}
		//返回最大编号+1
		return rs+1;
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：修改文件show_name
	 * @param file
	 */
	@Override
	public void modifyShowName(Tb_file file) {
		file_dao.modifyShowName(file);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-18
	 * 功能：删除文件或文件夹
	 * @param id
	 */
	@Override
	public void deleteFolderOrFile(String id) {
		file_dao.delete(id);
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-20
	 * 功能：更新用户已用存储空间
	 * @param user
	 */
	@Override
	public void updateUsedStorage(Tb_user user) {
		user_dao.updateUsedStorage(user);
	}
	
}
