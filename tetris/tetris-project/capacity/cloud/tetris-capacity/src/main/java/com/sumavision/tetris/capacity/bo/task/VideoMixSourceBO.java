package com.sumavision.tetris.capacity.bo.task;

import java.util.List;

/**
 * 合屏参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午4:45:35
 */
public class VideoMixSourceBO {

	private Integer width;
	
	private Integer height;
	
	/** 输出视频帧率 */
	private String fps = "25";
	
	/** 屏幕布局 */
	private List<RectBO> rect_array;

	public Integer getWidth() {
		return width;
	}

	public VideoMixSourceBO setWidth(Integer width) {
		this.width = width;
		return this;
	}

	public Integer getHeight() {
		return height;
	}

	public VideoMixSourceBO setHeight(Integer height) {
		this.height = height;
		return this;
	}

	public String getFps() {
		return fps;
	}

	public VideoMixSourceBO setFps(String fps) {
		this.fps = fps;
		return this;
	}

	public List<RectBO> getRect_array() {
		return rect_array;
	}

	public VideoMixSourceBO setRect_array(List<RectBO> rect_array) {
		this.rect_array = rect_array;
		return this;
	}
	
}
