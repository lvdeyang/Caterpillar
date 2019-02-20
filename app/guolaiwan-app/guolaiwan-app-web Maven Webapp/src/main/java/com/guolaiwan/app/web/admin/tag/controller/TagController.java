package com.guolaiwan.app.web.admin.tag.controller;

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
import com.guolaiwan.app.web.admin.tag.vo.TagVO;
import com.guolaiwan.bussiness.chapman.product.dao.ShelveProductDAO;
import com.guolaiwan.bussiness.chapman.product.dao.TagMapDAO;
import com.guolaiwan.bussiness.chapman.product.po.ShelveProductPO;
import com.guolaiwan.bussiness.chapman.product.po.TagMapPO;
import com.guolaiwan.bussiness.common.dao.CodeListDAO;
import com.guolaiwan.bussiness.common.enumeration.CodeType;
import com.guolaiwan.bussiness.common.po.CodeListPO;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping("/tag")
public class TagController extends BaseController {

	@Autowired
	private CodeListDAO conn_code_list;
	
	@Autowired
	private ShelveProductDAO conn_shelve_product;
	
	@Autowired
	private TagMapDAO conn_tag_map;
	
	
	//获取所有标签
	@ResponseBody
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public Map<String, Object> get(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<CodeListPO> tags = conn_code_list.getTags();
		
		List<TagVO> _tags = TagVO.getConverter(TagVO.class).convert(tags, TagVO.class);
		
		result.put("tags", _tags);
		
		return success(result);
	}
	
	//添加一个标签
	@ResponseBody
	@RequestMapping(value = "/add/{userId}", method = RequestMethod.POST)
	public Map<String, Object> add(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String name = request.getParameter("name");
		
		CodeListPO tag = new CodeListPO();
		tag.setUpdateTime(new Date());
		tag.setName(name);
		tag.setLevel_1(CodeType.TAG);
		conn_code_list.save(tag);
		
		tag.setCode(new StringBufferWrapper().append(CodeType.TAG.toString()).append(tag.getId()).toString());
		conn_code_list.saveOrUpdate(tag);
		
		TagVO _tag = new TagVO().set(tag);
		
		result.put("tag", _tag);
		
		return success(result);
	}
	
	//删除标签
	@ResponseBody
	@RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
	public Map<String, Object> remove(
			@PathVariable Long userId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> tagIds = JSON.parseArray(params.getString("tagIds"), Long.class);
		
		List<CodeListPO> tags = conn_code_list.getAllByIds(tagIds);
		
		List<TagMapPO> tagMaps = conn_tag_map.gatTagMapsByTagId(tagIds);
		if(tagMaps!=null && tagMaps.size()>0){
			for(TagMapPO tagMap:tagMaps){
				tagMap.getProduct().getTags().remove(tagMap);
				tagMap.setProduct(null);
			}
			conn_tag_map.deleteAll(tagMaps);
		}
		
		conn_code_list.deleteAll(tags);
		
		return success(result);
	}
	
}
