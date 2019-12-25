package com.sumavision.tetris.outlink;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.attend.AttendDao;
import com.sumavision.tetris.attend.AttendPo;
import com.sumavision.tetris.attend.AttendQuery;
import com.sumavision.tetris.commons.util.date.DateUtil;

import javassist.compiler.ast.NewExpr;

/**
 * @Author sjj
 * @Description //TODO 考勤记录调用
 * @Date
 **/

@EnableScheduling
@Component
public class AttendoutLink {
	@Autowired 
	AttendDao dao;
	@Autowired 
	AttendQuery query;
	

    /**
     * 获取全部考勤数据
     * @return
     * @throws ParseException 
     */
	@Scheduled(cron = "0 0 0 * * *")
    void getAllRec() throws ParseException{
        JSONObject params=new JSONObject();
        String response=HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpGet,myUrl.HOST+myUrl.GETALLREC,null,null);
        JSONObject resultJsonObject=JSONObject.parseObject(response);
        JSONArray records=resultJsonObject.getJSONObject("result").getJSONArray("records");
        for (int i = 0; i < records.size(); i++) {
        	AttendPo po=new AttendPo();
            po.setAddTime(DateUtil.parse(records.getJSONObject(i).getString("addTime"),"yyyy-MM-dd HH:mm:ss"));
            po.setAttendRecId(records.getJSONObject(i).getInteger("attendRecId"));
            po.setCompanyId(records.getJSONObject(i).getInteger("companyId"));
            po.setCompanyName(records.getJSONObject(i).getString("companyName"));
            po.setDutyDay(DateUtil.parse(records.getJSONObject(i).getString("dutyDay"),"yyyy-MM-dd"));
            po.setDeviceId(records.getJSONObject(i).getInteger("deviceId"));
            po.setGroupId(records.getJSONObject(i).getInteger("groupId"));
            po.setOffDutuState(records.getJSONObject(i).getInteger("offDutuState"));
            po.setOffDutyTime(DateUtil.parse(records.getJSONObject(i).getString("offDutyTime"),"yyyy-MM-dd HH:mm:ss"));
            po.setOnDutyState(records.getJSONObject(i).getInteger("onDutyState"));
            po.setOnDutyTime(DateUtil.parse(records.getJSONObject(i).getString("onDutyTime"),"yyyy-MM-dd HH:mm:ss"));
            po.setWorkerId(records.getJSONObject(i).getInteger("workerId"));
            po.setWorkerName(records.getJSONObject(i).getString("workerName"));
            po.setWorkerNo(records.getJSONObject(i).getString("workerNo"));
            Long id=query.getAttendById(records.getJSONObject(i).getInteger("attendRecId"));
            if (id!=null) {
			 po.setId(id);
           }
            dao.save(po);
		}
    }

}
