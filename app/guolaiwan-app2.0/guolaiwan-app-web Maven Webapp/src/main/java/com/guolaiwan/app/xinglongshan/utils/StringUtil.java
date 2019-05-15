package com.guolaiwan.app.xinglongshan.utils;

import java.util.UUID;


import org.apache.commons.lang3.StringUtils;


/**
 * 字符串操作类
 * 
 * @author crazy_cabbage
 * 
 */
public class StringUtil  extends StringUtils {
	/**
	 * 不为空 返回真
	 * 
	 * @param str
	 *            字符串
	 * @return 如果字符串不为空且长度大于1 返回真 ，其他返回假
	 */
	public static boolean isNotBlank(String str) {
		return str != null && !str.isEmpty();
	}

	/**
	 * 如果为空 返回真
	 * 
	 * @param str
	 *            字符串
	 * @return 如果为空或长度等于零，返回真，其他返回假
	 */
	public static boolean isBlank(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 去掉空格不为空 返回真
	 * 
	 * @param str
	 *            字符串
	 * @return 如果字符串不为空且去掉空格长度大于1 返回真 ，其他返回假
	 */
	public static boolean isNotTrimBlank(String str) {
		return str != null && !str.trim().isEmpty();
	}

	/**
	 * 去掉空格为空返回真
	 * 
	 * @param str
	 *            字符串
	 * @return 如果字符串为空或去掉空格长度为0,返回真，其他返回假
	 */
	public static boolean isTrimBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	/**
	 * 空串
	 */
	public final static String EMPTY = "";

	/**
	 * 首字母大写
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 首字母大写的字符串
	 */
	public static String capFirstUpperCase(String str) {
		if (isBlank(str)) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);

	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 首字母小写的字符串
	 */
	public static String capFirstLowerCase(String str) {
		if (isBlank(str)) {
			return str;
		}
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 把号码转成字符串 0-9A-Za-z 如10 A 11 B 36 a
	 * 
	 * @param no
	 *            号码
	 * @return 数字转成的号码
	 */
	public static String num2String(Long no) {
		if (no < 10) {
			return String.valueOf(no);
		} else if (no < 36) {
			return String.valueOf((char) (no - 10 + 'A'));
		} else if (no < 62) {
			return String.valueOf((char) (no - 36 + 'a'));
		} else {
			return num2String(no / 62)+num2String(no % 62) ;
		}
	}
	
	/**
     * Long型的比较
     * 
     * @param long1
     * @param long2
     * @return
     *
     */
    public static boolean equals(Long long1,Long long2){
        if(null == long1 || null == long2){
            return false;
        }
        if(long1.equals(long2)){
            return true;
        }
        return false;
    }
	/**
	 * 获取取消"-" 的UUID值
	 * @return uuid
	 */
	public static String generateUuid() {
		String uuid = UUID.randomUUID().toString();
   	   	   	   uuid=uuid.replace("-","");
		return uuid;
	}
	

  
    // 将字符串转移为ASCII码  
    public static String getCnASCII(String cnStr) {  
        StringBuffer strBuf = new StringBuffer();  
        byte[] bGBK = cnStr.getBytes();  
        for (int i = 0; i < bGBK.length; i++) {  
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));  
        }  
        return strBuf.toString();  
    }  
  
     
	
}
