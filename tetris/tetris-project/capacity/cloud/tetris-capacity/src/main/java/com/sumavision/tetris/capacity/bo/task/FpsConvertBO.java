package com.sumavision.tetris.capacity.bo.task;

/**
 * 帧率变换参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月18日 下午1:50:26
 */
public class FpsConvertBO {

	private String fps;
	
	private String fps_change_mode;

	public String getFps() {
		return fps;
	}

	public FpsConvertBO setFps(String fps) {
		this.fps = fps;
		return this;
	}

	public String getFps_change_mode() {
		return fps_change_mode;
	}

	public void setFps_change_mode(String fps_change_mode) {
		this.fps_change_mode = fps_change_mode;
	}
	
}
