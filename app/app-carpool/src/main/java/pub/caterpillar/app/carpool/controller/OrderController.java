package pub.caterpillar.app.carpool.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import pub.caterpillar.app.carpool.dao.DriverDAO;
import pub.caterpillar.app.carpool.dao.OrderDAO;
import pub.caterpillar.app.carpool.enumeration.DriverDirections;
import pub.caterpillar.app.carpool.enumeration.OrderPayStatus;
import pub.caterpillar.app.carpool.enumeration.OrderPriceType;
import pub.caterpillar.app.carpool.enumeration.OrderTakingStatus;
import pub.caterpillar.app.carpool.notify.DriverNotifyTool;
import pub.caterpillar.app.carpool.po.DriverPO;
import pub.caterpillar.app.carpool.po.OrderPO;
import pub.caterpillar.app.carpool.po.RoutePO;
import pub.caterpillar.app.carpool.po.SeatInfoPO;
import pub.caterpillar.app.carpool.po.UserPO;
import pub.caterpillar.app.carpool.service.OrderService;
import pub.caterpillar.app.carpool.service.SystemService;
import pub.caterpillar.app.carpool.vo.BalanceVO;
import pub.caterpillar.app.carpool.vo.OrderVO;
import pub.caterpillar.app.carpool.vo.SeatInfoVO;
import pub.caterpillar.app.carpool.vo.WeixinConfigVO;
import pub.caterpillar.app.carpool.weixin.YuebaWxPayConstants;
import pub.caterpillar.app.carpool.weixin.YuebaWxUtil;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderDAO conn_order;
	
	@Autowired
	private DriverDAO conn_driver;
	
	@Autowired
	private OrderService orderService;
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/new/order/after/30/minutes", method = RequestMethod.GET)
	public Object queryNewOrderAfter30Minutes(HttpServletRequest request) throws Exception{
		List<OrderPO> orders = conn_order.queryNewOrderAfter30Minutes();
		List<OrderVO> view_orders = OrderVO.getConverter(OrderVO.class).convert(orders, OrderVO.class);
		return view_orders;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/unpay/order")
	public Object queryUnPayOrder() throws Exception{
		List<OrderPO> orders = conn_order.queryUnpayOrder();
		List<OrderVO> view_orders = OrderVO.getConverter(OrderVO.class).convert(orders, OrderVO.class);
		return view_orders;
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.POST})
	public ModelAndView add(
			long departureId,
			String departureName,
			long destinationId,
			String destinationName,
			String date,
			String time,
			long fleetId,
			String fleetName,
			long routeId,
			String routeUuid,
			int passengerNum,
			String priceType,
			float price,
			float addMoney,
			String remark,
			HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		UserPO user = (UserPO)session.getAttribute("user");
		
		//重组出发时间
		String travelTimeStr = new StringBuffer().append(date)
											     .append(" ")
											     .append(time)
											     .append(":00")
											     .toString();
		Date travelTime = DateUtil.parse(travelTimeStr, DateUtil.shortDateTimePatten);
		
		//创建时间
		Date now = new Date();
		
		OrderPO order = new OrderPO();
		order.setDepartureId(departureId);
		order.setDepartureName(departureName);
		order.setDestinationId(destinationId);
		order.setDestinationName(destinationName);
		order.setTravelTime(travelTime);
		order.setFleetId(fleetId);
		order.setFleetName(fleetName);
		order.setRouteId(routeId);
		order.setRouteUuid(routeUuid);
		order.setPassengerNum(passengerNum);
		order.setPriceType(OrderPriceType.fromName(priceType));
		order.setPrice(price);
		order.setAddMoney(addMoney);
		order.setRemark(remark);
		order.setCreateTime(now);
		order.setTakingStatus(OrderTakingStatus.NEW);
		order.setPayStatus(OrderPayStatus.UNPAY);
		order.setUserId(user.getId());
		conn_order.save(order);

		//通知司机有新订单
		List<String> mobiles = conn_driver.queryMobileByRouteAndStatusAndFreeSeat(routeId);
		DriverNotifyTool.getInstance().orderNotify(mobiles, order.getId(), order.getTravelTime(), order.getAddMoney(), order.getRemark());
		
		//公众号消息
		
		//短信提醒
		
		ModelAndView mv = new ModelAndView(new StringBufferWrapper().append("redirect:/visitor/index/").append(user.getMobile()).toString());
		
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/taking/status/{id}", method = RequestMethod.GET)
	public Object queryTakingStatus(
			@PathVariable long id, 
			HttpServletRequest request) throws Exception{
		String status = conn_order.queryOrderStatus(id);
		return status;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/pay/status/{id}", method = RequestMethod.GET)
	public Object queryPayStatus(
			@PathVariable long id, 
			HttpServletRequest request) throws Exception{
		String status = conn_order.queryOrderPayStatus(id);
		return status;
	}
	
	
	
	@RequestMapping(value = "/visitor/cancel/order/{id}", method = RequestMethod.POST)
	public ModelAndView visitorCancelOrder(
			@PathVariable long id,
			HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		UserPO user = (UserPO)session.getAttribute("user");
		
		JSONObject result = orderService.visitorCancelOrder(id);
		if(result != null){
			//通知接单司机
			DriverNotifyTool.getInstance().visitorCancelOrder(result.getString("mobile"), result.getString("orderUuid"));
			
			//公众号消息
			
			//短信提醒
		}
		
		ModelAndView mv = new ModelAndView(new StringBufferWrapper().append("redirect:/visitor/index/").append(user.getMobile()).toString());
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/system/order/{mobile}", method = RequestMethod.GET)
	public Object querySystemOrder(
			@PathVariable String mobile,
			HttpServletRequest request) throws Exception{
		
		DriverPO driver = conn_driver.queryByMobile(mobile);
		
		RoutePO route = null;
		if(DriverDirections.ROUTE.equals(driver.getDirection())){
			route = driver.getRoute();
		}else if(DriverDirections.REVERT.equals(driver.getDirection())){
			route = driver.getRevert();
		}
		
		List<OrderPO> orders = conn_order.queryNewOrderByRoute(route.getId());
		List<OrderVO> view_orders = OrderVO.getConverter(OrderVO.class).convert(orders, OrderVO.class);
		
		return view_orders;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/taking/system/order/{mobile}/{orderId}", method = RequestMethod.POST)
	public Object takingSystemOrder(
			@PathVariable String mobile,
			@PathVariable long orderId,
			HttpServletRequest request) throws Exception{
		
		List<SeatInfoPO> seatInfos = orderService.takingSystemOrder(mobile, orderId);
		
		List<SeatInfoVO> view_seatInfos = SeatInfoVO.getConverter(SeatInfoVO.class).convert(seatInfos, SeatInfoVO.class);
		
		return view_seatInfos;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/admin/distribute/system/order/{mobile}/{orderId}", method = RequestMethod.POST)
	public Object adminDistributeSystemOrder(
			@PathVariable String mobile,
			@PathVariable long orderId,
			HttpServletRequest request) throws Exception{
		
		orderService.takingSystemOrder(mobile, orderId);
		
		String orderUuid = conn_order.convertToUuid(orderId);
		
		//为司机推送消息
		DriverNotifyTool.getInstance().adminDistributeSystemOrder(mobile, orderUuid);
		
		//公众号消息
		
		//短信提醒
		
		return null;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/notice/pay", method = RequestMethod.POST)
	public Object noticePay(HttpServletRequest request) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String xml="";
			String tempStr="";
			while((tempStr=reader.readLine())!=null){
				xml+=tempStr;
				System.out.println(tempStr);
			}
			String out_trade_no = YuebaWxUtil.getXmlPara(xml, "out_trade_no");
			String[] strs=out_trade_no.split("-");
			long orderId=Long.parseLong(strs[1]);
			OrderPO orderPO=conn_order.get(orderId);
			orderPO.setPayStatus(OrderPayStatus.PAYED);
			conn_order.save(orderPO);
			//公众号提醒
			
			//短信提醒
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	@ResponseBody
	@RequestMapping(value = "/prev/pay/{id}")
	public Object prevPay(
			@PathVariable long id,
			String cip,HttpServletRequest request) throws Exception{
		
		WeixinConfigVO vo=WeixinConfigVO.getInstance();
		YuebaWxPayConstants.set(vo, "http://www.yueba.net.cn/app-carpool/order/notice/pay");
	    OrderPO orderPO=conn_order.get(id);
	    HttpSession session = request.getSession();
		UserPO user = (UserPO)session.getAttribute("user");
		String orderNo="yuebaOrder-"+id;
		//统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息
		String prePayInfoXml = YuebaWxUtil.unifiedOrder("WxPay", orderNo, 1, "192.165.56.64", user.getOpenId());
		//生成包含prepay_id的map，map传入前端
		Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);
		//将订单号放入map，用以支付后处理
		map.put("orderNo",orderNo);
		return map;
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/balance/list", method = RequestMethod.GET)
	public Object queryBalanceList(
			HttpServletRequest request) throws Exception{
		List<BalanceVO> balanceVOs=new ArrayList<BalanceVO>();
	    List<DriverPO> driverPOs=conn_driver.findAll();
	    for (DriverPO driverPO : driverPOs) {
			BalanceVO vo=new BalanceVO();
			vo.setFleetName(driverPO.getFleet().getName());
			vo.setDriverId(driverPO.getId());
			vo.setDriverName(driverPO.getName());
			List<OrderPO> orderPOs=conn_order.queryBalanceOrderByDriver(driverPO.getMobile());
			vo.setCount(orderPOs.size());
			long amount=0l;
			for (OrderPO orderPO : orderPOs) {
				amount+=orderPO.getPayPrice();
			}
			vo.setAmount(amount);
			balanceVOs.add(vo);
		}
		return balanceVOs;
	}
	
}
