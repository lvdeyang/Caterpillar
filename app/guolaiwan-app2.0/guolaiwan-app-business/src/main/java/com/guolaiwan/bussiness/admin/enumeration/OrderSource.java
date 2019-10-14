package com.guolaiwan.bussiness.admin.enumeration;

public enum OrderSource {
	// 网页
	WEBPAGE("WEBPAGE"),
	// 直播来源
	LIVE("LIVE"),
	// 分销线下
	DISTRIBUTORUNLINE("DISTRIBUTORUNLINE"),
	// 分销线上
    DISTRIBUTORONLINE("DISTRIBUTORONLINE"),
	// 公众号
	PUBLICADDRESS("PUBLICADDRESS"),
	// 线下
	UNLINE("UNLINE"),
	// APP
	APP("APP");

	private String name;

	private OrderSource(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static OrderSource fromName(String name) throws Exception {
		if ("WEBPAGE".equals(name)) {
			return WEBPAGE;
		} else if ("直播".equals(name)) {
			return LIVE;
		} else if ("App".equals(name)) {
			return APP;
		} else if ("分销线下".equals(name)) {
			return DISTRIBUTORUNLINE;
		}else if ("分销线上".equals(name)) {
			return DISTRIBUTORONLINE;
		} else if ("线下".equals(name)) {
			return UNLINE;
		} else if ("公众号".equals(name)) {
			return PUBLICADDRESS;
		} else {
			throw new Exception("错误的订单来源：" + name);
		}
	}

	public static OrderSource fromString(String s) throws Exception {
		if ("WEBPAGE".equals(s)) {
			return WEBPAGE;
		} else if ("LIVE".equals(s)) {
			return LIVE;
		} else if ("APP".equals(s)) {
			return APP;
		} else if ("PUBLICADDRESS".equals(s)) {
			return PUBLICADDRESS;
		} else if ("DISTRIBUTORONLINE".equals(s)) {
			return DISTRIBUTORONLINE;
		} else if ("DISTRIBUTORONLINE".equals(s)) {
			return DISTRIBUTORONLINE;
		} else {
			throw new Exception("错误的订单来源：" + s);
		}
	}

	public static String fromStr2Name(String s) throws Exception {
		if ("WEBPAGE".equals(s)) {
			return "网页";
		} else if ("LIVE".equals(s)) {
			return "直播";
		} else if ("APP".equals(s)) {
			return "APP";
		} else if ("PUBLICADDRESS".equals(s)) {
			return "公众号";
		} else if ("DISTRIBUTORUNLINE".equals(s)) {
			return "分销线下";
		} else if ("DISTRIBUTORONLINE".equals(s)) {
			return "分销线上";
		} else if ("UNLINE".equals(s)) {
			return "线下";
		} else {
			throw new Exception("错误的订单来源：" + s);
		}
	}

}
