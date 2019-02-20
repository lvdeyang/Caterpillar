package pub.guolaiwan.app.classify;

public enum DistributorType {

	PROVINCE("省"),
	CITY("市"),
	COUNTY("县");
	
	private String name;
	
	private DistributorType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
