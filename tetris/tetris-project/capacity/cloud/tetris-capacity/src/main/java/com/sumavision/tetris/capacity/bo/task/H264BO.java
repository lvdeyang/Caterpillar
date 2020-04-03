
package com.sumavision.tetris.capacity.bo.task;

/**
 * h264参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月30日 上午10:03:34
 */
public class H264BO extends BaseEncodeBO<H264BO>{

	/** 编码档次 baseline/main/high */
	private String profile;
	
	private Integer level;
	
	private Integer ref_frames;
	
	/** 编码延时 0-100*/
	private Integer lookahead;
	
	private boolean scenecut;
	
	private boolean mbtree;
	
	/** 熵编码算法 cabac/cavlc */
	private String entropy_type;
	
	/** 编码方式 Progressive/Interlace/MbAFF/PAFF */
	private String encoding_type;
	
	private Integer vbv_buffer_size;
	
	private X264BO x264;
	
	private Uux264BO uux264;
	
	private IntelBO msdk_encode;
	
	private CudaBO cuda_encode;

	public String getProfile() {
		return profile;
	}

	public H264BO setProfile(String profile) {
		this.profile = profile;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public H264BO setLevel(Integer level) {
		this.level = level;
		return this;
	}

	public Integer getRef_frames() {
		return ref_frames;
	}

	public H264BO setRef_frames(Integer ref_frames) {
		this.ref_frames = ref_frames;
		return this;
	}

	public Integer getLookahead() {
		return lookahead;
	}

	public H264BO setLookahead(Integer lookahead) {
		this.lookahead = lookahead;
		return this;
	}

	public boolean isScenecut() {
		return scenecut;
	}

	public H264BO setScenecut(boolean scenecut) {
		this.scenecut = scenecut;
		return this;
	}

	public boolean isMbtree() {
		return mbtree;
	}

	public H264BO setMbtree(boolean mbtree) {
		this.mbtree = mbtree;
		return this;
	}

	public String getEntropy_type() {
		return entropy_type;
	}

	public H264BO setEntropy_type(String entropy_type) {
		this.entropy_type = entropy_type;
		return this;
	}

	public String getEncoding_type() {
		return encoding_type;
	}

	public H264BO setEncoding_type(String encoding_type) {
		this.encoding_type = encoding_type;
		return this;
	}

	public Integer getVbv_buffer_size() {
		return vbv_buffer_size;
	}

	public H264BO setVbv_buffer_size(Integer vbv_buffer_size) {
		this.vbv_buffer_size = vbv_buffer_size;
		return this;
	}

	public X264BO getX264() {
		return x264;
	}

	public H264BO setX264(X264BO x264) {
		this.x264 = x264;
		return this;
	}

	public Uux264BO getUux264() {
		return uux264;
	}

	public H264BO setUux264(Uux264BO uux264) {
		this.uux264 = uux264;
		return this;
	}

	public IntelBO getMsdk_encode() {
		return msdk_encode;
	}

	public H264BO setMsdk_encode(IntelBO msdk_encode) {
		this.msdk_encode = msdk_encode;
		return this;
	}

	public CudaBO getCuda_encode() {
		return cuda_encode;
	}

	public H264BO setCuda_encode(CudaBO cuda_encode) {
		this.cuda_encode = cuda_encode;
		return this;
	}
	
}
