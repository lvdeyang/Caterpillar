package com.sumavision.tetris.mvc.wrapper;

import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sumavision.tetris.commons.context.SpringContext;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;
import com.sumavision.tetris.mvc.listener.ServletContextListener.Path;

/**
 * 文件上传请求解析<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月30日 下午4:09:40
 */
public class MultipartHttpServletRequestWrapper extends HttpServletRequestWrapper{

	//设置缓冲区大小，这里是40kb
	private int bufferSize = 1024*40;
	
	//设置最大文件尺寸，这里是10MB
	private long maxSize = 1024*1024*50;
	
	private Map<String, Object> params;
	
	private HttpServletRequest nativeRequest;
	
	private CachedHttpServletRequestWrapper cachedRequest;
	
	public HttpServletRequest getNativeRequest(){
		return this.nativeRequest;
	}
	
	public CachedHttpServletRequestWrapper getCachedRequest() {
		return cachedRequest;
	}

	public MultipartHttpServletRequestWrapper(HttpServletRequest request, long maxSize) throws Exception{
		super(request);
		this.nativeRequest = request;
		//this.cachedRequest = new CachedHttpServletRequestWrapper(request);
		this.maxSize = maxSize;
		this.params = new HashMap<String, Object>();
		parse();
	}
	
	public MultipartHttpServletRequestWrapper(HttpServletRequest request, int bufferSize, long maxSize) throws Exception{
		super(request);
		this.nativeRequest = request;
		//this.cachedRequest = new CachedHttpServletRequestWrapper(request);
		this.bufferSize = bufferSize;
		this.maxSize = maxSize;
		this.params = new HashMap<String, Object>();
		parse();
	}
	
	public MultipartHttpServletRequestWrapper(HttpServletRequest request) throws Exception{
		super(request);
		this.nativeRequest = request;
		//this.cachedRequest = new CachedHttpServletRequestWrapper(request);
		this.params = new HashMap<String, Object>();
		parse();
	}
	
	public MultipartHttpServletRequestWrapper(CachedHttpServletRequestWrapper request, long maxSize) throws Exception{
		super(request.getNativeRequest());
		this.nativeRequest = request.getNativeRequest();
		//this.cachedRequest = request;
		this.maxSize = maxSize;
		this.params = new HashMap<String, Object>();
		parse();
	}
	
	public MultipartHttpServletRequestWrapper(CachedHttpServletRequestWrapper request, int bufferSize, long maxSize) throws Exception{
		super(request.getNativeRequest());
		this.nativeRequest = request.getNativeRequest();
		//this.cachedRequest = request;
		this.bufferSize = bufferSize;
		this.maxSize = maxSize;
		this.params = new HashMap<String, Object>();
		parse();
	}
	
	public MultipartHttpServletRequestWrapper(CachedHttpServletRequestWrapper request) throws Exception{
		super(request.getNativeRequest());
		this.nativeRequest = request.getNativeRequest();
		//this.cachedRequest = request;
		this.params = new HashMap<String, Object>();
		parse();
	}
	
	private void parse() throws Exception{
		//缓冲区目录
		Path path = SpringContext.getBean(Path.class);
		String webappPath = path.webappPath();
		File repositroy = new File(new StringBufferWrapper().append(webappPath)
															.append("\\upload\\buffer")
															.toString());
		if(!repositroy.exists()) repositroy.mkdirs();
		
		//commons upload 设置
		DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(this.bufferSize); 
        factory.setRepository(repositroy);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(this.maxSize); 
        
        //解析http请求
        List<FileItem> items = upload.parseRequest(this.nativeRequest);
        Iterator<FileItem> iterator = items.iterator();
	    while (iterator.hasNext()) {
	       FileItem item = (FileItem) iterator.next();
	       if(item.isFormField()){
	    	   this.params.put(item.getFieldName(), item.getString("utf-8"));
	       }else{
	    	   this.params.put(item.getFieldName(), item);
	       }
	    }
	}
	
	public boolean contains(String name){
		return this.params.containsKey(name);
	}
	
	@Override
	public String getParameter(String name){
		Object param = this.params.get(name);
		if(param == null)
			try{
				throw new Exception("参数不存在："+name);
			}catch(Exception e){
				e.printStackTrace();
			}
		return param.toString();
	}
	
	public Boolean getBoolean(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return Boolean.valueOf(param.toString());
	}
	
	public boolean getBooleanValue(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return Boolean.parseBoolean(param.toString());
	}
	
	public String getUnDecodeString(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return param.toString();
	}
	
	public String getString(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return URLDecoder.decode(param.toString(), "utf-8");
	}
	
	public Integer getInteger(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return Integer.valueOf(param.toString());
	}
	
	public int getIntValue(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return Integer.parseInt(param.toString());
	}
	
	public Long getLong(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return Long.valueOf(param.toString());
	}
	
	public long getLongValue(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return Long.parseLong(param.toString());
	}
	
	public Float getFloat(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return Float.valueOf(param.toString());
	}
	
	public float getFloatValue(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return Float.parseFloat(param.toString());
	}
	
	public InputStream getInputStream(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return ((FileItem)param).getInputStream();
	}
	
	public FileItem getFileItem(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return (FileItem)param;
	}
	
	public CommonsMultipartFile getMultipartFile(String name) throws Exception{
		Object param = this.params.get(name);
		if(param == null) throw new Exception("参数不存在："+name);
		return new CommonsMultipartFile((FileItem)param);
	}
	
}
