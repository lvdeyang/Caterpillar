package com.guolaiwan.app.web.website.wxpay.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.RefundDetail;
import com.guolaiwan.app.aoyou.AoYouV1Service;
import com.guolaiwan.app.aoyou.AoYouV2Service;
import com.guolaiwan.app.aoyou.util.AoyouIDUtil;
import com.guolaiwan.app.qimingxin.TravelService;
import com.guolaiwan.app.qingdongling.QingDLAppUtil;
import com.guolaiwan.bussiness.admin.po.NhEticketsPo;
import com.guolaiwan.app.tianshitongcheng.api.TianShiTongChengAPI;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.weixin.SendMsgUtil;
import com.guolaiwan.bussiness.Parking.dao.AttractionsDao;
import com.guolaiwan.bussiness.Parking.dao.CarPositionDao;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.admin.dao.AddTheRoomDAO;
import com.guolaiwan.bussiness.admin.dao.AoYouOrderDao;
import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
import com.guolaiwan.bussiness.admin.dao.InvestWalletDAO;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.LiveTipGiftDAO;
import com.guolaiwan.bussiness.admin.dao.MealListDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantUserDao;
import com.guolaiwan.bussiness.admin.dao.MessageDAO;
import com.guolaiwan.bussiness.admin.dao.NhEticketsDao;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.TableDAO;
import com.guolaiwan.bussiness.admin.dao.TableStatusDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.AddTheRoomPO;
import com.guolaiwan.bussiness.admin.po.AoYouOrderPO;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.InvestWalletPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LiveTipGiftPO;
import com.guolaiwan.bussiness.admin.po.MealListPo;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MerchantUser;
import com.guolaiwan.bussiness.admin.po.MessagePO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.TablePO;
import com.guolaiwan.bussiness.admin.po.TableStatusPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.nanshan.dao.CurrentRoomSateDao;
import com.guolaiwan.bussiness.nanshan.dao.MessageMiddleClientDao;
import com.guolaiwan.bussiness.nanshan.po.CurrentRoomSatePO;
import com.guolaiwan.bussiness.nanshan.po.MessageMiddleClientPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import cn.hutool.json.JSONUtil;
import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPay;

@Controller
@RequestMapping("/website/wxreport")
public class WxPayReportController extends WebBaseControll {
	// @Autowired
	// private BasketDAO conn_basket;
	//
	@Autowired
	private OrderInfoDAO conn_orderInfo;

	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private BundleOrderDAO conn_bundleorder;

	@Autowired
	private VehicleDao par_king;

	@Autowired
	private AttractionsDao Attra_ctions;

	@Autowired
	private OrderDao Order;

	@Autowired
	private CarPositionDao Car_Position;

	@Autowired
	private ParkingPositionDao Parking_Position;

	@Autowired
	private InvestWalletDAO conn_investwallet;

	@Autowired
	private OrderDao conn_order;

	@Autowired
	private TableStatusDAO Table_Status;

	@Autowired
	private TableDAO Table;

	@Autowired
	private MessageDAO messageDAO;

	@Autowired
	private MessageMiddleClientDao messageClientDAO;

	@Autowired
	private NhEticketsDao nhEticketsDao;
	
	@Autowired
	private AoYouOrderDao aoYouOrderDao;
	
	private Map<String, String> tempMap=new HashMap<String, String>();

