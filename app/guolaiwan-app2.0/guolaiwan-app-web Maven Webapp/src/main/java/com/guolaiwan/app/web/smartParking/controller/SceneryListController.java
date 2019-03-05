package com.guolaiwan.app.web.smartParking.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import com.guolaiwan.app.web.smartParking.vo.CarPositionVo;
import com.guolaiwan.app.web.smartParking.vo.OrderVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.Parking.dao.AttractionsDao;
import com.guolaiwan.bussiness.Parking.dao.CarPositionDao;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
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
	private  AttractionsDao Attra_ctions;
	
	@Autowired
	private CarPositionDao  Car_Position;

	/*****************************************************************************************************/

	

	/**
	 * 查询   景区信息 模糊查询
	 * 景区名称 图片  地址  总车位  剩余车位    室内室外  充电柱
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/parking", method = RequestMethod.POST)
	public Map<String, Object> getMessage (HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String name = pageObject.getString("parking"); 
		List<AttractionsParkingPO> attractions =	Attra_ctions.getMessage(name);
		List<AttractionsVo> _merchants = AttractionsVo.getConverter(AttractionsVo.class).convert(attractions,
				AttractionsVo.class);
		return success(_merchants);
	}

	
	
	
	
	
	
	
	

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

	
	



	/** 根据用户信息保存信息 订单界面显示用
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, Object> Business(HttpServletRequest request) throws Exception {
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
        String  time = pageObject.getString("time"); //停车场时间
        String  stall = pageObject.getString("stall");  // 车位 
        dataMap.put("uid", uid);
        dataMap.put("ceng", ceng);
        dataMap.put("qu", qu);
        dataMap.put("stall", stall);
        dataMap.put("time", time);
		return success(dataMap);
	}

	
	

	/*****************************************************************************************************/


	
	

	/**
	 * 根据景区id 查询停车费用 每小时
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.POST)
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
			
		}
		return success(dataMap);
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

























































}
