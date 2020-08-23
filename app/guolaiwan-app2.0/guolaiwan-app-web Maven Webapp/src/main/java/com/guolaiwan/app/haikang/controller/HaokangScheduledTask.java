package com.guolaiwan.app.haikang.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;

@Component
@EnableScheduling
public class HaokangScheduledTask {
	private final static String IP="192.168.110.10:10443";
	private final static String RENLIAN="/api/aiapplication/v1/face/statisticsTotalNumByTime";
	private final static String CAR="/api/aiapplication/v1/vehicle/queryStatisticsVehiclesByGroupWithPage";
	/**
	 * 获取人脸抓拍数量，每天一点执行一次
	 */
	@Scheduled(cron = "*/3 * * * * ?")
	public void getFaces() {
		int Number=0;
		JSONObject jsonObject=new JSONObject();
		//设备Id（多个Id用“,”分割,最多传2000个,TOTAL表示统计某一时段所有相机的抓拍总数）
		jsonObject.put("cameraIndexCodes", "TOTAL");
		//目标类型(0-抓拍统计，1-高危人员，2-新面孔)
		jsonObject.put("humanType", 0); 
		//统计类型（0-日报表，1-月报表，2-年报表）
		jsonObject.put("statisticsType", 0);
		//查询的起始日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		jsonObject.put("beginTime", getISO8601Timestamp(getYesterday(new Date())));
		//查询的终止日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		jsonObject.put("stopTime", getISO8601Timestamp(new Date()));
		//查询结果的页码数(不能小于1，cameraIndexcodes为TOTAL时不生效)
		jsonObject.put("pageNo", 1);
		//查询结果每页的数量(不能小于1，cameraIndexcodes为TOTAL时不生效)
		jsonObject.put("pageSize", 100);
		String response=request(RENLIAN, jsonObject);
		if (response!=null&&!response.equals("")) {
			System.out.println(response);
		}
		/**
		 * 提交NUMBER
		 */
	}
	/**
	 * 获取车辆抓拍数量，每天一点执行一次
	 */
	@Scheduled(cron = "*/3 * * * * ?")
	public void getCars() {
		int Number=0;
		JSONObject jsonObject=new JSONObject();
		//查询结果的页码数(不能小于1，cameraIndexcodes为TOTAL时不生效)
		jsonObject.put("pageNo", 1);
		//查询结果每页的数量(不能小于1，cameraIndexcodes为TOTAL时不生效)
		jsonObject.put("pageSize", 100);
		//查询的起始日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		jsonObject.put("beginTime", getISO8601Timestamp(getYesterday(new Date())));
		//查询的终止日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		jsonObject.put("endTime", getISO8601Timestamp(new Date()));
		//默认为true
		jsonObject.put("nonEmptyRows",true);
		//分组类型（cross-按卡口，plate_location-按车牌归属地，vehicle_type-按车辆类型，vehicle_brand-按车辆主品牌）
		jsonObject.put("groupBy","cross");
		//卡口编号（多个用逗号分隔）
		jsonObject.put("crossingIndexCodes","12,22,32,42");//这块不知道写多少
		//组织编码（多个用逗号分隔）
		jsonObject.put("unitIndexCodes","53c372db1ac34e94a7cf985ac489fa9a");//这块不知道写多少
		//区间头类型（year-年报表，month-月报表，day-日报表）
		jsonObject.put("headerType", "day");
		//时间间隔表达式(1hour-按小时，1day-按天，1month-按月，1year-按年)
		jsonObject.put("interval", "1day");
		String response=request(CAR, jsonObject);
		if (response!=null&&!response.equals("")) {
			System.out.println(response);
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
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd'T'00:00:00.000XXX");
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
    
    
	public static String request(String path,JSONObject jsonBody) {
		ArtemisConfig.host = IP; // artemis网关服务器ip端口
		ArtemisConfig.appKey = "24209116";  // 秘钥appkey
		ArtemisConfig.appSecret = "uvPAa3jeTEEWqWYqxnxr";// 秘钥appSecret
		String body = jsonBody.toString();
		/**
		 * STEP2：设置OpenAPI接口的上下文
		 */
		final String ARTEMIS_PATH = "/artemis";

		/**
		 * STEP3：设置接口的URI地址
		 */
		final String previewURLsApi = ARTEMIS_PATH + path;
		Map<String, String> pathMap = new HashMap<String, String>(2) {
			{
				put("https://", previewURLsApi);
			}
		};
		String contentType = "application/json";
		String result = ArtemisHttpUtil.doPostStringArtemis(pathMap, body, null, null, contentType , null);
		return result;
	}

}
