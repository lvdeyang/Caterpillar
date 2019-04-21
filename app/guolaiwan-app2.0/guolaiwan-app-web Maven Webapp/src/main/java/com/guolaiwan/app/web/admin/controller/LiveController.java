package com.guolaiwan.app.web.admin.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.publicnum.vo.LiveMessageVo;
import com.guolaiwan.bussiness.admin.dao.LiveDAO;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.enumeration.CompanyType;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.LiveType;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.MenuPO;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/live")
public class LiveController extends BaseController {

	@Autowired
	private LiveDAO conn_live;
	@Autowired
	private LiveMessageDAO conn_liveMessage;

	// 列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		ModelAndView mv = new ModelAndView("admin/live/list", strMap);
		return mv;
	}
	/*
	 * //直播评论删除页面
	 * 
	 * @RequestMapping(value="/delect.do",method= RequestMethod.GET) public
	 * ModelAndView delect(HttpServletRequest request) throws Exception{
	 * Map<String, Object> strMap = new HashMap<String, Object>(); ModelAndView
	 * mv = new ModelAndView("admin/live/liveDelect", strMap); return mv; }
	 */

	// 获取直播信息列表
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<LivePO> lives = conn_live.findAll(page, limit);
		int count = conn_live.countAll();
		strMap.put("data", lives);
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("code", "0");
		return strMap;
	}

	// 获取评论信息列表
	@ResponseBody
	@RequestMapping(value = "/message.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getMessage(HttpServletRequest request) throws Exception {
		long liveId = Long.parseLong(request.getParameter("liveId"));
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<LiveMessagePO> lives = conn_liveMessage.findByLive(liveId);
		int count = conn_liveMessage.countAll();
		strMap.put("data", lives);
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("code", "0");
		return strMap;
	}

	// 修改页面弹出窗口
	@RequestMapping(value = "/liveDelect", method = RequestMethod.GET)
	public ModelAndView liveDelect(HttpServletRequest request) {
		String liveId = request.getParameter("liveId");
		ModelAndView mv = new ModelAndView("admin/live/liveDelect");
		request.setAttribute("liveId", liveId);
		return mv;
	}

	// 删除评论
	@ResponseBody
	@RequestMapping(value = "/delMessage.do", method = RequestMethod.POST)
	public String delMessage(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("Id"));
		conn_liveMessage.delectById(id);
		return "success";
	}

	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		LivePO live = new LivePO();
		live.setUpdateTime(new Date());
		conn_live.save(live);
		return "success";
	}

	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String edit(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String value = request.getParameter("value");

		LivePO live = conn_live.get(id);

		live.setLiveStatusType(LiveStatusType.fromString(value));

		conn_live.save(live);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/edit1.do", method = RequestMethod.POST)
	public String edit1(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String value = request.getParameter("value");
		String field = request.getParameter("field");

		LivePO live = conn_live.get(id);
		if (field.equals("liveName")) {
			live.setLiveName(value);
		} else if (field.equals("leshiyunId")) {
			live.setLeshiyunId(value);
		} else {
			live.setLiveStatusType(LiveStatusType.fromString(value));
		}

		conn_live.save(live);
		return "success";
	}

}
