package pub.caterpillar.weixin.comm;

import pub.caterpillar.commons.loader.properties.PropertiesLoader;
import pub.caterpillar.weixin.comm.bo.WeixinConfigBO;
/**
 * @author ldy
 * 获取配置
 */
public class ConfigUtil {

	private static ConfigUtil instance;
	
	//单例模式
	private ConfigUtil(){}
	
	public static ConfigUtil getInstance(){
		if(instance == null){
			instance = new ConfigUtil();
		}
		return instance;
	}
	
	/******************
	 *   微信公众号配置
	 ******************/
	
	//微信配置
	private WeixinConfigBO config_weixin;
	
	//配置文件路径
	private String config_path_weixin = "/properties/weixin/weixin.properties";
	
	//获取微信配置
	public WeixinConfigBO getWeixinConfigBean() throws Exception{
		if(config_weixin == null){
			config_weixin = PropertiesLoader.getInstance().load(config_path_weixin, WeixinConfigBO.class);
		}
		return config_weixin;
	}
}
