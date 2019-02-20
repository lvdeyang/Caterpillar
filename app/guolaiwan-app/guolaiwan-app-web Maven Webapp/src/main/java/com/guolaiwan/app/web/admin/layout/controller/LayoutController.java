package com.guolaiwan.app.web.admin.layout.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.layout.vo.FilterVO;
import com.guolaiwan.app.web.admin.layout.vo.PageLayoutVO;
import com.guolaiwan.app.web.admin.layout.vo.PageVO;
import com.guolaiwan.app.web.admin.layout.vo.SectionVO;
import com.guolaiwan.bussiness.admin.check.AdminDBCheck;
import com.guolaiwan.bussiness.admin.dao.FilterDAO;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.dao.PageDAO;
import com.guolaiwan.bussiness.admin.dao.PageLayoutDAO;
import com.guolaiwan.bussiness.admin.dao.SectionDAO;
import com.guolaiwan.bussiness.admin.dto.PageDTO;
import com.guolaiwan.bussiness.admin.enumeration.FilterType;
import com.guolaiwan.bussiness.admin.enumeration.PageType;
import com.guolaiwan.bussiness.admin.enumeration.SectionType;
import com.guolaiwan.bussiness.admin.po.FilterPO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.admin.po.PageLayoutPO;
import com.guolaiwan.bussiness.admin.po.PagePO;
import com.guolaiwan.bussiness.admin.po.SectionPO;
import com.guolaiwan.bussiness.common.enumeration.BooleanType;
import com.guolaiwan.bussiness.common.enumeration.OperatorType;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping("/layout")
public class LayoutController extends BaseController {

	@Autowired
	private PageLayoutDAO conn_page_layout;
	
	@Autowired
	private PageDAO conn_page;
	
	@Autowired
	private SectionDAO conn_section;
	
	@Autowired
	private FilterDAO conn_filter;
	
	@Autowired
	private MenuDAO conn_menu;
	
	@Autowired
	private AdminDBCheck dbcheck_admin;
	
	//获取所有的布局
	@ResponseBody
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public Map<String, Object> get(
			@PathVariable Long userId, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<PageLayoutPO> layouts = conn_page_layout.getAllLayout();
		
		List<PageLayoutVO> _layouts = PageLayoutVO.getConverter(PageLayoutVO.class).convert(layouts, PageLayoutVO.class);
		
		result.put("layouts", _layouts);
		
		return success(result);
	}
	
	//添加一个布局
	@ResponseBody
	@RequestMapping(value = "/add/{userId}", method = RequestMethod.POST)
	public Map<String, Object> add(
			@PathVariable Long userId, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String name = request.getParameter("name");
		String enable = request.getParameter("enable");
		String theme  = request.getParameter("theme");
		String remarks = request.getParameter("remarks");
		
		PageLayoutPO layout = new PageLayoutPO();
		layout.setName(name);
		layout.setUpdateTime(new Date());
		layout.setEnable(BooleanType.fromString(enable));
		layout.setTheme(theme);
		layout.setRemarks(remarks);
		conn_page_layout.save(layout);
		
		PageLayoutVO _layout = new PageLayoutVO().set(layout);
		
		result.put("layout", _layout);
		
		//系统中只能有一个可用的布局
		if(layout.getEnable().equals(BooleanType.TRUE)){
			PageLayoutPO oldEnable = conn_page_layout.getEnableLayoutExceptId(layout.getId());
			if(oldEnable != null){
				oldEnable.setEnable(BooleanType.FALSE);
				conn_page_layout.saveOrUpdate(oldEnable);
				PageLayoutVO _oldEnable = new PageLayoutVO().set(oldEnable);
				result.put("oldEnable", _oldEnable);
			}
		}
		
		return success(result);
	}
	
	//删除布局
	@ResponseBody
	@RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
	public Map<String, Object> remove(
			@PathVariable Long userId, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> layoutIds = JSON.parseArray(params.getString("layoutIds"), Long.class);
		
		List<PageLayoutPO> layouts = conn_page_layout.getAllByIds(layoutIds);
		
		conn_page_layout.deleteAll(layouts);
		
		return success(result);
	}
	
