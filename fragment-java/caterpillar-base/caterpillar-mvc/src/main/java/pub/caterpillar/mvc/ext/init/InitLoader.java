package pub.caterpillar.mvc.ext.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ConfigurableWebEnvironment;
import org.springframework.web.context.ContextLoaderListener;
import pub.caterpillar.commons.context.SpringContext;

/**
 * 上下文监听器
 * lvdeyang 2017年6月12日
 */
public class InitLoader extends ContextLoaderListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

	@Override
	public final void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		customInitialize();
	}
	
	@Override
	protected final void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac, ServletContext sc) {
		if(ObjectUtils.identityToString(wac).equals(wac.getId())){
			String idParam = sc.getInitParameter(CONTEXT_ID_PARAM);
			if(idParam != null){
				wac.setId(idParam);
			}else{
				wac.setId(ConfigurableWebApplicationContext.APPLICATION_CONTEXT_ID_PREFIX + ObjectUtils.getDisplayString(sc.getContextPath()));
			}
		}

		wac.setServletContext(sc);
		wac.setConfigLocation(SpringContext.SPRING_CONFIG_LOCATIONS);
		
		/*String configLocationParam = sc.getInitParameter(CONFIG_LOCATION_PARAM);
		if (configLocationParam != null) {
			wac.setConfigLocation(configLocationParam);
		}else{
			wac.setConfigLocation("classpath*:spring/*.xml");
		}*/
		
		ConfigurableEnvironment env = wac.getEnvironment();
		if (env instanceof ConfigurableWebEnvironment) {
			((ConfigurableWebEnvironment) env).initPropertySources(sc, null);
		}

		customizeContext(sc, wac);
		wac.refresh();
	}
	
	/**
	 * 
	 * 
	 * @Function: platform.mvc.initloader.InitLoader.customInitialize
	 * @Description:自定义初始化接口,等待后续重写
	 *
	 *
	 * @author:zhuzheng
	 * @date:2014-5-16 上午11:52:26
	 *
	 */
	public void customInitialize(){
		
	}
}
