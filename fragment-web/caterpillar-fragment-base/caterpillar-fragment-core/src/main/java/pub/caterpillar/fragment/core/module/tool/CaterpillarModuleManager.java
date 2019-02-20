package pub.caterpillar.fragment.core.module.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.fragment.core.module.base.CaterpillarModule;

/**
 * 模块管理器<br/>
 * <p>
 *     1.模块加载<br/>
 *     2.模块管理方法<br/>
 * </p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午1:54:34
 */
public class CaterpillarModuleManager {
	
	/** 日志对象 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CaterpillarModuleManager.class); 
	
	/** 配置文件名称 */
	private static final String JSONFILENAME = "caterpillar-module.json";
	
	/** jar文件后缀 */
	private static final String SUFFIX_JAR = ".jar";
	
	/** class文件后缀 */
	private static final String SUFFIX_CLASS = ".class";
	
	/** WEB-INF下类路径的分隔符 */
	private static final String SEPARATOR_CLASSPATH = "/WEB-INF/classes";
	
	/** 模块配置文件的编码格式 */
	private static final String ENCODE_JSON = "utf-8";

	/**
	 * 模块遍历<br/>
	 * <p>
	 *     1.遍历spring中配置的模块bean<br/>
	 *     2.获取模块bean对应的配置文件<br/>
	 *     3.bean排序
	 *     4.bean初始化
	 * </p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午5:12:57
	 */
	public void moduleScan() throws Exception{
		
		//配置模块bean
		String[] moduelNames = SpringContext.getApplicationContext().getBeanNamesForType(CaterpillarModule.class);
		List<CaterpillarModule> modules = new ArrayList<CaterpillarModule>();
		for(int i=0; i<moduelNames.length; i++){
			String moduleName = moduelNames[i];
			CaterpillarModule module = (CaterpillarModule)SpringContext.getBean(moduleName);
			//这个地方可以获取当前类所在的jar包全路径或当前类的全路径
			String path = module.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			if(path == null){
				//这个地方就是加载出错了，绕过这个地方
				LOGGER.info(new StringBufferWrapper().append("模块：“").append(moduleName).append("”加载失败！").toString());
				continue;
			}
			JSONObject config = null;
			if(path.endsWith(SUFFIX_JAR)){
				config = jarScan(path);
			}else if(path.endsWith(SUFFIX_CLASS)){
				config = classPathScan(path);
			}else{
				//这个地方也是不知道加载的是什么东西，绕过这个地方
				LOGGER.info(new StringBufferWrapper().append("模块：“").append(moduleName).append("”加载失败！").toString());
				continue;
			}
			
			if(config == null){
				//没有获取到模块的配置，绕过吧
				LOGGER.info(new StringBufferWrapper().append("未获取到模块：“").append(moduleName).append("”的配置文件：“").append(JSONFILENAME).append("”").toString());
				continue;
			}else{
				module.setConfig(config);
				preInit(module.getName());
				modules.add(module);
				afterInit(module.getName());
			}
		}
		
		//排序
		Collections.sort(modules);
		
		//模块初始化
		for(int i=0; i<modules.size(); i++){
			modules.get(i).init();
		}
		
	}
	
	/**
	 * 遍历jar包获取模块配置文件<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午5:44:46
	 * @param jarPath jar包的全路径
	 * @return JSONObject json文件的内容
	 * @throws Exception
	 */
	private JSONObject jarScan(String jarPath) throws Exception{
		
		JarFile jarFile = null;
		
		try{
			//遍历jar包
			jarFile = new JarFile(jarPath);  
		    Enumeration<JarEntry> entrys = jarFile.entries();  
		    JarEntry configEntry = null;
		    while (entrys.hasMoreElements()) {  
		        JarEntry jarEntry = entrys.nextElement();  
		        if(JSONFILENAME.equals(jarEntry.getName())){
		        	configEntry = jarEntry;
		        	break;
		        }
		    }  
		    
		    if(configEntry == null) return null;
		    
		    //读取文件的内容
		    InputStream jsonStream = jarFile.getInputStream(configEntry);
		    return readJson(jsonStream);
		    
		}finally{
			if(jarFile != null) jarFile.close();
		}
	}
	
	/**
	 * 遍历/WEB-INF/classes路径获取模块配置文件<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午5:46:09
	 * @param path
	 * @return
	 */
	private JSONObject classPathScan(String path) throws Exception{
		
		String classPath = new StringBufferWrapper().append(path.split(SEPARATOR_CLASSPATH)[0])
													.append(SEPARATOR_CLASSPATH)
													.toString();
		
		File classPathFolder = new File(classPath);
	    File jsonFile = classPathScan(classPathFolder);
	    if(jsonFile == null) return null;
	    
	    InputStream jsonStream = new FileInputStream(jsonFile);
	    return readJson(jsonStream);
		
	}
	
	/**
	 * 递归遍历/WEB-INF/classes，查找模块配置文件<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午6:08:03
	 * @param file 遍历的文件或文件夹
	 * @return File 配置文件File对象
	 */
	private File classPathScan(File file){
		if(file.isDirectory()){
			File[] subs = file.listFiles();
			for(File sub:subs){
				File result = classPathScan(sub);
				if(result != null){
					return result;
				}
			}
			return null;
		}else {
			if(JSONFILENAME.equals(file.getName())){
				return file;
			}else {
				return null;
			}
		}
	}
	
	/**
	 * 从流中读取字符串并且转换成json<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午6:09:46
	 * @param stream 要读取的流
	 * @return JSONObject 读取后的转换结果
	 * @throws Exception
	 */
	private JSONObject readJson(InputStream stream) throws Exception{
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new InputStreamReader(stream, ENCODE_JSON));  
		    String readLine = null; 
		    StringBufferWrapper json = new StringBufferWrapper();
		    while((readLine=reader.readLine()) != null){  
		    	json.append(readLine);  
		    } 
			
		    return JSON.parseObject(json.toString());
		}finally{
			if(stream != null) stream.close();
			if(reader != null) reader.close();
		}
	}
	
	/**
	 * 加载模块前打印日志<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午6:25:17
	 * @param name 模块名称
	 */
	private void preInit(String name){
		LOGGER.info(new StringBufferWrapper().append("\n\n")
											 .append("**************************\n")
										     .append(name)
										     .append("\n**************************")
										     .toString());
	}
	
	/**
	 * 加载模块后打印日志<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午6:25:17
	 * @param name 模块名称
	 */
	private void afterInit(String name){
		LOGGER.info(new StringBufferWrapper().append("**************************\n")
										     .append(name)
										     .append("\n**************************")
										     .append("\n\n")
										     .toString());
	}
	
}
