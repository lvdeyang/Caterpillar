package pub.caterpillar.commons.file.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//OSSUtils.createFolder(OSSUtils.bucketName, "image/test/");
		File file = new File("D:\\fish.jpg");
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			OSSUtils.uploadObjectOSS("image/test/", "test.jpg", file,inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
