package com.guolaiwan.app.gonghui.admin.controller;

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

import com.guolaiwan.bussiness.gonghui.dao.OnlineClassesDao;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/onlineclass")
public class OnlineClassesContoller extends BaseController {
	@Autowired OnlineClassesDao conn_onlineclass;
	
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(HttpServletRequest request,long classId,int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		if(request.getParameter("key[classId]")!=null){
			
			classId=Long.parseLong(request.getParameter("key[classId]").toString());
		}
		
		
		strMap.put("count", conn_onlineclass.countOnlineByClasses(classId));
		strMap.put("data", conn_onlineclass.findOnlineByClasses(classId,page,limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
}
