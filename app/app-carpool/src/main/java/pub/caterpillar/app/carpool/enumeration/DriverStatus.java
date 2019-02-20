package pub.caterpillar.app.carpool.enumeration;

public enum DriverStatus {

	OFF("下岗"),
	ON("上岗");
	
	private String name;
	
	private DriverStatus(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
	
}
