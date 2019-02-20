package com.guolaiwan.app.web.index.controller;

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

import com.guolaiwan.bussiness.admin.dao.AdminDAO;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.RoleDAO;
import com.guolaiwan.bussiness.admin.dao.RoleMenuDAO;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.RoleMenuPO;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
public class MerchantIndexController extends BaseController{

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private RoleMenuDAO conn_rolemenu;

	@Autowired
	private MenuDAO conn_menu;

	@RequestMapping("/merchant/my")
	public ModelAndView home(HttpServletRequest request){



		Map<String, Object> strMap=new HashMap<String, Object>();


		String field ="roleId";

		Object value=getMerchantInfo().getRoleId();

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

				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

				if(mpo.getShowis()==1)
				{
					sbHtml.append("<dd class=\"\"> <a href=\"javascript:;\" _href=\""+basePath+"/"+mpo.getUrl()+"\"> <cite>"+mpo.getName()+"</cite> </a> </dd>");
				}
			}
			sbHtml.append("</dl>");
			sbHtml.append("</li>");
		}
		strMap.put("menulist", sbHtml.toString());
		strMap.put("merchant", getMerchantInfo().getMerchantName());
		ModelAndView mv = new ModelAndView("merchant/MyJsp",strMap);
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

	// Hello World!
	@RequestMapping("/merchant/welcome")
	public ModelAndView welcome(HttpServletRequest request){
		// request.getSession().setAttribute("roleid", "3");
		ModelAndView mv = new ModelAndView("merchant/welcome");

		return mv;
	}

	@RequestMapping("/login/merchant")
	public ModelAndView myjspS(HttpServletRequest request){

		// request.getSession().setAttribute("roleid", "3");
		ModelAndView mv = new ModelAndView("merchant/login");

		return mv;
	}

	@Autowired
	private RoleDAO conn_role;
	//登录
	@ResponseBody
	@RequestMapping(value="/mer/login.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		System.out.println("商户登录");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		MerchantPO loginpo = conn_merchant.getByField("shopLoginName", username);

		if(loginpo != null) {
			if(!loginpo.getShopLoginPwd().equals(Sha1Util.getSha1(password))) {
				return "error";
			}
		} else {
			return "error";
		}

		int roleid =conn_role.getMerchantRole().getId().intValue();

		List<String> strUrls=new ArrayList<String>();
		//权限绑定
		String field ="roleId";
		Object value = roleid;
		List<RoleMenuPO> findByField = conn_rolemenu.findByField(field, value);
		List<RoleMenuPO> flist=searchQ(0,findByField);

		for (RoleMenuPO roleMenuPO : flist) {
			MenuPO mpo=conn_menu.getByField("id",Long.parseLong(String.valueOf(roleMenuPO.getMenuId())));
			strUrls.add(mpo.getUrl());
		}
		strUrls.add("/merchant/my");
		strUrls.add("/merchant/welcome");
		strUrls.add("/login/merchant");
		strUrls.add("/mer/login.do");
		strUrls.add("/logout/merchant");
		clearSession();
		request.getSession().setAttribute("mroleid", roleid);
		request.getSession().setAttribute("mstrUrls", strUrls);
		request.getSession().setAttribute("merchant", loginpo.getShopLoginName());
		request.getSession().setAttribute("merchantId", loginpo.getId());
		request.getSession().setAttribute("mcityCode", loginpo.getCityCode());
		request.getSession().setAttribute("mcityName", loginpo.getCityName());
		request.getSession().setAttribute("mComId", loginpo.getComId());
		request.getSession().setAttribute("mComName", loginpo.getComName());



		return "success";
	}


	@RequestMapping("/logout/merchant")
	public ModelAndView doLogout(HttpServletRequest request){

		HttpSession session = request.getSession(true); 

		session.removeAttribute("mroleid");

		ModelAndView mv = new ModelAndView("merchant/login");

		return mv;
	}
	
	@RequestMapping("/merchant/error")
	public ModelAndView myerror(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("merchant/error");
		return mv;
	}
}

