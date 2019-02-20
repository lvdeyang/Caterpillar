package pub.guolaiwan.app.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import pub.caterpillar.app.carpool.po.CarPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;
import pub.guolaiwan.app.po.DistributePolicy;
import pub.guolaiwan.app.po.RegionPo;
@Component
public class RegionDao extends AbstractBaseDao<RegionPo> {
	public List<RegionPo> queryAllFirstRegion(){
		QueryHql hql = this.newQueryHql();
		hql.andBy("parentId", Condition.eq, 0l);
		List<RegionPo> regions = this.findByHql(hql);
		if(regions==null || regions.size()<=0) return null;
		return regions;
	}
}
