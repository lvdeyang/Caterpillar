package com.guolaiwan.app.web.publicnum.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.interfac.util.HttpUtils;
import com.guolaiwan.app.web.admin.vo.BalanceVO;
import com.guolaiwan.app.web.admin.vo.ChildProductVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.publicnum.util.EmojiFilter;
import com.guolaiwan.app.web.publicnum.vo.BundleOrderVo;
import com.guolaiwan.app.web.publicnum.vo.LiveMessageVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.weixin.JsTicketUtil;
import com.guolaiwan.app.web.weixin.SendMsgUtil;
import com.guolaiwan.app.web.weixin.WxConfig;
import com.guolaiwan.app.web.weixin.YuebaWxPayConstants;
import com.guolaiwan.app.web.weixin.YuebaWxUtil;
import com.guolaiwan.bussiness.Parking.dao.OrderDao;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.admin.dao.ActivityBundleDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.BalanceDAO;
import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
import com.guolaiwan.bussiness.admin.dao.ChildProductDAO;
import com.guolaiwan.bussiness.admin.dao.CollectionDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.InvestWalletDAO;
import com.guolaiwan.bussiness.admin.dao.LiveDAO;
import com.guolaiwan.bussiness.admin.dao.LiveGiftDAO;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.LiveTipGiftDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantBusinessDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantUserDao;
import com.guolaiwan.bussiness.admin.dao.MessageDAO;
import com.guolaiwan.bussiness.admin.dao.OlChatMessageDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.OrderPeopleDao;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SurpportBuyDao;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.SystemCacheDao;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserOnedayBuyDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.ActiveBundlePo;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.BalancePO;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.CollectionPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.InvestWalletPO;
import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.LiveTipGiftPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MerchantUser;
import com.guolaiwan.bussiness.admin.po.MessagePO;
import com.guolaiwan.bussiness.admin.po.OlChatMessagePO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SurpportBuyPo;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.SystenCachePo;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.UserOneDayBuyPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.communication.http.client.HttpClient;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;
import pub.caterpillar.weixin.constants.WXContants;

//portal
@Controller
@RequestMapping("/pubnum")
public class PubNumController extends WebBaseControll {

	private boolean istest = WXContants.istest;
	@Autowired
	private SystemCacheDao conn_systemcache;
	@Autowired
	private InvestWalletDAO conn_investwallet;
	@Autowired
	private OlChatMessageDAO conn_olchatmessage;
	@Autowired
	private MessageDAO messagedao;
	@Autowired
	private MerchantBusinessDAO conn_merchantBusiness;
	// 商户
	@Autowired
	private MerchantDAO conn_merchant;

	@RequestMapping(value = "/index1", method = RequestMethod.GET)
	public ModelAndView index1(HttpServletRequest request, String rUrl) throws Exception {

		ModelAndView mv = null;
		if (!istest) {
			String gState = new Date().getTime() + "";
			if (rUrl != null && rUrl.indexOf("favicon.ico") == -1) {

				SystenCachePo cachePo = new SystenCachePo();
				cachePo.setWxKey(gState);
				cachePo.setWxVal(rUrl);
				conn_systemcache.save(cachePo);
			}

			String redirect = "http://" + WXContants.Website + "/guolaiwan/pubnum/index2";
			redirect = URLEncoder.encode(redirect);
			StringBufferWrapper weixinLogin = new StringBufferWrapper()
					.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append(WxConfig.appId)
					.append("&redirect_uri=").append(redirect)
					.append("&response_type=code&scope=snsapi_userinfo&state=" + gState + "#wechat_redirect");
			mv = new ModelAndView("redirect:" + weixinLogin.toString());
			System.out.println(weixinLogin.toString());
		} else {
			String gState = new Date().getTime() + "";
			SystenCachePo cachePo = new SystenCachePo();
			cachePo.setWxKey(gState);
			cachePo.setWxVal(rUrl);
			conn_systemcache.save(cachePo);
			mv = new ModelAndView("redirect:index2");
		}
		return mv;
	}

	@Autowired
	private UserInfoDAO conn_user;

	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public ModelAndView index2(String code, String state, HttpServletRequest request) throws Exception {
		List<SystenCachePo> cachePos = conn_systemcache.findByField("wxKey", state);

		String rUrl = "";
		if (!cachePos.isEmpty()) {
			rUrl = cachePos.get(0).getWxVal();
		}
		conn_systemcache.delete(cachePos.get(0));
		ModelAndView mv = null;
		String openid = "";
		String nickname = "";
		String headimgurl = "";
		// 获取授权access_token
		if (!istest) {
			JSONObject params = new JSONObject();
			params.put("appid", WxConfig.appId);
			params.put("secret", WxConfig.appsrcret);
			params.put("code", code);
			params.put("grant_type", "authorization_code");
			String result = HttpClient.get("https://api.weixin.qq.com/sns/oauth2/access_token", params);
			JSONObject accessTokenInfo = JSON.parseObject(result);
			String access_token = accessTokenInfo.getString("access_token");
			openid = accessTokenInfo.getString("openid");
			// 拉取用户详细信息
			params = new JSONObject();
			params.put("access_token", access_token);
			params.put("openid", openid);
			params.put("lang", "zh_CN");
			result = HttpClient.get("https://api.weixin.qq.com/sns/userinfo", params);
			JSONObject userInfo = JSON.parseObject(result);
			try {
				nickname = EmojiFilter.filterEmoji(userInfo.getString("nickname"));
			} catch (Exception e) {
				// TODO: handle exception
				nickname = "无法获取用户名";
			}

			headimgurl = URLDecoder.decode(userInfo.getString("headimgurl"));
		} else {
			openid = "opVUYv7wr-zPKl92ilFpqB8yS82I";
		}
		/**/
		// 测试

		UserInfoPO userInfoPO = null;
		List<UserInfoPO> users = conn_user.getUsersByOpenId(openid);
		if (users != null) {
			for (UserInfoPO userInfoPO2 : users) {
				MerchantPO merchantPO = conn_merchant.getMerByUser(userInfoPO2);
				if (merchantPO == null) {
					userInfoPO = userInfoPO2;
				}
			}
		}

		if (userInfoPO == null) {
			userInfoPO = new UserInfoPO();
			userInfoPO.setUpdateTime(new Date());
			userInfoPO.setUserOpenID(openid);
			// 测试
			if (!istest) {
				userInfoPO.setUserHeadimg(headimgurl);
				userInfoPO.setUserNickname(nickname);
			}
			conn_user.save(userInfoPO);
		}
		HttpSession session = request.getSession();
		session.setAttribute("type", "PHONENUM");
		session.setAttribute("userId", userInfoPO.getId());
		session.setAttribute("openid", openid);
		mv = new ModelAndView("redirect:" + rUrl);
		return mv;
	}

