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
import com.guolaiwan.bussiness.admin.dao.JudgesVoteMsgDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.VoteImposeDao;
import com.guolaiwan.bussiness.admin.dao.VoteModularDAO;
import com.guolaiwan.bussiness.admin.dao.VoteOptionsDao;
import com.guolaiwan.bussiness.admin.dao.VoteProductDAO;
import com.guolaiwan.bussiness.admin.po.JudgesPo;
import com.guolaiwan.bussiness.admin.po.JudgesVoteMsgPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.VoteImposePo;
import com.guolaiwan.bussiness.admin.po.VoteModularPO;
import com.guolaiwan.bussiness.admin.po.VoteOptionsPo;
import com.guolaiwan.bussiness.admin.po.VoteProductPO;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.weixin.constants.WXContants;

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
	private VoteModularDAO voteModularDaO;

	@Autowired
	private ProductDAO productDao;

	@Autowired
	private OrderInfoDAO orderInfoDAO;
	
	@Autowired
	private VoteOptionsDao voteoptionDAO;

	@Autowired
	private JudgesVoteMsgDAO judgesvotemsgDAO;
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
		String userid = request.getParameter("id");
		String optionId=request.getParameter("optionId");
		try {
			UserInfoPO user = conn_UserInfo.get(Long.parseLong(userid));
			JudgesPo judgesUserId = judgesdao.getJudgesUserId(userid);
			if (judgesUserId != null) {
				return "msg";
			} else {
				JudgesPo judgesPo = new JudgesPo();
				judgesPo.setUserId(userid);
				judgesPo.setOptionId(Long.parseLong(optionId));
				judgesdao.save(judgesPo);
				List<VoteProductPO> products = voteProductDao.getByOptionId(Long.parseLong(optionId));
				System.out.println(products.size()+"-------");
				for (VoteProductPO Product : products) {
					JudgesVoteMsgPO judgesmsg=new JudgesVoteMsgPO();
					judgesmsg.setOptionId(Long.parseLong(optionId));
					judgesmsg.setProductId(Product.getProductId());
					judgesmsg.setVoteproductId(Product.getId());
					judgesmsg.setUserId(Long.parseLong(userid));
					judgesmsg.setUsername(user.getUserNickname());
					judgesvotemsgDAO.save(judgesmsg);
				}
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
			judgesvotemsgDAO.deleteByUId(Long.parseLong(id));
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}


	// 限制票数的方法 返回值为0的时候次数已达到5次，为1的时候可以投票，为2的时候服务器出现异常
	@RequestMapping(value = "/votepoll", method = RequestMethod.GET)
	public Map<String, String> VotePoll(String userId, String productId,String optionId) {
		Date startTime = getStartTime();
		Date endTime = getEndTime();
		VoteOptionsPo voteOption = voteoptionDAO.get(Long.parseLong(optionId));
		try {
			Map<String, String> hashMap = new HashMap<String, String>();
			//当天的记录数量
			int count = voteImposeDao.countByUidPid(userId, productId,startTime,endTime);
			//当有记录但是已经满足当天投票总量时
			if (count != 0 && count == voteOption.getPollnum()) {
				hashMap.put("msg", "0");
				hashMap.put("count", count+"");
				hashMap.put("pollnum", (voteOption.getPollnum()-count)+"");
				return hashMap;
			}
			//当天的记录数量为0时 或者当有记录但是没有满足当天投票总量时
			if (count==0||(count != 0 && count != voteOption.getPollnum())) {
				VoteImposePo voteImposePo1 = new VoteImposePo();
				voteImposePo1.setUserId(userId);
				voteImposePo1.setPoll(1);
				voteImposePo1.setProductId(productId);
				voteImposeDao.save(voteImposePo1);
				//将票数封装到后台展示的po中
				VoteProductPO voteProduct = voteProductDao.getVoteProduct(Long.parseLong(productId));
				voteProduct.setPeoplevotenum(count+1);
				voteProductDao.saveOrUpdate(voteProduct);
				hashMap.put("msg", "1");
				hashMap.put("count", count+"");
				hashMap.put("pollnum", (voteOption.getPollnum()-count)+"");
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
	@RequestMapping(value = "/buypoll")
	public Map<String, String> buyPoll(String userId, String productId,String optionId) {
		try {
			Map<String, String> hashMap = new HashMap<String, String>();
			VoteOptionsPo voteOption = voteoptionDAO.get(Long.parseLong(optionId));
			int count = voteImposeDao.buyCountByUidPid(userId, productId);
			long ordernum=count+1;
			if (count != 0 && count == voteOption.getOrdernum()) {
				hashMap.put("msg", "0");
				return hashMap;
			}
			if(count >=0 && count <= voteOption.getOrdernum()){
				VoteProductPO voteProductPO = voteProductDao.getVoteProduct(Long.parseLong(productId));
				voteProductPO.setOrdernum(ordernum);
				voteProductDao.saveOrUpdate(voteProductPO);
				hashMap.put("msg", "1");
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

	// 跳转投票页面
	@ResponseBody
	@RequestMapping(value = "/votepage")
	public ModelAndView VotePage(HttpServletRequest request) {
		
		//这里上线应该修改成获取的optionId 再用
		
		/*String optionId=request.getParameter("optionId");*/
		String userId =request.getSession().getAttribute("userId").toString();
		VoteOptionsPo voteOption = voteoptionDAO.get(Long.parseLong("1"));
		List<JudgesPo> all = judgesdao.getByOptionId(Long.parseLong("1"));
		ModelAndView mView = new ModelAndView("mobile/vote/foodContest");
		for (JudgesPo judgesPo : all) {
			if(judgesPo.getUserId().equals(userId)){
				mView.addObject("isjudges", "1");
			}
		}
		mView.addObject("optionId", voteOption.getId());
		mView.addObject("voterule", voteOption.getVoterule());
		mView.addObject("pollnum", voteOption.getPollnum());
		mView.addObject("buynum", voteOption.getOrdernum());
		//此活动的logo
		mView.addObject("logo", "http://"+WXContants.Website+"/file"+voteOption.getSlidepic().toString());
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
	
	
	// 排序商品
	@ResponseBody
	@RequestMapping(value = "/sortproduct", method = RequestMethod.POST)
	public String sortproduct(HttpServletRequest request) {
		String id=request.getParameter("id");
		String optionId=request.getParameter("optionId");
		//按照模块id获取投票的商品
		List<VoteProductPO> getvoteproduct = voteProductDao.getvoteproduct(Long.parseLong(id));
		if(getvoteproduct==null){
			return "err";
		}
		VoteOptionsPo voteOption = voteoptionDAO.get(Long.parseLong(optionId));
		for (VoteProductPO voteProductPO : getvoteproduct) {
			//订单数量
			int ordercount = voteImposeDao.buyCountByPid(voteProductPO.getProductId()+"");
			//此商品的所有群众投票数
			int manvotes = voteImposeDao.countByPid(voteProductPO.getProductId()+"");
			List<JudgesVoteMsgPO> all = judgesvotemsgDAO.getByVotePId(voteProductPO.getId());
			long score=0;
			if(all!=null){
				for (JudgesVoteMsgPO judgesVoteMsgPO : all) {
					score+=judgesVoteMsgPO.getScore();
				}
				score=score/all.size();
			}
			long allcount=(manvotes*voteOption.getPepolevote())+(ordercount*voteOption.getOrdervote())+(((manvotes*voteOption.getPepolevote())*(voteOption.getJudgesvote()))/100*(score));
			voteProductPO.setAllvotes(allcount);
		}
		return "success";
	}

	//获得投票的商品
	@ResponseBody
	@RequestMapping(value = "/getvoteproduct", method = RequestMethod.GET)
	public List<Map<String, String>> getvoteproduct(String id,String userId,String optionId) {
		Date startTime = getStartTime();
		Date endTime = getEndTime();
		//按照模块id获取投票的商品
		List<VoteProductPO> getvoteproduct = voteProductDao.getvoteproduct(Long.parseLong(id));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if(getvoteproduct==null){
			return list;
		}
		VoteOptionsPo voteOption = voteoptionDAO.get(Long.parseLong(optionId));
		for (VoteProductPO voteProductPO : getvoteproduct) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			//通过id查到product
			ProductPO productPO = productDao.get(voteProductPO.getProductId());
			//此user今天的投票数量
			int count = voteImposeDao.countByUidPid(userId, voteProductPO.getProductId()+"",startTime,endTime);
			//订单数量
			int ordercount = voteImposeDao.buyCountByPid(voteProductPO.getProductId()+"");
			//此商品的所有群众投票数
			int manvotes = voteImposeDao.countByPid(voteProductPO.getProductId()+"");
			List<JudgesVoteMsgPO> all = judgesvotemsgDAO.getByVotePId(voteProductPO.getId());
			long score=0;
			if(all!=null){
				for (JudgesVoteMsgPO judgesVoteMsgPO : all) {
					score+=judgesVoteMsgPO.getScore();
				}
				score=score/all.size();
			}
			long allcount=(manvotes*voteOption.getPepolevote())+(ordercount*voteOption.getOrdervote())+(((manvotes*voteOption.getPepolevote())*(voteOption.getJudgesvote()))/100*(score));
			//封装所有的数据
			hashMap.put("avg", score+"");
			hashMap.put("count", count+"");
			hashMap.put("pollnum", (5-count)+"");
			hashMap.put("productname", voteProductPO.getProductName());
			hashMap.put("productId", productPO.getId()+"");
			hashMap.put("OutOfPrint", ordercount+"");
			hashMap.put("manvotes", manvotes+"");
			hashMap.put("productvotes", allcount+"");
			hashMap.put("hotel", productPO.getProductMerchantName());
			hashMap.put("image", productPO.getProductShowPic());
			list.add(hashMap);
		}
		return list;
	}
	
	
	
	
	//获得投票的商品
	@RequestMapping(value = "/getvoteproductpc", method = RequestMethod.GET)
	public List<Map<String, String>> getvoteproductpc(String id,String optionId) {
		//按照模块id获取投票的商品
		List<VoteProductPO> getvoteproduct = voteProductDao.getvoteproduct(Long.parseLong(id));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if(getvoteproduct==null){
			return list;
		}
		VoteOptionsPo voteOption = voteoptionDAO.get(Long.parseLong(optionId));
		for (VoteProductPO voteProductPO : getvoteproduct) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			//通过id查到product
			ProductPO productPO = productDao.get(voteProductPO.getProductId());
			//订单数量
			int ordercount = voteImposeDao.buyCountByPid(voteProductPO.getProductId()+"");
			//此商品的所有群众投票数
			int manvotes = voteImposeDao.countByPid(voteProductPO.getProductId()+"");
			List<JudgesVoteMsgPO> all = judgesvotemsgDAO.getByVotePId(voteProductPO.getId());
			long score=0;
			if(all!=null){
				for (JudgesVoteMsgPO judgesVoteMsgPO : all) {
					score+=judgesVoteMsgPO.getScore();
				}
				score=score/all.size();
			}

			long allcount=(manvotes*voteOption.getPepolevote())+(ordercount*voteOption.getOrdervote())+(((manvotes*voteOption.getPepolevote())*(voteOption.getJudgesvote()))/100*(score));
			hashMap.put("productpic", "http://www.guolaiwan.net/file"+productPO.getProductShowPic());
			hashMap.put("productname", productPO.getProductName());
			hashMap.put("allcount", allcount+"");
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
		Date startTime = getStartTime();
		Date endTime = getEndTime();
		//按照模块id获取投票的商品
		List<VoteProductPO> getvoteproduct = voteProductDao.getvoteproduct(name);
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
	
	// Pc端评委页面
	@ResponseBody
	@RequestMapping(value = "/getoneproduct")
	public ProductPO getoneproduct(HttpServletRequest request) {
		String productId=request.getParameter("productId");
		ProductPO productPO = productDao.get(Long.parseLong(productId));
		return productPO;
	}
	
	// 评委评分的方法
	@ResponseBody
	@RequestMapping(value = "/makescore")
	public String makeScore(HttpServletRequest request) {
		String productId=request.getParameter("productId");
		String userId=request.getParameter("userId");
		String score=request.getParameter("score");
		JudgesVoteMsgPO judges = judgesvotemsgDAO.getByUIdPId(Long.parseLong(userId), Long.parseLong(productId));
		judges.setScore(Long.parseLong(score));
		judgesvotemsgDAO.saveOrUpdate(judges);
		return "success";
	}
}