package com.bjtu.test;

import java.net.URI;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

import org.apache.hadoop.util.Progressable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;

import java.io.FileOutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

import antlr.ByteBuffer;

import com.bjtu.util.common.DataFormatUtil;
import com.bjtu.util.common.FileUtil;
import com.bjtu.util.common.HDFSUtil;

public class FileUploadTest {
	
	public static void main(String[] args) {
		//ԭʼ�ļ���С18578264��byte
//		try {
//			System.out.println(FileUtil.codeString("E://combine.pptx"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		
		hdfsTest2();
//		testSplit();
//		testCombine();
//		compare();
		System.out.println("Finish");
	}
	
	private static void divide(){
		
	}
	
	private static void compare(){
		try {
			RandomAccessFile dst = new RandomAccessFile("E://combine.pptx", "r");
			RandomAccessFile src = new RandomAccessFile("E://test.pptx", "r");
			int index=0;
			for(long i=0;i<src.length();i++){
				index++;
				if(src.readByte()!=dst.readByte()){
					System.out.println(i+"    is   different");
				}
			}
			System.out.println(index);
			dst.close();
			src.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testCombine(){
		try {
			int BUFFER_SIZE=10485760;//10MB
			byte[] buffer;
			int total=2;
			RandomAccessFile dst = new RandomAccessFile("E://combine.pptx", "rw");
			for(int i=1;i<=total;i++){
				RandomAccessFile src = new RandomAccessFile("E://test_"+i+".pptx", "r");
//				System.out.println("src--size->"+src.length());
				buffer=new byte[(int)src.length()];
				src.read(buffer);
				dst.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testSplit(){
		try {
			RandomAccessFile src = new RandomAccessFile("E://test.pptx", "r");
			System.out.println("src---size--->"+src.length());
			int BUFFER_SIZE=10485760;//10MB
			//���һ�θ��ƵĻ�������С
			long last_size=src.length()%BUFFER_SIZE;
			//���ƵĴ���
			long copy_number=src.length()/BUFFER_SIZE;
			byte[] buffer;
			RandomAccessFile dst;
			if(last_size>0){
				//���һ�θ��Ƶ����ݲ�Ϊ��
				int index=0;
				for(long i=0;i<copy_number;i++){
					index++;
					dst=new RandomAccessFile("E://test_"+index+".pptx", "rw");
					buffer=new byte[BUFFER_SIZE];
					src.read(buffer);
					dst.write(buffer);
				}
				index++;
				dst=new RandomAccessFile("E://test_"+index+".pptx", "rw");
				buffer=new byte[(int) last_size];
				src.read(buffer);
				dst.write(buffer);
			}else{
				int index=0;
				for(long i=0;i<copy_number;i++){
					index++;
					dst=new RandomAccessFile("E://test_"+index+".pptx", "rw");
					buffer=new byte[BUFFER_SIZE];
					src.read(buffer);
					dst.write(buffer);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void hdfsTest2(){
		//���ղ���HDFS
		int total=2;
		String hdfs_name="1429602152206";
		String file_name="test";
		String postfix="pptx";
		String src_path="/user/hadoop/user/402824814cb03d52014cb03dbc190001/tmp/";
		String dst_path="/user/hadoop/user/402824814cb03d52014cb03dbc190001/file/"+file_name+"."+postfix;
		String root_path="/user/hadoop/user/402824814cb03d52014cb03dbc190001/";
		try {
			RandomAccessFile src = new RandomAccessFile("E://hdfs2.pptx", "rw");
			//���ļ�FileSystem
			FileSystem fs=HDFSUtil.openFileSystem(dst_path);
			//��ȡ�ļ������
			//ѭ����������
			byte[] buffer;//������
			FSDataInputStream in;//�ļ�������
			for(int i=1;i<=total;i++){
				//��ȡ�ļ�������
				in=fs.open(new Path(src_path+hdfs_name+"_"+i+"."+postfix));
				//��������
				buffer=new byte[in.available()];
				in.read(buffer);
				
				//ƴ������
				src.write(buffer);
			}
			
			//�ر��ļ�FileSystem
			src.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void hdfsTest(){
		//���ղ���HDFS
		int total=2;
		String hdfs_name="1429602152206";
		String file_name="test";
		String postfix="pptx";
		String src_path="/user/hadoop/user/402824814cb03d52014cb03dbc190001/tmp/";
		String dst_path="/user/hadoop/user/402824814cb03d52014cb03dbc190001/file/"+file_name+"."+postfix;
		String root_path="/user/hadoop/user/402824814cb03d52014cb03dbc190001/";
		try {
			//���ļ�FileSystem
			FileSystem fs=HDFSUtil.openFileSystem(dst_path);
			//�������ļ�
			fs.createNewFile(new Path(dst_path));
			//��ȡ�ļ������
			FSDataOutputStream out=fs.append(new Path(dst_path));
			//ѭ����������
			byte[] buffer;//������
			FSDataInputStream in;//�ļ�������
			for(int i=1;i<=total;i++){
				//��ȡ�ļ�������
				in=fs.open(new Path(src_path+hdfs_name+"_"+i+"."+postfix));
				//��������
				buffer=new byte[in.available()];
				in.read(buffer);
				//ƴ������
				
				out.write(buffer);
				System.out.println(out.getPos());
			}
			
			//�ر��ļ�FileSystem
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void appendFile(FileSystem fs,String path){
		try {
			RandomAccessFile src = new RandomAccessFile("E://test.docx", "r");
			FSDataOutputStream dst = fs.append(new Path(path));
			System.out.println("size  in  Byte-------->"+src.length());
			int BUFFER_SIZE=4096;
			byte[] buffer;
			//���һ�θ��ƵĻ�������С
			long last_size=src.length()%BUFFER_SIZE;
			//���ƵĴ���
			long copy_number=src.length()/BUFFER_SIZE;
			if(last_size>0){
				//���һ�θ��Ƶ����ݲ�Ϊ��
				for(long i=0;i<copy_number;i++){
					buffer=new byte[BUFFER_SIZE];
					src.read(buffer);
					dst.write(buffer);
				}
				buffer=new byte[(int) last_size];
				src.read(buffer);
				dst.write(buffer);
			}else{
				for(long i=0;i<copy_number;i++){
					buffer=new byte[BUFFER_SIZE];
					src.read(buffer);
					dst.write(buffer);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void RandomAccessFileTest(){
		try {
			RandomAccessFile src=new RandomAccessFile("E://test.docx", "r");
			RandomAccessFile dst=new RandomAccessFile("E://rs.docx", "rw");
			System.out.println("size  in  Byte-------->"+src.length());
			
			int BUFFER_SIZE=4096;
			byte[] buffer;
			//���һ�θ��ƵĻ�������С
			long last_size=src.length()%BUFFER_SIZE;
			//���ƵĴ���
			long copy_number=src.length()/BUFFER_SIZE;
			if(last_size>0){
				//���һ�θ��Ƶ����ݲ�Ϊ��
				for(long i=0;i<copy_number;i++){
					buffer=new byte[BUFFER_SIZE];
					src.read(buffer);
					dst.write(buffer);
				}
				buffer=new byte[(int) last_size];
				src.read(buffer);
				dst.write(buffer);
			}else{
				for(long i=0;i<copy_number;i++){
					buffer=new byte[BUFFER_SIZE];
					src.read(buffer);
					dst.write(buffer);
				}
			}
			
			src.close();
			dst.close();
			System.out.println("Finish");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
