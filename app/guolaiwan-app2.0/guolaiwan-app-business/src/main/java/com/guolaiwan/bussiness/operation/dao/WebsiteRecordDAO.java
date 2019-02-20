package com.guolaiwan.bussiness.operation.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bytedeco.javacpp.RealSense.intrinsics;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.operation.po.WebsiteRecord;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.WebsiteRecordDAO")
public class WebsiteRecordDAO extends AbstractBaseDao<WebsiteRecord>{
	public int countToday() throws ParseException{
		CountHql hql=this.newCountHql();
		hql.andBy("updateTime",Condition.gt,DateUtil.parse(DateUtil.format(new Date(), DateUtil.defaultDatePattern)+" 00:00:00"));
		hql.andBy("updateTime",Condition.lt,DateUtil.parse(DateUtil.format(new Date(), DateUtil.defaultDatePattern)+" 23:59:59"));
		return countByHql(hql);
	}
	
	public int  countByDate(String date) throws ParseException{
		
		CountHql hql=this.newCountHql();
		hql.andBy("updateTime",Condition.gt,DateUtil.parse(date+" 00:00:00"));
		hql.andBy("updateTime",Condition.lt,DateUtil.parse(date+" 23:59:59"));
		return countByHql(hql);
		
	}
	
	public List<Object> getRecordGroupByUrl(){
		StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT   count(*) a,url from t_website_record GROUP BY url ORDER BY a DESC LIMIT 5;");
		List<Object> result = this.findBySql(sqlBuffer.toString());
		if(result==null || result.size()<=0) return null;
		return result;
	}
	
}
