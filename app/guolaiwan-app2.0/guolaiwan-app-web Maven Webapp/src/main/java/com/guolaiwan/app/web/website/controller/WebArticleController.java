package com.guolaiwan.app.web.website.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ArticleVO;
import com.guolaiwan.bussiness.admin.dao.ArticleDAO;
import com.guolaiwan.bussiness.admin.dao.ClassDAO;
import com.guolaiwan.bussiness.admin.po.ArticlePO;

@Controller
@RequestMapping("/article")
public class WebArticleController extends WebBaseControll{
	//显示列表
	@Autowired
	private ArticleDAO conn_Article;
			
	//首页
		@RequestMapping(value = "/articleInfo", method= RequestMethod.GET)
		public ModelAndView articleInfo(HttpServletRequest request) throws Exception {
			Map<String, Object> strMap=new HashMap<String, Object>();
			String uuid = request.getParameter("i");
			
			ArticlePO article = conn_Article.get(uuid);
			ArticleVO articles = new ArticleVO().set(article);
		
			strMap.put("article",articles);
			ModelAndView mv = new ModelAndView("web/article/articleInfo",strMap);
            return mv;
		}
	
}

