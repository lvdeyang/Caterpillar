package com.sumavision.tetris.punch;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sumavision.tetris.orm.po.AbstractBasePO;

/**
 * 外勤照片<br/>
 * <b>作者:</b>SJJ<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2020年1月3日  
 */
@Entity
@Table(name="TETRIS_OUTPIC")
public class PicPo extends AbstractBasePO {
	private static final long serialVersionUID = 1L;
	//关联考勤表
    Long attendId;
    //主键id
    String picture;
	/**
	 * @return the attendId
	 */
	public Long getAttendId() {
		return attendId;
	}
	/**
	 * @param attendId the attendId to set
	 */
	public void setAttendId(Long attendId) {
		this.attendId = attendId;
	}
	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
    
}
