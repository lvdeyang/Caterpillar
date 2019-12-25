package com.sumavision.tetris.capacity.bo.task;

/**
 * 编码基础参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午4:52:55
 */
public class BaseEncodeBO <V extends BaseEncodeBO> {

	private Integer width;
	
	private Integer height;
	
	private String ratio;
	
	private String fps = "25";
	
	private Integer bitrate = 1500000;
	
	private Integer max_bframe;
	
	private Integer max_bitrate = 1500000;
	
	private Integer gop_size = 25;
	
	/** rc模式 vbr/cbr/abr/crf/nearcbr */
	private String rc_mode = "vbr";
	
	private boolean open_gop;

	public Integer getWidth() {
		return width;
	}

	public V setWidth(Integer width) {
		this.width = width;
		return (V)this;
	}

	public Integer getHeight() {
		return height;
	}

	public V setHeight(Integer height) {
		this.height = height;
		return (V)this;
	}

	public String getRatio() {
		return ratio;
	}

	public V setRatio(String ratio) {
		this.ratio = ratio;
		return (V)this;
	}

	public String getFps() {
		return fps;
	}

	public V setFps(String fps) {
		this.fps = fps;
		return (V)this;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public V setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
		return (V)this;
	}

	public Integer getMax_bframe() {
		return max_bframe;
	}

	public V setMax_bframe(Integer max_bframe) {
		this.max_bframe = max_bframe;
		return (V)this;
	}

	public Integer getMax_bitrate() {
		return max_bitrate;
	}

	public V setMax_bitrate(Integer max_bitrate) {
		this.max_bitrate = max_bitrate;
		return (V)this;
	}

	public Integer getGop_size() {
		return gop_size;
	}

	public V setGop_size(Integer gop_size) {
		this.gop_size = gop_size;
		return (V)this;
	}

	public String getRc_mode() {
		return rc_mode;
	}

	public V setRc_mode(String rc_mode) {
		this.rc_mode = rc_mode;
		return (V)this;
	}

	public boolean isOpen_gop() {
		return open_gop;
	}

	public V setOpen_gop(boolean open_gop) {
		this.open_gop = open_gop;
		return (V)this;
	}
	
}
