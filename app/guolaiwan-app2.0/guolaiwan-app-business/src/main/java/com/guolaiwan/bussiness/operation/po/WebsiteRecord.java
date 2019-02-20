package com.guolaiwan.bussiness.operation.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_website_record")
public class WebsiteRecord extends AbstractBasePO {

	private String url;
	
	private String teminal;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTeminal() {
		return teminal;
	}

	public void setTeminal(String teminal) {
		this.teminal = teminal;
	}
	
	
	
	
	
}
