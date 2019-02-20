package pub.caterpillar.commons.util.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

/**
 * byte数组 操作
 * lvdeyang 2017年3月28日
 */
public class ByteUtil {

	//byte[]转16进制
	public static String bytesToHexString(byte[] src){      
        StringBuilder stringBuilder = new StringBuilder();      
        if (src == null || src.length <= 0) {      
            return null;      
        }      
        for (int i = 0; i < src.length; i++) {      
            int v = src[i] & 0xFF;      
            String hv = Integer.toHexString(v);      
            if (hv.length() < 2) {      
                stringBuilder.append(0);      
            }      
            stringBuilder.append(hv);      
        }      
        return stringBuilder.toString();      
    } 
	
	//inputStream 转换 byte[]
	public static byte[] inputStreamToBytes(InputStream in) throws IOException{
		byte[] in2b;
		ByteArrayOutputStream swapStream = null;
		try{
			swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = in.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        in2b = swapStream.toByteArray();  
		}finally{
			if(in != null) in.close();
			if(swapStream != null) swapStream.close();
		}
        return in2b;
	}
	
	//字节长度
	private static String getB(byte[] src){
		return new StringBufferWrapper().append(src.length).append("B").toString();
	}
	
	//字节长度转换KB
	private static String getKB(byte[] src){
		return new StringBufferWrapper().append(src.length/1024).append("KB").toString();
	}
	
	//字节长度转换MB
	private static String getMB(byte[] src){
		return new StringBufferWrapper().append(src.length/(1024*1024)).append("M").toString();
	}
	
	//字节长度转换GB
	private static String getGB(byte[] src){
		return new StringBufferWrapper().append(src.length/(1024*1024*1024)).append("G").toString();
	}
	
	//获取字节长度
	public static String getSize(byte[] src){
		int length = src.length;
		if(length < 1024){
			return getB(src);
		}else if(length>=1024 && length<1024*1024){
			return getKB(src);
		}else if(length>=1024*1024 && length<1024*1024*1024){
			return getMB(src);
		}else{
			return getGB(src);
		}
	}
	
	
}
