package com.sumavision.tetris.commons.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DownloadHlsUtil {

	/**
	 * 
	 * @param url 播放地址
	 * @return 下载文件地址
	 */
	public static String download(List<String> urls) throws Exception{
		
		
		File file=new File("D:\\merge.ts");
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedInputStream in = null;
		FileOutputStream fileOutputStream=new FileOutputStream("D:\\merge.ts");
		BufferedOutputStream bufferedOutputStream =new BufferedOutputStream(fileOutputStream);
		for (String url : urls) {
			in = new BufferedInputStream(new URL(url).openStream());
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
	        while ((bytesRead = in.read(buffer)) != -1) {
	        	if(bytesRead<1024){
	        		bufferedOutputStream.write(buffer, 0,bytesRead);
	        	}else{
	        		bufferedOutputStream.write(buffer);
	        	}
	        }
		}
		
		
		
        in.close();
        bufferedOutputStream.close();
        fileOutputStream.close();
        return "";
	}
	

	public static List<String> getTsList(String URL) throws IOException {
		List<String> ret=new ArrayList<String>();
		String[] urls=URL.split("/");
		File file=new File("D:\\"+urls[urls.length-1]);
		if(!file.exists()){
			file.createNewFile();
		}
		
		BufferedInputStream in = new BufferedInputStream(new URL(URL).openStream());
		FileOutputStream fileOutputStream=new FileOutputStream("D:\\"+urls[urls.length-1]);
		BufferedOutputStream bufferedOutputStream =new BufferedOutputStream(fileOutputStream);
		
		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		
        while ((bytesRead = in.read(buffer)) != -1) {
        	if(bytesRead<1024){
        		//byte[] temp=new byte[in.available()];
        		bufferedOutputStream.write(buffer, 0,bytesRead);
        	}else{
        		bufferedOutputStream.write(buffer);
        	}
        	
        }
        in.close();
        bufferedOutputStream.close();
        fileOutputStream.close();
        
        BufferedReader reader=new BufferedReader(new FileReader(new File("D:\\"+urls[urls.length-1])));
        urls[urls.length-1]="";
        String line=null;
        while ((line=reader.readLine())!=null) {
			if(!line.trim().isEmpty()&&line.indexOf("#")==-1){
								
				ret.add(StringUtils.join(urls, "/")+line);
				System.out.println(StringUtils.join(urls, "/")+line);
			}
			
		}
        
       return ret;
	}
	
	
}
