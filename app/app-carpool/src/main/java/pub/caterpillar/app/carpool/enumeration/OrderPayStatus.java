package pub.caterpillar.app.carpool.enumeration;

public enum OrderPayStatus {

	UNPAY("未支付"),
	PAYED("已支付");
	
	private String name;
	
	private OrderPayStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
