package com.guolaiwan.app.web.smartParking.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
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
		List<VehiclePO> userByid = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userByid) {
			dataMap.put("userHeadimg", vehiclePO.getNumber());
			dataMap.put("userNickname", vehiclePO.getType());
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
		String vehicle = null;
		List<VehiclePO> userBy = par_king.getNumber(userId);
		for (VehiclePO vehiclePO : userBy) {
			vehicle =  vehiclePO.getNumber();
		}
		List<String> listHasCup=new ArrayList<String>();
		 List<OrderPO> userByid = or_der.getOrder(userId,vehicle);
		for (OrderPO orderPO : userByid) {
			listHasCup.add(orderPO.getOrderStatus());
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
	public ModelAndView merchantIndex(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/secondHome"); 
		return mv;
	}































































}
