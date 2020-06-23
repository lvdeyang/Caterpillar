package com.sumavision.tetris.temp;

import com.sumavision.tetris.orm.po.AbstractBasePO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TETRIS_SHOW_TRANCECODE")
public class TempPo extends AbstractBasePO {
    private static final long serialVersionUID = 1L;
    private int x;//分辨率宽
    private int y;//分辨率高
    private String ratio;//宽高比 4:3 16:9
    private int rate;//码率bps
    private int frame;//帧率
    private String name;//模板名称
    private String type;//push 推流，transcode 转码
    private String filePath;//垫片路径
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
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getFrame() {
		return frame;
	}
	public void setFrame(int frame) {
		this.frame = frame;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
    
    
    
}
