package pub.caterpillar.mvc.wrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import pub.caterpillar.commons.util.binary.ByteUtil;

/**
 * 缓存http请求体
 * lvdeyang 2017年6月12日
 */
public class CachedHttpServletRequestWrapper extends HttpServletRequestWrapper{

	 private final byte[] cache;
	 
	 public byte[] getBytes(){
		 return cache;
	 }
	  
	 public CachedHttpServletRequestWrapper(HttpServletRequest request) throws IOException {  
	     super(request); 
	     try{
	    	 cache = ByteUtil.inputStreamToBytes(request.getInputStream());  
	     }finally{
	    	 request.getInputStream().close();
	     }
	 }  
	  
	 @Override
	 public BufferedReader getReader() throws IOException {  
	     return new BufferedReader(new InputStreamReader(getInputStream(), "utf-8"));  
	 }
	 
	 public BufferedReader getReader(String encoding) throws IOException {  
	     return new BufferedReader(new InputStreamReader(getInputStream(), encoding));  
	 }  
	      
	 @Override  
	 public ServletInputStream getInputStream() throws IOException {  
	     final ByteArrayInputStream bais = new ByteArrayInputStream(cache);  
	     return new ServletInputStream() {  
	              
	         @Override  
	         public int read() throws IOException {  
	             return bais.read();  
	         }

			 @Override
			 public boolean isFinished() {
				 return false;
			 }

			 @Override
			 public boolean isReady() {
				 return false;
			 }

			 @Override
			 public void setReadListener(ReadListener readListener) {
				
			 }  
	     };  
	 } 
}
