package com.guolaiwan.app.xinglongshan.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 算法
 * @author crazy_cabbage
 */
public final class MD5 {
	 /**
	    * 给定一个字符串 给出这个字符串的md5值
	    * @param pwd 密码
	    * @return 返回 MD5值
	    */
		 public static String md5(String pwd) {
	         return md5(pwd, "UTF-8");
		 }
		 /**
		  * 给定一个字符串给出这个字符串的md5 字节值
		  * @param pwd 密码
		  * @return 返md5字节数组
		  */
		 public static byte[] md5Byte(String pwd){
			 return md5Byte(pwd,"UTF-8");
		 }
		 /**
		  * 给定一个字符串 给出这个字符串的md5值
		  * @param pwd 密码
		  * @param charsetName 字符集名称
		  * @return 返回 MD5值
		  */
		 public static String md5(String pwd,String charsetName) {
				 return ByteUtil.bytes2String(md5Byte(pwd,charsetName));
		 }
		 /**
		  * 给定一个字符串，给出这个字符串的MD5字节数组
		  * @param pwd 密码串
		  * @param charsetName 字节码
		  * @return 给出这个字符串的md5字节数组
		  */
		public static byte[] md5Byte(String pwd,String charsetName){
			byte[] md5Byte = null;
			try {
				 MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				 md5Byte=messageDigest.digest(pwd.getBytes(Charset.forName(charsetName)));
			} catch (NoSuchAlgorithmException e) {
				throw new BusinessException("MD5 operate exception", e);
			}
			return md5Byte;
		}
		 /**
		  * 获得MD5值 
		  * @param pwd 密码
		  * @param times MD5 加密的次数
		  * @return MD5值
		  * @throws NoSuchAlgorithmException 
		  */
		 public static String md5(String pwd ,int times) {
			 if(times<1){
				 throw new BusinessException("MD5 operate exception");
			 }
			 for(int i=0;i<times;i++){
				 pwd = md5(pwd);
			 }
			 return pwd;
		 }
		 /**
		  * 宽字符的MD5 相当于把MD5 的值再MD5 一次
		  * @param pwd 密码值
		  * @return 返回加密后的值
		  */
		 public static String md5W(String pwd) {
			 return md5(pwd,2);
		 }
}