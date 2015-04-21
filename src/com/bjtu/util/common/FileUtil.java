package com.bjtu.util.common;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

public class FileUtil {
	//本机路径
	public static final String DEVIDE="\\";
	public static final String TEMPPATH="E:\\workspace\\user\\";
	
	//服务器路径
//	public static final String DEVIDE="/";
//	public static final String TEMPPATH="/usr/tmp";
	
	
	/**
	   * 压缩文件
	   * @param srcfile File[] 需要压缩的文件列表
	   * @param zipfile File    压缩后的文件
	   */
	public static void zipFiles(java.io.File[] srcfile, java.io.File zipfile) {
	    byte[] buf = new byte[1024];
	    try {
	      // Create the ZIP file
	      ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
	      // Compress the files
	      for (int i = 0; i < srcfile.length; i++) {
	        FileInputStream in = new FileInputStream(srcfile[i]);
	        // Add ZIP entry to output stream
	        out.putNextEntry(new ZipEntry(srcfile[i].getName()));
	        // Transfer bytes from the file to the ZIP file
	        int len;
	        while ( (len = in.read(buf)) > 0) {
	          out.write(buf, 0, len);
	        }
	        // Complete the entry
	        out.closeEntry();
	        in.close();
	      }
	      // Complete the ZIP file
	      out.close();
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
    /**
     * 删除子文件下全部文件
     * @param folderPath 文件路径
     */
    public static void delFolder(String folderPath) {
	     try {
	        delAllFiles(folderPath); //删除完里面所有内容
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //删除空文件夹
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}
	
    /**
     * 删除文件下全部文件
     * @param folderPath 文件路径
     */
    public static boolean delAllFiles(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
           if (path.endsWith(File.separator)) {
              temp = new File(path + tempList[i]);
           } else {
               temp = new File(path + File.separator + tempList[i]);
           }
           if (temp.isFile()) {
              temp.delete();
           }
           if (temp.isDirectory()) {
              delAllFiles(path + DEVIDE + tempList[i]);//先删除文件夹里面的文件
              delFolder(path + "DEVIDE" + tempList[i]);//再删除空文件夹
              flag = true;
           }
        }
        return flag;
   }
    
    /**
	 * @author 刘庶
	 * 编写日期：2015-04-21
	 * 功能：将File转换为byte[]
	 * @param buffer：追加的内容
	 */
	public static byte[] getBytes(File file){  
        byte[] buffer = null;  
        try {  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return buffer;  
    }  
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-04-21
	 * 功能：建立目录
	 * @param path
	 */
	public static void createFolder(String path){  
		File f=new File(path);
		if(f.exists()){
		}else{
			if(f.mkdirs()){
				//目录建立成功
			}else{
				//目录建立失败
			}
		}
    }  
	
}
