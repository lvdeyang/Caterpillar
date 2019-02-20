package pub.caterpillar.packing.web.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.sun.tools.doclint.Entity;

import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.util.binary.Md5Utils;
import pub.caterpillar.commons.util.path.PathUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.ext.init.InitLoader;
import pub.caterpillar.packing.business.admin.dao.AdminDAO;
import pub.caterpillar.packing.business.admin.enumeration.AdminRoleType;
import pub.caterpillar.packing.business.admin.po.AdminPO;

public class ContextListener extends InitLoader{

	//初始化用户名
	private final String USERNAME = "admin";
	
	//初始化密码
	private final String PASSWARD = "kucheguanjia";
	
	private AdminDAO conn_admin;
	
	@Override
	public void customInitialize() {
		
		//初始化bean
		conn_admin = SpringContext.getBean(AdminDAO.class);
		
		//初始化管理员账号密码
		List<AdminPO> admins = conn_admin.findByField("roleType", AdminRoleType.SUPER);
		if(admins==null || admins.size()<=0){
			AdminPO admin = new AdminPO();
			admin.setUsername(USERNAME);
			
			//密码编码
			byte[] md5Bytes = Md5Utils.encodeMD5(PASSWARD);
	        String base64Str = Md5Utils.encodeBase64(md5Bytes);
	        String hexStr = "{md5}".concat(base64Str);
			
			admin.setPassword(hexStr);
			admin.setRoleType(AdminRoleType.SUPER);
			conn_admin.save(admin);
		}
		
		try{
			String libPath = new StringBufferWrapper().append(PathUtil.getWebinfPath()).append("/").append("lib").toString();
			System.out.println(libPath);
			File lib = new File(libPath);
			File[] jars = lib.listFiles();
			for(File jar:jars){
				JarInputStream jarIn = new JarInputStream(new FileInputStream(jar));
				JarEntry entry = jarIn.getNextJarEntry();
			    while (null != entry) {
			    	String name = entry.getName();
			    	if("caterpillar-fragment.json".equals(name)){
			    		BufferedReader reader = new BufferedReader(new InputStreamReader(jarIn, "utf-8"));
			    		String line = reader.readLine();
			    		StringBufferWrapper module = new StringBufferWrapper();
			    		while (line != null){
			    			module.append(line);
			    			line = reader.readLine();
						}
			    		System.out.println(module.toString());
			    		break;
			    	}else{
			    		entry = jarIn.getNextJarEntry();
			    	}
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//扫描类路径--过滤扫描jar包
	public void classPathScan(Collection<String> jarFilters) throws Exception{
		String[] classPathes = System.getProperty("java.class.path").split(";");
		for(String classPath:classPathes){
			
			String jarName = null;
			
			if(classPath.endsWith(".jar")){
				int i = classPath.lastIndexOf("\\");
				jarName = classPath.substring(i+1, classPath.length());
			}else{
				//扫描文件夹
				jarName = classPath;
			}
			
			if(jarFilters!=null && jarFilters.size()>0){
				for(String jarFilter:jarFilters){
					if(jarName.indexOf(jarFilter) >= 0){
						scan(classPath.trim(), null);
						break;
					}
				}
			}else{
				scan(classPath.trim(), null);
			}
		}
	}
	
	//扫描当前路径--默认扫描当前项目目录或当前jar包--不扫描内部类
	public void scan(String path, String pack) throws Exception{
		
		//包名
		pack = pack==null?"":pack;
		
		//路径
		path = path==null?PathUtil.getClassPath():path;
		
		if(path.endsWith(".jar")){
			//jar包
			JarInputStream jarIn = new JarInputStream(new FileInputStream(path));
		    JarEntry entry = jarIn.getNextJarEntry();
		    while (null != entry) {
		    	String name = entry.getName();
		    	if(name.endsWith(".class") && name.indexOf("$")<=0){
		    		//annotationParser(name.replace(".class", ""));
		    	}
	            entry = jarIn.getNextJarEntry();
	        }
			
		}else{
			File target = new File(path);
			
			if(target.isFile()){
				
				//处理文件
				String name = target.getName();
				if(!name.endsWith(".class") || name.indexOf("$")>=0) return;
				//annotationParser(pack.replace(".class", ""));
				
			}else{
				
				//处理文件夹
				String[] subs = target.list();
				
				for(String sub:subs){
					
					if(!".svn".equals(sub) && !"META-INF".equals(sub)){
						
						String _pack = null;
						if("".equals(pack)){
							_pack = sub;
						}else{
							StringBuffer _pachBuffer = new StringBuffer();
							_pachBuffer.append(pack);
							_pachBuffer.append(".");
							_pachBuffer.append(sub);
							_pack = _pachBuffer.toString();
						}
						
						StringBuffer _pathBuffer = new StringBuffer();
						_pathBuffer.append(path);
						_pathBuffer.append("/");
						_pathBuffer.append(sub);
						String _path = _pathBuffer.toString();
						
						//继续扫描
						scan(_path, _pack);
					}
					
				}
				
			}
			
		}
		
	}
	
}
