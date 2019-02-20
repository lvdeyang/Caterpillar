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

import org.springframework.util.StringUtils;

import pub.caterpillar.orm.po.AbstractBasePO;


public class QueryHql extends Hql {
	
	

	/**
	 * @param clazz
	 */
	public QueryHql(Class<? extends AbstractBasePO> clazz) {
		super(clazz);
		hql.append("from ")
		.append(entityClass)
		.append(" ")
		.append(entity)
		.append(" ");
	}
	//如果是多个objs 则是数组
	public QueryHql insertQueryObject(boolean distinct ,String... objs){
		StringBuilder sb = new StringBuilder("select ");
		if (distinct) sb.append("distinct ");
		boolean isfirst = true;
		for (String o:objs){
			if (isfirst){
				isfirst = false;
			}else{
				sb.append(", ");
			}
			if (autoUseAlias){
				sb.append(entity);
				sb.append(".");
			}
			sb.append(o);
		}
		sb.append(" ");
		hql.insert(0, sb);
		return this;
	}
	
	public QueryHql insertQueryObject(boolean distinct ,Class<?> clazz,String... objs){
		StringBuilder sb = new StringBuilder("select ");
		if (distinct) sb.append("distinct ");
		sb.append("new ");
		sb.append(clazz.getSimpleName());
		sb.append("(");
		boolean isfirst = true;
		for (String o:objs){
			if (isfirst){
				isfirst = false;
			}else{
				sb.append(", ");
			}
			if (autoUseAlias){
				sb.append(entity);
				sb.append(".");
			}
			sb.append(o);
		}
		sb.append(") ");
		hql.insert(0, sb);
		return this;
	}
	
	public QueryHql insertListObject(boolean distinct,String... objs){
		StringBuilder sb = new StringBuilder("select ");
		if (distinct) sb.append("distinct ");
		sb.append("new ");
		sb.append("list");
		sb.append("(");
		boolean isfirst = true;
		for (String o:objs){
			if (isfirst){
				isfirst = false;
			}else{
				sb.append(", ");
			}
			if (autoUseAlias){
				sb.append(entity);
				sb.append(".");
			}
			sb.append(o);
		}
		sb.append(") ");
		hql.insert(0, sb);
		return this;
	}
	//需要加上as e.g. p.name as personName,那么personName 作为map的key值
	public QueryHql insertMapObject(boolean distinct,String... objs){
		StringBuilder sb = new StringBuilder("select ");
		if (distinct) sb.append("distinct ");
		sb.append("new ");
		sb.append("map");
		sb.append("(");
		boolean isfirst = true;
		for (String o:objs){
			if (isfirst){
				isfirst = false;
			}else{
				sb.append(", ");
			}
			if (autoUseAlias){
				sb.append(entity);
				sb.append(".");
			}
			sb.append(o);
		}
		sb.append(") ");
		hql.insert(0, sb);
		return this;
	}
	
	public CountHql transToCountHql(){
		CountHql hql = new CountHql();
		hql.autoUseAlias = this.autoUseAlias;
		hql.entity = this.entity;
		hql.entityClass = this.entityClass;
		hql.hasFirstCon = this.hasFirstCon;
		hql.valueName = this.valueName;
		hql.hql.append("select count(*) ").append(this.hql);
		hql.valueMap.putAll(this.valueMap);
		return hql;
	}
	
	public QueryHql orderBy(String field,boolean desc){
		if (!bOrderBy){
			hql.append("order by ");
			if (autoUseAlias){
				hql.append(entity);
				hql.append(".");
			}
			hql.append(field);
			bOrderBy = true;
		}else{
			hql.append(",");
			if (autoUseAlias){
				hql.append(entity);
				hql.append(".");
			}
			hql.append(field);
		}
		hql.append(" ");
		if (desc){
			hql.append("desc ");
		}
		return this;
	}
	
	public QueryHql innerJoin(Class<? extends AbstractBasePO> clazz){
		hql.append(", ");
		hql.append(clazz.getSimpleName());
		hql.append(" ");
		hql.append(StringUtils.uncapitalize(clazz.getSimpleName()));
		hql.append(" ");
		return this;
	}
	
	public QueryHql with(String... withCondition){
		if (withCondition.length>0){
			hql.append("with ");
			for (String condition:withCondition){
				hql.append(condition);
				hql.append(" ");
			}
		}
		setAutoUseAlias(false);
		return this;
	}
	
	public QueryHql innerJoin(String collectionField,String alias){
		hql.append("inner join ");
		hql.append(collectionField);
		hql.append(" ");
		hql.append(alias);
		hql.append(" ");
		setAutoUseAlias(false);
		return this;
	}
}
