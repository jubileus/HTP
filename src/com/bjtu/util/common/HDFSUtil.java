package com.bjtu.util.common;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSUtil {

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
			fs = FileSystem.get(URI.create(ROOT_PATH),conf);
			Path srcPath = new Path(path);
		    boolean isok = fs.mkdirs(srcPath);
		    fs.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
