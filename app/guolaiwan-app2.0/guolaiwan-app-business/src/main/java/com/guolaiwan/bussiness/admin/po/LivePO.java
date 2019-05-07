package com.guolaiwan.bussiness.admin.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.LiveType;
import com.guolaiwan.bussiness.admin.po.live.SubLivePO;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_live")
public class LivePO extends AbstractBasePO {

	private static final long serialVersionUID = 6285795210300284752L;

	//直播名称
	private String liveName;
	//用户
	private Long userId; 
	//商家
	private Long merchantId;
	//
	private String pubName;
	//直播状态（开播，停播，封号）
	private LiveStatusType liveStatusType;
	//直播类型（商户、用户）
	private LiveType liveType;
	
	//直播封面
	private String cover;
	//是否通知客户端
	private boolean flag;
	// 数量
	private int count;
	private int isback;
	private int isrecord;
	private int recordSize;
	
	private String leshiyunId="";
	
	//点赞数 张羽 5/3
	private long giveLike;
	
	
	
	public long getGiveLike() {
		return giveLike;
	}
	public void setGiveLike(long giveLike) {
		this.giveLike = giveLike;
	}
	public int getIsback() {
		return isback;
	}
	public void setIsback(int isback) {
		this.isback = isback;
	}
	public int getIsrecord() {
		return isrecord;
	}
	public void setIsrecord(int isrecord) {
		this.isrecord = isrecord;
	}
	public int getRecordSize() {
		return recordSize;
	}
	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getLiveName() {
		return liveName;
	}
	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getPubName() {
		return pubName;
	}
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	@Enumerated(EnumType.STRING)
	public LiveStatusType getLiveStatusType() {
		return liveStatusType;
	}
	public void setLiveStatusType(LiveStatusType liveStatusType) {
		this.liveStatusType = liveStatusType;
	}
	@Enumerated(EnumType.STRING)
	public LiveType getLiveType() {
		return liveType;
	}
	public void setLiveType(LiveType liveType) {
		this.liveType = liveType;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getLeshiyunId() {
		return leshiyunId;
	}
	public void setLeshiyunId(String leshiyunId) {
		this.leshiyunId = leshiyunId;
	}
	

}
