package com.guolaiwan.app.zizhu.controller;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.interfac.alipay.AliAppOrderInfo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.zhaji.ZhajiService;
import com.guolaiwan.app.zizhu.GlwHttpUtils;
import com.guolaiwan.app.zizhu.bean.CancelVoucherPo;
import com.guolaiwan.app.zizhu.bean.CancleVoucherVo;
import com.guolaiwan.app.zizhu.bean.PayCodePo;
import com.guolaiwan.app.zizhu.bean.PayCodeVo;
import com.guolaiwan.app.zizhu.bean.PayVoucherVo;
import com.guolaiwan.app.zizhu.bean.ProductPo;
import com.guolaiwan.app.zizhu.bean.ProductVo;
import com.guolaiwan.app.zizhu.bean.RefundPo;
import com.guolaiwan.app.zizhu.bean.RefundVo;
import com.guolaiwan.app.zizhu.bean.RefundVoucherPo;
import com.guolaiwan.app.zizhu.bean.RefundVoucherVo;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.OrderPeoplePo;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.RoomStatusPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.UserOneDayBuyPO;
import com.guolaiwan.app.zizhu.bean.CreateVoucherPo;
import com.guolaiwan.app.zizhu.bean.CreateVoucherVo;

import cn.hutool.core.date.DateTime;
import pub.caterpillar.commons.util.date.DateUtil;

@Controller
@RequestMapping("/ticketSales")
public class ZizhuController {

