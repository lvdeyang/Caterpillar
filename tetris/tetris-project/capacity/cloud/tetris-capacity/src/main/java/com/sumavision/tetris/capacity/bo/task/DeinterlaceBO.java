package com.sumavision.tetris.capacity.bo.task;

/**
 * 去交错参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午7:07:50
 */
public class DeinterlaceBO {

	/** slow/middle/fast/off,采用何种模式进行去隔行或者关闭 */
	private String mode = "off";
	
	/** cpu/msdk/cuda,采用何种平台去隔行 */
	private String plat = "cpu";
	
	/** N卡卡号，采用cuda进行去隔行时，要指定卡号，默认0 */
	private Integer nv_card_idx;

	public String getMode() {
		return mode;
	}

	public DeinterlaceBO setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public String getPlat() {
		return plat;
	}

	public DeinterlaceBO setPlat(String plat) {
		this.plat = plat;
		return this;
	}

	public Integer getNv_card_idx() {
		return nv_card_idx;
	}

	public DeinterlaceBO setNv_card_idx(Integer nv_card_idx) {
		this.nv_card_idx = nv_card_idx;
		return this;
	}
	
}
