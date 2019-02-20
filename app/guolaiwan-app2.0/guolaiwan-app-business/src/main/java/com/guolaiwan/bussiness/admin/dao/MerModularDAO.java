package com.guolaiwan.bussiness.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 
 * <p>Title: MerModularDAO</p>
 * <p>Description: 商户模块DAO</p>
 * <p>Company: guolaiwan</p> 
 * @author zzchao
 * @date 2018年02月24日 下午1:22:48
 */

@Repository("com.guolaiwan.bussiness.merchant.dao.MerModularDAO")
public class MerModularDAO extends AbstractBaseDao<MerModularPO> {
	
	
}
