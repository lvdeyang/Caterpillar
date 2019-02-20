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
import com.guolaiwan.app.web.admin.vo.MerchantVO;
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
/**
 * 选择景点页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/vice")
public class SceneryListController  extends WebBaseControll{


	@Autowired
	private  OrderDao or_der;
	@Autowired
	private  AttractionsDao Attra_ctions;



	/*****************************************************************************************************/




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
		int large = pageObject.getInteger("");  //
		int small = pageObject.getInteger("");  //
		List<AttractionsParkingPO> attractions =	Attra_ctions.getInformation(large, small);
		List<AttractionsVo> list = new ArrayList<AttractionsVo>(); 
		AttractionsVo ction = new AttractionsVo();
		for (AttractionsParkingPO attractionsPo : attractions) {
			ction.setParkingName(attractionsPo.getParkingName());
	        ction.setParkingImg(attractionsPo.getParkingDistrict());
	        ction.setAddress(attractionsPo.getAddress());
		}
		
		return success(list);
	}


























	/*****************************************************************************************************/




	/**
	 * 根据用户id 查询订单信息
	 * @return
	 **/
	/*	@ResponseBody
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public List<OrderVo> AddInformation1(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long userId = Long.parseLong(pageObject.getString("userId"));
		UserInfoPO user = conn_user.get(userId);	
	    List<OrderPO> userByid = or_der.getOrder(user.getId());
	    List<OrderVo> roomVos=new ArrayList<OrderVo>();
	    OrderVo order = new OrderVo();
	    for (OrderPO OrderPO : userByid) {
	    	order.setOrderStatus(OrderPO.getOrderStatus());
	    	order.setExpire(OrderPO.getExpire());
		}
		return roomVos;
	}*/













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
	@RequestMapping(value = "/merchant/smartparking")
	public ModelAndView merchantIndex(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/secondHome"); 
		return mv;
	}































































}
