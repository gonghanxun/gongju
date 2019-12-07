package com.gonghanxun.cms;

import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gonghanxun.cms.utils.StringUtils;



/**
 * 
 *
 */
public class TestStringUtils {
	
	static StringUtils stringUtils;
	
	@Before
	public void init() {
		
		stringUtils = new StringUtils();
		System.err.println("init beore -----------");
	}
	
	@BeforeClass
	public static void init1() {
		System.err.println("init beoreClass -----------");
		stringUtils = new StringUtils();
	}
	
	@After
	public void after() {
		
		System.err.println("after  -----------");
	}
	
	@AfterClass
	public static void afterClass() {
		System.err.println("afterClass  -----------");
		
	}
	
	
	
	@Test
	public void testIsBlank1() {
		
		boolean blank = stringUtils.isBlank("11");
		System.out.println(" blank is " + blank);
		//验证是否为假
		Assert.assertTrue(blank==false);	
		
	
	}
	
	@Test
	public void testIsMobileTrue() {
		String str = "13683679291";
		Assert.assertTrue("错误1", StringUtils.isMobile(str));
		
	}
	
	@Test
	public void testIsMobileFalse() {
		String str = "136836792b1";
		Assert.assertFalse("错误2", StringUtils.isMobile(str));
		
	}
	
	
	@Test
	public void testRandom() {
		String randomStr = StringUtils.getRandomStr(20);
		System.out.println(" randomStr is " + randomStr );
		Assert.assertTrue(20==randomStr.length());
		
	}
	
	@Test
	public void testGetRandomStrNum() {
		String randomStrNum = StringUtils.getRandomStrNum(15);
		System.out.println(" randomStrNum  is " + randomStrNum);
		Assert.assertTrue(15==randomStrNum.length());
	}
	
	/**
	 * 测试随机中文字符�?
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testGetCn() throws UnsupportedEncodingException {
		String s = StringUtils.getGb2312(20);
		System.out.println("中文是：s is " + s);
		
	}
	
	
	@Test
	public void testIsBlank2() {
		
		Boolean blank = stringUtils.isBlank("   ");
		System.out.println(" blank is " + blank);
		Assert.assertTrue(blank==true);
		
	}
	
	

}
