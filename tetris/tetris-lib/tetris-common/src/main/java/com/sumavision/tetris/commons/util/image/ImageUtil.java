package com.sumavision.tetris.commons.util.image;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.sumavision.tetris.commons.util.binary.ByteUtil;
import com.sumavision.tetris.commons.util.file.FileUtil;

@Component
public class ImageUtil {

	//前缀
	private final String SCHEMA_PREFIX = "data:image/";
	
	//后缀
	private final String SCHEMA_SUFFIX = ";base64,"; 
	
	private final sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	
	/**
	 * 获取图片base64编码 格式的UrlSchema<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月12日 上午11:19:59
	 * @param byte[] b 图片
	 * @return String UrlSchema
	 */
	public String getUrlSchema(byte[] b) throws IOException{
		byte[] typeB = new byte[4]; 
		for(int i=0; i<4; i++){
			typeB[i] = b[i];
		}
		String type = FileUtil.getTypeByStream(typeB);
		StringBuilder urlSchemaBuilder = new StringBuilder();
		urlSchemaBuilder.append(SCHEMA_PREFIX);
		urlSchemaBuilder.append(type);
		urlSchemaBuilder.append(SCHEMA_SUFFIX);
		String base64Image = encoder.encode(b).trim();
		urlSchemaBuilder.append(base64Image);
		return urlSchemaBuilder.toString();
	}
	
	/**
	 * 获取图片base64编码 格式的UrlSchema<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月12日 上午11:19:59
	 * @param InputStream in 图片
	 * @return String UrlSchema
	 */
	public String getUrlSchema(InputStream in) throws IOException{
		return getUrlSchema(ByteUtil.inputStreamToBytes(in));
	}
	
}