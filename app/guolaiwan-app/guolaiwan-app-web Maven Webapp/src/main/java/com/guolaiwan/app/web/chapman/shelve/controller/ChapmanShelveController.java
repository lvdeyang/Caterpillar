package com.guolaiwan.app.web.chapman.shelve.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guolaiwan.app.web.chapman.shelve.vo.ProductVO;
import com.guolaiwan.app.web.commons.tree.menu.handler.impl.RootMenuQueryHandler;
import com.guolaiwan.app.web.commons.tree.menu.handler.impl.SubMenuQueryHandler;
import com.guolaiwan.app.web.commons.tree.menu.util.MenuTreeUtil;
import com.guolaiwan.app.web.commons.tree.vo.UINodeVO;
import com.guolaiwan.bussiness.admin.check.AdminDBCheck;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.chapman.check.ChapmanDBCheck;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.product.dao.ProductDAO;
import com.guolaiwan.bussiness.chapman.product.dao.ShelveProductDAO;
import com.guolaiwan.bussiness.chapman.product.dto.ProductDTO;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import com.guolaiwan.bussiness.chapman.product.po.ShelveProductPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/chapman/shelve")
public class ChapmanShelveController extends BaseController {

	@Autowired
	private ProductDAO conn_product;
	
	@Autowired
	private ShelveProductDAO conn_shelve_product;
	
	@Autowired
	private MenuDAO conn_menu;
	
	@Autowired
	private ChapmanDBCheck dbcheck_chapman;
	
	@Autowired
	private AdminDBCheck dbcheck_admin;
	
	//获取类目树
	@ResponseBody
	@RequestMapping(value = "/tree/get/{chapmanId}/{menuId}", method = RequestMethod.GET)
	public Map<String, Object> getTree(
			@PathVariable Long chapmanId,
			@PathVariable String menuId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		List<UINodeVO> nodes = null;
		
		if("root".equals(menuId)){
			nodes = MenuTreeUtil.getNodeList(new RootMenuQueryHandler(), false, false, false);
		}else{
			nodes = MenuTreeUtil.getNodeList(new SubMenuQueryHandler(Long.valueOf(menuId)), false, false, false);
		}
		
		result.put("nodes", nodes);
		
		return success(result);
	}
	
	//获取类目下的货架商品
	@ResponseBody
	@RequestMapping(value = "/product/get/{chapmanId}/{menuId}", method = RequestMethod.GET)
	public Map<String, Object> getProduct(
			@PathVariable Long chapmanId,
			@PathVariable String menuId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		MenuPO menu = dbcheck_admin.menuExist(menuId);
		
		List<ProductDTO> products = conn_product.getProductByMenu(chapman.getId(), menu.getId());
		
		List<ProductVO> _products = ProductVO.getConverter(ProductVO.class).convert(products, ProductVO.class);
		
		result.put("products", _products);
		
		return success(result);
	}
	
	//商品上架
	@ResponseBody
	@RequestMapping(value = "/on/{chapmanId}/{productId}", method = RequestMethod.POST)
	public Map<String, Object> on(
			@PathVariable Long chapmanId,
			@PathVariable Long productId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		ProductPO product = dbcheck_chapman.productExist(productId);
		
		dbcheck_chapman.chapmanProductMatching(chapman, product);
		
		String menuId = request.getParameter("menuId");

		MenuPO menu = dbcheck_admin.menuExist(menuId);
		
		ShelveProductPO shelve = new ShelveProductPO();
		shelve.setUpdateTime(new Date());
		conn_shelve_product.save(shelve);
		
		product.setShelve(shelve);
		shelve.setProduct(product);
		conn_product.saveOrUpdate(product);
		
		if(menu.getProducts() == null){
			menu.setProducts(new HashSet<ShelveProductPO>());
		}
		menu.getProducts().add(shelve);
		shelve.setMenu(menu);
		conn_menu.saveOrUpdate(menu);
		
		return success(result);
	}
	
	//商品下架
	@ResponseBody
	@RequestMapping(value = "/off/{chapmanId}/{productId}", method = RequestMethod.DELETE)
	public Map<String, Object> off(
			@PathVariable Long chapmanId,
			@PathVariable Long productId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		ProductPO product = dbcheck_chapman.productExist(productId);
		
		dbcheck_chapman.chapmanProductMatching(chapman, product);
		
		ShelveProductPO shelve = product.getShelve();
		
		MenuPO menu = shelve.getMenu();
		
		product.setShelve(null);
		shelve.setProduct(null);
		
		menu.getProducts().remove(shelve);
		shelve.setMenu(null);
		
		conn_shelve_product.delete(shelve);
		
		return success(result);
	}
	
}
