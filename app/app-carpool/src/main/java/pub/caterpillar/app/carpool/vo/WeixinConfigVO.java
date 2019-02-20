package pub.caterpillar.app.carpool.vo;

import java.util.Collection;

import net.sf.ehcache.statistics.extended.ExtendedStatistics.Statistic;
import pub.caterpillar.app.carpool.enumeration.SystemPropertiesName;
import pub.caterpillar.app.carpool.po.SystemPropertiesPO;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class WeixinConfigVO {
	
	//服务器域名
	public static final String HOST = "yueba.net.cn";
	
	//服务根目录
	public static final String WEBROOT = "/carpool";

	private static WeixinConfigVO instance;
	
	private WeixinConfigVO(){
		this.empty = true;
	}
	
	public static WeixinConfigVO getInstance(){
		synchronized (WeixinConfigVO.class) {
			if(instance == null){
				instance = new WeixinConfigVO();
			}
			return instance;
		}
	}
	
	private boolean empty;
	
	private String appid;
	
	private String appsecret;
	
	private String token;
	
	private String mch_id;
	
	private String send_name;
	
	private String key;

	public boolean isEmpty() {
		return empty;
	}

	public WeixinConfigVO setEmpty(boolean empty) {
		this.empty = empty;
		return this;
	}

	public String getAppid() {
		return appid;
	}

	public WeixinConfigVO setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public WeixinConfigVO setAppsecret(String appsecret) {
		this.appsecret = appsecret;
		return this;
	}

	public String getToken() {
		return token;
	}

	public WeixinConfigVO setToken(String token) {
		this.token = token;
		return this;
	}

	public String getMch_id() {
		return mch_id;
	}

	public WeixinConfigVO setMch_id(String mch_id) {
		this.mch_id = mch_id;
		return this;
	}

	public String getSend_name() {
		return send_name;
	}

	public WeixinConfigVO setSend_name(String send_name) {
		this.send_name = send_name;
		return this;
	}

	public String getKey() {
		return key;
	}

	public WeixinConfigVO setKey(String key) {
		this.key = key;
		return this;
	}

	public WeixinConfigVO set(Collection<SystemPropertiesPO> properties) throws Exception{
		boolean empty = false;
		SystemPropertiesPO prop = findPropertiesByName("appid", properties);
		this.setAppid(prop==null?"未设置":prop.getValue());
		empty = prop==null?true:empty;
		
		prop = findPropertiesByName("appsecret", properties);
		this.setAppsecret(prop==null?"未设置":prop.getValue());
		empty = prop==null?true:empty;
		
		prop = findPropertiesByName("key", properties);
		this.setKey(prop==null?"未设置":prop.getValue());
		empty = prop==null?true:empty;
		
		prop = findPropertiesByName("mch_id", properties);
		this.setMch_id(prop==null?"未设置":prop.getValue());
		empty = prop==null?true:empty;
		
		prop = findPropertiesByName("send_name", properties);
		this.setSend_name(prop==null?"未设置":prop.getValue());
		empty = prop==null?true:empty;
		
		prop = findPropertiesByName("token", properties);
		this.setToken(prop==null?"未设置":prop.getValue());
		empty = prop==null?true:empty;
		
		this.setEmpty(empty);
		return this;
	}
	
	private SystemPropertiesPO findPropertiesByName(String name, Collection<SystemPropertiesPO> properties) throws Exception{
		if(properties==null || properties.size()<=0) return null;
		SystemPropertiesName targetName = SystemPropertiesName.fromName(name);
		for(SystemPropertiesPO property:properties){
			if(property.getName().equals(targetName)){
				return property;
			}
		}
		return null;
	}
	
	//生成basepath
	public static String getBasePath(){
		return new StringBufferWrapper().append("http://")
										.append(HOST)
										.append(WEBROOT)
										.toString();
	}
	
}
