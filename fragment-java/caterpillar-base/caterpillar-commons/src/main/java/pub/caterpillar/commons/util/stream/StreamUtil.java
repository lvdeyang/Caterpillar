package pub.caterpillar.commons.util.stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class StreamUtil {

	//输入流转化为字节数组
	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
		    output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
	
	//从异常流中获取异常信息字符串
	public static String getStackTrace(Throwable t) throws IOException{
		if(t == null)
	        return null;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try{
	        t.printStackTrace(new PrintStream(baos));
	    }finally{
	        baos.close();
	    }
	    return baos.toString();
	}
}
