package com.guolaiwan.bussiness.website.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.AddressDAO")
public class AddressDAO extends AbstractBaseDao<AddressPO>{
	/***
	 * 
	 */
	public int countByUser(long userId,int defaultAdd){
		CountHql cHql = this.newCountHql(); 
		cHql.andBy("userId", Condition.eq, userId);
		cHql.andBy("defaultAddress", Condition.eq, defaultAdd);
		int count = this.countByHql(cHql);
		return count;
	}
	
	public AddressPO getByUser(long userId,int defaultAdd){
		QueryHql hql = this.newQueryHql(); 
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("defaultAddress", Condition.eq, defaultAdd);
		List<AddressPO> addresses = this.findByHql(hql);
		if(addresses==null||addresses.size()==0) return null;
		return addresses.get(0);
	}
	
	/**
	 * app专用
	 * @param userId
	 * @param defaultAdd
	 * @return
	 */
	public List<AddressPO> getByUserId(long userId){
		QueryHql hql = this.newQueryHql(); 
		hql.andBy("userId", Condition.eq, userId);
		List<AddressPO> addresses = this.findByHql(hql);
		if(addresses==null||addresses.size()==0) return null;
		return addresses;
	}

	public List<AddressPO> getByUserIdAndDelFlg(long userId,int delFlg){
		QueryHql hql = this.newQueryHql(); 
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("delFlg", Condition.eq, delFlg);
		List<AddressPO> addresses = this.findByHql(hql);
		if(addresses==null||addresses.size()==0) return null;
		return addresses;
	}
	
	public List<AddressPO> getAddressByUserId(long userId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		List<AddressPO> list = findByHql(hql);
		return list;
	}
	
	public List<AddressPO> getAddressByUserIdAndAddressId(long userId , long addressId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, addressId);
		hql.andBy("userId", Condition.eq, userId);
		List<AddressPO> list = findByHql(hql);
		return list;
	}
	
	 
    /**
     * 通过用户身份证 查询地址信息列表信息
     * @param idNum  用户身份证号
     * @return
     * @throws ParseException
     */
	public List<AddressPO>  getAddressIdsByIdNum(String idNum) throws ParseException{
	   QueryHql hql =   this.newQueryHql();
	   hql.andBy("idNum", Condition.eq,idNum);
	   return findByHql(hql);
	}
	
	
}
