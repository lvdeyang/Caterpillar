package pub.caterpillar.fragment.core.ui.listener;

import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.fragment.core.module.tool.CaterpillarModuleManager;
import pub.caterpillar.mvc.ext.init.InitLoader;

/**
 * context监听器<br/>
 * <p>
 *     1.主web工程要创建成模块</br>
 *     2.在主web工程中不再需要配置content监听器<br/>
 *     3.在主web工程中不再需要配置SpringMvc<br/>
 *     2.在主web工程中的初始化由模块初始化代替<br/>
 * </p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午2:03:44
 */
public class ContextListener extends InitLoader{

	@Override
	public void customInitialize(){
		
		try {
			
			//扫描模块
			CaterpillarModuleManager moduleManager = SpringContext.getBean(CaterpillarModuleManager.class);
			moduleManager.moduleScan();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
}
