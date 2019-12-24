package com.sumavision.tetris.attend;

import com.sumavision.tetris.orm.exception.ErrorTypeException;

/**
 * 考勤分类<br/>
 * <b>作者:</b>mR<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月20日 上午11:38:11
 */
public enum AttendClassify {

	
	
	QUEQIN("缺勤", false),
	ZHENGCHANG("正常", false),
	CHIDAO("迟到", true),
	ZAOTUI("早退", true);
	
	private String name;
	
	private boolean show;
	
	private AttendClassify(String name, boolean show){
		this.name = name;
		this.show = show;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isShow() {
		return this.show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public static AttendClassify fromName(String name) throws Exception{
		AttendClassify[] values = AttendClassify.values();
		for(AttendClassify value:values){
			if(value.getName().equals(name)){
				return value;
			}
		}
		throw new ErrorTypeException("name", name);
	}
	
}

