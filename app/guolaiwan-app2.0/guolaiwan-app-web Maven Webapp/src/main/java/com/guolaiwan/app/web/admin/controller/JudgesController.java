package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.bussiness.admin.dao.InvestWalletDAO;
import com.guolaiwan.bussiness.admin.dao.JudgesDao;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.VoteImposeDao;
import com.guolaiwan.bussiness.admin.dao.VoteModularDAO;
import com.guolaiwan.bussiness.admin.dao.VoteOptionsDao;
import com.guolaiwan.bussiness.admin.dao.VoteProductDAO;
import com.guolaiwan.bussiness.admin.po.JudgesPo;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.VoteImposePo;
import com.guolaiwan.bussiness.admin.po.VoteModularPO;
import com.guolaiwan.bussiness.admin.po.VoteOptionsPo;
import com.guolaiwan.bussiness.admin.po.VoteProductPO;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@RestController
@RequestMapping("/judges")
public class JudgesController {

	@Autowired
	private UserInfoDAO conn_UserInfo;

	@Autowired
	private InvestWalletDAO conn_investWallet;

	@Autowired
	private JudgesDao judgesdao;

	@Autowired
	private VoteImposeDao voteImposeDao;

	@Autowired
	private VoteProductDAO voteProductDao;

	@Autowired
	private VoteProductDAO voteProductDaO;

	@Autowired
	private VoteModularDAO voteModularDaO;

	@Autowired
	private ProductDAO productDao;

	@Autowired
	private OrderInfoDAO orderInfoDAO;
	
	@Autowired
	private VoteOptionsDao voteoptionDAO;

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/getjudges", method = RequestMethod.GET)
	public ModelAndView getjudges(HttpServletRequest request) {
		String optionId=request.getParameter("optionId");
		ModelAndView mv = new ModelAndView("admin/vote/judgeslist");
		mv.addObject("optionId", optionId);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(HttpServletRequest request) throws Exception {
		String optionId=request.getParameter("optionId");
		List<UserInfoPO> list = new ArrayList<UserInfoPO>();
		List<JudgesPo> findAll = judgesdao.getByOptionId(Long.parseLong(optionId));
		for (JudgesPo judgesPo : findAll) {
			UserInfoPO _userinfo = conn_UserInfo.get(Long.parseLong(judgesPo.getUserId()));
			list.add(_userinfo);
		}
		List<UserInfoVO> _userinfo = UserInfoVO.getConverter(UserInfoVO.class).convert(list, UserInfoVO.class);
		int count = _userinfo.size();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", _userinfo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/skip.do")
	public ModelAndView merchantSkip(HttpServletRequest request) {
		String optionId=request.getParameter("optionId");
		ModelAndView mView = new ModelAndView("admin/vote/addjudges");
		mView.addObject("optionId", optionId);
		return mView;
	}

	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/userlist", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(HttpServletRequest request, int page, int limit) throws Exception {
		String userPhone = request.getParameter("userPhone");
		String nickname = request.getParameter("nickname");
		String openId = request.getParameter("openId");
		List<UserInfoPO> userinfo = conn_UserInfo.GetListbyPage(page, limit, userPhone, nickname, openId);
		List<UserInfoVO> _userinfo = UserInfoVO.getConverter(UserInfoVO.class).convert(userinfo, UserInfoVO.class);
		int count = conn_UserInfo.countByPhone(userPhone);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", _userinfo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/addjudges.do", method = RequestMethod.POST)
	public String addSubordinate(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			String optionId=request.getParameter("optionId");
			JudgesPo judgesUserId = judgesdao.getJudgesUserId(id);
			if (judgesUserId != null) {
				return "msg";
			} else {
				JudgesPo judgesPo = new JudgesPo();
				judgesPo.setUserId(id);
				judgesPo.setOptionId(Long.parseLong(optionId));
				judgesdao.save(judgesPo);
			}
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deletejudges.do", method = RequestMethod.POST)
	public String deletejudges(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			JudgesPo judgesUserId = judgesdao.getJudgesUserId(id);
			judgesdao.delete(judgesUserId.getId());
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}


	// 限制票数的方法 返回值为0的时候次数已达到5次，为1的时候可以投票，为2的时候服务器出现异常
	@RequestMapping(value = "/votepoll", method = RequestMethod.GET)
	public Map<String, String> VotePoll(String userId, String productId) {
		Date startTime = getStartTime();
		Date endTime = getEndTime();
		try {
			Map<String, String> hashMap = new HashMap<String, String>();
			//当天的记录数量
			int count = voteImposeDao.countByUidPid(userId, productId,startTime,endTime);
			System.out.println(count+"-"+userId+"-"+productId);
			//当天的记录数量为0时
			if (count == 0) {
				VoteImposePo voteImposePo1 = new VoteImposePo();
				voteImposePo1.setUserId(userId);
				voteImposePo1.setPoll(1);
				voteImposePo1.setProductId(productId);
				voteImposeDao.save(voteImposePo1);
				hashMap.put("msg", "1");
				hashMap.put("count", count+"");
				hashMap.put("pollnum", (5-count)+"");
				return hashMap;
			}
			//当有记录但是已经满足当天投票总量时
			if (count != 0 && count == 5) {
				hashMap.put("msg", "0");
				hashMap.put("count", count+"");
				hashMap.put("pollnum", (5-count)+"");
				return hashMap;
			}
			//当有记录但是没有满足当天投票总量时
			if (count != 0 && count != 5) {
				VoteImposePo voteImposePo1 = new VoteImposePo();
				voteImposePo1.setUserId(userId);
				voteImposePo1.setPoll(1);
				voteImposePo1.setProductId(productId);
				voteImposeDao.save(voteImposePo1);
				hashMap.put("msg", "1");
				hashMap.put("count", count+"");
				hashMap.put("pollnum", (5-count)+"");
				return hashMap;
			}
			hashMap.put("msg", " ");
			return hashMap;
		} catch (Exception e) {
			Map<String, String> hashMap = new HashMap<String, String>();
			e.printStackTrace();
			hashMap.put("msg", "2");
			return hashMap;
		}
	}
	
	
	/**
	 * 获得当天的0点
	 * @return 当天的0点
	 */
	private static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}
 
