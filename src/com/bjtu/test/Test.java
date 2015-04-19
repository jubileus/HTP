package com.bjtu.test;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		addFileType();
	}

	private static void addFileType(){
		//0=普通文件，1=文档文件，2=图片文件，3=视频文件，4=音频文件
		String prefix="/HTP/pages/img/icon/";
		
		String[] postfix={
				"folder",
				"zip",
				"rar",
				"txt",
				"doc",
				"docx",
				"xls",
				"xlsx",
				"ppt",
				"pptx",
				"pdf",
				
				"bmp",
				"jpg",
				"jpeg",
				"png",
				"gif",
				
				"avi",
				"mov",
				"mpg",
				"mpeg",
				"ram",
				"qt",
		
				"mp3",
				"wav",
		};
		String[] img={
				"icon_folder.png",
				"icon_zip.png",
				"icon_zip.png",
				"icon_txt.png",
				"icon_word.png",
				"icon_word.png",
				"icon_xls.png",
				"icon_xls.png",
				"icon_ppt.png",
				"icon_ppt.png",
				"icon_pdf.png",
				
				"icon_image.png",
				"icon_image.png",
				"icon_image.png",
				"icon_image.png",
				"icon_image.png",
				
				"icon_video.png",
				"icon_video.png",
				"icon_video.png",
				"icon_video.png",
				"icon_video.png",
				"icon_video.png",
				
				"icon_music.png",
				"icon_music.png"
		};
		int[] category={
				0,
				0,
				0,
				1,
				1,
				1,
				1,
				1,
				1,
				1,
				1,
				
				2,
				2,
				2,
				2,
				2,
				
				3,
				3,
				3,
				3,
				3,
				3,
		
				4,
				4
		};
		
		System.out.println("postfix---size--->"+postfix.length);
		System.out.println("img---size--->"+img.length);
		System.out.println("category---size--->"+category.length);
	}
}
