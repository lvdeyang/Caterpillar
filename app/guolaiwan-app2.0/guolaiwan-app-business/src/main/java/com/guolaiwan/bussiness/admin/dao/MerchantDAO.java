package com.guolaiwan.bussiness.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.dto.MerchantDTO;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 
 * <p>
 * Title: MerchantDAO
 * </p>
 * <p>
 * Description: 商户DAO
 * </p>
 * <p>
 * Company: guolaiwan
 * </p>
 * 
 * @author Mr.Sun
 * @date 2017年11月27日 下午1:22:48
 */

@Repository("com.guolaiwan.bussiness.merchant.dao.MerchantDAO")
public class MerchantDAO extends AbstractBaseDao<MerchantPO> {

	// 统计总条数
	public int getCountByPage() {
		CountHql cHql = this.newCountHql();

		int allcount = this.countByHql(cHql);
		return allcount;
	}

	// 分页查询
	public List<MerchantPO> getListByPageA(String[] names, Object[] values, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (int i = 0; i < names.length; i++) {
			hql.andBy(names[i], Condition.eq, values[i]);
		}
		hql.orderBy("updateTime", true);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		if (merchants == null || merchants.size() <= 0)
			return null;
		return merchants;
	}

	// 通用带参总数
	public int getCountByPageA(String[] names, Object[] values) {
		CountHql cHql = this.newCountHql();
		for (int i = 0; i < names.length; i++) {
			cHql.andBy(names[i], Condition.eq, values[i]);
		}
		int allcount = this.countByHql(cHql);
		return allcount;
	}

	// 后台检索专用
	public List<MerchantPO> findByPageC(Map<String, Object> map, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("shopName") && entry.getValue() != null) {
				hql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else {
				hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		// hql.andBy("shopMpic", Condition.eq, "1");
		// hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.N);
		hql.orderBy("updateTime", true);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		return merchants;
	}

	// 后台检索专用
	public int countByPageC(Map<String, Object> map) {
		CountHql cHql = this.newCountHql();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("shopName") && entry.getValue() != null) {
				cHql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else {
				cHql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		cHql.andBy("shopMpic", Condition.eq, "1");
		cHql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.N);
		int count = countByHql(cHql);
		return count;
	}

	// 审核状态分页查询
	public List<MerchantPO> getFieldListbyPage(ShopAuditStateType type, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("shopAuditState", Condition.eq, type);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		if (merchants == null || merchants.size() <= 0)
			return null;
		return merchants;
	}

	// 通用带参分页,审核状态
	public List<MerchantPO> getListByPageE(Map<String, Object> map, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
		}

		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		if (merchants == null || merchants.size() <= 0)
			return null;
		return merchants;
	}