	/**
	 * 获得当天的23.59.59
	 * @return 当天的23.59.59
	 */
	private static Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

	// 限制购买的方法
	// 返回值为0的时候次数已达到5次，为1的时候可以投票，为2的时候服务器出现异常--------------------有点问题后面再改
	@RequestMapping(value = "/buypoll", method = RequestMethod.GET)
	public Map<String, String> buyPoll(String userId, String productId) {
		try {
			Map<String, String> hashMap = new HashMap<String, String>();
			VoteImposePo voteImposePo = voteImposeDao.getVoteImposePo(userId, productId);
			//还没有记录的情况新建一条购买量
			if (voteImposePo == null) {
				VoteImposePo voteImposePo1 = new VoteImposePo();
				voteImposePo1.setUserId(userId);
				voteImposePo1.setBuy(1);
				voteImposePo1.setProductId(productId);
				voteImposeDao.save(voteImposePo1);
			}
			if (voteImposePo != null && voteImposePo.getBuy() == 3) {
				hashMap.put("msg", "0");
				return hashMap;
			}
			if (voteImposePo != null && voteImposePo.getBuy() != 3) {
				voteImposePo.setBuy(voteImposePo.getBuy() + 1);
				voteImposeDao.update(voteImposePo);
				VoteProductPO voteProduct = voteProductDao.getVoteProduct(Long.parseLong(productId));
				voteProduct.setPeoplevotenum(voteProduct.getPeoplevotenum() + 1);
				voteProductDao.update(voteProduct);
			}
			hashMap.put("msg", "1");
			return hashMap;
		} catch (Exception e) {
			Map<String, String> hashMap = new HashMap<String, String>();
			e.printStackTrace();
			hashMap.put("msg", "2");
			return hashMap;
		}
	}

