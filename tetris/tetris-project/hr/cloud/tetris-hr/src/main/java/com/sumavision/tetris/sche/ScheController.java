package com.sumavision.tetris.sche;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.user.UserClassify;
import com.sumavision.tetris.user.UserQuery;
import com.sumavision.tetris.user.UserVO;

@Controller
@RequestMapping(value = "/hr/sche")
public class ScheController {

	@Autowired
	private UserQuery userQuery;
	@Autowired
	private ScheQuery scheQuery;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Integer currentPage,
			Integer pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验		
		Page<SchePO> page = scheQuery.findAll(currentPage, pageSize);
		List<SchePO> entities =page.getContent();
		List<ScheVO> sches = ScheVO.getConverter(ScheVO.class).convert(entities, ScheVO.class);
		
		Long total = page.getTotalElements();
			
		return new HashMapWrapper<String, Object>().put("rows", sches)
													   .put("total", total)
													   .getMap();
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/alllist")
	public Object alllist(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		
		List<SchePO> entities =scheDao.findAll();
		List<ScheVO> sches = ScheVO.getConverter(ScheVO.class).convert(entities, ScheVO.class);

			
		return sches;
	}
	

	@Autowired
	ScheService scheService;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String name,
			String start,
			String end,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验
		
		SchePO schePO =scheService.add(user, name, start,end);
		
		return new ScheVO().set(schePO);
	}	
	
	@Autowired
	ScheDAO scheDao;
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Object edit(
			@PathVariable Long id,
			String name,
			String start,
			String end,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
	
		SchePO sche = scheDao.findOne(id);
	
		
		sche = scheService.edit(sche, name, start,end);
		
		return new ScheVO().set(sche);
	}
	
	/**
	 * 删除分类<br/>
	 * <b>作者:</b>ldy<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月2日 上午9:49:01
	 * @param @PathVariable Long id 分类id
	 */
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(
			@PathVariable Long id) throws Exception{
		
		
		SchePO sche = scheDao.findOne(id);
		
		
		scheService.remove(sche);
		
		
		return null;
	}
	
	
}
