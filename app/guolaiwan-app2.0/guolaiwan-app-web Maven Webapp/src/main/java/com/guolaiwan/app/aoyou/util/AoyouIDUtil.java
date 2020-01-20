package com.guolaiwan.app.aoyou.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class AoyouIDUtil {

	//获取配置信息
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(AoyouIDUtil.class.getClassLoader().getResourceAsStream("aoyouID.properties"));//获取properties文件
		} catch (IOException e) {e.printStackTrace();}
	}
		
	/**
     * 根据过来玩的产品id查找配置文件中的世园会票种id
     * @param id
     * @return
     */
    public static String getSyhID(String id) {
		String syhid = (String) properties.get("SYH_ID_" + id);
        return syhid;
    }
    
    /**
     * 根据过来玩的产品id查找配置文件中的冰雪票种id
     * @param id
     * @return
     */
    public static String getBxID(String id) {
		String bxid = (String) properties.get("BX_ID_" + id);
        return bxid;
    }
     
    /**
     * 判断产品id是否是世园会的票种id
     * @param id
     * @return
     */
    public static Boolean isSyhID(String id) {
    	Boolean isSyhID = false;
		String[] ids = ((String)properties.get("GLW_SYH_ID")).split(",");
		isSyhID = Arrays.asList(ids).contains(id);
        return isSyhID;
    }
     
    /**
     * 判断产品id是否是冰雪的票种id
     * @param id
     * @return
     */
    public static Boolean isBxID(String id) {
    	Boolean isBxID = false;
		String[] ids = ((String)properties.get("GLW_BX_ID")).split(",");
		isBxID = Arrays.asList(ids).contains(id);
        return isBxID;
    }
    
    public static void main(String[] args) {
		if(isSyhID("2470")){
			getSyhID("2470");
		}
		if(isBxID("2473")){
			getBxID("2473");
		}
	}
}
