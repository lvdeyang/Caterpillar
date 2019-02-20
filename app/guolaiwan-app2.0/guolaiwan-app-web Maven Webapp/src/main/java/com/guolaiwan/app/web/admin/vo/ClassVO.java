package com.guolaiwan.app.web.admin.vo;

import java.util.List;

import com.guolaiwan.bussiness.admin.po.ArticlePO;
import com.guolaiwan.bussiness.admin.po.ClassPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ClassVO extends AbstractBaseVO<ClassVO,ClassPO>{
	//分类名称
		private String className;
		//排序
	    private int classSort;
	    //分组
	    private long classGroup;
	    

	    
	    private List<ArticlePO> articles;
	    
	    
		
	    public String getClassName() {
			return className;
		}
		public ClassVO setClassName(String className) {
			this.className = className;
			return this;
		}
		public int getClassSort() {
			return classSort;
		}
		public ClassVO setClassSort(int classSort) {
			this.classSort = classSort;
			return this;
		}
	
		public long getClassGroup() {
			return classGroup;
		}
		public ClassVO setClassGroup(long classGroup) {
			this.classGroup = classGroup;
			return this;
		}
		/*public List<ClassPO> getClasszs() {
			return classzs;
		}
		public ClassVO setClasszs(List<ClassPO> classzs) {
			this.classzs = classzs;
			return this;
		}*/
		
		
		@Override
		public ClassVO set(ClassPO entity) throws Exception {
			
			this.setId(entity.getId())
				.setUuid(entity.getUuid())
				.setClassName(entity.getClassName())
				.setClassSort(entity.getClassSort())
				.setClassGroup(entity.getClassGroup());
			return this;
		}
		public List<ArticlePO> getArticles() {
			return articles;
		}
		public void setArticles(List<ArticlePO> articles) {
			this.articles = articles;
		}
		
		
		
	

}