	// 跳转投票页面
	@ResponseBody
	@RequestMapping(value = "/votepage")
	public ModelAndView VotePage(HttpServletRequest request) {
		/*String optionId=request.getParameter("optionId");*/
		VoteOptionsPo voteOption = voteoptionDAO.get(Long.parseLong("5"));
		ModelAndView mView = new ModelAndView("mobile/vote/foodContest");
		mView.addObject("optionId", "5");
		//此活动的logo
		mView.addObject("logo", "http://www.guolaiwan.net/file"+voteOption.getSlidepic().toString());
		return mView;
	}

	// 获取美食大赛的选项标签卡
	@ResponseBody
	@RequestMapping(value = "/getvotemodular", method = RequestMethod.POST)
	public List<VoteModularPO> getvotemodular(HttpServletRequest request) {
		String optionId=request.getParameter("optionId");
		//通过投票活动的id查到所有的模块
		List<VoteModularPO> findAll = voteModularDaO.getByOptionId(Long.parseLong(optionId));
		return findAll;
	}

	//获得投票的商品
	@RequestMapping(value = "/getvoteproduct", method = RequestMethod.GET)
	public List<Map<String, String>> getvoteproduct(String id,String userId) {
		Date startTime = getStartTime();
		Date endTime = getEndTime();
		//按照模块id获取投票的商品
		List<VoteProductPO> getvoteproduct = voteProductDaO.getvoteproduct(Long.parseLong(id));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if(getvoteproduct==null){
			return list;
		}
		for (VoteProductPO voteProductPO : getvoteproduct) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			//通过id查到product
			ProductPO productPO = productDao.get(voteProductPO.getProductId());
			//此user今天的投票数量
			int count = voteImposeDao.countByUidPid(userId, voteProductPO.getProductId()+"",startTime,endTime);
			//此user今天的投票数量
			int ordercount = voteImposeDao.buyCountByPid(voteProductPO.getProductId()+"");
			//此商品的所有群众投票数
			int productvotes = voteImposeDao.countByPid(voteProductPO.getProductId()+"");
			//封装所有的数据
			hashMap.put("count", count+"");
			hashMap.put("pollnum", (5-count)+"");
			hashMap.put("productname", voteProductPO.getProductName());
			hashMap.put("productId", productPO.getId()+"");
			hashMap.put("OutOfPrint", ordercount+"");
			hashMap.put("productvotes", productvotes+"");
			hashMap.put("hotel", productPO.getProductMerchantName());
			hashMap.put("image", productPO.getProductShowPic());
			list.add(hashMap);
		}
		return list;
	}
	
	
	//获得投票搜索的商品
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getproductbyname")
	public List<Map<String, String>> getproductbyname(HttpServletRequest request) {
		String userId=request.getParameter("userId");
		String name=request.getParameter("name");
		System.out.println(userId+"-"+name);
		Date startTime = getStartTime();
		Date endTime = getEndTime();
		//按照模块id获取投票的商品
		List<VoteProductPO> getvoteproduct = voteProductDaO.getvoteproduct(name);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if(getvoteproduct==null){
			return list;
		}
		for (VoteProductPO voteProductPO : getvoteproduct) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			//通过id查到product
			ProductPO productPO = productDao.get(voteProductPO.getProductId());
			//此user今天的投票数量
			int count = voteImposeDao.countByUidPid(userId, voteProductPO.getProductId()+"",startTime,endTime);
			//此user今天的投票数量
			int ordercount = voteImposeDao.buyCountByPid(voteProductPO.getProductId()+"");
			//此商品的所有群众投票数
			int productvotes = voteImposeDao.countByPid(voteProductPO.getProductId()+"");
			//封装所有的数据
			hashMap.put("count", count+"");
			hashMap.put("pollnum", (5-count)+"");
			hashMap.put("productname", voteProductPO.getProductName());
			hashMap.put("productId", productPO.getId()+"");
			hashMap.put("OutOfPrint", ordercount+"");
			hashMap.put("productvotes", productvotes+"");
			hashMap.put("hotel", productPO.getProductMerchantName());
			hashMap.put("image", productPO.getProductShowPic());
			list.add(hashMap);
		}
		return list;
	}
}