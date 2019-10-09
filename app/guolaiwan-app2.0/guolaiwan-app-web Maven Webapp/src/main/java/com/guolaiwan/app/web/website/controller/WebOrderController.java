package com.guolaiwan.app.web.website.controller;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.app.web.website.order.submit.TokenProcessor;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.SpecialOrderRemarkType;
import com.guolaiwan.bussiness.admin.enumeration.SpecialOrderType;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

@Controller
@RequestMapping("/user/order")
public class WebOrderController extends WebBaseControll{
	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private AddressDAO conn_address;

	@Autowired
	private ModularClassDAO conn_modularClass;

	@Autowired
	private OrderInfoDAO conn_orderInfo;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	


	//订单页面
	@RequestMapping(value = "/add", method= RequestMethod.GET)
	public ModelAndView productList(HttpServletRequest request) throws Exception {
		String type = request.getParameter("type");
		if(type==null||type.length()==0){
			throw new Exception();	
		}


		//将唯一的的订单码存到session中
		TokenProcessor tp = TokenProcessor.getInstance();  
		String token = tp.generateToken();  
		request.getSession().setAttribute("token",token); 

		Map<String, Object> strMap=new HashMap<String, Object>();
		ModelAndView mv = null;
		DecimalFormat deci = new DecimalFormat("0.00");
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

		String puuid = request.getParameter("pro");
		String count = request.getParameter("count");

		//地址
		UserInfoPO user = (UserInfoPO) request.getSession().getAttribute("userInfo");//用户
		String[] fieldsn = {"userId","defaultAddress"};
		Object[] valuesn = {user.getId(),0};
		List<AddressPO> addressn = conn_address.findByFields(fieldsn, valuesn);//非默认地址
		String[] fieldsd = {"userId","defaultAddress"};
		Object[] valuesd = {user.getId(),1};
		AddressPO addressd = conn_address.getByFields(fieldsd, valuesd);//默认地址；


		switch (type) {
		case "MERCHANT"://商家订单
			ProductPO productp = conn_product.get(puuid);//商品
			if(productp==null) return new ModelAndView("web/common/error");//商品不存在 

			long orderAllMoney = productp.getProductPrice()*Long.parseLong(count);//总价
			ProductVO  product = new ProductVO().set(productp);
			long meuuid = product.getProductMerchantID();
			MerchantPO merchant = conn_merchant.get(meuuid);//商家
			long jmoney;
			if(productp.getProductntegral()==0l){
				jmoney = 0;
			}else{
				jmoney = productp.getProductntegral()/100;//积分/10
			}
			long yun = productp.getSent();
			long payMoney = orderAllMoney - jmoney + yun; 
			strMap.put("sysConfig",sysConfig);
			strMap.put("product",product);
			strMap.put("merchant",merchant);
			strMap.put("user",user);
			strMap.put("addressn",addressn);
			strMap.put("addressd",addressd);
			strMap.put("count",count);//库存
			strMap.put("orderAllMoney",deci.format((double)orderAllMoney/100));//总价
			strMap.put("yun",deci.format((double)yun/100));//运费
			strMap.put("jmoney",deci.format((double)jmoney));//减额
			strMap.put("payMoney",deci.format((double)payMoney/100));//应付金额
			strMap.put("type","MERCHANT");//订单类型
			mv = new ModelAndView("web/order/add",strMap);
			return mv;
		case "DISTRIBUTOR":
			mv = new ModelAndView("web/order/add",strMap);
			return mv;
		default:
			throw new Exception("错误的订单类型！");	
		}
	}

	

	//创建订单
	@RequestMapping(value = "/createOrder.do", method= RequestMethod.POST)
	public ModelAndView createOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv =null;
		//订单标识码（防止重复提交）
		boolean b = isTokenValue(request);  
		if (!b){  
			Map<String, Object> errorMap = new HashMap<String, Object>();
			errorMap.put("info", "请不要重复提交订单。");
			mv = new ModelAndView("web/order/orderError",errorMap);
			System.out.println("重复提交订单。"); 
			return mv;
		}  
		request.getSession().removeAttribute("token");  
		String orderUuid = "";

