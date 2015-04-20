package com.bjtu.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
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
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class FileUploadTest {

	public static void main(String[] args) {
		try {
			RandomAccessFile raf = new RandomAccessFile("E://test.docx", "rw");
			System.out.println("size-------->"+raf.length());
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

}
