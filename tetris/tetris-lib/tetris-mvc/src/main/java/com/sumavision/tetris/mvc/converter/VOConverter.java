package com.sumavision.tetris.mvc.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * VO转换器 
 * lvdeyang 2017年6月23日
 */
public class VOConverter<V extends AbstractBaseVO<V, P>, P> {

	//转换方法
	public List<V> convert(Collection<? extends P> entityList, Class<V> cls) throws Exception{
		
		List<V> voList = new ArrayList<V>();
		
		if(entityList != null){
			for(P entity:entityList){
				V vo = cls.newInstance();
				voList.add(vo.set(entity));
			}
		}
		
		return voList;
		
	}
	
}
