package pub.caterpillar.app.carpool.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import pub.caterpillar.app.carpool.po.UserPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class UserDAO extends AbstractBaseDao<UserPO>{

	public UserPO queryByMobile(String mobile){
		if(mobile == null) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("mobile", Condition.eq, mobile);
		List<UserPO> users = this.findByHql(hql);
		if(users==null || users.size()<=0) return null;
		return users.get(0);
	}
	
	public UserPO queryByOpenId(String openId){
		if(openId == null) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("openId", Condition.eq, openId);
		List<UserPO> users = this.findByHql(hql);
		if(users==null || users.size()<=0) return null;
		return users.get(0);
	}
	
}
