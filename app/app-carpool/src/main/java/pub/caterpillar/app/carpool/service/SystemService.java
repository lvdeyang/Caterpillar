package pub.caterpillar.app.carpool.service;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pub.caterpillar.app.carpool.dao.SystemPropertiesDAO;
import pub.caterpillar.app.carpool.enumeration.SystemPropertiesName;
import pub.caterpillar.app.carpool.enumeration.SystemPropertiesType;
import pub.caterpillar.app.carpool.po.SystemPropertiesPO;
import pub.caterpillar.app.carpool.vo.WeixinConfigVO;

@Service("pub.caterpillar.app.carpool.service.SystemService")
public class SystemService {

	@Autowired
	private SystemPropertiesDAO conn_systemproperties;
	
	public void setWeixinConfig(
			String appid,
			String appsecret,
			String token,
			String mch_id,
			String send_name,
			String key) throws Exception{
		synchronized(SystemService.class){
			//备份配置
			WeixinConfigVO config = WeixinConfigVO.getInstance();
			String bak_appid = config.getAppid();
			String bak_appsecret = config.getAppsecret();
			String bak_token = config.getToken();
			String bak_mch_id = config.getMch_id();
			String bak_send_name = config.getSend_name();
			String bak_key = config.getKey();
			boolean bak_empty = config.isEmpty();
			
			try{
				List<SystemPropertiesPO> props = conn_systemproperties.queryWeixinConfig();
				if(props == null) props = new ArrayList<SystemPropertiesPO>();
				
				setOrAddWeixinConfig(SystemPropertiesName.APPID, appid, props);
				setOrAddWeixinConfig(SystemPropertiesName.APPSECRET, appsecret, props);
				setOrAddWeixinConfig(SystemPropertiesName.TOKEN, token, props);
				setOrAddWeixinConfig(SystemPropertiesName.MCH_ID, mch_id, props);
				setOrAddWeixinConfig(SystemPropertiesName.SEND_NAME, send_name, props);
				setOrAddWeixinConfig(SystemPropertiesName.KEY, key, props);
				conn_systemproperties.saveOrUpdateAll(props);
				
				config.set(props);
				
			}catch(Exception e){
				config.setAppid(bak_appid)
					  .setAppsecret(bak_appsecret)
					  .setToken(bak_token)
					  .setMch_id(bak_mch_id)
					  .setSend_name(bak_send_name)
					  .setKey(bak_key)
					  .setEmpty(bak_empty);
				throw e;
			}
			
		}
	}
	
	private void setOrAddWeixinConfig(SystemPropertiesName name, String value, List<SystemPropertiesPO> props){
		SystemPropertiesPO target = null;
		for(SystemPropertiesPO prop:props){
			if(prop.getName().equals(name)){
				target = prop;
			}
		}
		if(target == null){
			target = new SystemPropertiesPO();
			target.setName(name);
			target.setType(SystemPropertiesType.WEIXIN);
			props.add(target);
		}
		target.setValue(value);
	}
	
}
