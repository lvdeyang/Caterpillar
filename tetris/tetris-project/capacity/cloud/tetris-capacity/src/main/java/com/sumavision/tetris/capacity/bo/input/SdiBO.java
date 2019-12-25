package com.sumavision.tetris.capacity.bo.input;

/**
 * sdi参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 上午10:42:46
 */
public class SdiBO {

	/** 采集卡的类型 */
	private String card_type;
	
	/** 卡号,多张卡时选 */
	private Integer card_no;
	
	/** 卡上的端口号 */
	private Integer card_port;

	public String getCard_type() {
		return card_type;
	}

	public SdiBO setCard_type(String card_type) {
		this.card_type = card_type;
		return this;
	}

	public Integer getCard_no() {
		return card_no;
	}

	public SdiBO setCard_no(Integer card_no) {
		this.card_no = card_no;
		return this;
	}

	public Integer getCard_port() {
		return card_port;
	}

	public SdiBO setCard_port(Integer card_port) {
		this.card_port = card_port;
		return this;
	}
	
}
