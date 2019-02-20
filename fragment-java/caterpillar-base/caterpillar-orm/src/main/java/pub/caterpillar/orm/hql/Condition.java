/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.orm.component.Condition
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.orm.hql;



public enum Condition {
	in(" in "),
	nin(" not in "),
	eq("="),
	ne("!="),
	lk(" like "),
	lt("<"),
	gt(">"),
	le("<="),
	ge(">="),
	is(" is "),
	isNot(" is not ");

	
	String condi;

	Condition(String name) {
		condi = name;
	}
	
	@Override
	public String toString() {
		return condi;
	}
}
