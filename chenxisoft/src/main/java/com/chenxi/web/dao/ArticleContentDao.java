package com.chenxi.web.dao;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.ArticleContentPo;
import com.chenxi.web.po.ArticlePo;

import pub.caterpillar.orm.dao.AbstractBaseDao;

@Repository("ArticleContentDao")
public class ArticleContentDao extends AbstractBaseDao<ArticleContentPo>{

}
