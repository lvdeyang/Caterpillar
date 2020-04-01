package com.sumavision.tetris.capacity.server;

import com.sumavision.tetris.mvc.converter.AbstractBaseVO;

public class TempVo{
	private Long id;
	private int x;//分辨率宽
    private int y;//分辨率高
    private String ratio;//宽高比 4:3 16:9
    private int rate;//码率bps
    private int frame;//帧率
    private String name;//模板名称
    
	public int getX() {
		return x;
	}

	public TempVo setX(int x) {
		this.x = x;
		return this;
	}

	public int getY() {
		return y;
	}

	public TempVo setY(int y) {
		this.y = y;
		return this;
	}

	public String getRatio() {
		return ratio;
	}

	public TempVo setRatio(String ratio) {
		this.ratio = ratio;
		return this;
	}

	public int getRate() {
		return rate;
	}

	public TempVo setRate(int rate) {
		this.rate = rate;
		return this;
	}

	public int getFrame() {
		return frame;
	}

	public TempVo setFrame(int frame) {
		this.frame = frame;
		return this;
	}

	public String getName() {
		return name;
	}

	public TempVo setName(String name) {
		this.name = name;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
