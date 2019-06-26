package com.chenxi.web.yueba.mobile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.yueba.admin.dao.CommentDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.CommentPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/comment")
public class MCommentController {
	@Autowired
	CommentDao conn_comment;
	@Autowired
	WorkerDao conn_worker;
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/add", method = RequestMethod.POST)
	public Object add(HttpServletRequest request) throws Exception {
		String workerId=request.getParameter("workerId");
		String content=request.getParameter("content");
		CommentPo commentPo=new CommentPo();
		commentPo.setContent(content);
		commentPo.setWorkerId(Long.parseLong(workerId));
        conn_comment.save(commentPo);
        return "success";
	}
	
	@RequestMapping(value = "/mobile/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("workerId", workerId);
		ModelAndView mv = new ModelAndView("yuebamobile/comment", strMap);
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request,int currPage,int pageCount,long workerId) throws Exception {
	
		return conn_comment.findByField("workerId", workerId, currPage, pageCount);
	}
}
