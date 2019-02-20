package pub.caterpillar.app.carpool.enumeration;

public enum SeatStatus {

	FREE("空闲状态"),
	SYSTEM_ORDER("系统接单"),
	PERSONAL_ORDER("私人接单");
	
	private String name;
	
	private SeatStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
