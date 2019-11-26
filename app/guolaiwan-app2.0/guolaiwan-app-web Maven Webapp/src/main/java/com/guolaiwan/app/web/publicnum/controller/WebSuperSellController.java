package com.guolaiwan.app.web.publicnum.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.LogisticVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.supersell.SuperSellDAO;
import com.guolaiwan.bussiness.admin.dao.supersell.SuperSellRelDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.po.ActiveBundlePo;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.CollectionPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.supersell.SuperSellPO;
import com.guolaiwan.bussiness.admin.po.supersell.SuperSellRelPO;

import pub.caterpillar.commons.util.html2text.ReduceHtml2Text;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/supersell")
public class WebSuperSellController extends WebBaseControll{

	@Autowired
	private SuperSellDAO superSellDAO;
	@Autowired
	private SuperSellRelDAO superSellRelDAO;
	@Autowired
	private SysConfigDAO sysConfigDAO;
	
	@RequestMapping(value = "/index")
	public ModelAndView pubHome(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/supersell/supersell");
		return mv;
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/getProducts", method = RequestMethod.GET)
	public Object getActivityProducts(HttpServletRequest request, HttpServletResponse response, String comCode)
			throws Exception {
		List<SuperSellPO> superSellPOs=superSellDAO.findAll();
		SysConfigPO sys = sysConfigDAO.getSysConfig();
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("weburl", sys.getWebUrl());
		ret.put("supersell", superSellPOs.get(0));
		List<SuperSellRelPO> superSellRelPOs=superSellRelDAO.findBysuperIdRandom(superSellPOs.get(0).getId(),20);
		ret.put("rels", superSellRelPOs);
		return ret;
	}
	
	@RequestMapping(value = "/product/index")
	public ModelAndView proIndex(HttpServletRequest request,long productId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/supersell/product");
		mv.addObject("productId",productId);
		return mv;
	}
	
	@Autowired
	ProductDAO conn_product;
	
	
	@ResponseBody
	@RequestMapping(value = "/productInfo", method = RequestMethod.GET)
	public Map<String, Object> productInfo(HttpServletRequest request, HttpServletResponse response, long productId)
			throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		ProductPO product = conn_product.get(productId);
		
		SysConfigPO sysConfig = sysConfigDAO.getSysConfig();
		// 商品
		ProductVO _product = new ProductVO().set(product);
		_product.setProductShowPic(sysConfig.getWebUrl() + _product.getProductShowPic()); // 显示图片
		_product.setProductMorePic(split(_product.getProductMorePic(), sysConfig.getWebUrl()));// 多图
		_product.setVideourl(sysConfig.getWebUrl() +_product.getVideourl());
		dataMap.put("product", _product);

		return success(dataMap);
	}
	
}
