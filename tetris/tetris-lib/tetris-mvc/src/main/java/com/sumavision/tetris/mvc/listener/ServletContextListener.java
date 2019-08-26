package com.sumavision.tetris.mvc.listener;

import java.io.File;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

/**
 * servlet上下文环境<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月30日 下午3:37:59
 */
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener{

	private static ServletContext servletContext;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		servletContext = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
	/**
	 * 项目路径<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月30日 下午3:33:05
	 */
	@Component
	public static class Path{
		
		/**
		 * 获取webapp路径<br/>
		 * <b>作者:</b>lvdeyang<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b>2018年11月30日 下午2:37:47
		 * @return String webapp 路径
		 */
		public String webappPath() throws FileNotFoundException{
			 return WebUtils.getRealPath(servletContext, "");
		}
		
		/**
		 * WEB-INF路径<br/>
		 * <b>作者:</b>lvdeyang<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b>2019年1月8日 上午11:45:06
		 * @return String WEB-INF路径
		 */
		public String web_inf() throws Exception{
			String webappPath = webappPath();
			return new StringBufferWrapper().append(webappPath)
										    .append(File.separator)
										    .append("WEB-INF")
										    .toString();
		}
		
		/**
		 * 获取webapp下的类路径<br/>
		 * <b>作者:</b>lvdeyang<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b>2019年1月8日 上午11:44:26
		 * @return String webapp下类路径
		 */
		public String webappClassPath() throws Exception{
			String webInfPath = web_inf();
			return new StringBufferWrapper().append(webInfPath)
											.append(File.separator)
										    .append("classes")
										    .toString();
		}
		
		/**
		 * 获取类路径<br/>
		 * <b>作者:</b>lvdeyang<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b>2019年1月8日 下午2:05:16
		 * @return String 类路径
		 */
		public String classPath() throws Exception{
			String path = Thread.currentThread().getContextClassLoader().getResource(File.separator).getPath();
			return new StringBufferWrapper().append(path.split("classes")[0])
											.append("classes")
											.toString();
		}
		
	}
	
	/**
	 * web上下文环境<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年11月30日 下午3:35:56
	 */
	@Component
	public static class WebContext{
		
		/**
		 * 获取ServletContext, 主要用于非controller层<br/>
		 * <b>作者:</b>lvdeyang<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b>2018年11月30日 下午3:36:48
		 * @return
		 */
		public ServletContext servletContext(){
			return servletContext;
		}
		
	}
	
}
