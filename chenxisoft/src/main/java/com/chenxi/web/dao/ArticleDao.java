package com.chenxi.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.ArticlePo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
@Repository("ArticleDao")
public class ArticleDao extends AbstractBaseDao<ArticlePo> {


}
