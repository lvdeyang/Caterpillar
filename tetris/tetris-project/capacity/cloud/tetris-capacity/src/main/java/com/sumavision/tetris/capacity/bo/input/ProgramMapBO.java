package com.sumavision.tetris.capacity.bo.input;

import com.alibaba.fastjson.JSONObject;

/**
 * 节目映射参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月28日 下午3:08:07
 */
@Deprecated
public class ProgramMapBO {
	
	/** 不做节目映射，由集群指定节目参数 */
	private JSONObject normal_map;
	
	/** 各种类型媒体最多一个，靠媒体类型自动映射 */
	private JSONObject media_type_once_map;

	public JSONObject getNormal_map() {
		return normal_map;
	}

	public ProgramMapBO setNormal_map(JSONObject normal_map) {
		this.normal_map = normal_map;
		return this;
	}

	public JSONObject getMedia_type_once_map() {
		return media_type_once_map;
	}

	public ProgramMapBO setMedia_type_once_map(JSONObject media_type_once_map) {
		this.media_type_once_map = media_type_once_map;
		return this;
	}
	
}