		String type = request.getParameter("type");
		if(type!=null&&type.equals("DISTRIBUTOR")){//经销商订单
			
			
		}else if(type!=null&&type.equals("MERCHANT")){//商家订单
			String puuid= request.getParameter("puuid");
			String orderBookDate= request.getParameter("orderBookDate");
			String pcount1= request.getParameter("pcount");

			Map<String, Object> strMap = new HashMap<String, Object>();
			OrderInfoPO order = new OrderInfoPO();
			Date date= new Date();
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");

			//获取产品
			ProductPO product = conn_product.get(puuid);

			//获取商品数量
			int pcount = Integer.parseInt(pcount1);
			//订单总金额
			long orderAllMoney = pcount*product.getProductPrice();
			//支付金额
			long payMoney =orderAllMoney;

			//Session获取用户
			UserInfoPO user = GetUserInfo();


			//获取商家
			MerchantPO merchant = conn_merchant.get(product.getProductMerchantID());

			//地址

			String mailAddress1= request.getParameter("mailAddress");
			if(mailAddress1!=null&&mailAddress1.length()!=0){
				AddressPO address = conn_address.get(mailAddress1);
				long mailAddress =  address.getId();
				//收货地址id
				order.setMailAddress(mailAddress);
			}

			//订单号（城市编码+商家id+板块Code+时间戳+用户ID）
			String orderNO = getCityCodeByDomain()+"v"+merchant.getId()+"v"+product.getProductModularCode()+"v"+sdf1.format(date)+"v"+user.getId();
			order.setOrderNO(orderNO);
			//验单码

			//下单时间
			order.setCreateDate(date);
			order.setUpdateTime(date);
			//验单时间

			//供应商ID
			order.setShopId(merchant.getId());
			//供应商名称
			order.setShopName(merchant.getShopName());
			//站点ID
			//站点名称
			//商品ID
			order.setProductId(product.getId());
			//商品图片
			order.setProductPic(product.getProductShowPic());
			//商品名称
			order.setProductName(product.getProductName());
			//商品数量
			order.setProductNum(pcount);
			//商品单价
			order.setProductPrice(product.getProductPrice());
			//所属板块DI
			order.setBkCode(product.getProductModularCode());
			//所属板块名称
			order.setBkName(product.getProductModularCodeName());
			//套餐ID
			//套餐名称



			//会员ID
			order.setUserId(user.getId());
			//会员手机号
			order.setUserTel(user.getUserPhone());
			//会员信息
			//会员坐标经度
			//会员坐标维度
			//提成金额比例
			long proportion = product.getProductCommissionPrice();
			if(product.getProductCommissionCode()==1){
				order.setProportion(proportion);
			}
			//提成方式（0：佣金1：比例）
			order.setRoyaltyName(product.getProductCommissionCode());
			//积分数
			long integralNum = pcount * product.getProductntegral();
			order.setIntegralNum(integralNum);
			//订单佣金金额(分)
			long proportionMoney;
			if(product.getProductCommissionCode()==1){
				proportionMoney = pcount * product.getProductPrice()*proportion/100;
			}else {
				proportionMoney = pcount * proportion;
			}
			order.setProportionMoney(proportionMoney);
			//支付金额
			order.setPayMoney(payMoney);
			//订单总金额
			order.setOrderAllMoney(orderAllMoney);
			//订单说明
			if(request.getParameter("orderRemark")!=null&&request.getParameter("orderRemark").length()>0){
				order.setOrderRemark(request.getParameter("orderRemark"));
			}
			//支付方式
			/*String PayMode = request.getParameter("PayMode");
		order.setPayMode(PayType.fromString(PayMode));*/
			//订单状态
			order.setOrderType(OrderType.MERCHANT);
			order.setOrderState(OrderStateType.NOTPAY);
			order.setSource(OrderSource.WEBPAGE);
			//有效期

			//是否评价
			order.setCommentIs(0);
			order.setUserName(user.getUserNickname());
			//预订日期
			order.setOrderBookDate(sdf.parse(orderBookDate));

			conn_orderInfo.saveOrUpdate(order);
			orderUuid = order.getUuid();
		}
		mv = new ModelAndView("redirect:/user/order/submit?order="+orderUuid);
		return mv;
	}

	//支付页面
	@RequestMapping(value = "/submit", method= RequestMethod.GET)
	public ModelAndView submit(HttpServletRequest request) throws Exception{
		String orderUuid = request.getParameter("order");
		Map<String, Object> strMap = new HashMap<String, Object>();
		OrderInfoPO orderp = conn_orderInfo.get(orderUuid);
		ModelAndView mv = null;
		//1.判断库存
		long pNum = orderp.getProductNum();
		ProductPO product = conn_product.get(orderp.getProductId());
		if(product != null){
			long pStock = product.getProductStock();
			if(pNum>pStock){
				Map<String, Object> errorMap = new HashMap<String, Object>();
				String info = "商品不够啦！产品库存"+pStock+"，订购数量"+pNum+"。";
				errorMap.put("info", info);
				mv = new ModelAndView("/web/order/orderError",errorMap);
				return mv;
			}
		}

		//2.向页面传订单
		OrderInfoVO order = new OrderInfoVO();
		order.set(orderp);

		strMap.put("order", order);
		mv = new ModelAndView("web/order/submit",strMap);
		/*strMap.put("info", "支付端口尚未开放！，敬请期待！");
		mv = new ModelAndView("web/order/orderError",strMap);*/
		return mv;
	}

	//批量支付页面
	@RequestMapping(value = "/submit/batch", method= RequestMethod.POST)
	public ModelAndView submitBatch(
			HttpServletRequest request, 
			@RequestParam(required = false, value="orderUuids[]") List<String> orderUuids) throws Exception{

		ModelAndView mv = null;

		if(orderUuids==null || orderUuids.size()<=0){
			mv = new ModelAndView("/web/order/orderError");
			mv.addObject("info", "您没有结算任何订单！");
			return mv;
		}

		List<OrderInfoPO> orderInfos = conn_orderInfo.getAllByUuids(orderUuids);

		if(orderInfos==null || orderInfos.size()<=0){
			mv = new ModelAndView("/web/order/orderError");
			mv.addObject("info", "数据异常，系统订单不存在，请刷新页面！");
			return mv;
		}

		Set<Long> productIds = new HashSet<Long>();
		for(OrderInfoPO info:orderInfos){
			productIds.add(info.getProductId());
		}

		List<ProductPO> products = conn_product.getAllByIds(productIds);
		if(products==null || products.size()<=0){
			mv = new ModelAndView("/web/order/orderError");
			mv.addObject("info", "数据异常，系统商品已经下架，请联系管理员！");
			return mv;
		}
		for(OrderInfoPO info:orderInfos){
			ProductPO target = null;
			for(ProductPO product:products){
				if(product.getId().equals(info.getProductId())){
					target = product;
					break;
				}
			}

			//1.判断库存
			if(target != null){
				long pNum = info.getProductNum();
				long pStock = target.getProductStock();
				if(pNum>pStock){
					mv = new ModelAndView("/web/order/orderError");
					mv.addObject("info", "商品不够啦！产品库存"+pStock+"，订购数量"+pNum+"。");
					return mv;
				}
			}
		}

		List<OrderInfoVO> orders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfos, OrderInfoVO.class);

		mv = new ModelAndView("web/order/submit-batch");
		mv.addObject("orders", orders);
		return mv;
	}


	/**
	 * 
	 * @param 订单uuid
	 * @return 订单状态
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/state.do", method= RequestMethod.POST)
	public Map<String,Object> getState(HttpServletRequest request) throws Exception{

		String uuid = request.getParameter("orderUuid");

		OrderInfoPO order = conn_orderInfo.get(uuid);//订单不存在的状况
		if(order==null) throw new Exception();
		OrderStateType state = order.getOrderState(); 
		Map<String,Object> strMap  = new HashMap<String, Object>();
		strMap.put("state", state);
		return strMap;
	}

	@ResponseBody
	@RequestMapping(value = "/batch/state.do", method= RequestMethod.POST)
	public Map<String,Object> getStateBatch(HttpServletRequest request) throws Exception{

		String orderIds = request.getParameter("orderId");
		String[] idArr = orderIds.split("A");
		List<Long> ids = new ArrayList<Long>();
		for(String id:idArr){
			if(id != null){
				ids.add(Long.valueOf(id));
			}
		}
		List<OrderInfoPO> orders = conn_orderInfo.getAllByIds(ids);
		if(orders==null || orders.size()<=0) throw new Exception("未获取到订单信息！");

		boolean stat = true;

		for(OrderInfoPO order:orders){
			if(!OrderStateType.PAYSUCCESS.equals(order.getOrderState())){
				stat = false;
				break;
			}
		}

		Map<String,Object> strMap  = new HashMap<String, Object>();
		strMap.put("state", stat);
		return strMap;
	}

	//微信支付成功页面
	/**
	 * 
	 * @param 订单uuid
	 * @return 订单状态
	 * @throws Exception
	 */
	@RequestMapping(value = "/payed", method= RequestMethod.GET)
	public ModelAndView payed(HttpServletRequest request) throws Exception{
		Map<String,Object> strMap  = new HashMap<String, Object>();
		ModelAndView mv=null;
		String uuid = request.getParameter("order");
		OrderInfoPO order = conn_orderInfo.get(uuid);
		if(order.getOrderState().equals(OrderStateType.PAYSUCCESS)){
			strMap.put("order", order);
			mv = new ModelAndView("web/order/paySuccess",strMap);
		}
		return mv;
	}

	@RequestMapping(value = "/payed/batch", method= RequestMethod.GET)
	public ModelAndView payedBatch(HttpServletRequest request) throws Exception{
		Map<String,Object> strMap  = new HashMap<String, Object>();
		ModelAndView mv=null;

		String orderId = URLDecoder.decode(request.getParameter("order"));
		String[] idArr = orderId.split(",");
		List<Long> ids = new ArrayList<Long>();
		for(String id:idArr){
			ids.add(Long.valueOf(id));
		}

		List<OrderInfoPO> orders = conn_orderInfo.getAllByIds(ids);

		strMap.put("orders", orders);
		mv = new ModelAndView("web/order/paySuccessBatch",strMap);

		/*String uuid = request.getParameter("order");
		OrderInfoPO order = conn_orderInfo.get(uuid);
		if(order.getOrderState().equals(OrderStateType.PAYSUCCESS)){
			strMap.put("order", order);
			mv = new ModelAndView("web/order/paySuccess",strMap);
		}*/
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/payError", method= RequestMethod.POST)
	public Map<String, Object> payError(HttpServletRequest request) throws Exception{
		Map<String,Object> strMap  = new HashMap<String, Object>();
		if(request.getParameter("error")==null) return null;
		String error = request.getParameter("error");
		if(error.equals("timeover")){
			strMap.put("info", "微信二维码失效，请回订单支付页面，重新支付<a href=''>订单支付</a>");

		}
		return success(strMap);
	}


	//刪除订单
	@ResponseBody
	@RequestMapping(value = "/delete", method= RequestMethod.POST)
	public String orderdel(HttpServletRequest request) throws Exception {
		String  order = request.getParameter("order");
		conn_orderInfo.deleteByUuid(order);
		return "success";
	}


}
