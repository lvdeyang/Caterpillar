package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


import com.guolaiwan.bussiness.admin.po.QuestionModularPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class QuestionModularVO extends AbstractBaseVO<QuestionModularVO, QuestionModularPO> {
	
		//标识
		private String modularCode;

		//模块名称
		private String modularName;

		//是否显示
		private int modularIsv;
		
		// 排序
		private long sort;

		public String getModularCode() {
			return modularCode;
		}

		public void setModularCode(String modularCode) {
			this.modularCode = modularCode;
		}

		public String getModularName() {
			return modularName;
		}

		public void setModularName(String modularName) {
			this.modularName = modularName;
		}

		public int getModularIsv() {
			return modularIsv;
		}

		public void setModularIsv(int modularIsv) {
			this.modularIsv = modularIsv;
		}

		public long getSort() {
			return sort;
		}

		public void setSort(long sort) {
			this.sort = sort;
		}

		@Override
		public QuestionModularVO set(QuestionModularPO entity) throws Exception {
			// TODO Auto-generated method stub
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setId(entity.getId());
			this.setUuid(entity.getUuid());
			this.setUpdateTime(df.format(entity.getUpdateTime()));
			this.setModularCode(entity.getModularCode());
			this.setModularName(entity.getModularName());
			this.setModularIsv(entity.getModularIsv());
			this.setSort(entity.getSort());
			return this;
		}
}
