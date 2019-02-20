package pub.caterpillar.app.carpool.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import pub.caterpillar.app.carpool.po.SitePO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class SiteDAO extends AbstractBaseDao<SitePO>{

	public List<SitePO> filterById(Long id){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.ne, id);
		return this.findByHql(hql);
	}
	
}
