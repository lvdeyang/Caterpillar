package com.guolaiwan.app.web.listener;

import pub.caterpillar.mvc.init.InitLoader;

public class ContextListener extends InitLoader{

	@Override
	public void customInitialize() {
		
		System.out.println("context 初始化!");
	
	}
	
	
}
