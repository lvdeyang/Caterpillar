package com.guolaiwan.app.web.admin.controller;

import java.lang.reflect.InvocationTargetException;
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

import com.guolaiwan.app.web.admin.vo.CompanyVO;
import com.guolaiwan.app.web.admin.vo.ProductPackVO;
import com.guolaiwan.bussiness.admin.dao.CityInfoDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyRelDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.CompanyType;
import com.guolaiwan.bussiness.admin.enumeration.RecommendType;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.CompanyRelPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.sun.jna.platform.win32.WinDef.LONG;

import pub.caterpillar.mvc.controller.BaseController;


@Controller
@RequestMapping("/admin/company")
public class CompanyController extends BaseController{

	@Autowired
	private CompanyDAO conn_company;
	@Autowired
	private CompanyRelDAO conn_companyRel;
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private CityInfoDAO conn_city;


	//列表页面
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws Exception{
		Map<String, Object> strMap=new HashMap<String, Object>();
		List<CityInfoPO> citys = conn_city.getCitys();
		SysConfigPO sysConfigPO = conn_sysConfig.getSysConfig();
		strMap.put("sysConfigPO", sysConfigPO);
		strMap.put("citys", citys);
		ModelAndView mv = new ModelAndView("admin/company/list",strMap);
		return mv;
	}

	//获取列表
	@ResponseBody
	@RequestMapping(value="/list.do",method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page ,int limit) throws Exception{
		Map<String, Object> strMap=new HashMap<String, Object>();
		List<CompanyPO> companyPOs  = conn_company.findAll(page, limit);
		List<CompanyVO> companys = CompanyVO.getConverter(CompanyVO.class).convert(companyPOs, CompanyVO.class);

		int count = conn_company.countAll();
		strMap.put("data", companys);
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("code", "0");
		return strMap;
	}

	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do",method= RequestMethod.POST)
	public String add(HttpServletRequest request){
		CompanyPO company = new CompanyPO();
		company.setUpdateTime(new Date());
		company.setComCode(String.valueOf((1000l+conn_company.getMaxId())));
		company.setEnable(1);
		company.setcType(CompanyType.SON);
		conn_company.save(company);

		return "success";
	}

	//修改数据
	@ResponseBody
	@RequestMapping(value="/edit.do",method= RequestMethod.POST)
	public String edit(HttpServletRequest request) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		long id = Long.parseLong(request.getParameter("id"));
		String field = request.getParameter("field");
		String value = request.getParameter("value");
		if(field.equals("comCode")){
			List<CompanyPO> companys =  conn_company.findByField("comCode", value);
			if(!(companys==null||companys.size()==0)){
				return "hasComCode";
			}
		}

		CompanyPO company = conn_company.get(id);
		//字符串首字母转成大写
		char[] cs = field.toCharArray();
		cs[0]-=32;
		String.valueOf(cs);
		//反射
		Class<CompanyPO> comClass = CompanyPO.class;

		comClass.getDeclaredMethod("set"+String.valueOf(cs), String.class).invoke(company, value);
		//方法名，输入参数的类型，对象，输入参数的值
		conn_company.save(company);
		return "success";
	}


	//修改数据
	@ResponseBody
	@RequestMapping(value="/updateCity.do",method= RequestMethod.POST)
	public String updateCity(HttpServletRequest request) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		long id = Long.parseLong(request.getParameter("id"));
		String cityCode = request.getParameter("cityCode");
		String cityName = request.getParameter("cityName");
		

		CompanyPO company = conn_company.get(id);
		company.setCityCode(cityCode);
		company.setCityName(cityName);
		conn_company.saveOrUpdate(company);
		
		return "success";
	}


	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do",method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		//删除所有推荐的关联表
		conn_companyRel.deleteByComId(id);
		//删除子公司
		conn_company.delete(id);

		return "success";
	}

	//是否启用
	@ResponseBody
	@RequestMapping(value="/changeIsv.do",method= RequestMethod.POST)
	public String changeIsv(HttpServletRequest request) {
		long id = Long.parseLong(request.getParameter("id"));
		int value = Integer.parseInt(request.getParameter("value"));
		CompanyPO company = conn_company.get(id);
		company.setEnable(value);
		conn_company.saveOrUpdate(company);;
		return "success";
	}




	//选择图片
	@ResponseBody
	@RequestMapping(value="/pic.do",method= RequestMethod.POST)
	public String pic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long picId = Long.parseLong(request.getParameter("picId"));
		long id = Long.parseLong(request.getParameter("id"));
		CompanyPO company = conn_company.get(id);
		company.setPic(pic);
		company.setPicId(picId);
		conn_company.saveOrUpdate(company);;
		return "success";
	}


	//推荐按钮跳出子公司页面
	@RequestMapping(value="/reComSon",method= RequestMethod.GET)
	public ModelAndView reComSon(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String source = request.getParameter("source");
		Long sourceId = Long.parseLong(request.getParameter("sourceId"));
		//获取所有子公司
		List<CompanyPO> comSonList = conn_company.findSonList();
		List<CompanyVO> comSonVOList = CompanyVO.getConverter(CompanyVO.class).convert(comSonList, CompanyVO.class);
		//获取所有的关联类型,关联id
		List<CompanyRelPO> comRels = conn_companyRel.findByRelIdS(sourceId,source);
		for (CompanyVO companyVO : comSonVOList) {
			for (CompanyRelPO companyRelPO : comRels) {
				if(companyVO.getId()==companyRelPO.getSonComId()){
					companyVO.setCheck(1);
				}
			}
		}

		strMap.put("comSonList", comSonVOList);
		strMap.put("source", source);
		strMap.put("sourceId", sourceId);
		ModelAndView mv = new ModelAndView("/admin/company/sonList",strMap);
		return mv;
	}

	//推荐按钮
	@ResponseBody
	@RequestMapping(value="/reComSon.do",method= RequestMethod.POST)
	public Map<String, Object> reComSonDo(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String source = request.getParameter("source");
		long sourceId = Long.parseLong(request.getParameter("sourceId"));
		String comSonIds = request.getParameter("comSonIds");
		//删除  关联类型 关联id的数据
		conn_companyRel.deleteByRelId(sourceId,source);

		//没传
		if(comSonIds==null){
			strMap.put("msg", "error");
			return strMap;
		} 
		//啥也没选
		if(comSonIds.length()==0){
			strMap.put("msg", "success");
			strMap.put("count", 0);
			return strMap;
		} 
		String[] comSons = comSonIds.split(",");
		int count =0;
		for (String comSonId : comSons) {
			CompanyRelPO companyRel = new CompanyRelPO();
			companyRel.setUpdateTime(new Date());
			companyRel.setRelaId(sourceId);
			companyRel.setrType(RecommendType.fromString(source));
			companyRel.setSonComId(Long.parseLong(comSonId));
			conn_companyRel.save(companyRel);
			count++;
		}

		strMap.put("msg", "success");
		strMap.put("count", count);

		return strMap;
	}


}