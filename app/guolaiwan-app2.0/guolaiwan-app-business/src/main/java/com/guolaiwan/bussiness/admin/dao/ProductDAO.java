package com.guolaiwan.bussiness.admin.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.ProductSaleType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * Don't try so hard!
 * <p>
 * Title: MenuDAO
 * </p>
 * <p>
 * Description: 鏉冮檺DAO
 * </p>
 * <p>
 * Company: guolaiwan
 * </p>
 * 
 * @author zichao
 * @date 2017骞�11鏈�20鏃� 涓婂崍9:11:36
 */
@Repository("com.guolaiwan.bussiness.admin.dao.ProductDAO")
public class ProductDAO extends AbstractBaseDao<ProductPO> {

	// ###count鏈畬鎴�
	/**
	 * 鑾峰彇鎵�鏈夐椤垫帹鑽愬晢鍝侊紙App鎺ュ彛璋冪敤锛�
	 * 
	 * @param count
	 *            鎺ㄨ崘鏁伴噺
	 * @return 棣栭〉鎺ㄨ崘鐨勫晢鍝�
	 */
	public List<ProductPO> getRecommendProducts(int count, long comId) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("productIndexRecommend", Condition.eq, 1);
		hql.andBy("comId", Condition.eq, comId);
		List<ProductPO> products = findByHqlAndCount(hql, count);
		if (products == null || products.size() <= 0)
			return null;

