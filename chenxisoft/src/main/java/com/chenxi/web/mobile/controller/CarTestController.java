package com.chenxi.web.mobile.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.classes.Moudular;
import com.chenxi.web.dao.ArticleDao;
import com.chenxi.web.dao.ClassesDao;
import com.chenxi.web.dao.OnlineClassesDao;
import com.chenxi.web.dao.ProductDao;
import com.chenxi.web.dao.RecommDao;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ClassesPo;
import com.chenxi.web.po.OnlineClassesPo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.po.RecommPo;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/cartest")
public class CarTestController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("mobile/cartest", strMap);
		return mv;
	}
	
	@Autowired
	ArticleDao conn_article;
	@Autowired 
	ProductDao conn_product;
	@Autowired
	RecommDao conn_recomm;
	@Autowired
	OnlineClassesDao conn_onlineClass;
	@Autowired
	ClassesDao conn_classes;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object getActivityProducts(HttpServletRequest request, HttpServletResponse response, int currPage,int pageCount,String type)
			throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		if(type.equals("recomm")){
			List<ArticlePo> articlePos=new ArrayList<ArticlePo>();
			//List<ProductPo> productPos=new ArrayList<ProductPo>();
			List<RecommPo> recommPos=conn_recomm.findAll(currPage, pageCount);
			for (RecommPo recommPo : recommPos) {
				if(recommPo.getMoudular().equals(Moudular.CEPING)){
					articlePos.add(conn_article.get(recommPo.getContentId()));
				}else{
					//productPos.add(conn_product.get(recommPo.getContentId()));
				}
			}
			ret.put("articles", articlePos);
			//ret.put("products", productPos);
		}
		else if(type.equals("article")){
			List<OnlineClassesPo> onlineList=conn_onlineClass.findOnlineBymodular(Moudular.CEPING, currPage, pageCount);
			List<ArticlePo> articlePos=new ArrayList<ArticlePo>();
			for (OnlineClassesPo onlineClassesPo : onlineList) {
				ArticlePo articlePo=conn_article.get(onlineClassesPo.getContentId());
				articlePo.setClassesName(conn_classes.get(onlineClassesPo.getClassesId()).getName());
				articlePos.add(articlePo);
			}
			ret.put("articles", articlePos);
		}
		else if(type.equals("product")){
			List<OnlineClassesPo> onlineList=conn_onlineClass.findOnlineBymodular(Moudular.WEIXIU, currPage, pageCount);
			List<ProductPo> productPos=new ArrayList<ProductPo>();
			for (OnlineClassesPo onlineClassesPo : onlineList) {
				ProductPo productPo=conn_product.get(onlineClassesPo.getContentId());
				productPo.setClassesName(conn_classes.get(onlineClassesPo.getClassesId()).getName());
				productPos.add(productPo);
			}
			ret.put("products", productPos);
		}
	    
		return ret;
	}

}
