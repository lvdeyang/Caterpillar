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
import com.guolaiwan.app.web.smartParking.vo.AttractionsVo;
import com.guolaiwan.app.web.smartParking.vo.OrderVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.Parking.dao.AttractionsDao;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.sun.tools.javah.resources.l10n;

//登陆首页
@Controller
@RequestMapping("/smart")
public class ParkingController  extends WebBaseControll{

	@Autowired
	private UserInfoDAO conn_user;
	@Autowired
	private  VehicleDao par_king;





	/*****************************************************************************************************/


	/**
	 * 根据用户查询用户 信息
	 * @param request 头像  用户名
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/usere", method = RequestMethod.POST)
	public Map<String, Object> Information(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<UserInfoPO> user = conn_user.getUserByUid(userId);
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		for (UserInfoPO userInfoPO : user) {
			dataMap.put("userHeadimg", userInfoPO.getUserHeadimg());
			dataMap.put("userNickname", userInfoPO.getUserNickname());
			dataMap.put("userPhone", userInfoPO.getUserPhone());
		}
		return success(dataMap);
	}



	/*****************************************************************************************************/



	/**
	 *   根据用户id    添加车牌 车型
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/setVehicle", method = RequestMethod.POST)
	public int IncreaseInformation(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		if (pageObject == null ) {
			return 1;
		}
		if (pageObject != null && pageObject.size() > 0) {
			String number = pageObject.getString("parking");
			String type = pageObject.getString("parNumber");
			List<VehiclePO> userByid = par_king.getNumber(number,userId);
			if (userByid.size() == 0) {
				VehiclePO veh = new VehiclePO();
				veh.setVehicleid(userId);
				veh.setNumber(number);
				veh.setType(type);
				par_king.save(veh);
			}else {
				for (VehiclePO vehiclePO : userByid) {
					vehiclePO.setNumber(number);
					vehiclePO.setType(type);
					par_king.update(vehiclePO);
				}
			}
		}
		return 0;
	}
	
	
	
	
	
	/*****************************************************************************************************/

	
	
	
	/**
	 *   根据用户id    添加车牌 车型
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/setVeh", method = RequestMethod.POST)
	public Map<String, Object> IncreaseInforma(HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		if (pageObject != null && pageObject.size() > 0) {
			String number = pageObject.getString("parking");
			String type = pageObject.getString("parNumber");
			List<VehiclePO> userByid = par_king.getNumber(number,userId);
			for (VehiclePO vehiclePO : userByid) {	
			dataMap.put("carid",vehiclePO.getId() );
			}
		}
		return success(dataMap);
	}


	
	
	/*****************************************************************************************************/



	//转到jsp文件
	@RequestMapping(value = "/merchant/parking")
	public ModelAndView merchantIndex(HttpServletRequest request,HttpSession session,String merchantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/homepage"); 
		mv.addObject("userId",session.getAttribute("userId"));
		if(merchantId != null && merchantId != "")mv.addObject("merchantId",merchantId);
		return mv;
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








}
