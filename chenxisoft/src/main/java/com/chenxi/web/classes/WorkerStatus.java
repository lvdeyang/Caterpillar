package com.chenxi.web.classes;


public enum WorkerStatus {
	APPLY("APPLY"),
	CHECKING("CHECKING"),
	PASSED("PASSED"),
	REFUSE("REFUSE");
	
	
    private String name;
	
	private WorkerStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFiled() throws Exception{
		if(this.equals(APPLY)){
			return "APPLY";
		}else if(this.equals(CHECKING)){
			return "CHECKING";
		}else if(this.equals(PASSED)){
			return "PASSED";
		}else if(this.equals(REFUSE)){
			return "REFUSE";
		}else {
			throw new Exception("错误的");
		}
	}
	
	public static WorkerStatus fromName(String name) throws Exception{
		if("APPLY".equals(name)){
			return APPLY;
		}else if("CHECKING".equals(name)){
			return CHECKING;
		}else if("PASSED".equals(name)){
			return PASSED;
		} else if("REFUSE".equals(name)){
			return REFUSE;
		} else {
			throw new Exception("错误的："+name);
		}
	}
	
	public static WorkerStatus fromString(String s) throws Exception{
		if("APPLY".equals(s)){
			return APPLY;
		}else if("CHECKING".equals(s)){
			return CHECKING;
		} else if("PASSED".equals(s)){
			return PASSED;
		} else if("REFUSE".equals(s)){
			return REFUSE;
		} else {
			throw new Exception("错误的："+s);
		}
	}
}
