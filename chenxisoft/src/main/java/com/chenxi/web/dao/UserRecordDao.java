package com.chenxi.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.UserCollectionPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
@Repository("UserRecordDao")
public class UserRecordDao extends AbstractBaseDao<UserCollectionPo> {


}
