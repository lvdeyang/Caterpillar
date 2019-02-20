package pub.caterpillar.app.carpool.enumeration;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public enum OrderPriceType {

	CARPOOL("拼车"),
	CHARTER("包车");
	
	private String name;
	
	private OrderPriceType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static OrderPriceType fromName(String name) throws Exception{
		OrderPriceType[] types =OrderPriceType.values();
		for(OrderPriceType type:types){
			if(type.getName().equals(name)){
				return type;
			}
		}
		throw new Exception(new StringBufferWrapper().append("错误的打车模式：").append(name).toString());
	}
	
}