		return products;
	}

	/**
	 * 鑾峰彇鏌愬垎绫讳笅鐨勫晢鍝侊紙App鎺ュ彛璋冪敤锛�
	 * 
	 * @param productModularCode
	 *            妯″潡鍒嗙被鏍囪瘑
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> getProductByModularCode(String productModularCode, int pageNum, int pageSize) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("productModularCode", Condition.eq, productModularCode);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);

		return products;
	}

	public List<ProductPO> getProductsByMerAndModar(Long productMerchantID, String modarCode) {
		QueryHql hql = this.newQueryHql();

		hql.andBy("productMerchantID", Condition.eq, productMerchantID);
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		hql.andBy("productModularCode", Condition.eq, modarCode);
		List<ProductPO> products = findByHql(hql);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	/***
	 * 鑾峰彇鏌愬晢瀹朵笅鐨勫晢鍝侊紙App鎺ュ彛璋冪敤锛夊鏍搁�氳繃
	 * 
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> getProductsByMer(Long productMerchantID, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productMerchantID", Condition.eq, productMerchantID);
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		hql.andBy("integralGoods", Condition.eq, 0);
		hql.orderBy("productSort", true);	
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	/***
	 * 鑾峰彇鏌愬晢瀹朵笅鐨勫晢鍝佷釜鏁帮紙App鎺ュ彛璋冪敤锛夊鏍搁�氳繃
	 * 
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int countProductsByMer(Long productMerchantID) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("productMerchantID", Condition.eq, productMerchantID);
		cHql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		int count = countByHql(cHql);
		return count;
	}

	public int countByMerchantId(Long merchantId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("productMerchantID", Condition.eq, merchantId);
		return countByHql(cHql);
	}

	// 鑾峰彇鎵�鏈夌Н鍒嗗晢鍝�
	public List<ProductPO> getIntegralGoods() {
		QueryHql hql = this.newQueryHql();
		hql.andBy("integralGoods", Condition.eq, 1);
		return findByHql(hql);
	}
	
	public List<ProductPO> getPageIntegralGoods(long comId,int pageSize,int pageNum) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("integralGoods", Condition.eq, 1);
		hql.andBy("comId",Condition.eq,comId);
		return findByHql(hql, pageNum, pageSize);
	}
	
	public int countIntegralGoods(long comId) {
		CountHql hql = this.newCountHql();
		hql.andBy("integralGoods", Condition.eq, 1);
		hql.andBy("comId",Condition.eq,comId);
		return countByHql(hql);
	}
	

	/**
	 * 鑾峰彇鍟嗗鐨勬渶灏忎环閽�
	 * 
	 */
	public long getMinPriceByMer(long merId) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select min(productPrice) minPrice from t_sys_modular_product where productMerchantID = " + merId);
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("minPrice",
				StandardBasicTypes.LONG);
		if (query.uniqueResult() == null)
			return 0l;
		long minPrice = (long) query.uniqueResult();
		return minPrice;
	}

	// 棣栭〉鎼滅储
	public List<ProductPO> productSearch(String pcName, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productName", Condition.lk, pcName);
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	// 棣栭〉鎼滅储锛坈ount锛�
	public List<ProductPO> findByNP(String pcName, String pcPriceMin, String pcPriceMax, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		if (pcName != null && pcName.length() > 0) {
			hql.andBy("productName", Condition.lk, pcName);
		}
		if (pcPriceMin != null && pcPriceMin.length() > 0) {
			int priceMin = Integer.parseInt(pcPriceMin);
			hql.andBy("productName", Condition.ge, priceMin);
		}
		if (pcPriceMax != null && pcPriceMax.length() > 0) {
			int priceMax = Integer.parseInt(pcPriceMax);
			hql.andBy("productName", Condition.le, priceMax);
		}

		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}

	// 鍓嶅彴鎼滅储
	public List<ProductPO> getListByPageB(Map<String, Object> map, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		map.put("productIsShow", 1);
		map.put("productIndexRecommend", 1);
		map.put("productAuditstatus", ShopAuditStateType.T);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("productName")) {
				hql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else if (entry.getKey().equals("minProductPrice")) {
				hql.andBy("productPrice", Condition.ge, entry.getValue());
			} else if (entry.getKey().equals("maxProductPrice")) {
				hql.andBy("productPrice", Condition.le, entry.getValue());
			} else {
				hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	// 鍓嶅彴鎼滅储锛坈ount锛�
	public int getListByPageBC(Map<String, Object> map) {
		CountHql cHql = this.newCountHql();
		map.put("productIsShow", 1);
		map.put("productIndexRecommend", 1);
		map.put("productAuditstatus", ShopAuditStateType.T);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("productName")) {
				cHql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else if (entry.getKey().equals("minProductPrice")) {
				cHql.andBy("productPrice", Condition.ge, entry.getValue());
			} else if (entry.getKey().equals("maxProductPrice")) {
				cHql.andBy("productPrice", Condition.le, entry.getValue());
			} else {
				cHql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		int count = this.countByHql(cHql);
		return count;
	}

	// 鑾峰彇鐑棬浜у搧
	public List<ProductPO> getListHot(Long comId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productIsShow", Condition.eq, 1);
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("productIndexRecommend", Condition.eq, 1);
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		hql.orderBy("productSaleNum", true);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	// 閫氳繃妯″潡鍜岀被鑾峰彇鍟嗗搧
	public List<ProductPO> findByModular(String modularCode, long comId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("productIsShow", Condition.eq, 1);
		hql.andBy("productIndexRecommend", Condition.eq, 1);
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		hql.andBy("productModularCode", Condition.eq, modularCode);
		// hql.orderBy("productSaleNum", true);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	// 鑾峰彇浜у搧涓暟
	public int countProduct(String field, Object value) {
		CountHql cHql = this.newCountHql();
		cHql.andBy(field, Condition.eq, value);
		cHql.andBy("productIsShow", Condition.eq, 1);
		cHql.andBy("productIndexRecommend", Condition.eq, 1);
		cHql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		int count = this.countByHql(cHql);
		return count;
	}
	////////////////////////////////////////// 鍚庡彴///////////////////////////////

	public List<ProductPO> findByMerchant(long merchantId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productMerchantID", Condition.eq, merchantId);

		hql.orderBy("updateTime", true);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	// 鑾峰彇鏌愪釜鍏徃涓嬬殑鎵�鏈変骇鍝�
	public List<ProductPO> findByCom(long comId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		if (comId != 1l) {
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.orderBy("updateTime", true);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	// 鑾峰彇鏌愪釜鍏徃涓嬮潰鐨勫鏍搁�氳繃鐨勪骇鍝�
	public List<ProductPO> findByComT(long comId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		if (comId != 1l) {
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		hql.orderBy("updateTime", true);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	// 鑾峰彇鏌愪釜鍏徃涓嬮潰鐨勫鏍告湭閫氳繃鐨勪骇鍝�
	public List<ProductPO> findByComD(long comId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		if (comId != 1l) {
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.D);
		hql.orderBy("updateTime", true);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	// 鑾峰彇鏌愪釜鍏徃涓嬬殑鎵�鏈変骇鍝佹暟閲�
	public int countByCom(long comId) {
		CountHql chql = this.newCountHql();
		if (comId != 1l) {
			chql.andBy("comId", Condition.eq, comId);
		}
		int count = countByHql(chql);
		return count;
	}

	// 鑾峰彇鏌愪釜鍏徃涓嬬殑鎵�鏈夐�氳繃浜у搧鏁伴噺
	public int countByComT(long comId) {
		CountHql chql = this.newCountHql();
		if (comId != 1l) {
			chql.andBy("comId", Condition.eq, comId);
		}
		chql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		int count = countByHql(chql);
		return count;
	}

	// 鑾峰彇鏌愪釜鍏徃涓嬬殑鎵�鏈夋湭閫氳繃浜у搧鏁伴噺
	public int countByComD(long comId) {
		CountHql chql = this.newCountHql();
		if (comId != 1l) {
			chql.andBy("comId", Condition.eq, comId);
		}
		chql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.D);
		int count = countByHql(chql);
		return count;
	}

	// 鍚庡彴鏌ヨ
	public List<ProductPO> getListByPageE(Map<String, Object> map, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("productName") || entry.getKey().equals("productMerchantName")) {
				hql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else if (entry.getKey().equals("comId")) {
				// if((long)entry.getValue()!=1l){
				hql.andBy("comId", Condition.eq, entry.getValue());
				// }
			} else {
				hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		hql.orderBy("updateTime", true);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	// 鍚庡彴鏌ヨ锛坈ount锛�
	public int getCountByPageE(Map<String, Object> map) {
		CountHql cHql = this.newCountHql();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equals("productName") || entry.getKey().equals("productMerchantName")) {
				cHql.andBy(entry.getKey(), Condition.lk, entry.getValue());
			} else if (entry.getKey().equals("comId")) {
				// if((long)entry.getValue()!=1l){
				cHql.andBy("comId", Condition.eq, entry.getValue());
				// }
			} else {
				cHql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}

		}
		int allcount = this.countByHql(cHql);
		return allcount;
	}

	/**
	 * 鑾峰彇绉垎鍟嗗煄鍟嗗搧
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> findByPsTypeCom(long comId, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("psType", Condition.eq, ProductSaleType.GOLD);
		hql.andBy("comId", Condition.eq, comId);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}

	/**
	 * 鑾峰彇绉垎鍟嗗煄鍟嗗搧(涓暟)
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int countByPsTypeCom(Long comId) {
		CountHql cHql = newCountHql();
		cHql.andBy("psType", Condition.eq, ProductSaleType.GOLD);
		cHql.andBy("comId", Condition.eq, comId);
		int count = countByHql(cHql);
		return count;
	}

	/**
	 * app鎼滅储鍟嗗搧
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> appfindByCom(Long comId, String name, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T); // 瀹℃牳閫氳繃
		hql.andBy("productIsShow", Condition.eq, 1); // 浜у搧灞曠ず
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("productName", Condition.lk, name);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}

	/**
	 * app鎼滅储鍟嗗搧 淇敼浜嗗钩璋锋悳绱㈠姛鑳� 鍘绘帀浜哻omId
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> appfindByComNew(String name, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T); // 瀹℃牳閫氳繃
		hql.andBy("productIsShow", Condition.eq, 1); // 浜у搧灞曠ず
		hql.andBy("integralGoods", Condition.eq, 0); 
		hql.andBy("productName", Condition.lk, name);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}

	/**
	 * app鎼滄彁绀�
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> appPrompt(String name, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T); // 瀹℃牳閫氳繃
		hql.andBy("productIsShow", Condition.eq, 1); // 浜у搧灞曠ず
		hql.andBy("productName", Condition.lk, name);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}

	/**
	 * app鎼滅储鍟嗗搧锛堜釜鏁帮級
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int appCountByCom(Long comId, String name) {
		CountHql cHql = newCountHql();
		cHql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T); // 瀹℃牳閫氳繃
		cHql.andBy("productIsShow", Condition.eq, 1); // 浜у搧灞曠ず
		cHql.andBy("comId", Condition.eq, comId);
		cHql.andBy("productName", Condition.lk, name);
		int count = countByHql(cHql);
		return count;
	}

	/**
	 * app鎼滅储鍟嗗搧锛堜釜鏁帮級 骞宠胺鎼滅储鍔熻兘淇敼 鍘绘帀浜� Long comId
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int appCountByComNew(String name) {
		CountHql cHql = newCountHql();
		cHql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T); // 瀹℃牳閫氳繃
		cHql.andBy("productIsShow", Condition.eq, 1); // 浜у搧灞曠ず
		cHql.andBy("integralGoods", Condition.eq, 0); // 浜у搧灞曠ず
		cHql.andBy("productName", Condition.lk, name);
		int count = countByHql(cHql);
		return count;
	}

	/**
	 * app鍟嗗Id鑾峰彇yige鍟嗗搧
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ProductPO getByMerchantId(Long merchantId) {
		QueryHql hql = newQueryHql();
		hql.andBy("productMerchantID", Condition.eq, merchantId);
		hql.orderBy("id", false);
		List<ProductPO> products = findByHql(hql);
		if (products == null || products.size() == 0)
			return null;
		return products.get(0);
	}

	public List<ProductPO> findByMerchantId(Long merchantId) {
		QueryHql hql = newQueryHql();
		hql.andBy("productMerchantID", Condition.eq, merchantId);
		hql.andBy("productIsShow", Condition.eq, 1);	
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		hql.orderBy("productSort", true);
		List<ProductPO> products = findByHql(hql);
		return products;
	}

	/**
	 * app鑾峰彇鏌愪釜绉垎鍟嗗搧
	 * 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ProductPO getByGold(Long productId) {
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, productId);
		hql.andBy("psType", Condition.eq, ProductSaleType.GOLD);
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		List<ProductPO> products = findByHql(hql);
		if (products == null || products.size() == 0)
			return null;
		return products.get(0);
	}

	public List<ProductPO> getProductByProId(long id) {
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<ProductPO> list = findByHql(hql);
		return list;
	}

	// 鑾峰彇鍟嗗搧Id
	public List<ProductPO> getAllSettle(long id) {
		QueryHql hql = newQueryHql();
		hql.andBy("productMerchantID", Condition.eq, id);
		List<ProductPO> list = findByHql(hql);
		if (list == null || list.size() == 0)
			return null;
		return list;
	}

	public List<ProductPO> getMoHu(String name, int page, int limit) {
		QueryHql hql = newQueryHql();
		hql.andBy("productName", Condition.lk, name);
		List<ProductPO> list = findByHqlPage(hql, page, limit);
		return list;
	}

	public Long getMaxId() {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select max(a.id) maxid from t_sys_modular_product a");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("maxid",
				StandardBasicTypes.LONG);
		if (query.uniqueResult() == null)
			return 0l;
		long maxId = (long) query.uniqueResult();
		return maxId;
	}

	// 鏌ヨ鍒板簵鏀粯 id
	public ProductPO searchOrder(long merchantId, String name) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productMerchantID", Condition.eq, merchantId);
		hql.andBy("productName", Condition.eq, name);
		List<ProductPO> orders = findByHql(hql);
		if (orders == null || orders.size() == 0)
			return null;
		return orders.get(0);
	}

	public List<ProductPO> getactivity(long merchantId) {

		QueryHql hql = this.newQueryHql();

		hql.andBy("productMerchantID", Condition.eq, merchantId);
        hql.andBy("productAuditstatus",Condition.eq, ShopAuditStateType.T);
		List<ProductPO> products = findByHql(hql);
		return products;
	}
	
	// 后台检索专用
	public List<ProductPO> findByPageC(Map<String, Object> map, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			
				hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			
		}
		
		List<ProductPO> productPos = findByHqlPage(hql, pageNum, pageSize);
		return productPos;
	}
	
	/**
	 * 按照classcode查找相关的商品
	 * @param productClassCode
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> findByProductClassCode(String productClassCode, int pageNum, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("productClassCode", Condition.eq, productClassCode);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}
	
	
	/**
	 * 获取所有拼团的商品
	 * @return
	 */
	public List<ProductPO> getGroupProduct() {
		QueryHql hql = newQueryHql();
		hql.andBy("productIsShow", Condition.eq, 1);
		hql.andBy("isgroup", Condition.eq, 1);
		List<ProductPO> products = findByHql(hql);
		return products;
	}

}
