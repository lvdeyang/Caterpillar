package com.guolaiwan.app.interfac.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.druid.sql.visitor.functions.Length;


public class FilterSensitive {
	private String ENCODING = "utf-8";
	/**
	 * 
	 * @param comment 评论语句
	 * @return 是否有,敏感词 有返回敏感词下标 无 返回-1 
	 * @throws Exception
	 */
	public  int readSensitiveWordFile(String comment) throws Exception{
	List<String> list = null;
	//敏感词下标
	int subscript = -1;
	String path=URLDecoder.decode(FilterSensitive.class.getClassLoader().getResource("/").getPath())+File.separator+"com"+File.separator+"guolaiwan"+File.separator+"app"+
			File.separator+"interfac"+File.separator+"util"+File.separator;
		File file = new File(path+"sensitivewords.txt");    //读取文件
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),ENCODING);
		try {
			
			if(file.isFile() && file.exists()){      //文件流是否存在
				list = new ArrayList<String>();
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				while((txt = bufferedReader.readLine()) != null){    //读取文件，将文件内容放入到list中
					list.add(txt);
			    }
				for (String string : list) {
					if (subscript == -1) {
						subscript  =	comment.indexOf(string);
					}
				}
			}
			else{         //不存在抛出异常信息
				throw new Exception("敏感词库文件不存在");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			read.close();     //关闭文件流
		}
		return subscript;
	}
   	
	
}
