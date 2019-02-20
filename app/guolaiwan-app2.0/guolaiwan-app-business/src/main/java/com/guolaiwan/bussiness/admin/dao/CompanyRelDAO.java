package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.RecommendType;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.CompanyRelPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.CompanyRelDAO")
public class CompanyRelDAO extends AbstractBaseDao<CompanyRelPO> {
	
	//获取子公司的特别推荐
	public List<CompanyRelPO> queryCompanyRespecial(Long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("sonComId", Condition.eq, comId);
		hql.andBy("rType", Condition.eq, RecommendType.SPECIAL);
		return this.findByHql(hql);
	}
	
	//获取子公司的推荐视频
	public List<CompanyRelPO> queryCompanyRevideo(Long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("sonComId", Condition.eq, comId);
		hql.andBy("rType", Condition.eq, RecommendType.RADIO);
		return this.findByHql(hql);
	}
	
	//获取子公司的轮播图
	public List<CompanyRelPO> queryCompanyCarousel(Long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("sonComId", Condition.eq, comId);
		hql.andBy("rType", Condition.eq, RecommendType.CAROUSEL);
		return this.findByHql(hql);
	}
	
	//删除通过资源id和资源类型
	public void deleteByRelId(long rId,String source) throws Exception{
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("relaId",Condition.eq, rId);
		hql.andBy("rType", Condition.eq, RecommendType.fromString(source));
		deleteByHql(hql);
	}

	//删除通过子公司id
	public void deleteByComId(long comId) throws Exception{
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("sonComId",Condition.eq, comId);
		deleteByHql(hql);
	}

	public List<CompanyRelPO> findByRelIdS(long rId,String source) throws Exception{
		QueryHql hql = this.newQueryHql();
		hql.andBy("relaId",Condition.eq, rId);
		hql.andBy("rType", Condition.eq, RecommendType.fromString(source));
		List<CompanyRelPO> comRs = findByHql(hql);
		return comRs;
	}

	//通过资源id 和资源类型  获取数据个数
	public int countByRelIdS(long rId,String source) throws Exception{
		CountHql cHql = this.newCountHql();
		cHql.andBy("relaId",Condition.eq, rId);
		cHql.andBy("rType", Condition.eq, RecommendType.fromString(source));
		int count = countByHql(cHql);
		return count;
	}

	//通过子公司id 和资源类型  获取数据个数
	public int countByComIdS(long comId,String source) throws Exception{
		CountHql cHql = this.newCountHql();
		cHql.andBy("sonComId",Condition.eq, comId);
		cHql.andBy("rType", Condition.eq, RecommendType.fromString(source));
		int count = countByHql(cHql);
		return count;
	}


}