	@RequestMapping(value = "/index")
	public ModelAndView pubHome(HttpServletRequest request, String comCode) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/home");
		if (comCode == null) {
			comCode = "0000";
		}
		mv.addObject("comCode", comCode);
		return mv;
	}

	@Autowired
	private CompanyDAO conn_com;

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getComs", method = RequestMethod.GET)
	public Object getCompanys(HttpServletRequest request) throws Exception {
		return conn_com.findAll();
	}

	@RequestMapping(value = "/merchant/index")
	public ModelAndView merchantIndex(HttpServletRequest request, long merchantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/merchant");
		mv.addObject("merchantId", merchantId);
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		String userHeadimg = conn_user.get(userId).getUserHeadimg();
		mv.addObject("userHeadimg", userHeadimg);
		return mv;
	}

	@RequestMapping(value = "/merchant/index1")
	public ModelAndView carouselIndex(HttpServletRequest request, long code, String classify) throws Exception {
		ModelAndView mv = null;
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		String userHeadimg = conn_user.get(userId).getUserHeadimg();
		switch (classify) {
		case "ACTIVITY":
			mv = new ModelAndView("mobile/pubnum/activity");
			mv.addObject("refActivityId", 0);
			break;
		case "MERCHANT":
			mv = new ModelAndView("mobile/pubnum/merchant");
			mv.addObject("merchantId", code);
			mv.addObject("userHeadimg", userHeadimg);
			break;

		case "PRODUCT":
			mv = new ModelAndView("mobile/pubnum/product");
			// 轮播图商品购买跳转 张羽 新增参数到页面 商品购买数量限制 5/2
			mv.addObject("productRestrictNumber", conn_product.get(code).getProductRestrictNumber());
			mv.addObject("merchantId", conn_product.get(code).getProductMerchantID());
			mv.addObject("id", code);
			mv.addObject("userHeadimg", userHeadimg);
			break;
		default:
			break;
		}

		return mv;
	}

	@RequestMapping(value = "/column/index")
	public ModelAndView columnIndex(HttpServletRequest request, String modularCode) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/column");
		mv.addObject("modularCode", modularCode);
		return mv;
	}

	@RequestMapping(value = "/rejectOrder")
	public ModelAndView rejectOrder(HttpServletRequest request, String modularCode) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/column");
		mv.addObject("modularCode", modularCode);
		return mv;
	}

	@RequestMapping(value = "/product/index")
	public ModelAndView productIndex(HttpServletRequest request, long id, String activityproId) throws Exception {
		ModelAndView mv = null;
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		String userHeadimg = conn_user.get(userId).getUserHeadimg();
		if (activityproId != null && activityproId != "" && activityproId.length() != 0 && !activityproId.equals("0")) {
			mv = new ModelAndView("mobile/pubnum/activityproduct");
			mv.addObject("actId", activityproId);
			mv.addObject("productRestrictNumber", conn_product.get(id).getProductRestrictNumber());
			mv.addObject("merchantId", conn_product.get(id).getProductMerchantID());
			mv.addObject("id", id);
			mv.addObject("userHeadimg", userHeadimg);
		} else {
			mv = new ModelAndView("mobile/pubnum/product");
			mv.addObject("productRestrictNumber", conn_product.get(id).getProductRestrictNumber());
			mv.addObject("merchantId", conn_product.get(id).getProductMerchantID());
			mv.addObject("id", id);
			mv.addObject("userHeadimg", userHeadimg);
		}
		mv.addObject("userId", userId);
		return mv;
	}

	@RequestMapping(value = "product/index/line")
	public ModelAndView productLineIndex(HttpServletRequest request, long id) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/productLine");
		mv.addObject("id", id);
		mv.addObject("userId", request.getSession().getAttribute("userId"));
		return mv;
	}

	@RequestMapping(value = "/personal/index")
	public ModelAndView personalIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/personal");
		HttpSession session = request.getSession();
		UserInfoPO user = conn_user.get(Long.parseLong(session.getAttribute("userId").toString()));
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(value = "/address/index")
	public ModelAndView addressIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/address");
		HttpSession session = request.getSession();
		mv.addObject("userId", session.getAttribute("userId"));
		return mv;
	}

	@RequestMapping(value = "/address/edit")
	public ModelAndView addressAdd(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/editAddress");
		HttpSession session = request.getSession();
		mv.addObject("userId", session.getAttribute("userId"));
		return mv;
	}

	@Autowired
	private AddressDAO conn_address;

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delAddress", method = RequestMethod.GET)
	public Object delAddress(HttpServletRequest request, Long addressId) throws Exception {
		AddressPO addressPO = conn_address.get(addressId);
		addressPO.setDelFlg(1);
		conn_address.save(addressPO);
		return success();
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/setDefAddress", method = RequestMethod.GET)
	public Object setDefAddress(HttpServletRequest request, Long addressId) throws Exception {
		HttpSession session = request.getSession();
		Long userId = Long.parseLong(session.getAttribute("userId").toString());
		AddressPO defAddress = conn_address.getByUser(userId, 1);
		if (defAddress != null) {
			defAddress.setDefaultAddress(0);
			conn_address.save(defAddress);
		}

		AddressPO addressPO = conn_address.get(addressId);
		if (addressPO.getDefaultAddress() == 1) {
			addressPO.setDefaultAddress(0);
		} else {
			addressPO.setDefaultAddress(1);
		}

		conn_address.save(addressPO);
		return success();
	}

	@RequestMapping(value = "/order/list")
	public ModelAndView orderList(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/orderList");
		HttpSession session = request.getSession();
		mv.addObject("userId", session.getAttribute("userId"));
		return mv;
	}

	@RequestMapping(value = "/basket/index")
	public ModelAndView basketIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/basket");
		HttpSession session = request.getSession();
		mv.addObject("userId", session.getAttribute("userId"));
		return mv;
	}

	@RequestMapping(value = "/search/index")
	public ModelAndView searchIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/search");
		HttpSession session = request.getSession();
		mv.addObject("userId", session.getAttribute("userId"));
		return mv;
	}

	@RequestMapping(value = "/favoritelist/index")
	public ModelAndView favoritelistIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/favoritelist");
		HttpSession session = request.getSession();
		mv.addObject("userId", session.getAttribute("userId"));
		return mv;
	}

	@RequestMapping(value = "/order/info")
	public ModelAndView orderInfo(HttpServletRequest request, long orderId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/orderInfo");
		mv.addObject("orderId", orderId);
		return mv;
	}

	@Autowired
	private BundleOrderDAO conn_bundleOrder;

	@ResponseBody
	@RequestMapping(value = "/prev/pay/{id}")
	public Object prevPay(@PathVariable String id, String cip, HttpServletRequest request) throws Exception {
		String orderNo = id + "";
		int payMoney = 0;
		if (id.contains("A")) {
			BundleOrder order = new BundleOrder();
			order.setOrderStr(id);
			conn_bundleOrder.save(order);
			orderNo = "bundle-" + order.getId();
			String[] orderNos = id.split("A");
			for (String string : orderNos) {
				OrderInfoPO orderInfoPO = conn_order.get(Long.parseLong(string));
				payMoney += orderInfoPO.getPayMoney();
			}
		} else {
			OrderInfoPO orderInfoPO = conn_order.get(Long.parseLong(id));
			payMoney += orderInfoPO.getPayMoney();
		}

		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/payreport", WxConfig.appId,
				WxConfig.appsrcret);
		// 统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息
		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", orderNo, payMoney,
				"192.165.56.64", user.getUserOpenID());
		// 生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);
		// 将订单号放入map，用以支付后处理
		map.put("orderNo", orderNo);
		return map;
	}

	// 停车预定支付
	@Autowired
	private OrderDao Order;

	@ResponseBody
	@RequestMapping(value = "/prev/paypark/{id}/{text}/{attactionsId}")
	public Object prevPaypark(@PathVariable String id, @PathVariable Integer text, @PathVariable Integer attactionsId,
			HttpServletRequest request) throws Exception {
		System.out.println("-------------------------------------------------------------------------------" + "支付");
		String orderNo = "park-" + id + "-" + attactionsId/* +"-"ID+"-"景ID */;
		OrderPO orde = Order.getform(Long.parseLong(id));
		orde.setOrderNo(orderNo);
		Order.saveOrUpdate(orde);
		int payMoney = text;
		System.out.println("-----------------------------------------------------------------------------" + payMoney);
		/* int payMoney = 1; */
		// OrderInfoPO orderInfoPO=conn_order.get(Long.parseLong(id));
		// payMoney+=orderInfoPO.getPayMoney();
		// String aLong = request.getParameter("text");
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/payreportpark", WxConfig.appId,
				WxConfig.appsrcret);
		// 统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息
		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", orderNo, payMoney,
				"192.165.56.64", user.getUserOpenID());
		// 生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);
		// 将订单号放入map，用以支付后处理
		map.put("orderNo", orderNo);
		return map;
	}

	// 续费
	@ResponseBody
	@RequestMapping(value = "/prev/payrenewal/{id}/{money}/{attactionsId}/{date}")
	public Object prevRtrenew(@PathVariable String id, @PathVariable Integer money, @PathVariable Integer attactionsId,
			@PathVariable String date, HttpServletRequest request) throws Exception {
		String orderNo = "rene-" + id + "-" + attactionsId + "-"
				+ date/* +"-"ID+"-"景ID */;
		/* int payMoney = money; */
		int payMoney = 1;
		// OrderInfoPO orderInfoPO=conn_order.get(Long.parseLong(id));
		// payMoney+=orderInfoPO.getPayMoney();
		// String aLong = request.getParameter("text");
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/payreportrenew", WxConfig.appId,
				WxConfig.appsrcret);
		// 统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息
		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", orderNo, payMoney,
				"192.165.56.64", user.getUserOpenID());
		// 生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);
		// 将订单号放入map，用以支付后处理
		map.put("orderNo", orderNo);
		return map;
	}

	// 停车退款功能
	@ResponseBody
	@RequestMapping(value = "/prev/parkrefund/{id}")
	// TODO
	public Object prevPaket(@PathVariable String id, HttpServletRequest request) throws Exception {
		String orderNo = "refund-" + id;
		String newOrderNo = "";
		int payMoney = 0;
		OrderPO orderPO = Order.getOrderform(Long.parseLong(id));
		Double cost = orderPO.getParkingCost();
		payMoney += (cost * 100);
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/parkrefund", WxConfig.appId,
				WxConfig.appsrcret);
		// 统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息
		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", orderNo, payMoney,
				"192.165.56.64", orderPO.getOrderNo());
		// 生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);
		// 将订单号放入map，用以支付后处理
		map.put("orderNo", newOrderNo);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/prev/paybasket/{id}/{nums}")
	public Object prevPayBasket(@PathVariable String id, @PathVariable String nums, HttpServletRequest request)
			throws Exception {
		String orderNo = id + "";
		String newOrderNo = "";
		int payMoney = 0;
		if (id.contains("A")) {
			BundleOrder order = new BundleOrder();
			order.setOrderStr(id);
			conn_bundleOrder.save(order);
			orderNo = "bundle-" + order.getId();
			String[] orderNos = id.split("A");
			String[] numsStrs = nums.split("A");

			// 这里要换订单，十分麻烦
			for (int i = 0; i < orderNos.length; i++) {
				OrderInfoPO orderInfoPO = conn_order.get(Long.parseLong(orderNos[i]));

				if (!newOrderNo.equals("")) {
					newOrderNo += "A";
				}
				int numReal = Integer.parseInt(numsStrs[i]);
				if (numReal != orderInfoPO.getProductNum()) {
					// 更换订单
					OrderInfoPO newOrder = (OrderInfoPO) orderInfoPO.clone();
					newOrder.setId(null);
					newOrder.setPayMoney((newOrder.getPayMoney() / newOrder.getProductNum()) * numReal);
					newOrder.setPayMode(PayType.WEICHAT);
					newOrder.setProductNum(numReal);

					conn_order.save(newOrder);
					conn_order.delete(orderInfoPO);
					payMoney += newOrder.getPayMoney();
					newOrderNo += newOrder.getId() + "";
				} else {
					payMoney += orderInfoPO.getPayMoney();
					newOrderNo += orderInfoPO.getId() + "";
				}

			}
		} else {
			OrderInfoPO orderInfoPO = conn_order.get(Long.parseLong(id));
			int numReal = Integer.parseInt(nums);
			if (numReal != orderInfoPO.getProductNum()) {
				OrderInfoPO newOrder = (OrderInfoPO) orderInfoPO.clone();
				newOrder.setId(null);
				newOrder.setPayMoney((newOrder.getPayMoney() / newOrder.getProductNum()) * numReal);
				newOrder.setPayMode(PayType.WEICHAT);
				newOrder.setProductNum(numReal);
				conn_order.save(newOrder);
				conn_order.delete(orderInfoPO);
				payMoney += newOrder.getPayMoney();
				newOrderNo = newOrder.getId() + "";
			} else {
				payMoney += orderInfoPO.getPayMoney();
				newOrderNo = orderInfoPO.getId() + "";
			}

		}

		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/payreport", WxConfig.appId,
				WxConfig.appsrcret);
		// 统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息
		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", newOrderNo, payMoney,
				"192.165.56.64", user.getUserOpenID());
		// 生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);
		// 将订单号放入map，用以支付后处理
		map.put("orderNo", newOrderNo);
		return map;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/order/status", method = RequestMethod.GET)
	public Object orderStatus(HttpServletRequest request, Long orderId) throws Exception {
		OrderInfoPO orderInfoPO = conn_order.get(orderId);
		return orderInfoPO.getOrderState().toString();
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/prev/scan")
	public Object preScan(HttpServletRequest request, String url) {
		url = url.replace("FISH", "&");
		System.out.println(url);
		Map ret = new HashMap();
		String jsapi_ticket = JsTicketUtil.getJsapiTicket();
		String nonce_str = JsTicketUtil.create_nonce_str();
		String timestamp = JsTicketUtil.create_timestamp();
		String string1;
		String signature = "";
		int length = url.indexOf("#");
		String uri = url;
		if (length > 0) {
			uri = url.substring(0, length);// 当前网页的URL，不包含#及其后面部分
		}
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = JsTicketUtil.byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println(signature);
		System.out.println(WxConfig.appId);
		ret.put("appId", WxConfig.appId);
		ret.put("url", uri);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	@RequestMapping(value = "/admin/numchecklist/{muserId}")
	public ModelAndView numChecklist(@PathVariable Long muserId, HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubadmin/numchecklist");
		System.out.println(muserId);
		mv.addObject("muserId", muserId);
		return mv;
	}

	/**
	 * 商户中心页面心跳
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/merchantbeat")
	public void merchantBeat(HttpServletRequest request) throws Exception {
		Long merchantId = Long.parseLong(request.getParameter("merchantId"));
		conn_merchant.get(merchantId).setLastexercisetime(new Date());
	}

	@RequestMapping(value = "/admin/olchat")
	public ModelAndView olchat(HttpServletRequest request) throws Exception {
		Long merchantId = Long.parseLong(request.getSession().getAttribute("merchantId").toString());
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		String userHeadimg = conn_user.get(userId).getUserHeadimg();
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubadmin/olchat");
		mv.addObject("merchantId", merchantId);
		mv.addObject("userHeadimg", userHeadimg);
		return mv;
	}

	@Autowired
	private SysConfigDAO conn_sys;

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public Object adminLogin(HttpServletRequest request) throws Exception {
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();

		String phone = pageObject.getString("phone");
		String password = pageObject.getString("password");
		String auto = pageObject.getString("auto");
		List<UserInfoPO> users = conn_user.findByField("userPhone", phone);
		if (users == null) {
			throw new BaseException(StatusCode.FORBIDDEN, "用户名密码错误");
		}
		MerchantPO merchant = null;
		UserInfoPO user = null;
		for (UserInfoPO userInfoPO : users) {
			merchant = conn_merchant.getMerByUser(userInfoPO);
			if (merchant != null) {
				user = userInfoPO;
				break;
			}
		}
		// 判断是否为商户

		if (merchant == null) {
			throw new BaseException(StatusCode.FORBIDDEN, "该用户不是商户");
		}
		HttpSession session = request.getSession();
		if (!auto.equals("auto")) {
			String s_pas = Sha1Util.getSha1(password);
			System.out.println(s_pas);
			if (!s_pas.equals(user.getUserPassword())) {
				throw new BaseException(StatusCode.FORBIDDEN, "用户名密码错误");
			}

			if (session.getAttribute("userId") != null) {
				// user.setUserOpenID(session.getAttribute("openid").toString());
				// conn_user.save(user);
				// 新增
				MerchantUser merchantUser = conn_merchantuser
						.getMerUserByIds(Long.parseLong(session.getAttribute("userId").toString()), merchant.getId());
				if (merchantUser == null) {
					merchantUser = new MerchantUser();
					merchantUser.setUserId(Long.parseLong(session.getAttribute("userId").toString()));
					merchantUser.setMerchantId(merchant.getId());
					conn_merchantuser.save(merchantUser);
				}
			}
		}
		conn_merchant.get(merchant.getId()).setLastexercisetime(new Date());
		session.setAttribute("merchantId", merchant.getId());
		session.setAttribute("userName", phone);
		session.setAttribute("password", password);
		return null;
	}

	@RequestMapping(value = "/admin/logout")
	public ModelAndView adminLogout(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubadmin/apply");
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null && session.getAttribute("merchantId") != null) {
			String userId = session.getAttribute("userId").toString();
			String merchantId = session.getAttribute("merchantId").toString();
			conn_merchantuser.delMerUserByIds(Long.parseLong(userId), Long.parseLong(merchantId));
		}

		session.setAttribute("merchantId", null);
		return mv;
	}

	@Autowired
	private MerchantUserDao conn_merchantuser;

	@RequestMapping(value = "/admin/index")
	public ModelAndView marchantIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubadmin/index");
		HttpSession session = request.getSession();
		if (session.getAttribute("merchantId") == null) {
			mv = new ModelAndView("mobile/pubadmin/apply");
			// 新增多个用户
			List<MerchantUser> merchantUsers = conn_merchantuser.findByField("userId",
					Long.parseLong(session.getAttribute("userId").toString()));
			if (merchantUsers != null && !merchantUsers.isEmpty()) {
				MerchantPO merchantPO = conn_merchant.get(merchantUsers.get(0).getMerchantId());
				mv.addObject("phone", merchantPO.getUser().getUserPhone());
				mv.addObject("auto", "auto");
			}

			// List<UserInfoPO>
			// userInfoPOs=conn_user.getUsersByOpenId(session.getAttribute("openid").toString());
			// for (UserInfoPO userInfoPO2 : userInfoPOs) {
			// MerchantPO merchantPO=conn_merchant.getMerByUser(userInfoPO2);
			// if(merchantPO!=null){
			// mv.addObject("phone",merchantPO.getUser().getUserPhone());
			// mv.addObject("auto","auto");
			// }
			// }

			return mv;
		}
		Long merchantId = Long.parseLong(session.getAttribute("merchantId").toString());
		MerchantPO merchantPO = conn_merchant.get(merchantId);
		MerchantVO vo = new MerchantVO();
		vo.set(merchantPO);
		SysConfigPO sys = conn_sys.getSysConfig();
		vo.setShopPic(sys.getWebUrl() + vo.getShopPic());
		mv.addObject("merchant", vo);
		mv.addObject("userId", session.getAttribute("userId"));
		mv.addObject("muserId", merchantPO.getUser().getId());
		return mv;
	}

	@Autowired
	private ProductDAO conn_product;

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getMerchantProCount", method = RequestMethod.GET)
	public Object queryProCount(HttpServletRequest request, String code) throws Exception {
		Long merchantId = Long.parseLong(request.getSession().getAttribute("merchantId").toString());
		List<ProductPO> productPOs = conn_product.getProductsByMerAndModar(merchantId, code);
		if (productPOs == null) {
			return 0;
		}
		return productPOs.size();
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getMerchantPro", method = RequestMethod.GET)
	public Object queryPro(HttpServletRequest request, String code) throws Exception {
		Long merchantId = Long.parseLong(request.getSession().getAttribute("merchantId").toString());
		List<ProductPO> productPOs = conn_product.getProductsByMerAndModar(merchantId, code);
		List<ProductVO> vos = ProductVO.getConverter(ProductVO.class).convert(productPOs, ProductVO.class);
		SysConfigPO sysConfigPO = conn_sys.getSysConfig();
		for (ProductVO productVO : vos) {
			productVO.setProductShowPic(sysConfigPO.getWebUrl() + productVO.getProductShowPic());
		}
		return vos;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getMerchantAllPro", method = RequestMethod.GET)
	public Object queryAllPro(HttpServletRequest request) throws Exception {
		Long merchantId = Long.parseLong(request.getSession().getAttribute("merchantId").toString());
		List<ProductPO> productPOs = conn_product.getProductsByMer(merchantId, 1, 100);
		List<ProductVO> vos = ProductVO.getConverter(ProductVO.class).convert(productPOs, ProductVO.class);
		return vos;
	}

	@Autowired
	private OrderInfoDAO conn_order;

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getOrderCount", method = RequestMethod.GET)
	public Object queryAdminOrderCount(HttpServletRequest request, String type) throws Exception {
		Long merchantId = Long.parseLong(request.getSession().getAttribute("merchantId").toString());
		return conn_order.getOrdersByMerState(merchantId, OrderStateType.fromString(type)).size();

	}

	@RequestMapping(value = "/admin/order/{status}")
	public ModelAndView adminOrder(HttpServletRequest request, @PathVariable String status) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubadmin/orderList");
		mv.addObject("status", status);
		return mv;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getOrder", method = RequestMethod.GET)
	public Object queryAdminOrder(HttpServletRequest request, String type) throws Exception {
		Long merchantId = Long.parseLong(request.getSession().getAttribute("merchantId").toString());
		List<OrderInfoPO> orderList = conn_order.getOrdersByMerState(merchantId, OrderStateType.fromString(type));
		List<OrderInfoVO> vos = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderList, OrderInfoVO.class);
		SysConfigPO sys = conn_sys.getSysConfig();
		for (OrderInfoVO orderInfoVO : vos) {
			orderInfoVO.setProductPic(sys.getWebUrl() + orderInfoVO.getProductPic());
		}
		return vos;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
	public Object queryOrderBycondition(HttpServletRequest request, String type, long proId, String start, String end)
			throws Exception {
		Long merchantId = Long.parseLong(request.getSession().getAttribute("merchantId").toString());

		List<OrderInfoPO> orderList = conn_order.searchOrder(merchantId, OrderStateType.fromString(type), proId, start,
				end);
		List<OrderInfoVO> vos = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderList, OrderInfoVO.class);
		SysConfigPO sys = conn_sys.getSysConfig();
		for (OrderInfoVO orderInfoVO : vos) {
			orderInfoVO.setProductPic(sys.getWebUrl() + orderInfoVO.getProductPic());
		}
		return vos;
	}

	@RequestMapping(value = "/admin/product")
	public ModelAndView adminProduct(HttpServletRequest request, String code) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubadmin/product");
		mv.addObject("code", code);
		return mv;
	}

	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, String content) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/search");
		byte bb[];
		bb = content.getBytes("ISO-8859-1"); // 以"ISO-8859-1"方式解析name字符串
		content = new String(bb, "UTF-8");
		mv.addObject("content", content);
		return mv;
	}

	@RequestMapping(value = "/search/post")
	public ModelAndView search(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/search");
		String content = request.getParameter("searchContent");
		String type = request.getParameter("type");
		mv.addObject("content", content);
		mv.addObject("type", type);
		return mv;
	}

	@Autowired
	private LiveDAO conn_live;

	@RequestMapping(value = "/live/index")
	public ModelAndView liveIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/live");
		HttpSession session = request.getSession();
		return mv;
	}

	@RequestMapping(value = "/liveplay")
	public ModelAndView livePlay(HttpServletRequest request, Long id) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/liveplay");
		LivePO live = conn_live.get(id);
		mv.addObject("live", live);
		HttpSession session = request.getSession();
		mv.addObject("userId", session.getAttribute("userId"));
		return mv;
	}

	@Autowired
	private LiveMessageDAO conn_livemsg;

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getLiveMessage", method = RequestMethod.GET)
	public Object getLiveMessage(HttpServletRequest request, Long liveId) throws Exception {
		List<LiveMessagePO> messagePOs = conn_livemsg.findByLive(liveId);
		List<LiveMessageVo> vos = new ArrayList<LiveMessageVo>();
		for (LiveMessagePO liveMessagePO : messagePOs) {
			LiveMessageVo vo = new LiveMessageVo();
			vo.setUserName(liveMessagePO.getUserName());
			vo.setContent(liveMessagePO.getMessage());
			vo.setHeadImg(conn_user.get(liveMessagePO.getUserId()).getUserHeadimg());
			vos.add(vo);
		}
		return vos;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/changeOrderAddress", method = RequestMethod.GET)
	public Object changeOrderAddress(HttpServletRequest request, String orderStr, long addressId) throws Exception {
		String[] orderIds = orderStr.split("A");
		for (String idStr : orderIds) {
			OrderInfoPO orderInfoPO = conn_order.get(Long.parseLong(idStr));
			orderInfoPO.setMailAddress(addressId);
			conn_order.save(orderInfoPO);
		}
		return "";
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/changeOrderStatus", method = RequestMethod.GET)
	public Object changeOrderStatus(HttpServletRequest request, String orderId, String status, String trackingnumber)
			throws Exception {

		String[] orderids = null;
		if (orderId.indexOf("bundle") != -1) {
			String[] bundleStrs = orderId.split("-");
			BundleOrder bundleOrder = conn_bundleOrder.get(Long.parseLong(bundleStrs[1]));
			orderids = bundleOrder.getOrderStr().split("A");
		} else {
			orderids = orderId.split("A");
		}
		for (String orderIdStr : orderids) {
			OrderInfoPO orderInfoPO = conn_order.get(Long.parseLong(orderIdStr));
			orderInfoPO.setOrderState(OrderStateType.fromString(status));
			orderInfoPO.setTrackingnumber(trackingnumber);
			conn_order.save(orderInfoPO);
		}

		return success();
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/applyReject", method = RequestMethod.POST)
	public Object applyReject(HttpServletRequest request) throws Exception {
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();
		String reason = pageObject.getString("reason");
		String orderStr = pageObject.getString("orderId");
		String[] orderids = null;
		if (orderStr.indexOf("bundle") != -1) {
			String[] bundleStrs = orderStr.split("-");
			BundleOrder bundleOrder = conn_bundleOrder.get(Long.parseLong(bundleStrs[1]));
			orderids = bundleOrder.getOrderStr().split("A");
		} else {
			orderids = orderStr.split("A");
		}
		// long orderId =
		for (String orderIdStr : orderids) {
			Long orderId = Long.parseLong(orderIdStr);
			OrderInfoPO orderInfoPO = conn_order.get(orderId);
			orderInfoPO.setOrderState(OrderStateType.REFUNDING);
			orderInfoPO.setRefundReason(reason);
			conn_order.save(orderInfoPO);
			sendMessage(orderInfoPO, reason);
		}
		return success();
	}

	private void sendMessage(OrderInfoPO orderInfoPO, String reason) {

		ProductPO productPO = conn_product.get(orderInfoPO.getProductId());
		MerchantPO merchantPO = conn_merchant.get(orderInfoPO.getShopId());
		UserInfoPO buyUser = conn_user.get(orderInfoPO.getUserId());
		AddressPO addressPO = conn_address.get(orderInfoPO.getMailAddress());
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
		remarkObj.put("value",
				(productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName()) + "-用户:"
						+ buyUser.getUserNickname() + "(" + addressPO == null ? ""
								: addressPO.getConsigneePhone() + ")");
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
		remarkObj1.put("value",
				(productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName()) + "-用户:"
						+ buyUser.getUserNickname() + "(" + addressPO == null ? ""
								: addressPO.getConsigneePhone() + ")");

		remarkObj1.put("color", "");
		dataObject1.put("remark", remarkObj1);
		obj1.put("data", dataObject1);
		SendMsgUtil.sendTemplate(obj1.toJSONString());

	}

	@Autowired
	CommentDAO conn_comment;

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public Object changeOrderStatus(HttpServletRequest request) throws Exception {
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();
		Long userId = pageObject.getLong("userId");
		Long orderId = pageObject.getLong("orderId");
		String content = pageObject.getString("content");
		UserInfoPO user = conn_user.get(userId);
		OrderInfoPO order = conn_order.get(orderId);
		if (order.getOrderState().equals(OrderStateType.COMMENTED)) {
			return FORBIDDEN("您已评论了哦");
		}
		order.setOrderState(OrderStateType.COMMENTED);
		conn_order.update(order);
		CommentPO comment = new CommentPO();
		Date date = new Date();
		comment.setUpdateTime(date);
		comment.setContent(content); // 评论内容
		comment.setProId(order.getProductId());// 商品Id
		comment.setUserDate(date);// 评论时间
		comment.setUser(user);
		conn_comment.saveOrUpdate(comment);
		return success();
	}

	@Autowired
	private BalanceDAO conn_balance;

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/admin/balance", method = RequestMethod.GET)
	public Object adminBalance(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		Long merchantId = Long.parseLong(session.getAttribute("merchantId").toString());
		List<BalancePO> balancePOs = conn_balance.findByField("merchantId", merchantId);
		Date today = new Date();
		List<BalanceVO> volist = new ArrayList<BalanceVO>();
		for (BalancePO balancePO : balancePOs) {
			if (balancePO.getSettleDate().getDate() == 10) {
				if (balancePO.getSettleDate().getMonth() == today.getMonth()) {
					if (today.getDate() < 15) {
						continue;
					}
				}
				BalanceVO vo = new BalanceVO();
				vo.set(balancePO);
				vo.setSettleDate(DateUtil.format(balancePO.getSettleDate(), "yyyy-MM") + "-15");
				volist.add(vo);
			}

			else if (balancePO.getSettleDate().getDate() == 20) {
				if (balancePO.getSettleDate().getMonth() == today.getMonth()) {
					if (today.getDate() < 25) {
						continue;
					}
				}
				BalanceVO vo = new BalanceVO();
				vo.set(balancePO);
				vo.setSettleDate(DateUtil.format(balancePO.getSettleDate(), "yyyy-MM") + "-25");
				volist.add(vo);
			} else {

				if (balancePO.getSettleDate().getMonth() == today.getMonth()) {

					continue;

				} else if ((balancePO.getSettleDate().getMonth() + 1) == today.getMonth()) {
					if (today.getDate() < 5) {
						continue;
					}
				} else if (balancePO.getSettleDate().getYear() < today.getYear()) {
					if (today.getDate() < 5) {
						continue;
					}
				}
				BalanceVO vo = new BalanceVO();
				vo.set(balancePO);
				if (balancePO.getSettleDate().getYear() == today.getYear()) {
					vo.setSettleDate(
							DateUtil.format(balancePO.getSettleDate(), "yyyy") + "-" + (today.getMonth() + 1) + "-05");
				} else {
					vo.setSettleDate(DateUtil.format(today, "yyyy-MM") + "-05");
				}

				volist.add(vo);

			}

		}

		List<OrderInfoPO> orderList = conn_order.getOrdersByMerBalanced(merchantId);
		double total = 0d;
		for (OrderInfoPO orderInfoPO : orderList) {
			total += orderInfoPO.getPayMoney() / 100;
		}
		DecimalFormat df = new DecimalFormat("0.00");
		map.put("total", df.format(total));
		map.put("balances", volist);
		return map;
	}

	@RequestMapping(value = "/admin/orderinfo")
	public ModelAndView adminOrderInfo(HttpServletRequest request, long orderId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubadmin/orderInfo");
		mv.addObject("orderId", orderId);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/gotoshop")
	public ModelAndView gotoshop(HttpServletRequest request) {
		ModelAndView mv = null;
		Long productid = Long.valueOf(request.getParameter("productId"));
		ProductPO productPO = conn_product.get(productid);
		mv = new ModelAndView("mobile/pubnum/merchant");
		long merchantId = productPO.getProductMerchantID();
		mv.addObject("merchantId", merchantId);
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		String userHeadimg = conn_user.get(userId).getUserHeadimg();
		mv.addObject("userHeadimg", userHeadimg);
		return mv;

	}

	@Autowired
	private CollectionDAO conn_collection;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delCollectionPro", method = RequestMethod.POST)
	public Object delCollectionPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 解析json
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();

		long userId = pageObject.getLong("userId"); // 用户Id
		long proId = pageObject.getLong("proId"); // 收藏Id
		long activityproductId = pageObject.getLong("activityproductId");

		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("没获取到用户！");
		}
		CollectionPO collectionPO = conn_collection.getByUserIdProId(userId, proId, activityproductId);
		if (collectionPO == null) {
			return FORBIDDEN("没获取收藏！");
		}
		if (collectionPO.getUser().getId() != userId) {
			return FORBIDDEN("非法操作!");
		}

		conn_collection.delete(collectionPO);

		return success();
	}

	@Autowired
	private ChildProductDAO conn_childProduct;

	@ResponseBody
	@RequestMapping(value = "/demo.do/{id}", method = RequestMethod.GET)
	public ModelAndView getGuiDe(HttpServletRequest request, @PathVariable long id) throws Exception {
		ModelAndView mav = null;
		List<ChildProductPO> polist = conn_childProduct.getChildByProductId(id);
		List<ChildProductVO> volist = ChildProductVO.getConverter(ChildProductVO.class).convert(polist,
				ChildProductVO.class);
		mav = new ModelAndView("guide");
		mav.addObject(volist.get(0));
		request.setAttribute("Child", volist.get(0));
		return mav;
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/commentUser", method = RequestMethod.GET)
	public Object getCommentUser(HttpServletRequest request, HttpServletResponse response, long merchantId)
			throws Exception {
		Set<String> headers = new HashSet<String>();

		ProductPO productPO = conn_product.getByMerchantId(merchantId);
		if (productPO != null) {
			List<CommentPO> commentPOs = conn_comment.findByPro(productPO.getId(), 1, 50);
			for (CommentPO commentPO : commentPOs) {
				UserInfoPO user = commentPO.getUser();
				if (user.getUserHeadimg() != null) {
					if (isImagesTrue(user.getUserHeadimg())) {
						headers.add(user.getUserHeadimg());
					}

				}
			}
		}

		return headers;
	}

	private boolean isImagesTrue(String posturl) throws IOException {
		URL url = new URL(posturl);
		HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
		urlcon.setRequestMethod("GET");
		urlcon.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		if (urlcon.getResponseCode() == HttpURLConnection.HTTP_OK) {

			return true;
		} else {

			return false;
		}
	}

	@RequestMapping(value = "/share/list/{id}")
	public ModelAndView toShareJSP(@PathVariable long id, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("mobile/pubnum/sharelist");
		mav.addObject("shareId", id);
		return mav;
	}

	@Autowired
	private ActivityBundleDAO conn_bundle;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getActivityBundle", method = RequestMethod.GET)
	public Object getActivityBundle(HttpServletRequest request, HttpServletResponse response, String comCode)
			throws Exception {
		List<CompanyPO> companyPOs = conn_com.findByField("comCode", comCode);
		List<ActiveBundlePo> activeBundlePos = conn_bundle.findByField("comId", companyPOs.get(0).getId().intValue());
		SysConfigPO sys = conn_sys.getSysConfig();
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("bundles", activeBundlePos);
		ret.put("url", sys.getWebUrl());
		return ret;
	}

	@RequestMapping(value = "/activity/index")
	public ModelAndView activityIndex(HttpServletRequest request, long refActivityId, String comCode) throws Exception {
		ModelAndView mav = null;
		mav = new ModelAndView("mobile/pubnum/activity");
		mav.addObject("refActivityId", refActivityId);
		mav.addObject("comCode", comCode);
		return mav;
	}

	@Autowired
	private ActivityDAO conn_activity;
	@Autowired
	private ActivityRelDAO conn_actrel;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/activity/getProducts", method = RequestMethod.GET)
	public Object getActivityProducts(HttpServletRequest request, HttpServletResponse response, String comCode)
			throws Exception {

		List<CompanyPO> companyPOs = conn_com.findByField("comCode", comCode);
		List<ActiveBundlePo> activeBundlePos = conn_bundle.findByField("comId", companyPOs.get(0).getId().intValue());

		SysConfigPO sys = conn_sys.getSysConfig();
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("bundles", activeBundlePos);
		ret.put("url", sys.getWebUrl());
		List<ActivityPO> activityPOs = conn_activity.findRecomByCom(companyPOs.get(0).getId());
		ret.put("activity", activityPOs);
		DecimalFormat df = new DecimalFormat("0.00");
		for (ActivityPO activityPO : activityPOs) {
			List<ActivityRelPO> activityRelPOs = conn_actrel.findByAcId(activityPO.getId(), 1, 100);
			List<ProductVO> vos = new ArrayList<ProductVO>();
			for (ActivityRelPO activityRelPO : activityRelPOs) {
				ProductVO vo = new ProductVO();
				vo.set(conn_product.get(activityRelPO.getProductId()));
				String price = vo.getProductPrice();
				if (activityPO.getType().equals(ActivityType.FIXEDPRICE)) {
					if (activityRelPO.getPrice() > 0) {
						vo.setProductPrice(df.format(Double.parseDouble(activityRelPO.getPrice() + "") / 100));
					} else {
						vo.setProductPrice(df.format(Double.parseDouble(activityPO.getFixedPrice() + "") / 100));
					}
				} else if (activityPO.getType().equals(ActivityType.DAZHE)) {
					if (activityRelPO.getPrice() > 0) {
						vo.setProductPrice(df.format(Double.parseDouble(activityRelPO.getPrice() + "") / 100));
					} else {
						vo.setProductPrice(df.format(Double.parseDouble(
								Long.parseLong(vo.getProductPrice()) * activityPO.getDiscount() / 10 + "") / 100));
					}
				}
				vo.setActivityReId(activityRelPO.getId());
				vo.setIsSurpport(activityRelPO.getSurpportBuy());
				vos.add(vo);
			}
			ret.put(activityPO.getId() + "", vos);
		}
		return ret;
	}

	@Autowired
	ActivityRelDAO conn_activityRel;

	@RequestMapping(value = "/product/index/{id}")
	public ModelAndView productIndexN(@PathVariable long id, HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		String userHeadimg = conn_user.get(userId).getUserHeadimg();
		mv = new ModelAndView("mobile/pubnum/activityproduct");
		ActivityRelPO activityPro = conn_activityRel.getActivityRelByProductId(id);
		mv.addObject("productRestrictNumber", conn_product.get(id).getProductRestrictNumber());
		mv.addObject("merchantId", conn_product.get(id).getProductMerchantID());
		mv.addObject("actId", activityPro.getId());
		mv.addObject("id", id);
		mv.addObject("userHeadimg", userHeadimg);
		mv.addObject("userId", userId);
		return mv;
	}

	@RequestMapping(value = "/product/index/merchant/payment")
	public ModelAndView productIndexS(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/orderpayment");
		return mv;
	}

	@RequestMapping(value = "/product/index/merchant/renewall") ///////////////////////////////////////////
	// TODO
	public ModelAndView productRenewal(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/renewal");
		return mv;
	}

	@RequestMapping(value = "/merproduct/index/{id}")
	public ModelAndView productdetailsIndexN(@PathVariable long id, HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubadmin/productdetails");
		mv.addObject("id", id);
		mv.addObject("userId", request.getSession().getAttribute("userId"));
		return mv;
	}

	@RequestMapping(value = "/product/index/payinshop/{merchantId}")
	public ModelAndView payinshop(@PathVariable long merchantId, HttpServletRequest request) throws Exception {
		MerchantPO merchantPO = conn_merchant.get(merchantId);
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/payinshop");
		mv.addObject("merchantId", merchantId);
		mv.addObject("userId", request.getSession().getAttribute("userId"));
		return mv;
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/stock/check", method = RequestMethod.GET)
	public Object checkStock(HttpServletRequest request, HttpServletResponse response, long proId, int count)
			throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		ProductPO productPO = conn_product.get(proId);
		if (productPO.getProductStock() >= count) {
			ret.put("stock", productPO.getProductStock());
			productPO.setProductStock(productPO.getProductStock() - count);
			conn_product.save(productPO);
		} else {
			ret.put("stock", 0);
		}
		return ret;
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/stockact/check", method = RequestMethod.GET)
	public Object checkActStock(HttpServletRequest request, HttpServletResponse response, long actProId, int count,
			String bDate) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		ActivityRelPO actproductPO = conn_activityRel.get(actProId);
		if (actproductPO.getProductStock() >= count) {
			if (actproductPO.getDayStock() > 0) {
				int daycount = conn_order.countDateByActpro(actProId, DateUtil.parse(bDate, "yyyy-MM-dd HH:mm"));
				if (count <= actproductPO.getDayStock() - daycount) {
					ret.put("stock", actproductPO.getProductStock() > (actproductPO.getDayStock() - daycount)
							? (actproductPO.getDayStock() - daycount) : actproductPO.getProductStock());
					ret.put("stock", actproductPO.getProductStock());
					actproductPO.setProductStock(actproductPO.getProductStock() - count);
				} else {
					ret.put("stock", 0);
				}
			} else {
				ret.put("stock", actproductPO.getProductStock());
				actproductPO.setProductStock(actproductPO.getProductStock() - count);
				conn_activityRel.save(actproductPO);
			}

		} else {
			ret.put("stock", 0);
		}
		return ret;
	}

	@Autowired
	UserOnedayBuyDAO conn_userone;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/stockact/checkone", method = RequestMethod.GET)
	public Object checkOneActStock(HttpServletRequest request, HttpServletResponse response, long actProId, int count)
			throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		ActivityRelPO actproductPO = conn_activityRel.get(actProId);
		HttpSession session = request.getSession();
		List<UserOneDayBuyPO> userOneDayBuyPOs = conn_userone
				.findTodayBuy(Long.parseLong(session.getAttribute("userId").toString()), actProId);
		if (count > 0 && actproductPO.getOnePerDay() != 0 && userOneDayBuyPOs != null
				&& (actproductPO.getOnePerDay() - count) < userOneDayBuyPOs.size()) {
			ret.put("stock", 0);
		} else {
			ret.put("stock", 1);
		}
		return ret;
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/change/ordercount", method = RequestMethod.GET)
	public Object changeOrderCount(HttpServletRequest request, HttpServletResponse response, long orderId, int count)
			throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		OrderInfoPO orderInfoPO = conn_order.get(orderId);
		if (orderInfoPO.getActivityId() == 0) {
			ProductPO productPO = conn_product.get(orderInfoPO.getProductId());
			if (count > 0) {
				if (productPO.getProductStock() < count) {
					ret.put("stock", 0);
				} else {
					ret.put("stock", productPO.getProductStock());
					productPO.setProductStock(productPO.getProductStock() - count);
					conn_product.save(productPO);
				}
			} else {
				ret.put("stock", productPO.getProductStock() - count);
				productPO.setProductStock(productPO.getProductStock() - count);
				conn_product.save(productPO);
			}

		} else {

			ActivityRelPO actproductPO = conn_activityRel.get(orderInfoPO.getActivityId());
			HttpSession session = request.getSession();
			List<UserOneDayBuyPO> userOneDayBuyPOs = conn_userone
					.findTodayBuy(Long.parseLong(session.getAttribute("userId").toString()), actproductPO.getId());
			if (count > 0 && actproductPO.getOnePerDay() != 0 && userOneDayBuyPOs != null
					&& (actproductPO.getOnePerDay() - count) < userOneDayBuyPOs.size()) {
				ret.put("stock", -1);
			} else if (count > 0) {
				if (actproductPO.getProductStock() < count) {
					ret.put("stock", 0);
				} else {
					ret.put("stock", actproductPO.getProductStock());
					actproductPO.setProductStock(actproductPO.getProductStock() - count);
					conn_activityRel.save(actproductPO);
				}
			} else {
				conn_userone.deleteAll(userOneDayBuyPOs);
				ret.put("stock", actproductPO.getProductStock() - count);
				actproductPO.setProductStock(actproductPO.getProductStock() - count);
				conn_activityRel.save(actproductPO);
			}

		}

		return ret;
	}

	/**
	 * 订单：获取订单
	 * 
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/order/get", method = RequestMethod.GET)
	public Map<String, Object> getOrder(Long userId, int type, // 1.未支付;2.已支付;3.已发货;4.待退款;5.已退款;6.已收货;7.已评价
			String uType, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysConfigPO sysConfig = conn_sys.getSysConfig();
		List<OrderInfoVO> orders = new ArrayList<OrderInfoVO>(); // 已付款订单

		if (uType.equals("USER")) {
			switch (type) {
			case 1:// 未支付
				List<OrderInfoPO> orderingOrderpos = conn_order.getOrdersByState(userId, OrderStateType.NOTPAY);
				List<OrderInfoVO> orderingOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderingOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : orderingOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = orderingOrders;
				break;

			case 2:// 已支付
				List<OrderInfoPO> orderedOrderpos = conn_order.getOrdersByState(userId, OrderStateType.PAYSUCCESS);
				List<OrderInfoPO> orderedOrderpo2 = conn_order.getOrdersByState(userId, OrderStateType.PAYFINISH);

				if (orderedOrderpo2.size() > 0) {
					orderedOrderpos.addAll(orderedOrderpo2);
				}

				List<OrderInfoVO> orderedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderedOrderpos,
						OrderInfoVO.class);
				// 张羽 添加退款限制 4/28
				for (int i = 0; i < orderedOrderpos.size(); i++) {
					orderedOrders.get(i).setProductIsRefund(orderedOrderpos.get(i).getProductIsRefund());
				}
				orders = orderedOrders;
				break;
			case 3:// 已发货
				List<OrderInfoPO> deliverOrderpos = conn_order.getOrdersByState(userId, OrderStateType.DELIVER);
				List<OrderInfoVO> deliverOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(deliverOrderpos,
						OrderInfoVO.class);

				orders = deliverOrders;
				break;

			case 4:// 待退款
				List<OrderInfoPO> refundingOrderpos = conn_order.getOrdersByState(userId, OrderStateType.REFUNDING);
				List<OrderInfoVO> refundingOrders = OrderInfoVO.getConverter(OrderInfoVO.class)
						.convert(refundingOrderpos, OrderInfoVO.class);

				orders = refundingOrders;
				break;
			case 5:// 已退款
				List<OrderInfoPO> refundedOrderpos = conn_order.getOrdersByState(userId, OrderStateType.REFUNDED);
				List<OrderInfoVO> refundedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(refundedOrderpos,
						OrderInfoVO.class);

				orders = refundedOrders;
				break;
			case 6:// 已收货
				List<OrderInfoPO> receiptOrderpos = conn_order.getOrdersByState(userId, OrderStateType.RECEIPT);
				List<OrderInfoVO> receiptOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(receiptOrderpos,
						OrderInfoVO.class);

				orders = receiptOrders;
				break;
			case 7:// 已评价
				List<OrderInfoPO> commentedOrderpos = conn_order.getOrdersByState(userId, OrderStateType.COMMENTED);
				List<OrderInfoVO> commentedOrders = OrderInfoVO.getConverter(OrderInfoVO.class)
						.convert(commentedOrderpos, OrderInfoVO.class);

				orders = commentedOrders;
				break;
			case 8:// 已评价
				List<OrderInfoPO> testOrderpos = conn_order.getOrdersByState(userId, OrderStateType.TESTED);
				List<OrderInfoVO> testOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(testOrderpos,
						OrderInfoVO.class);
				List<OrderInfoPO> commentedOrderpos1 = conn_order.getOrdersByState(userId, OrderStateType.COMMENTED);
				List<OrderInfoVO> commentedOrders1 = OrderInfoVO.getConverter(OrderInfoVO.class)
						.convert(commentedOrderpos1, OrderInfoVO.class);

				orders = testOrders;
				orders.addAll(commentedOrders1);
				break;
			case 9:// 拒绝退款 4/24新增
				List<OrderInfoPO> rOrderpos = conn_order.getOrdersByState(userId, OrderStateType.PAYSUCCESS);
				List<OrderInfoPO> rOrderpos2 = new ArrayList<OrderInfoPO>();
				for (OrderInfoPO orderInfoPO : rOrderpos) {
					if (orderInfoPO.getJustification() != "" && orderInfoPO.getJustification() != null) {
						rOrderpos2.add(orderInfoPO);
					}
				}
				List<OrderInfoVO> rOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(rOrderpos2,
						OrderInfoVO.class);
				for (int i = 0; i < rOrderpos2.size(); i++) {
					rOrders.get(i).setJustification(rOrderpos2.get(i).getJustification());
				}
				orders = rOrders;
				break;

			default:
				break;
			}
		} else if (uType.equals("MERCHANT")) {
			UserInfoPO user = conn_user.get(userId);
			MerchantPO merchant = user.getMerchant();
			if (merchant == null) {
				return FORBIDDEN("该用户不是商户！");
			}
			long merchantId = merchant.getId();
			switch (type) {
			case 1:// 未支付
				List<OrderInfoPO> orderingOrderpos = conn_order.getOrdersByMerState(merchantId, OrderStateType.NOTPAY);
				List<OrderInfoVO> orderingOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderingOrderpos,
						OrderInfoVO.class);

				orders = orderingOrders;
				break;

			case 2:// 已支付
				List<OrderInfoPO> orderedOrderpos = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.PAYSUCCESS);
				List<OrderInfoPO> orderedOrderpo2 = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.PAYFINISH);
				orderedOrderpos.addAll(orderedOrderpo2);
				List<OrderInfoVO> orderedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderedOrderpos,
						OrderInfoVO.class);
				// 张羽 添加退款限制 4/28
				for (int i = 0; i < orderedOrderpos.size(); i++) {
					orderedOrders.get(i).setProductIsRefund(orderedOrderpos.get(i).getProductIsRefund());
				}

				orders = orderedOrders;
				break;
			case 3:// 已发货
				List<OrderInfoPO> deliverOrderpos = conn_order.getOrdersByMerState(merchantId, OrderStateType.DELIVER);
				List<OrderInfoVO> deliverOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(deliverOrderpos,
						OrderInfoVO.class);

				orders = deliverOrders;
				break;

			case 4:// 待退款
				List<OrderInfoPO> refundingOrderpos = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.REFUNDING);
				List<OrderInfoVO> refundingOrders = OrderInfoVO.getConverter(OrderInfoVO.class)
						.convert(refundingOrderpos, OrderInfoVO.class);

				orders = refundingOrders;
				break;
			case 5:// 已退款
				List<OrderInfoPO> refundedOrderpos = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.REFUNDED);
				List<OrderInfoVO> refundedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(refundedOrderpos,
						OrderInfoVO.class);

				orders = refundedOrders;
				break;
			case 6:// 已收货
				List<OrderInfoPO> receiptOrderpos = conn_order.getOrdersByMerState(merchantId, OrderStateType.RECEIPT);
				List<OrderInfoVO> receiptOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(receiptOrderpos,
						OrderInfoVO.class);

				orders = receiptOrders;
				break;
			case 7:// 已评价
				List<OrderInfoPO> commentedOrderpos = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.COMMENTED);
				List<OrderInfoVO> commentedOrders = OrderInfoVO.getConverter(OrderInfoVO.class)
						.convert(commentedOrderpos, OrderInfoVO.class);

				orders = commentedOrders;
				break;
			case 9:// 拒绝退款 4/24新增
				List<OrderInfoPO> rOrderpos = conn_order.getOrdersByState(userId, OrderStateType.PAYSUCCESS);
				List<OrderInfoPO> rOrderpos2 = new ArrayList<OrderInfoPO>();
				for (OrderInfoPO orderInfoPO : rOrderpos) {
					if (orderInfoPO.getJustification() != "" && orderInfoPO.getJustification() != null) {

						rOrderpos2.add(orderInfoPO);
					}
				}
				List<OrderInfoVO> rOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(rOrderpos2,
						OrderInfoVO.class);
				for (int i = 0; i < rOrderpos2.size(); i++) {
					rOrders.get(i).setJustification(rOrderpos2.get(i).getJustification());
				}
				orders = rOrders;
				break;

			default:
				break;
			}
		} else {
			return FORBIDDEN("错误的用户类型！");
		}

		// 合并订单
		Map<Long, BundleOrderVo> bundleMap = new HashMap<Long, BundleOrderVo>();
		List<BundleOrderVo> vos = new ArrayList<BundleOrderVo>();
		for (OrderInfoVO orderInfoVO : orders) {
			if (orderInfoVO.getProductName() == null) {
				orderInfoVO.setProductName("(到店支付)" + orderInfoVO.getShopName());
				orderInfoVO.setProductPrice(orderInfoVO.getPayMoney());
				orderInfoVO.setProductNum(1);
			}
			orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());

			BundleOrder bOrder = conn_bundleOrder.getBundleByOrderId(orderInfoVO.getId());
			if (bOrder != null) {
				if (bundleMap.containsKey(bOrder.getId())) {
					bundleMap.get(bOrder.getId()).getOrderList().add(orderInfoVO);
				} else {
					BundleOrderVo vo = new BundleOrderVo();
					vo.setOrderStatus(orderInfoVO.getOrderState());
					vo.setIsBundle(1);
					vo.setPayDate(orderInfoVO.getCreateDate());
					vo.setOrderId(bOrder.getId());
					vo.setUserName(orderInfoVO.getUserName());
					vo.getOrderList().add(orderInfoVO);
					bundleMap.put(bOrder.getId(), vo);
					vos.add(vo);
				}
			} else {
				BundleOrderVo vo = new BundleOrderVo();
				vo.setOrderStatus(orderInfoVO.getOrderState());
				vo.setIsBundle(0);
				vo.setPayDate(orderInfoVO.getCreateDate());
				vo.setOrderId(orderInfoVO.getId());
				vo.setUserName(orderInfoVO.getUserName());
				vo.getOrderList().add(orderInfoVO);
				vos.add(vo);
			}

		}
		return success(vos);

	}

	@Autowired
	SurpportBuyDao conn_surpportbuy;

	@RequestMapping(value = "product/index/surpport/{id}/{userId}")
	public ModelAndView surpportIndex(@PathVariable long id, @PathVariable long userId, HttpServletRequest request)
			throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/surpport");
		if (userId == 0) {
			mv.addObject("currentUser", request.getSession().getAttribute("userId"));
			mv.addObject("userName", conn_user
					.get(Long.parseLong(request.getSession().getAttribute("userId").toString())).getUserNickname());
		} else {
			mv.addObject("currentUser", userId);
			mv.addObject("userName", conn_user.get(userId).getUserNickname());
		}

		mv.addObject("loginUser", request.getSession().getAttribute("userId"));
		ActivityRelPO activityRelPO = conn_activityRel.get(id);
		ProductPO productPO = conn_product.get(activityRelPO.getProductId());
		ProductVO vo = new ProductVO();
		vo.set(productPO);
		ActivityPO activityPO = conn_activity.get(activityRelPO.getActivityId());
		int surpportCount = 0;
		List<SurpportBuyPo> curSurpports = conn_surpportbuy.getSurpports(userId, id);
		if (userId != 0) {
			surpportCount = conn_surpportbuy.getSurpportCount(userId, id);
		} else {
			surpportCount = conn_surpportbuy
					.getSurpportCount(Long.parseLong(request.getSession().getAttribute("userId").toString()), id);
			curSurpports = conn_surpportbuy
					.getSurpports(Long.parseLong(request.getSession().getAttribute("userId").toString()), id);
		}
		String users = "";
		for (SurpportBuyPo surpportBuyPo : curSurpports) {
			if (!users.isEmpty()) {
				users += ",";
			}
			users += conn_user.get(surpportBuyPo.getFriendId()).getUserHeadimg();
		}
		mv.addObject("product", productPO);
		mv.addObject("activityRel", activityRelPO);
		mv.addObject("activity", activityPO);
		DecimalFormat df = new DecimalFormat("#.00");
		Double prices = (double) (activityPO.getFixedPrice() / 100);
		mv.addObject("price", df.format(prices));
		mv.addObject("path", conn_sys.getSysConfig().getWebUrl());
		mv.addObject("surpportCount", surpportCount);
		mv.addObject("users", users);
		return mv;
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/surpport", method = RequestMethod.GET)
	public Object surpport(HttpServletRequest request, HttpServletResponse response, long curUserId, long loginId,
			long proId) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		if (conn_surpportbuy.checkSupport(curUserId, proId, loginId) > 0) {
			ret.put("ifSurpport", false);
			return ret;
		}
		SurpportBuyPo surpportBuyPo = new SurpportBuyPo();
		surpportBuyPo.setFriendId(loginId);
		surpportBuyPo.setProductId(proId);
		surpportBuyPo.setUserId(curUserId);
		conn_surpportbuy.save(surpportBuyPo);
		ret.put("ifSurpport", true);
		return ret;
	}

	@RequestMapping(value = "/gateway")
	public ModelAndView gatewayIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/gateway");
		return mv;
	}

	@RequestMapping(value = "/progateway")
	public ModelAndView progatewayIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/progateway");
		return mv;
	}

	@RequestMapping(value = "/camera")
	public ModelAndView camera(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/camera");
		return mv;
	}

	@Autowired
	private OrderPeopleDao conn_orderpeople;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getViewPeoples", method = RequestMethod.GET)
	public Object getViewPeoples(HttpServletRequest request, HttpServletResponse response, long orderId)
			throws Exception {

		return conn_orderpeople.findByField("orderId", orderId);
	}

	@RequestMapping(value = "/ipost")
	public ModelAndView ipost(HttpServletRequest request, String comCode) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/ipost");
		return mv;
	}

	// 转到jsp文件
	@RequestMapping(value = "/postdetails")
	public ModelAndView postdetails(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/postdetails");
		return mv;
	}

	@Autowired
	private LiveTipGiftDAO conn_liveTipGiftDao;
	@Autowired
	private LiveGiftDAO conn_liveGiftDao;

	// 打赏礼物支付 5/11
	@ResponseBody
	@RequestMapping(value = "/gift/pay/{orderId}/{userId}")
	public Map<String, Object> giftPay(@PathVariable Long orderId, @PathVariable Long userId,
			HttpServletRequest request) throws Exception {
		String orderNo = "gift-" + orderId;
		System.out.println("---------------------------------" + orderNo + "---------支付");
		LiveTipGiftPO order = conn_liveTipGiftDao.get(orderId);
		int price = order.getPrice();
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/giftPayreport", WxConfig.appId,
				WxConfig.appsrcret);
		// 统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息

		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", orderNo, price,
				"192.165.56.64", user.getUserOpenID());
		// 生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);

		// 将订单号放入map，用以支付后处理
		map.put("orderId", orderId);
		return success(map);
	}

	// 打赏礼物支付 5/11
	@ResponseBody
	@RequestMapping(value = "/gift/addOrder/{liveId}/{giftId}/{num}/{userId}")
	public Object addOrder(@PathVariable Long liveId, @PathVariable Long giftId, @PathVariable Integer num,
			@PathVariable Long userId, HttpServletRequest request) throws Exception {
		LiveGiftPO gift = conn_liveGiftDao.get(giftId);
		LiveTipGiftPO order = new LiveTipGiftPO();
		int price = gift.getPrice() * num;
		order.setGiftId(giftId);
		order.setGiftname(gift.getName());
		order.setGiftnumber(num);
		order.setLiveid(liveId);
		order.setPrice(price);
		order.setUsername(conn_user.get(userId).getUserNickname());
		conn_liveTipGiftDao.save(order);
		return order;
	}

	// 我的钱包 张羽 5/19
	@RequestMapping(value = "/wallet")
	public ModelAndView myWallet(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/wallet");
		HttpSession session = request.getSession();
		mv.addObject("userId", session.getAttribute("userId"));
		return mv;
	}

	// 我的钱包支付 提现记录 方法 张羽
	@ResponseBody
	@RequestMapping(value = "/wallet/addOrder")
	public Object addWalletOrder(HttpServletRequest request) throws Exception {
		long money = Long.parseLong(request.getParameter("money"));
		long id = Long.parseLong(request.getParameter("userId"));
		int type = Integer.parseInt(request.getParameter("type"));
		InvestWalletPO order = new InvestWalletPO();

		if (type == 1) {
			money = money * 100;
			System.out.println(money + "----------------------充值");
			UserInfoPO user = conn_user.get(id);
			order.setUsername(user.getUserNickname());
			order.setUserid(id);
			order.setMoney(money);
			conn_investwallet.save(order);
			return success(order);
		} else {
			UserInfoPO user = conn_user.get(id);
			if (user.getWallet() >= money * 100) {
				money = -(money * 100);
				System.out.println(money + "----------------------提现");
				order.setUsername(user.getUserNickname());
				order.setUserid(id);
				order.setMoney(money);
				conn_investwallet.save(order);
				return success(order);
			} else {
				return success(2);
			}
		}

	}

	// 充值失败删除记录 张羽
	@ResponseBody
	@RequestMapping(value = "/wallet/deleteOrder")
	public Object deleteWalletOrder(HttpServletRequest request) throws Exception {
		long orderId = Long.parseLong(request.getParameter("orderId"));
		conn_investwallet.delete(orderId);
		return success();
	}

	// 钱包充值方法 张羽
	@ResponseBody
	@RequestMapping(value = "/wallet/pay/{orderId}/{userId}")
	public Map<String, Object> walletPay(@PathVariable Long orderId, @PathVariable Long userId,
			HttpServletRequest request) throws Exception {
		String orderNo = "wallet-" + orderId;
		InvestWalletPO order = conn_investwallet.get(orderId);
		Long money = order.getMoney();
		int price = money.intValue();
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/walletPayreport", WxConfig.appId,
				WxConfig.appsrcret);
		// 统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息

		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", orderNo, price,
				"192.165.56.64", user.getUserOpenID());
		// 生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);

		// 将订单号放入map，用以支付后处理
		map.put("orderId", orderId);

		return success(map);
	}

	// 我的钱包充值成功修改用户余额 张羽 5/19
	@ResponseBody
	@RequestMapping(value = "/wallet/updata")
	public Object updataUserMoney(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("userId"));
		long orderId = Long.parseLong(request.getParameter("orderId"));
		InvestWalletPO order = conn_investwallet.get(orderId);
		long money = order.getMoney();
		UserInfoPO user = conn_user.get(id);
		long userMoney = user.getWallet();
		user.setWallet(userMoney + money);
		conn_user.saveOrUpdate(user);
		// 调用方法推送充值消息给 李姐 用户
		sendWalletPayMessage(order);
		return success(1);
	}

	// 余额购买成功修改用户余额并推送消息 张羽 5/21
	@ResponseBody
	@RequestMapping(value = "/wallet/walletbuy")
	public Object walletbuy(HttpServletRequest request) throws Exception {
		long orderId = Long.parseLong(request.getParameter("orderId"));
		long id = Long.parseLong(request.getParameter("userId"));
		UserInfoPO user = conn_user.get(id);
		OrderInfoPO order = conn_order.get(orderId);
		long productPrice = order.getPayMoney();
		long userMoney = user.getWallet();
		if (userMoney >= productPrice) {
			user.setWallet(userMoney - productPrice);
			order.setOrderState(OrderStateType.PAYSUCCESS);
			conn_order.saveOrUpdate(order);
			conn_user.saveOrUpdate(user);
			// 推送购买商品成功信息给用户 商家 李姐
			sendPayMessage(order);
		} else {
			// 余额不足 不允许购买
			return success(2);
		}
		return success(1);
	}

	@Autowired
	MerchantUserDao conn_merchantUser;

	/**
	 * 余额购买商品成功消息推送
	 * 
	 * @param orderInfoPO
	 */
	private void sendPayMessage(OrderInfoPO orderInfoPO) {

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
			timeObj.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
			timeObj.put("color", "");
			dataObject.put("keyword4", timeObj);
			JSONObject remarkObj = new JSONObject();
			remarkObj.put("value", "请做好接待工作");
			remarkObj.put("color", "");
			dataObject.put("remark", remarkObj);
			obj.put("data", dataObject);
			SendMsgUtil.sendTemplate(obj.toJSONString());
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
		timeObj.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName() : productPO.getProductName());
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
		remarkObj.put("value", "请做好接待工作(用户电话:" + pNum + ")");
		remarkObj.put("color", "");
		dataObject.put("remark", remarkObj);
		obj.put("data", dataObject);
		SendMsgUtil.sendTemplate(obj.toJSONString());

		// opVUYv9LtqKAbiaXInBqI01hlpYg

	}

	/**
	 * 用户钱包余额充值成功消息推送
	 * 
	 * @param InvestWalletPO
	 */
	private void sendWalletPayMessage(InvestWalletPO orderInfoPO) {

		// 用户推送消息
		Double amount = Double.parseDouble(orderInfoPO.getMoney() + "") / 100;
		DecimalFormat df = new DecimalFormat("0.00");
		UserInfoPO buyUser = conn_user.get(orderInfoPO.getUserid());
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
			firstObj.put("value", "恭喜您充值成功");
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
			timeObj.put("value", "我的钱包充值成功，金额为：" + df.format(amount));
			timeObj.put("color", "");
			dataObject.put("keyword4", timeObj);
			JSONObject remarkObj = new JSONObject();
			remarkObj.put("value", "感谢使用过来玩服务");
			remarkObj.put("color", "");
			dataObject.put("remark", remarkObj);
			obj.put("data", dataObject);
			SendMsgUtil.sendTemplate(obj.toJSONString());
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
		firstObj.put("value", "用户充值钱包通知");
		firstObj.put("color", "");
		dataObject.put("first", firstObj);

		JSONObject nameObj = new JSONObject();
		nameObj.put("value", conn_user.get(orderInfoPO.getUserid()).getUserNickname());
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
		timeObj.put("value", "我的钱包充值成功，金额为：" + df.format(amount));
		timeObj.put("color", "");

		String pNum = "";
		if (pNum == null || pNum.isEmpty()) {
			pNum = buyUser.getUserPhone();
		}
		dataObject.put("keyword4", timeObj);
		JSONObject remarkObj = new JSONObject();
		remarkObj.put("value", "用户电话:" + pNum);
		remarkObj.put("color", "");
		dataObject.put("remark", remarkObj);
		obj.put("data", dataObject);
		SendMsgUtil.sendTemplate(obj.toJSONString());

		// opVUYv9LtqKAbiaXInBqI01hlpYg

	}

	// 修改快递单号的接口

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/changeOrdernumbers", method = RequestMethod.GET)
	public Object changeOrdernumbers(HttpServletRequest request, String orderId, String trackingnumber)
			throws Exception {
		String[] orderids = null;
		if (orderId.indexOf("bundle") != -1) {
			String[] bundleStrs = orderId.split("-");
			BundleOrder bundleOrder = conn_bundleOrder.get(Long.parseLong(bundleStrs[1]));
			orderids = bundleOrder.getOrderStr().split("A");
		} else {
			orderids = orderId.split("A");
		}
		for (String orderIdStr : orderids) {
			OrderInfoPO orderInfoPO = conn_order.get(Long.parseLong(orderIdStr));
			orderInfoPO.setTrackingnumber(trackingnumber);
			conn_order.save(orderInfoPO);
		}

		return success();
	}

	// 跳转快递物流详情页面的方法
	@RequestMapping(value = "/logistics/particulars")
	public ModelAndView logistics(HttpServletRequest request, long orderId) throws Exception {
		ModelAndView mv = null;
		System.out.println("--------------------------------------------" + orderId);
		mv = new ModelAndView("mobile/pubnum/logisticspage");
		mv.addObject("orderId", orderId);
		return mv;
	}

	/**
	 * 轮询消息的方法
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getolchat")
	public List<OlChatMessagePO> getOlChat(HttpServletRequest request) {
		// 商户id
		long merchantId = Long.parseLong(request.getParameter("merchantId"));
		List<OlChatMessagePO> msgs = conn_olchatmessage.findByFlag(merchantId);
		return msgs;
	}

	/**
	 * 页面发送消息的方法 客服在线咨询
	 * 
	 * @param request
	 * @return
	 */
	@JsonBody
	@RequestMapping("/pullolchat")
	public Object pullolchat(HttpServletRequest request) {
		// fromuserId
		long userId = Long.parseLong(request.getParameter("userId"));
		// 商户的id
		long merchantId = Long.parseLong(request.getParameter("merchantId"));
		// 发送的数据
		String msg = request.getParameter("message");
		// 发给的userID
		long touser;
		// 客服人员
		long chatUserId = conn_merchant.get(merchantId).getChatUserId();
		MerchantPO merchant = conn_merchant.get(merchantId);
		// 如果页面带回来有touser 就对这个id发送 没有就是给商户发送
		if (request.getParameter("touser") != "" & request.getParameter("touser") != null) {
			touser = Long.parseLong(request.getParameter("touser"));
		} else {
			if (conn_merchantuser.getUserByMerchantId(merchantId) != null) {
				// 商家的userId
				touser = conn_merchantuser.getUserByMerchantId(merchantId).getUserId();
			} else if (chatUserId != 0) {
				touser = chatUserId;
			} else {
				touser = 0;
			}
		}
		UserInfoPO userpo = conn_user.get(userId);
		OlChatMessagePO ol = new OlChatMessagePO();
		ol.setFlag(false);
		ol.setFromuser(userpo.getUserNickname());
		ol.setFromuserId(userId);
		ol.setMerchantId(merchantId);
		ol.setMessage(msg);
		ol.setTouserId(touser);
		ol.setUserheadimg(userpo.getUserHeadimg());
		conn_olchatmessage.save(ol);
		// 获取到客服再商户页面内最后的活动时间
		Date lasttime = merchant.getLastexercisetime();
		long cha = 0;
		if (lasttime != null) {
			// 计算出用户发送信息的时间距离客服在商户页面内最后的活动时间差
			cha = ((new Date().getTime()) - (lasttime.getTime())) / (1000 * 60);
			// 相差1分钟时 推送公众号提醒客服有消息
			if (cha > 1) {
				sendStoreRemind(touser, userId, merchantId);
			}
		} else {
			// 这里是客服没有在商户出现过 防止没有客服的商家提醒时发生错误
			sendStoreRemind(touser, userId, merchantId);
		}

		return request;
	}

	/**
	 * 通知商家客服有新消息
	 */
	public void sendStoreRemind(long touser, long userId, long merchantId) {
		if (touser == 0)
			return;
		UserInfoPO user = conn_user.get(touser);
		UserInfoPO user1 = conn_user.get(userId);
		JSONObject obj1 = new JSONObject();
		obj1.put("touser", user.getUserOpenID());
		obj1.put("template_id", "v9eHHHq4Fu-Q45Pa0oBfibxd032Yq_9rAkqDZZ-V2G4");
		obj1.put("url", "http://www.guolaiwan.net/guolaiwan/pubnum/admin/index?null=");
		JSONObject microProObj1 = new JSONObject();
		microProObj1.put("appid", "");
		microProObj1.put("pagepath", "");
		obj1.put("miniprogram", microProObj1);
		JSONObject dataObject1 = new JSONObject();
		JSONObject firstObj1 = new JSONObject();
		firstObj1.put("value", "在线咨询通知");
		firstObj1.put("color", "");
		dataObject1.put("first", firstObj1);

		JSONObject nameObj = new JSONObject();
		nameObj.put("value", user1.getUserNickname());
		nameObj.put("color", "");
		dataObject1.put("keyword1", nameObj);

		JSONObject accountTypeObj = new JSONObject();
		accountTypeObj.put("value", DateUtil.getNowDate());
		accountTypeObj.put("color", "");
		dataObject1.put("keyword2", accountTypeObj);

		JSONObject accountObj = new JSONObject();
		accountObj.put("value", "上帝光临并在线咨询!请尽快查看~");
		accountObj.put("color", "");
		dataObject1.put("keyword3", accountObj);

		JSONObject remarkObj1 = new JSONObject();
		remarkObj1.put("value", "查看方法：点击客户服务→商户中心→登录商户（如已登录请忽略）→在线咨询");
		remarkObj1.put("color", "");
		dataObject1.put("remark", remarkObj1);
		obj1.put("data", dataObject1);
		SendMsgUtil.sendTemplate(obj1.toJSONString());

		conn_merchant.get(merchantId).setLastexercisetime(new Date());
	}

	/**
	 * 在页面展示之后 将是否发送过修改为是
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateflag")
	public Object updateflag(HttpServletRequest request) {
		// 需要修改的消息id
		long id = Long.parseLong(request.getParameter("id"));
		OlChatMessagePO ol = conn_olchatmessage.get(id);
		// 发送过就修改成已发送 true:已发送 flase：未发送
		ol.setFlag(true);
		conn_olchatmessage.saveOrUpdate(ol);
		return success();
	}

	// 调用阿里云接口发送base64的编码返回信息
	@ResponseBody
	@RequestMapping(value = "/IdentityCard", method = RequestMethod.POST)
	public Map<String, String> IdentityCard(String localData) throws Exception {
		String host = "https://yixi.market.alicloudapi.com";
		String path = "/ocr/idcard";
		String method = "POST";
		String appcode = "7fe03d3c707e4f1699cf999a30b6bf90";
		String image = localData;
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		// 根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		Map<String, String> querys = new HashMap<String, String>();
		Map<String, String> bodys = new HashMap<String, String>();
		Map<String, String> map = new HashMap<>();
		bodys.put("image", image);
		bodys.put("side", "front");
		try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			String ss = EntityUtils.toString(response.getEntity());
			String aString = "[" + ss + "]";
			System.out.println(aString);
			List<Map> list = JSONObject.parseArray(aString, Map.class);
			String name = list.get(0).get("data").toString();

			name = name.substring(0, name.length() - 1);
			name = name.substring(1);
			String[] str1 = name.split(",");
			// 创建Map对象

			// 循环加入map集合
			for (int i = 0; i < str1.length; i++) {
				// 根据":"截取字符串数组
				String[] str2 = str1[i].split(":");
				String name1 = str2[0].substring(1);
				name1 = name1.substring(0, name1.length() - 1);
				String name2 = str2[1].substring(1);
				name2 = name2.substring(0, name2.length() - 1);
				map.put(name1, name2);
			}
			Map<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("name", map.get("姓名"));
			hashMap.put("sfz", map.get("公民身份号码"));
			hashMap.put("msg", "0");
			System.out.println(map);
			return hashMap;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "1");
			return map;
		}

	}

	// 保存身份证信息
	@ResponseBody
	@RequestMapping(value = "/addmessage", method = RequestMethod.POST)
	public Map<String, String> addmessage(String localData, String idnums, String name) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
			MessagePO messagePO = new MessagePO();
			messagePO.setName(name);
			messagePO.setBase(localData);
			messagePO.setNumber(idnums);
			messagedao.save(messagePO);
			map.put("msg", "0");
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg", "1");
		}
		return map;
	}

	// 新的保存快递单号的方法-----董
	@ResponseBody
	@RequestMapping(value = "/UpdateKd", method = RequestMethod.GET)
	public Object UpdateKd(String orderId, String kdname, String id) throws Exception {
		try {
			String a[] = orderId.split("-");
			OrderInfoPO orderInfoPO = conn_order.get(Long.parseLong(a[1]));
			orderInfoPO.setTrackingnumber(kdname);
			orderInfoPO.setLogisticsId(Long.parseLong(id));
			orderInfoPO.setOrderState(OrderStateType.DELIVER);
			conn_order.saveOrUpdate(orderInfoPO);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}

	}
}
