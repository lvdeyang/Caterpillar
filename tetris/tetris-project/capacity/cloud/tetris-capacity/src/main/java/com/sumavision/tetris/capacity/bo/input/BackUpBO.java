package com.sumavision.tetris.capacity.bo.input;

/**
 * 节目备份参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 上午10:53:14
 */
public class BackUpBO {

	private BackUpPassByBO back_up_passby;
	
	private BackUpEsAndRawBO back_up_es;
	
	private BackUpEsAndRawBO back_up_raw;

	public BackUpPassByBO getBack_up_passby() {
		return back_up_passby;
	}

	public BackUpBO setBack_up_passby(BackUpPassByBO back_up_passby) {
		this.back_up_passby = back_up_passby;
		return this;
	}

	public BackUpEsAndRawBO getBack_up_es() {
		return back_up_es;
	}

	public BackUpBO setBack_up_es(BackUpEsAndRawBO back_up_es) {
		this.back_up_es = back_up_es;
		return this;
	}

	public BackUpEsAndRawBO getBack_up_raw() {
		return back_up_raw;
	}

	public BackUpBO setBack_up_raw(BackUpEsAndRawBO back_up_raw) {
		this.back_up_raw = back_up_raw;
		return this;
	}
	
}
