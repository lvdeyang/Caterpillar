package com.guolaiwan.app.web.business.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.CollectionDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.GroupBuyDAO;
import com.guolaiwan.bussiness.admin.dao.GroupTeamDAO;
import com.guolaiwan.bussiness.admin.dao.InvestWalletDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantUserDao;
import com.guolaiwan.bussiness.admin.dao.MessageDAO;
import com.guolaiwan.bussiness.admin.dao.OlChatMessageDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.SystemCacheDao;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.VPRelDAO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.GroupBuyPO;
import com.guolaiwan.bussiness.admin.po.GroupTeamPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.nanshan.dao.NsVideoPicDAO;
import com.guolaiwan.bussiness.nanshan.dao.ProblemDao;
import com.guolaiwan.bussiness.nanshan.po.NsVideoPicPO;
import com.guolaiwan.bussiness.nanshan.po.ProblemPo;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/business")
public class BusinessController extends WebBaseControll {

	@Autowired
	private SystemCacheDao conn_systemcache;

	@Autowired
	private InvestWalletDAO conn_investwallet;

	@Autowired
	private OlChatMessageDAO conn_olchatmessage;

	@Autowired
	private MessageDAO messagedao;

	@Autowired
	private UserInfoDAO conn_user;

	@Autowired
	private CompanyDAO conn_com;

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private SysConfigDAO conn_sys;

	@Autowired
	private MerchantUserDao conn_merchantuser;

	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private CollectionDAO conn_collection;

	@Autowired
	private ActivityRelDAO conn_activityRel;

	@Autowired
	private VPRelDAO conn_vPRel;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	@Autowired
	private NsVideoPicDAO nsvideopicDao;

	@Autowired
	private UserInfoDAO userDao;

	@Autowired
	private ProblemDao problemDao;

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

	// 南山项目单独跳转的南山首页
	@RequestMapping(value = "/merchant/nsAndView")
	public ModelAndView nsAndView(HttpServletRequest request, long merchantId, String comCode) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/home");
		mv.addObject("merchantId", merchantId);
		mv.addObject("comCode", comCode);
		return mv;
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
					hashMap.put("ProductPrice", df.format(Double.parseDouble(productPO.getProductPrice() + "") / 100));
					hashMap.put("productOldPrice",
							df.format(Double.parseDouble(productPO.getProductOldPrice() + "") / 100));
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
	public List<Map<String, Object>> getVideoPics() throws Exception {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<NsVideoPicPO> _videoPics = nsvideopicDao.findAll();
		for (NsVideoPicPO nsVideoPicPO : _videoPics) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			List<UserInfoPO> usInfoPO = userDao.getUserByUid(nsVideoPicPO.getUserId());
			hashMap.put("userimg", usInfoPO.get(0).getUserHeadimg());
			hashMap.put("username", usInfoPO.get(0).getUserNickname());
			hashMap.put("textname", nsVideoPicPO.getName());
			hashMap.put("textimg", nsVideoPicPO.getHeadPic());
			list.add(hashMap);
		}
		return list;
	}

	// 南山常见问题的列表
	@ResponseBody
	@RequestMapping(value = "/getProblem", method = RequestMethod.GET)
	public List<Map<String, Object>> getProblem() throws Exception {
		List<ProblemPo> ProblemPoList = problemDao.findAll();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (ProblemPo problemPo : ProblemPoList) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("content", problemPo.getContent());
			hashMap.put("replycontent", problemPo.getReplycontent());
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

	// 跳转美食页面
	@ResponseBody
	@RequestMapping(value = "/cate", method = RequestMethod.GET)
	public ModelAndView cate(String modularCode) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/delicacy");
		mv.addObject("modularCode", modularCode);
		return mv;
	}
	
	// 跳转拼团页面
	@ResponseBody
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public ModelAndView group(HttpServletRequest request,Long merchantId) throws Exception {
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
				if(productVO.getId()==GroupBuyPO.getProductid()){
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

	//进入拼团的开团页面
	@ResponseBody
	@RequestMapping(value = "/grouping")
	public ModelAndView grouping(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		long userId = Long.parseLong(request.getParameter("userId"));
		long productId = Long.parseLong(request.getParameter("productId"));
		mv = new ModelAndView("mobile/business/Grouping");
		GroupBuyPO groupBuyPO = conn_groupbuy.findByProductId(productId);
		ProductPO product = conn_product.get(productId);
		GroupTeamPO team = groupteam.findByUserId(userId);
		mv.addObject("groupBuyPO", groupBuyPO);
		mv.addObject("product", product);
		mv.addObject("userId", userId);
		mv.addObject("team", team);
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
	
}