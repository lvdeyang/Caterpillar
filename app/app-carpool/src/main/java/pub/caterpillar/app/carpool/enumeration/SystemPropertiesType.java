package pub.caterpillar.app.carpool.enumeration;

public enum SystemPropertiesType {

	WEIXIN("微信属性");
	
	private String name;
	
	private SystemPropertiesType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static SystemPropertiesType fromName(String name) throws Exception{
		SystemPropertiesType[] types = SystemPropertiesType.values();
		for(SystemPropertiesType type:types){
			if(type.getName().equals(name)){
				return type;
			}
		}
		throw new Exception("错误的系统属性类型：" + name);
	}
	
}
