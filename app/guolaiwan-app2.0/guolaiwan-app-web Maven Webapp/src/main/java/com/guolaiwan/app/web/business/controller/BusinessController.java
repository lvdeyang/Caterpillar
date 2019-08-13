package com.guolaiwan.app.web.business.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.bytedeco.javacpp.RealSense.intrinsics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.interfac.alipay.AliAppOrderInfo;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.coupleback.vo.CoupleBackVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.weixin.WxConfig;
import com.guolaiwan.app.web.weixin.YuebaWxPayConstants;
import com.guolaiwan.app.web.weixin.YuebaWxUtil;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.AddTheRoomDAO;
import com.guolaiwan.bussiness.admin.dao.BundleOrderDAO;
import com.guolaiwan.bussiness.admin.dao.GroupBuyDAO;
import com.guolaiwan.bussiness.admin.dao.GroupTeamDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantChildrenDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductComboDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.VPCommentDAO;
import com.guolaiwan.bussiness.admin.dao.VPRelDAO;
import com.guolaiwan.bussiness.admin.dao.VideoPicDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.AddTheRoomPO;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.GroupBuyPO;
import com.guolaiwan.bussiness.admin.po.GroupTeamPO;
import com.guolaiwan.bussiness.admin.po.MerchantChildrenPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.UserOneDayBuyPO;
import com.guolaiwan.bussiness.admin.po.VPRelPO;
import com.guolaiwan.bussiness.admin.po.VideoPicPO;
import com.guolaiwan.bussiness.coupleback.dao.CoupleBackDao;
import com.guolaiwan.bussiness.coupleback.po.CoupleBackPO;
import com.guolaiwan.bussiness.nanshan.dao.CurrentRoomSateDao;
import com.guolaiwan.bussiness.nanshan.dao.MessageMiddleClientDao;
import com.guolaiwan.bussiness.nanshan.po.CurrentRoomSatePO;
import com.guolaiwan.bussiness.nanshan.po.MessageMiddleClientPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPayApp;
import pub.caterpillar.weixin.wxpay.WXPayUtil;
import pub.caterpillar.weixin.wxpay.WXPayConstants.SignType;

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
	private VPCommentDAO conn_vPComment;
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
	@Autowired
	private VideoPicDAO conn_videoPic;
	@Autowired
	private AddTheRoomDAO conn_roomdao;
	@Autowired
	private CurrentRoomSateDao conn_roomSateDao; 
	
	 @Autowired
	 private MessageMiddleClientDao conn_mesMidleClien;
	 
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
	public List<Map<String, Object>> getVideoPics(long merchantId,HttpServletRequest request) throws Exception {
		Long userId = 	(Long) request.getSession().getAttribute("userId");
		System.out.println(userId +" ---------------------------");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<VideoPicPO> _videoPics = videopicDao.getbymerchantId(merchantId);
		for (VideoPicPO nsVideoPicPO : _videoPics) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			List<UserInfoPO> usInfoPO = userDao.getUserByUid(nsVideoPicPO.getUserId());
			int  pcomment  = conn_vPComment.countByField("videoPic",nsVideoPicPO);       //评论数
		    int   Praise  =	conn_vPRel.countPraise(nsVideoPicPO);
		   // 点赞数
		 	VPRelPO countPraise = conn_vPRel.getPraiseByVU(nsVideoPicPO,userId);
		 	if(countPraise != null){ //判断图片颜色
		 		hashMap.put("picture",1);
		 	}
		 	else{
		 		hashMap.put("picture",0 );
		 	}
			hashMap.put("videoPic",Praise );
			hashMap.put("pcomment", pcomment);
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
			hashMap.put("productModularCode", productlist.get(arr[i]).getProductModularCode());
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
		if(merchantId!=null&&merchantId!=0){
			mv.addObject("merchantId", merchantId);
		}
		mv.addObject("userId", userId);
		return mv;
	}

	// 获取这个商户的所有拼团商品
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getgroupproduct", method = RequestMethod.GET)
	public List<ProductVO> getgroupproduct(Long merchantId) throws Exception {
		List<GroupBuyPO> findAll = groupbuyDao.findAll();
		
		if(merchantId!=null&&merchantId!=0){
			System.out.println("子级的商品");
			List<MerchantChildrenPO> merchantChildren = merchant_Children.getCate(merchantId);
			List<ProductPO> productlist = productDAO.getactivity(merchantId);
			for (MerchantChildrenPO Children : merchantChildren) {
				List<ProductPO> getactivity = productDAO.getactivity(Children.getChildrenId());
				for (ProductPO productPO : getactivity) {
					productlist.add(productPO);
				}
			}
			List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(productlist, ProductVO.class);
			for (ProductVO productVO : listvo) {
				for (GroupBuyPO GroupBuyPO : findAll) {
					if (productVO.getId() == GroupBuyPO.getProductid()) {
						productVO.setGroupnum(GroupBuyPO.getGroupnum());
						productVO.setGroupprice(GroupBuyPO.getGroupprice());
					}
				}
			}
			return listvo;
		}else{
			System.out.println("全局的商品");
			List<ProductPO> groupProduct = productDAO.getGroupProduct();
			List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(groupProduct, ProductVO.class);
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
			hashMap.put("feature", Merchantlist.get(i).getFeature());
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
			mv.addObject("type", "006");
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
	public ModelAndView buyProduct(HttpServletRequest request,String inRoomDate,String outRoomDate) throws Exception {
		ModelAndView mv = null;
		String roomId=request.getParameter("roomId");
		AddTheRoomPO Room = conn_roomdao.get(Long.parseLong(roomId));
		MerchantPO  merchant = Mer_chant.get(Long.parseLong(Room.getMerchantId()));
		HttpSession  session  =  request.getSession();
		 long userId =(long)session.getAttribute("userId");
		mv = new ModelAndView("mobile/business/orders");
		mv.addObject("inRoomDate",inRoomDate);
		mv.addObject("userId",userId);
		mv.addObject("outRoomDate",outRoomDate);
		mv.addObject("merchant",merchant);
		mv.addObject("room", Room);		
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
	
	//攻略列表页面
	@ResponseBody
	@RequestMapping(value = "/gotoprocess")
	public ModelAndView goToProcess(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/process");
		return mv; 
	}
	
	//进入选房页面
	@ResponseBody
	@RequestMapping(value = "/gotolect")
	public ModelAndView goToLect(HttpServletRequest request) throws Exception {
		String merchantId=request.getParameter("merchantId");
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/lect");
		mv.addObject("merchantId", merchantId);
		return mv; 
	}
	
	//获取所有的房间 按照层数 商家
	@ResponseBody
	@RequestMapping(value = "/getallroom")
	public Map<String,Object> getAllRoom(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		//存储房间状态
		List<String> roomState = new ArrayList<String>();
		//获取查询信息
		String tier = request.getParameter("tier");
		String merchantId = request.getParameter("merchantId");
		//检索房间的时间
		String inRoomDate = request.getParameter("inRoomDate");
		String outRoomDate = request.getParameter("outRoomDate");
		//时间格式转换
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
		long sdf_inRoomDate = sdf.parse(inRoomDate).getTime();
		long sdf_outRoomDate = sdf.parse(outRoomDate).getTime();
        //检索房间类型选项		
		String identity = request.getParameter("identity");		
		//判断房间类型  全部 还是指定类型
		List<AddTheRoomPO> aRoomPOs = new ArrayList<AddTheRoomPO>();
		if("全部".equals(identity)){
			String[] room_fields = {"merchantId","tier"};
			Object[] room_values = {merchantId,tier};
			 aRoomPOs = conn_roomdao.findByFields(room_fields, room_values);			
		}else{			
			 aRoomPOs = conn_roomdao.findByMidTier(merchantId,tier,identity);			
		}	
		//判断房间的状态信息
	    String state = null;
	    boolean oneReal = false;
	  	boolean twoReal = false;
		if(aRoomPOs.size()>0){
			for(AddTheRoomPO po : aRoomPOs){
		     if( 0 == po.getState()){
		    	 state = "0";//下架状态
		     }else{
		      //查询房间状态表	 
		      List<CurrentRoomSatePO> cRoomSatePOs	= conn_roomSateDao.findByRoomId(po.getId());
		  	for(CurrentRoomSatePO cRoomSatePO : cRoomSatePOs){
				//上架状态 				
			    try{			   	
			    //获取日期			    
			    long beginTime = sdf.parse(cRoomSatePO.getInRoomDate()).getTime();
			    long endTime = sdf.parse(cRoomSatePO.getOutRoomDate()).getTime();
			    	if(beginTime>=sdf_inRoomDate && beginTime<sdf_outRoomDate){	
			    		if("1".equals(cRoomSatePO.getRoomState())){			    			
			    			 oneReal = true;
			    		}		    						    						    			
			    	}else if(endTime>sdf_inRoomDate && endTime <sdf_outRoomDate){					    	  
			    		if("1".equals(cRoomSatePO.getRoomState())){			    			
			    			twoReal = true;
			    		}							    	
					}					    					    					    	     			    
			     }
			    //房间时间为空
			    catch(Exception e){
			    	
			    }			 			  
			 }
			   //根据判断结果返回状态	
			   if(oneReal || twoReal){
				   state = "2";//已售状态
			   }else{
				   state = "1";//空闲状态  
			   }	
		     }	
		     roomState.add(state);
		  }						
		}		
        map.put("room", aRoomPOs);
        map.put("roomState", roomState);
	    
	    return map;
	}
	
	
	//进入房间详情页面
	@ResponseBody
	@RequestMapping(value = "/gotoroomdetails")
	public ModelAndView goToRoomDetails(HttpServletRequest request,String inRoomDate,String outRoomDate) throws Exception {
		String roomId=request.getParameter("roomId");
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/roomdetails");
		mv.addObject("inRoomDate",inRoomDate);
		mv.addObject("outRoomDate",outRoomDate);
		mv.addObject("roomId", roomId);
		return mv; 
	}
	
	//获取所有的房间 按照层数 商家
	@ResponseBody
	@RequestMapping(value = "/gettheroom")
	public AddTheRoomPO getTheRoom(HttpServletRequest request) throws Exception {
		String roomId=request.getParameter("roomId");
		AddTheRoomPO room = conn_roomdao.get(Long.parseLong(roomId));
		return room;
	}
	
	/**
	 * 
	 * 检索房间购买前的状态
	 * @throws ParseException 
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/search.do")
	public String  searchingRoomState(HttpServletRequest request,Long roomId) throws ParseException{
		//检索房间的时间
		String inRoomDate = request.getParameter("inRoomDate");
		String outRoomDate = request.getParameter("outRoomDate");
	   //时间格式转换
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
		long sdf_inRoomDate = sdf.parse(inRoomDate).getTime();
		long sdf_outRoomDate = sdf.parse(outRoomDate).getTime();
		//判断房间是否下架
		 boolean oneReal = false;
		 boolean twoReal = false;
		AddTheRoomPO aRoomPO = conn_roomdao.get(roomId);
		if(0 == aRoomPO.getState()){			
			return "false";			
		}else{
			  //查询房间状态表	 
		      List<CurrentRoomSatePO> cRoomSatePOs	= conn_roomSateDao.findByRoomId(aRoomPO.getId());
		  	for(CurrentRoomSatePO cRoomSatePO : cRoomSatePOs){
				//上架状态 				
			    try{			   	
			    //获取日期			    
			    long beginTime = sdf.parse(cRoomSatePO.getInRoomDate()).getTime();
			    long endTime = sdf.parse(cRoomSatePO.getOutRoomDate()).getTime();
			    	if(beginTime>=sdf_inRoomDate && beginTime<sdf_outRoomDate){	
			    		if("1".equals(cRoomSatePO.getRoomState())){			    			
			    			 oneReal = true;
			    		}		    						    						    			
			    	}else if(endTime>sdf_inRoomDate && endTime <sdf_outRoomDate){					    	  
			    		if("1".equals(cRoomSatePO.getRoomState())){			    			
			    			twoReal = true;
			    		}							    	
					}					    					    					    	     			    
			     }
			    //房间时间为空
			    catch(Exception e){
			    	
			    }			 			  
			 }
			   //根据判断结果返回状态	
			   if(oneReal || twoReal){
				   return "false";
			   }else{
				   return "success";
			   }			
		}
	}
	
	  
	
	/**
	 * 住宿信息保存
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/addMessage")
	public String addRoomClientInfo(HttpServletRequest request){
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		 String oderId = pageObject.getString("oderId");
		 String  clientLists = pageObject.getString("clientList");
		 JSONArray clientList =  pageObject.parseArray(clientLists);
		 String  roomId = pageObject.getString("proId");
		 String  userId =  pageObject.getString("userId");
		 String merchantId = pageObject.getString("merchantId");
		 String startDate = pageObject.getString("bookstartDate");
		 String endDate = pageObject.getString("bookendDate");
		 //遍历保存用户信息
		 for(int i = 0;i<clientList.size();i++){
			 long clientMessage = clientList.getLong(i);		 			 
			 MessageMiddleClientPO mesMidClien = new MessageMiddleClientPO();
			 mesMidClien.setMerchantId(Long.parseLong(merchantId));
			 mesMidClien.setMessageId(clientMessage);			
			 mesMidClien.setOrderId(Long.parseLong(oderId));
			 mesMidClien.setUserId(Long.parseLong(userId));
			 mesMidClien.setRoomId(Long.parseLong(roomId));
			 mesMidClien.setPayState("0");
			 mesMidClien.setStartDate(startDate);
			 mesMidClien.setEndDate(endDate);
			 conn_mesMidleClien.save(mesMidClien);
			 }			
		return "success";												
	}
	
	
	/**
	 * 订单：立即支付
	 * 
	 * @param request
	 * @param response
	 * @returncahung
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addRoomOrder", method = RequestMethod.POST)
	public Map<String, Object> addOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		
		String roomId = pageObject.getString("id");
		Long userId = Long.parseLong(pageObject.getString("userId"));
		String paytype = pageObject.getString("paytype");				
		String numberDays = pageObject.getString("numberDays");
				
		
		OrderInfoPO order = new OrderInfoPO();	
		//支付的金额
		
		AddTheRoomPO aTheRoomPO	= conn_roomdao.get(Long.parseLong(roomId));
		long roomPrice = aTheRoomPO.getPrice();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		
		// 支付金额		
		long payMoney = Integer.parseInt(numberDays) * roomPrice;
		// 订单总金额
		long orderAllMoney = payMoney;

		// 获取商家
		MerchantPO merchant = Mer_chant.get(Long.parseLong(aTheRoomPO.getMerchantId()));		
		UserInfoPO user = conn_user.get(userId);
		
		String orderStartDate = pageObject.getString("bookstartDate");
		String orderEndDate = pageObject.getString("bookendDate");
				
		if (orderStartDate != null && orderStartDate != "" && orderStartDate.length() != 0) {
			order.setOrderBookDate(DateUtil.parse(orderStartDate, DateUtil.defaultDatePattern));
		}
		if (orderEndDate != null && orderEndDate != "" && orderEndDate.length() != 0) {
			order.setEndBookDate(DateUtil.parse(orderEndDate, DateUtil.defaultDatePattern));
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

		// 订单号（商家id+板块Code+时间戳+用户ID）
		String orderNO = merchant.getId() + merchant.getModularCode() + df.format(date)
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
		// 房间ID
		order.setRoomId(Long.parseLong(roomId));
		// 商品图片
		order.setProductPic(aTheRoomPO.getRoomimg());
		// 商品名称
		order.setProductName(aTheRoomPO.getName());
		// 商品数量
		order.setProductNum(Long.parseLong(numberDays));
		
		// 商品单价
		order.setProductPrice((long)aTheRoomPO.getPrice());
		// 所属板块DI
		order.setBkCode(merchant.getModularCode());
		// 所属板块名称
		order.setBkName(merchant.getModularName());
										
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
		 order.setOrderBookDate(date);

		if (pageObject.getString("source") != null) {
			order.setSource(OrderSource.fromString(pageObject.getString("source")));
		} else {
			// 订单来源
			order.setSource(OrderSource.APP);
		}
		order.setOrderType(OrderType.MERCHANT);
				
		orderInfoDao.saveOrUpdate(order);
								
		long PayMoney = order.getPayMoney();
		String tradeNum=order.getOrderNO(); 
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
      /**
       * 微信统一下单
       * 
       * */
		public Map<String, String> weichatPay(long PayMoney, String tradeNum) {
			Map<String, String> reqData = new HashMap<String, String>();
			Map<String, String> resData = null;
			try {
				GuolaiwanWxPayApp wxPay = GuolaiwanWxPayApp
						.getInstance("http://" + WXContants.Website + "/website/wxreport/payroomPrice");
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
		
		@Autowired
		private BundleOrderDAO conn_bundleOrder;
		
		/**
		 * 微信付款
		 * 
		 * */
		
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
					OrderInfoPO orderInfoPO = orderInfoDao.get(Long.parseLong(string));
					payMoney += orderInfoPO.getPayMoney();
				}
			} else {
				OrderInfoPO orderInfoPO = orderInfoDao.get(Long.parseLong(id));
				payMoney += orderInfoPO.getPayMoney();
			}

			Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
			UserInfoPO user = conn_user.get(userId);
			YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/payroomPrice", WxConfig.appId,
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
	
		
}