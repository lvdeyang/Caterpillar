package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.QuestionModularPO;
import com.guolaiwan.bussiness.admin.po.QuestionSonModularPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class QuestionSonModularVO  extends AbstractBaseVO<QuestionSonModularVO, QuestionSonModularPO> {
	 //分类标识
	private String classCode;
	//绑定模块标识
	private String classmodularCode;

	// 分类名称
	private String className;
	
	  //是否显示
	private int classIsv;
	//排序
	private int classSort;
	
	// 模块图片
	private String modularPic;
	
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassmodularCode() {
		return classmodularCode;
	}

	public void setClassmodularCode(String classmodularCode) {
		this.classmodularCode = classmodularCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getClassIsv() {
		return classIsv;
	}

	public void setClassIsv(int classIsv) {
		this.classIsv = classIsv;
	}

	public int getClassSort() {
		return classSort;
	}

	public void setClassSort(int classSort) {
		this.classSort = classSort;
	}

	public String getModularPic() {
		return modularPic;
	}

	public void setModularPic(String modularPic) {
		this.modularPic = modularPic;
	}

	@Override
	public QuestionSonModularVO set(QuestionSonModularPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId());
		this.setUuid(entity.getUuid());
		this.setUpdateTime(df.format(entity.getUpdateTime()));
		this.setClassCode(entity.getClassCode());
		this.setClassName(entity.getClassName());
		this.setClassIsv(entity.getClassIsv());
		this.setModularPic(entity.getModularPic());
		this.setClassSort(entity.getClassSort());
		this.setClassmodularCode(entity.getClassmodularCode());
		return this;
	}

}
