package com.sumavision.tetris.capacity.bo.task;

/**
 * Mpeg2Object参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午5:01:19
 */
public class Mpeg2ObjectBO {

	private boolean ts_control;
	
	private boolean scenecut;
	
	private Integer keyint_max;

	public boolean isTs_control() {
		return ts_control;
	}

	public Mpeg2ObjectBO setTs_control(boolean ts_control) {
		this.ts_control = ts_control;
		return this;
	}

	public boolean isScenecut() {
		return scenecut;
	}

	public Mpeg2ObjectBO setScenecut(boolean scenecut) {
		this.scenecut = scenecut;
		return this;
	}

	public Integer getKeyint_max() {
		return keyint_max;
	}

	public Mpeg2ObjectBO setKeyint_max(Integer keyint_max) {
		this.keyint_max = keyint_max;
		return this;
	}
	
}
