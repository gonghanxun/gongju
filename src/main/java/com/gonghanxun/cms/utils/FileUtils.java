package com.gonghanxun.cms.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;

public class FileUtils {
	
	/**
	 * 
	 */
	public static synchronized String getSuffixName(String fileName) {
		
		int dotPos = fileName.lastIndexOf('.');
		if(dotPos<0)
			return "";
		
		return fileName.substring(dotPos);
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public static void delFile(String fileName) {
		
		File file = new File(fileName);
		
		// 获取文件的分隔符�?
		String fileSeperator = getProperty("file.separator");
		
		
		
		//文件不存�?
		if(!file.exists())
			return ;
		
		// 如果是目�?
		if(file.isDirectory()) {
			//递归删除子目录或者文�?
			String[] list = file.list();
			for (int i = 0; i < list.length; i++) {
				String childFileName = fileName+ fileSeperator + list[i];
				delFile(childFileName);
			}
		}
		// 如果是文�? 或�?�删除子目录之后 删除本身
		file.delete();
		
	}
	
	/**
	 * 根据属�?�key 获取系统环境变量�?
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		
		Properties properties = System.getProperties();
		
		return properties.getProperty(key);
	} 
	
	/**
	 * 
	 */
	public static String getEnv(String key) {
		
		Map<String, String> getenv = System.getenv();
	
		return getenv.get(key);
	}
	
	/**
	 * @param fileName
	 * @return
	 */
	public static long getSize(String fileName) {
		
		File file = new File(fileName);
		if(!file.exists() || ! file.isFile())
			return 0;
		return file.length();
		
	}
	
	/**
	 * 
	 */
	public static void comparePath(String src,String dst) throws FileNotFoundException, IOException {
		
		 File srcFile = new File(src);// 
		 File dstFile = new File(dst);//
		 if(!srcFile.exists()) {
			 System.out.println(" 源文�? 不存�?  "  + src);
			 return;
		 }
		 
		 if(!dstFile.exists()) {
			 System.out.println(" 目标文件 不存�?  "  + dst);
			 return;
		 }
		 if(srcFile.isFile() && dstFile.isFile()) {
			 if(srcFile.length() != dstFile.length()) {
				 System.out.println(" 文件长度不一�?" + src);
			 }else {
				 byte[] md5Src = DigestUtils.md5(new FileInputStream(srcFile));
				 byte[] md5Dst = DigestUtils.md5(new FileInputStream(dstFile));
				 String strMd5Src = new String(md5Src);
				 String strMd5Dst = new String(md5Dst);
				 if(!strMd5Src.equals(strMd5Dst)) {
					 System.out.println(" 文件内容不一�?  " +  src);
				 }
			 }
			 return ;
		 }
		 
		 if(srcFile.isDirectory()) {
			 String[] list = srcFile.list();
			 for (int i = 0; i < list.length; i++) {
				 String childSrcFile = src + "\\" + list[i];
				 String childDstFile = dst + "\\" + list[i];
				 comparePath(childSrcFile,childDstFile);
			}
			 
		 }
		
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static String read(String fileName) throws IOException {
		
		//用于存储文件内容
		StringBuilder sb = new StringBuilder();
		
		// 创建文件对象
		File file = new File(fileName);
		
		//创建文件输入�?
		FileInputStream fis = new FileInputStream(file);
		// 创建缓冲�?
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"GBK"));
		String ln=null;
		//按行读入
		while ((ln= br.readLine())!=null) {
			sb.append(ln);
		}
		
		closeStream(br,fis);
		
		return sb.toString();
	}
	
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static List<String> readByLines(String fileName) throws IOException {
		
		//用于存储文件内容
		List<String> lines = new ArrayList();
		
		// 创建文件对象
		File file = new File(fileName);
		
		//创建文件输入�?
		FileInputStream fis = new FileInputStream(file);
		// 创建缓冲�?
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		String ln=null;
		//按行读入
		while ((ln= br.readLine())!=null) {
			//sb.append(ln);
			lines.add(ln);
		}
		
		closeStream(br,fis);
		
		return lines;
	}
	
	
	
	/**
	 *  关闭�?
	 * @param stream
	 * @throws IOException 
	 */
	public static void closeStream(Closeable ... stream) throws IOException {
		
		for (int i = 0; i < stream.length; i++) {
			stream[i].close();
		}
	}
	
	/**
	 * 复制文件
	 * @param srcFile  源文�?
	 * @param dstFile  目标文件
	 * @throws IOException 
	 */
	public synchronized static void copy(String srcFileName ,String dstFileName) throws IOException {
		// 源文�?
		File srcFile = new File(srcFileName);
		File dstFile = new File(dstFileName);
		
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos  = new FileOutputStream(dstFile);
		byte b[]=new byte[1024];
		
		while(fis.read(b)>0) {
			fos.write(b);
		}
		closeStream(fos,fis);
	}
	
	

}
