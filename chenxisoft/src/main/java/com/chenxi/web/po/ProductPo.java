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
@Table(name = "product")
public class ProductPo extends AbstractBasePO{
	private String pic;
	private String name;
	private double price;
	private String shortContent;
	private String content;
	private ContentSource source;
	private Moudular moudular;
	private String url;
	private long classesId;
	private String classesName;
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getShortContent() {
		return shortContent;
	}
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getClassesId() {
		return classesId;
	}
	public void setClassesId(long classesId) {
		this.classesId = classesId;
	}
	@Transient
	public String getClassesName() {
		return classesName;
	}
	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}
	
	
}
