package pub.caterpillar.app.carpool.enumeration;

public enum CarStatus {

	WAITING("接单中"),
	STARTOFF("已出发");
	
	private String name;
	
	private CarStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
