package pub.caterpillar.mvc.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.core.tools.Generate;

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
				V vo = cls.newInstance().set(entity);
				if(vo != null){
					voList.add(vo);
				}
			}
		}
		
		return voList;
		
	}
	
}
