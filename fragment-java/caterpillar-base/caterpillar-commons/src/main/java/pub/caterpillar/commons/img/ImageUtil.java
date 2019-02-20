package pub.caterpillar.commons.img;

import java.io.IOException;
import java.io.InputStream;
import pub.caterpillar.commons.util.binary.BASE64Util;
import pub.caterpillar.commons.util.binary.ByteUtil;
import pub.caterpillar.commons.util.file.FileUtil;

public class ImageUtil {

	//前缀
	private static final String SCHEMA_PREFIX = "data:image/";
	
	//后缀
	private static final String SCHEMA_SUFFIX = ";base64,"; 
	
	//获取url schema
	public static String getUrlSchema(byte[] b) throws IOException{
		byte[] typeB = new byte[4]; 
		for(int i=0; i<4; i++){
			typeB[i] = b[i];
		}
		String type = FileUtil.getTypeByStream(typeB);
		StringBuilder urlSchemaBuilder = new StringBuilder();
		urlSchemaBuilder.append(SCHEMA_PREFIX);
		urlSchemaBuilder.append(type);
		urlSchemaBuilder.append(SCHEMA_SUFFIX);
		String base64Image = BASE64Util.bytesToBase64(b);
		urlSchemaBuilder.append(base64Image);
		return urlSchemaBuilder.toString();
	}
	
	//获取url schema
	public static String getUrlSchema(InputStream in) throws IOException{
		return getUrlSchema(ByteUtil.inputStreamToBytes(in));
	}
	
}
