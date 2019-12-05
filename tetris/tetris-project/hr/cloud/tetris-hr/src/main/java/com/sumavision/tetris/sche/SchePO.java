package com.sumavision.tetris.sche;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 菜单数据，包含父菜单和子菜单<br/>
 * <p>详细描述</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年11月20日 上午10:41:49
 */
@Entity
@Table(name="TETRIS_SCHE")
public class SchePO extends AbstractBasePO{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Date start;
	
	private Date end;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date date) {
		this.start = date;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	
	
}
