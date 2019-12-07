package com.gonghanxun.cms.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 

 */
public class StringUtils {
	
	/**
	 * 
	 */
	static char charArray[] = new char[36];
	static {
		
		for (int i = 0; i < 10; i++) {
			charArray[i] = (char)('0' + i);
		}
		for (int i = 0; i < 26; i++) {
			charArray[i+10] = (char)('A' + i);
		}		
	}
	
	
	

	
	public static boolean isBlank(String str) {
		return null==str||"".equals(str.trim());
	}
	

	public static boolean haveValue(String str) {
		return null!=str && !"".equals(str.trim());
	}
	
	/**
	 * 判断是否为数�?
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		String regex = "^\\d{1,}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(str);
		boolean find = matcher.find();
		return find;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		String regex = "^(135|136|138)\\d{8}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(str);
		boolean find = matcher.find();
		return find;
		
		
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public static String getRandomStr(int n) {
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder();
		//StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			char randomChar = (char)('a' +  random.nextInt(26));// 0 ~  25;
			sb.append(randomChar);
		}
		return sb.toString();
	}
	
	/**
	 * @param n
	 * @return
	 */
	public static String getRandomStrNum(int n) {
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int index =  random.nextInt(36);// 0 ~  25;
			char randomChar = charArray[index];
			sb.append(randomChar);
		}
		
		return sb.toString();
		
	}
	
	/**
	 * @param n
	 * @return 
	 * @throws UnsupportedEncodingException 
	 */
	public static String getGb2312(int n) throws UnsupportedEncodingException {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(getGb2312());
		}
		return sb.toString();
	}
	
	/**
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String getGb2312() throws UnsupportedEncodingException {
		
		byte word[] = new byte[2];
		//  0x1A   0x1A+94
		Random random = new Random();
		word[0] = (byte)(0xA1 + 0x10 + random.nextInt(39));
		word[1] = (byte)(0xA1  + random.nextInt(94));
		return new String(word,"GBK");
		
	}

}
