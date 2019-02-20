package pub.caterpillar.commons.img;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

/**
 * 二维码生成器
 * @author lvdeyang
 */
public class QrCodeUtils {

	//获取生成器
	public static Generator getGenerator(String type){
		Generator generator = null;
		switch (type) {
		case Generator.SIMPLE:
			generator = new Generator(Generator.SIMPLE);
			break;
		default:
			generator = new Generator(Generator.SIMPLE);
			break;
		}
		return generator;
	}
	
	//二维码配置
	public static class QrConfig{
		
		//二维码内容
		private String content;
		
		//二维码存储文件夹
		private String folder;
		
		//二维码文件名称
		private String fileName;
		
		//宽度
		private int width;
		
		//高度
		private int height;

		public String getContent() {
			return content;
		}

		public QrConfig setContent(String content) {
			this.content = content;
			return this;
		}
		
		public String getFolder() {
			return folder;
		}

		public QrConfig setFolder(String folder) {
			this.folder = folder;
			return this;
		}

		public String getFileName() {
			return fileName;
		}

		public QrConfig setFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public int getWidth() {
			return width;
		}

		public QrConfig setWidth(int width) {
			this.width = width;
			return this;
		}

		public int getHeight() {
			return height;
		}

		public QrConfig setHeight(int height) {
			this.height = height;
			return this;
		}
		
	}
	
	//生成器
	public static class Generator{
		
		//简单二维码
		public static final String SIMPLE = "simple";
		
		//生成器类型
		private String type;
		
		public Generator(String type){
			this.type = type;
		}
		
		//生成
		public void generate(QrConfig config) throws Exception{
			switch (this.type) {
			case SIMPLE:
				simpleGenerate(config);
				break;
			default:
				simpleGenerate(config);
				break;
			}
		}
		
		//生成简单二维码
		private void simpleGenerate(QrConfig config) throws Exception{
	        
			//配置
			String content = config.getContent();
			String folder = config.getFolder();
			String fileName = config.getFileName();
			int width = config.getWidth();
			int height = config.getHeight();
			
			// 图像类型 
	        String format = fileName.split("\\.")[1]; 
	        
	        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
	        
	        //生成矩阵  
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
	        
	        if(!folder.endsWith("\\") && !folder.endsWith("/")) folder = new StringBufferWrapper().append(folder).append("\\").toString();
	        
	        //构建路径
	        Path path = FileSystems.getDefault().getPath(folder, fileName);  
	        
	        // 输出图像  
	        MatrixToImageWriter.writeToPath(bitMatrix, format, path);
		}
		
	}
	
	//解析器--这个地方先不封装了
	public static class Parser{
		/*public void testDecode() {  
	        String filePath = "D://zxing.png";  
	        BufferedImage image;  
	        try {  
	            image = ImageIO.read(new File(filePath));  
	            LuminanceSource source = new BufferedImageLuminanceSource(image);  
	            Binarizer binarizer = new HybridBinarizer(source);  
	            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
	            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
	            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
	            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
	            JSONObject content = JSONObject.parseObject(result.getText());  
	            System.out.println("图片中内容：  ");  
	            System.out.println("author： " + content.getString("author"));  
	            System.out.println("zxing：  " + content.getString("zxing"));  
	            System.out.println("图片中格式：  ");  
	            System.out.println("encode： " + result.getBarcodeFormat());  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (NotFoundException e) {  
	            e.printStackTrace();  
	        }  
	    }*/  
	}
	
	//测试方法
	public static void main(String[] args) throws Exception{
		Generator generator = QrCodeUtils.getGenerator(QrCodeUtils.Generator.SIMPLE);
		
		QrConfig config = new QrCodeUtils.QrConfig().setContent("http://www.caterpillar.pub/packing/")
													.setFolder("D://")
												    .setFileName("test.png")
												    .setWidth(200)
												    .setHeight(200);
		generator.generate(config);
	}
	
}
