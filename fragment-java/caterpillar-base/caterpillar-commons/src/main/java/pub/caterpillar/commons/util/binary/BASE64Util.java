package pub.caterpillar.commons.util.binary;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64Util {

	//Base64编码
	private static final BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	
	//Base64解码
	private static final BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	
	//byte[] base64编码
	public static String bytesToBase64(byte[] src){
        String base64 = encoder.encodeBuffer(src).trim();
        return base64;
	}
	
	//base64解码 byte[]
	public static byte[] Base64ToBytes(String src) throws IOException{
        byte[] bytes = decoder.decodeBuffer(src);
        return bytes;
	}
	
}
