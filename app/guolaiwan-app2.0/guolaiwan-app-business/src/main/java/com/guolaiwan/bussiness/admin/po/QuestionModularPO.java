package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_QuestionModularPO")
public class QuestionModularPO extends AbstractBasePO {
   
	   //标识
		private String modularCode;

		// 模块名称
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
	
}
