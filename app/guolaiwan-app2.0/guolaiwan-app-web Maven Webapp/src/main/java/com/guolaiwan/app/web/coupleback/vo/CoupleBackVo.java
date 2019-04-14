package com.guolaiwan.app.web.coupleback.vo;

import com.guolaiwan.app.web.smartParking.vo.CarPositionVo;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.coupleback.po.CoupleBackPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class CoupleBackVo  extends AbstractBaseVO<CoupleBackVo, CoupleBackPO>{

	// 用户id
	private	long userId;
	// 分公司id
	private int  filialeId;
	// 用户头像
	private String headportrait;
	// 用户名称
	private String username;
	// 反馈日期
	private String date;
	// 反馈内容
	private String content;
	//回复内容
	private String replycontent;
	//状态   0未回复  1已回复
	private int state;




	@Override
	public CoupleBackVo set(CoupleBackPO entity) throws Exception {
	this.setUserId(entity.getUserId())
	.setFilialeId(entity.getFilialeId())
	.setHeadportrait(entity.getHeadportrait())
	.setUsername(entity.getUsername())
	.setDate(entity.getDate())
	.setContent(entity.getContent())
	.setReplycontent(entity.getReplycontent())
	.setState(entity.getState())
	.setId(entity.getId());
	return this;
	}
	
	
	


	public long getUserId() {
		return userId;
	}
	public CoupleBackVo setUserId(long userId) {
		this.userId = userId;
		return this;
	}
	public int getFilialeId() {
		return filialeId;
	}
	public CoupleBackVo setFilialeId(int filialeId) {
		this.filialeId = filialeId;
		return this;
	}
	public String getHeadportrait() {
		return headportrait;
	}
	public CoupleBackVo setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public CoupleBackVo setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getDate() {
		return date;
	}
	public CoupleBackVo setDate(String date) {
		this.date = date;
		return this;
	}
	public String getContent() {
		return content;
	}
	public CoupleBackVo setContent(String content) {
		this.content = content;
		return this;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public CoupleBackVo setReplycontent(String replycontent) {
		this.replycontent = replycontent;
		return this;
	}
	public int getState() {
		return state;
	}
	public CoupleBackVo setState(int state) {
		this.state = state;
		return this;
	}



}
