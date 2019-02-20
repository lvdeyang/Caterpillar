package pub.caterpillar.packing.business.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;
import pub.caterpillar.packing.business.admin.po.AdminPO;

@Repository
public class AdminDAO extends AbstractBaseDao<AdminPO>{

	@Override
	public List<AdminPO> findAll() {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("updateTime", true);
		return this.findByHql(hql);
	}
	
	//根据用户名获取用户
	public AdminPO queryByUsername(String username){
		QueryHql hql = this.newQueryHql();
		hql.andBy("username", Condition.eq, username);
		List<AdminPO> admins = this.findByHql(hql);
		if(admins!=null && admins.size()>0){
			return admins.get(0);
		}else{
			return null;
		}
	}
	
}
