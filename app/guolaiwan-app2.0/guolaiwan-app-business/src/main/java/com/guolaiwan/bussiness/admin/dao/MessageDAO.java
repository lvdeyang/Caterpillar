package com.guolaiwan.bussiness.admin.dao;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.MessagePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;

@Repository("com.guolaiwan.bussiness.admin.dao.MessageDAO")
public class MessageDAO extends AbstractBaseDao<MessagePO> {

}
