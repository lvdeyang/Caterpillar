package com.chenxi.web.mobile.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.dao.ArticleDao;
import com.chenxi.web.dao.UserRecordDao;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.UserCollectionPo;
import com.chenxi.web.po.UserRecordPo;

@Controller
@RequestMapping("/article")
public class MArticleController {
	@Autowired
	ArticleDao conn_article;
	@Autowired
	UserRecordDao conn_userrecord;
	@RequestMapping(value = "/mobile/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,long articleId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ArticlePo articlePo=conn_article.get(articleId);
		strMap.put("article", articlePo);
		UserRecordPo userRecordPo=new UserRecordPo();
		userRecordPo.setClassId(articlePo.getClassesId());
		userRecordPo.setContentId(articlePo.getId());
		HttpSession session = request.getSession();
		userRecordPo.setUserId(Long.parseLong(session.getAttribute("userId").toString()));
		conn_userrecord.save(userRecordPo);
		ModelAndView mv = new ModelAndView("mobile/article", strMap);
		return mv;
	}
}
