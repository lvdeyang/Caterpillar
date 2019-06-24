package com.guolaiwan.bussiness.admin.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.VideoPicType;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 小视频、图文
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_sys_VideoPic")
public class VideoPicPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	// 名称
	private String name;
	// 来源
	private String source;
	// 文字内容
	private String content;
	// 路径
	private String fUrl;
	// 类型
	private VideoPicType type;
	// 访问量
	private long visitNum;
	// 用户Id(发表者的id)
	private long userId;
	// 关联点赞表
	private List<VPRelPO> VPRels;
	// 关联收藏表
	private List<VPCommentPO> VPComments;
	// 显示图片
	private String headPic;
	// MP3路径
	private String mrul;

	private long merchantId;

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMrul() {
		return mrul;
	}

	public void setMrul(String mrul) {
		this.mrul = mrul;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getfUrl() {
		return fUrl;
	}

	public void setfUrl(String fUrl) {
		this.fUrl = fUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Enumerated(EnumType.STRING)
	public VideoPicType getType() {
		return type;
	}

	public void setType(VideoPicType type) {
		this.type = type;
	}

	public long getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(long visitNum) {
		this.visitNum = visitNum;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@OneToMany(mappedBy = "videoPic", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public List<VPRelPO> getVPRels() {
		return VPRels;
	}

	public void setVPRels(List<VPRelPO> vPRels) {
		VPRels = vPRels;
	}

	@OneToMany(mappedBy = "videoPic", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	@OrderBy("updateTime DESC")
	public List<VPCommentPO> getVPComments() {
		return VPComments;
	}

	public void setVPComments(List<VPCommentPO> vPComments) {
		VPComments = vPComments;
	}

}
