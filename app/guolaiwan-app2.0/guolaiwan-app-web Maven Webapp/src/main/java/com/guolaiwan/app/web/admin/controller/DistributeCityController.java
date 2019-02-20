package com.guolaiwan.app.web.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.distribute.vo.RegionVo;
import com.guolaiwan.bussiness.distribute.dao.RegionDao;
import com.guolaiwan.bussiness.distribute.po.RegionPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/distributecity")
public class DistributeCityController extends BaseController {
	@Autowired
	private RegionDao conn_Region;
	
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getCityInfos(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();

		strMap.put("allcount", conn_Region.GetCountByPage());

		ModelAndView mv = new ModelAndView("admin/distributecity/list",strMap);
		return mv;
	}	
	
	//显示添加页面
	@RequestMapping(value = "/addv",method = RequestMethod.GET)
	public ModelAndView addCityInfo(HttpServletRequest request) throws Exception{
		
		Long parentId = (long) request.getSession().getAttribute("parentId");
		ModelAndView mv = new ModelAndView("admin/distributecity/add");
		mv.addObject("parentId", parentId);
		return mv;
	}
	
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do/{parentId}", method= RequestMethod.POST)
	public String add(@PathVariable long parentId,HttpServletRequest request) throws Exception {
		
		String name = request.getParameter("name");
		RegionPo regionPo = new RegionPo();
		
		String code =String.valueOf(conn_Region.getMaxId()+1000l);
		regionPo.setUpdateTime(new Date());
		regionPo.setParentId(parentId);
		regionPo.setName(name);
		regionPo.setCode(code);
		conn_Region.save(regionPo);
		return "success";
	}	
	
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/citylist.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(HttpServletRequest request,int page,int limit) throws Exception {
		
		List<RegionPo> regionPos = conn_Region.queryAllFirstRegion(page,limit);
		List<RegionVo> regionVos = RegionVo.getConverter(RegionVo.class).convert(regionPos, RegionVo.class);
		
		int count = conn_Region.GetCountbyPage(0l);
			
		Map<String, Object> map= new HashMap<String, Object>();
		
		map.put("id",regionVos.get(0).getParentId());
		map.put("data", regionVos);
		map.put("count", count);
		map.put("msg", "");
		map.put("code", "0");
		
		Long parentId = regionVos.get(0).getParentId();
		HttpSession session = request.getSession();
		session.setAttribute("parentId", parentId);
		
		return map;
	}
	
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/lastclass.do/{parentId}", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getlastList(@PathVariable long parentId,HttpServletRequest request, int page,int limit) throws Exception {

		
		List<RegionPo> regionPos = conn_Region.querylastRegionByParent(parentId,page,limit);
		List<RegionVo> regionVos = RegionVo.getConverter(RegionVo.class).convert(regionPos, RegionVo.class);
		
		Long id;
		int count;
		Map<String, Object> map= new HashMap<String, Object>();
		
		if(regionVos.isEmpty()){
			id = 0l;
		}else{
			id = regionVos.get(0).getParentId();
			parentId = regionVos.get(0).getParentId();
		}
		regionPos = conn_Region.querylastnRegionByParent(id,page,limit);
		regionVos = RegionVo.getConverter(RegionVo.class).convert(regionPos, RegionVo.class);
		
		count = conn_Region.GetCountbyPage(id);
//		map.put("parentId", id);
		map.put("data", regionVos);
		map.put("count", count);
		map.put("msg", "");
		map.put("code", "0");

		HttpSession session = request.getSession();
		session.setAttribute("parentId", parentId);
		
		return map;
	}
	
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/nextclass.do/{id}", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getnextList(@PathVariable long id,HttpServletRequest request, int page,int limit) throws Exception {

		List<RegionPo> regionPos = conn_Region.querynextRegionByParent(id,page,limit);
		List<RegionVo> regionVos = RegionVo.getConverter(RegionVo.class).convert(regionPos, RegionVo.class);
		int count = conn_Region.GetCountbyPage(id);
		
		Map<String, Object> map= new HashMap<String, Object>();
