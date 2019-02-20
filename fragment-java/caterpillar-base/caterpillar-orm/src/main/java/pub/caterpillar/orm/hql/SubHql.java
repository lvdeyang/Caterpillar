package pub.caterpillar.orm.hql;

import pub.caterpillar.orm.po.AbstractBasePO;

public class SubHql extends Hql {

	public SubHql(Class<? extends AbstractBasePO> clazz) {
		super(clazz);
		isSubHql = true;
	}

}
