package com.guolaiwan.bussiness.admin.enumeration;

public enum ColumnType {

	RECOMMEND("首页推荐");

	private String name;

	private ColumnType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getFiled() throws Exception {
		if (this.equals(RECOMMEND)) {
			return "RECOMMEND";
		} else {
			throw new Exception("错误的栏目类型！");
		}
	}

	public static ColumnType fromName(String name) throws Exception {
		if ("首页推荐".equals(name)) {
			return RECOMMEND;
		} else {
			throw new Exception("错误的栏目类型：" + name);
		}
	}

	public static ColumnType fromString(String name) throws Exception {
		if ("RECOMMEND".equals(name)) {
			return RECOMMEND;
		} else {
			throw new Exception("错误的栏目类型：" + name);
		}
	}

}