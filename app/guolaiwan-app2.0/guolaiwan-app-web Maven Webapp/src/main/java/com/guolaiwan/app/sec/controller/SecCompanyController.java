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
import com.guolaiwan.bussiness.sec.po.SecCompanyPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/sec/company")
public class SecCompanyController extends BaseController {

	@Autowired
	private SecCompanyDAO conn_seccompany;

	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView companyList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("sec/company/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_seccompany.countAll();
		List<SecCompanyPo> companys = conn_seccompany.findAll(page, limit);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", companys);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("sec/company/add");
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
	    String name=request.getParameter("name");
	    String x=request.getParameter("x");
	    String y=request.getParameter("y");
	    String distance=request.getParameter("distance");
	    SecCompanyPo secCompanyPo=new SecCompanyPo();
	    secCompanyPo.setName(name);
	    //secCompanyPo.setX(x);
	    //secCompanyPo.setY(y);
	    //secCompanyPo.setDistance(Long.parseLong(distance));
	    conn_seccompany.save(secCompanyPo);
		
		return "success";
	}

	
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
		conn_seccompany.delete(id);
		return "success";
	}

	

}
