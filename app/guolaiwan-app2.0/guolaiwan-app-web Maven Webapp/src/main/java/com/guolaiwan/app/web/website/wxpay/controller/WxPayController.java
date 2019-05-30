package com.guolaiwan.app.web.website.wxpay.controller;

import java.io.File;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
//import com.guolaiwan.app.web.enumeration.LoginCacheName;
//import com.guolaiwan.app.web.user.vo.UserVO;
//import com.guolaiwan.app.web.website.order.vo.OrderDetailVO;
//import com.guolaiwan.bussiness.admin.dao.SystemConfigDao;
//import com.guolaiwan.bussiness.admin.po.SystemConfigPO;
//import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
//import com.guolaiwan.bussiness.chapman.product.enumeration.ProductDetailType;
//import com.guolaiwan.bussiness.chapman.product.po.ProductDetailPO;
//import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
//import com.guolaiwan.bussiness.order.dao.OrderDAO;
//import com.guolaiwan.bussiness.order.dao.OrderDetailDAO;
//import com.guolaiwan.bussiness.order.dto.OrderDetailDTO;
//import com.guolaiwan.bussiness.order.enumeration.OrderStatusType;
//import com.guolaiwan.bussiness.order.po.OrderDetailPO;
//import com.guolaiwan.bussiness.order.po.OrderPO;
//import com.guolaiwan.bussiness.user.check.UserDBCheck;
//import com.guolaiwan.bussiness.user.dao.BasketDAO;
//import com.guolaiwan.bussiness.user.po.BasketPO;
//import com.guolaiwan.bussiness.user.po.UserPO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.commons.qrcode.QRCodeGenerator;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPay;

@Controller
@RequestMapping("/website/wxpay")
public class WxPayController extends BaseController {

	@Autowired
	private OrderInfoDAO conn_orderInfo;
	//
	@Autowired
	private SysConfigDAO conn_sysConfig;

	// 下订单
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public ModelAndView pay(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		String orderUuid = request.getParameter("orderUuid");
		OrderInfoPO order = conn_orderInfo.get(orderUuid);
		if (order == null) {
			throw new Exception("数据异常，未获取到用户可下的订单信息！");
		}
		// //存支付方式
		// order.setPayMode(PayType.WEICHAT);

		// 计算总价
		long PayMoney = order.getPayMoney();

		//
		// //2017/08/06 Mr.huang计算总价
		// double total=0d;
		// boolean deleteFile=true;
		//// for (BasketPO basketPO : baskets) {
		//// if(basketPO.getCodeFilePath()!=null&&!basketPO.getCodeFilePath().isEmpty()&&deleteFile){
		//// deleteFile=true;
		//// File delFile=new File(basketPO.getCodeFilePath());
		//// if(delFile.exists()){
		//// delFile.delete();
		//// }
		//// }
		////
		//// total+=basketPO.getProduct().getPrice()*basketPO.getNum();
		//// }
		//
		// 2017/08/06 Mr.huang 微信支持
		try {

			GuolaiwanWxPay wxPay = GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport");

			Map<String, String> reqData = new HashMap<String, String>();

			String tradeNum = order.getId().toString();// 订单编号UUID.randomUUID().toString().replace("-",
														// "")

			reqData.put("device_info", "WEB");

			reqData.put("total_fee", String.valueOf((int) PayMoney));

			reqData.put("attach", "test");

			reqData.put("body", "过来玩");

			reqData.put("out_trade_no", tradeNum);

			reqData.put("spbill_create_ip", "192.165.56.64");

			reqData.put("trade_type", "NATIVE");

			Map<String, String> resData = wxPay.pay(reqData); // 生成二维码数据

			SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

			String fileName = order.getId() + DateUtil.getCurrentDateTime() + ".png";

			String path = sysConfig.getFolderUrl() + File.separator + "wxPayCode" + File.separator + fileName;

			String content = resData.get("code_url");

			QRCodeGenerator.generate(path, content); // 本地路径下生成二维码
			//
			// order表中存二维码的本地路径
			// for (BasketPO basketPO : baskets) {
			// basketPO.setTradeNum(tradeNum);
			// basketPO.setCodeFilePath(path);
			// }
			//
			// conn_basket.saveAll(baskets);

			result.put("imagePath", sysConfig.getWebUrl() + "/wxPayCode/" + fileName);

			//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		result.put("PayMoney", new DecimalFormat("0.00").format((double) PayMoney / 100));
		result.put("order", order);
		ModelAndView mv = new ModelAndView("web/order/wxPay", result);
		return mv;
	}

	// 批量支付订单
	@RequestMapping(value = "/pay/batch", method = RequestMethod.POST)
	public ModelAndView payBatch(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "orderUuids[]") List<String> orderUuids) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		List<OrderInfoPO> orders = conn_orderInfo.getAllByUuids(orderUuids);
		if (orders == null || orders.size() <= 0) {
			throw new Exception("数据异常，未获取到用户可下的订单信息！");
		}
		StringBufferWrapper idBuffer = new StringBufferWrapper();
		// 计算总价
		long PayMoney = 0l;
		for (OrderInfoPO order : orders) {
			PayMoney += order.getPayMoney();
			idBuffer.append(order.getId()).append("A");
		}

