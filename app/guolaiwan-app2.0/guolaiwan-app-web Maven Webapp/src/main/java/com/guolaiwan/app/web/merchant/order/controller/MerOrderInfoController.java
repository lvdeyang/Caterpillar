package com.guolaiwan.app.web.merchant.order.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/merchant/orderinfo")
public class MerOrderInfoController extends BaseController{
	@Autowired
	private OrderInfoDAO conn_OrderInfo;
	@Autowired
	private UserInfoDAO conn_userInfo;
	

	//显示列表
	@RequestMapping(value="/yandan",method= RequestMethod.GET)
	public ModelAndView toYandan(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		long merId = getMerchantInfo().getMerchantId();
		int count = conn_OrderInfo.countYdNow(merId);
		strMap.put("count", count);
		ModelAndView mv = new ModelAndView("merchant/orderinfo/yandan",strMap);
		return mv;
	}
	
	
	//验单
	@ResponseBody
	@RequestMapping(value="/yandan.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> yandan(HttpServletRequest request) throws Exception {
		
		long merId = getMerchantInfo().getMerchantId();
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		String orderNo = request.getParameter("orderNo");
		OrderInfoPO order = null;
		if(orderNo.length()>6){
			order = conn_OrderInfo.getByRoderNo(orderNo);
		}else{
			order = conn_OrderInfo.getByRoderNo(Long.parseLong(orderNo));
		}
		if(order==null){
			strMap.put("msg", "nothave");
			return strMap;
		}
		if(order.getShopId()!=merId){
			OrderInfoVO orderVO = new OrderInfoVO().set(order);
			strMap.put("msg", "nobelong");
			strMap.put("order", orderVO);
			return strMap;
		}
		//只有支付完成的才可以验单
		if(order.getOrderState().equals(OrderStateType.PAYSUCCESS)||order.getOrderState().equals(OrderStateType.PAYFINISH)){
			order.setOrderState(OrderStateType.TESTED);
			order.setYdDate(new Date());
			conn_OrderInfo.save(order);
			int count = conn_OrderInfo.countYdNow(merId);
			OrderInfoVO orderVO = new OrderInfoVO().set(order);
			strMap.put("msg", "success");
			strMap.put("order", orderVO);
			strMap.put("count", count);
			return strMap;
		}else{
			OrderInfoVO orderVO = new OrderInfoVO().set(order);
			strMap.put("msg", "stateError");
			strMap.put("order", orderVO);
			strMap.put("orderState", order.getOrderState().getName());
			return strMap;
		}
	}
	
	
	
	//验单列表
	@ResponseBody
	@RequestMapping(value="/ydList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> ydlist(int page,int limit) throws Exception {
		long merId = getMerchantInfo().getMerchantId();
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_OrderInfo.countByYd(merId);
		List<OrderInfoPO> orders = conn_OrderInfo.findByYd(merId, page, limit);
		List<OrderInfoVO> orderVos = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orders, OrderInfoVO.class);
		
		//用户名称
		for (OrderInfoVO orderInfoVO : orderVos) {
			orderInfoVO.setUserName(conn_userInfo.get(orderInfoVO.getUserId()).getUserNickname());
		}
		strMap.put("data", orderVos);
		strMap.put("code", "0");
		strMap.put("count", count);
		strMap.put("msg", "");
		return strMap;
	}
	
	
	


}
