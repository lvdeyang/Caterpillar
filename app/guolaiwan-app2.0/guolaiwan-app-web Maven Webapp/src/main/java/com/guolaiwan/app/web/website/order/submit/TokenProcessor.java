package com.guolaiwan.app.web.website.order.submit;

import java.util.Random;
import java.util.UUID;

public class TokenProcessor {

	//单例模式（饿汉式）
	private TokenProcessor() {}
	private static final TokenProcessor instance = new TokenProcessor();  
    
    public static TokenProcessor getInstance(){  
        return instance;  
    }
	
    
    //获得随机数字符串  
    public String generateToken(){  
        
        String token = System.currentTimeMillis() + new Random().nextInt() + "";  
        //获得数据摘要  
        try{  
        	 UUID uuid = UUID.randomUUID(); 
        	 return uuid.toString();
        } catch (Exception e){  
            throw new RuntimeException(e);  
        }  
    }
	
	

}
