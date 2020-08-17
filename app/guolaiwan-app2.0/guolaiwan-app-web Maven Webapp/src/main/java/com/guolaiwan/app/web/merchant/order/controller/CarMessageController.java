package com.guolaiwan.app.web.merchant.order.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.merchant.dao.CarMessageDAO;
import com.guolaiwan.bussiness.merchant.dao.ReportOrderAllDAO;
import com.guolaiwan.bussiness.merchant.dao.ReportOrderDAO;
import com.guolaiwan.bussiness.merchant.po.CarMessagePO;
import com.guolaiwan.bussiness.merchant.po.ReportOrderAllPO;
import com.guolaiwan.bussiness.merchant.po.ReportOrderPO;

import cn.hutool.core.util.IdcardUtil;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/carmessage")
public class CarMessageController extends BaseController{
	@Autowired
	CarMessageDAO conn_carmsg;

	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView activityList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("merchant/car/list", strMap);
		
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_carmsg.countAll();
	    List<CarMessagePO> carMessagePOs=conn_carmsg.findAll();
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", carMessagePOs);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("merchant/car/add");
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {

		String carNumstr = request.getParameter("carNum");
		String typeStr = request.getParameter("type");
		String regionStr = request.getParameter("region");
		CarMessagePO carMessagePO=new CarMessagePO();
		carMessagePO.setCarNum(Long.parseLong(carNumstr));
		carMessagePO.setRegion(regionStr);
		carMessagePO.setType(Integer.parseInt(typeStr));
		if(carMessagePO.getType()==0){
			carMessagePO.setTypeString("小型车");
		}else{
			carMessagePO.setTypeString("大型车");
		}
		conn_carmsg.save(carMessagePO);
		return "success";
	}

	
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
	    conn_carmsg.delete(id);
		return "success";
	}	
	

	
}
