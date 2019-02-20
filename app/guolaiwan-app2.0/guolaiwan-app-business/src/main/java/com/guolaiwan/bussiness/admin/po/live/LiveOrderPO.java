package com.guolaiwan.bussiness.admin.po.live;

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

import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_liveOrder")
public class LiveOrderPO extends AbstractBasePO {
	
	private long totalFee;
	private long liveId;
	private Date startTime;
	private Date endTime;
	private List<SubLivePO> subLivePOs;
	private int count;
	private int recordSize;
	private OrderStateType status;
	// 是否垫播 1表示垫播,0表示不垫播
	private int isMatPlay;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getLiveId() {
		return liveId;
	}

	public void setLiveId(long liveId) {
		this.liveId = liveId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public int getIsMatPlay() {
		return isMatPlay;
	}

	public void setIsMatPlay(int isMatPlay) {
		this.isMatPlay = isMatPlay;
	}

	@OneToMany(mappedBy = "liveOrderPO", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	public List<SubLivePO> getSubLivePOs() {
		return subLivePOs;
	}
	public void setSubLivePOs(List<SubLivePO> subLivePOs) {
		this.subLivePOs = subLivePOs;
	}
    @Enumerated(EnumType.STRING)
	public OrderStateType getStatus() {
		return status;
	}

	public void setStatus(OrderStateType status) {
		this.status = status;
	}
}
