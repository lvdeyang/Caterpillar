package pub.caterpillar.app.carpool.enumeration;

public enum SystemPropertiesName {
	
	/*************
	 ***微信属性***
	 *************/
	
	/** 微信公众号appid */
	APPID("appid"),
	/** 微信公众号appsecret */
	APPSECRET("appsecret"),
	/** Token */
	TOKEN("token"),
	/** 微信支付分配的商户id */
	MCH_ID("mch_id"),
	/** 商户名称 */
	SEND_NAME("send_name"),
	/** 商户密钥 */
	KEY("key");
	
	private String name;
	
	private SystemPropertiesName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static SystemPropertiesName fromName(String name) throws Exception{
		SystemPropertiesName[] props = SystemPropertiesName.values();
		for(SystemPropertiesName prop:props){
			if(prop.getName().equals(name)){
				return prop;
			}
		}
		throw new Exception("错误的系统属性名称：" + name);
	}
	
}
