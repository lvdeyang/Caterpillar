package com.chenxi.web.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chenxi.web.classes.WorkerStatus;
import com.chenxi.web.classes.ContentSource;
import com.chenxi.web.classes.Moudular;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "article")
public class ArticlePo extends AbstractBasePO {
	private String pic;
	private String title;
	private String content;
	private ContentSource source;
	private Moudular moudular;
	private long classesId;
	private String classesName;
	private String autor;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Enumerated(EnumType.STRING)
	public ContentSource getSource() {
		return source;
	}

	public void setSource(ContentSource source) {
		this.source = source;
	}
	@Enumerated(EnumType.STRING)
	public Moudular getMoudular() {
		return moudular;
	}

	public void setMoudular(Moudular moudular) {
		this.moudular = moudular;
	}

	public long getClassesId() {
		return classesId;
	}

	public void setClassesId(long classesId) {
		this.classesId = classesId;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Transient
	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	

}
