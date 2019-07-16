package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
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
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.JudgesPo;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

@RestController
@RequestMapping("/judges")
public class JudgesController {

	@Autowired
	private UserInfoDAO conn_UserInfo;

	@Autowired
	private InvestWalletDAO conn_investWallet;

	@Autowired
	private JudgesDao judgesdao;

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

}
