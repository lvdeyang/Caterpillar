package com.chenxi.web.yueba.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.yueba.admin.dao.CommentDao;

import pub.caterpillar.mvc.controller.BaseController;
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("yuebaadmin/comment", strMap);
		mv.addObject("workerId",workerId);
		return mv;
	}
	
	@Autowired
	private CommentDao conn_comment;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(int page, int limit,long workerId) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_comment.countByworkerId(workerId));
		strMap.put("data", conn_comment.findByworkerId(workerId,page, limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
}
