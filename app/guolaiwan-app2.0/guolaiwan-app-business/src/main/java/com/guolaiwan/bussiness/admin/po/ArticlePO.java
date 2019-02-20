package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_Sys_Article")
public class ArticlePO extends AbstractBasePO{
	//分类名称
	private String className;
    //uuid
	private String classUUid;
	//文章名称
	private String articleName;
    //文章内容
	private String articleCon;
	//排序
	private int articleSort;
    //是否启用
	private int articleEnable;
	//文章日期
    private Date articleDate;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getArticleCon() {
		return articleCon;
	}
	public void setArticleCon(String articleCon) {
		this.articleCon = articleCon;
	}
	public int getArticleSort() {
		return articleSort;
	}
	public void setArticleSort(int articleSort) {
		this.articleSort = articleSort;
	}
	public int getArticleEnable() {
		return articleEnable;
	}
	public void setArticleEnable(int articleEnable) {
		this.articleEnable = articleEnable;
	}
	public Date getArticleDate() {
		return articleDate;
	}
	public void setArticleDate(Date articleDate) {
		this.articleDate = articleDate;
	}
	public String getClassUUid() {
		return classUUid;
	}
	public void setClassUUid(String classUUid) {
		this.classUUid = classUUid;
	}
}
