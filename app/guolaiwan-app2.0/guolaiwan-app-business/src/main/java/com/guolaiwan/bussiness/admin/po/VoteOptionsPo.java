package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_voteoptions")
public class VoteOptionsPo extends AbstractBasePO {
	
	private static final long serialVersionUID = 1346849273922287352L;
	
	//投票名称
	private String votename;
	//logo的id
	private long picId;
	//logo的路径
	private String slidepic;
	//评委占的百分比
	private int judgesvote; 
	//销售额等同票数
	private int ordervote; 
	//群众投票等同票数
	private int pepolevote;
	//投票状态
	private String votestatustype;
	//投票数量限制
	private int pollnum;
	//购买限制
	private int ordernum;
	//投票的规则
	private String voterule;
	
	
	
	public String getVoterule() {
		return voterule;
	}
	public void setVoterule(String voterule) {
		this.voterule = voterule;
	}
	public int getPollnum() {
		return pollnum;
	}
	public void setPollnum(int pollnum) {
		this.pollnum = pollnum;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public String getVotestatustype() {
		return votestatustype;
	}
	public void setVotestatustype(String votestatustype) {
		this.votestatustype = votestatustype;
	}
	public String getVotename() {
		return votename;
	}
	public void setVotename(String votename) {
		this.votename = votename;
	}
	public long getPicId() {
		return picId;
	}
	public void setPicId(long picId) {
		this.picId = picId;
	}
	public String getSlidepic() {
		return slidepic;
	}
	public void setSlidepic(String slidepic) {
		this.slidepic = slidepic;
	}
	public int getJudgesvote() {
		return judgesvote;
	}
	public void setJudgesvote(int judgesvote) {
		this.judgesvote = judgesvote;
	}
	public int getOrdervote() {
		return ordervote;
	}
	public void setOrdervote(int ordervote) {
		this.ordervote = ordervote;
	}
	public int getPepolevote() {
		return pepolevote;
	}
	public void setPepolevote(int pepolevote) {
		this.pepolevote = pepolevote;
	} 
	
	
}
