package com.guolaiwan.app.web.admin.menu.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.menu.vo.MenuVO;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.enumeration.MenuRenderType;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.chapman.product.dao.ShelveProductDAO;
import com.guolaiwan.bussiness.chapman.product.po.ShelveProductPO;
import com.guolaiwan.bussiness.common.enumeration.BooleanType;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@Autowired
	private MenuDAO conn_menu;
	
	@Autowired
	private ShelveProductDAO conn_shelve_product;
	
	//查询菜单
	@ResponseBody
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public Map<String, Object> get(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<MenuPO> menus = conn_menu.getAllMenu();
		
		List<MenuVO> _menus = MenuVO.getConverter(MenuVO.class).convert(menus, MenuVO.class);
		
		result.put("menus", _menus);
		
		return success(result);
	}
	
	//添加一个菜单
	@ResponseBody
	@RequestMapping(value = "/add/{userId}", method = RequestMethod.POST)
	public Map<String, Object> add(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String name = request.getParameter("name");
		String summary = request.getParameter("summary");
		
		MenuPO menu = new MenuPO();
		menu.setName(name);
		menu.setUpdateTime(new Date());
		menu.setSummary(summary);
		conn_menu.save(menu);
		
		MenuVO _menu = new MenuVO().set(menu);
		
		result.put("menu", _menu);
		
		return success(result);
	}
	
	//删除一个菜单
	@ResponseBody
	@RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
	public Map<String, Object> remove(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> menuIds = JSON.parseArray(params.getString("menuIds"), Long.class);
		
		List<MenuPO> menus = conn_menu.getAllByIds(menuIds);
		
		for(MenuPO menu:menus){
			MenuPO parent = menu.getParent();
			if(parent != null){
				parent.getSubMenus().remove(menu);
				menu.setParent(null);
			}
		}
		
		List<ShelveProductPO> products = conn_shelve_product.getShelveProductByMenu(menuIds);
		if(products!=null && products.size()>0){
			for(ShelveProductPO product:products){
				product.getProduct().setShelve(null);
				product.setProduct(null);
			}
			conn_shelve_product.deleteAll(products);
		}
		
		conn_menu.deleteAll(menus);
		
		return success(result);
	}
	
}
