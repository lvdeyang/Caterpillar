package com.guolaiwan.app.web.chapman.tag.controller;

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
import com.guolaiwan.app.web.admin.tag.vo.TagVO;
import com.guolaiwan.bussiness.common.dao.CodeListDAO;
import com.guolaiwan.bussiness.common.po.CodeListPO;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/chapman/tag")
public class ChapmanTagController extends BaseController {

	@Autowired
	private CodeListDAO conn_code_list;
	
	//获取所有的标签
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Map<String, Object> get(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<CodeListPO> tags = conn_code_list.getTags();
		
		List<TagVO> _tags = TagVO.getConverter(TagVO.class).convert(tags, TagVO.class);
		
		result.put("tags", _tags);
		
		return success(result);
	}
	
	//获取标签下的商品
	@ResponseBody
	@RequestMapping(value = "/product/get", method = RequestMethod.GET)
	public Map<String, Object> getProduct(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		
		return success(result);
	}
	
	//为商品打标签
	@ResponseBody
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public Map<String, Object> addProduct(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		
		return success(result);
	}
	
	//删除商品标签
	@ResponseBody
	@RequestMapping(value = "/product/remove", method = RequestMethod.DELETE)
	public Map<String, Object> removeProduct(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		
		return success(result);
	}
	
}
