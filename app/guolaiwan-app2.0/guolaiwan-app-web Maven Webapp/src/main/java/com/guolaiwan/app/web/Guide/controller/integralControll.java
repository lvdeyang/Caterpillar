package com.guolaiwan.app.web.Guide.controller;

import java.io.BufferedReader;
import java.io.IOException;
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

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.interfac.alipay.AliAppOrderInfo;
import com.guolaiwan.app.web.admin.vo.ChildProductVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.website.vo.AddressVO;
import com.guolaiwan.app.web.weixin.SendMsgUtil;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.AuctionDAO;
import com.guolaiwan.bussiness.admin.dao.ChildProductDAO;
import com.guolaiwan.bussiness.admin.dao.CollectionDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.DistributorDAO;
import com.guolaiwan.bussiness.admin.dao.DistributorProductDAO;
import com.guolaiwan.bussiness.admin.dao.LiveAdvertisementDAO;
import com.guolaiwan.bussiness.admin.dao.LiveDAO;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.LiveProductDAO;
import com.guolaiwan.bussiness.admin.dao.LogisticsDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantUserDao;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.OrderPeopleDao;
import com.guolaiwan.bussiness.admin.dao.PhoneCodeDAO;
import com.guolaiwan.bussiness.admin.dao.PicproRelationDAO;
import com.guolaiwan.bussiness.admin.dao.PictureDAO;
import com.guolaiwan.bussiness.admin.dao.ProductComboDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.ProfessionalLiveDAO;
import com.guolaiwan.bussiness.admin.dao.ProfessionalLiveDirectorDAO;
import com.guolaiwan.bussiness.admin.dao.ProfessionalLiveMatPlayVedioDAO;
import com.guolaiwan.bussiness.admin.dao.ProfessionalLiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.RoomStatusDao;
import com.guolaiwan.bussiness.admin.dao.SurpportBuyDao;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserOnedayBuyDAO;
import com.guolaiwan.bussiness.admin.dao.VPCommentDAO;
import com.guolaiwan.bussiness.admin.dao.VPRelDAO;
import com.guolaiwan.bussiness.admin.dao.VideoPicDAO;
import com.guolaiwan.bussiness.admin.dao.live.LiveOrderDao;
import com.guolaiwan.bussiness.admin.dao.live.LiveRecordDao;
import com.guolaiwan.bussiness.admin.dao.live.SubLiveDao;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MerchantUser;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.OrderPeoplePo;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.RoomStatusPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.UserOneDayBuyPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.util.date.DateUtil;

@Controller
@RequestMapping("/integral")  //积分商城
public class integralControll extends WebBaseControll  {
	@Autowired
	private ProductDAO conn_product;
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private UserInfoDAO conn_user;
	@Autowired
	private ProductComboDAO conn_combo;
	@Autowired
	SurpportBuyDao conn_surpportbuy;
	@Autowired
	RoomStatusDao conn_roomstatus;
    @Autowired
    OrderPeopleDao conn_orderPeople;
    @Autowired
	private ActivityRelDAO conn_activityRel;
	@Autowired
	private OrderInfoDAO conn_order;
	@Autowired
	private MerchantDAO conn_merchant;
	@Autowired
	UserOnedayBuyDAO conn_userone;
	@Autowired
	private LogisticsDao conn_logistics;

	
	
