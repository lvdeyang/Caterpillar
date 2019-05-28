package com.guolaiwan.app.xinglongshan.utils;

import java.util.Date;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class DateConverter implements SingleValueConverter{
	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return Date.class.isAssignableFrom(type);
	}

	@Override
	public String toString(Object obj) {
		return DateUtil.format((Date) obj, DateUtil.yyyy_MM_dd_HH_mm_ss);
	}

	@Override
	public Object fromString(String str) {
		if (StringUtil.isTrimBlank(str)) {
			return null;
		} else if (str.length() == 19) {
			return DateUtil.parse(str, DateUtil.yyyy_MM_dd_HH_mm_ss);
		} else if (str.length() == 16) {
			return DateUtil.parse(str, DateUtil.yyyy_MM_dd_HH_mm);
		} else {
			return DateUtil.parse(str, DateUtil.yyyy_MM_dd);
		}
	}
}
