package com.bjtu.util.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;

public class StringUtil {
	
	private static Random random = new Random();
	private static Pattern mp = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7]))\\d{8}$");
	
	public static boolean isEmpty(String str){
		return str==null||"".equals(str);
	}
	
	public static String getUUID(){
		String uuid=UUID.randomUUID().toString();
		uuid=uuid.replaceAll("-", "");
		return uuid;
	}
	
	public static boolean isMobile(String mobile){
		Matcher matcher=mp.matcher(mobile);
		return matcher.matches();
	}
	
	public static String getRandomPassword(){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<4;i++){
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
        
    /**
     * 转换字符串，防止SQL注入
     * @return SQL查询所用字符串，以在两头添加“'”符号
     */
    public static String sqlEscapeString(String rawStr){
         return "'" + rawStr.replaceAll("\\\\", "\\\\").replaceAll("'", "\\'") + "'";
    }
        
    public static String inputStream2String(InputStream is,String charset) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(is,charset));
    	return buffer2String(in);
    }
    
    public static String buffer2String(BufferedReader buf) throws IOException {
    	StringBuffer buffer = new StringBuffer();
    	String line = "";
   		while ((line = buf.readLine()) != null) {
   			buffer.append(line);
   		}
    	return buffer.toString();
    }
    
    public static String compareStrings(String str1,String str2){
    	return str1.compareTo(str2)>0?(str1+str2):(str2+str1);
    }

    public static int countChinese(String str){
    	int count = 0; 
  	    String regEx = "[\\u4e00-\\u9fa5]"; 
  	    Pattern p = Pattern.compile(regEx); 
  	    Matcher m = p.matcher(str); 
  	    while (m.find()) { 
  	        for (int i = 0; i <= m.groupCount(); i++) { 
  	            count = count + 1; 
  	        }   	        
        }    
  	  return count;
    }
    
    public final static String encode(String s) {
    	char hexDigits[] = {
    	        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
    	        'e', 'f'};
    	        try {
    	        byte[] strTemp = s.getBytes();
    	        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
    	        mdTemp.update(strTemp);
    	        byte[] md = mdTemp.digest();
    	        int j = md.length;
    	        char str[] = new char[j * 2];
    	        int k = 0;
    	        for (int i = 0; i < j; i++) {
    	        byte byte0 = md[i];
    	          str[k++] = hexDigits[byte0 >>> 4 & 0xf];
    	          str[k++] = hexDigits[byte0 & 0xf];
    	        }
    	        return new String(str);
    	      }
    	      catch (Exception e) {
    	        return null;
    	      }
    }

}
