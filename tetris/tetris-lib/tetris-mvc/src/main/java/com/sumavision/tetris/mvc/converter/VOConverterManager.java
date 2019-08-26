package com.sumavision.tetris.mvc.converter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据对象转换器管理单元
 * lvdeyang 2017年6月23日
 */
public class VOConverterManager {
	
	//单例
	private static VOConverterManager instance;
	
	private VOConverterManager(){
		this.converterPool = new ConcurrentHashMap<Class, VOConverter>();
	}
	
	public static VOConverterManager getInstance(){
		if(instance == null){
			instance = new VOConverterManager();
		}
		return instance;
	}
	
	//转换器池
	private ConcurrentHashMap<Class, VOConverter> converterPool = null;
	
	//注册转换器
	public void register(Class vocls, VOConverter converter){
		this.converterPool.put(vocls, converter);
	}
	
	//获取转换器
	public VOConverter getConverter(Class vocls){
		return this.converterPool.get(vocls);
	}
	
}
