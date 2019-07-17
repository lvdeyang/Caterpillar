package com.guolaiwan.app.web.business.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.coupleback.vo.CoupleBackVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.GroupBuyDAO;
import com.guolaiwan.bussiness.admin.dao.GroupTeamDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantChildrenDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductComboDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.VPRelDAO;
import com.guolaiwan.bussiness.admin.dao.VideoPicDAO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.GroupBuyPO;
import com.guolaiwan.bussiness.admin.po.GroupTeamPO;
import com.guolaiwan.bussiness.admin.po.MerchantChildrenPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.VideoPicPO;
import com.guolaiwan.bussiness.coupleback.dao.CoupleBackDao;
import com.guolaiwan.bussiness.coupleback.po.CoupleBackPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/business")
public class BusinessController extends WebBaseControll {

	@Autowired
	private MerchantDAO Mer_chant;

	@Autowired
	private SysConfigDAO conn_sys;

	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private ActivityRelDAO conn_activityRel;

	@Autowired
	private VPRelDAO conn_vPRel;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	@Autowired
	private MerchantChildrenDao merchant_Children;

	@Autowired
	private VideoPicDAO videopicDao;

	@Autowired
	private UserInfoDAO userDao;

	@Autowired
	private CoupleBackDao coupleBackDao;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private OrderInfoDAO orderInfoDao;
	@Autowired
	private GroupBuyDAO groupbuyDao;
	@Autowired
	private GroupBuyDAO conn_groupbuy;
	@Autowired
	private GroupTeamDAO groupteam;
	@Autowired
	private AddressDAO addressDao;
	@Autowired
	private ProductComboDAO conn_combo;
	@Autowired
	private ActivityRelDAO activityDao;
	@Autowired
	private CoupleBackDao couple_back;
	@Autowired
	private UserInfoDAO conn_user;
	// 南山项目单独跳转的南山首页
	@RequestMapping(value = "/merchant/nsAndView")
	public ModelAndView nsAndView(HttpServletRequest request, long merchantId, String comCode) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/home");
		mv.addObject("merchantId", merchantId);
		mv.addObject("comCode", comCode);
		return mv;
	}
   // 南山项目跳转攻略详情
	@RequestMapping(value = "/merchant/strategy")
	public ModelAndView Strategy(HttpServletRequest request, long merchantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/strategy");
		mv.addObject("merchantId", merchantId);
		return mv;
	}
	// 南山项目跳转攻略详情发布
	@RequestMapping(value = "/merchant/announce")
	public ModelAndView announce(HttpServletRequest request,long merchantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/publish");
		mv.addObject("merchantId", merchantId);
		return mv;
	}
	
	/**
	 * 南山  攻略详情页
	 */
	@ResponseBody
	@RequestMapping(value = "/selefeedback", method = RequestMethod.GET)
	public Map<String, Object> seleFeedback(HttpServletRequest request, HttpServletResponse response,String merchantId) throws Exception {
		List<CoupleBackVo> _merchants = null;
		if (merchantId != "" && merchantId !=  null ) {
			List<CoupleBackPO> coup = couple_back.getbymerchantId(Long.parseLong(merchantId));
			_merchants = CoupleBackVo.getConverter(CoupleBackVo.class).convert(coup,
					CoupleBackVo.class);
		}
		
		return success(_merchants);
	}
	/**
	 * 根据用户反馈添加数据
	 * @param request 头像  用户名
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addfeedback", method = RequestMethod.POST)
	public Map<String, Object> addFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String content = pageObject.getString("content");
		Long merchantId = pageObject.getLong("merchantId");
		UserInfoPO user = conn_user.get(userId); //获取用户头像
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format( now ); 
		CoupleBackPO coup = new CoupleBackPO();
		coup.setUserId(userId);
		coup.setHeadportrait(user.getUserHeadimg());
		coup.setUsername(user.getUserNickname());
		coup.setDate(date);
		coup.setContent(content);
		coup.setState(0);
		coup.setMerchantId(merchantId);
		couple_back.save(coup);
		return success("");
	}

	
	
	
	
	
	
	
	
	// 封装南山首页需要活动版块的数据
	@ResponseBody
	@RequestMapping(value = "/merchant/nsActivity", method = RequestMethod.GET)
	public List<HashMap> nsActivity(HttpServletRequest request, HttpServletResponse response, long id, String comCode)
			throws Exception {
		// 获取图片地址
		SysConfigPO sys = conn_sys.getSysConfig();
		List<ProductPO> list = conn_product.getactivity(id);
		List<HashMap> activityRelPOs = new ArrayList<HashMap>();
		// 南山活动页面判断活动日期是否已经过去
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		DecimalFormat df = new DecimalFormat("0.00");
		for (ProductPO productPO : list) {
			ActivityRelPO activityRelPO = conn_activityRel.getActivityRelByProductId(productPO.getId());
			String da = format.format(date);
			Date date2 = format.parse(da);
			if (activityRelPO != null) {
				int compareTo = activityRelPO.getEndDate().compareTo(date2);
				if (compareTo == 1) {
					HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
					hashMap.put("id", productPO.getId());
					hashMap.put("activityRelPO", activityRelPO);
					hashMap.put("img", productPO.getProductShowPic());
					hashMap.put("ProductPrice", df.format(Double.parseDouble(activityRelPO.getPrice() + "") / 100));
					hashMap.put("url", sys.getWebUrl());
					activityRelPOs.add(hashMap);
				}
			}
		}
		return activityRelPOs;
	}

	// 南山攻略需要的数据
	@ResponseBody
	@RequestMapping(value = "/getVideoPics", method = RequestMethod.GET)
	public List<Map<String, Object>> getVideoPics(long merchantId) throws Exception {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<VideoPicPO> _videoPics = videopicDao.getbymerchantId(merchantId);
		for (VideoPicPO nsVideoPicPO : _videoPics) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			List<UserInfoPO> usInfoPO = userDao.getUserByUid(nsVideoPicPO.getUserId());
			hashMap.put("userimg", usInfoPO.get(0).getUserHeadimg());
			hashMap.put("username", usInfoPO.get(0).getUserNickname());
			hashMap.put("id", nsVideoPicPO.getId());
			hashMap.put("textname", nsVideoPicPO.getName());
			hashMap.put("textimg", nsVideoPicPO.getHeadPic());
			hashMap.put("updatetime", nsVideoPicPO.getUpdateTime());
			String text="["+nsVideoPicPO.getContent().toString()+"]";
			List<Map> list2 = JSONObject.parseArray(text, Map.class);
			String text1=list2.get(0).get("content").toString();
			List<Map> list3 = JSONObject.parseArray(text1, Map.class);
			for (int i=0;i<list3.size();i++) {
				if(list3.get(i).get("text")!=null){
					hashMap.put("frist", list3.get(i).get("text"));
					break;
				}
			}
			list.add(hashMap);
		}
		return list;
	}

	// 南山常见问题的列表
	@ResponseBody
	@RequestMapping(value = "/getProblem", method = RequestMethod.GET)
	public List<Map<String, Object>> getProblem(long merchantId) throws Exception {
		List<CoupleBackPO> ProblemPoList = coupleBackDao.getbymerchantId(merchantId);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (CoupleBackPO problemPo : ProblemPoList) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("content", problemPo.getContent());
			hashMap.put("replycontent", problemPo.getReplycontent() == null ? "暂无回复" : problemPo.getReplycontent());
			list.add(hashMap);
		}
		return list;
	}

	// 封装猜你喜欢的数据,随机4条数据返回页面------都是属于南山公司
	@ResponseBody
	@RequestMapping(value = "/guessyoulive", method = RequestMethod.GET)
	public List<Map<String, Object>> getguessyoulive(HttpServletRequest request, HttpServletResponse response, long id)
			throws Exception {
		List<ProductPO> productlist = productDAO.findByMerchantId(id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		DecimalFormat df = new DecimalFormat("0.00");
		int[] arr = new int[4];
		for (int i = 0; i < arr.length; i++) { // 生成4个无重复的随机数
			arr[i] = (int) (productlist.size() * Math.random());
			for (int j = 0; j < i; j++) {
				if (arr[i] == arr[j]) { // 和前面的重复了
					i--; // 如果重复了，先 -- 后 ++ 相当于下标不向前移动
					break;
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("ProductName", productlist.get(arr[i]).getProductName());
			hashMap.put("ProductPrice",
					df.format(Double.parseDouble(productlist.get(arr[i]).getProductPrice() + "") / 100));
			hashMap.put("productOldPrice",
					df.format(Double.parseDouble(productlist.get(arr[i]).getProductOldPrice() + "") / 100));

			hashMap.put("url", productlist.get(arr[i]).getProductShowPic());
			hashMap.put("id", productlist.get(arr[i]).getId());
			hashMap.put("pingfen", orderInfoDao.GetCountbyPage(id)/100);
			// 根据id统计多少人来个
			List<OrderInfoPO> newgetAllOrder = orderInfoDao.newgetAllOrder(productlist.get(arr[i]).getId());

			if (newgetAllOrder == null) {
				hashMap.put("number", "0");
			} else {
				hashMap.put("number", newgetAllOrder.size());
			}

			list.add(hashMap);
		}
		return list;
	}

	// 跳转活动页面的
	@ResponseBody
	@RequestMapping(value = "/getdetermineorder", method = RequestMethod.GET)
	public ModelAndView getdetermineorder(long id) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/determineorder");
		mv.addObject("productid", id);
		return mv;
	}

	// 封装确认订单的页面
	@ResponseBody
	@RequestMapping(value = "/affirmorder", method = RequestMethod.GET)
	public Map<String, Object> affirmorder(long productid) throws Exception {
		List<ProductPO> productlist = productDAO.getProductByProId(productid);
		DecimalFormat df = new DecimalFormat("0.00");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("ProductName", productlist.get(0).getProductName());
		hashMap.put("ProductPrice", df.format(Double.parseDouble(productlist.get(0).getProductPrice() + "") / 100));
		hashMap.put("productOldPrice",
				df.format(Double.parseDouble(productlist.get(0).getProductOldPrice() + "") / 100));
		hashMap.put("url", productlist.get(0).getProductShowPic());
		return hashMap;
	}

	// 跳转商品详情页面的方法
	@ResponseBody
	@RequestMapping(value = "/productdetails", method = RequestMethod.GET)
	public ModelAndView productdetails(long id) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/commodity");
		mv.addObject("productId", id);
		return mv;
	}

	// 封装商品详情页面所需要的数据
	@ResponseBody
	@RequestMapping(value = "/getparticulars", method = RequestMethod.GET)
	public Map<String, Object> getparticulars(long productId) throws Exception {
		List<OrderInfoPO> newgetAllOrder = orderInfoDao.newgetAllOrder(productId);
		List<ProductPO> productlist = productDAO.getProductByProId(productId);
		DecimalFormat df = new DecimalFormat("0.00");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("ProductName", productlist.get(0).getProductName());
		hashMap.put("ProductPrice", df.format(Double.parseDouble(productlist.get(0).getProductPrice() + "") / 100));
		hashMap.put("productOldPrice",
				df.format(Double.parseDouble(productlist.get(0).getProductOldPrice() + "") / 100));
		hashMap.put("url", productlist.get(0).getProductShowPic());
		hashMap.put("number", newgetAllOrder.size());
		return hashMap;
	}

	// 南山首页 商家轮播
	@ResponseBody
	@RequestMapping(value = "/getPicture", method = RequestMethod.POST)
	public List<String> getShopMpic(long merchantId) throws Exception {
		List<String> list = new ArrayList<String>();
		MerchantPO Merchant = Mer_chant.getMerchantById(merchantId).get(0);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		String[] shop = Merchant.getShopMpic().split(",");
		for (String string : shop) {
			list.add(sysConfig.getWebUrl() + string);
		}
		return list;
	}


	// 跳转美食页面
	@ResponseBody
	@RequestMapping(value = "/cate", method = RequestMethod.GET)
	public ModelAndView cate(String modularCode, String merchantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/delicacy");
		mv.addObject("modularCode", modularCode);
		mv.addObject("merchantId", merchantId);
		return mv;
	}

	// 跳转拼团页面
	@ResponseBody
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public ModelAndView group(HttpServletRequest request, Long merchantId) throws Exception {
		ModelAndView mv = null;
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		mv = new ModelAndView("mobile/business/groupproduct");
		mv.addObject("merchantId", merchantId);
		mv.addObject("userId", userId);
		return mv;
	}

	// 获取这个商户的所有拼团商品
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getgroupproduct", method = RequestMethod.GET)
	public List<ProductVO> getgroupproduct(Long merchantId) throws Exception {
		List<ProductPO> productlist = productDAO.getactivity(merchantId);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(productlist, ProductVO.class);
		List<GroupBuyPO> findAll = groupbuyDao.findAll();
		for (ProductVO productVO : listvo) {
			for (GroupBuyPO GroupBuyPO : findAll) {
				if (productVO.getId() == GroupBuyPO.getProductid()) {
					productVO.setGroupnum(GroupBuyPO.getGroupnum());
					productVO.setGroupprice(GroupBuyPO.getGroupprice());
				}
			}
		}
		return listvo;
	}

	// 获取这个商品的所拼的团
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getallteam", method = RequestMethod.GET)
	public List<GroupTeamPO> getallteam(Long productId) throws Exception {
		List<GroupTeamPO> teams = groupteam.findByProductId(productId);
		return teams;
	}

	// 进入拼团的开团页面
	@ResponseBody
	@RequestMapping(value = "/grouping")
	public ModelAndView grouping(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long userId = Long.parseLong(request.getParameter("userId"));
		long productId = Long.parseLong(request.getParameter("productId"));
		mv = new ModelAndView("mobile/business/Grouping");
		GroupBuyPO groupBuyPO = conn_groupbuy.findByProductId(productId);
		ProductPO product = conn_product.get(productId);
		mv.addObject("groupBuyPO", groupBuyPO);
		mv.addObject("product", product);
		mv.addObject("userId", userId);
		return mv;
	}

	// 跳转team详情页面
	@ResponseBody
	@RequestMapping(value = "/groupteam", method = RequestMethod.GET)
	public ModelAndView cate(Long teamId) throws Exception {
		ModelAndView mv = null;
		GroupTeamPO team = groupteam.get(teamId);
		ProductPO product = conn_product.get(team.getProductid());
		GroupBuyPO groupBuyPO = conn_groupbuy.findByProductId(team.getProductid());
		mv = new ModelAndView("mobile/business/groupteam");
		mv.addObject("team", team);
		mv.addObject("product", product);
		mv.addObject("groupBuyPO", groupBuyPO);
		return mv;
	}

	// 获取这个商品的所拼的团
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getteamman")
	public List<GroupTeamPO> getTeamMan(HttpServletRequest request) throws Exception {
		Long captain = Long.parseLong(request.getParameter("captain"));
		Long teamId = Long.parseLong(request.getParameter("teamId"));
		List<GroupTeamPO> teams = groupteam.findByCaptainAndTeamId(captain, teamId);
		return teams;
	}

	// 封装南山美食页面需要的数据
	@Autowired
	private MerchantChildrenDao merchantChildrenDao;
	@Autowired
	private MerchantDAO Merchantdao;

	@ResponseBody
	@RequestMapping(value = "/getCate", method = RequestMethod.GET)
	public List<Map<String, Object>> getCate(long merchantId) throws Exception {
		String modularName = "地方美食";
		List<MerchantChildrenPO> merchantChildrenList = merchantChildrenDao.getCate(merchantId);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<MerchantPO> Merchantlist = new ArrayList<MerchantPO>();
		for (int i = 0; i < merchantChildrenList.size(); i++) {
			MerchantPO po = Merchantdao.getfood(merchantChildrenList.get(i).getChildrenId(), modularName);
			if (po != null&&(po.getModularCode().equals("0003")||po.getModularCode().equals("0003"))) {
				Merchantlist.add(po);
			}
		}
		for (int i = 0; i < Merchantlist.size(); i++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("merchantId", Merchantlist.get(i).getId());
			hashMap.put("ShopName", Merchantlist.get(i).getShopName());
			hashMap.put("ShopPic", "http://www.guolaiwan.net/file" + Merchantlist.get(i).getShopPic());
			hashMap.put("ModularClass", Merchantlist.get(i).getModularClass());
			hashMap.put("Date",  Merchantlist.get(i).getDate());
			hashMap.put("Feature",  Merchantlist.get(i).getFeature());
			list.add(hashMap);
		}
		return list;
	}
	
	//美食商户子页面
	@ResponseBody
	@RequestMapping(value = "/gotodelicacystore")
	public ModelAndView goToDelicacystore(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		mv = new ModelAndView("mobile/business/delicacystore");
		mv.addObject("merchantId", merchantId);
		return mv; 
	}
	
	// 支付完成页面
	@ResponseBody
	@RequestMapping(value = "/gotopayment")
	public ModelAndView goToPayMent(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/payment");
		String orderId=request.getParameter("orderId");
		if(orderInfoDao.get(Long.parseLong(orderId)).getProductId()!=0){
			String productClassCode = productDAO.get(orderInfoDao.get(Long.parseLong(orderId)).getProductId()).getProductClassCode();
			mv.addObject("type", productClassCode);
		}else{
			mv.addObject("type", "");
		}
		mv.addObject("orderId", orderId);
		return mv;
	}
	
	//按照商品类型商户所有的商品 
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getallproduct")
	public List<ProductVO> getAllProduct(HttpServletRequest request) throws Exception {
		int page=1;
		String type=request.getParameter("type");
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		List<ProductVO> list =new ArrayList<ProductVO>();
		List<ProductPO> allproduct = conn_product.findByProductClassCode(type, page, 100);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(allproduct, ProductVO.class);
		for (ProductVO productVO : listvo) {
			if(productVO.getProductMerchantID()==merchantId){
				list.add(productVO);
			}
		}
		return list;
	}
	
	
	//按照商品类型商户所有的商品 
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getactivityproduct")
	public List<ProductVO> getActivityProduct(HttpServletRequest request) throws Exception {
		int page=1;
		String type=request.getParameter("type");
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		List<ActivityRelPO> activityproduct = activityDao.findAll();
		List<MerchantChildrenPO> cate = merchantChildrenDao.getCate(merchantId);
		List<ProductVO> list =new ArrayList<ProductVO>();
		List<ProductPO> allproduct = conn_product.findByProductClassCode(type, page, 100);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(allproduct, ProductVO.class);
		for (ProductVO productVO : listvo) {
			for (MerchantChildrenPO cate1 : cate) {
					for (ActivityRelPO activity : activityproduct) {
						if(productVO.getId()==activity.getProductId()&&productVO.getProductMerchantID()==cate1.getId()){
							list.add(productVO);
						}
					}
				}
		}
		return list;
	}
		
		
	//按照商品类型所有的商品 
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getproductbytype")
	public List<ProductVO> getProductByType(HttpServletRequest request) throws Exception {
		String type=request.getParameter("type");
		List<ProductPO> allproduct = conn_product.findByProductClassCode(type, 1, 100);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(allproduct, ProductVO.class);
		return listvo;
	}
	
	// 去为你优选
	@ResponseBody
	@RequestMapping(value = "/gotopreferably")
	public ModelAndView goToPreferably(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		mv = new ModelAndView("mobile/business/preferably");
		mv.addObject("merchantId", merchantId);
		mv.addObject("pingfen", orderInfoDao.GetCountbyPage(merchantId)/100);
		return mv; 
	}
	
	
	//按照merchantId分页加载所有的商品 为你优选 目的 强推重要的商户商品
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getallpreferably")
	public List<ProductVO> getAllPreferably(HttpServletRequest request) throws Exception {
		int page=Integer.parseInt(request.getParameter("page"));
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		List<ProductPO> allproduct = conn_product.findByMerchant(merchantId, page, 6);
		List<ProductVO> alllist = ProductVO.getConverter(ProductVO.class).convert(allproduct, ProductVO.class);
		return alllist;
	}
	
	
	// 酒店住宿页面
	@ResponseBody
	@RequestMapping(value = "/gotoaccommodation")
	public ModelAndView goToAccommodation(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		mv = new ModelAndView("mobile/business/accommodation");
		mv.addObject("merchantId", merchantId);
		return mv; 
	}
	
	// 搜索住宿的店家
	@ResponseBody
	@RequestMapping(value = "/gotohotel")
	public ModelAndView goToHotel(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		String name=new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
		mv = new ModelAndView("mobile/business/hotel");
		mv.addObject("merchantId", merchantId);
		mv.addObject("name", name);
		return mv; 
	}
	
	//按照商品类型所有的商户 要求搜索全局的商户
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/search")
	public Map<String, Object> search(HttpServletRequest request) throws Exception {
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		String name=request.getParameter("name");
		String type=request.getParameter("type");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<MerchantPO> merchantlist=new ArrayList<MerchantPO>();
		List<Integer> pingfens=new ArrayList<Integer>(); 
		List<MerchantPO> allMerchant = Mer_chant.getAllMerchant(name);
		for (MerchantPO merchantPO : allMerchant) {
			if(merchantPO.getModularCode().equals(type)){
				merchantlist.add(merchantPO);
				pingfens.add(orderInfoDao.GetCountbyPage(merchantPO.getId())/100);
			}
		}
		//这是当前商家和子商家的信息
		/*List<MerchantChildrenPO> merchantChildren = merchant_Children.getCate(merchantId);
		for (MerchantPO Merchant : allMerchant) {
			for (MerchantChildrenPO mChildren : merchantChildren) {
				if(Merchant.getId()==mChildren.getChildrenId()&&Merchant.getModularCode().equals(type)){
					merchantlist.add(Merchant);
					pingfens.add(orderInfoDao.GetCountbyPage(mChildren.getChildrenId())/100);
				}
			}
		}*/
		List<MerchantVO> merlist = MerchantVO.getConverter(MerchantVO.class).convert(merchantlist, MerchantVO.class);
		hashMap.put("pingfens", pingfens);
		hashMap.put("merlist", merlist);
		return hashMap;
	}
	
	//按照商品类型所有的商品
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/searchproduct")
	public List<ProductVO> searchProduct(HttpServletRequest request) throws Exception {
		String name=request.getParameter("name");
		String type=request.getParameter("type");
		List<ProductVO> Productlist =new ArrayList<ProductVO>();
		List<ProductPO> Product = conn_product.productSearch(name, 1, 500);
		List<ProductVO> Pvo = ProductVO.getConverter(ProductVO.class).convert(Product, ProductVO.class);
		for (ProductVO productVO : Pvo) {
			if(productVO.getProductClassCode().equals(type)){
				Productlist.add(productVO);
			}
		}
		return Productlist;
	}
	
	//按照modularcode获得相应的商家
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getmerchant")
	public Map<String, Object> getMerchant(HttpServletRequest request) throws Exception {
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		String code=request.getParameter("code");
		List<MerchantPO> allmerchant=new ArrayList<MerchantPO>();
		List<Integer> pingfens=new ArrayList<Integer>();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<MerchantChildrenPO> merchantChildren = merchant_Children.getCate(merchantId);
		MerchantPO merchantPO = Mer_chant.get(merchantId);
		if(merchantPO.getModularCode()==code){
			allmerchant.add(merchantPO);
			pingfens.add(orderInfoDao.GetCountbyPage(merchantId)/100);
		}
		for (MerchantChildrenPO merchantChildrenPO : merchantChildren) {
			if(Mer_chant.get(merchantChildrenPO.getChildrenId()).getModularCode().equals(code)){
				allmerchant.add(Mer_chant.get(merchantChildrenPO.getChildrenId()));
				pingfens.add(orderInfoDao.GetCountbyPage(merchantChildrenPO.getChildrenId())/100);
			}
		}
		List<MerchantVO> merlist = MerchantVO.getConverter(MerchantVO.class).convert(allmerchant, MerchantVO.class);
		hashMap.put("pingfens", pingfens);
		hashMap.put("merlist", merlist);
		return hashMap;
	}
	
	//按照modularclass获得相应的商家
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getmerchant1")
	public Map<String, Object> getMerchant1(HttpServletRequest request) throws Exception {
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		String code=request.getParameter("code");
		List<MerchantPO> allmerchant=new ArrayList<MerchantPO>();
		List<Integer> pingfens=new ArrayList<Integer>();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<MerchantChildrenPO> merchantChildren = merchant_Children.getCate(merchantId);
		MerchantPO merchantPO = Mer_chant.get(merchantId);
		if(merchantPO.getModularClass()==code){
			allmerchant.add(merchantPO);
			pingfens.add(orderInfoDao.GetCountbyPage(merchantId)/100);
		}
		for (MerchantChildrenPO merchantChildrenPO : merchantChildren) {
			if(Mer_chant.get(merchantChildrenPO.getChildrenId()).getModularClass().equals(code)){
				allmerchant.add(Mer_chant.get(merchantChildrenPO.getChildrenId()));
				pingfens.add(orderInfoDao.GetCountbyPage(merchantChildrenPO.getChildrenId())/100);
			}
		}
		List<MerchantVO> merlist = MerchantVO.getConverter(MerchantVO.class).convert(allmerchant, MerchantVO.class);
		hashMap.put("pingfens", pingfens);
		hashMap.put("merlist", merlist);
		return hashMap;
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
	
	// 搜索住宿的店家
	@ResponseBody
	@RequestMapping(value = "/gotoshopdetails")
	public ModelAndView goToShopDetails(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		MerchantPO merchant = Mer_chant.get(merchantId);
		mv = new ModelAndView("mobile/business/shopdetails");
		mv.addObject("merchantId", merchantId);
		mv.addObject("merchant", merchant);
		mv.addObject("pingfen", orderInfoDao.GetCountbyPage(merchantId)/100);
		return mv; 
	}
	
	//子级商户的商品套餐
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getproduct")
	public List<ProductVO> getProduct(HttpServletRequest request) throws Exception {
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		List<ProductPO> allproduct = conn_product.findByMerchantId(merchantId);
		List<ProductVO> alllist = ProductVO.getConverter(ProductVO.class).convert(allproduct, ProductVO.class);
		return alllist;
	}
	
	//套餐购买页面
	@ResponseBody
	@RequestMapping(value = "/buyproduct")
	public ModelAndView buyProduct(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long productId=Long.parseLong(request.getParameter("productId"));
		ProductPO product = conn_product.get(productId);
		mv = new ModelAndView("mobile/business/orders");
		mv.addObject("product", product);
		return mv; 
	}
	
	//攻略列表页面
	@ResponseBody
	@RequestMapping(value = "/gotoraiders")
	public ModelAndView goToRaiders(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		mv = new ModelAndView("mobile/business/raiders");
		mv.addObject("merchantId", merchantId);
		return mv; 
	}
	
	//攻略详情页面
	@ResponseBody
	@RequestMapping(value = "/gotoraidersdetails")
	public ModelAndView goToRaidersdetails(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long id=Long.parseLong(request.getParameter("id"));
		mv = new ModelAndView("mobile/business/raidersdetails");
		mv.addObject("id", id);
		return mv; 
	}
	
	//采摘商家列表页面
	@ResponseBody
	@RequestMapping(value = "/gotopickinglist")
	public ModelAndView goToPickingList(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		mv = new ModelAndView("mobile/business/pickinglist");
		mv.addObject("merchantId", merchantId);
		return mv; 
	}
	
	//采摘商品列表页面
	@ResponseBody
	@RequestMapping(value = "/gotocomdlist")
	public ModelAndView goToComdList(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		MerchantPO merchant = Mer_chant.get(merchantId);
		mv = new ModelAndView("mobile/business/comdlist");
		mv.addObject("merchantId", merchantId);
		mv.addObject("merchant", merchant);
		mv.addObject("pingfen", orderInfoDao.GetCountbyPage(merchantId)/100);
		return mv;
	}
	
	
	// 采摘活动页面
	@ResponseBody
	@RequestMapping(value = "/gotorecommend")
	public ModelAndView goToRecommend(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		mv = new ModelAndView("mobile/business/recommend");
		mv.addObject("merchantId", merchantId);
		return mv;
	}
	
	// 采摘购买详情页面
	@ResponseBody
	@RequestMapping(value = "/gotopickingpurchase")
	public ModelAndView goToPickingPurchase(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long productId=Long.parseLong(request.getParameter("productId"));
		ProductPO product = conn_product.get(productId);
		List<ProductPO> productPO=new ArrayList<ProductPO>();
		productPO.add(product);
		List<ProductVO> alllist = ProductVO.getConverter(ProductVO.class).convert(productPO, ProductVO.class);
		mv = new ModelAndView("mobile/business/pickingpurchase");
		mv.addObject("product", alllist.get(0));
		mv.addObject("productRestrictNumber", product.getProductRestrictNumber());
		return mv;
	}
	
	
	// 采摘首页页面
	@ResponseBody
	@RequestMapping(value = "/gotopicking")
	public ModelAndView goToPicking(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		mv = new ModelAndView("mobile/business/picking");
		mv.addObject("merchantId", merchantId);
		return mv;
	}
	// 选择添加联系地址等信息页面
	@ResponseBody
	@RequestMapping(value = "/gotologistics")
	public ModelAndView goToLogistics(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long productId=Long.parseLong(request.getParameter("productId"));
		mv = new ModelAndView("mobile/business/logistics");
		mv.addObject("productId", productId);
		return mv;
	}
	
	//删除联系地址
	@ResponseBody
	@RequestMapping(value = "/deleteaddress")
	public Object deleteAddress(HttpServletRequest request) throws Exception {
		long addressId=Long.parseLong(request.getParameter("addressId"));
		addressDao.delete(addressId);
		return success();
	}
	//添加联系地址
	@ResponseBody
	@RequestMapping(value = "/appendaddress")
	public Object appendAddress(HttpServletRequest request) throws Exception {
		long userId=Long.parseLong(request.getParameter("userId"));
		String username=request.getParameter("username");
		String telephone=request.getParameter("telephone");
		String consigneeAddress=request.getParameter("consigneeAddress");
		AddressPO newAddress=new AddressPO();
		newAddress.setConsigneeAddress(consigneeAddress);
		newAddress.setConsigneePhone(telephone);
		newAddress.setUserId(userId);
		newAddress.setConsigneeName(username);
		addressDao.save(newAddress);
		return success();
	}
	//添加联系地址
	@ResponseBody
	@RequestMapping(value = "/changeaddress")
	public Object changeAddress(HttpServletRequest request) throws Exception {
		long userId=Long.parseLong(request.getParameter("userId"));
		long addressId=Long.parseLong(request.getParameter("addressId"));
		List<AddressPO> addresss = addressDao.getAddressByUserId(userId);
		for (AddressPO addressPO : addresss) {
			if(addressPO.getId()!=addressId){
				addressPO.setDefaultAddress(0);
				addressDao.saveOrUpdate(addressPO);
			}else{
				addressPO.setDefaultAddress(1);
				addressDao.saveOrUpdate(addressPO);
			}
		}
		return success();
	}
	
	//添加联系地址
	@ResponseBody
	@RequestMapping(value = "/getallcombo")
	public List<ProductComboPO> getAllCombo(HttpServletRequest request) throws Exception {
		long productId=Long.parseLong(request.getParameter("productId"));
		// 获取所有套餐
		List<ProductComboPO> comboList = conn_combo.findByField("productId", productId);
		return comboList;
	}
	
	// 商家的详情页面
	@ResponseBody
	@RequestMapping(value = "/gotobusinessdetails")
	public ModelAndView goToBusinessdetails(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		MerchantPO merchantPO = Mer_chant.get(merchantId);
		String shopIntroduction = merchantPO.getShopIntroduction();
		String shopName = merchantPO.getShopName();
		mv = new ModelAndView("mobile/business/businessdetails");
		mv.addObject("shopIntroduction", shopIntroduction);
		mv.addObject("shopName", shopName);
		return mv;
	}
	
	// 商品的详情页面
	@ResponseBody
	@RequestMapping(value = "/gotodetailspage")
	public ModelAndView goToDetailsPage(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long productId=Long.parseLong(request.getParameter("productId"));
		ProductPO product = conn_product.get(productId);
		List<ProductPO> productPO=new ArrayList<ProductPO>();
		productPO.add(product);
		List<ProductVO> alllist = ProductVO.getConverter(ProductVO.class).convert(productPO, ProductVO.class);
		mv = new ModelAndView("mobile/business/detailspage");
		mv.addObject("product", alllist.get(0));
		return mv;
	}
}