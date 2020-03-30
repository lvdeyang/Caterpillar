package com.sumavision.tetris.temp;

import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

public class GlsVo extends AbstractBaseVO<GlsVo, GlsPo>{
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
	
	
	
	public long getTempId() {
		return tempId;
	}



	public GlsVo setTempId(long tempId) {
		this.tempId = tempId;
		return this;
	}



	public String getContent() {
		return content;
	}



	public GlsVo setContent(String content) {
		this.content = content;
		return this;
	}



	public int getX() {
		return x;
	}



	public GlsVo setX(int x) {
		this.x = x;
		return this;
	}



	public int getY() {
		return y;
	}



	public GlsVo setY(int y) {
		this.y = y;
		return this;
	}



	public int getWidth() {
		return width;
	}



	public GlsVo setWidth(int width) {
		this.width = width;
		return this;
	}



	public int getHeight() {
		return height;
	}



	public GlsVo setHeight(int height) {
		this.height = height;
		return this;
	}



	public String getBackgroundColor() {
		return backgroundColor;
	}



	public GlsVo setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
		return this;
	}



	public String getFontColor() {
		return fontColor;
	}



	public GlsVo setFontColor(String fontColor) {
		this.fontColor = fontColor;
		return this;
	}



	public int getFontSize() {
		return fontSize;
	}



	public GlsVo setFontSize(int fontSize) {
		this.fontSize = fontSize;
		return this;
	}



	public int getRollSpead() {
		return rollSpead;
	}



	public GlsVo setRollSpead(int rollSpead) {
		this.rollSpead = rollSpead;
		return this;
	}



	public String getFontFamily() {
		return fontFamily;
	}



	public GlsVo setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
		return this;
	}



	public String getTrackType() {
		return trackType;
	}



	public GlsVo setTrackType(String trackType) {
		this.trackType = trackType;
		return this;
	}



	public String getLogoPath() {
		return logoPath;
	}



	public GlsVo setLogoPath(String logoPath) {
		this.logoPath = logoPath;
		return this;
	}



	@Override
	public GlsVo set(GlsPo entity) throws Exception {
		// TODO Auto-generated method stub
		this.setBackgroundColor(entity.getBackgroundColor()).setId(entity.getId())
		.setContent(entity.getContent())
		.setFontColor(entity.getFontColor())
		.setFontFamily(entity.getFontFamily())
		.setFontSize(entity.getFontSize())
		.setHeight(entity.getHeight())
		.setLogoPath(entity.getLogoPath())
		.setRollSpead(entity.getRollSpead())
		.setTempId(entity.getTempId())
		.setTrackType(entity.getTrackType())
		.setWidth(entity.getWidth())
		.setX(entity.getX())
		.setY(entity.getY());
		return this;
	}

}
