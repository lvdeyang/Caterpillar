package com.guolaiwan.app.web.admin.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.BundleOrderVo;
import com.guolaiwan.app.web.smartParking.vo.OrderVo;
import com.guolaiwan.bussiness.Parking.dao.CarPositionDao;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.BundleOrder;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPay;

@RestController
@RequestMapping("/admin/parkrefund")
public class ParkRefundController extends BaseController {
	
	@Autowired
	private OrderDao conn_order;
	
	@Autowired
	private BundleOrderDAO conn_bundle;
	
	@Autowired
	private UserInfoDAO conn_user;
	@Autowired
	private CarPositionDao  Car_Position;
	
	@Autowired
	private ParkingPositionDao  Parking_Position;
	
    /**
     *  jsp跳转
     * */
	@RequestMapping(value="/list")
	public ModelAndView parkskip(){
		Map<String, Object> map = new HashMap<String, Object>();		
		return new ModelAndView("admin/parkmanagement/refundinglist",map);
	}
	
	/**
	 *  停车退款列表
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value="/refundlist.do",method = RequestMethod.POST)
	public Map<String, Object> getReFundList(HttpServletRequest request) throws Exception{
		
		Map<String, Object> result = new  HashMap<String, Object>();
		// 检索条件
	
			List<OrderVo> orderVos = new ArrayList<OrderVo>();
		    List<OrderPO> orderPOs = conn_order.findAll();
		    OrderVo orderVo = new OrderVo();
		    List<OrderVo> orderVoes = orderVo.getConverter(OrderVo.class).convert(orderPOs, OrderVo.class);
		    
			for(OrderVo po : orderVoes){
				if( "REFUNDING".equals(po.getOrderStatus())){					
					orderVos.add(po);					
				}				
			}								
				result.put("data", orderVos);
				result.put("code", "0");
				result.put("msg", "");
				result.put("count", orderVos.size());								
	        	return result;
	}
	
	  /**
	   * 拒绝退款
	   * */
	 @RequestMapping(value = "/updateJustification.do", method = RequestMethod.POST)
	 public String updateJustification(String id,String justification) throws Exception {
		/* OrderPO orderPO = conn_order.get(Long.parseLong(id));
		 orderPO.setJustification(justification);
		 conn_order.save(orderPO);*/
		return "success";
	}
	 	 
	 /**
	  * 退款失败状态
	  * 
	  * */
		@JsonBody
		@RequestMapping(value = "/rejectOrderStatus", method = RequestMethod.GET)
		public Object rejectOrderStatus(HttpServletRequest request, String id, String status) throws Exception {		
			OrderPO orderPO = conn_order.get(Long.parseLong(id));
			orderPO.setOrderStatus(status);
			conn_order.update(orderPO);
			return success();
		}
	/**
	 * 
	 *  微信退款
	 * 
	 * */
		@RequestMapping(value = "/wxpay/refund", method = RequestMethod.GET)
		public Object refund(HttpServletRequest request, HttpServletResponse response, String orderId,String id) throws Exception {
			//判断订单是否存在		
			//if(conn_order.get(Long.parseLong(orderId)).isIswallet()==false){
			        OrderPO lisp =   conn_order.findByField("id", Long.parseLong(id)).get(0);
			        String orderNo =  lisp.getOrderNo();
					long amount = 0;
					Map<String, Object> result = new HashMap<String, Object>();				
					
					if (orderId.indexOf("bundle") != -1) {
						String[] bundleidStrs = orderId.split("-");
						BundleOrder bOrder = conn_bundle.get(Long.parseLong(bundleidStrs[1]));
						String[] orderStrs = bOrder.getOrderStr().split("A");
						for (String orderIdStr : orderStrs) {
							if ("REFUNDING".equals(conn_order.findByField("id", Long.parseLong(id)).get(0).getOrderStatus())  ) {
			
								amount += Math.round(conn_order.findByField("id", Long.parseLong(id)).get(0).getParkingCost()*100);
							}
						}
					} else {
						OrderPO  orderPOs = conn_order.get(Long.parseLong(id));
						if("REFUNDING".equals(orderPOs.getOrderStatus())){						
							amount = Math.round(orderPOs.getParkingCost()*100);						
					 }												
					}
										
					String refundOrderNum = "refund" + orderNo;
					try {
			
						GuolaiwanWxPay wxPay = GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/parkingrefund");
			
						Map<String, String> reqData = new HashMap<String, String>();
			
						reqData.put("out_trade_no", orderNo + "");
						reqData.put("out_refund_no", refundOrderNum);
						reqData.put("total_fee", amount + "");
						reqData.put("refund_fee", amount + "");
			
						Map<String, String> resData = wxPay.refund(reqData); // 生成二维码数据	
						 if (lisp != null) {
					        	//退款后修改车位状态
								int	parkingNumber = lisp.getParkingNumber();
								String	parkingLayer = 	lisp.getParkingLayer();
								String	district = 	lisp.getParkingDistrict();
							    long   attactionsId  =  lisp.getAttractionsId();
								CarPositionPO userName =  Car_Position.getAmend(attactionsId,parkingLayer,district);
								long Carid = userName.getId();
							    ParkingPositionPO getTruck  =  Parking_Position.getNumber(Carid,parkingNumber);
							    getTruck.setUseCondition(0);
								Parking_Position.saveOrUpdate(getTruck);
								lisp.setOrderStatus("REFUNDED");
								conn_order.saveOrUpdate(lisp);
					    }
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
					}
			
					return result;
								
		}	
		
}
