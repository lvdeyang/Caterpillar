package pub.caterpillar.app.carpool.enumeration;

public enum OrderBalanceStatus {

	YES("已结算"),
	NO("未结算");
	
	private String name;
	
	private OrderBalanceStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
