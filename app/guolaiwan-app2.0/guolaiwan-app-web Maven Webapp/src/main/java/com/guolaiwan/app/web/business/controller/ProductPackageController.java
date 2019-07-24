package com.guolaiwan.app.web.business.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.interfac.alipay.AliAppOrderInfo;
import com.guolaiwan.app.web.Guide.controller.integralControll;
import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MessageDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductComboDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;

import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MessagePO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPayApp;
import pub.caterpillar.weixin.wxpay.WXPayConstants.SignType;
import pub.caterpillar.weixin.wxpay.WXPayUtil;


@RestController
@RequestMapping("/product/package")
public class ProductPackageController extends BaseController {
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private MerchantDAO merchantDao;
	
	@Autowired
	private ActivityRelDAO conn_acre;
	
    @Autowired
    private OrderInfoDAO conn_orif;
    
    @Autowired
    private CommentDAO conn_comm;
    
    @Autowired
    private MessageDAO conn_message;
    
    @Autowired
	private UserInfoDAO conn_user;
    
    @Autowired
	private ProductComboDAO conn_combo;
	
	/**
	 * 跳转方法
	 * */
	@RequestMapping(value="/purchase/jump")
	public ModelAndView purchaseJump(HttpServletRequest request){
	   String merchanId	= request.getParameter("merchantId");
	   Map<String, Object> map = new HashMap<String, Object>();
	   map.put("productMerchantID", merchanId);
	   return new ModelAndView("mobile/business/purchase",map);	
	}
	
			
	//查询商品信息 
	@RequestMapping(value="/list")
	public Map<String, Object> findPackageName(HttpServletRequest request) throws NumberFormatException, Exception{		
        //返回封装数据 商品价格   商品封装
		Map<String, Object>  map =new HashMap<String, Object>();
		List<Object> pro_pric = new ArrayList<Object>();
		List<ProductVO> pro_list = new ArrayList<ProductVO>();
		
		//获取merchantId
		String merhcantId = request.getParameter("merchantId");
		//分页条件封装
		String pageNum = request.getParameter("pageNum");
		int pageSize =5 ; 
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("productMerchantID", Long.parseLong(merhcantId));
		//分页获取所有商品
	    List<ProductVO> pro_vo = new ProductVO().getConverter(ProductVO.class).
		convert( productDao.findByPageC(mapp,Integer.valueOf(pageNum), pageSize), ProductVO.class);
	   //判断活动是否过期  
	    boolean is_overtime;
	    long nowDate = new Date().getTime();	    	    
	    //获取活动商品id
	     Map<Long, ActivityRelPO> activ_merchan_id = new HashMap<Long, ActivityRelPO>();
	     List<ActivityRelPO> activ_po = conn_acre.findAll();
	     for(ActivityRelPO po : activ_po){
	    	 if(nowDate> po.getBeginDate().getTime() && nowDate < po.getEndDate().getTime()){
	    		 is_overtime = true;
	    	 }else{is_overtime = false; continue;}
	    	if(is_overtime){
	    		if(po.getProductStock()>0){
	    			activ_merchan_id.put(po.getProductId(), po);	
	    		}	    			    		
	    	} 	    		    	  
	     }	     
	     DecimalFormat def = new DecimalFormat("0.00");
	     //判断商品是否为活动商品
	    Set<Long> pro_id = activ_merchan_id.keySet();    
		for(ProductVO pro : pro_vo){
			if(pro_id.contains(pro.getId())){
			pro.setProductName(	pro.getProductName()+",act");			
			pro_pric.add(def.format(Double.parseDouble(activ_merchan_id.get(pro.getId()).getPrice()+"")/100));
			pro_list.add(pro);
			}else{
			if(pro.getProductStock()>0){	
			pro.setProductName(	pro.getProductName()+",comm");	
			pro_pric.add(pro.getProductPrice());
			pro_list.add(pro);
			}
			}			
		}    
	      map.put("productPOs",pro_list);
	      map.put("strArryList",pro_pric);
	      return map;
	}	
	