		String orderId = idBuffer.toString();

		// 2017/08/06 Mr.huang 微信支持
		try {

			GuolaiwanWxPay wxPay = GuolaiwanWxPay
					.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport/batch");

			Map<String, String> reqData = new HashMap<String, String>();

			// String
			// tradeNum=order.getOrderNO();//订单编号UUID.randomUUID().toString().replace("-",
			// "")

			reqData.put("device_info", "WEB");

			reqData.put("total_fee", String.valueOf((int) PayMoney));

			reqData.put("attach", "test");

			reqData.put("body", "过来玩");

			reqData.put("out_trade_no", orderId);

			reqData.put("spbill_create_ip", "192.165.56.64");

			reqData.put("trade_type", "NATIVE");

			Map<String, String> resData = wxPay.pay(reqData); // 生成二维码数据

			SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

			String fileName = UUID.randomUUID().toString() + DateUtil.getCurrentDateTime() + ".png";

			String path = sysConfig.getFolderUrl() + File.separator + "wxPayCode" + File.separator + fileName;

			String content = resData.get("code_url");

			QRCodeGenerator.generate(path, content); // 本地路径下生成二维码
			//
			// order表中存二维码的本地路径
			// for (BasketPO basketPO : baskets) {
			// basketPO.setTradeNum(tradeNum);
			// basketPO.setCodeFilePath(path);
			// }
			//
			// conn_basket.saveAll(baskets);

			result.put("imagePath", sysConfig.getWebUrl() + "/wxPayCode/" + fileName);

			//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		result.put("PayMoney", new DecimalFormat("0.00").format((double) PayMoney / 100));
		result.put("orders", orders);
		result.put("orderId", orderId);
		ModelAndView mv = new ModelAndView("web/order/wxPayBatch", result);
		return mv;
	}

	@Autowired
	private BundleOrderDAO conn_bundle;

	@ResponseBody
	@RequestMapping(value = "/refund", method = RequestMethod.GET)
	public Object refund(HttpServletRequest request, HttpServletResponse response, String orderId) throws Exception {
		long amount = 0;
		Map<String, Object> result = new HashMap<String, Object>();
		if (orderId.indexOf("bundle") != -1) {
			String[] bundleidStrs = orderId.split("-");
			BundleOrder bOrder = conn_bundle.get(Long.parseLong(bundleidStrs[1]));
			String[] orderStrs = bOrder.getOrderStr().split("A");
			for (String orderIdStr : orderStrs) {
				if (conn_orderInfo.get(Long.parseLong(orderIdStr)).getOrderState() == OrderStateType.REFUNDING) {

					amount += conn_orderInfo.get(Long.parseLong(orderIdStr)).getPayMoney();
				}
			}
		} else {
			OrderInfoPO orderInfoPO = conn_orderInfo.get(Long.parseLong(orderId));
			if (orderInfoPO.getOrderState() == OrderStateType.REFUNDING) {

				amount = orderInfoPO.getPayMoney();
			}
		}
		String refundOrderNum = "refund" + orderId;
		try {

			GuolaiwanWxPay wxPay = GuolaiwanWxPay.getInstance("http://"+WXContants.Website+"/website/wxreport/payreport");

			Map<String, String> reqData = new HashMap<String, String>();

			reqData.put("out_trade_no", orderId + "");
			reqData.put("out_refund_no", refundOrderNum);
			reqData.put("total_fee", amount + "");
			reqData.put("refund_fee", amount + "");

			Map<String, String> resData = wxPay.refund(reqData); // 生成二维码数据

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/mmpay", method = RequestMethod.GET)
	public Object mmpay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> reqData = new HashMap<String, String>();
		GuolaiwanWxPay wxPay = GuolaiwanWxPay.getInstance("");
		reqData.put("partner_trade_no","mmpay1"); //生成商户订单号
		reqData.put("openid","opVUYv_4GAy8GhpOWKMO2eqt-j0A");            // 支付给用户openid
		reqData.put("check_name","NO_CHECK");    //是否验证真实姓名呢
		reqData.put("re_user_name","fish");//收款用户姓名
		reqData.put("amount","1000");            //企业付款金额，单位为分
		reqData.put("desc","sendback");    			   //企业付款操作说明信息。必填。
		reqData.put("spbill_create_ip","192.165.56.64"); //调用接口的机器Ip地址
		Map<String, String> resData = wxPay.mmpay(reqData); // 生成二维码数据
		return resData;
	}
	
	
	

}
