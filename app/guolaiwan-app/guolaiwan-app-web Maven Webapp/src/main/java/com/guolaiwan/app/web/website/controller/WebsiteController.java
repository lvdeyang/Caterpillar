package com.guolaiwan.app.web.website.controller;

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

import com.guolaiwan.app.web.admin.menu.vo.MenuVO;
import com.guolaiwan.app.web.chapman.source.vo.SourceFolderVO;
import com.guolaiwan.app.web.chapman.vo.ChapmanVO;
import com.guolaiwan.app.web.enumeration.LoginCacheName;
import com.guolaiwan.app.web.user.vo.UserVO;
import com.guolaiwan.bussiness.admin.check.AdminDBCheck;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.dao.PageDAO;
import com.guolaiwan.bussiness.admin.dao.PageLayoutDAO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.chapman.product.dao.ProductDAO;
import com.guolaiwan.bussiness.user.dao.BasketDAO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/website")
public class WebsiteController extends BaseController {

	//默认查询数量
	private final int pageNum = 1;
	private final int pageSize = 12;
	
	@Autowired
	private ProductDAO conn_product;
	
	@Autowired
	private BasketDAO conn_basket;
	
	@Autowired
	private MenuDAO conn_menu;
	
	@Autowired
	private PageLayoutDAO conn_page_layout;
	
	@Autowired
	private PageDAO conn_page;
	
	@Autowired
	private AdminDBCheck dbcheck_admin;
	
	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("website/home");
		
		return mv;
	}
	
	//获取用户信息
	@ResponseBody
	@RequestMapping(value = "/get/user/info", method = RequestMethod.GET)
	public Map<String, Object> getUserInfo(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String key = request.getParameter(LoginCacheName.KEY.getName());
		
		if(key != null){
			
			HttpSession session = request.getSession(true);
			
			Object _key = session.getAttribute(LoginCacheName.KEY.getName());
			
			if(_key!=null && key.equals((String)_key)){
				
				//返回用户
				UserVO user = (UserVO)session.getAttribute(LoginCacheName.USER.getName());
				result.put("user", user);
				
				//返回购物车
				int basketNum = conn_basket.countByField("user.id", user.getId());
				result.put("basketNum", basketNum);
				
				//返回商户
				Object chapman = session.getAttribute(LoginCacheName.CHAPMAN.getName());
				if(chapman != null){
					result.put("chapman", (ChapmanVO)chapman);
				}
				
				//返回资源根目录
				Object root = session.getAttribute(LoginCacheName.ROOT.getName());
				if(root != null){
					result.put("root", (SourceFolderVO)root);
				}
				
			}
		}
		
		return success(result);
	}
	
	//获取菜单
	@ResponseBody
	@RequestMapping(value = "/menus/get", method = RequestMethod.GET)
	public Map<String, Object> getMenus(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<MenuPO> menus = conn_menu.getAllMenu();
		
		List<MenuVO> _menus = MenuVO.getConverter(MenuVO.class).convert(menus, MenuVO.class);
		
		result.put("menus", _menus);
		
		return success(result);
	}
	
}
