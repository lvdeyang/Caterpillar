package com.guolaiwan.app.web.merchant.car.controller;

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

import com.guolaiwan.app.web.merchant.car.vo.SiteVO;
import com.guolaiwan.bussiness.merchant.car.dao.RouteDAO;
import com.guolaiwan.bussiness.merchant.car.dao.SiteDAO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;
import com.guolaiwan.bussiness.merchant.car.po.SitePO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/merchant/site")
public class SiteController extends BaseController{
	@Autowired
	private SiteDAO conn_Site;
	@Autowired
	private RouteDAO conn_route;

	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getsite(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();

		long route = Long.parseLong(request.getParameter("route"));
		strMap.put("route",route);
		strMap.put("allcount", conn_Site.GetCountByPage());

		ModelAndView mv = new ModelAndView("merchant/car/site/list",strMap);
		return mv;

	}

	//添加页面
	@RequestMapping("/addv")
	public ModelAndView addSite(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		long route = Long.parseLong(request.getParameter("route"));
		strMap.put("route",route);
		ModelAndView mv = new ModelAndView("merchant/car/site/add",strMap);
		return mv;
	}
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="list.do",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	public Map<String,Object> GetList(int pagecurr,long route) throws Exception{
		RoutePO routePo = conn_route.get(route);
		List<SitePO> Sitepo = conn_Site.getListByRoute(routePo,pagecurr,5);
		List<SiteVO> Sitevo = SiteVO.getConverter(SiteVO.class).convert(Sitepo, SiteVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("alist", Sitevo);
		map.put("route",route);
		return map;			
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		long route = Long.parseLong(request.getParameter("route"));//路线
		RoutePO routePo = conn_route.get(route);

		String name = request.getParameter("name");
		long price=Long.parseLong(request.getParameter("price"));
		SitePO site=null;
		site = new SitePO();
		site.setName(name);
		site.setPrice(price);
		site.setRoute(routePo);

		conn_Site.save(site);
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		SitePO site = conn_Site.get(uuid);
		//解关联
		site.setRoute(null);
		conn_Site.delete(site);
		return "success";
	}
	//显示修改页面
	@RequestMapping(value="/updatev",method= RequestMethod.GET)
	public ModelAndView UpdateView(HttpServletRequest request) throws Exception{

		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		SitePO sitep = conn_Site.get(uuid);
		SiteVO site = new SiteVO().set(sitep);

		strMap.put("list", site);

		List<SitePO> sitelist=conn_Site.findAll();
		strMap.put("alist", sitelist);

		ModelAndView mv = new ModelAndView("merchant/car/site/Modify");
		mv.addAllObjects(strMap);

		return mv;
	}
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		SitePO site = conn_Site.get(uuid);
		String name  = request.getParameter("name");

		long price=Long.parseLong(request.getParameter("price"));

		site.setName(name);

		site.setPrice(price);
		conn_Site.update(site);
		return "success";
	}
}