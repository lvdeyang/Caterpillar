package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.CityInfoVO;
import com.guolaiwan.app.web.distribute.vo.DistributorVo;
import com.guolaiwan.app.web.admin.vo.CityInfoVO;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.admin.dao.CityInfoDAO;
import com.guolaiwan.bussiness.admin.dao.CityInfoDAO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.distribute.po.DistributorPo;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/parkoder")
public class ParkOrderController extends BaseController{
	@Autowired
	private OrderDao  Order;
	// 添加商品分类页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView addClass(HttpServletRequest request) throws Exception {
		ModelAndView mv =new ModelAndView("admin/parkOrder/list");
		return mv;
	}
	
	
	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(HttpServletRequest request, int page, int limit, int type) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrderPO> orderinfopo = new ArrayList<OrderPO>();
		int count = 0;
		switch (type) { 
		case 0://全部
			System.out.println("10000000000");
			orderinfopo = Order. getOrder("",page,limit);
			count = Order.getCount("");
			break;
		case 1://未支付
			System.out.println("11111111");
			orderinfopo = Order. getOrder("NOTPAY",page,limit);
			count = Order.getCount("NOTPAY");
			break;
		case 2://已支付
			System.out.println("222222222222222");
			orderinfopo = Order. getOrder("PAYSUCCESS",page,limit);
			count = Order.getCount("PAYSUCCESS");
			break;
		case 3://正在停车
			System.out.println("33333333333333333");
			orderinfopo = Order. getOrder("PARKING",page,limit);
			count = Order.getCount("PARKING");
			break;
		case 4://已过期
			orderinfopo = Order. getOrder("PAST",page,limit);
			count = Order.getCount("PAST");
			break;
		case 5://申请退款
			orderinfopo = Order. getOrder("REFUNDING",page,limit);
			count = Order.getCount("REFUNDING");
			break;
		case 6://退款成功
			orderinfopo = Order. getOrder("REFUNDED",page,limit);
			count = Order.getCount("REFUNDED");
			break;
		case 7://退款失败
			orderinfopo = Order. getOrder("REFUNDFAIL",page,limit);
			count = Order.getCount("REFUNDFAIL");
			break;
		}
		map.put("data", orderinfopo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}
	

}
