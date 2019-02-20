package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.ChildPicAndContentPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ChildPicAndContentVO extends AbstractBaseVO<ChildPicAndContentVO, ChildPicAndContentPO> {

	private String childPic;// 导览点图片

	private String childContent;// 导览点文字

	private long childId;// 上级导览点id

	private long voiceId;// 语音id

	// 中文女声
	private String chineseGirl;

	// 中文男声
	private String chineseBoy;

	// 英文女声
	private String englishGirl;

	// 英文男声
	private String englishBoy;

	public String getChineseGirl() {
		return chineseGirl;
	}

	public ChildPicAndContentVO setChineseGirl(String chineseGirl) {
		this.chineseGirl = chineseGirl;
		return this;
	}

	public String getChineseBoy() {
		return chineseBoy;
	}

	public ChildPicAndContentVO setChineseBoy(String chineseBoy) {
		this.chineseBoy = chineseBoy;
		return this;
	}

	public String getEnglishGirl() {
		return englishGirl;
	}

	public ChildPicAndContentVO setEnglishGirl(String englishGirl) {
		this.englishGirl = englishGirl;
		return this;
	}

	public String getEnglishBoy() {
		return englishBoy;
	}

	public ChildPicAndContentVO setEnglishBoy(String englishBoy) {
		this.englishBoy = englishBoy;
		return this;
	}

	public long getVoiceId() {
		return voiceId;
	}

	public ChildPicAndContentVO setVoiceId(long voiceId) {
		this.voiceId = voiceId;
		return this;
	}

	public long getChildId() {
		return childId;
	}

	public ChildPicAndContentVO setChildId(long childId) {
		this.childId = childId;
		return this;
	}

	public String getChildPic() {
		return childPic;
	}

	public ChildPicAndContentVO setChildPic(String childPic) {
		this.childPic = childPic;
		return this;
	}

	public String getChildContent() {
		return childContent;
	}

	public ChildPicAndContentVO setChildContent(String childContent) {
		this.childContent = childContent;
		return this;
	}

	@Override
	public ChildPicAndContentVO set(ChildPicAndContentPO entity) throws Exception {
		this.setId(entity.getId()).setUuid(entity.getUuid()).setUpdateTime(entity.getUpdateTime())
				.setChildPic(entity.getChildPic()).setChildId(entity.getChildId()).setVoiceId(entity.getVoiceId())
				.setChineseGirl(entity.getChineseGirl())
				.setChineseBoy(entity.getChineseBoy())
				.setEnglishGirl(entity.getEnglishGirl())
				.setEnglishBoy(entity.getEnglishBoy())
				.setChildContent(entity.getChildContent());
		return this;
	}

}