	/**
	 * 获取商品列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryProduct", method = RequestMethod.GET)
    public String checkIn(@RequestBody ProductPo po){
		List<ProductVo.Data> list=null;
		ProductVo vo=new ProductVo();
		ProductVo.Error error=vo.getError();
		if (po!=null) {
			 list=GlwHttpUtils.getProduct(po.getPage(), po.getId());
		}
		if (list!=null&&list.size()>0) {
			vo.setData(list);
			error.setCode(0);
			error.setMessage("success");
		}else{
			error.setCode(1);
			error.setMessage("faile");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 获取微信支付二维码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wxpay/scanPay", method = RequestMethod.GET)
    public String payCodeWx(@RequestBody PayCodePo po){
		PayCodeVo vo=new PayCodeVo();
		Map payData=new HashMap<String,Object>();
		if (po!=null) {
			payData=GlwHttpUtils.getPayCode(po);
		}
		if (!payData.isEmpty()) {
			vo.setCode("SUCCESS");
			vo.setMsg("支付成功");
			vo.setMchid((String)payData.get("mchid"));
			vo.setAppid((String)payData.get("appid"));
			vo.setQrcode_url((String)payData.get("qrcode_url"));
			vo.setTrans_status((String)payData.get("trans_status"));
			vo.setTrans_time((DateTime)payData.get("trans_time"));
			vo.setMemo((String)payData.get("memo"));
		}else{
			vo.setCode("FAIL");
			vo.setMsg("支付失败");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 获取支付宝支付二维码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/alipay/scanPay", method = RequestMethod.GET)
    public String payCodeAli(@RequestBody PayCodePo po){
		PayCodeVo vo=new PayCodeVo();
		Map payData=new HashMap<String,Object>();
		if (po!=null) {
			payData=GlwHttpUtils.getPayCode(po);
		}
		if (!payData.isEmpty()) {
			vo.setCode("SUCCESS");
			vo.setMsg("支付成功");
			vo.setMchid((String)payData.get("mchid"));
			vo.setAppid((String)payData.get("appid"));
			vo.setQrcode_url((String)payData.get("qrcode_url"));
			vo.setTrans_status((String)payData.get("trans_status"));
			vo.setTrans_time((DateTime)payData.get("trans_time"));
			vo.setMemo((String)payData.get("memo"));
		}else{
			vo.setCode("FAIL");
			vo.setMsg("支付失败");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 创建凭证
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createVoucher", method = RequestMethod.POST)
    public String createVoucher(@RequestBody CreateVoucherPo po){
		CreateVoucherVo vo=new CreateVoucherVo();
		List<CreateVoucherVo.Data.Details> list=null;
		CreateVoucherVo.Error error=vo.getError();
		if (po!=null) {
			list=GlwHttpUtils.createVoucher(po);
		}
		if (list!=null&&list.size()>0) {
			vo.getData().setDetails(list);
			error.setCode(0);
			error.setMessage("success");
		}else{
			error.setCode(1);
			error.setMessage("faile");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 取消凭证
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelPreVoucher", method = RequestMethod.POST)
    public String cancelVoucher(@RequestBody CancelVoucherPo po){
		CancleVoucherVo vo=new CancleVoucherVo();
		CancleVoucherVo.Error error=vo.getError();
		int status=-1;
		if (po!=null) {
		 status=GlwHttpUtils.cancelVoucher(po.getVoucherNumber());
		}
		if (status!=-1) {
			vo.getData().setVoucherNumber(po.getVoucherNumber());
			vo.getData().setStatus(status);
			switch(status){
				case 0:{
					error.setCode(0);
					error.setMessage("success");
				}
				case 1:{
					error.setCode(1);
					error.setMessage("faile");
				}
			}
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 凭证出票
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/payPreVoucher", method = RequestMethod.POST)
    public String payPreVoucher(@RequestBody CancelVoucherPo po){
		PayVoucherVo vo=new PayVoucherVo();
		List<PayVoucherVo.Data.Details> list=null;
		PayVoucherVo.Error error=vo.getError();
		if (po!=null) {
			list=GlwHttpUtils.payPreVoucher(po.getVoucherNumber());
		}
		if (list!=null&&list.size()>0) {
			vo.getData().setDetails(list);
			error.setCode(0);
			error.setMessage("success");
		}else{
			error.setCode(1);
			error.setMessage("faile");
		}
		return JSONObject.toJSONString(vo);
    }
	
	
	/**
	 * 凭证退票
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/refundVoucher", method = RequestMethod.POST)
    public String refundVoucher(@RequestBody RefundVoucherPo po){
		RefundVoucherVo vo=new RefundVoucherVo();
		RefundVoucherVo.Error error=vo.getError();
		Map refundData=new HashMap<String, Object>();
		if (po!=null) {
			refundData=GlwHttpUtils.refundPreVoucher(po);
		}
		if (!refundData.isEmpty()) {
			vo.Data().setRefundNumber((String)refundData.get("refundNumber"));
			vo.Data().setStatus((Integer)refundData.get("status"));
			vo.Data().setVoucherNumber((String)refundData.get("voucherNumber"));
			error.setCode(0);
			error.setMessage("success");
		}else{
			error.setCode(1);
			error.setMessage("faile");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 微信退款
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wxpay/refund", method = RequestMethod.POST)
    public String reFundWx(@RequestBody RefundPo po){
		RefundVo vo=new RefundVo();
		Map refundData=new HashMap<String,Object>();
		if (po!=null) {
			refundData=GlwHttpUtils.refund(po);
		}
		if (!refundData.isEmpty()) {
			vo.setCode("SUCCESS");
			vo.setMsg("支付成功");
			vo.setAmount((Integer)refundData.get("amount"));
			vo.setAppid((String)refundData.get("appid"));
			vo.setMchid((String)refundData.get("mchid"));
			vo.setMemo((String)refundData.get("memo"));
			vo.setOption_type((String)refundData.get("option_type"));
			vo.setRefer_no((String)refundData.get("refer_no"));
			vo.setTrade_no((String)refundData.get("trade_no"));
			vo.setTrans_no((String)refundData.get("trans_no"));
			vo.setTrans_status((String)refundData.get("trans_no"));
			vo.setTrans_time((DateTime)refundData.get("trans_time"));
			vo.setTrans_type((String)refundData.get("trans_type"));
		}else{
			vo.setCode("FAIL");
			vo.setMsg("支付失败");
		}
		return JSONObject.toJSONString(vo);
    }
	
	/**
	 * 支付宝退款
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/alipay/refund", method = RequestMethod.POST)
    public String reFundAli(@RequestBody RefundPo po){
		RefundVo vo=new RefundVo();
		Map refundData=new HashMap<String,Object>();
		if (po!=null) {
			refundData=GlwHttpUtils.refund(po);
		}
		if (!refundData.isEmpty()) {
			vo.setCode("SUCCESS");
			vo.setMsg("支付成功");
			vo.setAmount((Integer)refundData.get("amount"));
			vo.setAppid((String)refundData.get("appid"));
			vo.setMchid((String)refundData.get("mchid"));
			vo.setMemo((String)refundData.get("memo"));
			vo.setOption_type((String)refundData.get("option_type"));
			vo.setRefer_no((String)refundData.get("refer_no"));
			vo.setTrade_no((String)refundData.get("trade_no"));
			vo.setTrans_no((String)refundData.get("trans_no"));
			vo.setTrans_status((String)refundData.get("trans_no"));
			vo.setTrans_time((DateTime)refundData.get("trans_time"));
			vo.setTrans_type((String)refundData.get("trans_type"));
		}else{
			vo.setCode("FAIL");
			vo.setMsg("支付失败");
		}
		return JSONObject.toJSONString(vo);
    }
	
	
	
	public Map<String, Object> addOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();
		return data;
		//String productId = pageObject.getString("productId");
		//String num = pageObject.getString("productNum");
		//ProductPO productPO = conn_product.get(Long.parseLong(productId));
		//if (num == null) {
		//	num = "1";
		//}
		//OrderInfoPO order = new OrderInfoPO();
		// 4/26添加comId 张羽 4/28 添加退款限制
		//ProductPO productPO2 = conn_product.get(Long.parseLong(productId));
		//order.setComId(productPO2.getComId());
		//order.setProductIsRefund(productPO2.getProductIsRefund());
		//String orderStartDate = pageObject.getString("startDate");
		//if (orderStartDate != null && orderStartDate != "" && orderStartDate.length() != 0) {
		//	orderStartDate = orderStartDate.replace("T", " ");
		//	order.setOrderBookDate(DateUtil.parse(orderStartDate, DateUtil.dateTimePattenWithoutSecind));
		//}
		//long productprice = productPO.getProductPrice();
		// 支付金额
		//long payMoney = Integer.parseInt(num) * productprice;
		// 订单总金额
		//long orderAllMoney = payMoney;
		// 获取商家
		//MerchantPO merchant = conn_merchant.get(productPO.getProductMerchantID());
		//String orderBookDate = pageObject.getString("bookDate");
		//order.setOrderBookDate(DateUtil.parse(orderBookDate, DateUtil.dateTimePattenWithoutSecind));
		// 订单号（城市编码+商家id+板块Code+时间戳+用户ID）
		//String orderNO = getCityCodeByDomain() + merchant.getId() + productPO.getProductModularCode() + df.format(date)
		//		+ userId;
		//order.setOrderNO(orderNO);
		// 下单时间
		//order.setCreateDate(date);
		//order.setUpdateTime(date);
		// 供应商ID
		//order.setShopId(merchant.getId());
		// 供应商名称
		//order.setShopName(merchant.getShopName());
		// 站点ID
		// 站点名称
		// 商品ID
		//order.setProductId(productPO.getId());
		// 商品图片
		//order.setProductPic(productPO.getProductShowPic());
		// 商品名称
		//order.setProductName(productPO.getProductName());
		// 商品数量
		//order.setProductNum(Long.parseLong(num));
		//orderAllMoney = payMoney;
		//System.out.println(orderAllMoney + "-------------------------");
		// 商品单价
		//order.setProductPrice(productprice);
		// 所属板块DI
		//order.setBkCode(productPO.getProductModularCode());
		// 所属板块名称
		//order.setBkName(productPO.getProductModularCodeName());
		// 提成方式（0：佣金1：比例）
		//order.setRoyaltyName(productPO.getProductCommissionCode());
		// 订单佣金金额(分)
		//long proportionMoney;
		//if (productPO.getProductCommissionCode() == 1) {
		//	proportionMoney = Integer.parseInt(num) * productPO.getProductPrice() * proportion / 100;
		//} else {
		//	proportionMoney = Integer.parseInt(num) * proportion;
		//}
		//order.setProportionMoney(proportionMoney);
		// 支付金额
		//order.setPayMoney(payMoney);
		// 订单总金额
		//order.setOrderAllMoney(orderAllMoney);
		// 订单说明
		//if (request.getParameter("orderRemark") != null && request.getParameter("orderRemark").length() > 0) {
			//order.setOrderRemark(request.getParameter("orderRemark"));
		//}
		// 订单状态
		//order.setOrderState(OrderStateType.NOTPAY);
		// 订单支付类型 //ALIPAY WEICHAT
		//order.setPayMode(PayType.fromString(paytype));
		// 是否评价
		//order.setCommentIs(0);
		//if (photo != null) {
		//	order.setPhoto(URLDecoder.decode(photo));
		//}
		//if (idNum != null) {
		//	order.setIdNum(idNum);
		//}

		//if (pageObject.getString("source") != null) {
		//	order.setSource(OrderSource.fromString(pageObject.getString("source")));
		//} else {
			// 订单来源
		//	order.setSource(OrderSource.APP);
		//}
		//order.setOrderType(OrderType.MERCHANT);
		//productPO.setProductSaleNum(productPO.getProductSaleNum() + 1);
		//productPO.setProductShowNum(productPO.getProductShowNum() + 1);
		//conn_product.update(productPO);
		//conn_order.saveOrUpdate(order);
		//JSONArray array = pageObject.getJSONArray("idnums");
		//if (array != null) {
		//	for (Object obj : array) {
		//		JSONObject jobj = (JSONObject) obj;
		//		OrderPeoplePo orderPeoplePo = new OrderPeoplePo();
		//		orderPeoplePo.setIdNum(jobj.getString("idNum"));
		//		orderPeoplePo.setPhoto(URLDecoder.decode(jobj.getString("photo")));
		//		orderPeoplePo.setOrderId(order.getId());
		//		orderPeoplePo.setName(jobj.getString("name"));
		//		conn_orderPeople.save(orderPeoplePo);
		//	}
		//}
		//long PayMoney = order.getPayMoney();
		/* String tradeNum=order.getOrderNO(); */
		//String orderIdStr = String.valueOf(order.getId());
		// 调微信和支付宝
		//if (paytype.equals("WEICHAT")) { // 微信
			
		//} else if (paytype.equals("ALIPAY")) {
			
		//	return success(data);
		//} else {
		//	return ERROR("系统错误！");
		//}
	}
}