	//切换一个布局可用状态
	@ResponseBody
	@RequestMapping(value = "/toggle/enable/{userId}/{layoutId}", method = RequestMethod.PUT)
	public Map<String, Object> toggleEnable(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		if(layout.getEnable().equals(BooleanType.TRUE)){
			layout.setEnable(BooleanType.FALSE);
		}else if(layout.getEnable().equals(BooleanType.FALSE)){
			layout.setEnable(BooleanType.TRUE);
		}
		conn_page_layout.saveOrUpdate(layout);
		
		PageLayoutVO _layout = new PageLayoutVO().set(layout);
		
		result.put("layout", _layout);
		
		//系统中只能有一个可用的布局
		if(layout.getEnable().equals(BooleanType.TRUE)){
			PageLayoutPO oldEnable = conn_page_layout.getEnableLayoutExceptId(layout.getId());
			if(oldEnable != null){
				oldEnable.setEnable(BooleanType.FALSE);
				conn_page_layout.saveOrUpdate(oldEnable);
				PageLayoutVO _oldEnable = new PageLayoutVO().set(oldEnable);
				result.put("oldEnable", _oldEnable);
			}
		}
		
		return success(result);
	}
	
	//获取布局下的所有页面
	@ResponseBody
	@RequestMapping(value = "/page/get/{userId}/{layoutId}", method = RequestMethod.GET)
	public Map<String, Object> getPage(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		List<PageDTO> pages = conn_page.getPageByLayout(layout.getId());
		
		List<PageVO> _pages = PageVO.getConverter(PageVO.class).convert(pages, PageVO.class);
		
		result.put("pages", _pages);
		
		return success(result);
	}
	
	//为布局添加一个页面
	@ResponseBody
	@RequestMapping(value = "/page/add/{userId}/{layoutId}", method = RequestMethod.POST)
	public Map<String, Object> addPage(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String menuId = request.getParameter("menuId"); 
		
		MenuPO menu = dbcheck_admin.menuExist(menuId);
		
		PagePO page = new PagePO();
		page.setName(name);
		page.setUpdateTime(new Date());
		page.setType(PageType.fromString(type));
		conn_page.save(page);
		
		if(layout.getPages() == null){
			layout.setPages(new HashSet<PagePO>());
		}
		layout.getPages().add(page);
		page.setPageLayout(layout);
		conn_page_layout.saveOrUpdate(layout);
		
		if(menu.getPages() == null){
			menu.setPages(new HashSet<PagePO>());
		}
		menu.getPages().add(page);
		page.setMenu(menu);
		conn_menu.saveOrUpdate(menu);
		
		PageDTO tPage = new PageDTO().set(page, menu);

		PageVO _page = new PageVO().set(tPage);
		
		result.put("page", _page);
		
		return success(result);
	}
	
	//为布局删除一个页面
	@ResponseBody
	@RequestMapping(value = "/page/remove/{userId}/{layoutId}", method = RequestMethod.DELETE)
	public Map<String, Object> removePage(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> pageIds = JSON.parseArray(params.getString("pageIds"), Long.class);
		
		List<PagePO> pages = conn_page.getPageByLayout(layout.getId(), pageIds);
		
		if(pages!=null && pages.size()>0){
			for(PagePO page:pages){
				layout.getPages().remove(page);
				page.setPageLayout(null);
				page.getMenu().getPages().remove(page);
				page.setMenu(null);
			}
		}
		conn_page.deleteAll(pages);
		
		return success(result);
	}
	
	//修改布局页面关联的菜单
	@ResponseBody
	@RequestMapping(value = "/page/menu/change/{userId}/{layoutId}/{pageId}", method = RequestMethod.PUT)
	public Map<String, Object> changePageMenu(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			@PathVariable Long pageId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		PagePO page = dbcheck_admin.pageExist(pageId);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		String menuId = params.getString("menuId");
		
		MenuPO menu = dbcheck_admin.menuExist(menuId);
		
		dbcheck_admin.layoutPageMatching(layout, page);
		
		MenuPO pMenu = page.getMenu();
		
		if(page.getMenu()==null || !pMenu.getId().equals(menu.getId())){
			pMenu.getPages().remove(page);
			page.setMenu(menu);
			if(menu.getPages() == null){
				menu.setPages(new HashSet<PagePO>());
			}
			menu.getPages().add(page);
			conn_page.saveOrUpdate(page);
			
			PageDTO tPage = new PageDTO().set(page, menu);

			PageVO _page = new PageVO().set(tPage);
			
			result.put("page", _page);
		}
		
		return success(result);
	}
	
