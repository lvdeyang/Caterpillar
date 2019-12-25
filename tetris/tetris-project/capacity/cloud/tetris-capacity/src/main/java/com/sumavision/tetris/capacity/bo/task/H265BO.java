package com.sumavision.tetris.capacity.bo.task;

/**
 * H265参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 下午3:34:49
 */
public class H265BO extends BaseEncodeBO<H265BO>{
	
	/** 编码档次 baseline/main/high */
	private String profile;
	
	private Integer level;
	
	private boolean mbtree;
	
	private Integer ref_frames;
	
	/** 编码延时 0-100*/
	private Integer lookahead;
	
	private boolean bframe_adaptive;
	
	private Integer vbv_buffer_size;

	private X265BO x265;
	
	private Ux265BO ux265;
	
	private GpuIntelBO msdk_encode;

	public String getProfile() {
		return profile;
	}

	public H265BO setProfile(String profile) {
		this.profile = profile;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public H265BO setLevel(Integer level) {
		this.level = level;
		return this;
	}

	public Integer getRef_frames() {
		return ref_frames;
	}

	public H265BO setRef_frames(Integer ref_frames) {
		this.ref_frames = ref_frames;
		return this;
	}

	public Integer getLookahead() {
		return lookahead;
	}

	public H265BO setLookahead(Integer lookahead) {
		this.lookahead = lookahead;
		return this;
	}

	public boolean isMbtree() {
		return mbtree;
	}

	public H265BO setMbtree(boolean mbtree) {
		this.mbtree = mbtree;
		return this;
	}

	public boolean isBframe_adaptive() {
		return bframe_adaptive;
	}

	public H265BO setBframe_adaptive(boolean bframe_adaptive) {
		this.bframe_adaptive = bframe_adaptive;
		return this;
	}

	public Integer getVbv_buffer_size() {
		return vbv_buffer_size;
	}

	public H265BO setVbv_buffer_size(Integer vbv_buffer_size) {
		this.vbv_buffer_size = vbv_buffer_size;
		return this;
	}

	public X265BO getX265() {
		return x265;
	}

	public H265BO setX265(X265BO x265) {
		this.x265 = x265;
		return this;
	}

	public Ux265BO getUx265() {
		return ux265;
	}

	public H265BO setUx265(Ux265BO ux265) {
		this.ux265 = ux265;
		return this;
	}

	public GpuIntelBO getMsdk_encode() {
		return msdk_encode;
	}

	public H265BO setMsdk_encode(GpuIntelBO msdk_encode) {
		this.msdk_encode = msdk_encode;
		return this;
	}
	
}
