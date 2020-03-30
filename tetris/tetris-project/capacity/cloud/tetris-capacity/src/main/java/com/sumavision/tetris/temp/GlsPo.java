package com.sumavision.tetris.temp;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sumavision.tetris.orm.po.AbstractBasePO;

@Entity
@Table(name = "TETRIS_SHOW_GLS")
public class GlsPo extends AbstractBasePO{
	private long tempId;
	private String content;
	private int x;
	private int y;
	private int width;
	private int height;
	private String backgroundColor;//(255,255,255,60)
	private String fontColor;//(255,255,255,255)
	private int fontSize;
	private int rollSpead;//80
	private String fontFamily;//STZhongsong
	private String trackType;//滚动字幕方向right_to_left
	private String logoPath;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public int getRollSpead() {
		return rollSpead;
	}
	public void setRollSpead(int rollSpead) {
		this.rollSpead = rollSpead;
	}
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	public String getTrackType() {
		return trackType;
	}
	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public long getTempId() {
		return tempId;
	}
	public void setTempId(long tempId) {
		this.tempId = tempId;
	}
	
	
}
