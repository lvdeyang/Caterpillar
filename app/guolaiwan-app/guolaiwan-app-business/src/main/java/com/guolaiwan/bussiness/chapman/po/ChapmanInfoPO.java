package com.guolaiwan.bussiness.chapman.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 附加信息描述映射
 * lvdeyang 2017年6月22日
 */
@Entity
@Table(name = "t_app_chapman_info")
public class ChapmanInfoPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	//描述的建
	private String infoKey;
	
	//描述的值
	private String infoValue;
	
	//一级分类
	private String level_1;
	
	//二级分类
	private String level_2;
	
	//三级分类
	private String level_3;
	
	//描述对象
	private Long targetId;
	
	//描述对象类型
	private String targetType;

	@Column(name = "info_key")
	public String getInfoKey() {
		return infoKey;
	}

	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
	}

	@Column(name = "info_value")
	public String getInfoValue() {
		return infoValue;
	}

	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}

	public String getLevel_1() {
		return level_1;
	}

	public void setLevel_1(String level_1) {
		this.level_1 = level_1;
	}

	public String getLevel_2() {
		return level_2;
	}

	public void setLevel_2(String level_2) {
		this.level_2 = level_2;
	}

	public String getLevel_3() {
		return level_3;
	}

	public void setLevel_3(String level_3) {
		this.level_3 = level_3;
	}

	@Column(name = "target_id")
	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	@Column(name = "target_type")
	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	
}
