package com.chenxi.web.yueba.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.yueba.admin.po.CommentPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("CommentDao")
public class CommentDao extends AbstractBaseDao<CommentPo> {
	
}
