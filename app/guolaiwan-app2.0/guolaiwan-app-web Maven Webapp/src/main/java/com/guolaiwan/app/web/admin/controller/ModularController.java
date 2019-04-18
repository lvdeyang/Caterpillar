package com.guolaiwan.app.web.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bytedeco.javacpp.RealSense.intrinsics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Commit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ModularVO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/modular")
public class ModularController extends BaseController{


	@Autowired
	private ModularDAO conn_Modular;

	@Autowired
	private ModularClassDAO conn_Class;

	@Autowired
	private SysConfigDAO conn_sysConfig;
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getModulars(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();

		strMap.put("allcount", conn_Modular.GetCountByPage());

		ModelAndView mv = new ModelAndView("admin/modular/list",strMap);
		return mv;
	}
	//添加页面
	@RequestMapping("/addv")
	public ModelAndView addModular(){

		ModelAndView mv = new ModelAndView("admin/modular/add");
		return mv;
	}


	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(int page,int limit) throws Exception {
		int count = conn_Modular.countAll();
		List<ModularPO> modularpo=conn_Modular.GetListbyPage(page, limit);
		List<ModularVO> modularvo = ModularVO.getConverter(ModularVO.class).convert(modularpo, ModularVO.class);
		String weburl = conn_sysConfig.getSysConfig().getWebUrl();
		for (ModularVO mv : modularvo) {
			mv.setModularPic(weburl+mv.getModularPic());
		}
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", modularvo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count",count);
		return map;
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {

		String modularName = request.getParameter("modularName");
		String modularPic  = request.getParameter("modularPic");
		long id  = conn_Modular.getMaxId();
		
		String modularCode = String.valueOf((id+2000l));
		ModularPO modular = new ModularPO();
		modular.setUpdateTime(new Date());
		modular.setModularCode(modularCode);
		modular.setModularName(modularName);
		modular.setModularIsv(1);
		modular.setModularPic(modularPic);

		conn_Modular.save(modular);

		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		ModularPO modular = conn_Modular.get(uuid);
		conn_Modular.delete(modular);
		return "success";
	}
	//显示修改页面
	@RequestMapping(value="/updatev",method= RequestMethod.GET)
	public ModelAndView UpdateView(HttpServletRequest request) throws Exception{

		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		ModularPO modular = conn_Modular.get(uuid);
		ModularVO _modular = new ModularVO().set(modular);
		String weburl = conn_sysConfig.getSysConfig().getWebUrl();
		
		strMap.put("list", _modular);
		strMap.put("weburl", weburl);
		ModelAndView mv = new ModelAndView("admin/modular/Modify");
		mv.addAllObjects(strMap);

		return mv;
	}
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		ModularPO modular = conn_Modular.get(uuid);
		String modularName = request.getParameter("modularName");
		String modularPic  = request.getParameter("modularPic");
		modular.setModularName(modularName);
		modular.setModularPic(modularPic);
		modular.setUpdateTime(new Date());
		conn_Modular.update(modular);
		return "success";
	}

	//显示列表
	@RequestMapping(value="/comSel",method= RequestMethod.GET)
	public ModelAndView getComSel(HttpServletRequest request){
		int index=0;
		if(request.getParameter("index")!=null){
			index = Integer.parseInt(request.getParameter("index"));
		}
		Map<String, Object> strMap = new HashMap<String, Object>();

		Object value=1;
		List<ModularPO> modularPolist=conn_Modular.findByField("modularIsv", value);

		List<ModularClassPO> modularClassPOlist=null;

		StringBuilder sbHtml=new StringBuilder();
		//循环模块
		for (ModularPO modularPO : modularPolist) {
			sbHtml.append("<div class=\"layui-colla-item\">");
			sbHtml.append("<h2 class=\"layui-colla-title\">"+modularPO.getModularName()+"</h2>");
			sbHtml.append("<div class=\"layui-colla-content\">");
			sbHtml.append("<div class=\"layui-collapse\" lay-accordion=\"\">");


			//循环分类
			String[] fields={"classmodularCode","classIsv"};
			Object[] values={modularPO.getModularCode(),1};
			modularClassPOlist=conn_Class.findByFields(fields, values);
			for (ModularClassPO classPO : modularClassPOlist) {
				sbHtml.append("<div class=\"layui-colla-item child\" data-mcode='"+modularPO.getModularCode()+"' data-mname='"+modularPO.getModularName()+"' data-ccode='"+classPO.getClassCode()+"' data-cname='"+classPO.getClassName()+"'>");
				sbHtml.append("<h2 class=\"layui-colla-title\">"+classPO.getClassName()+"</h2>");
				sbHtml.append("</div>");
			}

			sbHtml.append("</div>");
			sbHtml.append("</div>");
			sbHtml.append(" </div>");
		}


		strMap.put("list", sbHtml.toString());
		strMap.put("index", index);
		ModelAndView mv = new ModelAndView("admin/modular/comSel",strMap);
		return mv;
	}
	
	//改变显示状态modularList
	@ResponseBody
	@RequestMapping(value="/changeIsv.do", method= RequestMethod.POST)
	public String updateIsv(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		int val = Integer.parseInt(request.getParameter("val"));
		ModularPO modular =  conn_Modular.get(id);
		modular.setModularIsv(val);
		conn_Modular.update(modular);
		return "success";
	}
	
}
