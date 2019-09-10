package com.chenxi.web.stock.dao;

import org.springframework.stereotype.Repository;

import com.chenxi.web.stock.po.FinancePo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
@Repository("FinanceDao")
public class FinanceDao extends AbstractBaseDao<FinancePo>{

}
