package com.guolaiwan.app.sec.thread.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.guolaiwan.app.sec.vo.SecUserPointVo;
import com.guolaiwan.bussiness.sec.dao.SecPointDAO;
import com.guolaiwan.bussiness.sec.dao.SecPointTimeDAO;
import com.guolaiwan.bussiness.sec.dao.SecUserDAO;
import com.guolaiwan.bussiness.sec.dao.SecUserPointDAO;
import com.guolaiwan.bussiness.sec.enums.SecPointType;
import com.guolaiwan.bussiness.sec.enums.SecUserPointStatus;
import com.guolaiwan.bussiness.sec.po.SecPointPo;
import com.guolaiwan.bussiness.sec.po.SecPointTimePo;
import com.guolaiwan.bussiness.sec.po.SecUserPo;
import com.guolaiwan.bussiness.sec.po.SecUserPointPo;

import cn.hutool.json.JSONException;
import pub.caterpillar.commons.util.date.DateUtil;

@Component
@EnableScheduling
public class SecScheduledTask {
	@Autowired
	SecPointTimeDAO conn_secPointTime;
	@Autowired
	SecUserPointDAO conn_secuserpoint;
	@Autowired
	SecPointDAO conn_secPoint;
	@Autowired
	SecUserDAO conn_secuser;
	
	//每天晚上23点执行
	//@Scheduled(cron = "0 0 23 * * ?")

	@Scheduled(cron = "0 * 15 * * ?")//在每天下午2点到下午2:59期间的每1分钟触发
	public void SetUserPoint(){
		try {
			List<SecPointTimePo> secPointTimePos=conn_secPointTime.findAll();
			
			for (SecPointTimePo secPointTimePo : secPointTimePos) {
				SecPointPo secPointPo=conn_secPoint.get(secPointTimePo.getSecPointId());
				List<SecUserPo> secComUserPos=conn_secuser.findByField("companyId", secPointPo.getCompanyId());
				for (SecUserPo secUserPo : secComUserPos) {
					List<SecUserPointPo> secUserPointPos=conn_secuserpoint.findbyUserAndPointTimeAndDate(secUserPo.getId(),
							secPointTimePo.getId(),DateUtil.format(new Date(),"yyyy-MM-dd"));
					Date currDate=DateUtil.parse("1970-01-01 "+DateUtil.format(new Date(), "HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
					if(secUserPointPos==null||secUserPointPos.isEmpty()){
						SecUserPointPo secUserPointPo=new SecUserPointPo();
						secUserPointPo.setSecPointId(secPointTimePo.getSecPointId());
						secUserPointPo.setSecUserId(secUserPo.getId());
						secUserPointPo.setSetTime(new Date());
						secUserPointPo.setSecPointTimeId(secPointTimePo.getId());
						secUserPointPo.setStatus(SecUserPointStatus.NOT);
						conn_secuserpoint.save(secUserPointPo);
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	

}