	//获取页面版块
	@ResponseBody
	@RequestMapping(value = "/page/section/get/{userId}/{layoutId}/{pageId}", method = RequestMethod.GET)
	public Map<String, Object> getPageSection(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			@PathVariable Long pageId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		PagePO page = dbcheck_admin.pageExist(pageId);
		
		dbcheck_admin.layoutPageMatching(layout, page);
		
		List<SectionPO> sections = conn_section.findByField("page.id", page.getId());
		
		List<SectionVO> _sections = SectionVO.getConverter(SectionVO.class).convert(sections, SectionVO.class);
		
		result.put("sections", _sections);
		
		return success(result);
	}
	
	//添加页面版块
	@ResponseBody
	@RequestMapping(value = "/page/section/add/{userId}/{layoutId}/{pageId}", method = RequestMethod.POST)
	public Map<String, Object> addPageSection(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			@PathVariable Long pageId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		PagePO page = dbcheck_admin.pageExist(pageId);
		
		dbcheck_admin.layoutPageMatching(layout, page);
		
		String type = request.getParameter("type");
		String template = request.getParameter("template");
		String remarks = request.getParameter("remarks");
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		
		SectionPO section = new SectionPO();
		section.setUpdateTime(new Date());
		section.setType(SectionType.fromString(type));
		section.setTemplate(template);
		section.setRemarks(remarks);
		section.setTitle(title);
		section.setSummary(summary);
		conn_section.save(section);
		
		if(page.getSections() == null){
			page.setSections(new HashSet<SectionPO>());
		}
		page.getSections().add(section);
		section.setPage(page);
		conn_page.saveOrUpdate(page);

		SectionVO _section = new SectionVO().set(section);
		
		result.put("section", _section);
		
		return success(result);
	}
	
	//删除页面版块
	@ResponseBody
	@RequestMapping(value = "/page/section/remove/{userId}/{layoutId}/{pageId}", method = RequestMethod.DELETE)
	public Map<String, Object> removePageSection(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			@PathVariable Long pageId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		PagePO page = dbcheck_admin.pageExist(pageId);
		
		dbcheck_admin.layoutPageMatching(layout, page);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> sectionIds = JSON.parseArray(params.getString("sectionIds"), Long.class);

		List<SectionPO> sections = conn_section.getSectionByPage(page.getId(), sectionIds);
		
		if(sections!=null && sections.size()>0){
			for(SectionPO section:sections){
				page.getSections().remove(section);
				section.setPage(null);
			}
			conn_section.saveOrUpdateAll(sections);
		}
		
		return success(result);
	}
	
	//获取版块过滤器
	@ResponseBody
	@RequestMapping(value = "/page/section/filter/get/{userId}/{layoutId}/{pageId}/{sectionId}", method = RequestMethod.GET)
	public Map<String, Object> getPageSectionFilter(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			@PathVariable Long pageId,
			@PathVariable Long sectionId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		PagePO page = dbcheck_admin.pageExist(pageId);
		
		dbcheck_admin.layoutPageMatching(layout, page);
		
		SectionPO section = dbcheck_admin.sectionExist(sectionId);
		
		dbcheck_admin.sectionPageMatching(page, section);
		
		List<FilterPO> filters = conn_filter.findByField("section.id", section.getId());
		
		List<FilterVO> _filters = FilterVO.getConverter(FilterVO.class).convert(filters, FilterVO.class);
		
		result.put("filters", _filters);
		
		return success(result);
	}
	
