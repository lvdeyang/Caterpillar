package com.guolaiwan.app.web.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
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
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.VoteImposeDao;
import com.guolaiwan.bussiness.admin.po.JudgesPo;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.VoteImposePo;

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

	// 添加数据页面
	@RequestMapping(value = "/getjudges", method = RequestMethod.GET)
	public ModelAndView getjudges(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/vote/judgeslist");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList() throws Exception {

		List<UserInfoPO> list = new ArrayList<UserInfoPO>();
		List<JudgesPo> findAll = judgesdao.findAll();
		for (JudgesPo judgesPo : findAll) {
			UserInfoPO _userinfo = conn_UserInfo.get(Long.parseLong(judgesPo.getUserId()));
			list.add(_userinfo);
		}
		List<UserInfoVO> _userinfo = UserInfoVO.getConverter(UserInfoVO.class).convert(list, UserInfoVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", _userinfo);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/skip.do")
	public ModelAndView merchantSkip(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView("admin/vote/addjudges");
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
			JudgesPo judgesUserId = judgesdao.getJudgesUserId(id);
			if (judgesUserId != null) {
				return "msg";
			} else {
				JudgesPo judgesPo = new JudgesPo();
				judgesPo.setUserId(id);
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

	// 定时方法，tomcat启动开始定时没天0点清除数据库
	// Bean被容器初始化之后，会调用@PostConstruct的注解方法
	@PostConstruct
	public void VoteTiming() {
		// TODO Auto-generated method stub
		// 一天的毫秒数
		long daySpan = 60 * 1000;

		// 规定的每天时间15:33:30运行
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '15:52:00'");
		// 首次运行时间
		Date startTime = null;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));

			// 如果今天的已经过了 首次运行时间就改为明天
			if (System.currentTimeMillis() > startTime.getTime())
				startTime = new Date(startTime.getTime() + daySpan);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timer t = new Timer();

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// 要执行的代码
				List<VoteImposePo> findAll = voteImposeDao.findAll();
				for (VoteImposePo voteImposePo : findAll) {
					voteImposePo.setPoll(0);
					voteImposePo.setBuy(0);
					voteImposeDao.update(voteImposePo);
				}
			}
		};

		// 以每24小时执行一次
		t.scheduleAtFixedRate(task, startTime, daySpan);
	}

	// 限制票数的方法 返回值为0的时候次数已达到5次，为1的时候可以投票，为2的时候服务器出现异常
	@RequestMapping(value = "/votepoll", method = RequestMethod.GET)
	public Map<String, String> VotePoll(String userId, String productId) {

		try {
			Map<String, String> hashMap = new HashMap<String, String>();
			VoteImposePo voteImposePo = voteImposeDao.getVoteImposePo(userId, productId);
			if (voteImposePo == null) {
				VoteImposePo voteImposePo1 = new VoteImposePo();
				voteImposePo1.setUserId(userId);
				voteImposePo1.setPoll(1);
				voteImposePo1.setProductId(productId);
				voteImposeDao.save(voteImposePo1);
			}
			if (voteImposePo != null && voteImposePo.getPoll() == 5) {
				hashMap.put("msg", "0");
				return hashMap;
			}

			if (voteImposePo != null && voteImposePo.getPoll() != 5) {
				voteImposePo.setPoll(voteImposePo.getPoll() + 1);
				voteImposeDao.update(voteImposePo);
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

	// 限制购买的方法 返回值为0的时候次数已达到5次，为1的时候可以投票，为2的时候服务器出现异常
	@RequestMapping(value = "/buypoll", method = RequestMethod.GET)
	public Map<String, String> buyPoll(String userId, String productId) {

		try {
			Map<String, String> hashMap = new HashMap<String, String>();
			VoteImposePo voteImposePo = voteImposeDao.getVoteImposePo(userId, productId);
			if (voteImposePo == null) {
				VoteImposePo voteImposePo1 = new VoteImposePo();
				voteImposePo1.setUserId(userId);
				voteImposePo1.setBuy(1);
				voteImposePo1.setProductId(productId);
				voteImposeDao.save(voteImposePo1);
			}
			if (voteImposePo != null && voteImposePo.getPoll() == 3) {
				hashMap.put("msg", "0");
				return hashMap;
			}

			if (voteImposePo != null && voteImposePo.getPoll() != 3) {
				voteImposePo.setBuy(voteImposePo.getBuy() + 1);
				voteImposeDao.update(voteImposePo);
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

}