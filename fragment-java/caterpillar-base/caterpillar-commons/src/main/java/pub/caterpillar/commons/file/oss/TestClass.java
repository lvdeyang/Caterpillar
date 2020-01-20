package pub.caterpillar.commons.file.oss;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.commons.qrcode.QRCodeGenerator;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			//路径
			Date d = new Date();
			String datepath =sdf.format(d); 
			String fileName = "000000.png";
			String ydNO = File.separator+"orderNO"+File.separator+datepath+File.separator+fileName;
			String path = "D:\\"+ydNO;
			//生成二维码
			QRCodeGenerator.generate(path, "000000");
			File newFile=new File(path);
			OSSUtils.createFolder("glw-old-file", "file/orderNO/"+datepath+"/");
			OSSUtils.uploadObjectOSS("file/orderNO/"+datepath+"/", fileName,newFile, new FileInputStream(newFile));

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
