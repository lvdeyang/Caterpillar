package com.guolaiwan.app.web.business.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.Guide.controller.integralControll;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.weixin.SendMsgUtil;
import com.guolaiwan.app.web.weixin.WxConfig;
import com.guolaiwan.app.web.weixin.YuebaWxPayConstants;
import com.guolaiwan.app.web.weixin.YuebaWxUtil;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.InvestWalletDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantChildrenDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantUserDao;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.TableDAO;
import com.guolaiwan.bussiness.admin.dao.TableStatusDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.BookType;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.InvestWalletPO;
import com.guolaiwan.bussiness.admin.po.MerchantChildrenPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MerchantUser;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.TablePO;
import com.guolaiwan.bussiness.admin.po.TableStatusPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.distribute.dao.MealListDao;
import com.guolaiwan.bussiness.distribute.po.MealListPo;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.weixin.constants.WXContants;

/**
 * 美食
 */
@RestController
@RequestMapping("/cate")
public class CateController extends WebBaseControll {
	private final int recommendSize = 6;
	@Autowired
	private MerchantDAO conn_merchant;
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private MerchantChildrenDao merchant_Children;
	@Autowired
	private MerchantDAO Merchantdao;
	@Autowired
	private MerchantDAO Mer_chant;
	@Autowired
	private OrderInfoDAO orderInfoDao;
	@Autowired
	private ProductDAO productDao;
	@Autowired
	private MealListDao MealList;
	
