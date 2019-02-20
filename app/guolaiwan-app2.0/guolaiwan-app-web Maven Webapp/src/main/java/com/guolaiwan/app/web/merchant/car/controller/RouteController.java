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

import com.guolaiwan.app.web.merchant.car.vo.RouteVO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.merchant.car.dao.RouteDAO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/merchant/route")
public class RouteController extends BaseController{
	@Autowired
	private RouteDAO conn_Route;
	@Autowired
	private MerchantDAO conn_merchant;

	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getRoute(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();

		strMap.put("allcount", conn_Route.GetCountByPage());

		ModelAndView mv = new ModelAndView("merchant/car/route/list",strMap);
		return mv;

	}

	//添加页面
	@RequestMapping("/addv")
	public ModelAndView addRoute(){

		ModelAndView mv = new ModelAndView("merchant/car/route/add");
		return mv;
	}
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="list.do",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	public Map<String,Object> GetList(int pagecurr) throws Exception{
		//绑定商家根据登录名和商家登录名相等
		String merchantName =getMerchantInfo().getMerchantName();
		MerchantPO merchant= conn_merchant.getByField("shopLoginName", merchantName);
		
		
		List<RoutePO> Routepo = conn_Route.findByField("merchant",merchant,pagecurr,5);
		List<RouteVO> Routevo = RouteVO.getConverter(RouteVO.class).convert(Routepo, RouteVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("alist", Routevo);
		return map;			
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String merchantName =getMerchantInfo().getMerchantName();
		MerchantPO merchant= conn_merchant.getByField("shopLoginName", merchantName);
		if(merchant==null){
			return "norool";
		}
		
		
		String name = request.getParameter("name");
		String origin =request.getParameter("origin");
		String end =request.getParameter("end");
		RoutePO Route=null;
		Route = new RoutePO();
		Route.setName(name);
		Route.setOrigin(origin);
		Route.setEnd(end);
		Route.setMerchant(merchant);
		Route.setLab(origin+"-"+end);
		conn_Route.save(Route);
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		RoutePO Route = conn_Route.get(uuid);
		
		Route.setMerchant(null);
		conn_Route.delete(Route);
		return "success";
	}
	//显示修改页面
	@RequestMapping(value="/updatev",method= RequestMethod.GET)
	public ModelAndView UpdateView(HttpServletRequest request) throws Exception{

		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		RoutePO Routep = conn_Route.get(uuid);
		RouteVO Route = new RouteVO().set(Routep);

		strMap.put("list", Route);

		List<RoutePO> Routelist=conn_Route.findAll();
		strMap.put("alist", Routelist);

		ModelAndView mv = new ModelAndView("merchant/car/route/Modify");
		mv.addAllObjects(strMap);

		return mv;
	}
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String merchantName =getMerchantInfo().getMerchantName();
		MerchantPO merchant= conn_merchant.getByField("shopLoginName", merchantName);
		if(merchant==null){
			return "norool";
		}
		
		String uuid =request.getParameter("uuid");
		RoutePO Route = conn_Route.get(uuid);
		String name  = request.getParameter("name");
		String origin =request.getParameter("origin");
		String end =request.getParameter("end");

		Route.setName(name);
		Route.setOrigin(origin);
		Route.setEnd(end);
		Route.setLab(origin+"-"+end);
		conn_Route.update(Route);
		return "success";
	}
}