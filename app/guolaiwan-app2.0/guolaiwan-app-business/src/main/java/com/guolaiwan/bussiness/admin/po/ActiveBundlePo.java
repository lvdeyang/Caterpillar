package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.ActivityBundleType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_activitybundle")
public class ActiveBundlePo extends AbstractBasePO {
    private String title;
    private String pic;
    //分公司id
    private int  comId;
    private ActivityBundleType type;
	
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Enumerated(EnumType.STRING)
	public ActivityBundleType getType() {
		return type;
	}
	public void setType(ActivityBundleType type) {
		this.type = type;
	}
	
    
}
