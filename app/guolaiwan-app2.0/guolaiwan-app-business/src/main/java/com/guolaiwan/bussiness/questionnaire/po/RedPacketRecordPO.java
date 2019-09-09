package com.guolaiwan.bussiness.questionnaire.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_redpacketrecord")
public class RedPacketRecordPO extends AbstractBasePO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6519045759743245415L;
	//绑定的问卷Id
	private long questionnaireId;
	//用户Id
	private long userId;
	
	
	public long getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
