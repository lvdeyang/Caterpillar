package com.guolaiwan.app.web.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.AdminVO;
import com.guolaiwan.app.web.admin.vo.LogisticVO;
import com.guolaiwan.app.web.smartParking.vo.OrderVo;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.admin.dao.AdminDAO;
import com.guolaiwan.bussiness.admin.dao.CityInfoDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.RoleDAO;
import com.guolaiwan.bussiness.admin.dto.CountGroupDTO;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;
import com.guolaiwan.bussiness.admin.po.RolePO;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.controller.BaseController;


@Controller
@RequestMapping("/parkingorder")
public class ParkingOrderController extends BaseController {
	
	@Autowired
	private OrderDao  Order;
	
	
	
	
	// 查询全部 订单     
	@ResponseBody
	@RequestMapping(value = "/order.do", method = RequestMethod.POST)
	public Map<String, Object> getList(HttpServletRequest request , int page, int limit, int type) throws Exception {
		List<OrderPO> order=Order.getOrderfor(page,limit);
		List<OrderPO>  length =Order.getOrderfor();
		List<OrderVo> listvo = OrderVo.getConverter(OrderVo.class).convert(order, OrderVo.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count",length.size());
	    map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}
	
	
	
	// 搜索商家和商品
		@ResponseBody
		@RequestMapping(value = "/switchover.do", method = RequestMethod.POST)
		public Map<String, Object> switchoverSele(int page, int limit,String type) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println("type: "+type);
			String  status = ( "0".equals(type))?"PAYSUCCESS":( "1".equals(type))?"PARKING":( "2".equals(type))?"PAST":( "3".equals(type))?"REFUNDING":( "4".equals(type))?"REFUNDED":"REFUNDFAIL";
			List<OrderPO>  length =Order.getOrderfor(status);
			List<OrderPO>  order =Order.getOrderfor(status,page,limit);
			List<OrderVo> listvo = OrderVo.getConverter(OrderVo.class).convert(order, OrderVo.class);
			map.put("count",length.size());
		    map.put("data", listvo);
			map.put("code", "0");
			map.put("msg", "");
			return map;
		}
	
	
	
	
	
	
	
	
	
	
	// 停车场订单页面
		@RequestMapping(value = "/order", method = RequestMethod.GET)
		public ModelAndView getList(HttpServletRequest request) {
			ModelAndView mv = new ModelAndView("admin/parkingOrder/Order");
			return mv;
		}
	
}




