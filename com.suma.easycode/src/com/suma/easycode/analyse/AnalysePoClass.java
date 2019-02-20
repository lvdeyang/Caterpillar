package com.suma.easycode.analyse;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnalysePoClass {
	private static final String exceptParam="serialVersionUID";
	public static List<ParamBO> analysePo(String className) throws SecurityException, NoSuchMethodException, ClassNotFoundException {
		List<ParamBO> boList=new ArrayList<ParamBO>();
		// 下面的代码可以获取ClassTest对饮的Class
		
		Class clazz = Class.forName(className);
       
		Field[] fs = clazz.getDeclaredFields();// 获取PrivateClass所有属性
		for (int i = 0; i < fs.length; i++) {
			if(!fs[i].getName().equals(exceptParam)){
				ParamBO bo=new ParamBO();
				bo.setName(fs[i].getName());
				bo.setType(fs[i].getType().getTypeName());
				boList.add(bo);
			}
		}
		return boList;
	}

}
