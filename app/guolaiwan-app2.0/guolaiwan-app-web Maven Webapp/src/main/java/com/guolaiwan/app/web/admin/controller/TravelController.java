package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guolaiwan.app.qimingxin.TravelService;
import com.guolaiwan.bussiness.admin.dao.NhEticketsDao;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.NhEticketsPo;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import cn.hutool.json.JSONUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/travel/NhTicket")
public class TravelController extends BaseController {
	@Autowired
	private NhEticketsDao nhEticketsDao;

	@Autowired
	private OrderInfoDAO orderInfoDAO;

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
	public void onsumption(String sign, String appkey, String body, String timestamp) {
		// String appkey, String orderId, String otaOrderId, String quantity,
		// String usedQuantity,String refundQuqntity, String etickets
		System.out.println("景点票消费回调！！！");
		// 返回电子票拆分
		Map<String, Object> resultMap = JSONUtil.toBean(JSONUtil.parse(body), Map.class, false);
		// 消费的已验单
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

		String otaOrderId = resultMap.get("otaOrderId").toString();
		String agreeFlag = resultMap.get("agreeFlag").toString();
		if (agreeFlag == "true") {
			System.out.println("*****************" + "南湖票务退款回调 成功！！！");
			OrderInfoPO orderInfoPO = orderInfoDAO.getByRoderNo(otaOrderId);
			orderInfoPO.setOrderState(OrderStateType.REFUNDED);
		}

		return null;
	}

	@RequestMapping(value = "/review/{id}", method = RequestMethod.GET)
	public Object review(@PathVariable Long id) throws Exception {

		System.out.println("订单Id是" + id);
		OrderInfoPO orderInfoPO = orderInfoDAO.get(id);
		System.out.println(orderInfoPO.toString());
		orderInfoPO.setOrderState(OrderStateType.INREVIEW);

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
				orderInfoPO.setOrderState(OrderStateType.REFUNDING);
				orderInfoDAO.update(orderInfoPO);
				return "SUCCESS";
			}

		} // 上面是我瞎写的...有待测试.......

		return null;
	}

}
