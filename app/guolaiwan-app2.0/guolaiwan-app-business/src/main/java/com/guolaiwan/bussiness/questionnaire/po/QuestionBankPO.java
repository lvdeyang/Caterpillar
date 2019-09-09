package com.guolaiwan.bussiness.questionnaire.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_questionbank")
public class QuestionBankPO extends AbstractBasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 902075117523739097L;
	//题目
	private String topic;
	//问题类型
	private String questiontype;
	//选项
	private String options;
	//答案
	private String answer;
	//绑定的问卷Id
	private long questionnaireId;
	
	
	public long getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getQuestiontype() {
		return questiontype;
	}
	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
