package com.guolaiwan.app.web.business.controller;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.springframework.core.serializer.Serializer;

/**
 *  图片 \Base64  转换 
 * 
 * */
public class PictureConvertor implements Serializable {
	
	 // 将图片文件转化为字节数组字符串,并将其进行Base64处理
    public static String getImg(String imgPath) {
        byte[] bytes = null;
        try{
            InputStream inputStream = new FileInputStream( imgPath );
                 bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode( bytes );
    }


   //对字节数组Base64编码
   public static boolean GenerateImg(String str, String imgPath) { //生成图片
        boolean flag = true ;
        BASE64Decoder decoder = new BASE64Decoder();
        try{
             if(str != null){
                 byte[] b = decoder.decodeBuffer( str );
                 for(int i = 0; i< b.length;i++){
                     if(b[i]<0){
                        b[i] += 256;
                     }
                 }
                 OutputStream out = new FileOutputStream( imgPath );
                 out.write( b );
                 out.flush();
                 out.close();
                 flag = true;
             }else{
                 System.out.println("Base64编码不能为null");
                 flag = false;
             }
        }catch (Exception e){
            e.printStackTrace();
        }
           return  flag;
    }
     
}