	@RequestMapping(value = "visitors/home")
	public ModelAndView home(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		mv = new ModelAndView("guide/guidemap/home");
		mv.addObject("userId", userId);
		return mv;
	}
	
	
	/**
	 * 
	 * 查询所有积分商品显示
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/integ", method = RequestMethod.POST)
	public Map<String, Object> getInteg(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<ProductPO>	 integ  =      conn_product.getIntegralGoods();
		for (ProductPO productPO : integ) {
			String pic =productPO.getProductShowPic();
			productPO.setProductShowPic(conn_sysConfig.getSysConfig().getWebUrl()+pic);
		};
		List<ProductVO> volist = ProductVO.getConverter(ProductVO.class).convert(integ, ProductVO.class);
		strMap.put("product", volist);
		return success(strMap);
	}
	
	
	/**
	 * 
	 * 用积分总换商品
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/convertibility", method = RequestMethod.POST)
	public Map<String, Object> convertibility(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		Map<String, Object> strMap = new HashMap<String, Object>();
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		String str  = "0";  //表示积分不足
		JSONObject pageObject = JSON.parseObject(param);
		int leng = pageObject.getInteger("integral"); // 所用积分
		Long id = pageObject.getLong("id"); // 商品id
	    UserInfoPO   UserInfo =  conn_user.getUserByUid(userId).get(0);
		long  Integral =  UserInfo.getUserIntegral();
        ProductPO productPO = conn_product.get(id);
        if (productPO.getProductStock() < 1) {
        	str  = "2"; //库存不足 
		}else if(Integral >= leng){
        	Integral =   Integral -leng;
			UserInfo.setUserIntegral(Integral);
			conn_user.saveOrUpdate(UserInfo); //减少用户积分
			System.out.println("总换成功");
        	productPO.setProductStock(productPO.getProductStock()-1);
        	conn_product.saveOrUpdate(productPO);
        	str =  "1";//总换成功
        }
		return success(str);
	}
	
	
	
	/**
	 * 订单：立即支付
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/order/add", method = RequestMethod.POST)
	public Map<String, Object> addOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String productId = pageObject.getString("productId");
		String num = pageObject.getString("productNum");
		Long userId = Long.parseLong(pageObject.getString("userId"));
		String paytype = pageObject.getString("paytype");
		String activityId = pageObject.getString("activityId");
		long money = pageObject.getLong("payMoney");
		long comboName = pageObject.getLong("comboName");
		ProductPO productPO = conn_product.get(Long.parseLong(productId));
		if (num == null) {
			num = "1";
		}

		OrderInfoPO order = new OrderInfoPO();
		// 4/26添加comId 张羽 4/28 添加退款限制
		ProductPO productPO2 = conn_product.get(Long.parseLong(productId));
		order.setComId(productPO2.getComId());
		order.setLogisticsId(comboName); // 添加快递领取 到店领取
		order.setProductIsRefund(productPO2.getProductIsRefund());
		

		String orderStartDate = pageObject.getString("startDate");
		if (orderStartDate != null && orderStartDate != "" && orderStartDate.length() != 0) {
			orderStartDate = orderStartDate.replace("T", " ");
			order.setOrderBookDate(DateUtil.parse(orderStartDate, DateUtil.dateTimePattenWithoutSecind));
		}
		String endBookDate = pageObject.getString("endDate");
		if (endBookDate != null && endBookDate != "" && endBookDate.length() != 0) {
			endBookDate = endBookDate.replace("T", " ");
			order.setEndBookDate(DateUtil.parse(endBookDate, DateUtil.dateTimePattenWithoutSecind));
		}

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();

		//生成验单码,和二维码图片
		String ydNO = ydNoCode(order.getId()+"");
		order.setYdNO(ydNO);
		
		order.setProductPrice(money);
		// 支付金额
		long payMoney = Integer.parseInt(num) * money;
		// 订单总金额
		long orderAllMoney = payMoney;
		// 获取产品

		// 获取商家
		MerchantPO merchant = conn_merchant.get(productPO.getProductMerchantID());
		UserInfoPO user = conn_user.get(userId);
		String orderBookDate = pageObject.getString("bookDate");
		if (orderBookDate != null && orderBookDate != "" && orderBookDate.length() != 0) {
			orderBookDate = orderBookDate.replace("T", " ");
			order.setOrderBookDate(DateUtil.parse(orderBookDate, DateUtil.dateTimePattenWithoutSecind));
		}

		// 会员ID
		order.setUserId(userId);
		if (user.getUserPhone() != null) {
			// 会员手机号
			order.setUserTel(user.getUserPhone());
			// 会员信息
			// 会员坐标经度
			// 会员坐标维度
			// 提成金额比例
		}
		if (user.getUserNickname() != null) {
			order.setUserName(user.getUserNickname());
		}

		// 订单号（城市编码+商家id+板块Code+时间戳+用户ID）
		String orderNO = getCityCodeByDomain() + merchant.getId() + productPO.getProductModularCode() + df.format(date)
				+ userId;
		order.setOrderNO(orderNO);
		// 验单码

		// 下单时间
		order.setCreateDate(date);
		order.setUpdateTime(date);
		// 验单时间

		// 供应商ID
		order.setShopId(merchant.getId());
		// 供应商名称
		order.setShopName(merchant.getShopName());
		// 站点ID
		// 站点名称
		// 商品ID
		order.setProductId(productPO.getId());
		// 商品图片
		order.setProductPic(productPO.getProductShowPic());
		// 商品名称
		order.setProductName(productPO.getProductName());
		// 商品数量
		order.setProductNum(Long.parseLong(num));
		// 获取用户
		if (activityId != null) {

			UserOneDayBuyPO buyPO = new UserOneDayBuyPO();
			buyPO.setUpdateTime(new Date());
			buyPO.setUserId(userId);
			buyPO.setProId(Long.parseLong(activityId));
			if (orderBookDate != null && orderBookDate != "" && orderBookDate.length() != 0) {
				orderBookDate = orderBookDate.replace("T", " ");
				buyPO.setBookDate(DateUtil.parse(orderBookDate, DateUtil.dateTimePattenWithoutSecind));
			}
			conn_userone.save(buyPO);
			order.setActivityId(Long.parseLong(activityId));
			conn_surpportbuy.delSurpport(userId, Long.parseLong(activityId));

			ActivityRelPO activityRelPO = new ActivityRelPO();
			activityRelPO = conn_activityRel.get(Long.parseLong(activityId));
			payMoney = Integer.parseInt(num) * (activityRelPO.getPrice());
			orderAllMoney = payMoney;
		}

		// 所属板块DI
		order.setBkCode(productPO.getProductModularCode());
		// 所属板块名称
		order.setBkName(productPO.getProductModularCodeName());

		// 套餐ID
		// 套餐名称

		long proportion = productPO.getProductCommissionPrice();
		if (productPO.getProductCommissionCode() == 1) {
			order.setProportion(proportion);
		}
		// 提成方式（0：佣金1：比例）
		order.setRoyaltyName(productPO.getProductCommissionCode());
		// 积分数
		long integralNum = Integer.parseInt(num) * productPO.getProductntegral();
		order.setIntegralNum(integralNum);
		// 订单佣金金额(分)
		long proportionMoney;
		if (productPO.getProductCommissionCode() == 1) {
			proportionMoney = Integer.parseInt(num) * productPO.getProductPrice() * proportion / 100;
		} else {
			proportionMoney = Integer.parseInt(num) * proportion;
		}
		order.setProportionMoney(proportionMoney);
		// 支付金额
		order.setPayMoney(payMoney);
		// 订单总金额
		order.setOrderAllMoney(orderAllMoney);
		// 订单说明
		if (request.getParameter("orderRemark") != null && request.getParameter("orderRemark").length() > 0) {
			order.setOrderRemark(request.getParameter("orderRemark"));
		}
		// 订单状态
		order.setOrderState(OrderStateType.PAYSUCCESS);
		// 订单支付类型 //ALIPAY WEICHAT
		order.setPayMode(PayType.fromString(paytype));
		// 有效期
		// 配送信息
		if (pageObject.getString("addressId") != null && pageObject.getString("addressId") != "") {
			Long addressId = Long.parseLong(pageObject.getString("addressId"));
			order.setMailAddress(addressId);
		}
		// 是否评价
		order.setCommentIs(0);
		// // 预订日期
		// order.setOrderBookDate(date);

		
		String photo=pageObject.getString("photo");
		String idNum=pageObject.getString("idNum");
		if(photo!=null){
			order.setPhoto(URLDecoder.decode(photo));
		}
		if(idNum!=null){
			order.setIdNum(idNum);
		}
		
		
		if (pageObject.getString("source") != null) {
			order.setSource(OrderSource.fromString(pageObject.getString("source")));
		} else {
			// 订单来源
			order.setSource(OrderSource.APP);
		}
		order.setOrderType(OrderType.MERCHANT);
		productPO.setProductSaleNum(productPO.getProductSaleNum() + 1);
		productPO.setProductShowNum(productPO.getProductShowNum() + 1);
		conn_product.update(productPO);
		conn_order.saveOrUpdate(order);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JSONArray array=pageObject.getJSONArray("idnums");
		if(array!=null){
			for (Object obj : array) {
				JSONObject jobj=(JSONObject)obj;
				OrderPeoplePo orderPeoplePo=new OrderPeoplePo();
				orderPeoplePo.setIdNum(jobj.getString("idNum"));
				orderPeoplePo.setPhoto(URLDecoder.decode(jobj.getString("photo")));
				orderPeoplePo.setOrderId(order.getId());
				orderPeoplePo.setName(jobj.getString("name"));
				conn_orderPeople.save(orderPeoplePo);
			}
		}
		sendMessage(order);
		data.put("id", order.getId());
		return success(data);
	}

	@RequestMapping(value = "/order/info")
	public ModelAndView orderInfo(HttpServletRequest request, long orderId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("guide/guidemap/orderInfo");
		mv.addObject("orderId", orderId);
		return mv;
	}
	
	
	@Autowired MerchantUserDao conn_merchantUser;
	@Autowired
	private AddressDAO conn_address;
    private void sendMessage(OrderInfoPO orderInfoPO){
    	ProductPO productPO=conn_product.get(orderInfoPO.getProductId());
    	MerchantPO merchantPO=conn_merchant.get(orderInfoPO.getShopId());
    	
    	//用户推送消息
    	Double amount=Double.parseDouble(orderInfoPO.getPayMoney()+"");
    	DecimalFormat df=new DecimalFormat("0.00");  
    	UserInfoPO buyUser=conn_user.get(orderInfoPO.getUserId());
    	if(buyUser!=null){
    		JSONObject obj=new JSONObject();
    		obj.put("touser", buyUser.getUserOpenID());
        	obj.put("template_id", "hYekXkjHcZjheDGxqUJM2OwIZpXT0DKwPsfNZbF07SA");
        	obj.put("url", "");
        	JSONObject microProObj=new JSONObject();
        	microProObj.put("appid", "");
        	microProObj.put("pagepath", "");
        	obj.put("miniprogram", microProObj);
        	JSONObject dataObject=new JSONObject();
        	JSONObject firstObj=new JSONObject();
        	firstObj.put("value", "您的商品总换成功");
        	firstObj.put("color", "");
        	dataObject.put("first", firstObj);
        	
        	
        	JSONObject nameObj=new JSONObject();
        	nameObj.put("value", buyUser.getUserNickname());
        	nameObj.put("color", "");
        	dataObject.put("keyword1", nameObj);
        	
        	JSONObject accountTypeObj=new JSONObject();
        	accountTypeObj.put("value", orderInfoPO.getId());
        	accountTypeObj.put("color", "");
        	dataObject.put("keyword2", accountTypeObj);
        	
        	
        	JSONObject accountObj=new JSONObject();
        	accountObj.put("value", df.format(amount));
        	accountObj.put("color", "");
        	dataObject.put("keyword3", accountObj);
        	JSONObject timeObj=new JSONObject();
        	timeObj.put("value",productPO==null?"积分商品兑换订单:"+merchantPO.getShopName():productPO.getProductName());
        	timeObj.put("color", "");
        	dataObject.put("keyword4", timeObj);
        	JSONObject remarkObj=new JSONObject();
        	remarkObj.put("value", "感谢使用过来玩服务");
        	remarkObj.put("color", "");
        	dataObject.put("remark", remarkObj);
        	obj.put("data", dataObject);
        	SendMsgUtil.sendTemplate(obj.toJSONString());
    	}
    	

		JSONObject obj = new JSONObject();
		obj.put("touser", "opVUYv9LtqKAbiaXInBqI01hlpYg");
		obj.put("template_id", "imgTupyObgSuKRYqZrc8VAXgzGePPEeuqwVG7IF_Rzw");
		obj.put("url", "");
		JSONObject microProObj = new JSONObject();
		microProObj.put("appid", "");
		microProObj.put("pagepath", "");
		obj.put("miniprogram", microProObj);
		JSONObject dataObject = new JSONObject();
		JSONObject firstObj = new JSONObject();
		firstObj.put("value", "积分订单完成");
		firstObj.put("color", "");
		dataObject.put("first", firstObj);
		String nowDate = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");

		JSONObject nameObj = new JSONObject();
		nameObj.put("value", nowDate);
		nameObj.put("color", "");
		dataObject.put("keyword2", nameObj);

		JSONObject accountTypeObj = new JSONObject();
		accountTypeObj.put("value", orderInfoPO.getId());
		accountTypeObj.put("color", "");
		dataObject.put("keyword1", accountTypeObj);

		JSONObject remarkObj = new JSONObject();
		remarkObj.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName()
				: productPO.getProductName() + "(" + df.format(amount) + "积分)");
		remarkObj.put("color", "");
		dataObject.put("remark", remarkObj);
		obj.put("data", dataObject);
		SendMsgUtil.sendTemplate(obj.toJSONString());

		// opVUYv9LtqKAbiaXInBqI01hlpYg
    };
	
	
    /**
	 * 订单：订单详情
	 * 
	 * @param orderId
	 * @return order 邮寄地址address
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/orderInfo", method = RequestMethod.GET)
	public Map<String, Object> orderInfo(HttpServletRequest request, Long orderId) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 订单
		OrderInfoPO order = conn_order.get(orderId);

		// 邮寄地址
		AddressPO address = conn_address.get(order.getMailAddress());
		if (address != null) {
			AddressVO _address = new AddressVO().set(address);
			dataMap.put("address", _address);
		} else {
			dataMap.put("address", null);
		}
		// 商家
		MerchantPO merchantPO = conn_merchant.get(order.getShopId());

		OrderInfoVO _order = new OrderInfoVO().set(order);
		if (_order.getProductName() == null) {
			_order.setProductName("");
		}
		_order.setYdNO(sysConfig.getWebUrl() + _order.getYdNO());
		_order.setProductPic(sysConfig.getWebUrl() + _order.getProductPic());
		_order.setShopLongitude(merchantPO.getShopLongitude());
		_order.setShopLatitude(merchantPO.getShopLatitude());  
		
		if (_order.getComboId() != 0) {
			ProductComboPO comboPO = conn_combo.get(_order.getComboId());
			if(comboPO!=null){
				_order.setProductPrice(new DecimalFormat("0.00").format((double) comboPO.getComboprice() / 100));
				_order.setComboName(comboPO.getCombo());
			}
			
		} else {
			_order.setComboName("标准");
		}
		LogisticsPo logisticsPo = conn_logistics.get(_order.getLogisticsId());
		if (logisticsPo != null) {
			_order.setLogisticsName(logisticsPo.getName());
		} else { 
			_order.setLogisticsName("-");
		}

		if (_order.getActivityId() != 0) {
			ActivityRelPO activityRelPO = conn_activityRel.get(_order.getActivityId());
			if(activityRelPO!=null){
				_order.setProductPrice(
						new DecimalFormat("0.00").format((double) activityRelPO.getPrice() / 100));
			}
		}
		ProductPO Product =   conn_product.getProductByProId(order.getProductId()).get(0);
	    if ( Product.getSite() != null) {
	    	dataMap.put("site",  Product.getSite());
		}	
		dataMap.put("order", _order);
		return success(dataMap);
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

	
}
