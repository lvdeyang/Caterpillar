package com.sumavision.tetris.sche;

import java.util.Date;
import java.util.List;

import com.sumavision.tetris.commons.util.date.DateUtil;
import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

public class ScheVO extends AbstractBaseVO<ScheVO, SchePO>{

	private String name;
	
	private String start;
	
	private String end;
	
	
	public String getName() {
		return name;
	}


	public ScheVO setName(String name) {
		this.name = name;
		return this;
	}


	public String getStart() {
		return start;
	}


	public ScheVO setStart(String start) {
		this.start = start;
		return this;
	}


	public String getEnd() {
		return end;
	}


	public ScheVO setEnd(String end) {
		this.end = end;
		return this;
	}


	@Override
	public ScheVO set(SchePO entity) throws Exception {
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(entity.getUpdateTime()==null?"":DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setName(entity.getName())
			.setEnd(DateUtil.format(entity.getEnd(),"HH:mm"))
			.setStart(DateUtil.format(entity.getStart(),"HH:mm"));
		return this;
	}

}
