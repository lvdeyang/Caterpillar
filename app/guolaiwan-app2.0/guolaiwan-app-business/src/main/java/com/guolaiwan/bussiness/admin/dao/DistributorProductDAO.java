package com.guolaiwan.bussiness.admin.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.DistributorProductPO;
import com.guolaiwan.bussiness.admin.po.LiveProductPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 分销商-商品
 * @author 一只会写bug的井蛙
 *
 */
@Repository("com.guolaiwan.bussiness.admin.dao.DistributorProductDAO")
public class DistributorProductDAO extends AbstractBaseDao<DistributorProductPO>{

	// 通过商品id删除
	public void deleteByProductId(ProductPO product) {
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("product",Condition.eq, product);
		deleteByHql(hql);
	}

	// findByProductId
	public List<DistributorProductPO> findByProductId(ProductPO product) {
		QueryHql hql = newQueryHql();
		hql.andBy("product", Condition.eq, product);
		List<DistributorProductPO> distributorProducts = findByHql(hql);
		return distributorProducts;
	}

	// findByDistributorId
	public List<DistributorProductPO> findByDistributorId(long distributorId, int page, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		List<DistributorProductPO> distributorProducts = findByHql(hql, page, pageSize);
		return distributorProducts;
	}


	public int countOfADistributor (long distributorId) {
		CountHql chql = this.newCountHql();
		chql.andBy("distributorId", Condition.eq, distributorId);
		int count =countByHql(chql);
		return count;
	}


	// findByDistributorId
	public DistributorProductPO getByDisAndPro(long distributorId,ProductPO product) {
		QueryHql hql = newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		hql.andBy("product", Condition.eq, product);
		List<DistributorProductPO> distributorProducts = findByHql(hql);
		DistributorProductPO distributorProduct =  distributorProducts.get(0);
		return distributorProduct;
	}


	/**
	 * app调用
	 * @param distributorId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<DistributorProductPO> findByDisId(long distributorId, int page, int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		List<DistributorProductPO> distributorProducts = findByHql(hql, page, pageSize);
		return distributorProducts;
	}

	/**
	 * app调用
	 * @param distributorId
	 * @return
	 */
	public int countByDisId(long distributorId) {
		CountHql cHql = newCountHql();
		cHql.andBy("distributorId", Condition.eq, distributorId);
		int count = countByHql(cHql);
		return count;
	}


	//活动页面的商品列表：（商品名称检索）
	public List<ProductDTO> findByDistriJS(Map<String, Object> map,int pageNum,int pageSize){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper();
		sqlWrapper.append("select mp.*,dp.distributorPrice,dp.sellPrice,dp.uuid distributorProductUUID from ");
		sqlWrapper.append("t_sys_distributorproduct dp,t_sys_modular_product mp ");
		sqlWrapper.append("where dp.product_id = mp.id ");
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("productName")){
				sqlWrapper.append(" and mp.productName like '%"+entry.getValue()+"%' ");
			}else if(entry.getKey().equals("minPrice")){
				sqlWrapper.append(" and dp.distributorPrice > "+entry.getValue());
			}else if(entry.getKey().equals("maxPrice")){
				sqlWrapper.append(" and dp.distributorPrice < "+entry.getValue());
			}else{
				sqlWrapper.append(" and dp.distributorId = "+entry.getValue());
			}
		}

		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("updateTime",StandardBasicTypes.DATE)
				.addScalar("uuid",StandardBasicTypes.STRING)
				.addScalar("productBeginDate",StandardBasicTypes.DATE)
				.addScalar("productClassCode",StandardBasicTypes.STRING)
				.addScalar("productClassName",StandardBasicTypes.STRING)
				.addScalar("productCommissionCode",StandardBasicTypes.INTEGER)
				.addScalar("productCommissionPrice",StandardBasicTypes.LONG)
				.addScalar("productEctivedate",StandardBasicTypes.DATE)
				.addScalar("productEnddate",StandardBasicTypes.DATE)
				.addScalar("productIndexRecommend",StandardBasicTypes.INTEGER)
				.addScalar("productIntroduce",StandardBasicTypes.STRING)
				.addScalar("productIsShow",StandardBasicTypes.INTEGER)
				.addScalar("productLimitNum",StandardBasicTypes.LONG)
				.addScalar("productLimitType",StandardBasicTypes.INTEGER)
				.addScalar("productListRecommend",StandardBasicTypes.INTEGER)
				.addScalar("productMerchantName",StandardBasicTypes.STRING)
				.addScalar("productModularCode",StandardBasicTypes.STRING)
				.addScalar("productModularCodeName",StandardBasicTypes.STRING)
				.addScalar("productName",StandardBasicTypes.STRING)
				.addScalar("productOldPrice",StandardBasicTypes.LONG)
				.addScalar("productPrice",StandardBasicTypes.LONG)
				.addScalar("productSaleNum",StandardBasicTypes.LONG)
				.addScalar("productShowNum",StandardBasicTypes.LONG)
				.addScalar("productShowPic",StandardBasicTypes.STRING)
				.addScalar("productMorePic",StandardBasicTypes.STRING)
				.addScalar("distributorPrice",StandardBasicTypes.LONG)
				.addScalar("distributorProductUUID",StandardBasicTypes.STRING)
				.addScalar("sellPrice",StandardBasicTypes.LONG);

		query.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
		//分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}

	//活动页面的商品个数：（商品名称检索）
	public int countByDistriJS(Map<String, Object> map){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper();
		sqlWrapper.append("select count(*) ac from ");
		sqlWrapper.append("t_sys_distributorproduct dp,t_sys_modular_product mp ");
		sqlWrapper.append("where dp.product_id = mp.id ");
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("productName")){
				sqlWrapper.append(" and mp.productName like '%"+entry.getValue()+"%' ");
			}else if(entry.getKey().equals("minPrice")){
				sqlWrapper.append(" and dp.distributorPrice > "+entry.getValue());
			}else if(entry.getKey().equals("maxPrice")){
				sqlWrapper.append(" and dp.distributorPrice < "+entry.getValue());
			}else{
				sqlWrapper.append(" and dp.distributorId = "+entry.getValue());
			}
		}

		System.out.println(sqlWrapper.toString());
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("ac",StandardBasicTypes.INTEGER);

		int count = (int)query.uniqueResult();

		return count;
	}

}
