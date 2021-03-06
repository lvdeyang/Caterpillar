package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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

	private static int pageSize=10;
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
	// 开幕式
	@ResponseBody
	@RequestMapping(value = "/getOpening", method = RequestMethod.GET)
	public ModelAndView getOpening(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("mobile/vote/start");
		return mv;
	}
	// 闭幕式
	@ResponseBody
	@RequestMapping(value = "/getclosing", method = RequestMethod.GET)
	public ModelAndView getclosing(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("mobile/vote/closing");
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
	@RequestMapping(value = "/votepoll", method = RequestMethod.POST)
	public Map<String, String> VotePoll(HttpServletRequest request,String userId, String productId,String optionId) {
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if(!isAjax){
			Map<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("msg", "err");
			return hashMap;
		}
		VoteProductPO voteProductPO = voteProductDao.getVoteProduct(Long.parseLong(productId));
		long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;
		Date startTime = new Date(zero);
		Date endTime = new Date(twelve);
		VoteOptionsPo voteOption = voteoptionDAO.get(Long.parseLong(optionId));
		try {
			Map<String, String> hashMap = new HashMap<String, String>();
			//当天的记录数量
			int count = voteImposeDao.countByUidPid(userId, productId,startTime,endTime);
			//当有记录但是已经满足当天投票总量时
			if (count >= 0 && count == voteOption.getPollnum()) {
				hashMap.put("msg", "0");
				hashMap.put("count", count+"");
				hashMap.put("pollnum", (voteOption.getPollnum()-count)+"");
				return hashMap;
			}
			//当天的记录数量为0时 或者当有记录但是没有满足当天投票总量时
			if (count==0||(count > 0 && count <= voteOption.getPollnum())) {
				VoteImposePo voteImposePo1 = new VoteImposePo();
				voteImposePo1.setUserId(userId);
				voteImposePo1.setPoll(1);
				voteImposePo1.setProductId(productId);
				voteImposeDao.save(voteImposePo1);
				voteProductPO.setAllvotes(voteProductPO.getAllvotes()+1);
				voteProductDao.saveOrUpdate(voteProductPO);
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
		mView.addObject("titleshow", voteOption.getTitleshow());
		mView.addObject("logoshow", voteOption.getLogoshow());
		mView.addObject("downpicshow", voteOption.getDownpicshow());
		mView.addObject("title", voteOption.getTitle());
		//此活动的logo
		mView.addObject("logo", "http://"+WXContants.Website+"/file"+voteOption.getSlidepic().toString());
		mView.addObject("downpic", "http://"+WXContants.Website+"/file"+voteOption.getDownpic().toString());
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
	
	
	/*// 排序商品      这方法有大问题 不用
	@ResponseBody
	@RequestMapping(value = "/sortproduct", method = RequestMethod.POST)
	public String sortproduct(HttpServletRequest request) {
		String id=request.getParameter("id");
		String optionId=request.getParameter("optionId");
		int i=1;
		//按照模块id获取投票的商品
		List<VoteProductPO> getvoteproduct = voteProductDao.getvoteproduct(Long.parseLong(id));
		if(getvoteproduct==null){
			return "err";
		}
		VoteOptionsPo voteOption = voteoptionDAO.get(Long.parseLong(optionId));
		for (VoteProductPO voteProductPO : getvoteproduct) {
			
			voteProductPO.setRanking(i);
			voteProductDao.update(voteProductPO);
			i+=1;
		}
		return "success";
	}*/

	//获得投票的商品
	@ResponseBody
	@RequestMapping(value = "/getvoteproduct", method = RequestMethod.GET)
	public List<Map<String, String>> getvoteproduct(String id,String userId,String optionId,Integer page) {
		long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;
		Date startTime = new Date(zero);
		Date endTime = new Date(twelve);
		List<VoteProductPO> getvoteproduct=new ArrayList<VoteProductPO>();
		//按照模块id获取投票的商品
		getvoteproduct = voteProductDao.getvoteproducts(Long.parseLong(id),page,pageSize);
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
			int allvotes = (int) voteProductPO.getAllvotes();
			List<JudgesVoteMsgPO> all = judgesvotemsgDAO.getByVotePId(voteProductPO.getId());
			long score=0;
			long avg=0;
			if(all!=null){
				for (JudgesVoteMsgPO judgesVoteMsgPO : all) {
					score+=judgesVoteMsgPO.getScore();
				}
				score=score/all.size();
			}
			if(score<=10){
				avg=score*10;
			}else{
				avg=score;
			}
			double allcount=(manvotes*voteOption.getPepolevote())+(ordercount*voteOption.getOrdervote())+(((manvotes*voteOption.getPepolevote())*(voteOption.getJudgesvote()*1.0/100))*(avg*1.0/100));
			double allcount1=(allvotes*voteOption.getPepolevote())+(((allvotes*voteOption.getPepolevote())*(voteOption.getJudgesvote()*1.0/100))*(avg*1.0/100));
			//封装所有的数据
			hashMap.put("avg", score+"");
			hashMap.put("count", count+"");
			hashMap.put("pollnum", (5-count)+"");
			hashMap.put("productname", voteProductPO.getProductName());
			hashMap.put("productId", productPO.getId()+"");
			hashMap.put("OutOfPrint", ordercount+"");
			hashMap.put("allvotes", (int)allcount1+"");
			hashMap.put("manvotes", manvotes+"");
			hashMap.put("ranking", voteProductPO.getRanking()+"");
			hashMap.put("productvotes", allcount+allcount1+"");
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
			long avg=0;
			if(all!=null){
				for (JudgesVoteMsgPO judgesVoteMsgPO : all) {
					score+=judgesVoteMsgPO.getScore();
				}
				score=score/all.size();
			}
			if(score<=10){
				avg=score*10;
			}else{
				avg=score;
			}
			double allcount=(manvotes*voteOption.getPepolevote())+(ordercount*voteOption.getOrdervote())+(((manvotes*voteOption.getPepolevote())*(voteOption.getJudgesvote()*1.0/100))*(avg*1.0/100));
			hashMap.put("productpic", "http://www.guolaiwan.net/file"+productPO.getProductShowPic());
			hashMap.put("productname", productPO.getProductName());
			hashMap.put("allcount", allcount+"");
			hashMap.put("manvotes", manvotes+"");
			hashMap.put("ordercount", ordercount+"");
			hashMap.put("avg", (avg/10)+"");
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
		String optionId=request.getParameter("optionId");
		long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;
		Date startTime = new Date(zero);
		Date endTime = new Date(twelve);
		//按照模块id获取投票的商品
		List<VoteProductPO> getvoteproduct = voteProductDao.getvoteproduct(name);
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
			//此user今天的购买数量
			int ordercount = voteImposeDao.buyCountByPid(voteProductPO.getProductId()+"");
			//此商品的所有群众投票数
			int productvotes = voteImposeDao.countByPid(voteProductPO.getProductId()+"");
			//此商品的所有群众投票数
			int manvotes = voteImposeDao.countByPid(voteProductPO.getProductId()+"");
			List<JudgesVoteMsgPO> all = judgesvotemsgDAO.getByVotePId(voteProductPO.getId());
			long score=0;
			long avg=0;
			if(all!=null){
				for (JudgesVoteMsgPO judgesVoteMsgPO : all) {
					score+=judgesVoteMsgPO.getScore();
				}
				score=score/all.size();
			}
			if(score<=10){
				avg=score*10;
			}else{
				avg=score;
			}
			double allcount=(manvotes*voteOption.getPepolevote())+(ordercount*voteOption.getOrdervote())+(((manvotes*voteOption.getPepolevote())*(voteOption.getJudgesvote()*1.0/100))*(avg*1.0/100));
			//封装所有的数据
			hashMap.put("avg", score+"");
			hashMap.put("count", count+"");
			hashMap.put("pollnum", (5-count)+"");
			hashMap.put("productname", voteProductPO.getProductName());
			hashMap.put("productId", productPO.getId()+"");
			hashMap.put("OutOfPrint", ordercount+"");
			hashMap.put("allvotes", (int)voteProductPO.getAllvotes()+"");
			hashMap.put("manvotes", manvotes+"");
			hashMap.put("ranking", voteProductPO.getRanking()+"");
			hashMap.put("productvotes", allcount+"");
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
	
	// Pc端页面
	@RequestMapping(value = "/gotoranking")
	public ModelAndView goToVotePC(HttpServletRequest request) {
		String optionId=request.getParameter("optionId");
		ModelAndView mv = new ModelAndView("mobile/vote/ranking");
		mv.addObject("optionId", optionId);
		return mv;
	}
}