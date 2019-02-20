package com.guolaiwan.app.web.admin.controller;

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

import com.guolaiwan.app.web.admin.vo.CityInfoVO;
import com.guolaiwan.app.web.admin.vo.ClassVO;
import com.guolaiwan.bussiness.admin.dao.ClassDAO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.ClassPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/class")
public class ClassController extends BaseController{
	@Autowired
	private ClassDAO conn_Class;
	//显示列表
		@RequestMapping(value="/list",method= RequestMethod.GET)
		public ModelAndView getClasss(HttpServletRequest request){
			Map<String, Object> strMap = new HashMap<String, Object>();
			strMap.put("allcount", conn_Class.GetCountByPage());
			ModelAndView mv = new ModelAndView("admin/class/list",strMap);
			return mv;
		     }
		//添加页面
				@RequestMapping("/addv")
				public ModelAndView addClass(){
					
					ModelAndView mv = new ModelAndView("admin/class/add");
					return mv;
				}
				//异步读取
				@ResponseBody
				@RequestMapping(value="/classList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
				public Map<String, Object> getList() throws Exception {
					List<ClassPO> Classpo = conn_Class.findAll();
					List<ClassVO> Classvo = ClassVO.getConverter(ClassVO.class).convert(Classpo, ClassVO.class);
					Map<String, Object> map= new HashMap<String, Object>();
					map.put("list", Classvo);
					return map;
					}
				//异步读取列表分页
				@ResponseBody
				@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
				public Map<String, Object> GetList(int pagecurr) throws Exception {
					List<ClassPO> Classpo=conn_Class.GetListbyPage(pagecurr, 5);
					List<ClassVO> Classvo = ClassVO.getConverter(ClassVO.class).convert(Classpo, ClassVO.class);
					Map<String, Object> map= new HashMap<String, Object>();
					map.put("alist", Classvo);
					return map;
				}
				//添加数据
				@ResponseBody
				@RequestMapping(value="/add.do", method= RequestMethod.POST)
				public String add(HttpServletRequest request) throws Exception {
			                    
					String className = request.getParameter("className");
					int classSort=Integer.parseInt(request.getParameter("classSort"));
					long classGroup=Long.parseLong(request.getParameter("classGroup"));
					
					ClassPO Class= new ClassPO();
					Class.setClassName(className);
					Class.setClassSort(classSort);
					Class.setClassGroup(classGroup);
					
					conn_Class.save(Class);
					return "success";
					
				}
				//删除数据
				@ResponseBody
				@RequestMapping(value="/del.do", method= RequestMethod.POST)
				public String del(HttpServletRequest request) throws Exception {
					String uuid =request.getParameter("uuid");
					ClassPO Class = conn_Class.get(uuid);
					conn_Class.delete(Class);
					return "success";
				}
				//显示修改页面
				@RequestMapping(value="/updatev",method= RequestMethod.GET)
				public ModelAndView UpdateView(HttpServletRequest request){
					
					Map<String, Object> strMap=new HashMap<String, Object>();
					String uuid =request.getParameter("uuid");
					ClassPO Class = conn_Class.get(uuid);
					strMap.put("list", Class);
					
					List<ClassPO> Classlist=conn_Class.findAll();
					strMap.put("alist", Classlist);
					
					ModelAndView mv = new ModelAndView("admin/class/Modify");
					mv.addAllObjects(strMap);
				
					return mv;
				}
				//修改数据
				@ResponseBody
				@RequestMapping(value="/update.do", method= RequestMethod.POST)
				public String update(HttpServletRequest request) throws Exception {
					String uuid =request.getParameter("uuid");
					ClassPO Class = conn_Class.get(uuid);
			 
		            
					String className = request.getParameter("className");
					
					int classSort =Integer.parseInt(request.getParameter("classSort"));
					long classGroup=Long.parseLong(request.getParameter("classGroup"));
					Class.setClassName(className);
					Class.setClassSort(classSort);
					Class.setClassGroup(classGroup);
							
					
					conn_Class.update(Class);
					
					return "success";
				}
	
}
