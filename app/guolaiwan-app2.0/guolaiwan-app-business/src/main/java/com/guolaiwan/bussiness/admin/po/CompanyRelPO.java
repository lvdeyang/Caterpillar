package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.RecommendType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_company_relation")
public class CompanyRelPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	//分公司ID
	private Long sonComId;
	//轮播图，视频，特别推荐id
	private Long relaId;
	//关联的类型
	private RecommendType rType;
	
	
	public Long getSonComId() {
		return sonComId;
	}
	public void setSonComId(Long sonComId) {
		this.sonComId = sonComId;
	}
	public Long getRelaId() {
		return relaId;
	}
	public void setRelaId(Long relaId) {
		this.relaId = relaId;
	}
	@Enumerated(EnumType.STRING)
	public RecommendType getrType() {
		return rType;
	}
	public void setrType(RecommendType rType) {
		this.rType = rType;
	}

}
