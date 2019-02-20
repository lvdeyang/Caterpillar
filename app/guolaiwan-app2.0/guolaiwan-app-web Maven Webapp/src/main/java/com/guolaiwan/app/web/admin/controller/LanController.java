package com.guolaiwan.app.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.LanVO;
import com.guolaiwan.bussiness.admin.dao.LanDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.po.LanPO;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;

import pub.caterpillar.mvc.controller.BaseController;

@RequestMapping(value = "/admin/lan")
@Controller
public class LanController extends BaseController {

	@Autowired
	private LanDAO conn_lan;

	@Autowired
	private ModularDAO conn_modular;

	@Autowired
	private ModularClassDAO conn_modularClass;

	@ResponseBody
	@RequestMapping(value = "/addLan.do", method = RequestMethod.POST)
	public String addLan(HttpServletRequest request) {
		String name = request.getParameter("lanName");
		LanPO lan = new LanPO();
		lan.setName(name);
		conn_lan.save(lan);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/getLanList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getLanList() throws Exception {
		List<LanPO> listpo = conn_lan.getAllLans();
		List<LanVO> listvo = LanVO.getConverter(LanVO.class).convert(listpo, LanVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		// 获取所有模块和子模块
		List<ModularPO> modulars = conn_modular.findAll();
		List<ModularClassPO> Classes = conn_modularClass.findAll();
		strMap.put("modulars", modulars);
		strMap.put("Classes", Classes);
		ModelAndView mv = new ModelAndView("admin/lan/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/updateLan/{id}" , method = RequestMethod.GET)
	public ModelAndView toUpdateLan(@PathVariable long id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView mav = null;
		List<LanPO> polist = conn_lan.getLanById(id);
		List<LanVO> volist = LanVO.getConverter(LanVO.class).convert(polist, LanVO.class);
		map.put("oneLanList", volist.get(0));
		mav = new ModelAndView("admin/lan/update");
		mav.addAllObjects(map);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateLan.do" , method = RequestMethod.POST , produces = "application/json; charset=utf-8")
	public String updateLan(HttpServletRequest request , HttpServletResponse response){
		String id = request.getParameter("lanId");
		String name = request.getParameter("lanName");
		LanPO lan = new LanPO();
		lan.setId(Long.parseLong(id));
		lan.setName(name);
		conn_lan.update(lan);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/addv" , method = RequestMethod.GET)
	public ModelAndView toInsert(){
		Map<String, Object> strMap = new HashMap<String, Object>();
		// 获取所有模块和子模块
		List<ModularPO> modulars = conn_modular.findAll();
		List<ModularClassPO> Classes = conn_modularClass.findAll();
		strMap.put("modulars", modulars);
		strMap.put("Classes", Classes);
		ModelAndView mv = new ModelAndView("admin/lan/add", strMap);
		return mv;
	}
	
}