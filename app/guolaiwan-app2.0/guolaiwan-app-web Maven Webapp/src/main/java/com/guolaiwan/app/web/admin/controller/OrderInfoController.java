package com.guolaiwan.app.web.admin.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.Border;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.http.HttpRequest;
import com.guolaiwan.app.web.admin.Utils.ExportExcelSeedBack;
import com.guolaiwan.app.web.admin.vo.BundleOrderVo;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.admin.vo.TableOrderVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
import com.guolaiwan.bussiness.admin.dao.LogisticsDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductComboDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.TableDAO;
import com.guolaiwan.bussiness.admin.dao.TableStatusDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dto.CountGroupDTO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;
import com.guolaiwan.bussiness.admin.po.LuckDrawRecord;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.RoomStatusPO;
import com.guolaiwan.bussiness.admin.po.TableStatusPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.UserOneDayBuyPO;
import com.guolaiwan.bussiness.distribute.dao.MealListDao;
import com.guolaiwan.bussiness.distribute.po.MealListPo;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.orm.hql.Condition;

@Controller
@RequestMapping("/admin/orderinfo")
public class OrderInfoController extends BaseController {
	@Autowired
	private OrderInfoDAO conn_OrderInfo;
	@Autowired
	private UserInfoDAO conn_userInfo;
	@Autowired
	private ProductDAO conn_product;
	@Autowired
	private MerchantDAO conn_merchant;
	@Autowired
	private AddressDAO conn_address;
	@Autowired
	private TableStatusDAO conn_tablestatus;
	@Autowired
	private TableDAO conn_table;
	@Autowired
	private MealListDao conn_meallist;
	// 显示列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getOrderInfos(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		if (getLoginInfo() != null) {
			strMap.put("allcount", conn_OrderInfo.GetCountByPage());
		} else {
			Long merchantId = getMerchantInfo().getMerchantId();
			int getCountByMerchant = conn_OrderInfo.GetCountByMerchant(getMerchantInfo().getMerchantId());
			strMap.put("allcount", conn_OrderInfo.GetCountByMerchant(getMerchantInfo().getMerchantId()));
		}

		ModelAndView mv = new ModelAndView("admin/orderinfo/list", strMap);
		return mv;
	}

	@Autowired LogisticsDao conn_logistics;
	// 切换tab
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(HttpServletRequest request, int page, int limit, int type) throws Exception {
		// 检索条件
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//添加获取comId   4/26
		String comId = Long.toString(getLoginInfo().getComId());
		dataMap.put("comId", comId);
		
		String mName = request.getParameter("mName");
		if (mName != null && mName.length() != 0) {
			dataMap.put("shopName", mName);
		}

		String pName = request.getParameter("pName");
		if (pName != null && pName.length() != 0) {
			dataMap.put("productName", pName);
		}

		String pmenus = request.getParameter("pmenus");
		if (pmenus != null && pmenus.length() != 0) {

			dataMap.put("source", pmenus);

		}

		if (getLoginInfo() == null) {
			dataMap.put("shopId", getMerchantInfo().getMerchantId());
		}

		List<OrderInfoPO> orderinfopo = new ArrayList<OrderInfoPO>();
		int count = 0;
		switch (type) {
		case 0:// 未付款
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.NOTPAY, dataMap, page, limit);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.NOTPAY, dataMap);
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}
			break;
		case 1:// 已付款
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.PAYSUCCESS, dataMap, page, limit);
			List<OrderInfoPO> orderedOrderpo2 = conn_OrderInfo.findOrdersByState(OrderStateType.PAYFINISH, dataMap,
					page, limit);
			orderinfopo.addAll(orderedOrderpo2);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.PAYSUCCESS, dataMap);
			int count1 = conn_OrderInfo.countOrdersByState(OrderStateType.PAYFINISH, dataMap);
			count = count + count1;
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}
			break;
		case 2:// 已发货
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.DELIVER, dataMap, page, limit);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.DELIVER, dataMap);
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}
			break;
		case 3:// 已收货
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.RECEIPT, dataMap, page, limit);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.RECEIPT, dataMap);
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}
			break;
		case 4:// 已确认
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.CONFIRMED, dataMap, page, limit);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.CONFIRMED, dataMap);
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}
			break;
		case 5:// 已验单
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.TESTED, dataMap, page, limit);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.TESTED, dataMap);
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}
			break;
		case 6:// 已评价
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.COMMENTED, dataMap, page, limit);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.COMMENTED, dataMap);
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}
			break;
		case 7:// 申请退款
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.REFUNDING, dataMap, page, limit);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.REFUNDING, dataMap);
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}

			break;
		case 8:// 退款成功
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.REFUNDED, dataMap, page, limit);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.REFUNDED, dataMap);
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}

			break;
		case 9:// 退款失败
			orderinfopo = conn_OrderInfo.findOrdersByState(OrderStateType.REFUNDFAIL, dataMap, page, limit);
			count = conn_OrderInfo.countOrdersByState(OrderStateType.REFUNDFAIL, dataMap);
			for (OrderInfoPO oipo : orderinfopo) {
				AddressPO address = conn_address.get(oipo.getMailAddress());
				if (address != null) {
					oipo.setUserTel(address.getConsigneePhone());
					oipo.setUserName(address.getConsigneeName());
				}
			}
			break;
		default:
			break;
		}

		List<OrderInfoVO> orderinfovo = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderinfopo,
				OrderInfoVO.class);
		for (OrderInfoVO _orderInfo : orderinfovo) {
			long mailAddress = _orderInfo.getMailAddress();
			_orderInfo.setMailAddressPO(conn_address.get(mailAddress));
			ProductComboPO comboPO = conn_combo.get(_orderInfo.getComboId());
			_orderInfo.setComboName(comboPO==null?"标准":comboPO.getCombo());
			LogisticsPo logisticsPo=conn_logistics.get(_orderInfo.getLogisticsId());
			_orderInfo.setLogisticsName(logisticsPo==null?"过来玩物流":logisticsPo.getName());
		}
		request.getSession().setAttribute("orderinfovo", orderinfovo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", orderinfovo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	// 搜索(订单号和id)
	@ResponseBody
	@RequestMapping(value = "/getorder.do", method = RequestMethod.POST)
	public Map<String, Object> getorder(HttpServletRequest request, String orderIds, String orderNO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Long orderId = null;
		if (orderIds != null && orderIds.length() != 0) {
			try {
				orderId = Long.parseLong(orderIds);
			} catch (Exception e) {
				orderId = null;
			}
		}
		OrderInfoPO order = conn_OrderInfo.getByIdNO(orderId, orderNO);
		if (order == null || order.getOrderState() == null) {
			map.put("data", null);
			map.put("type", 10);
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", 1);
			return map;
		} else {
			String type = order.getOrderState().getFiled();
			switch (type) {
			case "NOTPAY":
				map.put("type", 0);
				break;
			case "PAYSUCCESS":
				map.put("type", 1);
				break;
			case "PAYFINISH":
				map.put("type", 1);
				break;
			case "DELIVER":
				map.put("type", 2);
				break;
			case "RECEIPT":
				map.put("type", 3);
				break;
			case "CONFIRMED":
				map.put("type", 4);
				break;
			case "TESTED":
				map.put("type", 5);
				break;
			case "COMMENTED":
				map.put("type", 6);
				break;
			case "REFUNDING":
				map.put("type", 7);
				break;
			case "REFUNDED":
				map.put("type", 8);
				break;
			case "REFUNDFAIL":
				map.put("type", 9);
				break;
			default:
				map.put("type", 10);
				break;
			}
			OrderInfoVO _order = new OrderInfoVO().set(order);
			_order.setMailAddressPO(conn_address.get(_order.getMailAddress()));
			List<OrderInfoVO> _orders = new ArrayList<OrderInfoVO>();
			_orders.add(_order);
			request.getSession().setAttribute("orderinfovo", _orders);
			map.put("data", _orders);
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", 1);
			return map;
		}
	}

	// 搜索商家和商品
	@ResponseBody
	@RequestMapping(value = "/countMP.do", method = RequestMethod.POST)
	public Map<String, Object> countMP(String mName, String pName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CountGroupDTO> _countGroups = conn_OrderInfo.countMP(mName, pName);
		for (int i = 0; i < _countGroups.size(); i++) {
			System.out.println(_countGroups.get(i).getName());
		}
		map.put("cgroups", _countGroups);
		return map;
	}

	// 搜索商家和商品
	@ResponseBody
	@RequestMapping(value = "/pCount.do", method = RequestMethod.POST)
	public Map<String, Object> pCount(String pmenus) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(pmenus);
		List<CountGroupDTO> _countcsGroups = conn_OrderInfo.pCount(pmenus);
		map.put("csgroups", _countcsGroups);
		return map;
	}

	// 显示列表
	@RequestMapping(value = "/yandan", method = RequestMethod.GET)
	public ModelAndView toYandan(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("admin/orderinfo/yandan", strMap);
		return mv;
	}

	// 验单
	@ResponseBody
	@RequestMapping(value = "/yandan.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> yandan(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		String orderNo = request.getParameter("orderNo");
		OrderInfoPO order = conn_OrderInfo.getByRoderNo(orderNo);
		if (order == null) {
			strMap.put("msg", "nothave");
			return strMap;
		}

		// 只有支付完成的才可以验单
		if (order.getOrderState().equals(OrderStateType.PAYSUCCESS)
				|| order.getOrderState().equals(OrderStateType.PAYFINISH)) {
			order.setOrderState(OrderStateType.TESTED);
			order.setYdDate(new Date());
			conn_OrderInfo.save(order);
			OrderInfoVO orderVO = new OrderInfoVO().set(order);
			strMap.put("msg", "success");
			strMap.put("order", orderVO);
			return strMap;
		} else {
			OrderInfoVO orderVO = new OrderInfoVO().set(order);
			strMap.put("msg", "stateError");
			strMap.put("order", orderVO);
			strMap.put("orderState", order.getOrderState().getName());
			return strMap;
		}
	}

	// 验单列表
	@ResponseBody
	@RequestMapping(value = "/ydList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> ydlist(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_OrderInfo.countByYd();
		List<OrderInfoPO> orders = conn_OrderInfo.findByYd(page, limit);
		List<OrderInfoVO> orderVos = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orders, OrderInfoVO.class);

		// 用户名称
		for (OrderInfoVO orderInfoVO : orderVos) {
			orderInfoVO.setUserName(conn_userInfo.get(orderInfoVO.getUserId()).getUserNickname());
		}
		strMap.put("data", orderVos);
		strMap.put("code", "0");

		strMap.put("count", count);
		strMap.put("msg", "");
		return strMap;
	}

	// 详情页面
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(HttpServletRequest request) throws Exception {

		Map<String, Object> strMap = new HashMap<String, Object>();
		String orderNO = request.getParameter("orderNO");
		OrderInfoPO order = conn_OrderInfo.getByRoderNo(orderNO);
		MerchantPO merchant = conn_merchant.get(order.getShopId());
		ProductPO product = conn_product.get(order.getProductId());
		UserInfoPO userInfo = conn_userInfo.get(order.getUserId());

		OrderInfoVO orderVO = new OrderInfoVO().set(order);
		MerchantVO merchantVO = new MerchantVO().set(merchant);
		ProductVO productVO = new ProductVO().set(product);
		UserInfoVO userVO = new UserInfoVO().set(userInfo);

		strMap.put("order", orderVO);
		strMap.put("merchant", merchantVO);
		strMap.put("product", productVO);
		strMap.put("user", userVO);
		ModelAndView mv = new ModelAndView("admin/orderinfo/info", strMap);
		return mv;
	}

	// 结算页面
	@RequestMapping(value = "/settleList", method = RequestMethod.GET)
	public ModelAndView settle(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/orderinfo/settle");
		return mv;
	}

	// 结算页面列表
	@ResponseBody
	@RequestMapping(value = "/settleList.do", method = RequestMethod.POST)
	public Map<String, Object> settleDo(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<CountGroupDTO> _countGroups = conn_OrderInfo.findSettle(page, limit);

		for (CountGroupDTO countGroupDTO : _countGroups) {
			DecimalFormat df = new DecimalFormat("0.00");
			String sumMoneyStr = df.format(((double) countGroupDTO.getSumMoney() / 100));
			countGroupDTO.setSumMoneyStr(sumMoneyStr);
		}
		int count = conn_OrderInfo.countSettle();
		strMap.put("data", _countGroups);
		strMap.put("count", count);
		strMap.put("msg", "");
		strMap.put("code", "0");
		return strMap;
	}

	@RequestMapping("/addv")
	public ModelAndView addProduct() {
		ModelAndView mv = new ModelAndView("admin/orderinfo/add");
		return mv;
	}

	@RequestMapping(value = "/refundlist", method = RequestMethod.GET)
	public ModelAndView getrefunds(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrderInfoPO> orderinfopo = new ArrayList<OrderInfoPO>();

		orderinfopo = conn_OrderInfo.findByField("orderState", OrderStateType.REFUNDING);

		map.put("allcount", generateBundleVos(orderinfopo).size());

		ModelAndView mv = new ModelAndView("admin/orderinfo/refundinglist", map);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/agreeRefund.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> GetrefundList(HttpServletRequest request, String orderId) throws Exception {
		// 检索条件

		return success();
	}

	@Autowired
	private BundleOrderDAO conn_bundleOrder;

	@ResponseBody
	@RequestMapping(value = "/refundlist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> GetrefundList(HttpServletRequest request, int page, int limit) throws Exception {
		// 检索条件

		List<OrderInfoPO> orderinfopo = new ArrayList<OrderInfoPO>();

		orderinfopo = conn_OrderInfo.findByField("orderState", OrderStateType.REFUNDING);
		List<BundleOrderVo> bundleOrderVos = generateBundleVos(orderinfopo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", bundleOrderVos);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", bundleOrderVos.size());
		return map;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/changeOrderStatus", method = RequestMethod.GET)
	public Object changeOrderStatus(HttpServletRequest request, String orderId, String status) throws Exception {
		if(orderId.indexOf("bundle") != -1){
			Long bundleOrderId=Long.parseLong(orderId.split("-")[1]);
			BundleOrder bOrder=conn_bundleOrder.get(bundleOrderId);
			String[] orderIds=bOrder.getOrderStr().split("A");
			for (String orderidStr : orderIds) {
				OrderInfoPO orderInfoPO = conn_OrderInfo.get(Long.parseLong(orderidStr));
				orderInfoPO.setOrderState(OrderStateType.REFUNDED);
				conn_OrderInfo.update(orderInfoPO);
			}
			
		}else{
			OrderInfoPO orderInfoPO = conn_OrderInfo.get(Long.parseLong(orderId));
			orderInfoPO.setOrderState(OrderStateType.REFUNDED);
			conn_OrderInfo.update(orderInfoPO);
		}
		
		return success();
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/rejectOrderStatus", method = RequestMethod.GET)
	public Object rejectOrderStatus(HttpServletRequest request, String orderId, String status) throws Exception {
		if(orderId.indexOf("bundle") != -1){
			Long bundleOrderId=Long.parseLong(orderId.split("-")[1]);
			BundleOrder bOrder=conn_bundleOrder.get(bundleOrderId);
			String[] orderIds=bOrder.getOrderStr().split("A");
			for (String orderidStr : orderIds) {
				OrderInfoPO orderInfoPO = conn_OrderInfo.get(Long.parseLong(orderidStr));
				orderInfoPO.setOrderState(OrderStateType.REFUNDED);
				conn_OrderInfo.update(orderInfoPO);
			}
			
		}else{
			OrderInfoPO orderInfoPO = conn_OrderInfo.get(orderId);
			orderInfoPO.setOrderState(OrderStateType.PAYSUCCESS);
			conn_OrderInfo.update(orderInfoPO);
		}
		
		return success();
	}

	private List<BundleOrderVo> generateBundleVos(List<OrderInfoPO> orderList) {
		List<BundleOrderVo> bundleOrderVos = new ArrayList<BundleOrderVo>();
		Map<Long, BundleOrderVo> bundleMap = new HashMap<Long, BundleOrderVo>();
		for (OrderInfoPO orderInfoPO : orderList) {
			BundleOrder bOder = conn_bundleOrder.getBundleByOrderId(orderInfoPO.getId());
			if (bOder == null) {
				BundleOrderVo vo = new BundleOrderVo();
				vo.setCreateDate(DateUtil.format(orderInfoPO.getCreateDate()));
				vo.setId(orderInfoPO.getId() + "");
				vo.setUserTel(orderInfoPO.getUserTel());
				AddressPO address = conn_address.get(orderInfoPO.getMailAddress());
				if (address != null) {
					vo.setUserTel(address.getConsigneePhone());
				}
				vo.setReason(orderInfoPO.getRefundReason());
				vo.setProductName(orderInfoPO.getProductName() + "*" + orderInfoPO.getProductNum());
				vo.setOrderAllMoney(new DecimalFormat("0.00").format((double)orderInfoPO.getPayMoney() / 100));
				bundleOrderVos.add(vo);
			} else {
				if (!bundleMap.containsKey(bOder.getId())) {
					BundleOrderVo vo = new BundleOrderVo();
					vo.setCreateDate(DateUtil.format(orderInfoPO.getCreateDate()));
					vo.setId("bundle-" + bOder.getId());
					AddressPO address = conn_address.get(orderInfoPO.getMailAddress());
					if (address != null) {
						vo.setUserTel(address.getConsigneePhone());
					}
					vo.setReason(orderInfoPO.getRefundReason());
					vo.setProductName(orderInfoPO.getProductName() + "*" + orderInfoPO.getProductNum());
					vo.setOrderAllMoney(new DecimalFormat("0.00").format((double)orderInfoPO.getPayMoney() / 100));
					bundleOrderVos.add(vo);
				} else {
					BundleOrderVo vo = bundleMap.get(bOder.getId());
					vo.setProductName(vo.getProductName() + "<br>" + orderInfoPO.getProductName() + "*"
							+ orderInfoPO.getProductNum());
					vo.setOrderAllMoney(
							(new DecimalFormat("0.00").format(Double.parseDouble(vo.getOrderAllMoney()) + (double)orderInfoPO.getPayMoney() / 100)));
				}
			}
		}
		return bundleOrderVos;
	}

	@RequestMapping(value = "/driveallorder")
	public String derive(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DecimalFormat df = new DecimalFormat("0.00");
		List<OrderInfoVO> orderInfoVOs = (List<OrderInfoVO>) request.getSession().getAttribute("orderinfovo");
		String title = "订单" + DateUtil.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		String[] headers = new String[] { "序号", "订单号", "商户名称", "商品数量", "商品单价", "板块名称", "商品名称", "总金额", "支付金额", "提成比例",
				"提成金额", "支付方式", "下单日期", "订单状态", "验单日期", "用户姓名", "联系方式" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		if (orderInfoVOs != null) {
			for (int i = 0; i < orderInfoVOs.size(); i++) {
				double accured = 0;
				Object[] obj = new Object[headers.length];
				obj[1] = orderInfoVOs.get(i).getOrderNO();
				obj[2] = orderInfoVOs.get(i).getShopName();
				obj[3] = orderInfoVOs.get(i).getProductNum();
				obj[4] = orderInfoVOs.get(i).getProductPrice();
				obj[5] = orderInfoVOs.get(i).getBkName();
				obj[6] = orderInfoVOs.get(i).getProductName();
				obj[7] = orderInfoVOs.get(i).getOrderAllMoney();
				obj[8] = orderInfoVOs.get(i).getPayMoney();
				if (orderInfoVOs.get(i).getProductId() > 0) {
					ProductPO productPO = conn_product.get(orderInfoVOs.get(i).getProductId());
					if (orderInfoVOs.get(i).getActivityId() != 0) {
						obj[9] = "0.00";
					} else if (productPO.getProductCommissionCode() == 1) {
						accured = productPO.getProductCommissionPrice() * orderInfoVOs.get(i).getProductNum()
								/ 100;
						obj[9] = df.format(productPO.getProductCommissionPrice()/100);
					} else {
						accured =productPO.getProductCommissionPrice()
								* Double.parseDouble(orderInfoVOs.get(i).getPayMoney()) / 100;
						obj[9] = df.format(Double.parseDouble(productPO.getProductCommissionPrice()+"") / 100);
					}
					
				} else {
					List<ProductPO> productPOs=conn_product.findByMerchantId(orderInfoVOs.get(i).getShopId());
					if(!productPOs.isEmpty()){
						if (productPOs.get(0).getProductCommissionCode() == 1) {
							accured = productPOs.get(0).getProductCommissionPrice() 
									/ 100;
							obj[9] = df.format(productPOs.get(0).getProductCommissionPrice()/100);
						} else {
							accured =productPOs.get(0).getProductCommissionPrice()
									* Double.parseDouble(orderInfoVOs.get(i).getPayMoney()) / 100;
							obj[9] = df.format(Double.parseDouble(productPOs.get(0).getProductCommissionPrice()+"") / 100);
						}
					}
					
				}

				obj[10] =  df.format(accured);
				obj[11] = orderInfoVOs.get(i).getPayMode();
				obj[12] = orderInfoVOs.get(i).getPayDate();
				obj[13] = orderInfoVOs.get(i).getOrderState();
				obj[14] = orderInfoVOs.get(i).getYdDate();
				obj[15] = orderInfoVOs.get(i).getUserName();
				obj[16] = orderInfoVOs.get(i).getUserTel();
				dataList.add(obj);
			}
		}
		outputList(title, headers, dataList, response);
		return "success";

	}

	// 导出表
	public void outputList(String title, String headers[], List<Object[]> dataList, HttpServletResponse response)
			throws Exception {
		String headStr = "attachment; filename=\"" + new String(title.getBytes("gb2312"), "utf-8") + "\"";
		response.setContentType("octets/stream");
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", headStr);
		ServletOutputStream out = response.getOutputStream();
		// ExportExcel ex = new ExportExcel(title, headers, dataList);//有标题
		ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);// 没有标题
		ex.export(out);
	}
	
	
	// 显示列表
		@RequestMapping(value = "/sell", method = RequestMethod.GET)
		public ModelAndView toSell(HttpServletRequest request) {
			Map<String, Object> strMap = new HashMap<String, Object>();
			ModelAndView mv = new ModelAndView("admin/orderinfo/sell", strMap);
			return mv;
		}
        @Autowired ProductComboDAO conn_combo;
        @Autowired OrderInfoDAO conn_order;
		// 售票
		@ResponseBody
		@RequestMapping(value = "/sell.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
		public Map<String, Object> sell(HttpServletRequest request) throws Exception {
			Map<String, Object> strMap = new HashMap<String, Object>();
            String productNum=request.getParameter("productNum");
            if(productNum!=null){
            	String[] pNums=productNum.split("-");
            	//productNum格式为merchantId-productId-comboId的形式。
            	if(!pNums[0].equals(getMerchantInfo().getMerchantId()+"")){
            		strMap.put("code", "404");
        			strMap.put("msg", "不是该商户商品");
        			return strMap;
            	}
            	ProductPO productPO=conn_product.get(Long.parseLong(pNums[1]));
            	MerchantPO merchant=conn_merchant.get(Long.parseLong(pNums[0]));

            	OrderInfoPO order = new OrderInfoPO();
        		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        		Date date = new Date();
        		long productprice = productPO.getProductPrice();
        		// 支付金额
        		long payMoney = productprice;
        		// 订单总金额
        		long orderAllMoney = payMoney;
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
        		order.setProductNum(1);
        		// 商品单价
        		order.setProductPrice(productprice);
        		// 所属板块DI
        		order.setBkCode(productPO.getProductModularCode());
        		// 所属板块名称
        		order.setBkName(productPO.getProductModularCodeName());

        		// 提成方式（0：佣金1：比例）
        		order.setRoyaltyName(productPO.getProductCommissionCode());
        		// 积分数
        		long integralNum = productPO.getProductntegral();
        		order.setIntegralNum(integralNum);
        		// 订单佣金金额(分)
        		long proportionMoney=0;
        		
        		order.setProportionMoney(proportionMoney);
        		// 支付金额
        		order.setPayMoney(payMoney);
        		// 订单总金额
        		order.setOrderAllMoney(orderAllMoney);
        		// 订单状态
        		order.setOrderState(OrderStateType.PAYSUCCESS);
        		// 订单支付类型 //ALIPAY WEICHAT
        		order.setPayMode(PayType.WEICHAT);
        		// 是否评价
        		order.setCommentIs(0);
        			// 订单来源
        		order.setSource(OrderSource.UNLINE);
        		
        		order.setOrderType(OrderType.MERCHANT);
        		productPO.setProductSaleNum(productPO.getProductSaleNum() + 1);
        		productPO.setProductShowNum(productPO.getProductShowNum() + 1);
        		conn_product.save(productPO);
        		conn_order.save(order);
            }
        	strMap.put("code", "0");
			strMap.put("msg", "success");
			return strMap;
		}

		// 售票列表
		@ResponseBody
		@RequestMapping(value = "/sellList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
		public Map<String, Object> sellList(int page, int limit) throws Exception {
			Map<String, Object> strMap = new HashMap<String, Object>();
			Long merchantId = getMerchantInfo().getMerchantId();
			int count = conn_OrderInfo.countBySell(merchantId);
			List<OrderInfoPO> orders = conn_OrderInfo.findBySell(page, limit,merchantId);
			List<OrderInfoVO> orderVos = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orders, OrderInfoVO.class);
			for (OrderInfoVO orderInfoVO : orderVos) {
				ProductComboPO comboPO=conn_combo.get(orderInfoVO.getComboId());
				orderInfoVO.setComboName(comboPO==null?"标准":comboPO.getCombo());
			}
			strMap.put("data", orderVos);
			strMap.put("code", "0");

			strMap.put("count", count);
			strMap.put("msg", "");
			return strMap;
		}
		
		//4/23 新增的方法 将拒绝退款理由存入数据库
		@ResponseBody
		@RequestMapping(value = "/updateJustification.do", method = RequestMethod.POST)
		public String updateJustification(Long orderId,String justification) throws Exception {
			OrderInfoPO orderInfoPO = conn_OrderInfo.get(orderId);
			orderInfoPO.setJustification(justification);
			conn_OrderInfo.save(orderInfoPO);
			return "success";
		}
	
		// 桌子订单列表
		@RequestMapping(value = "/tableorderlist", method = RequestMethod.GET)
		public ModelAndView tableOrderList(HttpServletRequest request) {
			ModelAndView mv = new ModelAndView("admin/orderinfo/tableorderlist");
			return mv;
		}
		
		//获取所有的餐桌订单
		@ResponseBody
		@RequestMapping("/alltableorder")
		public Map<String, Object> allTableOrder(HttpServletRequest request,int page,int limit ) throws Exception{
			Map<String, Object> strMap = new HashMap<String, Object>();
			List<TableStatusPO> allorders = conn_tablestatus.findAll(page, limit);
			int count = conn_tablestatus.countAll();
			String type="";
			List<TableOrderVO> all=new ArrayList<TableOrderVO>();
			for (TableStatusPO tableStatusPO : allorders) {
				TableOrderVO vo=new TableOrderVO();
				vo.setMerchantId(tableStatusPO.getMerchantId());
				vo.setUserId(tableStatusPO.getUserId());
				vo.setOrderid(tableStatusPO.getId()+"");
				vo.setMerchantName(conn_merchant.get(tableStatusPO.getMerchantId()).getShopName()+"");
				vo.setTableId(tableStatusPO.getTableId()+"");
				vo.setUserName(tableStatusPO.getUserName()+"");
				vo.setUserPhone(tableStatusPO.getUserPhone()+"");
				vo.setType(tableStatusPO.getType().getFiled()+"");
				vo.setTableDate(tableStatusPO.getTableDate()+"");
				if(tableStatusPO.getTableState()==null){
					vo.setTableState("未预定");
				}else if(tableStatusPO.getTableState().equals("PAYSUCCESS")){
					vo.setTableState("已预订");
				}else if(tableStatusPO.getTableState().equals("PAST")){
					vo.setTableState("已过期");
				}else if(tableStatusPO.getTableState().equals("NOTPAY")){
					vo.setTableState("未支付");
				}
				if(tableStatusPO.getTableId()!=0){
					vo.setBookPrice(conn_table.get(tableStatusPO.getTableId()).getBookprice()/100+"");
				}else{
					vo.setBookPrice("0");
				}
				if(tableStatusPO.getDishState()==null){
					vo.setDishState("未订餐");
				}else if(tableStatusPO.getDishState().equals("PAYSUCCESS")){
					vo.setDishState("已预订");
				}else if(tableStatusPO.getDishState().equals("PAST")){
					vo.setDishState("已过期");
				}else if(tableStatusPO.getDishState().equals("NOTPAY")){
					vo.setDishState("未支付");
				}
				vo.setDishPrice(tableStatusPO.getDishMoney()/100+"");
				all.add(vo);
			}
			strMap.put("data", all);
			strMap.put("code", "0");
			strMap.put("count", count);
			strMap.put("msg", "");
			return strMap;
		}

		
		// 桌子订单列表
		@RequestMapping(value = "/getdishlist")
		public ModelAndView getDishList(HttpServletRequest request,Long tableId,Long userId,Long merchantId) {
			ModelAndView mv = new ModelAndView("admin/orderinfo/dishlist");
			mv.addObject("tableId", tableId);
			mv.addObject("userId", userId);
			mv.addObject("merchantId", merchantId);
			return mv;
		}
		
		//获取所有的餐桌订单
		@JsonBody
		@ResponseBody
		@RequestMapping("/getdishorder")
		public Map<String, Object> getDishOrder(HttpServletRequest request,Long tableId,Long userId,Long merchantId,int page,int limit ) throws Exception{
			System.out.println("我进来啦啦啦啦啦");
			Map<String, Object> strMap = new HashMap<String, Object>();
			List<ProductPO> product=new ArrayList<ProductPO>();
			List<MealListPo> mealList = conn_meallist.getMealList(tableId, userId, merchantId,page,limit);
			if(mealList!=null){
				for (MealListPo mealListPo : mealList) {
					product.add(conn_product.get(mealListPo.getProductId()));
				}
			}
			List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(product, ProductVO.class);
			int count = conn_meallist.countByTidUidMid(tableId, userId, merchantId);
			strMap.put("data", listvo);
			strMap.put("code", "0");
			strMap.put("count", count );
			strMap.put("msg", "");
			return strMap;
		}
		
}
