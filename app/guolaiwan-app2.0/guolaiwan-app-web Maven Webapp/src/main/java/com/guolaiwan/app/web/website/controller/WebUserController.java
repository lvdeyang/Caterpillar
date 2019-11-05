package com.guolaiwan.app.web.website.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.util.binary.Sha1Util;

@Controller
@RequestMapping("/user")
public class WebUserController extends WebBaseControll {

	@Autowired
	private UserInfoDAO conn_user;
	@Autowired
	private OrderInfoDAO conn_order;
	@Autowired
	private AddressDAO conn_address;
	@Autowired
	private MerchantDAO conn_merchant;

	// 登录页面
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		String rul = "";

		if (request.getParameter("rul") != null)
			rul = request.getParameter("rul");
		String str = rul.replace("!", "&");
		ModelAndView mav = new ModelAndView("web/user/login");
		mav.addObject("rul", str);
		return mav;
	}

	// 用户登录
	@RequestMapping("/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		String userPhone = request.getParameter("userPhone");
		String userPassword = request.getParameter("userPassword");
		String code = request.getParameter("code").toLowerCase();

		HttpSession session = request.getSession(true);
		String _code = (String) session.getAttribute("verCode");

		if (!code.equals(_code)) {
			// 验证码错误
			return "codeerror";
		}

		UserInfoPO user = conn_user.getUserByPhone(userPhone);
		if (user == null) {
			return "phoneerror";
		}

		String s_pas = Sha1Util.getSha1(userPassword);
		String ss = user.getUserPassword();
		if (!s_pas.equals(user.getUserPassword())) {
			return "pwderror";
		}
		//判断是否为商户
		MerchantPO merchant = conn_merchant.getMerByUser(user);
		session.setAttribute("userInfo", user);
		if(merchant == null){
			return "userSuccess";
		}else{
			session.setAttribute("merchant", merchant);
			return "merSuccess";
		}
	}

	//登出
	@RequestMapping("/doLogout")
	public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("userInfo");

		ModelAndView mav = new ModelAndView("redirect:/index");
		return mav;
	}

	// 登录后跳转到个人中心
	@RequestMapping("/myuser")
	public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("redirect:/index");
		return mav;
	}

	@RequestMapping("/usercenter")
	public ModelAndView usercenter(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("web/user/my-user");
		return mav;
	}

	// 修改密码页面
	@RequestMapping("/mypwd")
	public String mypwd() {
		return "web/user/my-pwd";
	}

	// 修改用户密码
	@RequestMapping("modifyPwd")
	@ResponseBody
	public String modifyPwd(HttpServletRequest request, HttpServletResponse response) {
		String userPassword = request.getParameter("userPassword");
		String newPassword = request.getParameter("newPassword");
		String repsNewPwd = request.getParameter("repsNewPwd");

		UserInfoPO user = conn_user.getUserByPhone(GetUserInfo().getUserPhone());
		String password = user.getUserPassword();

		if (password.equals(Sha1Util.getSha1(userPassword)) && newPassword.equals(repsNewPwd)) {
			user.setUserPassword(Sha1Util.getSha1(newPassword));
			conn_user.saveOrUpdate(user);

			return "success";
		}

		return "error";
	}

	// 注册页面
	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("web/user/register");
		return mv;
	}

	// 用户注册
	@RequestMapping("/doRegist")
	@ResponseBody
	public String doRegist(HttpServletRequest request, HttpServletResponse response) {

		String userPhone = request.getParameter("userPhone");
		String userPassword = request.getParameter("userPassword");
		String repsword = request.getParameter("repsword");

		String code = request.getParameter("code").toLowerCase();

		String userPhoneRegex = "\\d{11}";
		boolean phoneNo = userPhone.matches(userPhoneRegex);
		UserInfoPO haveregi = conn_user.getByField("userPhone", userPhone);

		boolean quphone = haveregi != null;
		if (!phoneNo || quphone) {
			return "";
		}

		HttpSession session = request.getSession(true);
		String _code = (String) session.getAttribute("verCode");
		if (!userPassword.equals(repsword)) {
			return "perror";
		}

		if (!code.equals(_code)) {
			// 验证码错误
			return "cerror";
		}

		UserInfoPO user = new UserInfoPO();
		user.setUpdateTime(new Date());
		user.setUserPhone(userPhone);
		user.setUserPassword(Sha1Util.getSha1(userPassword));
		conn_user.save(user);

		return "success";
	}

	// 用户个人中心左侧菜单
	@RequestMapping("/memberLeft")
	public ModelAndView memberLeft(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();
		UserInfoPO userpo = conn_user.findByField("userPhone", GetUserInfo().getUserPhone()).get(0);
		UserInfoVO user = new UserInfoVO().set(userpo);
		data.put("user", user);

		ModelAndView mav = new ModelAndView("web/user/member-left", data);
		return mav;
	}

	@RequestMapping("/order/list")
	public ModelAndView orderListPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/web/user/my-order");
		return mav;
	}
	
	// 用户订单列表页面
	@RequestMapping("/order/list/{index}")
	public ModelAndView orderList(@PathVariable int index, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();
		long userId = GetUserInfo().getId();

		int count;
		int pageSize = 10;
		String url = ""+index;
		String params ="";
		int pagecurr =1;
		if(request.getParameter("pagecurr")!=null && request.getParameter("pagecurr").length()>0){
			pagecurr=Integer.parseInt(request.getParameter("pagecurr"));
		}

		switch (index) {
		// 全部订单
		case 0:
			List<OrderInfoPO> allOrderpos = conn_order.findByFieldByPage("userId", userId, pagecurr, pageSize);
			List<OrderInfoVO> allOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(allOrderpos,
					OrderInfoVO.class);
			data.put("orders", allOrders);

			count = conn_order.countByField("userId", userId);
			data.put("pages", getPages(pagecurr,count,pageSize,url,params));

			break;
			// 未付款
		case 1:
			List<OrderInfoPO> orderingOrderpos = conn_order.getOrdersByState(userId, OrderStateType.NOTPAY, pagecurr, pageSize);
			List<OrderInfoVO> orderingOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderingOrderpos,
					OrderInfoVO.class);
			data.put("orders", orderingOrders);

			String[] fields ={"userId","orderState"};
			Object[] values ={userId,OrderStateType.NOTPAY};
			count = conn_order.countByFields(fields, values);
			data.put("pages", getPages(pagecurr,count,pageSize,url,params));

			break;
			// 已付款
		case 2:
			List<OrderInfoPO> orderedOrderpos = conn_order.getOrdersByState(userId, OrderStateType.PAYSUCCESS, pagecurr, pageSize);
			List<OrderInfoVO> orderedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderedOrderpos,
					OrderInfoVO.class);
			data.put("orders", orderedOrders);
			String[] fields2 ={"userId","orderState"};
			Object[] values2 ={userId,OrderStateType.PAYSUCCESS};
			count = conn_order.countByFields(fields2, values2);
			data.put("pages", getPages(pagecurr,count,pageSize,url,params));
			break;
			// 已验单
		case 3:
			List<OrderInfoPO> testedOrderpos = conn_order.getOrdersByState(userId, OrderStateType.TESTED, pagecurr, pageSize);
			List<OrderInfoVO> testedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(testedOrderpos,
					OrderInfoVO.class);
			data.put("orders", testedOrders);
			String[] fields3 ={"userId","orderState"};
			Object[] values3 ={userId,OrderStateType.TESTED};
			count = conn_order.countByFields(fields3, values3);
			data.put("pages", getPages(pagecurr,count,pageSize,url,params));
			break;
			// 申请退款  
		case 4:
			List<OrderInfoPO> refundingOrderpos = conn_order.getOrdersByState(userId, OrderStateType.REFUNDING, pagecurr, pageSize);
			List<OrderInfoVO> refundingOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(refundingOrderpos,
					OrderInfoVO.class);
			data.put("orders", refundingOrders);
			String[] fields4 ={"userId","orderState"};
			Object[] values4 ={userId,OrderStateType.REFUNDING};
			count = conn_order.countByFields(fields4, values4);
			data.put("pages", getPages(pagecurr,count,pageSize,url,params));
			break;
			// 退款失败
		case 5:
			List<OrderInfoPO> refundfailOrderpos = conn_order.getOrdersByState(userId, OrderStateType.REFUNDFAIL, pagecurr, pageSize);
			List<OrderInfoVO> refundfailOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(refundfailOrderpos,
					OrderInfoVO.class);
			data.put("orders", refundfailOrders);
			String[] fields5 ={"userId","orderState"};
			Object[] values5 ={userId,OrderStateType.REFUNDFAIL};
			count = conn_order.countByFields(fields5, values5);
			data.put("pages", getPages(pagecurr,count,pageSize,url,params));
			break;
			// 退款成功
		case 6:
			List<OrderInfoPO> refundedOrderpos = conn_order.getOrdersByState(userId, OrderStateType.REFUNDED, pagecurr, pageSize);
			List<OrderInfoVO> refundedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(refundedOrderpos,
					OrderInfoVO.class);
			data.put("orders", refundedOrders);
			String[] fields6 ={"userId","orderState"};
			Object[] values6 ={userId,OrderStateType.REFUNDED};
			count = conn_order.countByFields(fields6, values6);
			data.put("pages", getPages(pagecurr,count,pageSize,url,params));
			break;
		default:

			break;
		}
		data.put("state", index);
		ModelAndView mav = new ModelAndView("/web/user/my-order", data);

		return mav;
	}

	// 订单详情页
	@RequestMapping("/order/info")
	public ModelAndView orderInfo(@RequestParam String orderNO) {
		Map<String, Object> data = new HashMap<String, Object>();

		OrderInfoPO order = conn_order.getByField("orderNO", orderNO);
		OrderInfoVO orderInfoVO=new OrderInfoVO();
		try {
			orderInfoVO.set(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("order", orderInfoVO);
		ModelAndView mav = new ModelAndView("/web/user/my-order-info", data);

		return mav;
	}

	// 用户地址页面
	@RequestMapping("/address/list")
	public ModelAndView addressPage() {
		Map<String, Object> data = new HashMap<String, Object>();
		UserInfoPO user = GetUserInfo();
		List<AddressPO> addresses = conn_address.findByField("userId", user.getId());
		data.put("addresses", addresses);
		data.put("user", user);
		ModelAndView mav = new ModelAndView("/web/user/my-address", data);
		return mav;
	}

}
