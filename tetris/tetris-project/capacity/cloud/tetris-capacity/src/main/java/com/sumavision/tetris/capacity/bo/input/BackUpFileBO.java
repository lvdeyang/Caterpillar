package com.sumavision.tetris.capacity.bo.input;

/**
 * backup中file参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月6日 下午2:32:36
 */
public class BackUpFileBO {

	private String url;
	
	private String select_index;
	
	private String decode_mode = "cpu";
	
	private String deinterlace_mode;
	
	private Integer nv_card_idx;
	
	public String getUrl() {
		return url;
	}

	public BackUpFileBO setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getSelect_index() {
		return select_index;
	}

	public BackUpFileBO setSelect_index(String select_index) {
		this.select_index = select_index;
		return this;
	}

	public String getDecode_mode() {
		return decode_mode;
	}

	public BackUpFileBO setDecode_mode(String decode_mode) {
		this.decode_mode = decode_mode;
		return this;
	}

	public Integer getNv_card_idx() {
		return nv_card_idx;
	}

	public BackUpFileBO setNv_card_idx(Integer nv_card_idx) {
		this.nv_card_idx = nv_card_idx;
		return this;
	}

	public String getDeinterlace_mode() {
		return deinterlace_mode;
	}

	public BackUpFileBO setDeinterlace_mode(String deinterlace_mode) {
		this.deinterlace_mode = deinterlace_mode;
		return this;
	}

}
