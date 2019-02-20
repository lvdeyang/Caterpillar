package com.guolaiwan.bussiness.common.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import com.guolaiwan.bussiness.common.enumeration.CodeType;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 代码表
 * lvdeyang 2017年6月21日
 */
@Entity
@Table(name = "t_app_code_list")
public class CodeListPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	//代码名称
	private String name;
	
	//代码
	private String code;
	
	//一级分类
	private CodeType level_1;
	
	//二级分类
	private CodeType level_2;
	
	//三级分类
	private CodeType level_3;
	
	//四级分类
	private CodeType level_4;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Enumerated(EnumType.STRING)
	public CodeType getLevel_1() {
		return level_1;
	}

	public void setLevel_1(CodeType level_1) {
		this.level_1 = level_1;
	}

	@Enumerated(EnumType.STRING)
	public CodeType getLevel_2() {
		return level_2;
	}

	public void setLevel_2(CodeType level_2) {
		this.level_2 = level_2;
	}

	@Enumerated(EnumType.STRING)
	public CodeType getLevel_3() {
		return level_3;
	}

	public void setLevel_3(CodeType level_3) {
		this.level_3 = level_3;
	}

	@Enumerated(EnumType.STRING)
	public CodeType getLevel_4() {
		return level_4;
	}

	public void setLevel_4(CodeType level_4) {
		this.level_4 = level_4;
	}
	
}
