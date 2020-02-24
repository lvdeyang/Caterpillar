package com.guolaiwan.app.web.admin.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.qimingxin.TravelService;
import com.guolaiwan.app.web.weixin.SendMsgUtil;
import com.guolaiwan.bussiness.admin.dao.InvestWalletDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantBusinessDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MessageDAO;
import com.guolaiwan.bussiness.admin.dao.NhEticketsDao;
import com.guolaiwan.bussiness.admin.dao.OlChatMessageDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SystemCacheDao;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserOnedayBuyDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MessagePO;
import com.guolaiwan.bussiness.admin.po.NhEticketsPo;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.nanshan.dao.MessageMiddleClientDao;
import com.guolaiwan.bussiness.nanshan.po.MessageMiddleClientPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import cn.hutool.json.JSONUtil;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.weixin.constants.WXContants;

@Controller
@RequestMapping("/travel/NhTicket")
public class TravelController extends BaseController {
	@Autowired
	private NhEticketsDao nhEticketsDao;
	@Autowired
	private ProductDAO conn_product;
	@Autowired
	private OrderInfoDAO orderInfoDAO;
	// 商户
	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	MessageMiddleClientDao conn_messageclient;
	@Autowired
	MessageDAO conn_message;
	@Autowired
	UserOnedayBuyDAO conn_userone;
	@Autowired
	private UserInfoDAO conn_user;
	@Autowired
	private AddressDAO conn_address;

	/**
	 * 票务消费回调
	 * 
	 * @param sign
	 * @param appkey
	 * @param body
	 * @param timestamp
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/onsumption")
	public Map<String, Object> onsumption(String sign, String appkey, String body, String timestamp) {
		// String appkey, String orderId, String otaOrderId, String quantity,
		// String usedQuantity,String refundQuqntity, String etickets
		System.out.println("景点票消费回调！！！");
		// 返回电子票拆分
		Map<String, Object> resultMap = JSONUtil.toBean(JSONUtil.parse(body), Map.class, false);
		// 消费的已验单
		Map<String, Object> responseMap = new HashMap<String, Object>();
		OrderInfoPO orderInfoPO = orderInfoDAO.getByRoderNo(resultMap.get("otaOrderId").toString());
		orderInfoPO.setOrderState(OrderStateType.TESTED);
		System.out.println("修改订单状态为已验单");
		String[] kStrings = resultMap.get("etickets").toString().split(",");
		for (String string : kStrings) {
			List<NhEticketsPo> list = nhEticketsDao.getBySn(string);
			if (list != null) {
				list.get(0).setIsInUse(1);
				nhEticketsDao.update(list.get(0));
			}
		}
		responseMap.put("appkey", appkey);
		responseMap.put("rspCode", "200");
		responseMap.put("rspDesc", "请求验证成功");
		return responseMap;
	}

	/**
	 * 票务退款回调
	 * 
	 * @param sign
	 * @param appkey
	 * @param body
	 * @param timestamp
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/refundCallBack")
	public Object refund(String sign, String appkey, String body, String timestamp) throws Exception {
		System.out.println("退款回调！！！" + "**********************" + body);

		/*******************************************/
		Map<String, Object> resultMap = JSONUtil.toBean(JSONUtil.parse(body), Map.class, false);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String otaOrderId = resultMap.get("otaOrderId").toString();
		String agreeFlag = resultMap.get("agreeFlag").toString();
		if (agreeFlag.equals("true") || agreeFlag.equals("True")) {
			System.out.println("*****************" + "南湖票务退款回调 成功！！！");
			OrderInfoPO orderInfoPO = orderInfoDAO.getByRoderNo(otaOrderId);
			orderInfoPO.setOrderState(OrderStateType.REFUNDING);
			sendMessage(orderInfoPO, "南湖景点票申请退款！");
			responseMap.put("appkey", appkey);
			responseMap.put("rspCode", "200");
			responseMap.put("rspDesc", "请求验证成功");
			return responseMap;
		}

