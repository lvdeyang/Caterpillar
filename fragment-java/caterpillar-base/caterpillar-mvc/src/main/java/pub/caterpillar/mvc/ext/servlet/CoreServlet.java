package pub.caterpillar.mvc.ext.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.wrapper.UrlDecoderHttpServletRequestWrapper;

@SuppressWarnings("serial")
public class CoreServlet extends DispatcherServlet {
	
	private static final Logger LOG = LoggerFactory.getLogger(CoreServlet.class);
	
	private String encoding = "UTF-8";
	
	private String path404 = "/WEB-INF/jsp/error/error_404.jsp";
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setPath404(String path404) {
		this.path404 = path404;
	}

	@Override
	protected WebApplicationContext createWebApplicationContext(ApplicationContext parent) {
		Class<?> contextClass = getContextClass();
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Servlet with name '" + getServletName() +
					"' will try to create custom WebApplicationContext context of class '" +
					contextClass.getName() + "'" + ", using parent context [" + parent + "]");
		}
		if (!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {
			throw new ApplicationContextException(
					"Fatal initialization error in servlet with name '" + getServletName() +
					"': custom WebApplicationContext class [" + contextClass.getName() +
					"] is not of type ConfigurableWebApplicationContext");
		}
		ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);

		wac.setEnvironment(getEnvironment());
		wac.setParent(parent);
		wac.setConfigLocation("");
		configureAndRefreshWebApplicationContext(wac);
		return wac;
	}
	
	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info(new StringBufferWrapper().append("来访ip：")
															   .append(request.getRemoteAddr())
															   .append("，访问路径：")
															   .append(request.getRequestURL())
															   .toString());
		
		//对GET请求包装request
		if("GET".equals(request.getMethod())){
			request = new UrlDecoderHttpServletRequestWrapper(request, this.encoding);
		}
		
		super.doService(request, response);
	}

	@Override
	protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String reqPath = new StringBufferWrapper().append("访问路径：").append(request.getRequestURL()).toString();
		
		//这个地方放置死循环
		if(reqPath.indexOf(this.path404) >= 0){
			LOG.info(new StringBufferWrapper().append("系统未找到404页面，路径：").append(this.path404).toString());
			return;
		}
		
		//重定向404页面
		request.setAttribute("message", request.getRequestURL());
		request.getRequestDispatcher(this.path404).forward(request, response);
	}
	
}
