package pub.caterpillar.app.carpool.enumeration;

public enum DriverDirections {

	ROUTE("出发路线"),
	REVERT("返回路线");
	
	private String name;
	
	private DriverDirections(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
