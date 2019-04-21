package com.guolaiwan.bussiness.admin.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.ProductSaleType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.sun.jna.platform.win32.WinDef.LONG;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * Don't try so hard!
 * <p>Title: MenuDAO</p>
 * <p>Description: 权限DAO</p>
 * <p>Company: guolaiwan</p> 
 * @author zichao
 * @date 2017年11月20日 上午9:11:36
 */
@Repository("com.guolaiwan.bussiness.admin.dao.ProductDAO")
public class ProductDAO extends AbstractBaseDao<ProductPO> {








	//  ###count未完成
	/**
	 * 获取所有首页推荐商品（App接口调用）
	 * @param count 推荐数量
	 * @return 首页推荐的商品
	 */
	public List<ProductPO> getRecommendProducts(int count,long comId ) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("productIndexRecommend", Condition.eq, 1);
		hql.andBy("comId", Condition.eq, comId);
		List<ProductPO> products = findByHqlAndCount(hql, count);
		if(products==null || products.size()<=0) return null;

		return products;
	}

	/**
	 * 获取某分类下的商品（App接口调用）
	 * @param productModularCode 模块分类标识
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> getProductByModularCode(
			String productModularCode, 
			int pageNum, 
			int pageSize) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("productModularCode", Condition.eq, productModularCode);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);

		return products;
	}


	public List<ProductPO> getProductsByMerAndModar(
			Long productMerchantID,
			String modarCode) {
		QueryHql hql = this.newQueryHql();

		hql.andBy("productMerchantID",  Condition.eq, productMerchantID);
		hql.andBy("productAuditstatus",  Condition.eq, ShopAuditStateType.T);
		hql.andBy("productModularCode", Condition.eq, modarCode);
		List<ProductPO> products =findByHql(hql);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	
	/***
	 * 获取某商家下的商品（App接口调用）审核通过
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> getProductsByMer(
			Long productMerchantID
			,int pageNum
			,int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.andBy("productMerchantID",  Condition.eq, productMerchantID);
		hql.andBy("productAuditstatus",  Condition.eq, ShopAuditStateType.T);

		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	
	/***
	 * 获取某商家下的商品个数（App接口调用）审核通过
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int countProductsByMer(
			Long productMerchantID) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("productMerchantID",  Condition.eq, productMerchantID);
		cHql.andBy("productAuditstatus",  Condition.eq, ShopAuditStateType.T);
		int count =countByHql(cHql);
		return count;
	}
	
	public int countByMerchantId(Long merchantId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("productMerchantID", Condition.eq, merchantId);
		return countByHql(cHql);
	}
	
	/**
	 * 获取商家的最小价钱
	 * 
	 */
	public long getMinPriceByMer(long merId){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select min(productPrice) minPrice from t_sys_modular_product where productMerchantID = "+merId);
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("minPrice",StandardBasicTypes.LONG);
		if(query.uniqueResult()==null) return 0l;
		long minPrice = (long)query.uniqueResult();
		return minPrice;
	}

	// 首页搜索
	public List<ProductPO> productSearch(String pcName, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productName",  Condition.lk, pcName);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	// 首页搜索（count）
	public List<ProductPO> findByNP(String pcName,String pcPriceMin,String pcPriceMax,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		if(pcName!=null&&pcName.length()>0){
			hql.andBy("productName", Condition.lk, pcName);	
		}
		if(pcPriceMin!=null&&pcPriceMin.length()>0){
			int priceMin = Integer.parseInt(pcPriceMin);
			hql.andBy("productName", Condition.ge, priceMin);	
		}
		if(pcPriceMax!=null&&pcPriceMax.length()>0){
			int priceMax = Integer.parseInt(pcPriceMax);
			hql.andBy("productName", Condition.le, priceMax);	
		}

		List<ProductPO> products = findByHqlPage(hql,pageNum,pageSize);
		return products;
	}
	//前台搜索
	public List<ProductPO> getListByPageB(Map<String,Object> map,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		map.put("productIsShow",1);
		map.put("productIndexRecommend",1);
		map.put("productAuditstatus",ShopAuditStateType.T);
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("productName")){
				hql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else if(entry.getKey().equals("minProductPrice")){
				hql.andBy("productPrice",  Condition.ge, entry.getValue());
			}else if(entry.getKey().equals("maxProductPrice")){
				hql.andBy("productPrice",  Condition.le, entry.getValue());
			}else{
				hql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}
		}
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}

	//前台搜索（count）
	public int getListByPageBC(Map<String,Object> map) {
		CountHql cHql=this.newCountHql();
		map.put("productIsShow",1);
		map.put("productIndexRecommend",1);
		map.put("productAuditstatus",ShopAuditStateType.T);
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("productName")){
				cHql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else if(entry.getKey().equals("minProductPrice")){
				cHql.andBy("productPrice",  Condition.ge, entry.getValue());
			}else if(entry.getKey().equals("maxProductPrice")){
				cHql.andBy("productPrice",  Condition.le, entry.getValue());
			}else{
				cHql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}
		}
		int count=this.countByHql(cHql);
		return count;
	}

	//获取热门产品
	public List<ProductPO> getListHot(Long comId , int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productIsShow", Condition.eq, 1);
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("productIndexRecommend", Condition.eq, 1);
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		hql.orderBy("productSaleNum", true);
		List<ProductPO> products = findByHqlPage(hql,pageNum,pageSize);
		if(products==null || products.size()<=0) return null;
		return products;
	}


	//通过模块和类获取商品
	public List<ProductPO> findByModular(String modularCode,long comId, int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("productIsShow", Condition.eq, 1);
		hql.andBy("productIndexRecommend", Condition.eq, 1);
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		hql.andBy("productModularCode", Condition.eq, modularCode);
//		hql.orderBy("productSaleNum", true);
		List<ProductPO> products = findByHqlPage(hql,pageNum,pageSize);
		if(products==null || products.size()<=0) return null;
		return products;
	}

	//获取产品个数
	public int countProduct(String field,Object value){
		CountHql cHql=this.newCountHql();
		cHql.andBy(field,  Condition.eq, value);
		cHql.andBy("productIsShow",  Condition.eq, 1);
		cHql.andBy("productIndexRecommend",  Condition.eq, 1);
		cHql.andBy("productAuditstatus",  Condition.eq, ShopAuditStateType.T);
		int count=this.countByHql(cHql);
		return count;
	}
	//////////////////////////////////////////后台///////////////////////////////

	public List<ProductPO> findByMerchant(long merchantId,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productMerchantID", Condition.eq, merchantId);
		
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	//获取某个公司下的所有产品
	public List<ProductPO> findByCom(long comId,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		if(comId!=1l){
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}

	//获取某个公司下面的审核通过的产品
	public List<ProductPO> findByComT(long comId,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		if(comId!=1l){
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}

	//获取某个公司下面的审核未通过的产品
	public List<ProductPO> findByComD(long comId,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		if(comId!=1l){
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.D);
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}

	//获取某个公司下的所有产品数量
	public int countByCom(long comId) {
		CountHql chql = this.newCountHql();
		if(comId!=1l){
			chql.andBy("comId", Condition.eq, comId);
		}
		int count =countByHql(chql);
		return count;
	}

	//获取某个公司下的所有通过产品数量
	public int countByComT(long comId) {
		CountHql chql = this.newCountHql();
		if(comId!=1l){
			chql.andBy("comId", Condition.eq, comId);
		}
		chql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);
		int count =countByHql(chql);
		return count;
	}

	//获取某个公司下的所有未通过产品数量
	public int countByComD(long comId) {
		CountHql chql = this.newCountHql();
		if(comId!=1l){
			chql.andBy("comId", Condition.eq, comId);
		}
		chql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.D);
		int count =countByHql(chql);
		return count;
	}
	//后台查询
	public List<ProductPO> getListByPageE(Map<String,Object> map,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("productName")||entry.getKey().equals("productMerchantName")){
				hql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else if(entry.getKey().equals("comId")){
				if((long)entry.getValue()!=1l){
					hql.andBy("comId",  Condition.eq, entry.getValue());
				}
			}else{
				hql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}
		}
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}

	//后台查询（count）
	public int  getCountByPageE(Map<String,Object> map){
		CountHql cHql=this.newCountHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("productName")||entry.getKey().equals("productMerchantName")){
				cHql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else if(entry.getKey().equals("comId")){
				if((long)entry.getValue()!=1l){
					cHql.andBy("comId",  Condition.eq, entry.getValue());
				}
			}else{
				cHql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}

		}
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	/**
	 * 获取积分商城商品
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> findByPsTypeCom(long comId,int pageNum,int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("psType", Condition.eq, ProductSaleType.GOLD);
		hql.andBy("comId", Condition.eq, comId);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}
	/**
	 * 获取积分商城商品(个数)
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
	 * app搜索商品
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> appfindByCom(Long comId,String name,int pageNum,int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);  //审核通过
		hql.andBy("productIsShow", Condition.eq, 1);                          //产品展示
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("productName", Condition.lk, name);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}
	
	/**
	 * app搜索商品
	 * 修改了平谷搜索功能 去掉了comId
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> appfindByComNew(String name,int pageNum,int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);  //审核通过
		hql.andBy("productIsShow", Condition.eq, 1);                          //产品展示
		hql.andBy("productName", Condition.lk, name);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}
	
	
	/**
	 * app搜提示
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> appPrompt(String name,int pageNum,int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);  //审核通过
		hql.andBy("productIsShow", Condition.eq, 1);                          //产品展示
		hql.andBy("productName", Condition.lk, name);
		List<ProductPO> products = findByHqlPage(hql, pageNum, pageSize);
		return products;
	}

	/**
	 * app搜索商品（个数）
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int appCountByCom(Long comId,String name) {
		CountHql cHql = newCountHql();
		cHql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);  //审核通过
		cHql.andBy("productIsShow", Condition.eq, 1);                          //产品展示
		cHql.andBy("comId", Condition.eq, comId);
		cHql.andBy("productName", Condition.lk, name);
		int count = countByHql(cHql);
		return count;
	}
	
	/**
	 * app搜索商品（个数）
	 * 平谷搜索功能修改  去掉了 Long comId 
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public int appCountByComNew(String name) {
		CountHql cHql = newCountHql();
		cHql.andBy("productAuditstatus", Condition.eq, ShopAuditStateType.T);  //审核通过
		cHql.andBy("productIsShow", Condition.eq, 1);                          //产品展示
		cHql.andBy("productName", Condition.lk, name);
		int count = countByHql(cHql);
		return count;
	}
	
	/**
	 * app商家Id获取yige商品
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
		if(products==null||products.size()==0) return null;
		return products.get(0);
	}
	
	
	public List<ProductPO> findByMerchantId(Long merchantId) {
		QueryHql hql = newQueryHql();
		hql.andBy("productMerchantID", Condition.eq, merchantId);
		hql.orderBy("id", false);
		List<ProductPO> products = findByHql(hql);
		return products;
	}
	
	/**
	 * app获取某个积分商品
	 * @param comId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ProductPO getByGold(Long productId) {
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, productId);
		hql.andBy("psType", Condition.eq, ProductSaleType.GOLD);
		hql.andBy("productAuditstatus",  Condition.eq, ShopAuditStateType.T);
		List<ProductPO> products = findByHql(hql);
		if(products==null||products.size()==0) return null;
		return products.get(0);
	}
	
	public List<ProductPO> getProductByProId(long id){
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<ProductPO> list = findByHql(hql);
		return list;
	}
	
	//获取商品Id
	public List<ProductPO> getAllSettle(long id){
		QueryHql hql = newQueryHql();
		hql.andBy("productMerchantID", Condition.eq, id);
		List<ProductPO> list = findByHql(hql);
		if(list==null||list.size()==0) return null;
		return list;
	}
	public List<ProductPO> getMoHu(String name , int page , int limit){
		QueryHql hql = newQueryHql();
		hql.andBy("productName", Condition.lk, name);
		List<ProductPO> list = findByHqlPage(hql, page, limit);
		return list;
	}
	
	public Long getMaxId(){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select max(a.id) maxid from t_sys_modular_product a");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("maxid",StandardBasicTypes.LONG);
		if(query.uniqueResult()==null) return 0l;
		long maxId = (long)query.uniqueResult();
		return maxId;
	}
	
	
	
	// 查询到店支付 id
		public ProductPO searchOrder(long merchantId,String name)
				throws ParseException {
			QueryHql hql = this.newQueryHql();
			hql.andBy("productMerchantID", Condition.eq, merchantId);
			hql.andBy("productName", Condition.eq, name);
			List<ProductPO> orders = findByHql(hql);
			if (orders == null || orders.size() == 0)
				return null;
			return orders.get(0);
		}
	
	
	
	
	
}