	//添加一个版块过滤器
	@ResponseBody
	@RequestMapping(value = "/page/section/filter/add/{userId}/{layoutId}/{pageId}/{sectionId}", method = RequestMethod.POST)
	public Map<String, Object> addPageSectionFilter(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			@PathVariable Long pageId,
			@PathVariable Long sectionId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		PagePO page = dbcheck_admin.pageExist(pageId);
		
		dbcheck_admin.layoutPageMatching(layout, page);
		
		SectionPO section = dbcheck_admin.sectionExist(sectionId);
		
		dbcheck_admin.sectionPageMatching(page, section);
		
		String remarks = request.getParameter("remarks");
		String type = request.getParameter("type");
		String operator = request.getParameter("operator");
		String key_1 = request.getParameter("key_1");
		String value_1 = request.getParameter("value_1");
		String value_2 = request.getParameter("value_2");
		
		//条件公式
		StringBufferWrapper formula = new StringBufferWrapper();
		
		FilterType _type = FilterType.fromString(type);
		OperatorType _operator = OperatorType.fromString(operator);
		
		formula.append(_type.getFiled());
		if(_type.equals(FilterType.PRODUCTTYPE) || _type.equals(FilterType.TAG)){
			String value = (key_1==null||"".equals(key_1))?value_1:key_1;
			if(_operator!=null){
				if(_operator.equals(OperatorType.EQ) || _operator.equals(OperatorType.NE)){
					formula.append(_operator.getName())
						   .append(value)
						   .append(" ");
				}else if(_operator.equals(OperatorType.IN) || _operator.equals(OperatorType.NIN)){
					formula.append(" ")
						   .append(_operator.getName())
						   .append(" (")
						   .append(value)
						   .append(") ");
				}
			}
		}else if(_type.equals(FilterType.NUM)){
			formula.append(value_1).append(" ");
		}else if(_type.equals(FilterType.TIME)){
			//处理动态日期
			if("dynamic".equals(value_1)){
				value_1 = DateUtil.format(new Date(), DateUtil.dateTimePattern);
			}
			if("dynamic".equals(value_2)){
				value_2 = DateUtil.format(new Date(), DateUtil.dateTimePattern);
			}
			
			if(_operator.equals(OperatorType.LIKE)
					|| _operator.equals(OperatorType.LT) || _operator.equals(OperatorType.GT)
					|| _operator.equals(OperatorType.LE) || _operator.equals(OperatorType.GE)){
				formula.append(" ").append(_operator).append(" ").append(value_1);
			}else if(_operator.equals(OperatorType.BETWEEN)){
				formula.append(" ").append(OperatorType.GE).append(" ").append(value_1)
					   .append(" and ")
					   .append(_type.getFiled()).append(" ").append(OperatorType.LE).append(" ").append(value_2);
			}
		}
		
		FilterPO filter = new FilterPO();
		filter.setUpdateTime(new Date());
		filter.setRemarks(remarks);
		filter.setType(_type);
		filter.setFormula(formula.toString());
		filter.setKey_1(key_1);
		filter.setValue_1(value_1);
		filter.setValue_2(value_2);
		filter.setOperator(operator);
		conn_filter.save(filter);
		
		if(section.getFilters() == null){
			section.setFilters(new HashSet<FilterPO>());
		}
		section.getFilters().add(filter);
		filter.setSection(section);
		conn_section.saveOrUpdate(section);
		
		FilterVO _filter = new FilterVO().set(filter);
		
		result.put("filter", _filter);
		
		return success(result);
	}
	
	//删除一个版块过滤器
	@ResponseBody
	@RequestMapping(value = "/page/section/filter/remove/{userId}/{layoutId}/{pageId}/{sectionId}", method = RequestMethod.DELETE)
	public Map<String, Object> removePageSectionFilter(
			@PathVariable Long userId, 
			@PathVariable Long layoutId,
			@PathVariable Long pageId,
			@PathVariable Long sectionId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		PageLayoutPO layout = dbcheck_admin.pageLayoutExist(layoutId);
		
		PagePO page = dbcheck_admin.pageExist(pageId);
		
		dbcheck_admin.layoutPageMatching(layout, page);
		
		SectionPO section = dbcheck_admin.sectionExist(sectionId);
		
		dbcheck_admin.sectionPageMatching(page, section);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		
		List<Long> filterIds = JSON.parseArray(params.getString("filterIds"), Long.class);
		
		List<FilterPO> filters = conn_filter.getFilterBySection(section.getId(), filterIds);
		
		if(filters!=null && filters.size()>0){
			for(FilterPO filter:filters){
				section.getFilters().remove(filter);
				filter.setSection(null);
			}
			conn_filter.deleteAll(filters);
		}
		
		return success(result);
	}
	
}
