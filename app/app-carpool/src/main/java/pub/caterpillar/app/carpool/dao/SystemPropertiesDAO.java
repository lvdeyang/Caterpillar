package pub.caterpillar.app.carpool.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import pub.caterpillar.app.carpool.enumeration.SystemPropertiesType;
import pub.caterpillar.app.carpool.po.SystemPropertiesPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class SystemPropertiesDAO extends AbstractBaseDao<SystemPropertiesPO>{

	public List<SystemPropertiesPO> queryWeixinConfig(){
		QueryHql hql = this.newQueryHql();
		hql.andBy("type", Condition.eq, SystemPropertiesType.WEIXIN);
		return this.findByHql(hql);
	}
	
}
