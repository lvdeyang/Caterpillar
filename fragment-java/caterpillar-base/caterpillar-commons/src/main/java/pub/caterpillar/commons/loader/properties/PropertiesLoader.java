package pub.caterpillar.commons.loader.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import pub.caterpillar.commons.util.path.PathUtil;
import com.alibaba.fastjson.JSON;

/**
 * @author ldy
 * 加载properties
 */
public class PropertiesLoader {
	
	private static PropertiesLoader instance;
	
	//单例
	private PropertiesLoader(){}
	
	public static PropertiesLoader getInstance(){
		if(instance == null){
			instance = new PropertiesLoader();
		}
		return instance;
	}

	//path相对于classpath
	public <T>T load(String path, Class<T> c) throws Exception{
		File file = null;
		FileInputStream inputStream = null;
		InputStreamReader reader = null;
		Properties prop = null;
		try{
			String classPath = PathUtil.getClassPath();
			String packagePath = classPath + path;
			
			if(packagePath.indexOf("%20") >= 0){
				packagePath = packagePath.replaceAll("%20", " ");
				System.out.println("项目路径中存在空格，请将空格删除！");
			}
			System.out.println("加载properties：" + packagePath);
			file = new File(packagePath);
			inputStream = new FileInputStream(file);
			reader = new InputStreamReader(inputStream, "utf-8");
			
			
			prop = new Properties();
			prop.load(reader);
			
			T transObj = JSON.parseObject(JSON.toJSONString(prop), c);
			
			return transObj;
			
		}finally{
			if(reader != null){
				reader.close();
			}
			if(inputStream != null){
				inputStream.close();
			}
			if(prop != null){
				prop.clear();
			}
		}
	}
}
