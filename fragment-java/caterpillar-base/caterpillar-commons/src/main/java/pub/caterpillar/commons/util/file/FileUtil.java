package pub.caterpillar.commons.util.file;

import java.io.IOException;
import java.io.InputStream;
import pub.caterpillar.commons.util.binary.ByteUtil;

/**
 * 文件工具类
 * lvdeyang 2017年3月28日
 */
public class FileUtil {

	//根据文件流读取图片文件真实类型--inputStream只能读一遍，用完这个方法流就不能读了
	public static String getTypeByStream(InputStream is) throws IOException{
		byte[] b = new byte[4];   
		try{
		    is.read(b, 0, b.length);
		}finally{
			if(is != null) is.close();
		}
	    return getTypeByStream(b);
	}
	
	//根据文件流读取图片文件真实类型
	public static String getTypeByStream(byte[] b) throws IOException{
        String type = ByteUtil.bytesToHexString(b).toUpperCase();
        if(type.contains("FFD8FF")){
        	return "jpg";
        }else if(type.contains("89504E47")){
        	return "png";
        }else if(type.contains("47494638")){
        	return "gif";
        }else if(type.contains("49492A00")){
        	return "tif";
        }else if(type.contains("424D")){
        	return "bmp";
        }else if(type.contains("41433130")){
        	return "dwg";
        }else if(type.contains("38425053")){
        	return "psd";
        }else if(type.contains("7B5C727466")){
        	return "rtf";
        }else if(type.contains("3C3F786D6C")){
        	return "xml";
        }else if(type.contains("68746D6C3E")){
        	return "html";
        }else if(type.contains("44656C69766572792D646174653A")){
        	return "eml";
        }else if(type.contains("CFAD12FEC5FD746F")){
        	return "dbx";
        }else if(type.contains("2142444E")){
        	return "pst";
        }else if(type.contains("D0CF11E0")){
        	return "xls.or.doc";
        }else if(type.contains("5374616E64617264204A")){
        	return "mdb";
        }else if(type.contains("FF575043")){
        	return "wpd";
        }else if(type.contains("252150532D41646F6265")){
        	return "eps.or.ps";
        }else if(type.contains("255044462D312E")){
        	return "pdf";
        }else if(type.contains("AC9EBD8F")){
        	return "qdf";
        }else if(type.contains("E3828596")){
        	return "pwl";
        }else if(type.contains("504B0304")){
        	return "zip";
        }else if(type.contains("52617221")){
        	return "rar";
        }else if(type.contains("57415645")){
        	return "wav";
        }else if(type.contains("41564920")){
        	return "avi";
        }else if(type.contains("2E7261FD")){
        	return "ram";
        }else if(type.contains("AC9EBD8F")){
        	return "qdf";
        }else if(type.contains("2E524D46")){
        	return "rm";
        }else if(type.contains("000001BA")){
        	return "mpg";
        }else if(type.contains("000001B3")){
        	return "mpg";
        }else if(type.contains("6D6F6F76")){
        	return "mov";
        }else if(type.contains("3026B2758E66CF11")){
        	return "asf";
        }else if(type.contains("4D546864")){
        	return "mid";
        }
        System.out.println("未获查到当前文件类型！");
        return type;
	}
	 
}
