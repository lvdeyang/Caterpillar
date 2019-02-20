package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_childpicandcontent")
public class ChildPicAndContentPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

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

	public void setChineseGirl(String chineseGirl) {
		this.chineseGirl = chineseGirl;
	}

	public String getChineseBoy() {
		return chineseBoy;
	}

	public void setChineseBoy(String chineseBoy) {
		this.chineseBoy = chineseBoy;
	}

	public String getEnglishGirl() {
		return englishGirl;
	}

	public void setEnglishGirl(String englishGirl) {
		this.englishGirl = englishGirl;
	}

	public String getEnglishBoy() {
		return englishBoy;
	}

	public void setEnglishBoy(String englishBoy) {
		this.englishBoy = englishBoy;
	}

	public long getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(long voiceId) {
		this.voiceId = voiceId;
	}

	public long getChildId() {
		return childId;
	}

	public void setChildId(long childId) {
		this.childId = childId;
	}

	public String getChildPic() {
		return childPic;
	}

	public void setChildPic(String childPic) {
		this.childPic = childPic;
	}

	public String getChildContent() {
		return childContent;
	}

	public void setChildContent(String childContent) {
		this.childContent = childContent;
	}

}