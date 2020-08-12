package com.guolaiwan.app.haikang.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;

@Component
@EnableScheduling
public class HaokangScheduledTask {
	private final static String IP="";
	private final static String RENLIAN="/api/aiapplication/v1/face/statisticsTotalNumByTime";
	private final static String CAR="/api/aiapplication/v1/vehicle/queryStatisticsVehiclesByGroupWithPage";
	/**
	 * 获取人脸抓拍数量，每天一点执行一次
	 */
	@Scheduled(cron = "0 0 1 * * ? ")
	public void getFaces() {
		int Number=0;
		HashMap<String, Object> postMap=new HashMap<String, Object>();
		//设备Id（多个Id用“,”分割,最多传2000个,TOTAL表示统计某一时段所有相机的抓拍总数）
		postMap.put("cameraIndexCodes", "TOTAL");
		//目标类型(0-抓拍统计，1-高危人员，2-新面孔)
		postMap.put("humanType", 0);
		//统计类型（0-日报表，1-月报表，2-年报表）
		postMap.put("statisticsType", 0);
		//查询的起始日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		postMap.put("beginTime", getISO8601Timestamp(getYesterday(new Date())));
		//查询的终止日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		postMap.put("stopTime", getISO8601Timestamp(new Date()));
		//查询结果的页码数(不能小于1，cameraIndexcodes为TOTAL时不生效)
		postMap.put("pageNo", 1);
		//查询结果每页的数量(不能小于1，cameraIndexcodes为TOTAL时不生效)
		postMap.put("pageSize", 100);
		String response=HttpUtil.post(IP+RENLIAN,postMap);
		if (response!=null&&!response.equals("")) {
			JSONObject jsonObject=new JSONObject(response);
			Number=jsonObject.getJSONObject("data").getInt("Number");
		}
		/**
		 * 提交NUMBER
		 */
	}
	/**
	 * 获取车辆抓拍数量，每天一点执行一次
	 */
	@Scheduled(cron = "0 0 1 * * ? ")
	public void getCars() {
		int Number=0;
		HashMap<String, Object> postMap=new HashMap<String, Object>();
		//查询结果的页码数(不能小于1，cameraIndexcodes为TOTAL时不生效)
		postMap.put("pageNo", 1);
		//查询结果每页的数量(不能小于1，cameraIndexcodes为TOTAL时不生效)
		postMap.put("pageSize", 100);
		//查询的起始日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		postMap.put("beginTime", getISO8601Timestamp(getYesterday(new Date())));
		//查询的终止日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		postMap.put("endTime", getISO8601Timestamp(new Date()));
		//默认为true
		postMap.put("nonEmptyRows",true);
		//分组类型（cross-按卡口，plate_location-按车牌归属地，vehicle_type-按车辆类型，vehicle_brand-按车辆主品牌）
		postMap.put("groupBy","cross");
		//卡口编号（多个用逗号分隔）
		postMap.put("crossingIndexCodes","12,22,32,42");//这块不知道写多少
		//组织编码（多个用逗号分隔）
		postMap.put("unitIndexCodes","53c372db1ac34e94a7cf985ac489fa9a");//这块不知道写多少
		//区间头类型（year-年报表，month-月报表，day-日报表）
		postMap.put("headerType", "day");
		//时间间隔表达式(1hour-按小时，1day-按天，1month-按月，1year-按年)
		postMap.put("interval", "1day");
		String response=HttpUtil.post(IP+CAR,postMap);
		if (response!=null&&!response.equals("")) {
			JSONObject jsonObject=new JSONObject(response);
			Number=jsonObject.getJSONObject("data").getInt("total");
		}
		/**
		 * 提交NUMBER
		 */
	}
	 /** 
     * 传入Data类型日期，返回字符串类型时间（ISO8601标准时间） 
     * @param date 
     * @return 
     */  
    public static String getISO8601Timestamp(Date date){  
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");  
        df.setTimeZone(tz);  
        String nowAsISO = df.format(date);  
        return nowAsISO;  
    } 
    /**
     * 获取当前时间前一天
     * @param date
     * @return
     */
    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }
}
