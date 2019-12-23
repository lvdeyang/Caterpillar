package com.sumavision.tetris.capacity.bo.task;

/**
 * AvsObject参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午5:10:21
 */
public class AvsObjectBO {

	/** avs-jizhun/avs-zengqiang/avs-guangdian */
	private String profile;
	
	private String pixel_format = "YUV420";
	
	private boolean bframe_adaptive;
	
	private Integer lookahead;
	
	private boolean mbtree_switch;
	
	/** Progressive/Integererlace/PAFF */
	private String encoding_type;
	
	private Integer vbv_buffer_size;
	
	private boolean avs_aec;
	
	private boolean avs_rdo;
	
	private boolean avs_dblock_filter;
	
	private boolean avs_weighting_quant;
	
	private boolean avs_chroma_offset;
	
	private Integer avs_delta_cb;
	
	private Integer avs_delta_cr;

	public String getProfile() {
		return profile;
	}

	public AvsObjectBO setProfile(String profile) {
		this.profile = profile;
		return this;
	}

	public String getPixel_format() {
		return pixel_format;
	}

	public AvsObjectBO setPixel_format(String pixel_format) {
		this.pixel_format = pixel_format;
		return this;
	}

	public boolean isBframe_adaptive() {
		return bframe_adaptive;
	}

	public AvsObjectBO setBframe_adaptive(boolean bframe_adaptive) {
		this.bframe_adaptive = bframe_adaptive;
		return this;
	}

	public Integer getLookahead() {
		return lookahead;
	}

	public AvsObjectBO setLookahead(Integer lookahead) {
		this.lookahead = lookahead;
		return this;
	}

	public boolean isMbtree_switch() {
		return mbtree_switch;
	}

	public AvsObjectBO setMbtree_switch(boolean mbtree_switch) {
		this.mbtree_switch = mbtree_switch;
		return this;
	}

	public String getEncoding_type() {
		return encoding_type;
	}

	public AvsObjectBO setEncoding_type(String encoding_type) {
		this.encoding_type = encoding_type;
		return this;
	}

	public Integer getVbv_buffer_size() {
		return vbv_buffer_size;
	}

	public AvsObjectBO setVbv_buffer_size(Integer vbv_buffer_size) {
		this.vbv_buffer_size = vbv_buffer_size;
		return this;
	}

	public boolean isAvs_aec() {
		return avs_aec;
	}

	public AvsObjectBO setAvs_aec(boolean avs_aec) {
		this.avs_aec = avs_aec;
		return this;
	}

	public boolean isAvs_rdo() {
		return avs_rdo;
	}

	public AvsObjectBO setAvs_rdo(boolean avs_rdo) {
		this.avs_rdo = avs_rdo;
		return this;
	}

	public boolean isAvs_dblock_filter() {
		return avs_dblock_filter;
	}

	public AvsObjectBO setAvs_dblock_filter(boolean avs_dblock_filter) {
		this.avs_dblock_filter = avs_dblock_filter;
		return this;
	}

	public boolean isAvs_weighting_quant() {
		return avs_weighting_quant;
	}

	public AvsObjectBO setAvs_weighting_quant(boolean avs_weighting_quant) {
		this.avs_weighting_quant = avs_weighting_quant;
		return this;
	}

	public boolean isAvs_chroma_offset() {
		return avs_chroma_offset;
	}

	public AvsObjectBO setAvs_chroma_offset(boolean avs_chroma_offset) {
		this.avs_chroma_offset = avs_chroma_offset;
		return this;
	}

	public Integer getAvs_delta_cb() {
		return avs_delta_cb;
	}

	public AvsObjectBO setAvs_delta_cb(Integer avs_delta_cb) {
		this.avs_delta_cb = avs_delta_cb;
		return this;
	}

	public Integer getAvs_delta_cr() {
		return avs_delta_cr;
	}

	public AvsObjectBO setAvs_delta_cr(Integer avs_delta_cr) {
		this.avs_delta_cr = avs_delta_cr;
		return this;
	}
	
}