	// 通用带参
	public int getCountByCity(Map<String, Object> map) {
		CountHql cHql = this.newCountHql();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			cHql.andBy(entry.getKey(), Condition.eq, entry.getValue());
		}
		int allcount = this.countByHql(cHql);
		System.out.println("共有：" + allcount);
		return allcount;
	}

	// 通过用户id获取商户
	public MerchantPO getMerByUser(UserInfoPO user) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("user", Condition.eq, user);

		List<MerchantPO> merchants = findByHql(hql);
		if (merchants == null || merchants.size() <= 0)
			return null;
		return merchants.get(0);
	}

	// 通过模块和类获取商品
	public List<MerchantPO> findByModular(String modularCode, long comId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("modularCode", Condition.eq, modularCode);
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.andBy("comId", Condition.eq, comId);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		if (merchants == null || merchants.size() <= 0)
			return null;
		return merchants;
	}

	// 通过模块和类获取商品
	public List<MerchantPO> findByModular(String modularCode, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("modularCode", Condition.eq, modularCode);
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		if (merchants == null || merchants.size() <= 0)
			return null;
		return merchants;
	}

	// 通用带参分页,审核状态
	public List<MerchantPO> getListByPageB(Map<String, Object> map, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		map.put("shopAuditState", ShopAuditStateType.T);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("shopName")) {
				hql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else {
				hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		if (merchants == null || merchants.size() <= 0)
			return null;
		return merchants;
	}

	// 通用带参分页,审核状态,数量
	public int getListByPageBC(Map<String, Object> map) {
		CountHql cHql = this.newCountHql();
		map.put("shopAuditState", ShopAuditStateType.T);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("shopName")) {
				cHql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else {
				cHql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		int count = this.countByHql(cHql);
		return count;
	}

	/**
	 * 获取指定模块下的商家（App接口调用）
	 * 
	 * @param productModularCode
	 *            模块分类标识
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<MerchantPO> getMerchantByModularCode(String modularCode, int pageNum, int pageSize) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("modularCode", Condition.eq, modularCode);
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);

		return merchants;
	}

	/**
	 * 获取指定模块下的商家（App接口调用）（检索条件）
	 * 
	 * @param productModularCode
	 *            模块分类标识
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<MerchantPO> getMerchantByModularCode(String modularCode, Map<String, Object> Retrievals, int pageNum,
			int pageSize) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.andBy("modularCode", Condition.eq, modularCode);
        hql.orderBy("updateTime", true);
		for (Map.Entry<String, Object> entry : Retrievals.entrySet()) {
			hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
		}

		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		return merchants;
	}
	
	
	public List<MerchantPO> getMerchantByModularCode1(String modularCode, Map<String, Object> Retrievals, int pageNum,
			int pageSize) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.andBy("modularCode1", Condition.eq, modularCode);
		hql.orderBy("updateTime", true);
		for (Map.Entry<String, Object> entry : Retrievals.entrySet()) {
			hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
		}

		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		return merchants;
	}
	
	public List<MerchantPO> getMerchantByModularCode2(String modularCode, Map<String, Object> Retrievals, int pageNum,
			int pageSize) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.andBy("modularCode2", Condition.eq, modularCode);
		hql.orderBy("updateTime", true);
		for (Map.Entry<String, Object> entry : Retrievals.entrySet()) {
			hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
		}

		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		return merchants;
	}
	

	/**
	 * 获取指定模块下的商家个数（App接口调用）
	 * 
	 * @param productModularCode
	 *            模块分类标识
	 * @return count
	 */
	public int countMerchantByModularCode(String modularCode) {

		CountHql cHql = this.newCountHql();
		cHql.andBy("modularCode", Condition.eq, modularCode);
		int count = countByHql(cHql);

		return count;
	}

	/**
	 * 获取指定模块下的商家个数（App接口调用）（检索）
	 * 
	 * @param productModularCode
	 *            模块分类标识
	 * @return count
	 */
	public int countMerchantByModularCode(String modularCode, Map<String, Object> Retrievals) {

		CountHql cHql = this.newCountHql();
		cHql.andBy("modularCode", Condition.eq, modularCode);
		cHql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		for (Map.Entry<String, Object> entry : Retrievals.entrySet()) {
			cHql.andBy(entry.getKey(), Condition.eq, entry.getValue());
		}
		int count = countByHql(cHql);

		return count;
	}

	// 通过上模块找商品找商家
	@SuppressWarnings("unchecked")
	public List<MerchantDTO> getMerchantByPro(Map<String, Object> nmap, int pageNum, int pageSize) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select DISTINCT m.* from t_sys_merchant m , t_sys_modular_product p where m.id = p.productMerchantID");
		for (Map.Entry<String, Object> entry : nmap.entrySet()) {
			if (entry.getKey().equals("shopName")) {
				sqlWrapper.append(" ");
				sqlWrapper.append("and m.shopName like '%");
				sqlWrapper.append(entry.getValue());
				sqlWrapper.append("%'");
			} else {
				sqlWrapper.append(" and p.");
				sqlWrapper.append(entry.getKey());
				sqlWrapper.append("='");
				sqlWrapper.append(entry.getValue());
				sqlWrapper.append("'");
			}
		}
		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("uuid", StandardBasicTypes.STRING).addScalar("shopName", StandardBasicTypes.STRING)
				.addScalar("shopPic", StandardBasicTypes.STRING);
		query.setResultTransformer(Transformers.aliasToBean(MerchantDTO.class));

		// 分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}

	// 通过上模块找商品找商家个数
	public int getMerchantByProC(Map<String, Object> nmap) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select count(1) c from t_sys_merchant m , t_sys_modular_product p where m.id = p.productMerchantID");
		for (Map.Entry<String, Object> entry : nmap.entrySet()) {
			if (entry.getKey().equals("shopName")) {
				sqlWrapper.append(" ");
				sqlWrapper.append("and m.shopName like '%");
				sqlWrapper.append(entry.getValue());
				sqlWrapper.append("%'");
			} else {
				sqlWrapper.append(" and p.");
				sqlWrapper.append(entry.getKey());
				sqlWrapper.append("='");
				sqlWrapper.append(entry.getValue());
				sqlWrapper.append("'");
			}
		}
		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("c",
				StandardBasicTypes.INTEGER);

		if (query.uniqueResult() == null)
			return 0;

		return (int) query.uniqueResult();
	}

	/**
	 * app专用
	 * 
	 * @param comId
	 * @return
	 */
	public List<MerchantPO> appfindByCom(Long comId, String modular, String mclass) {
		QueryHql hql = newQueryHql();
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("modularCode", Condition.eq, modular);
		hql.andBy("modularClassId", Condition.eq, mclass);
		List<MerchantPO> merchants = findByHql(hql);
		if (merchants == null)
			return new ArrayList<MerchantPO>();
		return merchants;
	}

	/**
	 * app专用搜索商家
	 * 
	 * @param comId
	 * @return
	 */
	public List<MerchantPO> appfindByCom(Long comId, String name, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("shopName", Condition.lk, name);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		if (merchants == null)
			return new ArrayList<MerchantPO>();
		return merchants;
	}

	
	/**
	 * app专用搜索商家 新增方法 
	 * 修改了平谷搜索的问题去掉了comId条件
	 * @param comId
	 * @return
	 */
	public List<MerchantPO> appfindByComNew(String name, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.andBy("shopName", Condition.lk, name);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		if (merchants == null)
			return new ArrayList<MerchantPO>();
		return merchants;
	}
	
	
	/**
	 * app专用搜索商家 liu
	 * 
	 * @param comId
	 * @return
	 */
	public List<MerchantPO> appfindBy(String name, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.andBy("shopName", Condition.lk, name);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		if (merchants == null)
			return new ArrayList<MerchantPO>();
		return merchants;
	}

	/**
	 * app专用搜索商家（个数）
	 * 
	 * @param comId
	 * @return
	 */
	public int appCountByCom(Long comId, String name) {
		CountHql cHql = newCountHql();
		cHql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		cHql.andBy("comId", Condition.eq, comId);
		cHql.andBy("shopName", Condition.lk, name);
		int count = countByHql(cHql);
		return count;
	}

	
	/**
	 * app专用搜索商家（个数）
	 * 修改了平谷搜索的功能 去掉了comId条件
	 * @param comId
	 * @return
	 */
	public int appCount(String name) {
		CountHql cHql = newCountHql();
		cHql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		cHql.andBy("shopName", Condition.lk, name);
		int count = countByHql(cHql);
		return count;
	}
	
	
	public List<MerchantPO> getMerchantBySc(String name) {
		QueryHql hql = newQueryHql();
		hql.andBy("shopName", Condition.lk, name);
		List<MerchantPO> list = findByHql(hql);
		return list;
	}

	// 获取商品Id
	public List<MerchantPO> getAllMerchant(String sName) {
		QueryHql hql = newQueryHql();
		hql.andBy("shopName", Condition.lk, sName);
		List<MerchantPO> list = findByHql(hql);
		if (list == null || list.size() == 0)
			return null;
		return list;
	}

	public List<MerchantPO> getMerchantById(long id) {
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<MerchantPO> list = findByHql(hql);
		return list;
	}

	public MerchantPO getMerchantByShopName(String shopName) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("shopName", Condition.eq, shopName);
		List<MerchantPO> list = findByHql(hql);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	// 分类商家推荐检索
	public List<MerchantPO> findMerchant(Map<String, Object> map, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("shopName") && entry.getValue() != null) {
				hql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else {
				hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		// hql.andBy("shopMpic", Condition.eq, "1");
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.orderBy("updateTime", true);
		List<MerchantPO> merchants = findByHqlPage(hql, pageNum, pageSize);
		return merchants;
	}

	// 分类商家推荐检索
	public int countMerchant(Map<String, Object> map) {
		CountHql cHql = this.newCountHql();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("shopName") && entry.getValue() != null) {
				cHql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else {
				cHql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		cHql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		int count = countByHql(cHql);
		return count;
	}

	// 获取某个公司下的所有商户
	public List<MerchantPO> findMerByCom(long comId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		if (comId != 1l) {
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.andBy("shopAuditState", Condition.eq, ShopAuditStateType.T);
		hql.orderBy("updateTime", true);
		List<MerchantPO> merchantPOs = findByHqlPage(hql, pageNum, pageSize);
		if (merchantPOs == null || merchantPOs.size() <= 0)
			return null;
		return merchantPOs;
	}

	/**
	 * 导出
	 * 
	 * @param map
	 * @return
	 */
	public List<MerchantPO> findAllByMapParams(Map<String, Object> map) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("shopName") && entry.getValue() != null) {
				hql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else {
				hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		hql.orderBy("updateTime", true);
		List<MerchantPO> merchants = findByHql(hql);
		return merchants;
	}
}
