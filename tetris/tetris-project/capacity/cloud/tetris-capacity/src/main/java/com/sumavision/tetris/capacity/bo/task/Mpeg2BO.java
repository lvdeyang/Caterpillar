package com.sumavision.tetris.capacity.bo.task;

/**
 * mpeg2参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午4:45:40
 */
public class Mpeg2BO extends BaseEncodeBO<Mpeg2BO>{

	private Mpeg2ObjectBO m2v;

	public Mpeg2ObjectBO getM2v() {
		return m2v;
	}

	public Mpeg2BO setM2v(Mpeg2ObjectBO m2v) {
		this.m2v = m2v;
		return this;
	}
	
}
