package pub.caterpillar.shell.jcef.handler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.cef.callback.CefCallback;
import org.cef.handler.CefResourceHandlerAdapter;
import org.cef.misc.IntRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.caterpillar.shell.jcef.context.AppContext;

/**
 * 自定义协议
 * lvdeyang 2017年8月4日
 */
public class ClientSchemeHandler extends CefResourceHandlerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientSchemeHandler.class);
	
	//协议头
	public static final String SCHEME = "client";
	
	//协议域
	public static final String DOMAIN = "shell";
	
	//项目根目录
	public static final String basePath = "";

	private byte[] data_;
	
	private String mime_type_;
	
	private int offset_ = 0;

	public ClientSchemeHandler() {
	    super();
	}
	
	@Override
	public synchronized boolean processRequest(CefRequest request, CefCallback callback){
		
	    boolean handled = false;
	    
	    String url = request.getURL();
	    String uri = url.replace(ClientSchemeHandler.SCHEME+"://"+ClientSchemeHandler.DOMAIN+"/", "");
	    
	    if(url.endsWith(".png")){
	    	
	        mime_type_ = "image/png";
	        
	    }else if(url.endsWith(".jpg")){
	    	
	    	mime_type_ = "image/jpeg";
	    	
	    }else if(url.endsWith(".html")){
	    	
	        mime_type_ = "text/html";
	        
	    }else if(url.endsWith(".css")){
	    	
	    	mime_type_ = "text/css";
	    	
	    }else if(url.endsWith(".js")){
	    	
	    	mime_type_ = "application/x-javascript";
	    	
	    }else if(url.endsWith(".json")){
	    	
	    	mime_type_ = "application/json";
	    	
	    }
	    
	    handled = loadContent(uri);
	    
	    if (!handled) {
	        String html = "<html><head><title>Error 404</title></head>";
            html += "<body><h1>Error 404</h1>";
            html += "File  " + uri + " ";
            html += "does not exist</body></html>";
	        data_ = html.getBytes();
	        handled = true;
        }

	    if (handled) {
	        callback.Continue();
	        return true;
	    }

	    return false;
	}

	@Override
	public void getResponseHeaders(CefResponse response, IntRef response_length, StringRef redirectUrl){
	    response.setMimeType(mime_type_);
	    response.setStatus(200);
	    response_length.set(data_.length);
	}

	@Override
	public synchronized boolean readResponse(byte[] data_out, int bytes_to_read, IntRef bytes_read, CefCallback callback) {
	    boolean has_data = false;

	    if(offset_ < data_.length){
	        int transfer_size = Math.min(bytes_to_read, (data_.length - offset_));
	        System.arraycopy(data_, offset_, data_out, 0, transfer_size);
	        offset_ += transfer_size;

	        bytes_read.set(transfer_size);
	        has_data = true;
	    }else{
	        offset_ = 0;
	        bytes_read.set(0);
	    }

	    return has_data;
	}

	@SuppressWarnings("unused")
	private boolean loadContent(String resName) {
		InputStream inStream = null;
		ByteArrayOutputStream outFile = null;
		try {
			AppContext context = AppContext.getInstance();
			File file = new File(context.getBasePath()+"/"+context.getAppfolder()+"/"+resName);
			inStream = new FileInputStream(file);
		    if(inStream != null){
		        outFile = new ByteArrayOutputStream();
		        int readByte = -1;
		        while((readByte=inStream.read()) >= 0){
		        	outFile.write(readByte);
		        }
		        data_ = outFile.toByteArray();
		        return true;
		    }
		}catch(IOException e){
        	logger.error("资源加载失败！", e);
        	if(inStream != null){
        		try {
        			inStream.close();
				} catch (Exception e1) {
					logger.error("释放资源异常", e1);
				}
        	}
        	if(outFile != null){
        		try {
					outFile.close();
				} catch (IOException e2) {
					logger.error("释放资源异常", e2);
				}
        	} 
        }
	    return false;
	}
  
}
