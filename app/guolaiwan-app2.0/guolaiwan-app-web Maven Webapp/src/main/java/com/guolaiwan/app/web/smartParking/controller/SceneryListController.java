package com.guolaiwan.app.web.smartParking.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.ast.Var;
import org.bytedeco.javacpp.RealSense.timestamp_callback;
import org.bytedeco.javacpp.opencv_core.RefOrVoid.type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Name;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.publicnum.vo.RoomVo;
import com.guolaiwan.app.web.smartParking.vo.AttractionsVo;
import com.guolaiwan.app.web.smartParking.vo.CarPositionVo;
import com.guolaiwan.app.web.smartParking.vo.OrderVo;
import com.guolaiwan.app.web.smartParking.vo.ParkingPositionVo;
import com.guolaiwan.app.web.smartParking.vo.VehiceVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.weixin.WxConfig;
import com.guolaiwan.app.web.weixin.YuebaWxPayConstants;
import com.guolaiwan.app.web.weixin.YuebaWxUtil;
import com.guolaiwan.bussiness.Parking.dao.AttractionsDao;
import com.guolaiwan.bussiness.Parking.dao.CarPositionDao;
import com.guolaiwan.bussiness.Parking.dao.MoneyDao;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.MoneyPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.weixin.constants.WXContants;
/**
 * 选择景点页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/vice")
public class SceneryListController  extends WebBaseControll{


	@Autowired
	private  VehicleDao par_king;
	
	@Autowired
	private  AttractionsDao Attra_ctions;

	@Autowired
	private CarPositionDao  Car_Position;
	
	@Autowired
	private OrderDao  Order;
	
	@Autowired
	private ParkingPositionDao  Parking_Position;
	
	@Autowired
	private SysConfigDAO conn_sysConfig;
	
	@Autowired
	private MoneyDao Money;
	
	@Autowired
	private MerchantDAO Merchant;


	/*****************************************************************************************************/
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	private static double EARTH_RADIUS = 6378.137;

	/**
	 * 查询   景区信息 
	 * 景区名称 图片  地址  总车位  剩余车位    室内室外  充电柱
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/infor", method = RequestMethod.POST)
	public Map<String, Object> getScenicSpot(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String latitude = pageObject.getString("latitude"); //用户当前经度
		String longitude = pageObject.getString("longitude"); //用户当前纬度
		String merchantId = pageObject.getString("merchantId"); //根据商户排序
		List<AttractionsParkingPO> attractions =	Attra_ctions.getInformation(0, 100);
		List<AttractionsVo> _merchants =null;
		if (merchantId != null && merchantId != "") {
		  MerchantPO  MerchantPO = Merchant.getMerchantById(Long.parseLong(merchantId)).get(0);
		  if (MerchantPO != null && MerchantPO!= null ) {
			String  MerchantLongitude =    MerchantPO.getShopLongitude();//商户经度
		   	String  MerchantLatitude =  MerchantPO.getShopLatitude(); //商户纬度
		   	_merchants = AttractionsVo.getConverter(AttractionsVo.class).convert(attractions,
					AttractionsVo.class);
		   	for (AttractionsVo attractionsVo : _merchants) {
		   		String[]   District =  attractionsVo.getParkingDistrict().split(",");
		   		double  dist  =     getDistance(Double.parseDouble(District[1]),Double.parseDouble(District[0]),Double.parseDouble(MerchantLatitude),Double.parseDouble(MerchantLongitude));
		   		attractionsVo.setParkingDistrict(dist+"");
		   	/*   Collections.sort(_merchants);*/
		   		//自定义排序1
		   		
		   	}
		   	
		   	Collections.sort(_merchants, new Comparator<AttractionsVo>() {
				@Override
				public int compare(AttractionsVo o1, AttractionsVo o2) {
					// TODO Auto-generated method stub
					return (int) (Double.parseDouble(o1.getParkingDistrict())-Double.parseDouble(o2.getParkingDistrict()));

				}
	   		});
		   	for (AttractionsVo attractionsVo : _merchants) {
				System.out.println(attractionsVo.getParkingName()+" - " +attractionsVo.getParkingDistrict());
			}
		  }
		}
		return success(_merchants);
	}
	
	
	
	/**
	 * 通过经纬度获取距离(单位：米)
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return 距离
      **/
	public static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s;
	}


	/*****************************************************************************************************/
	
	
	
	
	/**
	 * 查询    停车结束时间
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public Map<String, Object> SeleFinish(HttpServletRequest request) throws Exception {
		String vehicle = null; // 车牌
        Long userId = 	(Long) request.getSession().getAttribute("userId");
		List<String> listHasCup=new ArrayList<String>();
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		List<String> list  = new ArrayList<String>();
		list.add("PAYSUCCESS");
		list.add("PARKING");
		List<OrderPO> userByid = Order.getOrderform(userId,list,vehicle);
	    for (OrderPO orderPO : userByid) {
	    	listHasCup.add(orderPO.getDueTime());
		}
		return success(listHasCup);
	}
	
	






	/*****************************************************************************************************/



	/**
	 * 查询   景区条例
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/regu", method = RequestMethod.POST)
	public Map<String, Object> getRegulations(HttpServletRequest request) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid");   //
		AttractionsParkingPO attractions = Attra_ctions.getUid(uid);
			dataMap.put("regulations", attractions.getRegulations());
			dataMap.put("parkingName", attractions.getParkingName());
		return success(dataMap);
	}




	/*****************************************************************************************************/




	/**
	 * 根据用户搜索     模糊 查询订单
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Map<String, Object> FuzzyQuery(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		if (pageObject == null) {
			return ERROR("未获取");
		}
		String name = pageObject.getString("input"); 
		List<AttractionsParkingPO> userName =  Attra_ctions.getQuery(name);
		List<AttractionsVo>   _merchants = AttractionsVo.getConverter(AttractionsVo.class).convert(userName,
				AttractionsVo.class);
		return success(_merchants);
	}
	
	
	/*****************************************************************************************************/

	
	
	
	/**
	 * 获取 用户车型      查询订单 景区名称 区 车位 
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/vehicleorder", method = RequestMethod.POST)
	public Map<String, Object> seleOrder(HttpServletRequest request) throws Exception {
		String vehicle = null; // 车牌
		String type = null; // 车牌
        Long userId = 	(Long) request.getSession().getAttribute("userId");
		List<String> listHasCup=new ArrayList<String>();
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
			type =  vehiclePO.getType();
		}
		List<String> list  = new ArrayList<String>();
		list.add("PAYSUCCESS");
		list.add("PARKING");
		List<OrderPO> userByid = Order.getOrderform(userId,list,vehicle);
		List<VehiceVo>  arrList  = new ArrayList<VehiceVo>();
	    for (OrderPO order : userByid) {
	    	VehiceVo  vehice = new VehiceVo();
	    	vehice.setVehicle(vehicle);
	    	vehice.setType(type);
	    	vehice.setParkingn(order.getParkingName());
	    	vehice.setNumber(order.getParkingNumber());
	    	vehice.setDistrict(order.getParkingDistrict());
	    	vehice.setDueTime(order.getDueTime());
	    	arrList.add(vehice);
		}
		return success(arrList);
	}








	/********************************************--------------------------------------------------------*********************************************************/


	/**  ***************************             选车位                     ***************************     **/




	/**
	 * 根据景区id 查询 停车场层数
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/Information", method = RequestMethod.POST)
	public Map<String, Object> ParkingInformation(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		List<String> listHasCup=new ArrayList<String>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid");  
		List<CarPositionPO> userName =  Car_Position.getInformation(uid);
		for (CarPositionPO carPositionPO : userName) {
			listHasCup.add(carPositionPO.getNumber());
		}
		List<String> listNoCup=new ArrayList<String>(new HashSet<String>(listHasCup));

		return success(listNoCup);
	}





	/*****************************************************************************************************/




	/**
	 * 根据景区id  查询 车位地图
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/Informap", method = RequestMethod.POST)
	public Map<String, Object> ParkingInfor(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid");  
		String ceng = pageObject.getString("ceng");
		String qu = pageObject.getString("qu");
		List<CarPositionPO> userName =  Car_Position.getInfor(uid,ceng,qu);
		for (CarPositionPO carPositionPO : userName) {
			dataMap.put("long", carPositionPO.getLongitude());
			dataMap.put("id", carPositionPO.getId());
		}
		return success(dataMap);
	}








	/*****************************************************************************************************/




	/**
	 * 根据景区id  查询 车位信息                               ///////////////////////////////////////////////////////////////////
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/truck", method = RequestMethod.POST)
	public Map<String, Object> ParkingTruck(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid");
		List<ParkingPositionPO> userName =  Parking_Position.getTruck(uid);
		List<ParkingPositionVo> _merchants = ParkingPositionVo.getConverter(ParkingPositionVo.class).convert(userName,
				ParkingPositionVo.class);
		return success(_merchants);
	}








	/*****************************************************************************************************/


	/**
	 * 根据景区id层数 查询区
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/tier", method = RequestMethod.POST)
	public Map<String, Object> ParkingTier(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		List<String> listHasCup=new ArrayList<String>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid");   
		String Tier = pageObject.getString("input"); 
		List<CarPositionPO> userName =  Car_Position.getTier(uid,Tier);
		for (CarPositionPO carPositionPO : userName) {
			listHasCup.add(carPositionPO.getDistrict());
		}
		return success(listHasCup);
	}



	/*****************************************************************************************************/


	/**
	 * 根据景区id 查询  营业时间
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/hours", method = RequestMethod.POST)
	public Map<String, Object> BusinessHours(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("id");   
		List<AttractionsParkingPO> userName =  Attra_ctions.getBusinessHours(uid);
		for (AttractionsParkingPO attractionsParkingPO : userName) {
			dataMap.put("stoppingTime", attractionsParkingPO.getStoppingTime());
			dataMap.put("phone", attractionsParkingPO.getPhone());
		}
		return success(dataMap);
	}






	/*****************************************************************************************************/




	/** 根据用户id 景区id查询订单状态
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public Map<String, Object> SeleBusiness(HttpServletRequest request) throws Exception {
		String vehicle = null;
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		List<String> listHasCup=new ArrayList<String>();
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		Long uid = pageObject.getLong("uid");   
		List<OrderPO> userByid = Order.getOrderform(userId,uid,vehicle);
		for (OrderPO orderPO : userByid) {
			listHasCup.add(orderPO.getOrderStatus());
		}
		return success(listHasCup);
	}






	/*****************************************************************************************************/






	/** 根据用户信息保存信息 订单界面显示用
	 * @return
	 **/
	//TODO
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public  Map<String, Object> Business(HttpServletRequest request) throws Exception {
		String vehicle = null;
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid");   // 景区id
		String ceng = pageObject.getString("ceng");  
		String qu = pageObject.getString("qu"); 
		int  stall =  Integer.parseInt(pageObject.getString("stall")); // 车位 
		AttractionsParkingPO attractions = Attra_ctions.getUid(uid);
		String name = null;
		//停车场时间
		String time = null;
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
			name   =  	attractions.getParkingName();
			time  =   attractions.getStoppingTime();
			OrderPO odp = new OrderPO();
			odp.setOrderId(userId);
			odp.setParkingLayer(ceng);
			odp.setParkingName(name);
			odp.setTime(time);
			odp.setParkingDistrict(qu);
			odp.setParkingNumber(stall);
			odp.setAttractionsId(uid);
			odp.setParkingCost(0);
			odp.setPlatenumber(vehicle);
			odp.setOrderStatus("NOTPAY");
			Order.save(odp);
			dataMap.put("uid", odp.getId());
		return success(dataMap);
	}


	/*****************************************************************************************************/

	/** 根据用户id查询订单
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/seleorder", method = RequestMethod.POST)
	public Map<String, Object> SeleOrder(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid");   // 景区id
		List<OrderPO> userByid = Order.getOrderform(userId,uid);
		for (OrderPO orderPO : userByid) {
			dataMap.put("id", orderPO.getId());
			dataMap.put("cheng", orderPO.getParkingLayer());
			dataMap.put("name", orderPO.getParkingName());
			dataMap.put("qu",orderPO.getParkingDistrict() );
			dataMap.put("number",orderPO.getParkingNumber());
			dataMap.put("time",orderPO.getTime());
			/*dataMap.put("",orderPO );
			dataMap.put("",orderPO );*/
		}
		return success(dataMap);
	}
	
	/*****************************************************************************************************/
	
	/** 根据用户id 车位 查询车位情况
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/number", method = RequestMethod.POST)
	public Map<String, Object> SeleNumber(HttpServletRequest request) throws Exception {
		Long id = null;
		String param = getRequestJson(request);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid"); 
		String ceng = pageObject.getString("cheng");
		String qu = pageObject.getString("qu");
		int number = pageObject.getInteger("number"); 
		List<CarPositionPO> userName =  Car_Position.getInfor(uid,ceng,qu);
		for (CarPositionPO carPositionPO : userName) {
		id = carPositionPO.getId();
		}
		ParkingPositionPO userNam =  Parking_Position.getNumber(id,number);// 车位   用户id
	    	dataMap.put("condition",userNam.getUseCondition());
		return success(dataMap);
	}






	/*****************************************************************************************************/

	/**
	 * 根据景区id 车型 区数  查询停车费用 每小时
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/selecost", method = RequestMethod.POST)
	public Map<String, Object> SeleCost(HttpServletRequest request) throws Exception {
		String vehicle = null;
		String type = null;
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid");   
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			type =  vehiclePO.getType();
			vehicle =  vehiclePO.getNumber();
		}
		String district = null;
		String parkingLayer = null;
		List<OrderPO> userByid = Order.getOrderform(userId,uid,vehicle);
		for (OrderPO order : userByid) {
			district = 	order.getParkingDistrict();
			parkingLayer = order.getParkingLayer();
		}
		List<MoneyPO> userName =  Money.getMoney(uid,type,district,parkingLayer);
		for (MoneyPO moneyPO : userName) {
			dataMap.put("cost", moneyPO.getMoney());
		}
		return success(dataMap);
	}


	/*****************************************************************************************************/
	/**
	 * 根据景区id 车型 层数  区数  查询停车费用 每小时
	 * 续费
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/renew", method = RequestMethod.POST)
	public Map<String, Object> RenewCost(HttpServletRequest request) throws Exception {
		String type = null; // 车型
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("attid");  // 景区id 
		String district = pageObject.getString("District");  // 区
		String parkingLayer = pageObject.getString("Layer"); // 层  
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			type =  vehiclePO.getType();
		}
		List<MoneyPO> userName =  Money.getMoney(uid,type,district,parkingLayer);
		for (MoneyPO moneyPO : userName) {
			dataMap.put("cost", moneyPO.getMoney());
		}
		return success(dataMap);
	}
	
	
	/*****************************************************************************************************/
	/**
	 *   增加订单信息
	 * @return
	 **/
	//TODO
	@ResponseBody
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public Object prevPay(HttpServletRequest request) throws Exception {
		String district = null; // 区
		String parkingLayer = null; // 层
		int parkingNumber = 0; // 车位编号
		long id =  0 ;
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String sDate = pageObject.getString("sDate");  
		Long uid = pageObject.getLong("uid"); 
		String  date   =   sDate.replace('T',' ');  //入场时间
		String eDate = pageObject.getString("eDate");
		String vehicle = pageObject.getString("mark");
		String  edate   =   eDate.replace('T',' ');  //离场时间
		String inner =  pageObject.getString("inner");  //  停车时间
		String parkingCost = pageObject.getString("Text"); // 费用
		Long  orderid = pageObject.getLong("orderid"); // 订单id
		double  money  =   Double.parseDouble(parkingCost);      
		double  duration  =   Double.parseDouble(inner);
		OrderPO userByid = Order.getform(orderid,userId,uid,vehicle);
		userByid.setBookingTime(date+":00");
		userByid.setDueTime(edate+":00");
		userByid.setParkingCost(money);
		userByid.setStoppingTime(duration);
		userByid.setPlatenumber(vehicle);
		Order.saveOrUpdate(userByid);
		/*	parkingNumber = order.getParkingNumber();
			parkingLayer = 	order.getParkingLayer();
			district = 	order.getParkingDistrict();*/
			
	/*	List<CarPositionPO> userName =  Car_Position.getAmend(uid,parkingLayer,district);
		for (CarPositionPO carPositionPO : userName) {
		id = carPositionPO.getId();
		}
		
		List<ParkingPositionPO> getTruck  =  Parking_Position.getNumber(id,parkingNumber);
		for (ParkingPositionPO parkingPositionPO : getTruck) {
		 	parkingPositionPO.setUseCondition(1);
		 	Parking_Position.saveOrUpdate(parkingPositionPO);
		}*/
		return "";
	}



	/*****************************************************************************************************/


	/**
	 * 查询订单信息
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/selePay", method = RequestMethod.POST)
	public Map<String, Object> SelePrevPay(HttpServletRequest request) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long uid = pageObject.getLong("uid"); 
		String vehicle = pageObject.getString("vehicle"); 
		List<String> relaIds = new ArrayList<String>();
		relaIds.add("PAYSUCCESS");
		relaIds.add("PARKING");
		relaIds.add("PAST");
		relaIds.add("REFUNDING");
		relaIds.add("REFUNDED");
		OrderPO OrderInfor = Order.getOrderform(userId,uid,relaIds,vehicle);
			dataMap.put("parkingName",OrderInfor.getParkingName());
			dataMap.put("orderStatus",OrderInfor.getOrderStatus());
			dataMap.put("parkingLayer",OrderInfor.getParkingLayer());
			dataMap.put("parkingDistrict",OrderInfor.getParkingDistrict());
			dataMap.put("parkingNumber",OrderInfor.getParkingNumber());
			dataMap.put("stoppingTime",OrderInfor.getStoppingTime());
			dataMap.put("parkingCost",OrderInfor.getParkingCost());
			dataMap.put("bookingTime",OrderInfor.getBookingTime());
			dataMap.put("dueTime",OrderInfor.getDueTime());
		return success(dataMap);
	}
	
	
	/*****************************************************************************************************/
	

	/**
	 * 增加 退款理由
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public Map<String, Object> Refund(HttpServletRequest request) throws Exception {
		String vehicle = null;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String refund = pageObject.getString("ref");   
		Long uid = pageObject.getLong("uid"); 
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		List<String> relaIds = new ArrayList<String>();
		relaIds.add("PAYSUCCESS");
		OrderPO OrderInfor = Order.getOrderform(userId,uid,relaIds,vehicle);
		OrderInfor.setRefund(refund);
		OrderInfor.setOrderStatus("REFUNDING");
		Order.saveOrUpdate(OrderInfor);
		dataMap.put("id", OrderInfor.getId());
		return success(dataMap);
	}




	/*****************************************************************************************************/

	/**
	 *  获取 二维码
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/long", method = RequestMethod.POST)
	public Map<String, Object> SelePre(HttpServletRequest request) throws Exception {
		String vehicle = null;
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long attid = pageObject.getLong("attid");  
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		List<String> relaIds = new ArrayList<String>();
		relaIds.add("PAYSUCCESS");
		relaIds.add("PARKING");
		relaIds.add("PAST");
		relaIds.add("REFUNDING");
		relaIds.add("REFUNDED");
		OrderPO Orderinfo = Order.getOrderform(userId,attid,relaIds,vehicle);
		dataMap.put("img", conn_sysConfig.getSysConfig().getWebUrl()+Orderinfo.getPath());
		dataMap.put("number", Orderinfo.getId());
		return success(dataMap);
	}





	/*****************************************************************************************************/


	
	

	/** 根据用户id 查询 订单
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/allmessage", method = RequestMethod.POST)
	public Map<String, Object> SeleMessage(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String Headimg = pageObject.getString("Headimg");   
		List<String> listHasCup=new ArrayList<String>();
		List<OrderPO> userByid = Order.getOrderform(userId,"PAYSUCCESS",Headimg);
		for (OrderPO orderPO : userByid) {
			AttractionsParkingPO userName =  Attra_ctions.getUid(orderPO.getAttractionsId());
				 listHasCup.add(userName.getParkingName());
				 listHasCup.add(userName.getParkingDistrict());
				 listHasCup.add(userName.getAddress());
		}
		return success(listHasCup);
	}






	/*****************************************************************************************************/
	
	/** 根据用户id 正在停车      查询 订单 信息
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/isparking", method = RequestMethod.POST)
	public Map<String, Object> IsParking(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String vehicle = null;
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		dataMap.put("vehicle", vehicle);
		List<OrderPO> userByid = Order.getOrderform(userId,"PARKING",vehicle);
		for (OrderPO orderPO : userByid) {
			dataMap.put("name", orderPO.getPlatenumber());
			dataMap.put("number", orderPO.getParkingNumber());
			dataMap.put("cost", orderPO.getParkingCost());
			dataMap.put("over", orderPO.getOverTimeMoney());
			dataMap.put("time", orderPO.getBookingTime());
			dataMap.put("parkingName", orderPO.getParkingName());
			dataMap.put("uid", orderPO.getAttractionsId());
			dataMap.put("parkingLayer", orderPO.getParkingLayer());
			dataMap.put("parkingDistrict", orderPO.getParkingDistrict());
			dataMap.put("id", orderPO.getId());
		}
		return success(dataMap);
	}
	
	
	
	/*****************************************************************************************************/
	
	/** 根据用户id  续费   车场名称   查询  信息
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/vehiclename", method = RequestMethod.POST)
	public Map<String, Object> vehiclename(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String Name = pageObject.getString("tion");   
		String vehicle = null;
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		dataMap.put("vehicle", vehicle);
		List<String> relaIds = new ArrayList<String>();
		relaIds.add("PAYSUCCESS");
		relaIds.add("PARKING");
		List<OrderPO> userByid = Order.getOrderName(userId,relaIds,vehicle,Name);
		for (OrderPO orderPO : userByid) {
			dataMap.put("name", orderPO.getPlatenumber());
			dataMap.put("number", orderPO.getParkingNumber());
			dataMap.put("cost", orderPO.getParkingCost());
			dataMap.put("over", orderPO.getOverTimeMoney());
			dataMap.put("time", orderPO.getBookingTime());
			dataMap.put("parkingName", orderPO.getParkingName());
			dataMap.put("uid", orderPO.getAttractionsId());
			dataMap.put("parkingLayer", orderPO.getParkingLayer());
			dataMap.put("parkingDistrict", orderPO.getParkingDistrict());
			dataMap.put("id", orderPO.getId());
		}
		return success(dataMap);
	}
	
	
	
	
	
	
	/*****************************************************************************************************/
	/** 根据用户id 续费      查询 订单 信息
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/feeparking", method = RequestMethod.POST)
	public Map<String, Object> feeParking(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String vehicle = null;
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		dataMap.put("vehicle", vehicle);
		List<String> relaIds = new ArrayList<String>();
		relaIds.add("PAYSUCCESS");
		relaIds.add("PARKING");
		List<OrderPO> userByid = Order.getOrderform(userId,relaIds,vehicle);
		for (OrderPO orderPO : userByid) {
			dataMap.put("name", orderPO.getPlatenumber());
			dataMap.put("number", orderPO.getParkingNumber());
			dataMap.put("cost", orderPO.getParkingCost());
			dataMap.put("over", orderPO.getOverTimeMoney());
			dataMap.put("time", orderPO.getBookingTime());
			dataMap.put("parkingName", orderPO.getParkingName());
			dataMap.put("uid", orderPO.getAttractionsId());
			dataMap.put("parkingLayer", orderPO.getParkingLayer());
			dataMap.put("parkingDistrict", orderPO.getParkingDistrict());
			dataMap.put("id", orderPO.getId());
		}
		return success(dataMap);
	}
	
	
	
	
	
	
	/*****************************************************************************************************/
	/** 根据用户id  状态   正在停车   计算 超时时间 钱数
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/seleparking", method = RequestMethod.POST)
	public Map<String, Object> SeleIsparkingMoney(HttpServletRequest request) throws Exception {
		String vehicle = null; //车牌
		String type = null; //车型
		double   parkingmoney = 0;// 钱
	    String	Time = null; //  到期时间
	    long	attactionid = 0; // 景区id
	    String district = null; // 区
	    String parkingLayer = null; // 层
	    int fineMultiple  = 0 ; //罚款倍数
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
			type = vehiclePO.getType();
		}
		OrderPO userByid = Order.getOrder(userId,"PARKING",vehicle);
		if(userByid != null){
			Time = userByid.getDueTime();
			attactionid = userByid.getAttractionsId();
			district = userByid.getParkingDistrict();
			parkingLayer = userByid.getParkingLayer();
		}
		AttractionsParkingPO attractions = Attra_ctions.getUid(attactionid);
		fineMultiple = attractions.getFineMultiple();
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try
	    {
		    String aString = 	df.format(new Date());
		    Date d1 = df.parse(aString);
		    Date d2 = df.parse(Time);
		    long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
		    Long time = diff/1000;
		    if (time < 0 ) { // 超时
		    	Long overtime = Math.abs(time)/60L;// 转换分钟
		    	int  leng =   (int) (overtime/30); // 转换 几个半个小时
		    	if (overtime % 30 != 0 ) {
		    		leng = leng +1;
				}
		    	List<MoneyPO> userName =  Money.getMoney(attactionid,type,district,parkingLayer);
				for (MoneyPO moneyPO : userName) {
			     parkingmoney  = 	 moneyPO.getMoney();
				}
			    double  money =   ((parkingmoney/2.0)*fineMultiple)*leng;
			    userByid.setOverTimeMoney(money);
				Order.saveOrUpdate(userByid);
 		    	dataMap.put("time", time);
		    	dataMap.put("money", money);
		    	dataMap.put("leng", leng);
		    	dataMap.put("id",  userByid.getId());
			}else {// 到计时
				userByid.setOverTimeMoney(0.0);
				Order.saveOrUpdate(userByid);
				dataMap.put("time", time);
				dataMap.put("id",  userByid.getId());
			}
	    }
	    catch (Exception e){
	    
	    }
		return success(dataMap);
	}
	
	
	
	
	
	
	/*****************************************************************************************************/
	/*****************************************************************************************************/
	/** 根据用户id  状态   正在停车   计算 超时时间 钱数
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/seleparkingMoney", method = RequestMethod.POST)
	public Map<String, Object> SeleparkingMoney(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String type = null; //车型
		double   parkingmoney = 0;// 钱
		String	Time = null; //  到期时间
		long	attactionid = 0; // 景区id
		String district = null; // 区
		String parkingLayer = null; // 层
		int fineMultiple  = 0 ; //罚款倍数
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long id = pageObject.getLong("id");  
		List<VehiclePO> userBy = par_king.getNumber(userId); 
		for (VehiclePO vehiclePO : userBy) {
			type = vehiclePO.getType(); // 获取车型
		}
		OrderPO userByid = Order.getOrderform(id);
		if(userByid != null){
			Time = userByid.getDueTime();
			attactionid = userByid.getAttractionsId();
			district = userByid.getParkingDistrict();
			parkingLayer = userByid.getParkingLayer();
		}
		AttractionsParkingPO attractions = Attra_ctions.getUid(attactionid);
		fineMultiple = attractions.getFineMultiple();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			String aString = 	df.format(new Date());
			Date d1 = df.parse(aString);
			Date d2 = df.parse(Time);
			long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
			Long time = diff/1000;
			if (time < 0 ) { // 超时
				Long overtime = Math.abs(time)/60L;// 转换分钟
				int  leng =   (int) (overtime/30); // 转换 几个半个小时
				if (overtime % 30 != 0 ) {
					leng = leng +1;
				}
				List<MoneyPO> userName =  Money.getMoney(attactionid,type,district,parkingLayer);
				for (MoneyPO moneyPO : userName) {
					parkingmoney  = 	 moneyPO.getMoney();
				}
				double  money =   ((parkingmoney/2.0)*fineMultiple)*leng;
				double  ismoney =  userByid.getOverTimeMoney();
				userByid.setOverTimeMoney(ismoney+money);	
				double Overtime = (leng/2.0);
				userByid.setOverTime(Overtime);
				Order.saveOrUpdate(userByid);
				dataMap.put("time", time);
				dataMap.put("money", money);
				dataMap.put("leng", leng);
			}else {// 到计时
				userByid.setOverTimeMoney(0.0);
				Order.saveOrUpdate(userByid);
				dataMap.put("time", time);
			}
		}
		catch (Exception e){
			
		}
		return success(dataMap);
	}
	
	
	
	
	
	
	/*****************************************************************************************************/

	
	
	/** 计算 用户时间    续费 
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/addmoney", method = RequestMethod.POST)
	public Map<String, Object> addMoney(HttpServletRequest request) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		String vehicle = null;
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		int length = pageObject.getInteger("length"); // 欠费 时长 *30
		int sele = pageObject.getInteger("sele");  // 用户续费时长
		int moeny = pageObject.getInteger("money");  // 当前钱数
		Long attid = pageObject.getLong("attid"); 
		System.out.println("-----------------------------------------------------------------------------------------------------------------" +attid);
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		int  time  = (int) ((length*30)+((sele/0.5)*30)); //计算时长
		List<String> relaIds = new ArrayList<String>();
			relaIds.add("PAYSUCCESS");
			relaIds.add("PARKING");
		OrderPO userByid = Order.getOrderform(userId,attid,relaIds,vehicle); 
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +userByid.getId());
		String getDueTime =   userByid.getDueTime(); //到期时间
	    Date date  =  	DateUtil.parse(getDueTime);
	    Date addMinute = DateUtil.addMinute(date,time);
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String format    =  df.format(addMinute);  // 到期时间
		double dou =   time/60.0;
		userByid.setDueTime(format); // 到期时间
		userByid.setStoppingTime(dou); // 停车总时间     
		userByid.setParkingCost(moeny);   
		String ydNO = ydNoCode(userByid.getId()+""); // 生成二维码
		userByid.setPath(ydNO);
		Order.saveOrUpdate(userByid);
		return success(dataMap);
	}
	
	
	
/*****************************************************************************************************/

	
	
	/** 订单 信息 
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/theorder", method = RequestMethod.POST)
	public Map<String, Object> TheOrder(HttpServletRequest request) throws Exception {
		String vehicle = null; //车牌
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		List<String> list  = new ArrayList<String>();
		list.add("PAYSUCCESS");
		list.add("PARKING");
		list.add("PAST");
		list.add("REFUNDING");
		list.add("REFUNDED");
		List<OrderPO> order = Order.getOrderform(userId,list,vehicle);
		List<OrderVo>   _merchants = OrderVo.getConverter(OrderVo.class).convert(order,
				OrderVo.class);
		return success( _merchants);
	}
	
	

	/*****************************************************************************************************/

	
	/** 查询 续费 停车场信息
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/reneworder", method = RequestMethod.POST)
	public Map<String, Object> renewOrder(HttpServletRequest request) throws Exception {
		String vehicle = null; //车牌
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		List<String> list  = new ArrayList<String>();
		list.add("PAYSUCCESS");
		list.add("PARKING");
		List<OrderPO> order = Order.getOrderform(userId,list,vehicle);
		List<OrderVo>   _merchants = OrderVo.getConverter(OrderVo.class).convert(order,
				OrderVo.class);
		return success( _merchants);
	}
	
	
	
	
	
	
	
	
	
	
	/*****************************************************************************************************/

	
	
	
	
	
	
	
	private String getRequestJson(HttpServletRequest request) {
		try {
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			request.getInputStream().close();
			br.close();
			return sb.toString();
		} catch (IOException e) {
			return "";
		}
	}






	/*****************************************************************************************************/




	



	//转到jsp文件
	@RequestMapping(value = "/merchant/scenic") //跳转到 选停车场页面
	public ModelAndView ScenicSpot(HttpServletRequest request,HttpSession session,String merchantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/parkingspace"); 
		if(merchantId != null && merchantId != "")mv.addObject("merchantId",merchantId);
		return mv;
	}
	//转到jsp文件
	@RequestMapping(value = "/merchant/agreemen")
	public ModelAndView ScenicAgreement(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/agreement"); 
		return mv;
	}
	//转到jsp文件
	@RequestMapping(value = "/merchant/parking")
	public ModelAndView ScenicParking(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/selectlocation"); 
		return mv;
	}

	@RequestMapping(value = "/merchant/payment")
	public ModelAndView ScenicOrderPayment(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/orderpayment"); 
		return mv;
	}
	@RequestMapping(value = "/merchant/order")
	public ModelAndView ScenicOrder(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/orderdetails"); 
		return mv;
	}
	//转到jsp文件
	@RequestMapping(value = "/merchant/cance")
	public ModelAndView CancelOrder(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/cancelorder"); 
		return mv;
	}
	//转到jsp文件
	@RequestMapping(value = "/merchant/parkings")
	public ModelAndView Parkings(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/parkings"); 
		return mv;
	}
	//转到jsp文件
	@RequestMapping(value = "/merchant/app")
	public ModelAndView Park(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/app"); 
		return mv;
	}
	//转到jsp文件
	@RequestMapping(value = "/merchant/navigation")
	public ModelAndView Spacenavigation(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/spacenavigation"); 
		return mv;
	}
	//转到jsp文件
		@RequestMapping(value = "/merchant/renewall")
		public ModelAndView Renewal(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("parking/park/renewal"); 
			return mv;
		}
		//转到jsp文件
		@RequestMapping(value = "/merchant/information")
		public ModelAndView Orderinformation(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("parking/park/orderinformation"); 
			return mv;
		}
		//转到jsp文件
		@RequestMapping(value = "/merchant/additional")
		public ModelAndView Additionalpayments(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("parking/park/additionalpayments"); 
			return mv;
		}




















































}
