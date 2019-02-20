package com.guolaiwan.app.web.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ModularClassVO;
import com.guolaiwan.app.web.admin.vo.ModularVO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.RolePO;

import pub.caterpillar.mvc.controller.BaseController;
@Controller
@RequestMapping("/admin/modularclass")
public class ModularClassController extends BaseController{

	@Autowired
	private ModularClassDAO  conn_ModularClass;

	@Autowired
	private ModularDAO  conn_Modular;
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getModulars(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<ModularPO> modulars = conn_Modular.findAll();
		strMap.put("allcount", conn_ModularClass.GetCountByPage());
		strMap.put("modulars", modulars);
		ModelAndView mv = new ModelAndView("admin/modularclass/list",strMap);
		return mv;
	}
	//添加页面
	@RequestMapping("/addv")
	public String addModularClass(HttpServletRequest request,  HttpServletResponse response,Model model){
		List<ModularPO> list=conn_Modular.findAll();
		model.addAttribute("list",list);
		return "admin/modularclass/add";
	}

	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(int page,int limit,HttpServletRequest request) throws Exception {
		String mCode = request.getParameter("mCode");
		String cName = request.getParameter("cName");
		List<ModularClassPO> modularclasspo=conn_ModularClass.GetListbyPage(page, limit,mCode,cName);
		List<ModularClassVO> modularclassvo = ModularClassVO.getConverter(ModularClassVO.class).convert(modularclasspo, ModularClassVO.class);
		int count = conn_ModularClass.countBySelect(mCode,cName);
		for (ModularClassVO modularClassVO2 : modularclassvo) {
			ModularPO modularPO = conn_Modular.getModularByCode(modularClassVO2.getClassmodularCode());
			if(modularPO!=null){
				modularClassVO2.setClassmodularCode(modularPO.getModularName());
			}else{
				modularClassVO2.setClassmodularCode("");
			}
		}

		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", modularclassvo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count",count);
		return map;
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String classmodularCode = request.getParameter("classmodularCode");
		String className = request.getParameter("className");
		int classIsv =1;
		long id  = conn_ModularClass.getMaxId();
		String classCode = String.valueOf((id+2000l));
		int classSort =Integer.parseInt(request.getParameter("classSort"));
		ModularClassPO modularclass = new ModularClassPO();
		modularclass.setUpdateTime(new Date());
		modularclass.setClassCode(classCode);
		modularclass.setClassmodularCode(classmodularCode);
		modularclass.setClassName(className);
		modularclass.setClassIsv(classIsv);
		modularclass.setClassSort(classSort);
		conn_ModularClass.save(modularclass);
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		ModularClassPO modularclass = conn_ModularClass.get(uuid);
		conn_ModularClass.delete(modularclass);
		return "success";
	}
	//显示修改页面
	@RequestMapping(value="/updatev",method= RequestMethod.GET)
	public ModelAndView UpdateView(HttpServletRequest request){

		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		ModularClassPO modularclass = conn_ModularClass.get(uuid);
		List<ModularPO> modulars=conn_Modular.findAll();
		strMap.put("list", modularclass);
		strMap.put("modularss", modulars);
		ModelAndView mv = new ModelAndView("admin/modularclass/Modify",strMap);

		return mv;
	}
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		ModularClassPO modularclass = conn_ModularClass.get(uuid);


		String classCode = request.getParameter("classCode");
		String classmodularCode = request.getParameter("classmodularCode");
		String className = request.getParameter("className");
		int classIsv =1;
		if(request.getParameter("classIsv")==null)
			classIsv=0;
		int classSort =Integer.parseInt(request.getParameter("classSort"));
		modularclass.setClassCode(classCode);
		modularclass.setClassmodularCode(classmodularCode);
		modularclass.setClassName(className);
		modularclass.setClassIsv(classIsv);
		modularclass.setClassSort(classSort);
		modularclass.setUpdateTime(new Date());



		conn_ModularClass.update(modularclass);

		return "success";
	}

	//改变显示状态modularList
	@ResponseBody
	@RequestMapping(value="/changeIsv.do", method= RequestMethod.POST)
	public String updateIsv(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		int val = Integer.parseInt(request.getParameter("val"));
		ModularClassPO modularclass =  conn_ModularClass.get(id);
		modularclass.setClassIsv(val);;
		conn_ModularClass.update(modularclass);
		return "success";
	}
	
	
}

