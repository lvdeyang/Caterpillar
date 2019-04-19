package com.guolaiwan.app.web.admin.controller;

import java.util.Date;
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

import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.bussiness.admin.dao.ActivityBundleDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.po.ActiveBundlePo;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.orm.hql.QueryHql;

@Controller
@RequestMapping("/admin/activitybundle")
public class ActivityBundleController extends BaseController {
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private ActivityBundleDAO conn_bundle;
	@Autowired
	private MerchantDAO conn_merchant;
	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView bundleList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("admin/activitybundle/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_bundle.countAll();
		Long comId = null;
		if (getLoginInfo() != null) {
			 comId = getLoginInfo().getComId();
		}
		List<ActiveBundlePo> bundles = conn_bundle.queryByPage(page, limit,comId.intValue());
		
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", bundles);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/activitybundle/add");
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String comName = getLoginInfo().getComName();
		String title = request.getParameter("title");
		Long comId = null;
		if (getLoginInfo() != null) {
			 comId = getLoginInfo().getComId();
		}
		ActiveBundlePo bundlePo = new ActiveBundlePo();
		bundlePo.setTitle(title);
		bundlePo.setComId(comId.intValue());
		conn_bundle.save(bundlePo);
		return "success";
	}

	// 选择图片
	@ResponseBody
	@RequestMapping(value = "/pic.do", method = RequestMethod.POST)
	public String pic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long id = Long.parseLong(request.getParameter("id"));
		ActiveBundlePo bundlePo = conn_bundle.get(id);
		bundlePo.setPic(pic);

		conn_bundle.saveOrUpdate(bundlePo);
		return "success";
	}

	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
		conn_bundle.delete(id);
		return "success";
	}
}