	/**
	 *  商家 信息查询
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value="/merInfo",method=RequestMethod.POST)
	public Map<String, Object> getMerInfo(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();	
		String merchantId =  request.getParameter("merchantId");
		//获取商户信息		
		List<MerchantPO> merchantPOs = merchantDao.findByField("id",Long.parseLong(merchantId) );
		List<MerchantVO> mer_vo  = new MerchantVO().getConverter(MerchantVO.class)
				.convert( merchantPOs,MerchantVO.class );
		
	    List<ProductPO> productPOs = productDao.findByField("productMerchantID",Long.parseLong(merchantId));
		List<ProductVO> pr_vo  = new ProductVO().getConverter(ProductVO.class)
				.convert(productPOs, ProductVO.class);
		// 判断商品是否为购票产品
		for(int i= 0;i<pr_vo.size();i++){			
			if("园林".equals(pr_vo.get(i).getProductClassName())){
			 continue;	
			}else{ pr_vo.remove(i); }				
		}
		//评分
		double grade1 = 4.6;
		double grade2 = 0.0;
		//获取该商户的所有商品
		DecimalFormat defs = new DecimalFormat("0.0");
		int gradeSegment = productPOs.size()%3;
		switch (gradeSegment) {
		case 0:
			grade2 = 0.1;
			break;
        case 1:
        	grade2 = 0.2;
			break;
        case 2:
        	grade2 = 0.3;
	     break;
		default:
			break;
		}
		String grade = defs.format(grade1 + grade2);			
		//随机好评率
		 int feedback = 94;
		 Random random = new Random();
		 while(feedback<95){
			 feedback = random.nextInt(100);			 
		 }
		map.put("beginTime", merchantPOs.get(0).getBeginTimeDate().split(" ")[1]);
		map.put("endTime", merchantPOs.get(0).getEndTimeDate().split(" ")[1]);
		map.put("grade", grade); 
		map.put("feedback", feedback); 
		map.put("merpos", mer_vo.get(0));
		map.put("prosinfos", pr_vo);
		return map;
	}
	
	/**
	 * 跳转购票详情页面
	 * */
	@RequestMapping(value="/commodity/jump")
	public ModelAndView commodityJump(HttpServletRequest request,long choice){	
	 String merchantId = request.getParameter("merchantId");
	 String proId = request.getParameter("proId");
	 Map<String, Object> map = new HashMap<String, Object>();
	 map.put("merchantId", merchantId);
	 map.put("proId", proId);
     if(choice == 0){
	 return new ModelAndView("mobile/business/commodity",map);
	}else{
		return new ModelAndView("mobile/business/activityPurchase",map);	
	 }
	}
	
