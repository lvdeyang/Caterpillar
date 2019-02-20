package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
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

import com.guolaiwan.app.web.admin.vo.MenuVO;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.dao.RoleMenuDAO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.admin.po.RoleMenuPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/rolemenu")
public class RoleMenuController extends BaseController{
	
	@Autowired
	private RoleMenuDAO conn_rolemenu;
	
	@Autowired
	private MenuDAO conn_menu;
	
	    // 查询角色列表
		@RequestMapping(value="/list",method= RequestMethod.GET)
		public ModelAndView getRoles(HttpServletRequest request) throws Exception{
			
			String Roid=request.getParameter("uuid");
			String ctype="";
			if (request.getParameter("c")!=null)
			{
				 ctype=request.getParameter("c");
			}
			
			
			Map<String, Object> strMap = new HashMap<String, Object>();
			
			StringBuilder sbHtml=new StringBuilder();
			//循环父类
			List<MenuPO> listpo=conn_menu.findAll();
			List<MenuVO> listvo = MenuVO.getConverter(MenuVO.class).convert(listpo, MenuVO.class);
			
			List<MenuVO> flist=search(0L,listvo);
			
		    for (MenuVO menuVO : flist) {
				sbHtml.append("<tr>");
				sbHtml.append("<td>");
				sbHtml.append(menuVO.getName());
				sbHtml.append(" <input type=\"hidden\" name=\"classf\" value=\""+menuVO.getId()+"\">");
				sbHtml.append("</td>");
				//循环子类
				List<MenuVO> clist=search(menuVO.getId(),listvo);
				sbHtml.append("<td><div class=\"layui-input-block\">");
				for (MenuVO menuVOc : clist) {
					//判断检索是否存在该权限
					String[] fields={"roleId","menuId"};
					Object[] values={Integer.parseInt(Roid),Integer.parseInt(menuVOc.getId().toString()) };
					
					
				  	List<RoleMenuPO> findByFields = conn_rolemenu.findByFields(fields, values);
					if(findByFields.size()>0)
					   sbHtml.append("<input name=\"c_"+menuVO.getId()+"\" checked=\"\" type=\"checkbox\" value=\""+menuVOc.getId()+"\">"+menuVOc.getName());
					else
					   sbHtml.append("<input name=\"c_"+menuVO.getId()+"\" type=\"checkbox\" value=\""+menuVOc.getId()+"\">"+menuVOc.getName());
				}
				sbHtml.append(" </div></td>");
				
				sbHtml.append("</tr>");
			}
			
			
			strMap.put("str", sbHtml.toString());
			strMap.put("roid", Roid);
			strMap.put("c", ctype);
			ModelAndView mv = new ModelAndView("admin/roleMenu/list",strMap);
			return mv;
		}
		
		
		
		//后台获取操作数据
		
		@RequestMapping(value="/add.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
		public ModelAndView add(HttpServletRequest request) throws Exception {
	 
			int roleId=Integer.parseInt(request.getParameter("roid"));
	     	String[] fclassIDs = request.getParameterValues("classf");
	     	conn_rolemenu.deleteByField("roleId", roleId);//根据role删除
	     	for (String strf : fclassIDs) 
	     	{
	     		String[] chIDs= request.getParameterValues("c_"+strf);
	     		
	     		if(chIDs!=null){

		     		//父添加
		     		RoleMenuPO rmF=new  RoleMenuPO();
		     		rmF.setMenuFid(0);
		     		rmF.setRoleId(roleId);
		     		rmF.setMenuId(Integer.parseInt(strf));
		     		conn_rolemenu.save(rmF);
		     		//子循环添加
		     		for (String strc : chIDs) {
		     			RoleMenuPO rmc=new  RoleMenuPO();
		     			rmc.setMenuFid(Integer.parseInt(strf));
		     			rmc.setRoleId(roleId);
		     			rmc.setMenuId(Integer.parseInt(strc));
			     		conn_rolemenu.save(rmc);
					}
	     		}
	     	}
	     	ModelAndView mv=new ModelAndView("redirect:/admin/rolemenu/list?uuid="+roleId+"&c=1");
			return mv;
		}
		
		//获取父类
		public List<MenuVO> search(Long parentId,List<MenuVO> list){
			
			List<MenuVO> results = new ArrayList<>();
			   for(int i=0;i<list.size();i++){
				   if(((list.get(i))).getParentId()==parentId)
					   results.add(list.get(i));
				   }
				   return results;
		}
		
		

}
