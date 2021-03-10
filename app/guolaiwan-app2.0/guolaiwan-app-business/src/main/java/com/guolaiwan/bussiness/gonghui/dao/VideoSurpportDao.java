package com.guolaiwan.bussiness.gonghui.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.gonghui.po.ClassesPo;
import com.guolaiwan.bussiness.gonghui.po.RecordPo;
import com.guolaiwan.bussiness.gonghui.po.VideoPo;
import com.guolaiwan.bussiness.gonghui.po.VideoSurpportPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.gonghui.dao.VideoSurpportDao")
public class VideoSurpportDao extends AbstractBaseDao<VideoSurpportPo> {
	
	public int countTodayByUser(long userId) throws ParseException {
		CountHql hql = this.newCountHql();
		hql.andBy("userId",Condition.eq,userId);
		Date today=new Date();
		Date start=DateUtil.parse(DateUtil.format(today,"yyyy-MM-dd")+" 00:00:00","yyyy-MM-dd HH:mm:ss");
		Date end=DateUtil.parse(DateUtil.format(today,"yyyy-MM-dd")+" 23:59:59","yyyy-MM-dd HH:mm:ss");
		hql.andBy("updateTime", Condition.ge, start);
		hql.andBy("updateTime", Condition.le, end);
		return countByHql(hql);
	}


}
