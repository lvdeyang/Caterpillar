package com.guolaiwan.app.web.admin.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
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
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.RolePO;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.commons.util.date.DateUtil;
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



	// 根据状态查询订单
	@ResponseBody
	@RequestMapping(value = "/switchover.do", method = RequestMethod.POST)
	public Map<String, Object> switchoverSele(int page, int limit,String type,String mName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("type: "+type);
		String  status = ( 
				"0".equals(type))?"PAYSUCCESS":( "1".equals(type))?"PARKING":
					( "2".equals(type))?"PAST":( "3".equals(type))?"REFUNDING":
						( "4".equals(type))?"REFUNDED":( "5".equals(type))?"REFUNDFAIL":"";
		System.out.println(status);
		List<OrderPO>  length =Order.getOrderfor(status,mName);
		List<OrderPO>  order =Order.getOrderfor(page,limit,status,mName);
		List<OrderVo> listvo = OrderVo.getConverter(OrderVo.class).convert(order, OrderVo.class);
		map.put("count",length.size());
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}



	// 根据id 查询
	@ResponseBody
	@RequestMapping(value = "/demand.do", method = RequestMethod.POST)
	public Map<String, Object> demandId(int page, int limit,String orderIds) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		List<OrderPO>  order  = Order.getOrderfor(Long.parseLong(orderIds));
		List<OrderVo> listvo = OrderVo.getConverter(OrderVo.class).convert(order, OrderVo.class);
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}
/*
	@RequestMapping(value = "/deriveorder.do")
	public String derive(HttpServletRequest request, HttpServletResponse response,String type,String mName) throws Exception {
		String  status = ( 
				"0".equals(type))?"PAYSUCCESS":( "1".equals(type))?"PARKING":
					( "2".equals(type))?"PAST":( "3".equals(type))?"REFUNDING":
						( "4".equals(type))?"REFUNDED":( "5".equals(type))?"REFUNDFAIL":"";
		List<OrderPO>  order = Order.getOrderform(status,mName);
		DecimalFormat df = new DecimalFormat("0.00");
		String title = "订单" + DateUtil.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		String[] headers = new String[] { "序号", "用户订单id", "景区id", "停车场层数", "停车场 名称", "停车场营业时间", "停车区", "车位编号", "停车费用", "停车总时间",
				"预订时间", "到期时间", "订单状态", "退款理由", "车牌号", "超时金额", "超时时间" , "支付订单号", "商品订单号", "二维码路径"};
		List<Object[]> dataList = new ArrayList<Object[]>();
		if (order != null) {
			for (int i = 0; i < order.size(); i++) {
				double accured = 0;
				Object[] obj = new Object[headers.length];
				obj[1] = order.get(i).getOrderId();
				obj[1] = order.get(i).getAttractionsId();
				obj[1] = order.get(i).getParkingLayer();
				obj[1] = order.get(i).getParkingName();
				obj[1] = order.get(i).getTime();
				obj[1] = order.get(i).getP
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				obj[1] = order.get(i).
				dataList.add(obj);
			}
		}
		outputList(title, headers, dataList, response);
		return "success";

	}*/





	// 停车场订单页面
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView getList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/parkingorder/order");
		return mv;
	}

}