//		map.put("parentId", id);
		map.put("data", regionVos);
		map.put("count", count);
		map.put("msg", "");
		map.put("code", "0");
		
		Long parentId;
		if(count == 0)
		{
			parentId = id;
		}else{
			parentId = regionVos.get(0).getParentId();
		}

		HttpSession session = request.getSession();
		session.setAttribute("parentId", parentId);
		
		return map;
	}
	
	//显示修改页面
	@RequestMapping(value="/updatev/{uuid}/{parentId}",method= RequestMethod.GET)
	public ModelAndView UpdateView(@PathVariable String uuid,@PathVariable Long parentId,HttpServletRequest request){

		Map<String, Object> strMap=new HashMap<String, Object>();
		RegionPo regionPo = conn_Region.get(uuid);
		strMap.put("list", regionPo);
		strMap.put("parentId", parentId);


		ModelAndView mv = new ModelAndView("admin/distributecity/Modify");
		mv.addAllObjects(strMap);

		return mv;
	}	
	
	//修改数据
	@ResponseBody
	@RequestMapping(value="/updatev/{uuid}/update.do/{parentId}", method= RequestMethod.POST)
	public String update(@PathVariable long parentId,HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		RegionPo regionPo = conn_Region.get(uuid);
		
		String name = request.getParameter("name");
		regionPo.setName(name);
		regionPo.setParentId(parentId);
		conn_Region.update(regionPo);

		return "success";
	}

	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		
		String ids = request.getParameter("id"); 
		long id = Integer.parseInt(ids);
		RegionPo regionPo = conn_Region.get(id);
		System.out.println(regionPo);
		conn_Region.delete(regionPo);
		
		List<RegionPo> regionPos = conn_Region.findByField("parentId", id);
		for (RegionPo regionPo2:regionPos) {
			deletRegion(regionPo2);
		}
//		conn_Region.delete(regionPo);
		
//		String ids = request.getParameter("id"); 
//		String uuidf = request.getParameter("uuid");
//		System.out.println(uuidf);
//		long id = Integer.parseInt(ids);
//		
//		RegionPo regionPo ;
////		conn_Region.delete(regionPo);
//		
//		List<RegionPo> regionPos = conn_Region.querydellistRegionByParent(id);
//		
//		String uuid ; 
//		
//		if(regionPos != null){
//			for (int i = 0; i < regionPos.size(); i++) {
//				uuid = regionPos.get(i).getUuid();
//				System.out.println(uuid);
//				regionPo = conn_Region.get(uuid);
//				conn_Region.delete(regionPo);
//				regionPos = conn_Region.querydelnlistRegionByParent(regionPos.get(i).getId());
//				
//				if(regionPos != null){
//					for (int j = 0; j < regionPos.size(); j++) {
//						uuid = regionPos.get(j).getUuid();
//						System.out.println(uuid);
//						regionPo = conn_Region.get(uuid);
//						conn_Region.delete(regionPo);
//
//						regionPos = conn_Region.querydelnlistRegionByParent(regionPos.get(j).getId());
//						if(regionPos != null){
//							for (int k = 0; k < regionPos.size(); k++) {
//								uuid = regionPos.get(k).getUuid();
//								System.out.println(uuid);
//								regionPo = conn_Region.get(uuid);
//								conn_Region.delete(regionPo);
//								
//							}
//						}
//					}
//				}
//				
//		}	
//		}

		return "success";
	}
	
	public void deletRegion(RegionPo regionPo)
	{
		List<RegionPo> regionPos = conn_Region.findByField("parentId", regionPo.getId());
		for(RegionPo regionPo2:regionPos){
			deletRegion(regionPo2);
		}
		conn_Region.delete(regionPo);
	}
	
}