	/**
	 *  单个商品详情
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value="/commodity/info",method=RequestMethod.GET)
	public Map<String, Object> getCommodityInfo(Long proId,Long merchantId,Long choice) throws Exception{
	  //封装前台数据
	   Map<String, Object> map = new HashMap<String, Object>();
       List<MerchantPO> mer_po = new ArrayList<MerchantPO>();       
       List<String> priceList = new ArrayList<String>();
       List<Integer> oderNumList = new ArrayList<Integer>();
      //商户信息 商品信息  订单数量
       mer_po.add(merchantDao.get(merchantId));
       List<MerchantVO> mer_vo = new MerchantVO().getConverter(MerchantVO.class)
    		   .convert(mer_po, MerchantVO.class);                   
      //价格处理     
       DecimalFormat def = new DecimalFormat("0.00");     
      //票种类型
       List<ProductComboPO> pComboPOs = conn_combo.findByField("productId", proId);  
       for(ProductComboPO po : pComboPOs){	     
    	      //票种的价格
    	      String price = def.format(Double.parseDouble(po.getComboprice()+"")/100);
    	      priceList.add(price); 
    	     //票种的订单数量 
    	      String[] fields ={"productId","comboId"};
    	      Long[]  values = {proId,po.getId()};	      
    	      oderNumList.add(conn_orif.countByFields(fields, values));
    	        	   
       }
       //普通票
       if(choice==0){
           List<ProductVO> pr_vo =  new ProductVO().getConverter(ProductVO.class)
                   .convert(productDao.findByField("id", proId), ProductVO.class);
            //开放时间
             String[] beginDateStr = pr_vo.get(0).getProductBeginDate().split(" ");
             String[] endDateStr  = pr_vo.get(0).getProductEnddate().split(" ");
             map.put("beginDate", beginDateStr[0]);
             map.put("endDate", endDateStr[0]);
             map.put("proinfo", pr_vo);
       }
       //活动票
       if(choice==1){
        List<ActivityRelVO> act_vo = new ActivityRelVO().getConverter(ActivityRelVO.class)
    	   .convert(conn_acre.findByField("productId", proId), ActivityRelVO.class);    	
    	 //开放时间
         String beginDateStr =  act_vo.get(0).getBeginTime();
         String endDateStr  = act_vo.get(0).getEndTime();
    	 map.put("beginDate", beginDateStr);
         map.put("endDate",endDateStr);
         map.put("proinfo", act_vo);
       }
         //评分
     		double grade1 = 4.6;
     		double grade2 = 0.0;
     		//获取该商品的所有订单
     		 DecimalFormat defs = new DecimalFormat("0.0");
     	    int count =	conn_orif.countByField("productId", proId);
     		int gradeSegment = count%3;
     		switch (gradeSegment) {
     		case 0:
     			grade2 = 0.1;
     			break;
             case 1:
             	grade2 = 0.2;
     			break;
             case 2:
             	grade2 = 0.3;
     	     break;
     		default:
     			break;
     		}    		
     		String grade = defs.format(grade1 + grade2);
     		map.put("grade",grade );
     //随机好评率
		 int feedback = 94;
		 Random random = new Random();
		 while(feedback<95){
			 feedback = random.nextInt(100);			 
		 }
		 map.put("feedback", feedback); 
		 
		 
  	   map.put("pComboPOs", pComboPOs); 
  	   map.put("priceList",priceList );
       map.put("merinfo", mer_vo);            
       map.put("oderNumList", oderNumList);
	   return map;
	
	}
	/**
	 * 商品评论
	 * @throws Exception 
	 * */
	@RequestMapping(value="/comment")
	public Map<String,Object> getProComment(Long proId) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		//该商品的所有评论
		List<CommentVO> comm_vo = new CommentVO().getConverter(CommentVO.class)
		.convert(conn_comm.findByField("proId", proId), CommentVO.class);
		map.put("comm", comm_vo);		
		return map;				
	} 
	
	/**
	 *  跳转订单页面
	 * */
	@RequestMapping(value="/payment/jump")
	public ModelAndView paymentJump(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String,Object>();
		String merchantId = request.getParameter("merchantId");
		String proId = request.getParameter("proId");
		String choice = request.getParameter("choice");
		String comboId = request.getParameter("comboId");		
		HttpSession session  =  request.getSession();
		long userId = (long)session.getAttribute("userId");
		map.put("userId", userId);
		map.put("merchantId", merchantId);
		map.put("proId", proId);
		map.put("choice", choice);
		map.put("comboId", comboId);
		return new ModelAndView("mobile/business/ordersTicket",map);		
	}
	
  /**
   * 查询购票商品价格
   * */	
	@RequestMapping(value="/orders/info",method=RequestMethod.GET)
	public Map<String, Object> getOrdersInfo(long comboId){
		Map<String, Object> map = new HashMap<String, Object>();
		DecimalFormat dlf = new DecimalFormat("0.00");
		ProductComboPO ComboPOs = conn_combo.get(comboId);
		//获取票的价格
		String ticketPrice =  dlf.format(Double.parseDouble(ComboPOs.getComboprice()+"")/100);
		map.put("ticketPrice", ticketPrice);
		return map;
	}
	
	  
	
	/**
	 * 购票人信息
	 * 
	 * */
	@RequestMapping(value="/add/info",method=RequestMethod.POST)
	public String saveTicketInfo(HttpServletRequest request){
    //保存
	String state =	request.getParameter("state");
	if("0".equals(state)){	
	String base = request.getParameter("facedate");
    String name = request.getParameter("name");
    String phone = request.getParameter("phone");
    String idcard = request.getParameter("idcard");
    String merchantId = request.getParameter("merchantId");
    String proId = request.getParameter("proId");
    HttpSession session  =  request.getSession();
    long userId = (long)session.getAttribute("userId");
    MessagePO mes = new MessagePO();
    mes.setBase(base);
    mes.setMerchantid(merchantId);
    mes.setName(name);
    mes.setNumber(idcard);
    mes.setProId(Long.parseLong(proId));
    mes.setState("0");
    mes.setUserId(userId);
    mes.setPhone(phone);
    conn_message.save(mes);
	}else{
		//更新
		String name = request.getParameter("name");
	    String phone = request.getParameter("phone");
	    String idcard = request.getParameter("idcard");
	    String base = request.getParameter("facedate");	 
	    MessagePO mes  =  conn_message.get(Long.parseLong(state));
	    mes.setName(name);
	    mes.setBase(base);
	    mes.setNumber(idcard);
	    /*mes.setTickPhone(phone);*/
	    conn_message.save(mes);
	}	
	return "success"; 
	}
	
	/**
	 *  实时查询数据库数量
	 * 
	 * */
	@RequestMapping(value="/common/info")
    public Map<String, Object> getCommon(long proId,long ticket,long ticketnumber){	
		Map<String, Object> map = new HashMap<String, Object>();
		String state = "";
		//普通商品
		if(ticket == 1){
		ProductPO pro_po = productDao.get(proId);
		//查询库存
		if(pro_po.getProductStock()>0){	
			if(pro_po.getProductStock()<ticketnumber){
				state="1";//库存比买的数量少 
				map.put("state", state);	
				map.put("Stock", pro_po.getProductStock());					
			}else{
				//判断是否限购
				if(pro_po.getProductLimitType()==1){
					if(ticketnumber> pro_po.getProductLimitNum()){
						state= "2";//买的数量多于限购
						map.put("state", state);
						map.put("limitNum", pro_po.getProductLimitNum());
					}					
				}else{
					state = "3";//购买成功
					map.put("state", state);
				}			
			}						
		 }else{
			 state = "0"; // 库存为0的状态
			 map.put("state", state); 
		   }			
		}	
		return map ;
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
	 * 添加用户信息
	 * */
	@RequestMapping(value="/user/list")
	public Map<String, Object> getMessageInfo(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		 HttpSession session  =  request.getSession();
		 long userId = (long)session.getAttribute("userId");
		 try{
		List<MessagePO> mes_po = conn_message.findByField("userId", userId);
		map.put("message", mes_po);	 
		 }catch(Exception e){System.out.println(e);}		
		return map;
	}
	/**
	 * 修改用户信息
	 * 
	 * */
	@RequestMapping(value="/update/message")
	public Map<String, Object> updateMessage(Long id){
		Map<String, Object> map = new HashMap<String, Object>();		
	    MessagePO mes_po =	conn_message.get(id);
	    map.put("mes", mes_po);
		return map;
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
		//String activityId = pageObject.getString("activityId");
				
		/*
		 * String productId = request.getParameter("productId"); String num =
		 * request.getParameter("num"); String paytype =
		 * request.getParameter("payType"); Long userId =
		 * Long.parseLong(request.getParameter("userId"));
		 */
	

		ProductPO productPO = productDao.get(Long.parseLong(productId));
		if (num == null) {
			num = "1";
		}
		String comboId = productPO.getComId()+"";
		OrderInfoPO order = new OrderInfoPO();
		// 4/26添加comId 张羽 4/28 添加退款限制
		ProductPO productPO2 = productDao.get(Long.parseLong(productId));
		order.setComId(productPO2.getComId());
		order.setProductIsRefund(productPO2.getProductIsRefund());

		if (comboId != null) {
			order.setComboId(Long.parseLong(comboId));
		}

		/*String orderStartDate = pageObject.getString("startDate");
		if (orderStartDate != null && orderStartDate != "" && orderStartDate.length() != 0) {
			orderStartDate = orderStartDate.replace("T", " ");
			order.setOrderBookDate(DateUtil.parse(orderStartDate, DateUtil.dateTimePattenWithoutSecind));
		}
		String endBookDate = pageObject.getString("endDate");
		if (endBookDate != null && endBookDate != "" && endBookDate.length() != 0) {
			endBookDate = endBookDate.replace("T", " ");
			order.setEndBookDate(DateUtil.parse(endBookDate, DateUtil.dateTimePattenWithoutSecind));
		}*/
		

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();

		long productprice = productPO.getProductPrice();
		// 支付金额
		long payMoney = Integer.parseInt(num) * productprice;
		// 订单总金额
		long orderAllMoney = payMoney;
		// 获取产品

		// 获取商家
		MerchantPO merchant = merchantDao.get(productPO.getProductMerchantID());
		UserInfoPO user = conn_user.get(userId);
		/*String orderBookDate = pageObject.getString("bookDate");
		if (orderBookDate != null && orderBookDate != "" && orderBookDate.length() != 0) {
			orderBookDate = orderBookDate.replace("T", " ");
			order.setOrderBookDate(DateUtil.parse(orderBookDate, DateUtil.dateTimePattenWithoutSecind));
		}*/

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

		// 订单号（商家id+板块Code+时间戳+用户ID）
		String orderNO = merchant.getId() + productPO.getProductModularCode() + df.format(date)
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
		if (comboId != null && !comboId.equals("0")) {
			ProductComboPO comboPO = conn_combo.get(Long.parseLong(comboId));
			payMoney = Integer.parseInt(num) * (comboPO.getComboprice());
			orderAllMoney = payMoney;
			productprice = comboPO.getComboprice();
		}
		/*// 获取用户
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
			productprice = activityRelPO.getPrice();
		}
*/
		/*
		 * if (order.getOrderBookDate() != null && order.getEndBookDate() !=
		 * null) { long bet = DateUtil.daysBetween(order.getOrderBookDate(),
		 * order.getEndBookDate()); payMoney = payMoney * (bet+1); orderAllMoney
		 * = payMoney; }
		 */
/*		// 张羽 修改支付时的价钱按照页面的计算来 4/30
		long bet = Long.parseLong(pageObject.getString("payMoney"));
		payMoney = payMoney * bet;
		orderAllMoney = payMoney;
		System.out.println(orderAllMoney + "-------------------------");*/
		// 商品单价
		order.setProductPrice(productprice);
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
		order.setOrderState(OrderStateType.NOTPAY);
		// 订单支付类型 //ALIPAY WEICHAT
		order.setPayMode(PayType.fromString(paytype));
		// 有效期
		
		// 是否评价
		order.setCommentIs(0);
		// // 预订日期
		// order.setOrderBookDate(date);

		if (pageObject.getString("source") != null) {
			order.setSource(OrderSource.fromString(pageObject.getString("source")));
		} else {
			// 订单来源
			order.setSource(OrderSource.APP);
		}
		order.setOrderType(OrderType.MERCHANT);
		productPO.setProductSaleNum(productPO.getProductSaleNum() + 1);
		productPO.setProductShowNum(productPO.getProductShowNum() + 1);
		productDao.update(productPO);
		conn_orif.saveOrUpdate(order);
	
		long PayMoney = order.getPayMoney();
		/* String tradeNum=order.getOrderNO(); */
		String orderIdStr = String.valueOf(order.getId());
		// 调微信和支付宝
		if (paytype.equals("WEICHAT")) { // 微信
			Map<String, String> weichatPay = weichatPay(PayMoney, orderIdStr);
			weichatPay.put("orderId", order.getId() + "");
			return success(weichatPay);
		} else if (paytype.equals("ALIPAY")) {
			String productnum = String.valueOf(order.getProductNum());
			String pname = order.getProductName();
			// String num,Long allMoney,String productName,String orderNo
			String sign = AliAppOrderInfo.getInstance().getSign(productnum, PayMoney, pname, orderIdStr);
			data.put("orderId", order.getId());
			data.put("orderInfo", sign);
			return success(data);
		} else {
			return ERROR("系统错误！");
		}
	}


	// 微信支付方法
		public Map<String, String> weichatPay(long PayMoney, String tradeNum) {
			Map<String, String> reqData = new HashMap<String, String>();
			Map<String, String> resData = null;
			try {
				GuolaiwanWxPayApp wxPay = GuolaiwanWxPayApp
						.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
				reqData.put("total_fee", "" + PayMoney); // 总价
				reqData.put("attach", "test"); // 订单的自定义数据
				reqData.put("body", "guolaiwan-order"); // 内容
				reqData.put("out_trade_no", tradeNum); // 订单号
				reqData.put("spbill_create_ip", "192.165.56.64");
				reqData.put("trade_type", "APP"); // 支付类型APP
				reqData.put("device_info", "WEB");
				resData = wxPay.pay(reqData);
				System.out.println("7" + resData);
				String noncestr = resData.get("nonce_str");
				String partnerid = resData.get("mch_id");
				String prepay_id = resData.get("prepay_id");

				resData.put("noncestr", noncestr);
				resData.put("package", "Sign=WXPay");
				resData.put("partnerid", partnerid);
				resData.put("prepayid", prepay_id);
				resData.put("timestamp", "" + new Date().getTime() / 1000);
				resData.remove("nonce_str");
				resData.remove("return_msg");
				resData.remove("mch_id");
				resData.remove("prepay_id");
				resData.remove("device_info");
				resData.remove("trade_type");
				resData.remove("result_code");
				resData.remove("return_code");
				resData.remove("sign");
				System.out.println(resData);
				resData.put("sign", WXPayUtil.generateSignature(resData, GuolaiwanWxPayApp.AppKey, SignType.MD5));

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			System.out.println(resData.toString());
			return resData;
			
		}
}
