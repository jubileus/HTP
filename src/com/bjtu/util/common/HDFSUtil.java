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
	 * @author 刘庶
	 * 编写日期：2015-04-12
	 * 功能：建立目录（文件夹）
	 * @param path：文件路径
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
	 * @author 刘庶
	 * 编写日期：2015-04-19
	 * 功能：删除目录或文件
	 * @param path：目录或文件路径
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
	 * @author 刘庶
	 * 编写日期：2015-04-21
	 * 功能：打开文件目录
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
	 * @author 刘庶
	 * 编写日期：2015-04-21
	 * 功能：在指定目录下的目标文件后追加内容
	 * @param @param path：文件路径
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
	 * @author 刘庶
	 * 编写日期：2015-04-21
	 * 功能：在指定目录下的建立空白文件
	 * @param fs
	 * @param path：文件路径
	 */
	public static void createNewFile(String path,FileSystem fs){
		try {
			fs.createNewFile(new Path(path));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-21
	 * 功能：在指定目录下的目标文件后追加内容
	 * @param dst：目标文件
	 * @param buffer：追加的内容
	 */
	public static void appendFile(FSDataOutputStream dst,byte[] buffer){
		try {
			dst.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-23
	 * 功能：从HDFS中下载文件
	 * @param path：目标文件
	 * @param file_name：文件名称
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
