package com.guolaiwan.app.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.MenuVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.RolePO;

import pub.caterpillar.mvc.controller.BaseController;

/**
 * Hello World!
 * <p>Title: MenuController</p>
 * <p>Description: 权限Contorller</p>
 * <p>Company: guolaiwan</p> 
 * @author Mr.Sun
 * @date 2017年11月18日 下午4:22:53
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController extends BaseController {

	@Autowired
	private MenuDAO conn_menu;
	
	// 查询权限列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getMenus(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<MenuPO> pmenus =conn_menu.getMenuByP(0);
		int allcount=conn_menu.GetCountByPage();
		strMap.put("pmenus", pmenus);
	    strMap.put("allcount", allcount);
	    strMap.put("allpages", GetAllPages(allcount, 5));
		ModelAndView mv = new ModelAndView("admin/menu/list",strMap);
		return mv;
	}
	
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/pageList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(int pagecurr,String name) throws Exception {
		List<MenuPO> listpo=conn_menu.GetListbyPage(pagecurr, 5);
		List<MenuVO> listvo = MenuVO.getConverter(MenuVO.class).convert(listpo, MenuVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}
	
	// 权限添加弹出窗口
	@RequestMapping("/addv")
	public ModelAndView addMenu(){
		
		ModelAndView mv = new ModelAndView("admin/menu/add");
		return mv;
	}
	
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
 
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		int showis = Integer.parseInt(request.getParameter("showis"));
		int sort = Integer.parseInt(request.getParameter("sort"));
		int parentId = Integer.parseInt(request.getParameter("parentId"));
		
		MenuPO menu = new MenuPO();
		menu.setName(name);
		menu.setUrl(url);
		menu.setShowis(showis);
		menu.setSort(sort);
		menu.setParentId(parentId);
		conn_menu.save(menu);
		
		return "success";
	}
	
	//修改页面弹出窗口
	@RequestMapping(value="/updatev",method= RequestMethod.GET)
	public ModelAndView updateView(HttpServletRequest request){
		
		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		MenuPO menu = conn_menu.get(uuid);
		strMap.put("list", menu);
		
		ModelAndView mv = new ModelAndView("admin/menu/modify");
		mv.addAllObjects(strMap);
	
		return mv;
	}
	
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		MenuPO menu = conn_menu.get(uuid);
 
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		int showis = Integer.parseInt(request.getParameter("showis"));
		int sort = Integer.parseInt(request.getParameter("sort"));
		int parentId = Integer.parseInt(request.getParameter("parentId"));
		
		menu.setName(name);
		menu.setUrl(url);
		menu.setShowis(showis);
		menu.setSort(sort);
		menu.setParentId(parentId);
		
		conn_menu.update(menu);
		
		return "success";
	}
	
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		MenuPO menu = conn_menu.get(uuid);
		conn_menu.delete(menu);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/getMenuByP.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getMenuByP(int pId,int pagecurr) throws Exception {
		List<MenuPO> listpo = conn_menu.getMenuByPPage(pId,pagecurr,5);
		List<MenuVO> listvo = MenuVO.getConverter(MenuVO.class).convert(listpo, MenuVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/pCount.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getPCount(int pId) throws Exception {
		int pCount = conn_menu.getPCount(pId);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("pCount", pCount);
		return map;
	}
	
}
