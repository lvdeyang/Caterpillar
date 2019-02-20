package com.guolaiwan.app.web.website.order.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.enumeration.LoginCacheName;
import com.guolaiwan.app.web.user.vo.UserVO;
import com.guolaiwan.app.web.website.order.vo.OrderDetailVO;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import com.guolaiwan.bussiness.order.dao.OrderDAO;
import com.guolaiwan.bussiness.order.dao.OrderDetailDAO;
import com.guolaiwan.bussiness.order.dto.OrderDetailDTO;
import com.guolaiwan.bussiness.order.enumeration.OrderStatusType;
import com.guolaiwan.bussiness.order.po.OrderDetailPO;
import com.guolaiwan.bussiness.order.po.OrderPO;
import com.guolaiwan.bussiness.user.check.UserDBCheck;
import com.guolaiwan.bussiness.user.dao.BasketDAO;
import com.guolaiwan.bussiness.user.po.BasketPO;
import com.guolaiwan.bussiness.user.po.UserPO;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping("/website/order")
public class WebsiteOrderController extends BaseController{

	@Autowired
	private BasketDAO conn_basket;
	
	@Autowired
	private OrderDAO conn_order;
	
	@Autowired
	private OrderDetailDAO conn_order_detail;
	
	@Autowired
	private UserDBCheck dbcheck_user;
	
	//下订单
	@ResponseBody
	@RequestMapping(value = "/book/{userId}", method = RequestMethod.POST)
	public Map<String, Object> book(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(true);
		
		UserVO _user = (UserVO)session.getAttribute(LoginCacheName.USER.getName());
		
		UserPO user = dbcheck_user.userLegit(_user.getId(), userId);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		
		JSONObject params = parser.parseJSON();
		
		
		List<Long> basketIds = JSON.parseArray(params.getString("basketIds"), Long.class);
		
		List<BasketPO> baskets = conn_basket.getBasketByUser(userId, basketIds);
		
		if(baskets == null){
			throw new Exception("数据异常，未获取到用户可下单的购物车信息！");
		}
		
		//创建订单
		OrderPO order = new OrderPO();
		order.setUpdateTime(new Date());
		order.setOrdertime(new Date());
		order.setStatus(OrderStatusType.PAID);
		conn_order.save(order);
		
		order.setUser(user);
		if(user.getOrders() == null){
			user.setOrders(new HashSet<OrderPO>());
		}
		user.getOrders().add(order);
		
		Set<OrderDetailPO> orderDetails = new HashSet<OrderDetailPO>();
		
		for(BasketPO basket:baskets){
			
			ProductPO product = basket.getProduct();
			ChapmanPO chapman = product.getChapman();
			
			//解关联
			basket.setProduct(null);
			product.getBaskets().remove(basket);
			basket.setUser(null);
			user.getBaskets().remove(basket);
			
			OrderDetailPO orderDetail = new OrderDetailPO();
			orderDetail.setProduct(product);
			if(product.getOrderDetails() == null){
				product.setOrderDetails(new HashSet<OrderDetailPO>());
			}
			product.getOrderDetails().add(orderDetail);
			
			orderDetail.setChapman(chapman);
			if(chapman.getOrderDetails() == null){
				chapman.setOrderDetails(new HashSet<OrderDetailPO>());
			}
			chapman.getOrderDetails().add(orderDetail);
			
			orderDetail.setUpdateTime(new Date());
			orderDetail.setNum(basket.getNum());
			orderDetail.setOrder(order);
			orderDetails.add(orderDetail);
			
		}
		
		order.setOrderDetails(orderDetails);
		conn_order.saveOrUpdate(order);
		
		conn_basket.deleteAll(baskets);
		
		List<OrderDetailDTO> currOrderDetails = conn_order_detail.getOrderDetailByOrderId(order.getId());
		
		List<OrderDetailVO> _currOrderDetails = OrderDetailVO.getConverter(OrderDetailVO.class).convert(currOrderDetails, OrderDetailVO.class);
		
		result.put("orders", _currOrderDetails);
		
		return success(result);
	}
	
	//获取用户历史订单
	@ResponseBody
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public Map<String, Object> get(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(true);
		
		UserVO _user = (UserVO)session.getAttribute(LoginCacheName.USER.getName());
		
		UserPO user = dbcheck_user.userLegit(_user.getId(), userId);
		
		List<OrderDetailDTO> orderDetails = conn_order_detail.getOrderDetailByUser(user.getId());
		
		List<OrderDetailVO> _orderDetails = OrderDetailVO.getConverter(OrderDetailVO.class).convert(orderDetails, OrderDetailVO.class);
		
		result.put("orders", _orderDetails);
		
		return success(result);
	}
	
}
