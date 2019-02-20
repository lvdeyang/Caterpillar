/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.orm.component.QueryHql
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.orm.hql;

import pub.caterpillar.orm.po.AbstractBasePO;

public class CountHql extends Hql {

	/**
	 * @param clazz
	 */
	public CountHql(Class<? extends AbstractBasePO> clazz) {
		super(clazz);
		hql.append("select count(*) from ")
		.append(entityClass)
		.append(" ")
		.append(entity)
		.append(" ");
	}
	
	CountHql(){
		super(AbstractBasePO.class);
	}

}
