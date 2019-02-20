/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.orm.component.DeleteHql
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.orm.hql;

import pub.caterpillar.orm.po.AbstractBasePO;

public class DeleteHql extends Hql {

	/**
	 * @param clazz
	 */
	public DeleteHql(Class<? extends AbstractBasePO> clazz) {
		super(clazz);
		hql.append("delete from ")
		.append(entityClass)
		.append(" ")
		.append(entity)
		.append(" ");
	}

}