		return null;
	}

	@RequestMapping(value = "/review/{id}", method = RequestMethod.GET)
	public Object review(@PathVariable Long id) throws Exception {

		System.out.println("订单Id是" + id);
		OrderInfoPO orderInfoPO = orderInfoDAO.get(id);
		System.out.println(orderInfoPO.toString());
		if (orderInfoPO.getProductId() == 2469) {
			List<NhEticketsPo> NhList = nhEticketsDao.getByOrderId(orderInfoPO.getId());
			List<String> snList = new ArrayList<String>();
			for (NhEticketsPo nhEticketsPo : NhList) {
				snList.add(nhEticketsPo.getSn());
			}
			System.out.println(orderInfoPO.getOrderNO() + "####" + orderInfoPO.getTargetId() + "####" + snList + "####"
					+ NhList.size());
			String result = TravelService.orderRefund(orderInfoPO.getTargetId(), orderInfoPO.getOrderNO(), snList,
					NhList.size());
			System.out.println("已经申请退款！！！");
			System.out.println(result);
			Map<String, Object> resultMap = JSONUtil.toBean(JSONUtil.parse(result), Map.class, false);

			if (resultMap.get("rspCode").equals("200") && resultMap.get("rspDesc").equals("请求验证成功")) {
				System.out.println(result);
				System.out.println("南湖申请退票....等待审核中");
				orderInfoPO.setOrderState(OrderStateType.INREVIEW);
				orderInfoDAO.update(orderInfoPO);

				return "SUCCESS";
			}

		} // 上面是我瞎写的...有待测试.......

		return null;
	}

	private void sendMessage(OrderInfoPO orderInfoPO, String reason) {

		ProductPO productPO = conn_product.get(orderInfoPO.getProductId());
		MerchantPO merchantPO = conn_merchant.get(orderInfoPO.getShopId());
		UserInfoPO buyUser = conn_user.get(orderInfoPO.getUserId());

		// 用户推送消息
		Double amount = Double.parseDouble(orderInfoPO.getPayMoney() + "") / 100;
		DecimalFormat df = new DecimalFormat("0.00");

		JSONObject obj = new JSONObject();
		obj.put("touser", "opVUYv0IKcoKT8TXWbtDP00MBOkw");
		obj.put("template_id", "GxkZ1LE6s6UxF9nsT8mesUc3Vk73KZwAzU8pFz4Pl8U");
		obj.put("url", "");
		JSONObject microProObj = new JSONObject();
		microProObj.put("appid", "");
		microProObj.put("pagepath", "");
		obj.put("miniprogram", microProObj);
		JSONObject dataObject = new JSONObject();
		JSONObject firstObj = new JSONObject();
		firstObj.put("value", "用户申请退款,订单号:" + orderInfoPO.getId());
		firstObj.put("color", "");
		dataObject.put("first", firstObj);

		JSONObject nameObj = new JSONObject();
		nameObj.put("value", reason);
		nameObj.put("color", "");
		dataObject.put("reason", nameObj);

		JSONObject accountTypeObj = new JSONObject();
		accountTypeObj.put("value", df.format(amount));
		accountTypeObj.put("color", "");
		dataObject.put("refund", accountTypeObj);

		JSONObject remarkObj = new JSONObject();

		String userNameStr = "";
		String userPhoneStr = "";
		List<MessageMiddleClientPO> clientPOs = conn_messageclient.findByField("orderId", orderInfoPO.getId());
		MessagePO messagePO = null;
		if (clientPOs != null && !clientPOs.isEmpty()) {
			messagePO = conn_message.get(clientPOs.get(0).getMessageId());
		}
		if (messagePO != null) {
			userNameStr = messagePO.getName();
			userPhoneStr = messagePO.getPhone();
		} else if (conn_address.get(orderInfoPO.getMailAddress()) != null) {
			// conn_address.get(orderInfoPO.getMailAddress()).getConsigneeName();
			AddressPO addressPO = conn_address.get(orderInfoPO.getMailAddress());
			userNameStr = addressPO.getConsigneeName();
			userPhoneStr = addressPO.getConsigneePhone();
		} else {
			userNameStr = conn_user.get(orderInfoPO.getUserId()).getUserNickname();
			userPhoneStr = "用户未留下电话信息";
		}

		remarkObj.put("value", (productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName())
				+ "-用户:" + userNameStr + "(" + userPhoneStr + ")");
		remarkObj.put("color", "");
		dataObject.put("remark", remarkObj);
		obj.put("data", dataObject);
		SendMsgUtil.sendTemplate(obj.toJSONString());

		JSONObject obj1 = new JSONObject();
		obj1.put("touser", "opVUYv9LtqKAbiaXInBqI01hlpYg");
		obj1.put("template_id", "GxkZ1LE6s6UxF9nsT8mesUc3Vk73KZwAzU8pFz4Pl8U");
		obj1.put("url", "");
		JSONObject microProObj1 = new JSONObject();
		microProObj1.put("appid", "");
		microProObj1.put("pagepath", "");
		obj.put("miniprogram", microProObj1);
		JSONObject dataObject1 = new JSONObject();
		JSONObject firstObj1 = new JSONObject();
		firstObj1.put("value", "用户申请退款,订单号:" + orderInfoPO.getId());
		firstObj1.put("color", "");
		dataObject1.put("first", firstObj1);

		JSONObject nameObj1 = new JSONObject();
		nameObj1.put("value", reason);
		nameObj1.put("color", "");
		dataObject1.put("reason", nameObj1);

		JSONObject accountTypeObj1 = new JSONObject();
		accountTypeObj1.put("value", df.format(amount));
		accountTypeObj1.put("color", "");
		dataObject1.put("refund", accountTypeObj1);

		JSONObject remarkObj1 = new JSONObject();
		remarkObj1.put("value", (productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName())
				+ "-用户:" + userNameStr + "(" + userPhoneStr + ")");

		remarkObj1.put("color", "");
		dataObject1.put("remark", remarkObj1);
		obj1.put("data", dataObject1);
		SendMsgUtil.sendTemplate(obj1.toJSONString());

	}

}
