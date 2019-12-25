package com.sumavision.tetris.capacity.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sumavision.tetris.capacity.service.InitService;

@WebListener
public class InitListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		
		InitService initService = ctx.getBean(InitService.class);
		
		try {
			initService.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
