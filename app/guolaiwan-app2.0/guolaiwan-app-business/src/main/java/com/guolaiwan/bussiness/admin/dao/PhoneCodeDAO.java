package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.PhoneCodePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.merchant.dao.PhoneCodeDAO")
public class PhoneCodeDAO extends AbstractBaseDao<PhoneCodePO> {
	/**
	 * 注册
	 * @param phone
	 * @return
	 */
	public PhoneCodePO getByPhoneReg(String phone){
		QueryHql hql = this.newQueryHql();
		hql.andBy("type", Condition.eq, 1);
		hql.andBy("source", Condition.eq, 1);
		hql.andBy("phoneNum", Condition.eq, phone);
		List<PhoneCodePO> phoneCodes = findByHql(hql);
		if(phoneCodes==null||phoneCodes.size()==0) return null;
		return phoneCodes.get(0);
	}
	/**
	 * 重置密码
	 * @param phone
	 * @return
	 */
	public PhoneCodePO getByPhoneRep(String phone){
		QueryHql hql = this.newQueryHql();
		hql.andBy("type", Condition.eq, 2);
		hql.andBy("source", Condition.eq, 1);
		hql.andBy("phoneNum", Condition.eq, phone);
		List<PhoneCodePO> phoneCodes = findByHql(hql);
		if(phoneCodes==null||phoneCodes.size()==0) return null;
		return phoneCodes.get(0);
	}
	
}
