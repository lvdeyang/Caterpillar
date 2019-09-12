package com.guolaiwan.bussiness.questionnaire.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_questionnaire")
public class QuestionnairePO extends AbstractBasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5183307706502550413L;
	//问卷标题
	private String title;
	//问卷副标题
	private String onthertitle;
	//答题数量
	private int questionnum;
	//答题时限
	private int questiontime;
	//背景图路径
	private String questionnairePic;
	//问卷规则
	private String questionnairerole;
	
	
	public String getOnthertitle() {
		return onthertitle;
	}
	public void setOnthertitle(String onthertitle) {
		this.onthertitle = onthertitle;
	}
	@Lob
	@Column(columnDefinition = "LONGTEXT")
	public String getQuestionnairerole() {
		return questionnairerole;
	}
	public void setQuestionnairerole(String questionnairerole) {
		this.questionnairerole = questionnairerole;
	}
	public int getQuestiontime() {
		return questiontime;
	}
	public void setQuestiontime(int questiontime) {
		this.questiontime = questiontime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuestionnum() {
		return questionnum;
	}
	public void setQuestionnum(int questionnum) {
		this.questionnum = questionnum;
	}
	public String getQuestionnairePic() {
		return questionnairePic;
	}
	public void setQuestionnairePic(String questionnairePic) {
		this.questionnairePic = questionnairePic;
	}
	

}
