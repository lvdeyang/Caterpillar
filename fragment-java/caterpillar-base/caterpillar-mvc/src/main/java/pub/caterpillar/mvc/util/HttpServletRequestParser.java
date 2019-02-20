package pub.caterpillar.mvc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import pub.caterpillar.commons.util.binary.ByteUtil;
import pub.caterpillar.commons.util.file.FileUtil;
import pub.caterpillar.commons.util.path.PathUtil;
import pub.caterpillar.commons.util.wrapper.StringBuilderWrapper;
import pub.caterpillar.mvc.wrapper.CachedHttpServletRequestWrapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 请求体解析器
 * lvdeyang 2017年6月12日
 */
public class HttpServletRequestParser {

	//包装请求
	private CachedHttpServletRequestWrapper request = null;
	
	//项目根路径
	private static final String PATH_WEBROOT = PathUtil.getProjectPath()+"/";
	
	//图片缓存目录
    private File buffer = null;
	
	public HttpServletRequestParser(HttpServletRequest request) throws IOException{
		this.request = new CachedHttpServletRequestWrapper(request);
	}
	
	public HttpServletRequestParser(CachedHttpServletRequestWrapper request){
		this.request = request;
	}
	
	public HttpServletRequestParser(HttpServletRequest request, String buffPath) throws IOException{
		this.buffer = new File(new StringBuilderWrapper().append(PATH_WEBROOT).append(buffPath).toString());
		this.request = new CachedHttpServletRequestWrapper(request);
	}
	
	public HttpServletRequestParser(CachedHttpServletRequestWrapper request, String buffPath){
		this.buffer = new File(new StringBuilderWrapper().append(PATH_WEBROOT).append(buffPath).toString());
		this.request = request;
	}
	
	//将请求体解析成json--针对纯表单提交
	public JSONObject parseJSON() throws IOException {
		BufferedReader br = null;
		try {
			br = this.request.getReader();
			String line = null;
			StringBuilderWrapper sb = new StringBuilderWrapper();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return JSON.parseObject(sb.toString());
		}finally{
			if(br != null) br.close();
		}
	}
	
	//解析带有文件的请求--保存到数据库
	public List<Object> parseRequestAndSaveFile(boolean parseFile) throws Exception{
    	
    	List<Object> result = new ArrayList<Object>();
    	Map<String, String> formList = new HashMap<String, String>();
    	Map<String, Object> fileList = new HashMap<String, Object>();
    	result.add(formList);
    	result.add(fileList);
		
        DiskFileItemFactory factory = new DiskFileItemFactory();
        
        //设置缓冲区大小，这里是4kb
        factory.setSizeThreshold(4096); 
        //设置缓冲区目录
        factory.setRepository(buffer);
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        //设置最大文件尺寸，这里是4MB
        upload.setSizeMax(4194304); 

        //得到所有的文件
        List<FileItem> items = upload.parseRequest(this.request);
        Iterator<FileItem> i = items.iterator();
	    while (i.hasNext()) {
	       FileItem fi = (FileItem) i.next();
	       if(fi.isFormField()){
	    	   formList.put(fi.getFieldName(), fi.getString());
	       }else{
	    	   if(parseFile){
	    		   fileList.put(fi.getFieldName(), ByteUtil.inputStreamToBytes(fi.getInputStream()));
	    	   }else{
	    		   fileList.put(fi.getFieldName(), fi);
	    	   }
	       }
	    }
        return result;
	}
	
    //解析带有文件的请求并保存文件--保存成文件
	public Map<String, String> parseRequestAndSaveFile(Map<String, String> pathMap, List<String> typeList) throws Exception{
  		
  		String message = StringUtils.join(typeList.toArray(), ",");
  		
  		List<Object> parseResult = parseRequestAndSaveFile(false);
  		Map<String, String> formList = (Map<String, String>)parseResult.get(0);
  		Map<String, Object> fileList = (Map<String, Object>)parseResult.get(1);
  		
  		Set<String> fileFiledNameList = fileList.keySet();
  		for(String name:fileFiledNameList){
  			FileItem fileItem = (FileItem)fileList.get(name);
  			String fileName = fileItem.getName();
  			if(fileName != null){
  				byte[] file = ByteUtil.inputStreamToBytes(fileItem.getInputStream());
  				String fileType = FileUtil.getTypeByStream(file);
  				
  				boolean pass = false;
  				for(String type:typeList){
  					if(fileType.equals(type)){
  						pass = true;
  						break;
  					}
  				}
  				if(!pass) throw new Exception("非法的文件格式，必须为："+message);
  				
  				//文件重命名
  				String path = pathMap.get(fileItem.getFieldName());
  				String newFileName = String.valueOf(new Date().getTime());
  				newFileName = newFileName+"."+fileName.split("\\.")[1];
  				formList.put(fileItem.getFieldName(), path + newFileName);
  				File savedFile = new File(PATH_WEBROOT+path, newFileName);
  				fileItem.write(savedFile);
  			}
  		}
          
  		return formList;
  	}
}
