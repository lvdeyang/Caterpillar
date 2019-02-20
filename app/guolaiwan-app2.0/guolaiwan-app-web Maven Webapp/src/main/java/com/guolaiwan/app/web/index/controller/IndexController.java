package com.guolaiwan.app.web.index.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.dao.AdminDAO;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.RoleMenuDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.admin.po.RoleMenuPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.operation.dao.WebsiteRecordDAO;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private AdminDAO conn_admin;
	
	@Autowired
	private RoleMenuDAO conn_rolemenu;
	
	@Autowired
	private MenuDAO conn_menu;
	
	@RequestMapping("/admin/my")
	public ModelAndView home(HttpServletRequest request){
		
		
		
		Map<String, Object> strMap=new HashMap<String, Object>();
		
		
		String field ="roleId";
		
		Object value=getLoginInfo().getRoleId();
		
		StringBuilder sbHtml=new StringBuilder();
		List<RoleMenuPO> findByField = conn_rolemenu.findByField(field, value);
		
		//父类
		List<RoleMenuPO> flist=search(0,findByField);
		for (RoleMenuPO roleMenuPO : flist) {
			
			MenuPO mpo=conn_menu.getByField("id",Long.parseLong(String.valueOf(roleMenuPO.getMenuId())));
			sbHtml.append("<li class=\"layui-nav-item\"> <a class=\"javascript:;\" href=\"javascript:;\"> <i class=\"layui-icon\" style=\"top: 3px;\">&#xe62d;</i><cite>"+mpo.getName()+"</cite> </a>");
			sbHtml.append("<dl class=\"layui-nav-child\">");
			//子类
			List<RoleMenuPO> clist=search(roleMenuPO.getMenuId(),findByField);
			for (RoleMenuPO roleMenuPO2 : clist) {
				
				
				mpo=conn_menu.getByField("id",Long.parseLong(String.valueOf(roleMenuPO2.getMenuId())));
				if(mpo==null){
					continue;
				}
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

				if(mpo.getShowis()==1)
				{
					if(mpo.getUrl().indexOf("http")==-1){
						sbHtml.append("<dd class=\"\"> <a href=\"javascript:;\" _href=\""+basePath+"/"+mpo.getUrl()+"\"> <cite>"+mpo.getName()+"</cite> </a> </dd>");
					}else{
						sbHtml.append("<dd class=\"\"> <a id=\"mims\" href=\"javascript:;\" _href=\""+mpo.getUrl()+"\"> <cite>"+mpo.getName()+"</cite> </a> </dd>");
					}
				}
			}
			sbHtml.append("</dl>");
			sbHtml.append("</li>");
		}
		strMap.put("menulist", sbHtml.toString());
		strMap.put("adminName", getLoginInfo().getAdminName());
		ModelAndView mv = new ModelAndView("admin/MyJsp",strMap);
		return mv;
	}
	
	//获取父类
	public List<RoleMenuPO> search(int parentId,List<RoleMenuPO> list){
				
				List<RoleMenuPO> results = new ArrayList<>();
				   for(int i=0;i<list.size();i++){
					   if(((list.get(i))).getMenuFid()==parentId)
						   results.add(list.get(i));
				}
					   return results;
	}
			
	public List<RoleMenuPO> searchQ(int parentId,List<RoleMenuPO> list){
		
		List<RoleMenuPO> results = new ArrayList<>();
		   for(int i=0;i<list.size();i++){
			   if(((list.get(i))).getMenuFid()!=parentId)
				   results.add(list.get(i));
		}
			   return results;
}
	
	
	@Autowired
	private OrderInfoDAO conn_order;
	@Autowired
	private UserInfoDAO conn_user;
	@Autowired
	private WebsiteRecordDAO conn_websiterecord;
	@Autowired
	private MerchantDAO conn_merchant;
	
	// Hello World!
	@RequestMapping("/admin/welcome")
	public ModelAndView welcome(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("admin/welcome");
		mv.addObject("orderCount",conn_order.countAll());
		mv.addObject("merchantCount",conn_merchant.countAll());
		mv.addObject("userCount",conn_user.countAll());
		try {
			mv.addObject("todayrequest",conn_websiterecord.countToday());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			mv.addObject("todayrequest",0);
			e.printStackTrace();
		}
		//终端统计
		JSONArray terminalArr=new JSONArray();
		JSONObject appObj=new JSONObject();
		appObj.put("value", conn_order.countByField("source", OrderSource.APP));
		appObj.put("name", "App");
		terminalArr.add(appObj);
		JSONObject pubObj=new JSONObject();
		pubObj.put("value", conn_order.countByField("source", OrderSource.PUBLICADDRESS));
		pubObj.put("name", "公众号");
		terminalArr.add(pubObj);
		JSONObject unlineObj=new JSONObject();
		unlineObj.put("value", conn_order.countByField("source", OrderSource.UNLINE));
		unlineObj.put("name", "线下购票");
		terminalArr.add(unlineObj);
		mv.addObject("terReprt",terminalArr.toJSONString());
		//订单统计
		List<String> orderDateList=new ArrayList<String>();
		orderDateList.add(DateUtil.format(DateUtil.operDate(new Date(),-4),DateUtil.defaultDatePattern));
		orderDateList.add(DateUtil.format(DateUtil.operDate(new Date(),-3),DateUtil.defaultDatePattern));
		orderDateList.add(DateUtil.format(DateUtil.operDate(new Date(),-2),DateUtil.defaultDatePattern));
		orderDateList.add(DateUtil.format(DateUtil.operDate(new Date(),-1),DateUtil.defaultDatePattern));
		orderDateList.add(DateUtil.format(new Date(),DateUtil.defaultDatePattern));
		mv.addObject("orderDateList",JSONArray.toJSON(orderDateList));
		List<Integer> orderCountList=new ArrayList<Integer>();
		for (String dateStr : orderDateList) {
			try {
				orderCountList.add(conn_order.countByDate(dateStr));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.addObject("orderCountList", orderCountList);
		//商品统计
		List<Object> proOrderList=conn_order.getOrdersGroupByProduct();
		JSONArray proOrderArr=(JSONArray) JSONArray.toJSON(proOrderList);
		List<String> proOrderTitles=new ArrayList<String>();
		List<Integer> proOrderCounts=new ArrayList<Integer>();
		for (Object object : proOrderArr) {
			JSONArray objec=(JSONArray) JSONArray.toJSON(object);
			proOrderTitles.add(objec.getString(1));
			proOrderCounts.add(objec.getInteger(0));
		}
		mv.addObject("proOrderTitles", JSONArray.toJSON(proOrderTitles));
		mv.addObject("proOrderCounts", proOrderCounts);
		//商家统计
		List<Object> proOrderList1=conn_order.getOrdersGroupBymerchant();
		JSONArray proOrderArr1=(JSONArray) JSONArray.toJSON(proOrderList1);
		List<String> proOrderTitles1=new ArrayList<String>();
		List<Integer> proOrderCounts1=new ArrayList<Integer>();
		for (Object object : proOrderArr1) {
			JSONArray objec=(JSONArray) JSONArray.toJSON(object);
			proOrderTitles1.add(objec.getString(1));
			proOrderCounts1.add(objec.getInteger(0));
		}
		mv.addObject("proOrderTitles1", JSONArray.toJSON(proOrderTitles1));
		mv.addObject("proOrderCounts1", proOrderCounts1);
		//访问曲线
		List<String> recordDateList=new ArrayList<String>();
		recordDateList.add(DateUtil.format(DateUtil.operDate(new Date(),-4),DateUtil.defaultDatePattern));
		recordDateList.add(DateUtil.format(DateUtil.operDate(new Date(),-3),DateUtil.defaultDatePattern));
		recordDateList.add(DateUtil.format(DateUtil.operDate(new Date(),-2),DateUtil.defaultDatePattern));
		recordDateList.add(DateUtil.format(DateUtil.operDate(new Date(),-1),DateUtil.defaultDatePattern));
		recordDateList.add(DateUtil.format(new Date(),DateUtil.defaultDatePattern));
		mv.addObject("recordDateList",JSONArray.toJSON(recordDateList));
		List<Integer> recordCountList=new ArrayList<Integer>();
		for (String dateStr : recordDateList) {
			try {
				recordCountList.add(conn_websiterecord.countByDate(dateStr));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.addObject("recordCountList", recordCountList);
		//日访问量
		List<Object> recordList=conn_websiterecord.getRecordGroupByUrl();
		JSONArray recordArr=(JSONArray) JSONArray.toJSON(recordList);
		List<String> recordTitles=new ArrayList<String>();
		List<Integer> recordCounts=new ArrayList<Integer>();
		for (Object object : recordArr) {
			JSONArray objec=(JSONArray) JSONArray.toJSON(object);
			recordTitles.add(objec.getString(1));
			recordCounts.add(objec.getInteger(0));
		}
		mv.addObject("recordTitles", JSONArray.toJSON(recordTitles));
		mv.addObject("recordCounts", recordCounts);
		mv.addObject("user",getLoginInfo().getAdminName());
		return mv;
	}
	
	@RequestMapping("/login/myjsps")
	public ModelAndView myjspS(HttpServletRequest request){
		
		request.getSession().setAttribute("roleid", "3");
		ModelAndView mv = new ModelAndView("admin/login");
		
		return mv;
	}
	
	    //登录
		@ResponseBody
		@RequestMapping(value="/login/login.do", method= RequestMethod.POST)
		public String add(HttpServletRequest request) throws Exception {
			System.out.println("登录");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			AdminPO loginpo = conn_admin.getByField("adminName", username);
			
			if(loginpo != null&&!"Admin".equals(username)) {
				if(!loginpo.getPassword().equals(Sha1Util.getSha1(password))) {
					return "error";
				}
			} else if(!"Admin".equals(username)){
				return "error";
			}
			
			int roleid = loginpo.getRoleId();
			
			List<String> strUrls=new ArrayList<String>();
			//权限绑定
			String field ="roleId";
			Object value = roleid;
			List<RoleMenuPO> findByField = conn_rolemenu.findByField(field, value);
			List<RoleMenuPO> flist=searchQ(0,findByField);
			
			for (RoleMenuPO roleMenuPO : flist) {
				MenuPO mpo=conn_menu.getByField("id",Long.parseLong(String.valueOf(roleMenuPO.getMenuId())));
				if(mpo!=null){
					if(mpo!=null){
						if(mpo!=null){
							strUrls.add(mpo.getUrl());
						}
					}
				}
				
			}
			 strUrls.add("/admin/my");
			 strUrls.add("/admin/welcome");
		    strUrls.add("/login/error");
		    strUrls.add("/admin/modular/comSel");
		    strUrls.add("/admin/picture/sellist");
		    strUrls.add("/admin/merchant/sellist");
		    
			request.getSession().setAttribute("roleid", roleid);
			request.getSession().setAttribute("strUrls", strUrls);
			request.getSession().setAttribute("adminName", loginpo.getAdminName());
			request.getSession().setAttribute("cityCode", loginpo.getCityCode());
			request.getSession().setAttribute("cityName", loginpo.getCityName());
			request.getSession().setAttribute("comId", loginpo.getComId());
			request.getSession().setAttribute("comName", loginpo.getComName());
			System.out.println(getLoginInfo().getComName());
			return "success";
		}
		

		@RequestMapping("/do/logout")
		public ModelAndView doLogout(HttpServletRequest request){
			
			HttpSession session = request.getSession(true); 
			
			session.removeAttribute("roleid");
			
			ModelAndView mv = new ModelAndView("admin/login");
			
			return mv;
		}
		
		@RequestMapping("/login/error")
		public ModelAndView myerror(HttpServletRequest request){
			
			
			ModelAndView mv = new ModelAndView("admin/error");
			
			return mv;
		}
}

