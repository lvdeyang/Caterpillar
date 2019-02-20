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

import com.guolaiwan.app.web.admin.vo.ReSpecialVO;
import com.guolaiwan.bussiness.admin.dao.CompanyRelDAO;
import com.guolaiwan.bussiness.admin.dao.ReSpecialDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.ReSpecialPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/reSpecial")
public class ReSpecialController extends BaseController{
	@Autowired
	private ReSpecialDAO conn_reSpecial;

	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private CompanyRelDAO conn_companyRel;
	
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getreSpecial(HttpServletRequest request){
		long comId = getLoginInfo().getComId();
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfigPO = conn_sysConfig.getSysConfig();
		strMap.put("sysConfigPO", sysConfigPO);
		ModelAndView mv =null;
		if(comId==1l){
			mv = new ModelAndView("admin/reSpecial/list",strMap);
			return mv;	
		}
		mv = new ModelAndView("admin/reSpecial/sonList",strMap);
		return mv;
	}


	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="list.do",method = RequestMethod.POST)
	public Map<String,Object> GetList(int page,int limit) throws Exception{
		long comId = getLoginInfo().getComId();
		List<ReSpecialPO> reSpecialPOs = conn_reSpecial.findByCom(comId, page, limit);
		int count = conn_reSpecial.countByCom(comId);
		List<ReSpecialVO>  reSpecialVOs= ReSpecialVO.getConverter(ReSpecialVO.class).convert(reSpecialPOs, ReSpecialVO.class);
		
		for (ReSpecialVO reSpecialVO : reSpecialVOs) {
			reSpecialVO.setReCount(conn_companyRel.countByRelIdS(reSpecialVO.getId(),"SPECIAL"));
		}
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", reSpecialVOs);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count",count );
		return map;			
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		ReSpecialPO reSpecial = new ReSpecialPO();
		reSpecial.setUpdateTime(new Date());
		reSpecial.setComId(getLoginInfo().getComId());
		reSpecial.setComName(getLoginInfo().getComName());
		conn_reSpecial.save(reSpecial);
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id =Long.parseLong(request.getParameter("id"));
		conn_reSpecial.delete(id);
		conn_companyRel.deleteByRelId(id, "SPECIAL");
		return "success";
	}

	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String field = request.getParameter("field");
		String value = request.getParameter("value");


		ReSpecialPO reSpecial = conn_reSpecial.get(id);

		if(field.equals("sort")){
			reSpecial.setSort(Integer.parseInt(value));
		}else{
			//字符串首字母转成大写
			char[] cs = field.toCharArray();
			cs[0]-=32;
			String.valueOf(cs);
			//反射
			Class<ReSpecialPO> reSpecialClass = ReSpecialPO.class;

			reSpecialClass.getDeclaredMethod("set"+String.valueOf(cs), String.class).invoke(reSpecial, value);
			//方法名，输入参数的类型，对象，输入参数的值
		}
		conn_reSpecial.save(reSpecial);

		return "success";
	}

	//是否显示
	@ResponseBody
	@RequestMapping(value="/changeIsv.do",method= RequestMethod.POST)
	public String changeIsv(HttpServletRequest request) {
		long id = Long.parseLong(request.getParameter("id"));
		int value = Integer.parseInt(request.getParameter("value"));
		ReSpecialPO reSpecial = conn_reSpecial.get(id);
		reSpecial.setEnable(value);
		conn_reSpecial.saveOrUpdate(reSpecial);
		return "success";
	}

	//选择图片
	@ResponseBody
	@RequestMapping(value="/pic.do",method= RequestMethod.POST)
	public String pic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long picId = Long.parseLong(request.getParameter("picId"));
		long id = Long.parseLong(request.getParameter("id"));
		ReSpecialPO reSpecial = conn_reSpecial.get(id);
		reSpecial.setPicId(picId);
		reSpecial.setSlidepic(pic);
		conn_reSpecial.saveOrUpdate(reSpecial);
		return "success";
	}

}