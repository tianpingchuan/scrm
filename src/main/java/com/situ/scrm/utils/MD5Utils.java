/**
 * @Company:中享思途   
 * @Title:MD5Utils.java 
 * @Author:wxinpeng   
 * @Date:2020年2月10日 下午3:12:59     
 */
package com.situ.scrm.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
 * @ClassName:MD5Utils 
 * @Description:(MD5加密Utils)  
 */
public class MD5Utils implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String CHART_SET = "UTF-8";// 编码方式

	/**
	 * 加密
	 * @param string 要加密的字符串
	 * @return 加过密的字符串
	 */
	public static String encode(String string) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(string.getBytes(CHART_SET));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
}
