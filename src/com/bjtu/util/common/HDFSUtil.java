package com.bjtu.util.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSUtil {
	private static String PORT_INFO="hdfs://192.168.10.133:9000";
	private static String ROOT_PATH="hdfs://192.168.10.133:9000/user/hadoop";
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-12
	 * ���ܣ�����Ŀ¼���ļ��У�
	 * @param path���ļ�·��
	 */
	public static void createFolder(String path){	
		try{
			Configuration conf = new Configuration();
		    FileSystem fs;
			fs = FileSystem.get(URI.create(PORT_INFO+path),conf);
			Path srcPath = new Path(path);
		    fs.mkdirs(srcPath);
		    fs.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-19
	 * ���ܣ�ɾ��Ŀ¼���ļ�
	 * @param path��Ŀ¼���ļ�·��
	 * @param fs
	 */
	public static void deleteFolderOrFile(String path){
		try{
			Configuration conf = new Configuration();
			FileSystem fs=FileSystem.get(URI.create(PORT_INFO+path),conf);
			Path srcPath = new Path(path);
		    fs.delete(srcPath, true);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-21
	 * ���ܣ����ļ�Ŀ¼
	 */
	public static FileSystem openFileSystem(String path){
	    try {
	    	Configuration conf = new Configuration();
		    FileSystem fs;
			return FileSystem.get(URI.create(PORT_INFO+path),conf);
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-21
	 * ���ܣ���ָ��Ŀ¼�µ�Ŀ���ļ���׷������
	 * @param @param path���ļ�·��
	 * @param fs
	 */
	public static FSDataOutputStream getFileOutputStream(String path,FileSystem fs){
		try {
			return fs.append(new Path(path));
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-21
	 * ���ܣ���ָ��Ŀ¼�µĽ����հ��ļ�
	 * @param fs
	 * @param path���ļ�·��
	 */
	public static void createNewFile(String path,FileSystem fs){
		try {
			fs.createNewFile(new Path(path));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-21
	 * ���ܣ���ָ��Ŀ¼�µ�Ŀ���ļ���׷������
	 * @param dst��Ŀ���ļ�
	 * @param buffer��׷�ӵ�����
	 */
	public static void appendFile(FSDataOutputStream dst,byte[] buffer){
		try {
			dst.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-04-23
	 * ���ܣ���HDFS�������ļ�
	 * @param path��Ŀ���ļ�
	 * @param file_name���ļ�����
	 */
	public static FSDataInputStream download(String path,String file_name){
		try {
			Configuration conf = new Configuration();  
		    FileSystem fs = FileSystem.get(URI.create(PORT_INFO+path), conf);
		    return fs.open(new Path(path+"/"+file_name));
		} catch (Exception e) {
			return null;
		}
	}
}