	@ResponseBody
	@RequestMapping(value = "/payreport", method = RequestMethod.POST)
	public String pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		// Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);

		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				int i = 0;
				// 获取订单号
				String tradeNum = respData.get("out_trade_no");
				if(tempMap.containsKey(tradeNum)){
					return null;
				}else{
					tempMap.put(tradeNum, tradeNum);
				}
				
				
				String[] orderIds = tradeNum.split("A");
				// orderNo="bundle-"+order.getId();
				if (tradeNum.indexOf("bundle") != -1) {
					Long bundleOrderId = Long.parseLong(tradeNum.split("-")[1]);
					BundleOrder bundleOrder = conn_bundleorder.get(bundleOrderId);
					orderIds = bundleOrder.getOrderStr().split("A");
				}
				for (String orderId : orderIds) {
					i = i + 1;
					OrderInfoPO order = conn_orderInfo.get(Long.parseLong(orderId));
					if (order.getOrderState().toString().equals("NOTPAY")) {
						// 微信支付
						order.setPayMode(PayType.WEICHAT);
						order.setPayDate(new Date());
						// 生成验单码,和二维码图片
						String ydNO = ydNoCode(orderId);
						order.setYdNO(ydNO);
						order.setPayDate(new Date());
						// 支付状态、减库存
						// order.setOrderState(OrderStateType.PAYSUCCESS);
						ProductPO product = conn_product.get(order.getProductId());
						if (product == null) {
							MerchantPO merchantPO = conn_merchant.get(order.getShopId());
							if (merchantPO.getShopyd() == 1) {
								order.setOrderState(OrderStateType.PAYSUCCESS);
							} else {
								order.setOrderState(OrderStateType.TESTED);
								order.setYdDate(new Date());
							}
						} else {
							order.setOrderState(OrderStateType.PAYSUCCESS);
						}
						long userIntegral = order.getPayMoney() / 100; // 购买后增加积分
						if (userIntegral >= 1) {
							List<UserInfoPO> UserInfoPO = conn_user.getUserByUid(order.getUserId()); //
							if (UserInfoPO != null) {
								long Integral = UserInfoPO.get(0).getUserIntegral();
								UserInfoPO.get(0).setUserIntegral(Integral + userIntegral);
								conn_user.saveOrUpdateAll(UserInfoPO);
							}
						}
						conn_orderInfo.saveOrUpdate(order);

						// 判断是不是分销商品 调用接口 买票（凤凰山 皮影乐园）
						isDistribute(order);
						isNhTicket(order);
						// 中青旅==========================================================================================================
						isAoYou(order);
						// 中青旅==========================================================================================================
						if(order.getUserId()!=0){
							sendMessage(order);
						}
						
					}
				}
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!" + i + "个订单。订单号：" + tradeNum);
				tempMap.remove(tradeNum);
			} else {
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		} else {
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}

		System.out.println("微信返回字符串:" + stringBuffer);

		return stringBuffer.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/refundreport", method = RequestMethod.POST)
	public String refundreport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		// request.getParameter(arg0);
		System.out.println("*****************wxreport****************");
		// Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}
		/*
		 * // 事发突然...我要瞎写了... OrderInfoPO oPo =
		 * conn_orderInfo.getByRoderNo(refundNum); long nhProductId = 2469; if
		 * (oPo.getProductId() == nhProductId) { List<NhEticketsPo> NhList =
		 * nhEticketsDao.getByOrderId(oPo.getId()); List<String> snList = new
		 * ArrayList<String>(); for (NhEticketsPo nhEticketsPo : NhList) {
		 * snList.add(nhEticketsPo.getSn()); } String result =
		 * TravelService.orderRefund(refundNum, oPo.getTargetId(), snList,
		 * NhList.size()); Map<String, Object> resultMap =
		 * JSONUtil.toBean(JSONUtil.parse(result), Map.class, false);
		 * 
		 * if (resultMap.get("rspCode").equals("200") &&
		 * resultMap.get("rspDesc").equals("请求验证成功")) {
		 * System.out.println(result); System.out.println("南湖申请退票....等待审核中"); }
		 * 
		 * } // 上面是我瞎写的...有待测试.......
		 */ GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processRefundResponseXml(xml);
		String returncode = respData.get("return_code");
		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			// 获取订单号
			String tradeNum = respData.get("out_trade_no");
			String refundNum = respData.get("out_refund_no");

			String[] rIds = tradeNum.split("A");
			if (tradeNum.indexOf("bundle") != -1) {
				Long bundleOrderId = Long.parseLong(tradeNum.split("-")[1]);
				BundleOrder bundleOrder = conn_bundleorder.get(bundleOrderId);
				rIds = bundleOrder.getOrderStr().split("A");
			}
			for (String ridStr : rIds) { // 退款
				OrderInfoPO order = conn_orderInfo.get(Long.parseLong(ridStr));
				order.setOrderState(OrderStateType.REFUNDED);
				conn_orderInfo.save(order);
			}
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("SUCCESS");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("OK");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付退款成功!订单号：" + tradeNum);

			return stringBuffer.toString();
		}
		stringBuffer.append("<xml><return_code><![CDATA[");
		stringBuffer.append("FAIL");
		stringBuffer.append("]]></return_code>");
		stringBuffer.append("<return_msg><![CDATA[");
		stringBuffer.append("fail");
		stringBuffer.append("]]></return_msg>");
		return stringBuffer.toString();
	}
	
	@Autowired
	TableStatusDAO conn_tableStatus;
	@ResponseBody
	@RequestMapping(value = "/refundtablereport", method = RequestMethod.POST)
	public String refundtablereport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		// request.getParameter(arg0);
		System.out.println("*****************wxreport****************");
		// Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}
	    GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processRefundResponseXml(xml);
		String returncode = respData.get("return_code");
		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			// 获取订单号
			String tradeNum = respData.get("out_trade_no");
			String refundNum = respData.get("out_refund_no");

			String[] rIds = tradeNum.split("-");
			
			
			TableStatusPO order = conn_tableStatus.get(Long.parseLong(rIds[1]));
			order.setDishState("refunded");
			conn_tableStatus.save(order);
			
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("SUCCESS");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("OK");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付退款成功!订单号：" + tradeNum);

			return stringBuffer.toString();
		}
		stringBuffer.append("<xml><return_code><![CDATA[");
		stringBuffer.append("FAIL");
		stringBuffer.append("]]></return_code>");
		stringBuffer.append("<return_msg><![CDATA[");
		stringBuffer.append("fail");
		stringBuffer.append("]]></return_msg>");
		return stringBuffer.toString();
	}

	// 中青旅==========================================================================================================
	/**
	 * 对接遨游系统订单支付
	 * 
	 * @param order
	 * @throws Exception 
	 */
	public void isAoYou(OrderInfoPO order) throws Exception {
		//世园会
		Long productId = order.getProductId();
		if(AoyouIDUtil.isSyhID(productId.toString())){
			AoYouOrderPO aoYouOrderPO = aoYouOrderDao.getByOrderNo(order.getOrderNO());
			JSONObject syhOrder = AoYouV1Service.submitOrder(aoYouOrderPO.getSaleorder_no(), aoYouOrderPO.getMobile_no());
			System.out.println("支付世园会票务订单返回结果:" + syhOrder);
			JSONObject errdata = JSON.parseObject(syhOrder.get("errdata").toString());
			aoYouOrderPO.setConfirm_code(errdata.getString("confirm_code"));
			aoYouOrderDao.saveOrUpdate(aoYouOrderPO);
		}
		
		//冰雪
		if(AoyouIDUtil.isBxID(productId.toString())){
			AoYouOrderPO aoYouOrderPO = aoYouOrderDao.getByOrderNo(order.getOrderNO());
			JSONObject syhOrder = AoYouV2Service.submitOrder(aoYouOrderPO.getOrderno(), aoYouOrderPO.getMobile_no());
			System.out.println("支付冰雪票务订单返回结果:" + syhOrder);
			JSONObject errdata = JSON.parseObject(syhOrder.get("errdata").toString());
			aoYouOrderPO.setConfirm_code(errdata.getString("confirm_code"));
			aoYouOrderDao.saveOrUpdate(aoYouOrderPO);
		}
	}
	// 中青旅==========================================================================================================
	
	/**
	 * 启明芯南湖票
	 * 
	 * @param order
	 */
	public void isNhTicket(OrderInfoPO order) {
		long nhProductId = 2469;
		if (order.getProductId() == nhProductId) {
			System.out.println("进入南湖票务支付订单中....");
			// 支付回调 result
			String result = TravelService.payOrder(order.getTargetId());
			System.out.println("南湖支付回调"+result);
			Map<String, Object> resultMap = JSONUtil.toBean(JSONUtil.parse(result), Map.class, false);
			if (resultMap.get("rspCode").equals("200")) {
				System.out.println("回调成功 开始发送票务已支付");
				String object = com.alibaba.fastjson.JSON.toJSONString(resultMap.get("etickets"));
				JSONArray jsonArray = JSONArray.parseArray(object.toString());
				// 支付订单回调成功 添加二维码信息到NhEtickets表中
				for (int i = 0; i < jsonArray.size(); i++) {

					List<NhEticketsPo> list = nhEticketsDao.getByOrderId(order.getId());
					if (list == null||list.size()<jsonArray.size()) {
						NhEticketsPo nhEticketsPo = new NhEticketsPo();
						nhEticketsPo.setIsInUse(0);
						nhEticketsPo.setOrderId(order.getId());
						nhEticketsPo.setSn(jsonArray.getJSONObject(i).get("sn").toString());
						nhEticketsPo.setUrl(jsonArray.getJSONObject(i).get("url").toString());
						nhEticketsDao.save(nhEticketsPo);
					}

				}
				System.out.println("二维码信息存储成功！");
			}
		}
	}

	/**
	 * 判断是不是分销商品 天使同城的对接 （凤凰山）（皮影乐园）
	 * 
	 * @return
	 */
	private void isDistribute(OrderInfoPO order) {
		System.out.println("进行判断是否分销商品");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long merchatId = order.getShopId();
		if (order.getProductId() == 0)
			return;
		ProductPO product = conn_product.get(order.getProductId());
		String distributeId = product.getDistributeId();
		if (distributeId == null || distributeId == "") {
			System.out.println("判断此商品不是分销商品");
		} else {
			System.out.println("判断此商品是分销商品");
			String id = order.getId().toString();
			String userName = messageDAO.getByUserId(order.getUserId()).getName();
			String buynum = String.valueOf(order.getProductNum());
			String userTel = messageDAO.getByUserId(order.getUserId()).getPhone();
			String startDate = df.format(order.getOrderBookDate());
			String result = "";
			if (merchatId == 358) {
				System.out.println("调用了凤凰山的接口");
				result = TianShiTongChengAPI.sendFHSPost(id, distributeId, buynum, userName, userTel, startDate);
			} else if (merchatId == 386) {
				System.out.println("调用了皮影乐园的接口");
				result = TianShiTongChengAPI.sendPYLYPost(id, distributeId, buynum, userName, userTel, startDate);
			} else if(merchatId==40){
				System.out.println("调用了清东陵的接口");

				List<MessageMiddleClientPO> clientPOs = conn_messageclient.findByField("orderId", order.getId());
				MessagePO messagePO = null;
				if (clientPOs != null && !clientPOs.isEmpty()) {
					messagePO = conn_message.get(clientPOs.get(0).getMessageId());
				}
				
				 
				try {
					String resultqdl=QingDLAppUtil.orderSubmit("89130", "213536", order.getUuid(), order.getProductPrice()+"", 
							order.getProductNum()+"", order.getOrderBookDate(), "", messagePO!=null?messagePO.getPhone():"", 
									messagePO!=null?messagePO.getName():"",messagePO!=null?messagePO.getPhone():"", 
									"0", "0",  "0", "", "", "0", "0", messagePO!=null?messagePO.getNumber():"", "", "");
					
					JSONObject parseObject = JSON.parseObject(resultqdl);
					String qrcode = parseObject.get("UUqrcodeIMG").toString();
					String orders_id = parseObject.get("UUordernum").toString();
					order.setDistributeQcode(qrcode);
					order.setDistributeId(orders_id);
					conn_orderInfo.saveOrUpdate(order);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (merchatId == 358||merchatId == 386) {
				System.out.println("接口返回参数：");
				System.err.println(result);
				JSONObject parseObject = JSON.parseObject(result);
				String success = parseObject.get("success").toString();
				if (success.equals("true")) {
					System.out.println("接口调用成功 获取qcode存起来");
					String info = parseObject.get("info").toString();
					JSONObject infojson = JSON.parseObject(info);
					String qrcode = infojson.get("qrcode").toString();
					String orders_id = infojson.get("id").toString();
					order.setDistributeQcode(qrcode);
					order.setDistributeId(orders_id);
					conn_orderInfo.saveOrUpdate(order);
					System.out.println("购买成功");
				} else {
					System.out.println("接口调用失败");
				}
			}
			
			
		}
	}

	@RequestMapping(value = "/parkrefund", method = RequestMethod.POST)
	public String parkreport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		// Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				int i = 0;
				// 获取订单号
				String tradeNum = respData.get("out_trade_no");
				String refundNum = respData.get("out_refund_no");
				/*
				 * if(refundNum!=null&&refundNum.indexOf("refund")!=-1){
				 * String[] rIds = tradeNum.split("A");
				 * if(tradeNum.indexOf("bundle")!=-1){ Long
				 * bundleOrderId=Long.parseLong(tradeNum.split("-")[1]);
				 * BundleOrder bundleOrder=conn_bundleorder.get(bundleOrderId);
				 * rIds=bundleOrder.getOrderStr().split("A");
				 * 
				 * } for (String ridStr : rIds) { //退款 OrderInfoPO order =
				 * conn_orderInfo.get(Long.parseLong(ridStr));
				 * order.setOrderState(OrderStateType.REFUNDED);
				 * conn_orderInfo.save(order); sendMessage(order); }
				 * 
				 * stringBuffer.append("<xml><return_code><![CDATA[");
				 * stringBuffer.append("SUCCESS");
				 * stringBuffer.append("]]></return_code>");
				 * stringBuffer.append("<return_msg><![CDATA[");
				 * stringBuffer.append("OK");
				 * stringBuffer.append("]]></return_msg>");
				 * System.out.println("微信支付退款成功!订单号："+tradeNum); return
				 * stringBuffer.toString(); } String[] orderIds =
				 * tradeNum.split("A"); //orderNo="bundle-"+order.getId();
				 * if(tradeNum.indexOf("bundle")!=-1){ Long
				 * bundleOrderId=Long.parseLong(tradeNum.split("-")[1]);
				 * BundleOrder bundleOrder=conn_bundleorder.get(bundleOrderId);
				 * orderIds=bundleOrder.getOrderStr().split("A"); } for (String
				 * orderId : orderIds) { i=i+1; OrderInfoPO order =
				 * conn_orderInfo.get(Long.parseLong(orderId));
				 * if(order.getOrderState().toString().equals("NOTPAY")){ //微信支付
				 * order.setPayMode(PayType.WEICHAT); order.setPayDate(new
				 * Date()); //生成验单码,和二维码图片 String ydNO = ydNoCode(orderId);
				 * order.setYdNO(ydNO); //支付状态、减库存
				 * order.setOrderState(OrderStateType.PAYSUCCESS); ProductPO
				 * product = conn_product.get(order.getProductId()); if(product
				 * != null){ //long productNum = order.getProductNum();
				 * //product.setProductStock(product.getProductStock()-
				 * productNum); //conn_product.saveOrUpdate(product); }
				 * conn_orderInfo.saveOrUpdate(order); sendMessage(order); } }
				 */
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!" + i + "个订单。订单号：" + tradeNum);
			} else {
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		} else {
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}

		System.out.println("微信返回字符串:" + stringBuffer);

		return stringBuffer.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/payreportpark", method = RequestMethod.POST)
	public String paypark(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				// 获取订单号
				String vehicle = null;
				String tradeNum = respData.get("out_trade_no");
				Long orderId = Long.parseLong(tradeNum.split("-")[1]);
				Long attactionsId = Long.parseLong(tradeNum.split("-")[2]);

				// orderNo="bundle-"+order.getId();

				// OrderInfoPO order =
				// conn_orderInfo.get(Long.parseLong(orderId));
				// if(order.getOrderState().toString().equals("NOTPAY")){

				// }
				OrderPO userByid = Order.getform(orderId);
				if ("NOTPAY".equals(userByid.getOrderStatus())) {
					// 修改 总 车位 剩余车位
					AttractionsParkingPO att = Attra_ctions.getUid(attactionsId);
					int remain = att.getUsedParking();
					att.setUsedParking(remain - 1);
					Attra_ctions.saveOrUpdate(att);

					// 生成 二维码 修改订单状态
					if (userByid != null) {
						String ydNO = ydNoCode(userByid.getId() + "");
						userByid.setOrderStatus("PAYSUCCESS");
						userByid.setPath(ydNO);
						Order.saveOrUpdate(userByid);
						// 修改 车位 已使用
						int parkingNumber = userByid.getParkingNumber();
						String parkingLayer = userByid.getParkingLayer();
						String district = userByid.getParkingDistrict();

						CarPositionPO userName = Car_Position.getAmend(attactionsId, parkingLayer, district);
						long id = userName.getId();

						ParkingPositionPO getTruck = Parking_Position.getNumber(id, parkingNumber);
						getTruck.setUseCondition(1);
						Parking_Position.saveOrUpdate(getTruck);

						stringBuffer.append("<xml><return_code><![CDATA[");
						stringBuffer.append("SUCCESS");
						stringBuffer.append("]]></return_code>");
						stringBuffer.append("<return_msg><![CDATA[");
						stringBuffer.append("OK");
						stringBuffer.append("]]></return_msg>");
						System.out.println("微信支付付款成功!订单号：" + tradeNum);
					}
				}
			} else {
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		} else {
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}

		System.out.println("微信返回字符串:" + stringBuffer);

		return stringBuffer.toString();
	}

	@Autowired
	LiveTipGiftDAO conn_liveTipGiftDao;
	
	@Autowired
	LiveMessageDAO conn_liveMessage;
	
	
	@ResponseBody
	@RequestMapping(value = "/giftPayreport", method = RequestMethod.POST)
	// TODO
	public String payprot(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				// 获取订单号
				String vehicle = null;
				String tradeNum = respData.get("out_trade_no");
				long orderId=Long.parseLong(tradeNum.split("-")[1]);
				LiveTipGiftPO order = conn_liveTipGiftDao.get(orderId);
				if(order.getFlg()==0){
					order.setFlg(1);
					conn_liveTipGiftDao.save(order);
					LiveMessagePO liveMessagePO=new LiveMessagePO();
					liveMessagePO.setUserName(order.getUsername());
					liveMessagePO.setUserId(order.getUserId());
					liveMessagePO.setLiveId(order.getLiveid());
					liveMessagePO.setMessage("赠送了礼物:【"+order.getGiftname()+"】x"+order.getGiftnumber());
					conn_liveMessage.save(liveMessagePO);
				}
				
				
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!订单号：" + tradeNum);
			} else {
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		} else {
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}

		System.out.println("微信返回字符串:" + stringBuffer);

		return stringBuffer.toString();
	}

	/**
	 * 钱包充值支付 张羽
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/walletPayreport", method = RequestMethod.POST)
	// TODO
	public String walletPayProt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				// 获取订单号
				String vehicle = null;
				String tradeNum = respData.get("out_trade_no");
				// 获取订单号
				Long orderId = Long.parseLong(tradeNum.split("-")[1]);
				OrderInfoPO order = conn_orderInfo.get(orderId);
				// 生成验单码,和二维码图片
				String ydNO = ydNoCode(order.getOrderNO());
				order.setYdNO(ydNO);
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!订单号：" + tradeNum);
			} else {
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		} else {
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}

		System.out.println("微信返回字符串:" + stringBuffer);

		return stringBuffer.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/payreportrenew", method = RequestMethod.POST)
	// TODO
	public String portrenew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				// 获取订单号
				String tradeNum = respData.get("out_trade_no");
				Long orderId = Long.parseLong(tradeNum.split("-")[1]);
				// 获取商品订单号
				String transaction = respData.get("transaction_id");
				// 查询 订单信息
				OrderPO orderinfor = Order.getform(orderId);
				String booking = orderinfor.getBookingTime(); // 入场时间
				Long attid = orderinfor.getAttractionsId(); // 景区id
				String duetime = orderinfor.getDueTime(); // 离场时间说
				String status = orderinfor.getOrderStatus();// 订单状态
				String layer = orderinfor.getParkingLayer();// 层
				String district = orderinfor.getParkingDistrict();// 区
				Integer number = orderinfor.getParkingNumber();// 车位编号
				String name = orderinfor.getParkingName();// 车场名称
				String plate = orderinfor.getPlatenumber();// 车牌号
				Long userid = orderinfor.getOrderId(); // 用户id
				orderinfor.setOrderStatus("PAST");
				Order.saveOrUpdate(orderinfor);

				// 新创订单
				OrderPO odp = new OrderPO();
				odp.setBookingTime(booking);
				odp.setDueTime(duetime);
				odp.setStoppingTime(0.0);
				odp.setOrderStatus(status);
				odp.setParkingLayer(layer);
				odp.setParkingName(name);
				odp.setParkingDistrict(district);
				odp.setParkingNumber(number);
				odp.setAttractionsId(attid);
				odp.setParkingCost(0);
				odp.setOrderId(userid);
				odp.setPlatenumber(plate);
				odp.setOrderNo(tradeNum);
				odp.setCommodityNumber(transaction);
				Order.saveOrUpdate(odp);

				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!订单号：" + tradeNum);
			} else {
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		} else {
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}

		System.out.println("微信返回字符串:" + stringBuffer);

		return stringBuffer.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/tablePayment", method = RequestMethod.POST)
	public String tablePayment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				// 获取订单号
				String tradeNum = respData.get("out_trade_no");
				Long orderId = Long.parseLong(tradeNum.split("-")[1]);
				// 查询 订单信息
				TableStatusPO TableStatus = Table_Status.getByField("id", orderId);
				if (TableStatus != null && !"PAYSUCCESS".equals(TableStatus.getTableState())) {
					TableStatus.setTableState("PAYSUCCESS");
					// 生成验单码,和二维码图片
					String ydNO = ydNoCode(orderId + "");
					TableStatus.setYdNO(ydNO);
					TablePO addpo = Table.getByField("id", TableStatus.getTableId());

					// 用户推送消息
					Double amount = Double.parseDouble(addpo.getBookprice() + "") / 100;
					DecimalFormat df = new DecimalFormat("0.00");
					UserInfoPO buyUser = conn_user.get(TableStatus.getUserId());
					if (buyUser != null) {
						JSONObject obj = new JSONObject();
						obj.put("touser", buyUser.getUserOpenID());
						obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
						obj.put("url", "");
						JSONObject microProObj = new JSONObject();
						microProObj.put("appid", "");
						microProObj.put("pagepath", "");
						obj.put("miniprogram", microProObj);
						JSONObject dataObject = new JSONObject();
						JSONObject firstObj = new JSONObject();
						firstObj.put("value", "您的订单支付成功");
						firstObj.put("color", "");
						dataObject.put("first", firstObj);

						JSONObject nameObj = new JSONObject();
						nameObj.put("value", buyUser.getUserNickname());
						nameObj.put("color", "");
						dataObject.put("keyword1", nameObj);

						JSONObject accountTypeObj = new JSONObject();
						accountTypeObj.put("value", TableStatus.getId());
						accountTypeObj.put("color", "");
						dataObject.put("keyword2", accountTypeObj);

						JSONObject accountObj = new JSONObject();
						accountObj.put("value", df.format(amount));
						accountObj.put("color", "");
						dataObject.put("keyword3", accountObj);
						JSONObject timeObj = new JSONObject();
						timeObj.put("color", "");
						dataObject.put("keyword4", timeObj);
						JSONObject remarkObj = new JSONObject();
						remarkObj.put("value", "感谢使用过来玩服务");
						remarkObj.put("color", "");
						dataObject.put("remark", remarkObj);
						obj.put("data", dataObject);
						SendMsgUtil.sendTemplate(obj.toJSONString());
					}

					// 商户推送消息
					// UserInfoPO userInfoPO=merchantPO.getUser();
					List<MerchantUser> merchantUsers = conn_merchantUser.findByField("merchantId",
							TableStatus.getMerchantId());
					try {
						for (MerchantUser merchantUser : merchantUsers) {
							UserInfoPO userInfoPO = conn_user.get(merchantUser.getUserId());
							if (userInfoPO == null) {
								continue;
							}
							JSONObject obj = new JSONObject();
							obj.put("touser", userInfoPO.getUserOpenID());
							obj.put("template_id", "Av9VDHMyVJLuphngV9ZndeHPNMOE3JYkm2W3-OrJDfs");
							obj.put("url", "");
							JSONObject microProObj = new JSONObject();
							microProObj.put("appid", "");
							microProObj.put("pagepath", "");
							obj.put("miniprogram", microProObj);
							JSONObject dataObject = new JSONObject();
							JSONObject firstObj = new JSONObject();
							firstObj.put("value", "新的过来玩订单");
							firstObj.put("color", "");
							dataObject.put("first", firstObj);

							JSONObject nameObj = new JSONObject();
							nameObj.put("value", buyUser.getUserNickname());
							nameObj.put("color", "");
							dataObject.put("keyword1", TableStatus.getUserName());

							JSONObject accountTypeObj = new JSONObject();
							accountTypeObj.put("value", TableStatus.getId());
							accountTypeObj.put("color", "");
							dataObject.put("keyword2", TableStatus.getUserPhone());

							JSONObject accountObj = new JSONObject();
							accountObj.put("value", df.format(amount));
							accountObj.put("color", "");
							dataObject.put("keyword3", addpo.getTablename());
							JSONObject timeObj = new JSONObject();
							timeObj.put("color", "");
							dataObject.put("keyword4", TableStatus.getTableDate() + " , " + TableStatus.getType());
							JSONObject remarkObj = new JSONObject();
							remarkObj.put("value", "预订");
							remarkObj.put("color", "");
							dataObject.put("remark", remarkObj);
							obj.put("data", dataObject);
							SendMsgUtil.sendTemplate(obj.toJSONString());
						}
					} catch (Exception e) {
						// TODO: handle exception

					}
					Table_Status.saveOrUpdate(TableStatus);
				}
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!订单号：" + tradeNum);
			} else {
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		} else {
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}

		System.out.println("微信返回字符串:" + stringBuffer);

		return stringBuffer.toString();
	}

	@Autowired
	private MealListDao MealList;

	@ResponseBody
	@RequestMapping(value = "/buyDishPayment", method = RequestMethod.POST)
	public String buyDish(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);
		StringBuffer stringBuffer = new StringBuffer();
		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				// 获取订单号
				String tradeNum = respData.get("out_trade_no");
				Long orderId = Long.parseLong(tradeNum.split("-")[1]);
				List<TableStatusPO> TableStatus = Table_Status.findByField("id", orderId);
				String table = "";
				if (TableStatus != null && TableStatus.size() > 0
						&& !"PAYSUCCESS".equals(TableStatus.get(0).getDishState())) {
					if (TableStatus.get(0).getYdNO() == null || TableStatus.get(0).getYdNO() == "") {
						String ydNO = ydNoCode(orderId + "");
						TableStatus.get(0).setYdNO(ydNO);
					}
					TableStatus.get(0).setDishState("PAYSUCCESS");
					List<MealListPo> MealListPo = MealList.findByDistributor(
							Long.parseLong(TableStatus.get(0).getUserId()), TableStatus.get(0).getMerchantId());
					String meal = null;
					for (MealListPo mealListPo2 : MealListPo) {
						mealListPo2.setTableId(TableStatus.get(0).getId());
						MealList.saveOrUpdate(mealListPo2);
						List<ProductPO> product = conn_product.findByField("id", mealListPo2.getProductId());
						if (meal == null) {
							meal = product.get(0).getProductName() + "X" + mealListPo2.getMealAmount();
						} else {
							meal += "," + product.get(0).getProductName() + "X" + mealListPo2.getMealAmount();
						}
					}
					if (!"0".equals(TableStatus.get(0).getTableId() + "")) {
						List<TablePO> addpo = null;
						addpo = Table.findByField("id", TableStatus.get(0).getTableId());
						table = "用户已订桌 订桌号:" + addpo.get(0).getTableNo() + " 房间名称 : " + addpo.get(0).getTablename()
								+ " ,用户姓名:" + TableStatus.get(0).getUserName() + " ,用户手机号:"
								+ TableStatus.get(0).getUserPhone();
					} else {
						table = "用户未订桌,请留好桌位 用户姓名:" + TableStatus.get(0).getUserName() + " ,用户手机号:"
								+ TableStatus.get(0).getUserPhone();
					}
					// 用户推送消息
					Double amount = Double.parseDouble(TableStatus.get(0).getDishMoney() + "") / 100;
					DecimalFormat df = new DecimalFormat("0.00");
					UserInfoPO buyUser = conn_user.get(Long.parseLong(TableStatus.get(0).getUserId()));
					if (buyUser != null) {
						JSONObject obj = new JSONObject();
						obj.put("touser", buyUser.getUserOpenID());
						obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
						obj.put("url", "");
						JSONObject microProObj = new JSONObject();
						microProObj.put("appid", "");
						microProObj.put("pagepath", "");
						obj.put("miniprogram", microProObj);
						JSONObject dataObject = new JSONObject();
						JSONObject firstObj = new JSONObject();
						firstObj.put("value", "您的订单支付成功");
						firstObj.put("color", "");
						dataObject.put("first", firstObj);

						JSONObject nameObj = new JSONObject();
						nameObj.put("value", buyUser.getUserNickname());
						nameObj.put("color", "");
						dataObject.put("keyword1", nameObj);

						JSONObject accountTypeObj = new JSONObject();
						accountTypeObj.put("value", TableStatus.get(0).getId());
						accountTypeObj.put("color", "");
						dataObject.put("keyword2", accountTypeObj);

						JSONObject accountObj = new JSONObject();
						accountObj.put("value", df.format(amount));
						accountObj.put("color", "");
						dataObject.put("keyword3", accountObj);
						JSONObject timeObj = new JSONObject();
						timeObj.put("color", "");
						dataObject.put("keyword4", timeObj);
						JSONObject remarkObj = new JSONObject();
						remarkObj.put("value", "感谢使用过来玩服务");
						remarkObj.put("color", "");
						dataObject.put("remark", remarkObj);
						obj.put("data", dataObject);
						SendMsgUtil.sendTemplate(obj.toJSONString());
					}

					// 商户推送消息
					// UserInfoPO userInfoPO=merchantPO.getUser();
					List<MerchantUser> merchantUsers = conn_merchantUser.findByField("merchantId",
							TableStatus.get(0).getMerchantId());
					try {
						for (MerchantUser merchantUser : merchantUsers) {
							UserInfoPO userInfoPO = conn_user.get(merchantUser.getUserId());
							if (userInfoPO == null) {
								continue;
							}
							JSONObject obj = new JSONObject();
							obj.put("touser", userInfoPO.getUserOpenID());
							obj.put("template_id", "FqlDkv8NKzKkDaWDYpY8V3t5krEXB86AeGkkJRbbws0");
							obj.put("url", "");
							JSONObject microProObj = new JSONObject();
							microProObj.put("appid", "");
							microProObj.put("pagepath", "");
							obj.put("miniprogram", microProObj);
							JSONObject dataObject = new JSONObject();
							JSONObject firstObj = new JSONObject();
							firstObj.put("value", "新的过来玩订单");
							firstObj.put("color", "");
							dataObject.put("first", firstObj);

							JSONObject nameObj = new JSONObject();
							nameObj.put("value", buyUser.getUserNickname());
							nameObj.put("color", "");
							dataObject.put("keyword1", TableStatus.get(0).getDishMoney());

							JSONObject accountTypeObj = new JSONObject();
							accountTypeObj.put("value", TableStatus.get(0).getId());
							accountTypeObj.put("color", "");
							dataObject.put("keyword2", meal);

							JSONObject accountObj = new JSONObject();
							accountObj.put("value", df.format(amount));
							accountObj.put("color", "");
							dataObject.put("keyword3",
									TableStatus.get(0).getTableDate() + " , " + TableStatus.get(0).getType());
							JSONObject timeObj = new JSONObject();
							timeObj.put("color", "");
							dataObject.put("keyword4", table);
							JSONObject remarkObj = new JSONObject();
							remarkObj.put("value", "预订");
							remarkObj.put("color", "");
							dataObject.put("remark", remarkObj);
							obj.put("data", dataObject);
							SendMsgUtil.sendTemplate(obj.toJSONString());
						}
					} catch (Exception e) {
						// TODO: handle exception

					}

					Table_Status.saveOrUpdate(TableStatus.get(0));

				}

				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!订单号：" + tradeNum);
			} else {
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		} else {
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}

		System.out.println("微信返回字符串:" + stringBuffer);

		return stringBuffer.toString();
	}

	@Autowired
	UserInfoDAO conn_user;
	@Autowired
	MerchantDAO conn_merchant;
	@Autowired
	MerchantUserDao conn_merchantUser;
	@Autowired
	MessageMiddleClientDao conn_messageclient;
	@Autowired
	MessageDAO conn_message;
	@Autowired
	private AddressDAO conn_address;

	private void sendMessage(OrderInfoPO orderInfoPO) {

		ProductPO productPO = conn_product.get(orderInfoPO.getProductId());
		MerchantPO merchantPO = conn_merchant.get(orderInfoPO.getShopId());

		// 用户推送消息
		Double amount = Double.parseDouble(orderInfoPO.getPayMoney() + "") / 100;
		DecimalFormat df = new DecimalFormat("0.00");
		UserInfoPO buyUser = conn_user.get(orderInfoPO.getUserId());
		if (buyUser != null) {
			JSONObject obj = new JSONObject();
			obj.put("touser", buyUser.getUserOpenID());
			obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
			obj.put("url", "");
			JSONObject microProObj = new JSONObject();
			microProObj.put("appid", "");
			microProObj.put("pagepath", "");
			obj.put("miniprogram", microProObj);
			JSONObject dataObject = new JSONObject();
			JSONObject firstObj = new JSONObject();
			firstObj.put("value", "您的订单支付成功");
			firstObj.put("color", "");
			dataObject.put("first", firstObj);

			JSONObject nameObj = new JSONObject();
			nameObj.put("value", buyUser.getUserNickname());
			nameObj.put("color", "");
			dataObject.put("keyword1", nameObj);

			JSONObject accountTypeObj = new JSONObject();
			accountTypeObj.put("value", orderInfoPO.getId());
			accountTypeObj.put("color", "");
			dataObject.put("keyword2", accountTypeObj);

			JSONObject accountObj = new JSONObject();
			accountObj.put("value", df.format(amount));
			accountObj.put("color", "");
			dataObject.put("keyword3", accountObj);
			JSONObject timeObj = new JSONObject();
			timeObj.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
			timeObj.put("color", "");
			dataObject.put("keyword4", timeObj);
			JSONObject remarkObj = new JSONObject();
			remarkObj.put("value", "感谢使用过来玩服务");
			remarkObj.put("color", "");
			dataObject.put("remark", remarkObj);
			obj.put("data", dataObject);
			SendMsgUtil.sendTemplate(obj.toJSONString());
		}

		// 商户推送消息
		// UserInfoPO userInfoPO=merchantPO.getUser();
		List<MerchantUser> merchantUsers = conn_merchantUser.findByField("merchantId", merchantPO.getId());
		try {
			for (MerchantUser merchantUser : merchantUsers) {
				UserInfoPO userInfoPO = conn_user.get(merchantUser.getUserId());
				if (userInfoPO == null) {
					continue;
				}
				JSONObject obj = new JSONObject();
				obj.put("touser", userInfoPO.getUserOpenID());
				obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
				obj.put("url", "");
				JSONObject microProObj = new JSONObject();
				microProObj.put("appid", "");
				microProObj.put("pagepath", "");
				obj.put("miniprogram", microProObj);
				JSONObject dataObject = new JSONObject();
				JSONObject firstObj = new JSONObject();
				firstObj.put("value", "新的过来玩订单");
				firstObj.put("color", "");
				dataObject.put("first", firstObj);

				JSONObject nameObj = new JSONObject();
				nameObj.put("value", buyUser.getUserNickname());
				nameObj.put("color", "");
				dataObject.put("keyword1", nameObj);

				JSONObject accountTypeObj = new JSONObject();
				accountTypeObj.put("value", orderInfoPO.getOrderNO());
				accountTypeObj.put("color", "");
				dataObject.put("keyword2", accountTypeObj);

				JSONObject accountObj = new JSONObject();
				accountObj.put("value", df.format(amount));
				accountObj.put("color", "");
				dataObject.put("keyword3", accountObj);
				JSONObject timeObj = new JSONObject();
				timeObj.put("value",
						productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
				timeObj.put("color", "");
				dataObject.put("keyword4", timeObj);
				JSONObject remarkObj = new JSONObject();
				remarkObj.put("value", "请做好接待工作");
				remarkObj.put("color", "");
				dataObject.put("remark", remarkObj);
				obj.put("data", dataObject);
				SendMsgUtil.sendTemplate(obj.toJSONString());
			}

		} catch (Exception e) {
			// TODO: handle exception

		}

		// 过来玩工作人员

		// 用户姓名
		JSONObject nameObj = new JSONObject();

		List<MessageMiddleClientPO> clientPOs = conn_messageclient.findByField("orderId", orderInfoPO.getId());
		MessagePO messagePO = null;
		if (clientPOs != null && !clientPOs.isEmpty()) {
			messagePO = conn_message.get(clientPOs.get(0).getMessageId());
		}
		if (messagePO != null) {
			nameObj.put("value", messagePO.getName());
		} else if (conn_address.get(orderInfoPO.getMailAddress()) != null) {
			nameObj.put("value", conn_address.get(orderInfoPO.getMailAddress()).getConsigneeName());
		} else {
			nameObj.put("value", conn_user.get(orderInfoPO.getUserId()).getUserNickname());
		}
		nameObj.put("color", "");

		// remark
		String pNum = "";
		if (messagePO != null) {
			pNum = messagePO.getPhone();
		} else if (conn_address.get(orderInfoPO.getMailAddress()) != null) {
			pNum = conn_address.get(orderInfoPO.getMailAddress()).getConsigneePhone();
		}
		if (pNum == null || pNum.isEmpty()) {
			pNum = buyUser.getUserPhone();
		}

		JSONObject remarkObj = new JSONObject();
		if (conn_address.get(orderInfoPO.getMailAddress()) != null) {
			remarkObj.put("value", "请做好接待工作(用户电话:" + pNum + "  用户地址:"
					+ conn_address.get(orderInfoPO.getMailAddress()).getConsigneeAddress() + ")");
		} else {
			remarkObj.put("value", "请做好接待工作(用户电话:" + pNum + "  用户地址: 此用户未填写详细地址)");
		}
		remarkObj.put("color", "");

		JSONObject obj = new JSONObject();
		obj.put("touser", "opVUYv-uZbDTdwuJDUEPIK3SrUz8");
		obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
		obj.put("url", "");
		JSONObject microProObj = new JSONObject();
		microProObj.put("appid", "");
		microProObj.put("pagepath", "");
		obj.put("miniprogram", microProObj);
		JSONObject dataObject = new JSONObject();
		JSONObject firstObj = new JSONObject();
		firstObj.put("value", "新的过来玩订单");
		firstObj.put("color", "");
		dataObject.put("first", firstObj);

		// 用户姓名
		dataObject.put("keyword1", nameObj);

		JSONObject accountTypeObj = new JSONObject();
		accountTypeObj.put("value", orderInfoPO.getId());
		accountTypeObj.put("color", "");
		dataObject.put("keyword2", accountTypeObj);

		JSONObject accountObj = new JSONObject();
		accountObj.put("value", df.format(amount));
		accountObj.put("color", "");
		dataObject.put("keyword3", accountObj);
		JSONObject timeObj = new JSONObject();
		timeObj.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
		timeObj.put("color", "");
		dataObject.put("keyword4", timeObj);
		// remark
		dataObject.put("remark", remarkObj);
		obj.put("data", dataObject);
		SendMsgUtil.sendTemplate(obj.toJSONString());

		// opVUYv9LtqKAbiaXInBqI01hlpYg

		JSONObject obj2 = new JSONObject();
		obj2.put("touser", "opVUYv_KDzCscwik3O4pnl64qYLU");
		obj2.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
		obj2.put("url", "");
		JSONObject microProObj2 = new JSONObject();
		microProObj2.put("appid", "");
		microProObj2.put("pagepath", "");
		obj2.put("miniprogram", microProObj2);
		JSONObject dataObject2 = new JSONObject();
		JSONObject firstObj2 = new JSONObject();
		firstObj2.put("value", "新的过来玩订单");
		firstObj2.put("color", "");
		dataObject2.put("first", firstObj2);

		// 用户姓名
		dataObject2.put("keyword1", nameObj);

		JSONObject accountTypeObj2 = new JSONObject();
		accountTypeObj2.put("value", orderInfoPO.getId());
		accountTypeObj2.put("color", "");
		dataObject2.put("keyword2", accountTypeObj2);

		JSONObject accountObj2 = new JSONObject();
		accountObj2.put("value", df.format(amount));
		accountObj2.put("color", "");
		dataObject2.put("keyword3", accountObj2);
		JSONObject timeObj2 = new JSONObject();
		timeObj2.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
		timeObj2.put("color", "");

		dataObject2.put("keyword4", timeObj2);

		dataObject2.put("remark", remarkObj);
		obj2.put("data", dataObject2);
		SendMsgUtil.sendTemplate(obj2.toJSONString());

		JSONObject obj3 = new JSONObject();
		obj3.put("touser", "opVUYv3nODu4o8ejUmHoiluoEHec");
		obj3.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
		obj3.put("url", "");
		JSONObject microProObj3 = new JSONObject();
		microProObj3.put("appid", "");
		microProObj3.put("pagepath", "");
		obj3.put("miniprogram", microProObj3);
		JSONObject dataObject3 = new JSONObject();
		JSONObject firstObj3 = new JSONObject();
		firstObj3.put("value", "新的过来玩订单");
		firstObj3.put("color", "");
		dataObject3.put("first", firstObj3);

		// 用户姓名
		dataObject3.put("keyword1", nameObj);

		JSONObject accountTypeObj3 = new JSONObject();
		accountTypeObj3.put("value", orderInfoPO.getId());
		accountTypeObj3.put("color", "");
		dataObject3.put("keyword2", accountTypeObj3);

		JSONObject accountObj3 = new JSONObject();
		accountObj3.put("value", df.format(amount));
		accountObj3.put("color", "");
		dataObject3.put("keyword3", accountObj3);
		JSONObject timeObj3 = new JSONObject();
		timeObj3.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
		timeObj3.put("color", "");

		dataObject3.put("keyword4", timeObj3);

		dataObject3.put("remark", remarkObj);
		obj3.put("data", dataObject3);
		SendMsgUtil.sendTemplate(obj3.toJSONString());

		JSONObject obj4 = new JSONObject();
		obj4.put("touser", "opVUYv1VUjjOlwYUeLj4hJRcTZPE");
		obj4.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
		obj4.put("url", "");
		JSONObject microProObj4 = new JSONObject();
		microProObj4.put("appid", "");
		microProObj4.put("pagepath", "");
		obj4.put("miniprogram", microProObj2);
		JSONObject dataObject4 = new JSONObject();
		JSONObject firstObj4 = new JSONObject();
		firstObj4.put("value", "新的过来玩订单");
		firstObj4.put("color", "");
		dataObject4.put("first", firstObj4);

		// 用户姓名
		dataObject4.put("keyword1", nameObj);

		JSONObject accountTypeObj4 = new JSONObject();
		accountTypeObj4.put("value", orderInfoPO.getId());
		accountTypeObj4.put("color", "");
		dataObject4.put("keyword2", accountTypeObj4);

		JSONObject accountObj4 = new JSONObject();
		accountObj4.put("value", df.format(amount));
		accountObj4.put("color", "");
		dataObject4.put("keyword3", accountObj2);
		JSONObject timeObj4 = new JSONObject();
		timeObj4.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
		timeObj4.put("color", "");

		dataObject4.put("keyword4", timeObj4);

		dataObject4.put("remark", remarkObj);
		obj4.put("data", dataObject4);
		SendMsgUtil.sendTemplate(obj4.toJSONString());
		
		
		
		JSONObject obj5 = new JSONObject();
		obj5.put("touser", "opVUYv1Xq-iWZJ6Ukrmr3x-5vNF4");
		obj5.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
		obj5.put("url", "");
		JSONObject microProObj5 = new JSONObject();
		microProObj5.put("appid", "");
		microProObj5.put("pagepath", "");
		obj5.put("miniprogram", microProObj5);
		JSONObject dataObject5 = new JSONObject();
		JSONObject firstObj5 = new JSONObject();
		firstObj5.put("value", "新的过来玩订单");
		firstObj5.put("color", "");
		dataObject5.put("first", firstObj4);

		// 用户姓名
		dataObject4.put("keyword1", nameObj);

		JSONObject accountTypeObj5 = new JSONObject();
		accountTypeObj5.put("value", orderInfoPO.getId());
		accountTypeObj5.put("color", "");
		dataObject5.put("keyword2", accountTypeObj5);

		JSONObject accountObj5 = new JSONObject();
		accountObj5.put("value", df.format(amount));
		accountObj5.put("color", "");
		dataObject5.put("keyword3", accountObj2);
		JSONObject timeObj5 = new JSONObject();
		timeObj5.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
		timeObj5.put("color", "");

		dataObject5.put("keyword4", timeObj5);

		dataObject5.put("remark", remarkObj);
		obj5.put("data", dataObject5);
		SendMsgUtil.sendTemplate(obj5.toJSONString());
		
		
		JSONObject obj6 = new JSONObject();
		obj6.put("touser", "opVUYv5yGAOHbP8SYDPSjQxx8Wwk");
		obj6.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
		obj6.put("url", "");
		JSONObject microProObj6 = new JSONObject();
		microProObj6.put("appid", "");
		microProObj6.put("pagepath", "");
		obj6.put("miniprogram", microProObj6);
		JSONObject dataObject6 = new JSONObject();
		JSONObject firstObj6 = new JSONObject();
		firstObj6.put("value", "新的过来玩订单");
		firstObj6.put("color", "");
		dataObject6.put("first", firstObj6);

		// 用户姓名
		dataObject4.put("keyword1", nameObj);

		JSONObject accountTypeObj6 = new JSONObject();
		accountTypeObj6.put("value", orderInfoPO.getId());
		accountTypeObj6.put("color", "");
		dataObject6.put("keyword2", accountTypeObj6);

		JSONObject accountObj6 = new JSONObject();
		accountObj6.put("value", df.format(amount));
		accountObj6.put("color", "");
		dataObject6.put("keyword3", accountObj2);
		JSONObject timeObj6 = new JSONObject();
		timeObj6.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
		timeObj6.put("color", "");

		dataObject6.put("keyword4", timeObj6);

		dataObject6.put("remark", remarkObj);
		obj6.put("data", dataObject6);
		SendMsgUtil.sendTemplate(obj6.toJSONString());

	}

	@ResponseBody
	@RequestMapping(value = "/payreport/batch", method = RequestMethod.POST)
	public Map<String, Object> payBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦进来啦");
		// Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);
		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				// 并不好用，要求是一个返回xml格式的，先放在了map里吧
				ret.put("return_code", "SUCCESS");
				ret.put("return_msg", "OK");
				System.out.println("微信支付交易成功");

				// 获取订单号
				String orderId = URLDecoder.decode(respData.get("out_trade_no"));
				String[] idArr = orderId.split(",");
				List<Long> ids = new ArrayList<Long>();
				for (String id : idArr) {
					if (id != null) {
						ids.add(Long.valueOf(id));
					}
				}

				List<OrderInfoPO> orders = conn_orderInfo.getAllByIds(ids);
				for (OrderInfoPO order : orders) {
					if (order.getOrderState().toString().equals("NOTPAY")) {
						// 微信支付
						order.setPayMode(PayType.WEICHAT);

						// 生成验单码,和二维码图片
						String ydNO = ydNoCode(order.getOrderNO());
						order.setYdNO(ydNO);
						// 支付状态、减库存
						order.setOrderState(OrderStateType.PAYSUCCESS);
						ProductPO product = conn_product.get(order.getProductId());
						if (product != null) {
							long productNum = order.getProductNum();
							product.setProductStock(product.getProductStock() - productNum);
							conn_product.saveOrUpdate(product);
						}
						conn_orderInfo.saveOrUpdate(order);
					}
				}
			} else {
				ret.put("return_code", "FAIL");
				ret.put("return_msg", "交易失败");
				System.out.println("微信支付交易失败");
			}
		} else {
			ret.put("return_code", "FAIL");
			ret.put("return_msg", "签名失败");
			System.out.println("微信支付签名失败");
		}

		return ret;
	}

	@Autowired
	private CurrentRoomSateDao currentRoomDao;
	@Autowired
	private AddTheRoomDAO addTheRoom;
	@Autowired
	private MessageMiddleClientDao mesclient;

	/**
	 * 新版住房支付
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/payroomPrice", method = RequestMethod.POST)
	public String payRoomPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		System.out.println("*****************wxreport****************");
		// Mr.huang 2017/09/12 飞的好低的小蜜蜂
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String xml = "";
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			xml += tempStr;
			System.out.println(tempStr);
		}

		GuolaiwanWxPay wxPay = GuolaiwanWxPay
				.getInstance("http://" + WXContants.Website + "/website/wxreport/payroomPrice");
		Map<String, String> respData = wxPay.processResponseXml(xml);

		System.out.println("返回的参数：" + respData.toString());

		String returncode = respData.get("return_code");
		String resultcode = respData.get("result_code");
		System.out.println("returncode是：" + returncode + ";resultcode是" + resultcode);
		StringBuffer stringBuffer = new StringBuffer();

		if (returncode.equals("SUCCESS")) {
			if (resultcode.equals("SUCCESS")) {
				int i = 0;
				// 获取订单号
				String tradeNum = respData.get("out_trade_no");
				String refundNum = respData.get("out_refund_no");

				if (refundNum != null && refundNum.indexOf("refund") != -1) {
					String[] rIds = tradeNum.split("A");
					if (tradeNum.indexOf("bundle") != -1) {
						Long bundleOrderId = Long.parseLong(tradeNum.split("-")[1]);
						BundleOrder bundleOrder = conn_bundleorder.get(bundleOrderId);
						rIds = bundleOrder.getOrderStr().split("A");
					}
					for (String ridStr : rIds) { // 退款
						OrderInfoPO order = conn_orderInfo.get(Long.parseLong(ridStr));
						order.setOrderState(OrderStateType.REFUNDED);
						conn_orderInfo.save(order);
						roomOrderMessage(order);
					}
					stringBuffer.append("<xml><return_code><![CDATA[");
					stringBuffer.append("SUCCESS");
					stringBuffer.append("]]></return_code>");
					stringBuffer.append("<return_msg><![CDATA[");
					stringBuffer.append("OK");
					stringBuffer.append("]]></return_msg>");
					System.out.println("微信支付退款成功!订单号：" + tradeNum);
					return stringBuffer.toString();
				}

				String[] orderIds = tradeNum.split("A");
				// orderNo="bundle-"+order.getId();
				if (tradeNum.indexOf("bundle") != -1) {
					Long bundleOrderId = Long.parseLong(tradeNum.split("-")[1]);
					BundleOrder bundleOrder = conn_bundleorder.get(bundleOrderId);
					orderIds = bundleOrder.getOrderStr().split("A");
				}

				for (String orderId : orderIds) {
					i = i + 1;
					OrderInfoPO order = conn_orderInfo.get(Long.parseLong(orderId));
					if (order.getOrderState().toString().equals("NOTPAY")) {
						// 微信支付
						order.setPayMode(PayType.WEICHAT);
						order.setPayDate(new Date());
						// 生成验单码,和二维码图片
						String ydNO = ydNoCode(orderId);
						order.setYdNO(ydNO);
						// 支付状态、减库存
						order.setOrderState(OrderStateType.PAYSUCCESS);

						AddTheRoomPO aRoomPO = addTheRoom.get(order.getRoomId());

						// 时间格式转换
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

						// 修改房间状态
						String[] cur_fields = { "roomId", "inRoomDate", "outRoomDate" };
						Object[] cur_values = { order.getRoomId(), sdf.format(order.getOrderBookDate()),
								sdf.format(order.getEndBookDate()) };
						List<CurrentRoomSatePO> cPos = currentRoomDao.findByFields(cur_fields, cur_values);

						for (CurrentRoomSatePO po : cPos) {
							po.setRoomState("1");
							currentRoomDao.saveOrUpdate(po);
						}
						// 修改房间对应住户的支付状态
						String[] mes_fields = { "roomId", "startDate", "endDate" };
						Object[] mes_values = { order.getRoomId(), sdf.format(order.getOrderBookDate()),
								sdf.format(order.getEndBookDate()) };
						List<MessageMiddleClientPO> mClientPOs = mesclient.findByFields(mes_fields, mes_values);
						for (MessageMiddleClientPO po : mClientPOs) {
							po.setOrderId(Long.parseLong(orderId));
							po.setPayState("1");
							mesclient.saveOrUpdate(po);
						}

						if (aRoomPO == null) {
							MerchantPO merchantPO = conn_merchant.get(order.getShopId());
							if (merchantPO.getShopyd() == 1) {
								order.setOrderState(OrderStateType.PAYSUCCESS);
							} else {
								order.setOrderState(OrderStateType.TESTED);
								order.setYdDate(new Date());
							}
						} else {
							order.setOrderState(OrderStateType.PAYSUCCESS);
						}
						long userIntegral = order.getPayMoney() / 100; // 购买后增加积分
						if (userIntegral >= 1) {
							List<UserInfoPO> UserInfoPO = conn_user.getUserByUid(order.getUserId()); //
							if (UserInfoPO != null) {
								long Integral = UserInfoPO.get(0).getUserIntegral();
								UserInfoPO.get(0).setUserIntegral(Integral + userIntegral);
								conn_user.saveOrUpdateAll(UserInfoPO);
							}
						}
						conn_orderInfo.saveOrUpdate(order);
						roomOrderMessage(order);
					}
				}
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("SUCCESS");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("OK");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付付款成功!" + i + "个订单。订单号：" + tradeNum);
			} else {
				stringBuffer.append("<xml><return_code><![CDATA[");
				stringBuffer.append("FAIL");
				stringBuffer.append("]]></return_code>");
				stringBuffer.append("<return_msg><![CDATA[");
				stringBuffer.append("交易失败");
				stringBuffer.append("]]></return_msg>");
				System.out.println("微信支付交易失败");
			}
		} else {
			stringBuffer.append("<xml><return_code><![CDATA[");
			stringBuffer.append("FAIL");
			stringBuffer.append("]]></return_code>");
			stringBuffer.append("<return_msg><![CDATA[");
			stringBuffer.append("签名失败");
			stringBuffer.append("]]></return_msg>");
			System.out.println("微信支付签名失败");
		}

		System.out.println("微信返回字符串:" + stringBuffer);

		return stringBuffer.toString();
	}

	@Autowired
	private AddTheRoomDAO theRoomDAO;

	private void roomOrderMessage(OrderInfoPO orderInfoPO) {

		AddTheRoomPO theRoomPO = theRoomDAO.get(orderInfoPO.getRoomId());
		MerchantPO merchantPO = conn_merchant.get(orderInfoPO.getShopId());

		// 用户推送消息
		Double amount = Double.parseDouble(orderInfoPO.getPayMoney() + "") / 100;
		DecimalFormat df = new DecimalFormat("0.00");
		UserInfoPO buyUser = conn_user.get(orderInfoPO.getUserId());
		if (buyUser != null) {
			JSONObject obj = new JSONObject();
			obj.put("touser", buyUser.getUserOpenID());
			obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
			obj.put("url", "");
			JSONObject microProObj = new JSONObject();
			microProObj.put("appid", "");
			microProObj.put("pagepath", "");
			obj.put("miniprogram", microProObj);
			JSONObject dataObject = new JSONObject();
			JSONObject firstObj = new JSONObject();
			firstObj.put("value", "您的订单支付成功");
			firstObj.put("color", "");
			dataObject.put("first", firstObj);

			JSONObject nameObj = new JSONObject();
			nameObj.put("value", buyUser.getUserNickname());
			nameObj.put("color", "");
			dataObject.put("keyword1", nameObj);

			JSONObject accountTypeObj = new JSONObject();
			accountTypeObj.put("value", orderInfoPO.getId());
			accountTypeObj.put("color", "");
			dataObject.put("keyword2", accountTypeObj);

			JSONObject accountObj = new JSONObject();
			accountObj.put("value", df.format(amount));
			accountObj.put("color", "");
			dataObject.put("keyword3", accountObj);
			JSONObject timeObj = new JSONObject();
			timeObj.put("value", theRoomPO == null ? "到店支付订单:" + merchantPO.getShopName() : theRoomPO.getName());
			timeObj.put("color", "");
			dataObject.put("keyword4", timeObj);
			JSONObject remarkObj = new JSONObject();
			remarkObj.put("value", "感谢使用过来玩服务");
			remarkObj.put("color", "");
			dataObject.put("remark", remarkObj);
			obj.put("data", dataObject);
			SendMsgUtil.sendTemplate(obj.toJSONString());
		}

		// 商户推送消息
		// UserInfoPO userInfoPO=merchantPO.getUser();
		List<MerchantUser> merchantUsers = conn_merchantUser.findByField("merchantId", merchantPO.getId());
		try {
			for (MerchantUser merchantUser : merchantUsers) {
				UserInfoPO userInfoPO = conn_user.get(merchantUser.getUserId());
				if (userInfoPO == null) {
					continue;
				}
				JSONObject obj = new JSONObject();
				obj.put("touser", userInfoPO.getUserOpenID());
				obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
				obj.put("url", "");
				JSONObject microProObj = new JSONObject();
				microProObj.put("appid", "");
				microProObj.put("pagepath", "");
				obj.put("miniprogram", microProObj);
				JSONObject dataObject = new JSONObject();
				JSONObject firstObj = new JSONObject();
				firstObj.put("value", "新的过来玩订单");
				firstObj.put("color", "");
				dataObject.put("first", firstObj);

				JSONObject nameObj = new JSONObject();
				nameObj.put("value", buyUser.getUserNickname());
				nameObj.put("color", "");
				dataObject.put("keyword1", nameObj);

				JSONObject accountTypeObj = new JSONObject();
				accountTypeObj.put("value", orderInfoPO.getOrderNO());
				accountTypeObj.put("color", "");
				dataObject.put("keyword2", accountTypeObj);

				JSONObject accountObj = new JSONObject();
				accountObj.put("value", df.format(amount));
				accountObj.put("color", "");
				dataObject.put("keyword3", accountObj);
				JSONObject timeObj = new JSONObject();
				timeObj.put("value", theRoomPO == null ? "到店支付订单:" + merchantPO.getShopName() : theRoomPO.getName());
				timeObj.put("color", "");
				dataObject.put("keyword4", timeObj);
				JSONObject remarkObj = new JSONObject();
				remarkObj.put("value", "请做好接待工作");
				remarkObj.put("color", "");
				dataObject.put("remark", remarkObj);
				obj.put("data", dataObject);
				SendMsgUtil.sendTemplate(obj.toJSONString());
			}

		} catch (Exception e) {
			// TODO: handle exception

		}

		JSONObject obj = new JSONObject();
		obj.put("touser", "opVUYv9LtqKAbiaXInBqI01hlpYg");
		obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
		obj.put("url", "");
		JSONObject microProObj = new JSONObject();
		microProObj.put("appid", "");
		microProObj.put("pagepath", "");
		obj.put("miniprogram", microProObj);
		JSONObject dataObject = new JSONObject();
		JSONObject firstObj = new JSONObject();
		firstObj.put("value", "新的过来玩订单");
		firstObj.put("color", "");
		dataObject.put("first", firstObj);

		JSONObject nameObj = new JSONObject();
		if (conn_address.get(orderInfoPO.getMailAddress()) != null) {
			nameObj.put("value", conn_address.get(orderInfoPO.getMailAddress()).getConsigneeName());
		} else {
			nameObj.put("value", conn_user.get(orderInfoPO.getUserId()).getUserNickname());
		}

		nameObj.put("color", "");
		dataObject.put("keyword1", nameObj);

		JSONObject accountTypeObj = new JSONObject();
		accountTypeObj.put("value", orderInfoPO.getId());
		accountTypeObj.put("color", "");
		dataObject.put("keyword2", accountTypeObj);

		JSONObject accountObj = new JSONObject();
		accountObj.put("value", df.format(amount));
		accountObj.put("color", "");
		dataObject.put("keyword3", accountObj);
		JSONObject timeObj = new JSONObject();
		timeObj.put("value", theRoomPO == null ? "到店支付订单:" + merchantPO.getShopName() : theRoomPO.getName());
		timeObj.put("color", "");

		String pNum = "";
		if (conn_address.get(orderInfoPO.getMailAddress()) != null) {
			pNum = conn_address.get(orderInfoPO.getMailAddress()).getConsigneePhone();
		}
		if (pNum == null || pNum.isEmpty()) {
			pNum = buyUser.getUserPhone();
		}
		dataObject.put("keyword4", timeObj);
		JSONObject remarkObj = new JSONObject();
		if (conn_address.getAddressByUserId(buyUser.getId()).get(0) != null) {
			remarkObj.put("value", "请做好接待工作(用户电话:" + pNum + "  用户地址:"
					+ conn_address.getAddressByUserId(buyUser.getId()).get(0).getConsigneeAddress() + ")");
		} else {
			remarkObj.put("value", "请做好接待工作(用户电话:" + pNum + "  用户地址: 此用户未填写详细地址)");
		}
		remarkObj.put("color", "");
		dataObject.put("remark", remarkObj);
		obj.put("data", dataObject);
		SendMsgUtil.sendTemplate(obj.toJSONString());

		// opVUYv9LtqKAbiaXInBqI01hlpYg
		// opVUYv1VUjjOlwYUeLj4hJRcTZPE
		JSONObject obj2 = new JSONObject();
		obj2.put("touser", "opVUYv_KDzCscwik3O4pnl64qYLU");
		obj2.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
		obj2.put("url", "");
		JSONObject microProObj2 = new JSONObject();
		microProObj2.put("appid", "");
		microProObj2.put("pagepath", "");
		obj2.put("miniprogram", microProObj2);
		JSONObject dataObject2 = new JSONObject();
		JSONObject firstObj2 = new JSONObject();
		firstObj2.put("value", "新的过来玩订单");
		firstObj2.put("color", "");
		dataObject2.put("first", firstObj2);

		JSONObject nameObj2 = new JSONObject();
		if (conn_address.get(orderInfoPO.getMailAddress()) != null) {
			nameObj2.put("value", conn_address.get(orderInfoPO.getMailAddress()).getConsigneeName());
		} else {
			nameObj2.put("value", conn_user.get(orderInfoPO.getUserId()).getUserNickname());
		}
		nameObj2.put("color", "");
		dataObject2.put("keyword1", nameObj2);

		JSONObject accountTypeObj2 = new JSONObject();
		accountTypeObj2.put("value", orderInfoPO.getId());
		accountTypeObj2.put("color", "");
		dataObject2.put("keyword2", accountTypeObj2);

		JSONObject accountObj2 = new JSONObject();
		accountObj2.put("value", df.format(amount));
		accountObj2.put("color", "");
		dataObject2.put("keyword3", accountObj2);
		JSONObject timeObj2 = new JSONObject();
		timeObj2.put("value", theRoomPO == null ? "到店支付订单:" + merchantPO.getShopName() : theRoomPO.getName());
		timeObj2.put("color", "");

		dataObject2.put("keyword4", timeObj2);
		JSONObject remarkObj2 = new JSONObject();
		if (conn_address.getAddressByUserId(buyUser.getId()).get(0) != null) {
			remarkObj2.put("value", "请做好接待工作(用户电话:" + pNum + "  用户地址:"
					+ conn_address.getAddressByUserId(buyUser.getId()).get(0).getConsigneeAddress() + ")");
		} else {
			remarkObj2.put("value", "请做好接待工作(用户电话:" + pNum + "  用户地址: 此用户未填写详细地址)");
		}
		remarkObj2.put("color", "");
		dataObject2.put("remark", remarkObj2);
		obj2.put("data", dataObject2);
		SendMsgUtil.sendTemplate(obj2.toJSONString());

	}

}
