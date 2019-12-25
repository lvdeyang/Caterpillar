package com.sumavision.tetris.outlink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.attend.AttendDao;
import com.sumavision.tetris.attend.AttendPo;
import com.sumavision.tetris.attend.AttendQuery;

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
     */
	 @Scheduled(cron = "0 0 0 * * *")
    void getAllRec(){
        JSONObject params=new JSONObject();
        String response=HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpGet,myUrl.HOST+myUrl.GETALLREC,null,null);
        JSONObject resultJsonObject=JSONObject.parseObject(response);
        JSONArray records=resultJsonObject.getJSONObject("result").getJSONArray("records");
        for (int i = 0; i < records.size(); i++) {
        	AttendPo po=new AttendPo();
            po.setAddTime(records.getJSONObject(i).getString("addTime"));
            po.setAttendRecId(records.getJSONObject(i).getInteger("attendRecId"));
            po.setCompanyId(records.getJSONObject(i).getInteger("companyId"));
            po.setCompanyName(records.getJSONObject(i).getString("companyName"));
            po.setDutyDay(records.getJSONObject(i).getString("dutyDay"));
            po.setDeviceId(records.getJSONObject(i).getInteger("deviceId"));
            po.setGroupId(records.getJSONObject(i).getInteger("groupId"));
            po.setOffDutuState(records.getJSONObject(i).getInteger("offDutuState"));
            po.setOffDutyTime(records.getJSONObject(i).getString("offDutyTime"));
            po.setOnDutyState(records.getJSONObject(i).getInteger("onDutyState"));
            po.setOnDutyTime(records.getJSONObject(i).getString("onDutyTime"));
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
