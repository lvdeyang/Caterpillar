package pub.caterpillar.shell.jcef;

import java.io.File;
import org.cef.OS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.caterpillar.shell.jcef.window.JCEFWindow;

/**
 * 桌面程序壳
 * lvdeyang 2017年8月2日
 */
public class App {
	
	private final static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args){
		
		try{
			boolean osrEnabledArg = OS.isLinux();
			
		    String cookiePath = null;
		    
		    for(String arg:args){
		    	arg = arg.toLowerCase();
		    	if (!OS.isLinux() && arg.equals("--off-screen-rendering-enabled")){
		    		osrEnabledArg = true;
		    	}else if(arg.startsWith("--cookie-path=")){
			        cookiePath = arg.substring("--cookie-path=".length());
			        File testPath = new File(cookiePath);
			        if(!testPath.isDirectory() || !testPath.canWrite()) {
			        	logger.info("路径 ：" + cookiePath + "不存在或没有权限访问！");
			        	cookiePath = null;
			        }else {
			        	logger.info("cookies 路径：" + cookiePath);
			        }
		        }
		    }
		    
		    //开启离屏渲染
		    osrEnabledArg = true;
		    logger.info("离屏渲染： " + (osrEnabledArg ? "开启" : "关闭"));
		    
		    new JCEFWindow(osrEnabledArg, args, JCEFWindow.SettingsType.FULLSCREEN.getSettings()).draw();
		}catch(Exception e){
			logger.error("发生异常啦！", e);
		}
	}
	
}
