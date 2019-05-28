package com.chenxi.web.dao;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.MemberPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;

@Repository("MemberDao")
public class MemberDao extends AbstractBaseDao<MemberPo> {

}
