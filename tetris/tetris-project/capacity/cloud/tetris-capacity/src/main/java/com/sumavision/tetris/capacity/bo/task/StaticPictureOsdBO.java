package com.sumavision.tetris.capacity.bo.task;

import java.util.List;

/**
 * 图片osd参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年12月9日 下午3:36:55
 */
public class StaticPictureOsdBO {
	
	private String plat = "cpu";
	
	private Integer nv_card_idx;

	private List<PictureOsdObjectBO> static_pic_osds ;

	public String getPlat() {
		return plat;
	}

	public StaticPictureOsdBO setPlat(String plat) {
		this.plat = plat;
		return this;
	}

	public Integer getNv_card_idx() {
		return nv_card_idx;
	}

	public StaticPictureOsdBO setNv_card_idx(Integer nv_card_idx) {
		this.nv_card_idx = nv_card_idx;
		return this;
	}

	public List<PictureOsdObjectBO> getStatic_pic_osds() {
		return static_pic_osds;
	}

	public StaticPictureOsdBO setStatic_pic_osds(List<PictureOsdObjectBO> static_pic_osds) {
		this.static_pic_osds = static_pic_osds;
		return this;
	}

}
