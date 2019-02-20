/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.orm.component.Hql
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.orm.hql;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import pub.caterpillar.orm.po.AbstractBasePO;



public abstract class Hql {
	String valueName;
	String entityClass;
	String entity;
	StringBuilder hql;
	
	
	Map<String, Object> valueMap;
	
	boolean bOrderBy = false;
	boolean isSubHql = false;
	
	//查询条件是否有第一个条件
	//true:主查询不需要加where关键字，查询需要加and、or等加操作符
	//false:主查询需要加where关键字，子查询不需要加and、or等加操作符
	boolean hasFirstCon = false;
	boolean autoUseAlias = true;
	
	public Hql(Class<? extends AbstractBasePO> clazz) {
		entityClass = clazz.getSimpleName();
		entity = StringUtils.uncapitalize(entityClass);
		hql = new StringBuilder();
		valueMap = new LinkedHashMap<>(0);
		valueName = "Q_"+entityClass+"_";
	}
	
	protected String getValueName(){
		return valueName+valueMap.size();
	}

	public void setAutoUseAlias(boolean autoUseAlias) {
		this.autoUseAlias = autoUseAlias;
	}
	
	public void fetchAllProperties(){
		hql.append("fetch all properties ");
	}

	public Hql andBy(String expression){
		if (!hasFirstCon){
			if(!isSubHql) {
				hql.append("where ");
			}
			hasFirstCon = true;
		}else{
			hql.append("and ");
		}
		hql.append(expression);
		hql.append(" ");
		return this;
	}

	public Hql andBy(String field,Condition condition,Object value){
		return opBy("and", field, condition, value);
	}
	
	public Hql orBy(String field,Condition condition,Object value){
		return opBy("or", field, condition, value);
	}
	
	protected Hql opBy(String op, String field,Condition condition,Object value) {
		if (value==null||"".equals(value)) return this;
		String valueName = getValueName();
		if (!hasFirstCon){
			if(!isSubHql) {
				hql.append("where ");
			}
			hasFirstCon = true;
		}else{
			hql.append(op + " ");
		}
		if (autoUseAlias){
			hql.append(entity+".");
		}
		hql.append(field);
		hql.append(condition);
		hql.append(":");
		hql.append(valueName);
		hql.append(" ");
		switch (condition) {
		case lk:
			value = "%"+value+"%";
			valueMap.put(valueName,value);
			break;

		default:
			valueMap.put(valueName,value);
			break;
		} 
		
		return this;
	}
	
	public Hql andSubHql(Hql subHql) {
		return opSubHql("and", subHql);
	}
	
	public Hql orSubHql(Hql subHql) {
		return opSubHql("or", subHql);
	}
	
	protected Hql opSubHql(String op, Hql subHql) {
		if (!hasFirstCon){
			if(!isSubHql) {
				hql.append("where ");
			}
			hasFirstCon = true;
		}else{
			hql.append(op);
		}
		hql.append(" (");
		hql.append(subHql.toString());
		hql.append(")");
		valueMap.putAll(subHql.valueMap);
		return this;
	}
	
    public Hql andIsNull(String field){
		if (!hasFirstCon){
			if(!isSubHql) {
				hql.append("where ");
			}
			hasFirstCon = true;
		}else{
			hql.append("and ");
		}
		if (autoUseAlias){
			hql.append(entity+".");
		}
		hql.append(field);
		hql.append(" is null ");
    	return this;
    }
    
    public Hql andIsNotNull(String field){
		if (!hasFirstCon){
			if(!isSubHql) {
				hql.append("where ");
			}
			hasFirstCon = true;
		}else{
			hql.append("and ");
		}
		if (autoUseAlias){
			hql.append(entity+".");
		}
		hql.append(field);
		hql.append(" is not null ");
    	return this;
    }
    

    
	public Hql andIn(String field, Collection<?> array) {
		return opIn("and",false, field, array);
	}
	
//	public Hql andAnyIn(Collection<?> array,String collectionField){
//		if (array==null||array.isEmpty()) return this;
//		if (!(array instanceof Set<?>)){
//			array = new HashSet<>(array);
//		}
//		if (hasWhere){
//			hql.append("and ");
//		}else{
//			hql.append("where ");
//			hasWhere = true;
//		}
//		hql.append(" any(:");
//		hql.append(LIST_VALUES);
//		hql.append(listValues.size());
//		hql.append(") in ");
//		
//		hql.append("elements(");
//		if (autoUseAlias){
//			hql.append(entity+".");
//		}
//		hql.append(collectionField);
//		hql.append(") ");
//		listValues.add(array);
//    	return this;
//	}
	
	public Hql orIn(String field, Collection<?> array) {
    	return opIn("or",false, field, array);
	}
	
	protected Hql opIn(String op,boolean notIn, String field, Collection<?> array) {
		if (array==null||array.isEmpty()) return this;
		String valueName = getValueName();
		if (!(array instanceof Set<?>)){
			array = new HashSet<>(array);
		}
		if (!hasFirstCon){
			if(!isSubHql) {
				hql.append("where ");
			}
			hasFirstCon = true;
		}else{
			hql.append(op + " ");
		}
		if (autoUseAlias){
			hql.append(entity+".");
		}
		hql.append(field);
		if (notIn){
			hql.append(" not");
		}
		hql.append(" in (:");
		hql.append(valueName);
		hql.append(") ");
		valueMap.put(valueName, array);
		
    	return this;
	}
	
	public Hql andNotIn(String field, Collection<?> array) {	
		return opIn("and", true, field, array);
	}
    
	public Hql orNotIn(String field, Collection<?> array){
		return opIn("or", true, field, array);
	}
	
	
	public String toString(){
		return hql.toString();
	}
	
	public Map<String, Object> getValueMap(){
		return valueMap;
	}
}
