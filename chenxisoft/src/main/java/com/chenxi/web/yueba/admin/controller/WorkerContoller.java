package com.chenxi.web.yueba.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.classes.WorkerStatus;
import com.chenxi.web.dao.ArticleDao;
import com.chenxi.web.dao.OnlineClassesDao;
import com.chenxi.web.dao.ProductDao;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ClassesPo;
import com.chenxi.web.po.OnlineClassesPo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.yueba.admin.dao.LabelDao;
import com.chenxi.web.yueba.admin.dao.LevelDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.LabelPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/worker")
public class WorkerContoller extends BaseController {
	@Autowired
	WorkerDao conn_worker;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("yuebaadmin/worker", strMap);
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_worker.countAll());
		strMap.put("data", conn_worker.findAll(page, limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		if(request.getParameter("id")!=null){
			conn_worker.delete(Long.parseLong(request.getParameter("id")));
		}
		return "success";
	}
	
	@RequestMapping("/check")
	public ModelAndView check(long id){
		ModelAndView mv = new ModelAndView("yuebaadmin/checkworker");
		mv.addObject("id", id);
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/agree.do", method= RequestMethod.POST)
	public String agree(HttpServletRequest request) throws Exception {
		String checkMsg = request.getParameter("checkMsg");
        String id=request.getParameter("id");
		WorkerPo workerPo=conn_worker.get(Long.parseLong(id));
		workerPo.setStatus(WorkerStatus.PASSED);
		workerPo.setCheckMsg(checkMsg);
		conn_worker.update(workerPo);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/refuse.do", method= RequestMethod.POST)
	public String refuse(HttpServletRequest request) throws Exception {
		String checkMsg = request.getParameter("checkMsg");
        String id=request.getParameter("id");
		WorkerPo workerPo=conn_worker.get(Long.parseLong(id));
		workerPo.setStatus(WorkerStatus.REFUSE);
		workerPo.setCheckMsg(checkMsg);
		conn_worker.save(workerPo);
		return "success";
	
	}
	@Autowired
	LevelDao conn_level;
	
	
	@RequestMapping(value = "/setlevel", method = RequestMethod.GET)
	public ModelAndView setlevel(HttpServletRequest request,long id) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("levelList", conn_level.findAll());
		strMap.put("id", id);
		ModelAndView mv = new ModelAndView("yuebaadmin/setlevel", strMap);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/setlevel.do", method= RequestMethod.POST)
	public String setleveldo(HttpServletRequest request) throws Exception {
		String level = request.getParameter("level");
		String id=request.getParameter("id");
		WorkerPo workerPo=conn_worker.get(Long.parseLong(id));
		workerPo.setLevel(level);
		conn_worker.update(workerPo);
       
		return "success";
	
	}
	
}
