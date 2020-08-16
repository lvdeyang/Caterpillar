package com.guolaiwan.app.zizhu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.interfac.alipay.AliAppOrderInfo;
import com.guolaiwan.app.web.Guide.controller.integralControll;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.zizhu.GlwHttpUtils;
import com.guolaiwan.app.zizhu.bean.CancelVoucherPo;
import com.guolaiwan.app.zizhu.bean.CancleVoucherVo;
import com.guolaiwan.app.zizhu.bean.PayCodePo;
import com.guolaiwan.app.zizhu.bean.PayCodeVo;
import com.guolaiwan.app.zizhu.bean.PayVoucherVo;
import com.guolaiwan.app.zizhu.bean.ProductPo;
import com.guolaiwan.app.zizhu.bean.ProductVo;
import com.guolaiwan.app.zizhu.bean.ProductVo.Data;
import com.guolaiwan.app.zizhu.bean.ProductVo.Error;
import com.guolaiwan.app.zizhu.bean.RefundPo;
import com.guolaiwan.app.zizhu.bean.RefundVo;
import com.guolaiwan.app.zizhu.bean.RefundVoucherPo;
import com.guolaiwan.app.zizhu.bean.RefundVoucherVo;
import com.guolaiwan.bussiness.admin.dao.DeviceDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.DevicePO;
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
	@Autowired
	DeviceDAO conn_device;
	
	/**
	 * 自助机登录
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkIn", method = RequestMethod.POST)
    public String checkIn(HttpServletRequest request){
		String uniqueCode = request.getParameter("uniqueCode");
		JSONObject response=new JSONObject();
		JSONObject data=new JSONObject();
		JSONObject error=new JSONObject();
		List<DevicePO> devicePOs=conn_device.findByField("deviceCode", uniqueCode);
		if(devicePOs==null||devicePOs.isEmpty()){
			error.put("code", 1);
			error.put("message", "自助机未注册");
			response.put("data", data);
			response.put("error", error);
		}else{
			//服务器时间
			data.put("systemTime",DateUtil.format(new Date(),DateUtil.dateTimePattern));
			//站点名称
			data.put("salesSiteName", devicePOs.get(0).getDeviceName());
			//地区名称
			data.put("areaName", devicePOs.get(0).getMerchantName());
			//销售窗口ID
			data.put("salesWinId", devicePOs.get(0).getMerchantId());
			//销售窗口名称
			data.put("salesWinName", "窗口一");
			//用户ID
			data.put("userId",uniqueCode);
			//userName
			data.put("userName","王小二");
			error.put("code", 0);
			error.put("message", "success");
			response.put("data", data);
			response.put("error", error);
		}
		return response.toString(); 
    }
	
	/**
	 * 心跳检测
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/hasHeartbeat", method = RequestMethod.POST)
    public String hasHeartbeat(HttpServletRequest request){
		String uniqueCode = request.getParameter("uniqueCode");
		JSONObject response=new JSONObject();
		JSONObject data=new JSONObject();
		JSONObject error=new JSONObject();
		data.put("result", true);
		error.put("code", 0);
		error.put("message", "success");
		response.put("data", data);
		response.put("error", error);
		return response.toString(); 
	}
	
	/**
	 * 获取商品列表
	 * @param request
	 * @return
	 */
	@Autowired
	ProductDAO conn_pro;

	@ResponseBody
	@RequestMapping(value = "/queryProduct", method = RequestMethod.GET)
    public String queryProduct(HttpServletRequest request){
		List<ProductVo.Data> list=null;
		ProductVo vo=new ProductVo();
		ProductVo.Data data=new Data();
		if (request!=null) {
			//String page = request.getParameter("page");
			String id = request.getParameter("salesWinId");
			System.out.println("shang hu id"+id);
			List<ProductPO> products = conn_pro.findByMerchantId(Long.parseLong(id));
			for (ProductPO productPO : products) {
				data.setId(productPO.getId().intValue());
				data.setName(productPO.getProductName());
				data.setCode("123");
				data.setProductCategory(1);
				data.setCrowdKindName("普通票");
				data.setPriceName("全价");
				data.setPrice(Integer.parseInt((productPO.getProductPrice()/100+"")));
				data.setBasicPrice(Integer.parseInt((productPO.getProductPrice()/100+"")));
				data.setValidityNum(Integer.parseInt(productPO.getProductStock()+""));
				data.setValidityUnits(2);
				data.setHasUser(true);
				data.setHasFace(false);
				vo.getData().add(data);
			
			}
			vo.getError().setCode(0);
			vo.getError().setMessage("success");
	
		}
	
		return JSONObject.toJSONString(vo);
    }
	
	private String getRequestJson(HttpServletRequest request) {
		try {
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			request.getInputStream().close();
			br.close();
			return sb.toString();
		} catch (IOException e) {
			return "";
		}
	}
	
	/**
	 * 创建凭证
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createVoucher", method = RequestMethod.POST)
    public String createVoucher(HttpServletRequest request){
		CreateVoucherVo vo=new CreateVoucherVo();
		vo.getData().setDetails(new ArrayList<CreateVoucherVo.Data.Details>());
		String json=getRequestJson(request);
		JSONObject pageObject = JSON.parseObject(json);
		int type=Integer.parseInt(pageObject.getString("settlementType"));
		PayType Paytype=null;
		switch (type) {
		case 1:
			Paytype=PayType.ALIPAY;
			break;
		default:
			Paytype=PayType.WEICHAT;
			break;
		}
		JSONArray array=pageObject.getJSONArray("details");
		if (!array.isEmpty()) {
			for (int i = 0; i < array.size(); i++) {
				int productId=array.getJSONObject(i).getInteger("productId");
				int productNum=array.getJSONObject(i).getInteger("quantity");
				OrderInfoPO order;
				try {
					order = addOrder(productId,productNum,Paytype);
					CreateVoucherVo.Data.Details details=new CreateVoucherVo.Data.Details();
					details.setProductId(array.getJSONObject(i).getIntValue("productId"));
					details.setTicketNo(order.getId()+"");
					details.setTicketNoPic("");
					details.setProductName(order.getProductName());
					details.setBusinessName("游客");
					details.setCrowdKindName("成人");
					details.setPriceName("全价");
					details.setPrice(Integer.parseInt((order.getProductPrice()/100+"")));
					details.setBasicPrice(Integer.parseInt((order.getProductPrice()/100+"")));
					details.setQuantity(array.getJSONObject(i).getIntValue("quantity"));
					details.setUseEndDate(new Date());
					details.setUseEndDate(new Date());
					details.setName(array.getJSONObject(i).getString("name"));
					details.setIdCard(array.getJSONObject(i).getString("idCard"));
					details.setPhone(array.getJSONObject(i).getString("phone"));
					details.setFace("");
					details.setAmount(order.getPayMoney());
					vo.getData().getDetails().add(details);
					vo.getError().setCode(0);
					vo.getError().setMessage("success");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					vo.getError().setCode(1);
					vo.getError().setMessage("faile");
				}
				
			}
			
		}else{
			vo.getError().setCode(1);
			vo.getError().setMessage("faile");
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
	
	@Autowired
	ProductDAO conn_product;
	@Autowired
	MerchantDAO conn_merchant;
	@Autowired
	OrderInfoDAO conn_order;
	public OrderInfoPO addOrder(long productId,int num,PayType payType) throws Exception {

		ProductPO productPO2 = conn_product.get(productId);
		
		OrderInfoPO order = new OrderInfoPO();
		// 4/26添加comId 张羽 4/28 添加退款限制
		order.setComId(productPO2.getComId());
		order.setProductIsRefund(productPO2.getProductIsRefund());
		long productprice = productPO2.getProductPrice();
		// 支付金额
		long payMoney = num * productprice;
		// 订单总金额
		long orderAllMoney = payMoney;
		// 获取商家
		MerchantPO merchant = conn_merchant.get(productPO2.getProductMerchantID());
		
		// 订单号（城市编码+商家id+板块Code+时间戳+用户ID）
		
		String orderNO = merchant.getId() + productPO2.getProductModularCode() + DateUtil.getCurrentDateTime();
		order.setOrderNO(orderNO);
		// 下单时间
		order.setCreateDate(new Date());
		order.setUpdateTime(new Date());
		// 供应商ID
		order.setShopId(merchant.getId());
		// 供应商名称
		order.setShopName(merchant.getShopName());
		// 站点ID
		// 站点名称
		// 商品ID
		order.setProductId(productPO2.getId());
		// 商品图片
		order.setProductPic(productPO2.getProductShowPic());
		// 商品名称
		order.setProductName(productPO2.getProductName());
		// 商品数量
		order.setProductNum(Long.parseLong(num+""));
		orderAllMoney = payMoney;
		//System.out.println(orderAllMoney + "-------------------------");
		// 商品单价
		order.setProductPrice(productprice);
		// 所属板块DI
		order.setBkCode(productPO2.getProductModularCode());
		// 所属板块名称
		order.setBkName(productPO2.getProductModularCodeName());
		// 提成方式（0：佣金1：比例）
		order.setRoyaltyName(productPO2.getProductCommissionCode());
		// 订单佣金金额(分)
		//long proportionMoney;
		//if (productPO.getProductCommissionCode() == 1) {
		//	proportionMoney = Integer.parseInt(num) * productPO.getProductPrice() * proportion / 100;
		//} else {
		//	proportionMoney = Integer.parseInt(num) * proportion;
		//}
		//order.setProportionMoney(proportionMoney);
		// 支付金额
		order.setPayMoney(payMoney);
		// 订单总金额
		order.setOrderAllMoney(orderAllMoney);
		// 订单说明
		//if (request.getParameter("orderRemark") != null && request.getParameter("orderRemark").length() > 0) {
			//order.setOrderRemark(request.getParameter("orderRemark"));
		//}
		// 订单状态
		order.setOrderState(OrderStateType.NOTPAY);
		// 订单支付类型 //ALIPAY WEICHAT
		order.setPayMode(payType);
		// 是否评价
		order.setCommentIs(0);
		//if (photo != null) {
		//	order.setPhoto(URLDecoder.decode(photo));
		//}
		//if (idNum != null) {
		//	order.setIdNum(idNum);
		//}

	    order.setSource(OrderSource.UNLINE);
	    order.setOrderBookDate(new Date());
	    order.setSettleDate(new Date());
		//} else {
			// 订单来源
		//	order.setSource(OrderSource.APP);
		//}
		order.setOrderType(OrderType.MERCHANT);
		//productPO.setProductSaleNum(productPO.getProductSaleNum() + 1);
		//productPO.setProductShowNum(productPO.getProductShowNum() + 1);
		//conn_product.update(productPO);
		conn_order.saveOrUpdate(order);
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
		if (payType.equals(PayType.WEICHAT)) { // 微信
			return order;
		} else{
			
		}
			
		return null;
	}
	
}
