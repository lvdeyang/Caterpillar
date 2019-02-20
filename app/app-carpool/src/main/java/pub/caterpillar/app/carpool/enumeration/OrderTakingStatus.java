package pub.caterpillar.app.carpool.enumeration;

public enum OrderTakingStatus {

	NEW("未接单"),
	TAKED("已接单"),
	SUCCESS("已完成"),
	CANCEL("已取消");
	
	private String name;
	
	private OrderTakingStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
}
