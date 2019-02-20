package com.guolaiwan.app.web.admin.category.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.category.vo.CategoryVO;
import com.guolaiwan.bussiness.chapman.product.dao.ShelveProductDAO;
import com.guolaiwan.bussiness.chapman.product.po.ShelveProductPO;
import com.guolaiwan.bussiness.common.dao.CodeListDAO;
import com.guolaiwan.bussiness.common.enumeration.CodeType;
import com.guolaiwan.bussiness.common.po.CodeListPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

	@Autowired
	private CodeListDAO conn_code_list;
	
	@Autowired
	private ShelveProductDAO conn_shelve_product;
	
	//获取类目
	@ResponseBody
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public Map<String, Object> getCategory(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
	
		List<CodeListPO> categories = conn_code_list.getCategories();
		
		List<CategoryVO> _categories = CategoryVO.getConverter(CategoryVO.class).convert(categories, CategoryVO.class);
		
		result.put("categories", _categories);
		
		return success(result);
	}
	
	//添加类目
	@ResponseBody
	@RequestMapping(value = "/add/{userId}", method = RequestMethod.POST)
	public Map<String, Object> addCategory(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
	
		String name = request.getParameter("name");
		
		CodeListPO category = new CodeListPO();
		category.setUpdateTime(new Date());
		category.setName(name);
		category.setLevel_1(CodeType.CATEGORY);
		conn_code_list.save(category);
		category.setCode(new StringBufferWrapper().append(CodeType.CATEGORY.toString()).append(category.getId()).toString());
		conn_code_list.saveOrUpdate(category);
		
		CategoryVO _category = new CategoryVO().set(category);
		
		result.put("category", _category);
		
		return success(result);
	}
	
	//删除类目
	@ResponseBody
	@RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
	public Map<String, Object> removeCategory(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
	
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> categoryIds = JSON.parseArray(params.getString("categoryIds"), Long.class);
		
		List<CodeListPO> categories = conn_code_list.getAllByIds(categoryIds);
		
		Set<String> categoryCodes = new HashSet<String>();
		for(CodeListPO category:categories){
			categoryCodes.add(category.getCode());
		}
		
		List<ShelveProductPO> shelveProducts = conn_shelve_product.getShelveProductByCategory(categoryCodes);
		if(shelveProducts!=null && shelveProducts.size()>0){
			for(ShelveProductPO shelveProduct:shelveProducts){
				shelveProduct.getProduct().setShelve(null);
				shelveProduct.setProduct(null);
			}
			conn_shelve_product.deleteAll(shelveProducts);
		}
		
		conn_code_list.deleteAll(categories);
		
		return success(result);
	}
	
}
