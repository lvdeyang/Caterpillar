package com.chenxi.web.yueba.mobile.controller;

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

import com.chenxi.web.dao.ArticleDao;
import com.chenxi.web.dao.OnlineClassesDao;
import com.chenxi.web.dao.ProductDao;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ClassesPo;
import com.chenxi.web.po.OnlineClassesPo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.yueba.admin.dao.ComboDao;
import com.chenxi.web.yueba.admin.dao.CommentDao;
import com.chenxi.web.yueba.admin.dao.DaysTypeDao;
import com.chenxi.web.yueba.admin.dao.OrderDao;
import com.chenxi.web.yueba.admin.dao.RegionDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.ComboPo;
import com.chenxi.web.yueba.admin.po.DaysTypePo;
import com.chenxi.web.yueba.admin.po.RegionsPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import net.sf.ehcache.search.parser.MValue;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/worker")
public class MWorkerContoller extends BaseController {
	
	@Autowired
	WorkerDao conn_worker;
	@Autowired
	RegionDao conn_region;
	@Autowired
	DaysTypeDao conn_daystype;
	@Autowired
	ComboDao conn_combo;
	@Autowired
	CommentDao conn_comment;
	@Autowired
	OrderDao conn_order;
	
	@RequestMapping(value = "/mobile/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		WorkerPo workerPo=conn_worker.get(workerId);
		workerPo.setOrderCount(conn_order.countCompleteByWorker(workerPo.getId()));
		strMap.put("worker", workerPo);
		
		StringBuffer sb=new StringBuffer();
		sb.append("<tr><td></td>");
		List<RegionsPo> regionsPos=conn_region.findAll();
	
		for (RegionsPo regionsPo : regionsPos) {
			sb.append("<td>"+regionsPo.getRegionName()+"</td>");
		
		}
		sb.append("</tr>");
        List<DaysTypePo> daysTypePos=conn_daystype.findAll();
        for (DaysTypePo daysTypePo : daysTypePos) {
        	sb.append("<tr><td>"+daysTypePo.getDays()+"天</td>");
        	for (RegionsPo regionsPo : regionsPos) {
        		ComboPo comboPo=conn_combo.findByRegionAndDays(regionsPo.getRegionName(), daysTypePo.getDays(),workerPo.getLevel());
    			sb.append("<td>"+(comboPo==null?"-":comboPo.getPrice())+"</td>");
    			
    		}
        	sb.append("</tr>");
		}
        strMap.put("priceHtml", sb.toString());
        
        strMap.put("comments",conn_comment.findByworkerId(workerId, 1, 3));
        
		
		ModelAndView mv = new ModelAndView("yuebamobile/worker", strMap);
		return mv;
	}
	@RequestMapping(value = "/mobile/addorder", method = RequestMethod.GET)
	public ModelAndView addorder(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		WorkerPo workerPo=conn_worker.get(workerId);
		List<RegionsPo> regionsPos=conn_region.findAll();
		List<DaysTypePo> daysTypePos=conn_daystype.findAll();
		strMap.put("worker", workerPo);
		strMap.put("regions", regionsPos);
		strMap.put("daystypes", daysTypePos);
		ModelAndView mv = new ModelAndView("yuebamobile/subscribe", strMap);
		return mv;
	}
	@RequestMapping(value = "/mobile/apply", method = RequestMethod.GET)
	public ModelAndView apply(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("yuebamobile/apply", strMap);
		return mv;
	}
	
	
}
