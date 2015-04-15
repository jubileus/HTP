package com.bjtu.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import org.apache.hadoop.util.Progressable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import java.io.FileOutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class HadoopTest {

	public static void main(String[] args) {
		 try {
//			 uploadToHdfs();			 
//			 readFromHdfs();
//			 deleteFromHdfs();
//			 createFolderFromHdfs();
			 getDirectoryFromHdfs();
		  } catch (Exception e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		  }
		  finally
		  {
			  System.out.println("---finish---");
		  }
	}
	
	/**上传文件到HDFS上去*/
	private static void uploadToHdfs() throws FileNotFoundException,IOException {
		 String localSrc = "e://prototype.zip";
		 String dst = "hdfs://192.168.10.133:9000/user/hadoop/prototype.zip";
		 InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
		 Configuration conf = new Configuration();
		  
		 FileSystem fs = FileSystem.get(URI.create(dst), conf);
		 OutputStream out = fs.create(new Path(dst), new Progressable() {
			 public void progress() {
			     System.out.print(".");
			 }
		 });
		 IOUtils.copyBytes(in, out, 4096, true);
	}
	 
	 /**从HDFS上下载文件*/
	 private static void readFromHdfs() throws FileNotFoundException,IOException {
	     String dst = "hdfs://192.168.10.133:9000/user/hadoop/qq.txt";  
	     Configuration conf = new Configuration();  
	     FileSystem fs = FileSystem.get(URI.create(dst), conf);
	     FSDataInputStream hdfsInStream = fs.open(new Path(dst));
	  
	     OutputStream out = new FileOutputStream("e://qq-hdfs.txt"); 
	     byte[] ioBuffer = new byte[1024];
	     int readLen = hdfsInStream.read(ioBuffer);

	     while(-1 != readLen){
		     out.write(ioBuffer, 0, readLen);  
		     readLen = hdfsInStream.read(ioBuffer);
	     }
	     out.close();
	     hdfsInStream.close();
	     fs.close();
	 }
	 
	 /**在HDFS创建文件夹*/
	 private static void createFolderFromHdfs() throws FileNotFoundException,IOException {
	     String dst = "hdfs://192.168.10.133:9000/user/hadoop/";  
	     Configuration conf = new Configuration();
	     FileSystem fs = FileSystem.get(URI.create(dst),conf);
	     Path srcPath = new Path("/user/hadoop/测试");
	     boolean isok = fs.mkdirs(srcPath);
	     if(isok){
	    	 System.out.println("create dir ok!");
	     }else{
	     	System.out.println("create dir failure");
	     }
	     fs.close();
	 }
	 
	 /**从HDFS上删除文件*/
	 private static void deleteFromHdfs() throws FileNotFoundException,IOException {
	     String dst = "hdfs://192.168.10.133:9000/user/hadoop/qq.txt";  
	     Configuration conf = new Configuration();  
	     FileSystem fs = FileSystem.get(URI.create(dst), conf);
	     fs.deleteOnExit(new Path(dst));
	     fs.close();
	 }

	 /**遍历HDFS上的文件和目录*/
	 private static void getDirectoryFromHdfs() throws FileNotFoundException,IOException {
		 String dst = "hdfs://192.168.10.133:9000/user/hadoop/";  
		 Configuration conf = new Configuration();  
		 FileSystem fs = FileSystem.get(URI.create(dst), conf);
		 FileStatus fileList[] = fs.listStatus(new Path(dst));
		 int size = fileList.length;
		 for(int i = 0; i < size; i++){
			 System.out.println("name:" + fileList[i].getPath().getName() + "   size:" + fileList[i].getLen());
		 }
		 fs.close();
	 } 
}
