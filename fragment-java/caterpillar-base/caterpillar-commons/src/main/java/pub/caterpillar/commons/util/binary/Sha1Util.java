package pub.caterpillar.commons.util.binary;

import java.security.MessageDigest;

/**
 * @author ldy
 * sha1加密
 */
public class Sha1Util {

	//测试方法
	public static void main(String[] args){
		System.out.println(Sha1Util.getSha1("a123456"));
	}
	
	//获取哈希加密字符串
	public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];      
            }
            return new String(buf);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
	}
   
}
