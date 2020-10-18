package com.guolaiwan.app.sec.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.sec.dao.SecCompanyDAO;
import com.guolaiwan.bussiness.sec.dao.SecUserDAO;
import com.guolaiwan.bussiness.sec.po.SecCompanyPo;
import com.guolaiwan.bussiness.sec.po.SecUserPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/sec/secuser")
public class SecUserController extends BaseController {

	@Autowired
	private SecUserDAO conn_secuser;

	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView companyList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("id", request.getParameter("id"));
		ModelAndView mv = new ModelAndView("sec/user/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(HttpServletRequest request,int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String comId=request.getParameter("comId");
		int count = conn_secuser.countByField("companyId", Long.parseLong(request.getParameter("comId")));
		List<SecUserPo> users = conn_secuser.findByField("companyId", Long.parseLong(request.getParameter("comId")),page, limit);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", users);
		return strMap;
	}


	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
		conn_secuser.delete(id);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/changestate", method = RequestMethod.POST)
	public String changestate(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String status=request.getParameter("status");
		String checkreason=request.getParameter("checkreason");
		SecUserPo secUserPo=conn_secuser.get(id);
		secUserPo.setStatus(status);
		secUserPo.setCheckreason(checkreason);
		// 删除所有推荐的关联表
		conn_secuser.saveOrUpdate(secUserPo);
		return "success";
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkout(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("id", request.getParameter("id"));
		ModelAndView mv = new ModelAndView("sec/user/checkout", strMap);
		return mv;
	}
	

}
