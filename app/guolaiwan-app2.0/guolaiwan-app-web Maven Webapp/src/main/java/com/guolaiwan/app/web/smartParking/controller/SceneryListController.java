package com.guolaiwan.app.web.smartParking.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.publicnum.vo.RoomVo;
import com.guolaiwan.app.web.smartParking.vo.AttractionsVo;
import com.guolaiwan.app.web.smartParking.vo.CarPositionVo;
import com.guolaiwan.app.web.smartParking.vo.OrderVo;
import com.guolaiwan.app.web.smartParking.vo.ParkingPositionVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.weixin.WxConfig;
import com.guolaiwan.app.web.weixin.YuebaWxPayConstants;
import com.guolaiwan.app.web.weixin.YuebaWxUtil;
import com.guolaiwan.bussiness.Parking.dao.AttractionsDao;
import com.guolaiwan.bussiness.Parking.dao.CarPositionDao;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

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
	private  AttractionsDao Attra_ctions;

	@Autowired
	private CarPositionDao  Car_Position;
	@Autowired
	private OrderDao  Order;
	@Autowired
	private ParkingPositionDao  Parking_Position;

	

	/*****************************************************************************************************/




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
		List<AttractionsParkingPO> attractions =	Attra_ctions.getInformation(0, 20);
		List<AttractionsVo> _merchants = AttractionsVo.getConverter(AttractionsVo.class).convert(attractions,
				AttractionsVo.class);
		return success(_merchants);
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
		List<AttractionsParkingPO> attractions = Attra_ctions.getUid(uid);
		for (AttractionsParkingPO attractionsParkingPO : attractions) {
			dataMap.put("regulations", attractionsParkingPO.getRegulations());
			dataMap.put("parkingName", attractionsParkingPO.getParkingName());

		}
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
		Long uid = pageObject.getLong("uid");   
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
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		
		Long uid = pageObject.getLong("uid");   
		List<OrderPO> userByid = Order.getOrderform(userId,uid);
		for (OrderPO orderPO : userByid) {
			dataMap.put("status", orderPO.getOrderStatus());
		}
		return success(dataMap);
	}

	
	
	
	
	
	/*****************************************************************************************************/






	/** 根据用户信息保存信息 订单界面显示用
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public  Map<String, Object> Business(HttpServletRequest request) throws Exception {
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
		String  stall = pageObject.getString("stall");  // 车位 
		System.out.println(stall);
		List<AttractionsParkingPO> attractions = Attra_ctions.getUid(uid);
		String name = null;
		//停车场时间
		String time = null;
		for (AttractionsParkingPO attractionsParkingPO : attractions) {
			name   =  	attractionsParkingPO.getParkingName();
			time  =attractionsParkingPO.getStoppingTime();
		}
		List<OrderPO> userByid = Order.getOrderform(userId,uid);
		
		if (userByid.size() == 0) {
			OrderPO odp = new OrderPO();
			odp.setOrderId(userId);
			odp.setParkingLayer(ceng);
			odp.setParkingName(name);
			odp.setTime(time);
			odp.setParkingDistrict(qu);
			odp.setParkingNumber(stall);
			odp.setAttractionsId(uid);
			odp.setOrderStatus("未支付");
			Order.save(odp);
			dataMap.put("uid", odp.getId());
		}else {
			for (OrderPO order : userByid) {
				order.setParkingLayer(ceng);
				order.setParkingName(name);
				order.setTime(time);
				order.setParkingDistrict(qu);
				order.setParkingNumber(stall);
				order.setOrderStatus("未支付");
				Order.saveOrUpdate(order);
				dataMap.put("uid", order.getId());
			}
		}
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

	
	
	
		/**
		 * 根据景区id 查询停车费用 每小时
		 * @return
		 **/
		@ResponseBody
		@RequestMapping(value = "/selecost", method = RequestMethod.POST)
		public Map<String, Object> SeleCost(HttpServletRequest request) throws Exception {
			String param = getRequestJson(request);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			if (param.indexOf("\\") >= 0) {
				param = param.replaceAll("\\\\", "");
				param = param.substring(1, param.length() - 1);
			}
			JSONObject pageObject = JSON.parseObject(param);
			Long uid = pageObject.getLong("uid");   
			List<AttractionsParkingPO> userName =  Attra_ctions.getUid(uid);
			for (AttractionsParkingPO attractionsParkingPO : userName) {
				dataMap.put("cost", attractionsParkingPO.getCost());
             System.out.println(attractionsParkingPO.getCost());
			}
			return success(dataMap);
		}


		/*****************************************************************************************************/





		/**
		 * 支付
		 * @return
		 **/
		/*	@ResponseBody
	@RequestMapping(value = "/prev/pay/{id}")
	public Object prevPay(@PathVariable String id, String cip, HttpServletRequest request) throws Exception {
		String orderNo = id + "";
		int payMoney=0;
			OrderInfoPO orderInfoPO=conn_order.get(Long.parseLong(id));
			payMoney+=orderInfoPO.getPayMoney();
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://"+WXContants.Website+"/website/wxreport/payreport", WxConfig.appId,
				WxConfig.appsrcret);
		// 统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息
		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", orderNo,payMoney,
				"192.165.56.64", user.getUserOpenID());
		// 生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);
		// 将订单号放入map，用以支付后处理
		map.put("orderNo", orderNo);
		return map;
	}


		 */







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
		@RequestMapping(value = "/merchant/scenic")
		public ModelAndView ScenicSpot(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("parking/park/parkingspace"); 
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
		@RequestMapping(value = "/merchant/navigation")
				public ModelAndView Spacenavigation(HttpServletRequest request,HttpSession session) throws Exception {
					ModelAndView mv = null;
					mv = new ModelAndView("parking/park/spacenavigation"); 
					return mv;
				}
		//转到jsp文件
		@RequestMapping(value = "/merchant/parkings")
		public ModelAndView Parkings(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("parking/park/parkings"); 
			return mv;
		}
























































	}
