package pub.caterpillar.commons.util.path;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class PathUtil {
	
	//协议
	public static final String PROTOCOL_WEB_HTTP = "http://";
	public static final String PROTOCOL_WEB_HTTPS = "https://";
	
	//默认端口
	public static final String PORT_DEFAULT_WEB_HTTP = "80";
	public static final String PORT_DEFAULT_WEB_HTTPS = "443";
	
	//获取WEB-INF路径
	public static String getWebinfPath() {
		String webinfPath = "";
		URL url = PathUtil.class.getClassLoader().getResource("");
		String path = null;
		try {
			path = url.toURI().getPath();
			webinfPath = path.substring(0, path.indexOf("/WEB-INF") + 8);
		} catch (URISyntaxException e) {
			System.err.println(e);
		}
		return webinfPath;
	}

	//获取classpath
	public static String getClassPath() {
		String classPath = "";
		URL url = PathUtil.class.getClassLoader().getResource("");
		String path = null;
		try {
			path = url.toURI().getPath();
			//兼容maven测试环境
			if(path.indexOf("/classes") > 0){
				classPath = path.substring(0, path.indexOf("/classes") + 8);
			}else{
				classPath = path.substring(0, path.length()-1);
			}
		} catch (URISyntaxException e) {
			System.err.println(e);
		}
		return classPath;
	}

	//获取webapps路径
	public static String getWebappPath() {
		String projectPath = getProjectPath();
		File f = new File(projectPath);
		if (!f.exists()) {
			return projectPath;
		}
		return f.getParent();
	}

	//获取web项目路径
	public static String getProjectPath() {
		String projectPath = "";
		URL url = PathUtil.class.getClassLoader().getResource("");
		String path = null;
		try {
			path = url.toURI().getPath();
			projectPath = path.substring(0, path.indexOf("/WEB-INF"));
		} catch (URISyntaxException e) {
			System.err.println(e);
		}
		return projectPath;
	}
	
	//获取web项目名称
	public static String getProjectName(){
		String projectPath = getProjectPath();
		String[] pathArr = projectPath.split("/");
		return pathArr[pathArr.length-1];
	}
	
	//http
	public static String getFullHttpUrl(String domainName, String reqPath){
		return getFullHttpUrl(domainName, PathUtil.PORT_DEFAULT_WEB_HTTP, reqPath);
	}
	
	public static String getFullHttpUrl(String domainName, String port, String reqPath){
		return getFullUrl(PathUtil.PROTOCOL_WEB_HTTP, domainName, port, reqPath);
	}
	
	//https
	public static String getFullHttpsUrl(String domainName, String reqPath){
		return getFullHttpsUrl(domainName, PathUtil.PORT_DEFAULT_WEB_HTTPS, reqPath);
	}
	
	public static String getFullHttpsUrl(String domainName, String port, String reqPath){
		return getFullUrl(PathUtil.PROTOCOL_WEB_HTTPS, domainName, port, reqPath);
	}
	
	//组件Url @params reqPath要以"/"开头
	public static String getFullUrl(String protocol, String domainName, String port, String reqPath){
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(protocol);
		urlBuilder.append(domainName);
		if(!PathUtil.PORT_DEFAULT_WEB_HTTPS.equals(port) && !PathUtil.PORT_DEFAULT_WEB_HTTP.equals(port)){
			urlBuilder.append(":");
			urlBuilder.append(port);
		}
		urlBuilder.append("/");
		urlBuilder.append(getProjectName());
		urlBuilder.append(reqPath);
		return urlBuilder.toString();
	}
}
