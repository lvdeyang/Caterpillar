package pub.caterpillar.app.carpool.listener;

import java.util.List;
import pub.caterpillar.app.carpool.dao.SystemPropertiesDAO;
import pub.caterpillar.app.carpool.po.SystemPropertiesPO;
import pub.caterpillar.app.carpool.vo.WeixinConfigVO;
import pub.caterpillar.commons.context.SpringContext;
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

	private SystemPropertiesDAO conn_systemproperties;
	
	private void initBean(){
		conn_systemproperties = SpringContext.getBean(SystemPropertiesDAO.class);
	}
	
	@Override
	public void customInitialize(){
		try{
			initBean();
			
			//初始化微信配置
			WeixinConfigVO config = WeixinConfigVO.getInstance();
			List<SystemPropertiesPO> props = conn_systemproperties.queryWeixinConfig();
			config.set(props);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
