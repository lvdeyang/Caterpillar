package com.guolaiwan.app.web.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.CityInfoVO;
import com.guolaiwan.app.web.admin.vo.CityInfoVO;
import com.guolaiwan.bussiness.admin.dao.CityInfoDAO;
import com.guolaiwan.bussiness.admin.dao.CityInfoDAO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/cityinfo")
public class CityInfoController extends BaseController{
	@Autowired
	private CityInfoDAO conn_CityInfo;
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getCityInfos(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();

		strMap.put("allcount", conn_CityInfo.GetCountByPage());

		ModelAndView mv = new ModelAndView("admin/cityinfo/list",strMap);
		return mv;
	}
	//添加页面
	@RequestMapping("/addv")
	public ModelAndView addCityInfo(){

		ModelAndView mv = new ModelAndView("admin/cityinfo/add");
		return mv;
	}
	//异步读取
	@ResponseBody
	@RequestMapping(value="/changeIsv.do", method= RequestMethod.POST)
	public String changeIsv(Long id,int value) throws Exception {
		CityInfoPO cityInfo = conn_CityInfo.get(id);
		cityInfo.setEnable(value);
		conn_CityInfo.saveOrUpdate(cityInfo);
		return "success";
	}
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page,int limit) throws Exception {
		List<CityInfoPO> cityinfopo=conn_CityInfo.GetListbyPage(page, limit);
		List<CityInfoVO> cityinfovo = CityInfoVO.getConverter(CityInfoVO.class).convert(cityinfopo, CityInfoVO.class);
		int count = conn_CityInfo.GetCountByPage();
		
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", cityinfovo);
		map.put("count", count);
		map.put("msg", "");
		map.put("code", "0");
		return map;
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String cityName = request.getParameter("cityName");
		String cityDomain = request.getParameter("cityDomain");
		CityInfoPO cityInfo = new CityInfoPO();
		
		String cityCode =String.valueOf(conn_CityInfo.getMaxId()+1000l);
		cityInfo.setUpdateTime(new Date());
		cityInfo.setCityName(cityName);
		cityInfo.setCityDomain(cityDomain);
		cityInfo.setCityCode(cityCode);
		cityInfo.setEnable(1);
		conn_CityInfo.save(cityInfo);
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		CityInfoPO cityinfo = conn_CityInfo.get(uuid);
		conn_CityInfo.delete(cityinfo);
		return "success";
	}
	//显示修改页面
	@RequestMapping(value="/updatev",method= RequestMethod.GET)
	public ModelAndView UpdateView(HttpServletRequest request){

		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		CityInfoPO cityinfo = conn_CityInfo.get(uuid);
		strMap.put("list", cityinfo);


		ModelAndView mv = new ModelAndView("admin/cityinfo/Modify");
		mv.addAllObjects(strMap);

		return mv;
	}
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		CityInfoPO cityinfo = conn_CityInfo.get(uuid);


		String cityName = request.getParameter("cityName");
		String cityDomain = request.getParameter("cityDomain");
		cityinfo.setCityName(cityName);
		cityinfo.setCityDomain(cityDomain);

		conn_CityInfo.update(cityinfo);

		return "success";
	}


}
