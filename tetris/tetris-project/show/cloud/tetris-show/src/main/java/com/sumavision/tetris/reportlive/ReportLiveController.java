package com.sumavision.tetris.reportlive;

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
@RequestMapping(value = "/show/reportlive")
public class ReportLiveController {

	@Autowired
	private UserQuery userQuery;
	@Autowired
	private ReportLiveQuery reportLiveQuery;

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/list")
	public Object list(
			Integer currentPage,
			Integer pageSize,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		//TODO 权限校验		
		Page<ReportLivePo> page = reportLiveQuery.findAll(currentPage, pageSize);
		List<ReportLivePo> entities =page.getContent();
		
		Long total = page.getTotalElements();
			
		return new HashMapWrapper<String, Object>().put("rows", entities)
													   .put("total", total)
													   .getMap();
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/alllist")
	public Object alllist(HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		
		
		List<ReportLivePo> entities =reportLiveDao.findAll();

			
		return entities;
	}
	

	@Autowired
	ReportLiveService reportLiveService;
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add")
	public Object add(
			String name, 
			String moniterIds,
			int cinterval,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
		//TODO 权限校验
		ReportLivePo reportLivePo =reportLiveService.add(user, name,moniterIds,cinterval);
		return reportLivePo;
	}	
	
	@Autowired
	ReportLiveDao reportLiveDao;
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/edit/{id}")
	public Object edit(
			@PathVariable Long id,
			String name, 
			String moniterIds,
			int cinterval,
			HttpServletRequest request) throws Exception{
		
		UserVO user = userQuery.current();
	
		ReportLivePo reportLivePo = reportLiveDao.findOne(id);
	
		
		reportLivePo = reportLiveService.edit(reportLivePo, name, moniterIds,cinterval);
		
		return reportLivePo;
	}
	

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(
			@PathVariable Long id) throws Exception{
		
		
		ReportLivePo reportLivePo = reportLiveDao.findOne(id);
		
		
		reportLiveService.remove(reportLivePo);
		
		
		return null;
	}
	
	
}
