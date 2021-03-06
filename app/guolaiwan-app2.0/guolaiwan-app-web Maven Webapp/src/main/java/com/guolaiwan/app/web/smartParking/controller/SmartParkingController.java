package com.guolaiwan.app.web.smartParking.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.publicnum.vo.RoomVo;
import com.guolaiwan.app.web.smartParking.vo.OrderVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.Parking.dao.CarPositionDao;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
/**
 * 退出页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/quit")
public class SmartParkingController  extends WebBaseControll{

	@Autowired
	private  VehicleDao par_king;
	@Autowired
	private  OrderDao or_der;
	@Autowired
	private CarPositionDao  Car_Position;
	
	@Autowired
	private ParkingPositionDao  Parking_Position;


	/*****************************************************************************************************/



	/**
	 * 根据用户id 查询车辆类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Map<String, Object> AddInformation(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>(); 
		List<VehiclePO>  vehicles = par_king.getNumber(userId);
		if( vehicles!= null&&!vehicles.isEmpty()){
			dataMap.put("userHeadimg", vehicles.get(0).getNumber());
			dataMap.put("userNickname", vehicles.get(0).getType());
		}
		return success(dataMap);
	}



	/*****************************************************************************************************/



	/**
	 * 查询   订单状态  订单 
	 * 是否过期
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public Map<String, Object> setState(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		String	vehicle = null;
		List<VehiclePO>  userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle  =  vehiclePO.getNumber();
		}
		List<String> listHasCup=new ArrayList<String>();
		List<OrderPO> userByid = or_der.getOrder(userId,vehicle);
		int boole =0;
		for (OrderPO orderPO : userByid) {//判断用户所有订单 如果有到时间还未进入停车场的修改状态 PAST过期 
			if ("PAYSUCCESS".equals(orderPO.getOrderStatus())) {
			  if(orderPO.getDueTime() != null){
				  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				   String time = df.format(new Date());
				   Date d1 = df.parse(time);
				   Date d2 = df.parse(orderPO.getDueTime());
				   long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
				   Long date = diff/1000;
				   if (date<0) {
				    int	parkingNumber = orderPO.getParkingNumber();
					String	parkingLayer = 	orderPO.getParkingLayer();
					String	district = 	orderPO.getParkingDistrict();
				    long   attactionsId  =  orderPO.getAttractionsId();
					CarPositionPO userName =  Car_Position.getAmend(attactionsId,parkingLayer,district);
					long Carid = userName.getId();
				    ParkingPositionPO getTruck  =  Parking_Position.getNumber(Carid,parkingNumber);
				    getTruck.setUseCondition(0);
					Parking_Position.saveOrUpdate(getTruck);   
					
					boole = 1;
					orderPO.setOrderStatus("PAST");
					or_der.save(orderPO);
				   }
				 }  
			}
			if (boole == 0) {
				listHasCup.add(orderPO.getOrderStatus()); 
			}else{
				boole = 0;
			}
		}
		return success(listHasCup);
	}






	/*****************************************************************************************************/

	
	

	/**
	 * 查询   车位层数   区
	 * 是否过期
	 * @param request
	 * @return
	 * @throws Exception
	 */
/*	@ResponseBody
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public Map<String, Object> setTier(HttpServletRequest request) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		long userId = 	(long) request.getSession().getAttribute("userId");	
		 List<OrderPO> userByid = or_der.getOrder(userId);
		for (OrderPO orderPO : userByid) {
			dataMap.put("orderStatus", orderPO.getOrderStatus());
		}
		
		return success(dataMap);
	}*/


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	






	/*****************************************************************************************************/
	//转到jsp文件
	@RequestMapping(value = "/merchant/smartparking")
	public ModelAndView merchantIndex(HttpServletRequest request,HttpSession session,String merchantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/secondHome"); 
		if(merchantId != null && merchantId != "")mv.addObject("merchantId",merchantId);
		return mv;
	}































































}
