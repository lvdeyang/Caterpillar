package com.chenxi.web.dao;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.ProductPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;

@Repository("ProductDao")
public class ProductDao extends AbstractBaseDao<ProductPo> {

}
