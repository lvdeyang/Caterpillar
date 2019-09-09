package com.chenxi.web.stock.dao;

import org.springframework.stereotype.Repository;

import com.chenxi.web.stock.po.StockPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
@Repository("StockDao")
public class StockDao extends AbstractBaseDao<StockPo> {

}
