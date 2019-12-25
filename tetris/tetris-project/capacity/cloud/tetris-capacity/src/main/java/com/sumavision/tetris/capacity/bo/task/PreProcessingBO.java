package com.sumavision.tetris.capacity.bo.task;

/**
 * 预处理参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午7:04:56
 */
public class PreProcessingBO {

	/** 去交错 */
	private DeinterlaceBO deinterlace;
	
	/** 缩放 */
	private ScaleBO scale;
	
	/** 裁剪 */
	private CutBO cut;
	
	/** 文本osd */
	private TextOsdBO text_osd;
	
	/** 静态图片osd */
	private PictureOsdBO static_pic_osd;
	
	/** 动态图片osd */
	private PictureOsdBO dynamic_pic_osd;
	
	/** 模糊 */
	private FuzzyBO fuzzy;
	
	/** 图像增强 */
	private EnhanceBO enhance;
	
	/** 重采样 */
	private ResampleBO resample;
	
	/** 帧率变换 */
	private FpsConvertBO fps_convert;

	public DeinterlaceBO getDeinterlace() {
		return deinterlace;
	}

	public PreProcessingBO setDeinterlace(DeinterlaceBO deinterlace) {
		this.deinterlace = deinterlace;
		return this;
	}

	public ScaleBO getScale() {
		return scale;
	}

	public PreProcessingBO setScale(ScaleBO scale) {
		this.scale = scale;
		return this;
	}

	public CutBO getCut() {
		return cut;
	}

	public PreProcessingBO setCut(CutBO cut) {
		this.cut = cut;
		return this;
	}

	public TextOsdBO getText_osd() {
		return text_osd;
	}

	public PreProcessingBO setText_osd(TextOsdBO text_osd) {
		this.text_osd = text_osd;
		return this;
	}

	public PictureOsdBO getStatic_pic_osd() {
		return static_pic_osd;
	}

	public PreProcessingBO setStatic_pic_osd(PictureOsdBO static_pic_osd) {
		this.static_pic_osd = static_pic_osd;
		return this;
	}

	public PictureOsdBO getDynamic_pic_osd() {
		return dynamic_pic_osd;
	}

	public PreProcessingBO setDynamic_pic_osd(PictureOsdBO dynamic_pic_osd) {
		this.dynamic_pic_osd = dynamic_pic_osd;
		return this;
	}

	public EnhanceBO getEnhance() {
		return enhance;
	}

	public PreProcessingBO setEnhance(EnhanceBO enhance) {
		this.enhance = enhance;
		return this;
	}

	public FuzzyBO getFuzzy() {
		return fuzzy;
	}

	public PreProcessingBO setFuzzy(FuzzyBO fuzzy) {
		this.fuzzy = fuzzy;
		return this;
	}

	public ResampleBO getResample() {
		return resample;
	}

	public PreProcessingBO setResample(ResampleBO resample) {
		this.resample = resample;
		return this;
	}

	public FpsConvertBO getFps_convert() {
		return fps_convert;
	}

	public PreProcessingBO setFps_convert(FpsConvertBO fps_convert) {
		this.fps_convert = fps_convert;
		return this;
	}
	
}
