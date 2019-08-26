package com.sumavision.tetris.commons.context;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;

/**
 * Spring上下文环境
 * lvdeyang 2017年6月12日
 */
@Component
public class SpringContext implements ApplicationContextAware {

	//spring配置扫描
	public static final String SPRING_CONFIG_LOCATIONS = "classpath*:spring/*.xml";

	//Spring应用上下文环境
	private static ApplicationContext applicationContext; 
	
	//语言环境
	private static Locale locale = Locale.CHINA;
	
	//异步线程
	private static Thread thread;
	
	/**
	 * 方法概述<br/>
	 * <p>详细描述</p>
	 * <b>作者:</b>Administrator<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月4日 下午7:16:58
	 */
	public static void asynchronizedDone(){
		/*synchronized(thread){
			if(thread != null) thread.notify();
		}*/
	}
	
	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 * 
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContext.applicationContext = applicationContext;
		
		try {
			//同步接口
			Map<String, SystemInitialization> handlerMaps = SpringContext.getBeanOfType(SystemInitialization.class);
			if(handlerMaps!=null && handlerMaps.size()>0){
				List<SystemInitialization> handlers = new ArrayListWrapper<SystemInitialization>().addAll(handlerMaps.values()).getList();
				Collections.sort(handlers, new SystemInitialization.HandlerComparator());
				for(int i=0; i<handlers.size(); i++){
					handlers.get(i).init();
				}
			}
			//异步接口
			Map<String, AsynchronizedSystemInitialization> asynchronizedHandlerMaps = SpringContext.getBeanOfType(AsynchronizedSystemInitialization.class);
			if(asynchronizedHandlerMaps!=null && asynchronizedHandlerMaps.size()>0){
				final List<AsynchronizedSystemInitialization> handlers = new ArrayListWrapper<AsynchronizedSystemInitialization>().addAll(asynchronizedHandlerMaps.values()).getList();
				Collections.sort(handlers, new AsynchronizedSystemInitialization.HandlerComparator());
				thread = new Thread(new Runnable() {
					@Override
					public void run() {
						synchronized (thread) {
							try {
								//thread.wait(0);
								thread.sleep(30*1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
								System.exit(0);
							}
							for(int i=0; i<handlers.size(); i++){
								handlers.get(i).init();
							}
						}
					}
				});
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLocale(String locale) {
		if ("cn".equalsIgnoreCase(locale))
			SpringContext.locale = Locale.CHINA;
		else if ("us".equalsIgnoreCase(locale))
			SpringContext.locale = Locale.US;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * 
	 * @param name
	 *            bean注册名
	 * @param requiredType
	 *            返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	public static Object getBean(String name, Class<?> requiredType)
			throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 
	 * 
	 * @Function: platform.base.util.SpringContextUtil.getBean
	 * @Description:byType
	 * 
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 * 
	 * @author:zhuzheng
	 * @date:2014-5-5 上午10:24:10
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> requiredType) throws BeansException {
		return (T) applicationContext.getBean(requiredType);
	}

	/**
	 * 获取某一类型所有的bean<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年12月14日 上午10:43:01
	 * @param Class<?> type 接口
	 * @return Map<String, ?> bean列表
	 */
	public static <T> Map<String, T> getBeanOfType(Class<T> type) throws Exception{
		return applicationContext.getBeansOfType(type);
	}
	
	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * 
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * 
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}

	@SuppressWarnings("resource")
	public static void loadContext() {
		new ClassPathXmlApplicationContext(SPRING_CONFIG_LOCATIONS);
	}

	public static String i18n(String code, String def) {
		return applicationContext.getMessage(code, null, def, locale);
	}

	public static String i18n(String code) {
		return applicationContext.getMessage(code, null, code, locale);
	}

	public static void registerBean(String beanName, Class<?> clazz) {
		@SuppressWarnings("resource")
		GenericApplicationContext ctx = new GenericApplicationContext();
		BeanDefinitionBuilder builderA = BeanDefinitionBuilder.rootBeanDefinition(clazz);
		builderA.setScope("singleton");
		ctx.registerBeanDefinition(beanName, builderA.getBeanDefinition());
	}
	
}
