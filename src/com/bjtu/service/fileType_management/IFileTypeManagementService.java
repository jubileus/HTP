package com.bjtu.service.fileType_management;

import java.util.List;

import com.bjtu.model.pojo.Tb_file_type;

public interface IFileTypeManagementService {
	
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ�����ļ�������Ϣ
	 * @param file_type
	 */
    public void addFileType(Tb_file_type file_type);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ���ҳ��ѯ�ļ�������Ϣ
	 * @param index,num,postfix
	 */
    public List getFileTypeByPage(int index,int num,String postfix);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ���ѯ�ļ���������
	 * @param num,postfix
	 */
    public int getFileTypePageCount(int num,String postfix);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-13
	 * ���ܣ���Tb_file_type�����Listת��ΪFileTypeEntity�����List���Է���
	 * @param file_type_list
	 */
    public List convertToFileTypeEntityList(List<Tb_file_type> file_type_list);
    
    /**
	 * @author ����
	 * ��д���ڣ�2015-4-14
	 * ���ܣ�����׺�Ƿ��Ѵ���
	 * @param file_type
	 */
    public int ifPostfixExist(Tb_file_type file_type);
    
    /**
   	 * @author ����
   	 * ��д���ڣ�2015-4-13
   	 * ���ܣ�ɾ���ļ�����
   	 * @param id
   	 */
    public void deleteFileType(String id);
    
}
