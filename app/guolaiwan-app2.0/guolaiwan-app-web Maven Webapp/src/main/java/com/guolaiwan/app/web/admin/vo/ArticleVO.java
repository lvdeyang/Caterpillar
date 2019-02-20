package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.guolaiwan.bussiness.admin.po.ArticlePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ArticleVO extends AbstractBaseVO<ArticleVO,ArticlePO>{
	
	//分类名称
		private String className;
		//分类uuid
	    private String classUUid;
		//文章名称
		private String articleName;
	    //文章内容
		private String articleCon;
		//排序
		private int articleSort;
	    //是否启用
		private int articleEnable;
		//文章日期
	    private String articleDate;
	    private List<ArticlePO> articles;
		
	    public String getClassName() {
			return className;
		}
		public ArticleVO setClassName(String className) {
			this.className = className;
			return this;
		}
		public String getClassUUid() {
			return classUUid;
		}
		public ArticleVO setClassUUid(String classUUid) {
			this.classUUid = classUUid;
			return this;
		}
		public String getArticleName() {
			return articleName;
		}
		public ArticleVO setArticleName(String articleName) {
			this.articleName = articleName;
			return this;
		}
		public String getArticleCon() {
			return articleCon;
		}
		public ArticleVO setArticleCon(String articleCon) {
			this.articleCon = articleCon;
			return this;
		}
		public int getArticleSort() {
			return articleSort;
		}
		public ArticleVO setArticleSort(int articleSort) {
			this.articleSort = articleSort;
			return this;
		}
		public int getArticleEnable() {
			return articleEnable;
		}
		public ArticleVO setArticleEnable(int articleEnable) {
			this.articleEnable = articleEnable;
			return this;
		}
		public String getArticleDate() {
			return articleDate;
		}
		public ArticleVO setArticleDate(String articleDate) {
			this.articleDate = articleDate;
            return this;		
		}
		public List<ArticlePO> getArticles() {
			return articles;
		}
		public ArticleVO setArticles(List<ArticlePO> articles) {
			this.articles = articles;
			return this;
		}
		@Override
		public ArticleVO set(ArticlePO entity)throws Exception{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		    .setUuid(entity.getUuid())
		    .setClassName(entity.getClassName())
		    .setClassUUid(entity.getClassUUid())
		    .setArticleName(entity.getArticleName())
		    .setArticleCon(entity.getArticleCon())
		    .setArticleSort(entity.getArticleSort())
		    .setArticleEnable(entity.getArticleEnable())
		    .setArticleDate(df.format(entity.getArticleDate()));
		return this;
		    
		
		}
	
}