	@Autowired
	private UserInfoDAO conn_user;
	/**
	 * 二级页面 轮播图（模块）
	 * 美食轮播图
	 * @param modularCode
	 * @return merchants
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getRecommendByModu", method = RequestMethod.GET)
	public List<MerchantVO> getRecommendByMou(HttpServletRequest request, HttpServletResponse response,
			String merchantId) throws Exception {
		List<MerchantChildrenPO> merchantChildren = merchant_Children.getCate(Long.parseLong(merchantId));
		 List<MerchantVO> list = new ArrayList<MerchantVO>();
		for (MerchantChildrenPO merchantChildrenPO : merchantChildren) {
			List<MerchantPO> po = Merchantdao.getMerchantById(merchantChildrenPO.getChildrenId());
			for (MerchantPO merchantPO : po) {
				if ( "0003".equals(merchantPO.getModularCode()) && merchantPO !=null ) {
					MerchantVO name = new MerchantVO();
					name.setId(merchantPO.getId());
					name.setShopName(merchantPO.getShopName());
					name.setShopPic(merchantPO.getShopPic());
					/*List<MerchantVO> Merchant = MerchantVO.getConverter(MerchantVO.class).convert(po, MerchantVO.class);*/
					list.add( name );
				}
			}
		}
		return list;
	}
	
	//按照商品类型所有的商户
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/search")
	public Map<String, Object> search(HttpServletRequest request) throws Exception {
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		String name=request.getParameter("name");
		String type=request.getParameter("type");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<MerchantPO> merchantlist=new ArrayList<MerchantPO>();
		List<MerchantChildrenPO> merchantChildren = merchant_Children.getCate(merchantId);
		for (MerchantChildrenPO merchantChildrenPO : merchantChildren) {
			List<MerchantPO> allMerchant = Mer_chant.getMerchantById(merchantChildrenPO.getChildrenId());
			for (MerchantPO merchantPO : allMerchant) {
				Boolean status  =   merchantPO.getShopName().contains(name);
			if (status  && merchantPO.getModularCode().equals(type)) {
				merchantlist.add(merchantPO);
			}
		  }	
		}
		List<MerchantVO> merlist = MerchantVO.getConverter(MerchantVO.class).convert(merchantlist, MerchantVO.class);
		hashMap.put("merlist", merlist);
		return hashMap;
	}
	
	
	//菜单名称
	@ResponseBody
	@RequestMapping(value="/order/list")
	public Map<String, Object> orderList(HttpServletRequest request) throws Exception{
		String merchantId = request.getParameter("merchantId");	 
		long userId = 	(Long) request.getSession().getAttribute("userId");
		//根据productMerchantID查询商品信息
		List<ProductPO> productPOs = productDao.findByMerchantId(Long.parseLong(merchantId)); 
		//对商品进行类型的存储
		Map<String, Object> hashMap = new HashMap<String, Object>();
		//遍历商品信息
		List<ProductVO> merlist	 = ProductVO.getConverter(ProductVO.class).convert(productPOs, ProductVO.class);
		for (ProductVO productVO : merlist) {
			MealListPo MealListPo = MealList.findByDistributor(productVO.getId(),userId,Long.parseLong(merchantId));
			if (MealListPo != null) {
				productVO.setMealAmount(MealListPo.getMealAmount());
			}
		}
		hashMap.put("name", merlist);
		return hashMap ;	  
	}
	
	
	//创建订单
	@ResponseBody
	@RequestMapping(value="/newTableStatus", method = RequestMethod.POST)
	public Map<String, Object> newTableStatus(HttpServletRequest request) throws Exception{
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		long merchantId = pageObject.getLong("merchantId"); // 商户id
		String orderId = pageObject.getString("orderId"); // 订单id
		String userName = pageObject.getString("userName"); // 用户名称
		String userPhone = pageObject.getString("userPhone"); // 用户手机号
		String tableDate = pageObject.getString("tableDate"); // 用户选择时间
		String type = pageObject.getString("type"); //就餐时间
		long userId = 	(Long) request.getSession().getAttribute("userId");
		String orderNo = null;
		if(orderId != null &&  !"".equals(orderId)){ //已有订单
			orderNo = orderId+"";
		}else{ //无订桌
			TableStatusPO table = new TableStatusPO();
			table.setDishState("NOTPAY");
			table.setMerchantId(merchantId);
			table.setUserName(userName);
			table.setUserPhone(userPhone);
			table.setTableDate(tableDate);
			table.setUserId(userId+"");
			table.setType(BookType.fromName(type));
			Table_Status.saveOrUpdate(table);
			orderNo = table.getId()+"";
		}
		//对商品进行类型的存储
		Map<String, Object> hashMap = new HashMap<String, Object>();
		System.out.println(orderNo);
		hashMap.put("orderId", orderNo);
		return hashMap ;	  
	}
	
	
	//整减 菜存入数据库
	@ResponseBody
	@RequestMapping(value="/order/setmeal")
	public Object setmeal(HttpServletRequest request) throws Exception{
	    long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		long merchantId = pageObject.getLong("merchantId"); // 商户id
		String tableId = pageObject.getString("tableId"); // 订桌Id
		long productId = pageObject.getLong("productId"); // 产品Id
		int mealAmount = pageObject.getInteger("mealAmount"); // 数量
		MealListPo MealListPo = MealList.findByDistributor(productId,userId,merchantId);
		if (  MealListPo!= null) {
			if(mealAmount >0){
				MealListPo.setMealAmount(mealAmount);
				MealList.save(MealListPo);
			}else{
				MealList.delete(MealListPo);
			}
		}else{
			MealListPo meal = new MealListPo();
			meal.setProductId(productId);
			meal.setMerchantId(merchantId);
			if (tableId != null &&  !"".equals(tableId)) {
				meal.setTableId(Long.parseLong(tableId));
			}
			meal.setMealAmount(mealAmount);
			meal.setUserId(userId);
			MealList.saveOrUpdate(meal);
		}
		return "" ;	  
	}
	
	@Autowired
	private  TableStatusDAO Table_Status;
	
	
	// 购买菜
	@ResponseBody
	@RequestMapping(value = "/buydish/port/{id}/{money}")
	public Object buyDish(@PathVariable String id, @PathVariable Integer money, HttpServletRequest request) throws Exception {
		String	orderNo = "buydish-" + id;
		TableStatusPO TableStatus  =  Table_Status.getByField("id",Long.parseLong(id));
		int payMoney = 10;
		if (TableStatus.getTableId() > 0) {
			TablePO  addpo  = Table.getByField("id",TableStatus.getTableId());
			if (addpo != null && "PAYSUCCESS".equals(TableStatus.getTableState()+"")) {
				payMoney = (int) (money - Long.parseLong(addpo.getBookprice()+""));
				if(payMoney <= 0 ){
					return 0;
				}
			}
		}else{
			payMoney = money;
		}
		TableStatus.setDishMoney(money);
		Table_Status.saveOrUpdate(TableStatus);
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/buyDishPayment", WxConfig.appId,
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
	@Autowired
	private InvestWalletDAO conn_investwallet;
	@Autowired
	private  TableDAO Table;
	// 余额购买成功修改用户余额并推送消息 
	

	
	@ResponseBody
	@RequestMapping(value = "/wallet/walletbuy")
	public Object walletbuy(HttpServletRequest request) throws Exception {
		String orderId = request.getParameter("orderId");
		long meony = Long.parseLong(request.getParameter("meony"));
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		UserInfoPO user = conn_user.get(userId);
		TableStatusPO TableStatus = Table_Status.getByField("id",Long.parseLong(orderId));
		MerchantPO merchantPO = conn_merchant.getByField("id",TableStatus.getMerchantId());
		long userMoney = user.getWallet();
		long payMoney = 0;
		TablePO addpo  = null;
		if (userMoney >= meony/100) {
			user.setWallet(userMoney - meony/100);
			if (TableStatus.getTableId() > 0) {
				 addpo  = Table.getByField("id",TableStatus.getTableId());
				if (addpo != null && "PAYSUCCESS".equals(TableStatus.getTableState()+"")) {
					payMoney = meony - Long.parseLong(addpo.getBookprice()+"");
					if(payMoney <= 0 ){
						return success(3);
					}
				}
			}else{
				payMoney = meony;
			}
			if (TableStatus.getYdNO() == null || TableStatus.getYdNO() == "") {
				String ydNO = ydNoCode(orderId+"");
				TableStatus.setYdNO(ydNO);
			}
			TableStatus.setDishMoney(payMoney);
			TableStatus.setDishState("PAYSUCCESS");
			// 推送购买商品成功信息给用户 商家 李姐
			Table_Status.saveOrUpdate(TableStatus);
			sendPayMessage(TableStatus);
			InvestWalletPO o =new InvestWalletPO();
			o.setMoney(payMoney);
			o.setUserid(user.getId());
			o.setUsername(user.getUserNickname());
			o.setProductname(merchantPO.getShopName());
			conn_investwallet.save(o);
			conn_user.saveOrUpdate(user);
		} else {
			// 余额不足 不允许购买
			return success(2);
		}
		return success(1);
	}
	
	
	
	@Autowired MerchantUserDao conn_merchantUser;
	
	@Autowired
	private ProductDAO conn_product;
	
	/**
	 * 余额购买商品成功消息推送
	 * 
	 * @param orderInfoPO
	 */
	private void sendPayMessage(TableStatusPO TableStatus) {
		String table ="";
		if(TableStatus != null  && "PAYSUCCESS".equals(TableStatus.getDishState())){
			TableStatus.setDishState("PAYSUCCESS");
			List<MealListPo> MealListPo = MealList.findByDistributor(Long.parseLong(TableStatus.getUserId()),TableStatus.getMerchantId());
			String meal = null;
			for (MealListPo mealListPo2 : MealListPo) {
				mealListPo2.setTableId(TableStatus.getId());
				MealList.saveOrUpdate(mealListPo2);
			    List<ProductPO> product = 	 conn_product.findByField("id", mealListPo2.getProductId());
			    if(meal == null){
			    	meal = product.get(0).getProductName()+"X"+mealListPo2.getMealAmount();
			    }else{
			    	meal += ","+product.get(0).getProductName()+"X"+mealListPo2.getMealAmount();
			    }
			}
			if(!"0".equals(TableStatus.getTableId()+"")){
				List<TablePO> addpo = null;
				addpo =  Table.findByField("id",TableStatus.getTableId());
				table = "用户已订桌 订桌号:"+addpo.get(0).getTableNo()+" 房间名称 : "+addpo.get(0).getTablename()+" ,用户姓名:"+TableStatus.getUserName()+" ,用户手机号:" +TableStatus.getUserPhone();
			}else{
				table = "用户未订桌,请留好桌位 用户姓名:"+TableStatus.getUserName()+" ,用户手机号:" +TableStatus.getUserPhone();
			}
			//用户推送消息
	    	Double amount=Double.parseDouble(TableStatus.getDishMoney()+"")/100;
	    	DecimalFormat df=new DecimalFormat("0.00");  
	    	UserInfoPO buyUser=conn_user.get(Long.parseLong(TableStatus.getUserId()));
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
	        	firstObj.put("value", "您的订单支付成功");
	        	firstObj.put("color", "");
	        	dataObject.put("first", firstObj);
	        	
	        	
	        	JSONObject nameObj=new JSONObject();
	        	nameObj.put("value", buyUser.getUserNickname());
	        	nameObj.put("color", "");
	        	dataObject.put("keyword1", nameObj);
	        	
	        	JSONObject accountTypeObj=new JSONObject();
	        	accountTypeObj.put("value", TableStatus.getId());
	        	accountTypeObj.put("color", "");
	        	dataObject.put("keyword2", accountTypeObj);

	        	JSONObject accountObj=new JSONObject();
	        	accountObj.put("value", df.format(amount));
	        	accountObj.put("color", "");
	        	dataObject.put("keyword3", accountObj);
	        	JSONObject timeObj=new JSONObject();
	        	timeObj.put("color", "");
	        	dataObject.put("keyword4", timeObj);
	        	JSONObject remarkObj=new JSONObject();
	        	remarkObj.put("value", "感谢使用过来玩服务");
	        	remarkObj.put("color", "");
	        	dataObject.put("remark", remarkObj);
	        	obj.put("data", dataObject);
	        	SendMsgUtil.sendTemplate(obj.toJSONString());
	    	}
	    	
	    	//商户推送消息
	    	//UserInfoPO userInfoPO=merchantPO.getUser();
	    	List<MerchantUser> merchantUsers=conn_merchantUser.findByField("merchantId", TableStatus.getMerchantId());
	    	try {
	    		for (MerchantUser merchantUser : merchantUsers) {
	        		UserInfoPO userInfoPO=conn_user.get(merchantUser.getUserId());
	        		if(userInfoPO==null){
	        			continue;
	        		}
	        		JSONObject obj=new JSONObject();
	        		obj.put("touser",userInfoPO.getUserOpenID() );
	            	obj.put("template_id", "FqlDkv8NKzKkDaWDYpY8V3t5krEXB86AeGkkJRbbws0");
	            	obj.put("url", "");
	            	JSONObject microProObj=new JSONObject();
	            	microProObj.put("appid", "");
	            	microProObj.put("pagepath", "");
	            	obj.put("miniprogram", microProObj);
	            	JSONObject dataObject=new JSONObject();
	            	JSONObject firstObj=new JSONObject();
	            	firstObj.put("value", "新的过来玩订单");
	            	firstObj.put("color", "");
	            	dataObject.put("first", firstObj);
	            	
	            	
	            	JSONObject nameObj=new JSONObject();
	            	nameObj.put("value", buyUser.getUserNickname());
	            	nameObj.put("color", "");
	            	dataObject.put("keyword1", TableStatus.getDishMoney());
	            	
	            	JSONObject accountTypeObj=new JSONObject();
	            	accountTypeObj.put("value", TableStatus.getId());
	            	accountTypeObj.put("color", "");
	            	dataObject.put("keyword2", meal);
	            	
	            	JSONObject accountObj=new JSONObject();
	            	accountObj.put("value", df.format(amount));
	            	accountObj.put("color", "");
	            	dataObject.put("keyword3", TableStatus.getTableDate() +" , "+TableStatus.getType());
	            	JSONObject timeObj=new JSONObject();
	            	timeObj.put("color", "");
	            	dataObject.put("keyword4", table);
	            	JSONObject remarkObj=new JSONObject();
	            	remarkObj.put("value", "预订");
	            	remarkObj.put("color", "");
	            	dataObject.put("remark", remarkObj);
	            	obj.put("data", dataObject);
	            	SendMsgUtil.sendTemplate(obj.toJSONString());
	    		}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	};
	
	
	
	
	
	

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
