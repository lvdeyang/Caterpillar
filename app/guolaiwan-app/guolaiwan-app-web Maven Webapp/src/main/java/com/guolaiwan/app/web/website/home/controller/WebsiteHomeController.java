package com.guolaiwan.app.web.website.home.controller;

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
import com.guolaiwan.app.web.website.home.vo.HomePageSectionVO;
import com.guolaiwan.app.web.website.vo.ShelveProductVO;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.chapman.product.dao.ProductDAO;
import com.guolaiwan.bussiness.chapman.product.dto.ProductDTO;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/website/home")
public class WebsiteHomeController extends BaseController {

	//默认查询数量
	private final int pageNum = 1;
	private final int pageSize = 6;
	
	@Autowired
	private MenuDAO conn_menu;
	
	@Autowired
	private ProductDAO conn_product;
	
	//初始化方法
	@ResponseBody
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public Map<String, Object> init(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<MenuPO> menus = conn_menu.getAllMenu();
		
		List<HomePageSectionVO> _sections = HomePageSectionVO.getConverter(HomePageSectionVO.class).convert(menus, HomePageSectionVO.class);
		
		for(HomePageSectionVO section:_sections){
			List<ProductDTO> products = conn_product.getProductHasDetails(section.getId(), pageNum, pageSize);
			
			List<ShelveProductVO> _products = ShelveProductVO.getConverter(ShelveProductVO.class).convert(products, ShelveProductVO.class);
			
			section.setProducts(_products);
		}
		
		result.put("sections", _sections);
		
		return success(result);
	}
	
}
