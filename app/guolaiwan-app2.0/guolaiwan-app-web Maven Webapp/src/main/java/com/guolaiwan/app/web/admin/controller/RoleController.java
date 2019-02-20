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

import com.guolaiwan.app.web.admin.vo.RoleVO;
import com.guolaiwan.bussiness.admin.dao.RoleDAO;
import com.guolaiwan.bussiness.admin.po.RolePO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleDAO conn_role;
	
	
	// 查询角色列表
	@RequestMapping(value="/role",method= RequestMethod.GET)
	public ModelAndView getRoles(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		/*Map<String, Object> strMap = new HashMap<String, Object>();
		// String role = request.getParameter("role");
		
		List<RolePO> list = conn_role.findAll();
		strMap.put("roleName", list);*/
		strMap.put("allcount", conn_role.GetCountByPage());
		
		ModelAndView mv = new ModelAndView("admin/role/list",strMap);
		return mv;
	}
	
	// 添加页面
	@RequestMapping("/addv")
	public ModelAndView addRole(){
		
		ModelAndView mv = new ModelAndView("admin/role/add");
		return mv;
	}
	
	 //显示修改页面
	@RequestMapping(value="/updatev",method= RequestMethod.GET)
	public ModelAndView UpdateView(HttpServletRequest request){
		
		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		RolePO role = conn_role.get(uuid);
		strMap.put("list", role);
		ModelAndView mv = new ModelAndView("admin/role/modify");
		mv.addAllObjects(strMap);
		return mv;
	}
	
	// 异步读取。。。
	@ResponseBody
	@RequestMapping(value="/roleList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList() throws Exception {
		List<RolePO> listpo = conn_role.findAll();
		List<RoleVO> listvo = RoleVO.getConverter(RoleVO.class).convert(listpo, RoleVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}
	
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
 
		String roleName = request.getParameter("roleName");
		String description = request.getParameter("description");
		
		RolePO role = new RolePO();
		role.setRoleName(roleName);
		role.setDescription(description);
		conn_role.save(role);
		
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		RolePO role = conn_role.get(uuid);
		conn_role.delete(role);
		return "success";
	}
	
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		RolePO role = conn_role.get(uuid);
 
		String roleName = request.getParameter("roleName");
		String description = request.getParameter("description");
		
		role.setRoleName(roleName);
		role.setDescription(description);
		
		conn_role.update(role);
		
		return "success";
	}
}
