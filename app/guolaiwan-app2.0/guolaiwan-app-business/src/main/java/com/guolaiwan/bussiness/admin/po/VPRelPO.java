package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 点赞收藏表
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_sys_VPRel")
public class VPRelPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	//操作者
	private Long userId;
	//是否点赞
	private int praise;
	//点赞时间
	private Date praiseTime;
	//是否收藏
	private int collection;
	//收藏时间
	private Date collectionTime;
	//关联的图文小视频
	private VideoPicPO videoPic;

	
	public int getPraise() {
		return praise;
	}
	public void setPraise(int praise) {
		this.praise = praise;
	}
	public int getCollection() {
		return collection;
	}
	public void setCollection(int collection) {
		this.collection = collection;
	}
	public Date getPraiseTime() {
		return praiseTime;
	}
	public void setPraiseTime(Date praiseTime) {
		this.praiseTime = praiseTime;
	}
	public Date getCollectionTime() {
		return collectionTime;
	}
	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@ManyToOne
	@JoinColumn(name = "videoPic_id")
	public VideoPicPO getVideoPic() {
		return videoPic;
	}
	public void setVideoPic(VideoPicPO videoPic) {
		this.videoPic = videoPic;
	}


}
