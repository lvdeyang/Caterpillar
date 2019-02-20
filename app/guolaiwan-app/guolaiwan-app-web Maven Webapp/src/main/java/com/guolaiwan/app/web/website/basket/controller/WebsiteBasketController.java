package com.guolaiwan.app.web.website.basket.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.enumeration.LoginCacheName;
import com.guolaiwan.app.web.user.vo.UserVO;
import com.guolaiwan.app.web.website.basket.vo.BasketVO;
import com.guolaiwan.bussiness.chapman.check.ChapmanDBCheck;
import com.guolaiwan.bussiness.chapman.product.dao.ProductDAO;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import com.guolaiwan.bussiness.user.check.UserDBCheck;
import com.guolaiwan.bussiness.user.dao.BasketDAO;
import com.guolaiwan.bussiness.user.dao.UserDAO;
import com.guolaiwan.bussiness.user.dto.BasketDTO;
import com.guolaiwan.bussiness.user.po.BasketPO;
import com.guolaiwan.bussiness.user.po.UserPO;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping("/website/basket")
public class WebsiteBasketController extends BaseController{

	@Autowired
	private UserDAO conn_user;
	
	@Autowired
	private BasketDAO conn_basket;
	
	@Autowired
	private ProductDAO conn_product;
	
	@Autowired
	private ChapmanDBCheck dbcheck_chapman;
	
	@Autowired
	private UserDBCheck dbcheck_user;
	
	//添加购物车条目
	@ResponseBody
	@RequestMapping(value = "/add/{userId}", method = RequestMethod.POST)
	public Map<String, Object> add(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String productId = request.getParameter("product");
		
		UserPO user = conn_user.get(userId);
		
		ProductPO product = dbcheck_chapman.productExist(productId);
		
		BasketPO eBasket = conn_basket.getBasketByUserAndProduct(user.getId(), product.getId());
		
		if(eBasket == null){
			
			BasketPO basket = new BasketPO();
			basket.setNum(1);
			basket.setUpdateTime(new Date());
			conn_basket.save(basket);
			
			basket.setUser(user);
			if(user.getBaskets() == null){
				user.setBaskets(new HashSet<BasketPO>());
			}
			user.getBaskets().add(basket);
			conn_user.saveOrUpdate(user);
			
			basket.setProduct(product);
			if(product.getBaskets() == null){
				product.setBaskets(new HashSet<BasketPO>());
			}
			conn_product.saveOrUpdate(product);
			
		}else{
			eBasket.setNum(eBasket.getNum() + 1);
			eBasket.setUpdateTime(new Date());
			conn_basket.saveOrUpdate(eBasket);
		}
		
		int basketNum = conn_basket.countByField("user.id", user.getId());
		
		result.put("basketNum", basketNum);
		
		return success(result);
	}
	
	//获取用户购物车条目
	@ResponseBody
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public Map<String, Object> get(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<BasketDTO> baskets = conn_basket.getBasketByUser(userId);
		
		List<BasketVO> _baskets = BasketVO.getConverter(BasketVO.class).convert(baskets, BasketVO.class);
		
		result.put("baskets", _baskets);
		
		return success(result);
	}
	
	//修改购物车条目商品数量
	@ResponseBody
	@RequestMapping(value = "/change/num/{userId}/{basketId}", method = RequestMethod.PUT)
	public Map<String, Object> changeNum(
			@PathVariable Long userId,
			@PathVariable Long basketId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(true);
		
		UserVO _user = (UserVO)session.getAttribute(LoginCacheName.USER.getName());
		
		UserPO user = dbcheck_user.userLegit(_user.getId(), userId);
		
		BasketPO basket = dbcheck_user.basketExist(basketId);
		
		dbcheck_user.userBasketMatching(user, basket);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		
		JSONObject params = parser.parseJSON();
		int num = params.getIntValue("num");
		
		basket.setNum(Integer.valueOf(num));
		basket.setUpdateTime(new Date());
		conn_basket.saveOrUpdate(basket);
		
		return success(result);
	}
	
	//删除购物车条目
	@ResponseBody
	@RequestMapping(value = "/delete/{userId}/{basketId}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(
			@PathVariable Long userId,
			@PathVariable Long basketId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		HttpSession session = request.getSession(true);
		
		UserVO _user = (UserVO)session.getAttribute(LoginCacheName.USER.getName());
		
		UserPO user = dbcheck_user.userLegit(_user.getId(), userId);
		
		BasketPO basket = dbcheck_user.basketExist(basketId);
		
		dbcheck_user.userBasketMatching(user, basket);
		
		user.getBaskets().remove(basket);
		basket.setUser(null);
		
		ProductPO product = conn_product.get(basket.getProduct().getId());
		product.getBaskets().remove(basket);
		basket.setProduct(null);
		
		conn_basket.delete(basket);
		
		return success(result);
	}
	
}
