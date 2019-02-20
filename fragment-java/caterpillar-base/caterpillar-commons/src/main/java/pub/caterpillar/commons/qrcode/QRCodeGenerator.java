package pub.caterpillar.commons.qrcode;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import pub.caterpillar.commons.util.date.DateUtil;

/**
 * 
 * @author Mr.huang 2017/08/06 path图片保存路径 content生成二维码内容
 */
public class QRCodeGenerator {

	public static void generate(String path, String content) throws WriterException, IOException {
		int width = 100;
		int height = 100;
		String format = "png";
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		File outputFile = new File(path);
		outputFile.mkdirs();
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
	}
	
}
