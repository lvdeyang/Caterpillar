package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_judgesvotemsg")
public class JudgesVoteMsgPO extends AbstractBasePO {
	
	private static final long serialVersionUID = -3031053404883668296L;
	private long userId; // 名称
	private String username;
	private long optionId;// 投票活动Id
	private long productId;// 商品Id
	private long voteproductId;// 投票商品Id
	private long score;//分数
	
	public long getVoteproductId() {
		return voteproductId;
	}

	public void setVoteproductId(long voteproductId) {
		this.voteproductId = voteproductId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getOptionId() {
		return optionId;
	}

	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
