package com.guolaiwan.app.haikang.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.merchant.dao.CameraFaceDAO;
import com.guolaiwan.bussiness.merchant.dao.CarMessageDAO;
import com.guolaiwan.bussiness.merchant.po.CameraFacePO;
import com.guolaiwan.bussiness.merchant.po.CarMessagePO;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import pub.caterpillar.commons.util.date.DateUtil;

@Component
@EnableScheduling
public class HaokangScheduledTask {
	private final static String IP="222.223.23.226:10443";
	private final static String RENLIAN="/api/frs/v1/event/face_capture/search";
	private final static String CAR="/api/mpc/v1/events/vehicleRecord";
	
	@Autowired
	CarMessageDAO carMessageDAO;
	
	@Autowired
	CameraFaceDAO cameraFaceDAO;
	/**
	 * 获取人脸抓拍数量，每天一点执行一次
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	@Scheduled(cron = "0 0/30 * * * ?")
	public void getFaces() throws JSONException, ParseException {
		/*int Number=0;
		JSONObject jsonObject=new JSONObject();
	
		//查询的起始日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		Date newDate=new Date();
		jsonObject.put("startTime", getISO8601Timestamp(newDate));
		
		//查询的终止日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		jsonObject.put("endTime", getISO8601Timestamp(DateUtil.addDay(newDate, 1)));
		//查询结果的页码数(不能小于1，cameraIndexcodes为TOTAL时不生效)
		jsonObject.put("pageNo", 1);
		//查询结果每页的数量(不能小于1，cameraIndexcodes为TOTAL时不生效)
		jsonObject.put("pageSize", 1000);
		//String response=request(RENLIAN, jsonObject);
		String response="";
		if (response!=null&&!response.equals("")) {
			//System.out.println("----------------------"+response+"--------------------------");
		}
		
		com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(response);
		com.alibaba.fastjson.JSONObject countJson=json.getJSONObject("data");*/
		/**
		 * 提交NUMBER
		 */
		//此处录入人脸数据
		//先判断是不是已经录入过今天数据（一天可能要不断的查询，因为要显示一天的实时数据）
		/*try {
			List<CameraFacePO> cameraFacePOs = cameraFaceDAO.findTodydata(newDate);
			if(cameraFacePOs!=null&&!cameraFacePOs.isEmpty()){
				//设置取到的数量
				cameraFacePOs.get(0).setCount(countJson.getIntValue("total"));
				cameraFacePOs.get(0).setCountDate(new Date());
				cameraFaceDAO.update(cameraFacePOs.get(0));
			}else{
				CameraFacePO cameraFacePO=new CameraFacePO();
				cameraFacePO.setCount(countJson.getIntValue("total"));
				cameraFacePO.setCountDate(new Date());
				cameraFaceDAO.save(cameraFacePO);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	/**
	 * 获取车辆抓拍数量，每天一点执行一次
	 */
	@Scheduled(cron = "0 0/30 * * * ?")
	//@Scheduled(cron = "0 */1 * * * ?")
	public void getCars() {
		/*int Number=0;
		JSONObject jsonObject=new JSONObject();
		//查询结果的页码数(不能小于1，cameraIndexcodes为TOTAL时不生效)
		jsonObject.put("pageNo", 1);
		//查询结果每页的数量(不能小于1，cameraIndexcodes为TOTAL时不生效)
		jsonObject.put("pageSize", 1000);
		//查询的起始日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		jsonObject.put("beginTime", getISO8601Timestamp(getYesterday(new Date())));
		//查询的终止日期（IOS8601格式yyyy-MM-dd’T’HH:mm:ss.SSSzzz）
		jsonObject.put("endTime", getISO8601Timestamp(new Date()));
		//String response=request(CAR, jsonObject);
		String response="";*/
		/**
		 * 提交NUMBER
		 */
		
		//此处录入车辆数据
		//先判断是不是已经录入过今天数据（一天可能要不断的查询，因为要显示一天的实时数据）
	
		/*com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(response);
		com.alibaba.fastjson.JSONObject countJson=json.getJSONObject("data");
		com.alibaba.fastjson.JSONArray array=countJson.getJSONArray("list");
		
		try {
			for (Object object : array) {
				com.alibaba.fastjson.JSONObject carJson=com.alibaba.fastjson.JSONObject.parseObject(object.toString());
			    
			    List<CarMessagePO> carMessagePOs=carMessageDAO.findByField("sigleNo", carJson.getString("uuid"));
			    if(carMessagePOs!=null&&!carMessagePOs.isEmpty()){
			    	
			    }else{
			    	CarMessagePO carMessagePO=new CarMessagePO();
			    	carMessagePO.setRegion(getAddressByCarCard(carJson.getString("plateNo").substring(0,1)));
			    	carMessagePO.setType(carJson.getString("vehicleType").equals("largeBus")?1:0);
			    	carMessagePO.setSigleNo(carJson.getString("uuid"));
			    	carMessageDAO.save(carMessagePO);
			    }
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	private  String getAddressByCarCard(String no) {
		String licensePlate[] = { "冀", "晋", "蒙", "辽", "黑", "吉", "苏", "沪", "浙", "皖", "闽", "赣", "鲁", "豫", "鄂", "湘", "粤",
				"桂", "琼", "渝", "川", "黔", "滇", " 陕", "甘", "青", "宁", "新" };
		
		String region[] = { "河北", "山西", "内蒙古", "辽宁", "黑龙江", "吉林", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南",
				"广东", "广西", "海南", "重庆", "四川", "贵州", "云南", "陕西", "甘肃省", "宁夏", "新疆" };
		for(int i=0;i<licensePlate.length;i++){
			if(no.equalsIgnoreCase(licensePlate[i])){
				return region[i];
			}
		}
		return "未知";
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
		ArtemisConfig.appKey = "27518149";  // 秘钥appkey
		ArtemisConfig.appSecret = "X86sJNFaxNogVTWDVm0D";// 秘钥appSecret
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
		//String result = ArtemisHttpUtil.doPostStringArtemis(pathMap, body, null, null, contentType , null);
		//return result;
		return "";
	}

}
