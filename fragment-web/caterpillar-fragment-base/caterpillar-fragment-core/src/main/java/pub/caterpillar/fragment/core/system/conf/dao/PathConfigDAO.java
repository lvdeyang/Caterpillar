package pub.caterpillar.fragment.core.system.conf.dao;

import java.util.Collection;
import java.util.List;
import pub.caterpillar.fragment.core.system.conf.po.PathConfigPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 系统路径配置查询接口<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月17日 下午2:19:34
 */
public class PathConfigDAO extends AbstractBaseDao<PathConfigPO>{

	/**
	 * 根据类型查询路径<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午2:25:36
	 * @param type 类型
	 * @return {@link pub.caterpillar.fragment.core.system.conf.po.PathConfigPO} 对象
	 */
	public PathConfigPO queryByType(String type){
		QueryHql hql = this.newQueryHql();
		hql.andBy("type", Condition.eq, type);
		List<PathConfigPO> configs = this.findByHql(hql);
		if(configs==null || configs.size()<=0) return null;
		return configs.get(0);
	}
	
	/**
	 * 根据类型集合查询路径<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午2:26:04
	 * @param types 类型集合
	 * @return {@link pub.caterpillar.fragment.core.system.conf.po.PathConfigPO} 集合
	 */
	public List<PathConfigPO> queryByType(Collection<String> types){
		if(types==null || types.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("type", Condition.in, types);
		return this.findByHql(hql);
	}
	
}
