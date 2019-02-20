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

import com.guolaiwan.bussiness.admin.dao.ZbiaoDAO;
import com.guolaiwan.bussiness.admin.po.ZbiaoPO;

import pub.caterpillar.mvc.controller.BaseController;


@Controller
@RequestMapping("/admin/zbiao")
public class ZbiaoController extends BaseController{

	@Autowired
	private ZbiaoDAO conn_biao;


	//页面
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("admin/zbiao/list",strMap);
		return mv;
	}

	//获取列表
	@ResponseBody
	@RequestMapping(value="/list.do",method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page ,int limit){
		Map<String, Object> strMap=new HashMap<String, Object>();
		List<ZbiaoPO> biaoList  = conn_biao.findAll(page, limit);
		int count = conn_biao.countAll();
		strMap.put("data", biaoList);
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("code", "0");
		return strMap;
	}

	//添加数据页面
	@RequestMapping(value="/add",method= RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("admin/zbiao/add",strMap);
		return mv;
	}

	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do",method= RequestMethod.POST)
	public String add(HttpServletRequest request){
		String name = request.getParameter("name");
		String enName = request.getParameter("enName");
		String modular = request.getParameter("modular");
		ZbiaoPO biao = new ZbiaoPO();
		biao.setUpdateTime(new Date());
		biao.setEnName(enName);
		biao.setName(name);
		biao.setModular(modular);
		conn_biao.save(biao);
		return "success";
	}

	//修改数据
	@ResponseBody
	@RequestMapping(value="/edit.do",method= RequestMethod.POST)
	public String edit(HttpServletRequest request) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		long id = Long.parseLong(request.getParameter("id"));
		String field = request.getParameter("field");
		String value = request.getParameter("value");
		ZbiaoPO biao = conn_biao.get(id);
		//字符串首字母转成大写
		char[] cs = field.toCharArray();
		cs[0]-=32;
		String.valueOf(cs);
		//反射
		Class<ZbiaoPO> ZbiaoClass = ZbiaoPO.class;
		ZbiaoClass.getDeclaredMethod("set"+String.valueOf(cs), String.class).invoke(biao, value);
		//方法名，输入参数的类型，对象，输入参数的值
		conn_biao.save(biao);
		return "success";
	}


	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do",method= RequestMethod.POST)
	public String del(HttpServletRequest request) {
		String uuid = request.getParameter("uuid");
		conn_biao.deleteByUuid(uuid);;
		return "success";
	}



}
