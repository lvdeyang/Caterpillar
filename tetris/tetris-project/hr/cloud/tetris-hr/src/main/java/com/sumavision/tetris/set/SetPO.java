package com.sumavision.tetris.set;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.sumavision.tetris.attend.AttendClassify;
import com.sumavision.tetris.orm.po.AbstractBasePO;


@Entity
@Table(name="TETRIS_SET")
public class SetPO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private Date curDate;
	
	private String scheName;
	
    private Date start;
	
	private Date end;
	
	private AttendClassify attendState;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getScheName() {
		return scheName;
	}

	public void setScheName(String scheName) {
		this.scheName = scheName;
	}
	@Enumerated(value = EnumType.STRING)
	public AttendClassify getAttendState() {
		return attendState;
	}

	public void setAttendState(AttendClassify attendState) {
		this.attendState = attendState;
	}

	
	
	
}
