package com.chenxi.web.stock.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

import com.chenxi.web.stock.dao.FinanceDao;
import com.chenxi.web.stock.dao.StockDao;
import com.chenxi.web.stock.po.FinancePo;
import com.chenxi.web.stock.po.StockPo;
import com.chenxi.web.yueba.admin.po.ComboPo;
import com.sun.tools.hat.internal.util.Comparer;

@Controller
@RequestMapping("/stock")
public class StockController {
	@Autowired
	StockDao stockDao;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("stock/stock", strMap);
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", stockDao.countAll());
		strMap.put("data", stockDao.findAll(page, limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
	
	
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView("stock/addStock");
		
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
	
		
		StockPo stockPo=new StockPo();
		stockPo.setCode(code);
		stockPo.setName(name);
		stockDao.save(stockPo);
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		if(request.getParameter("id")!=null){
			stockDao.delete(Long.parseLong(request.getParameter("id")));
		}
		return "success";
	}
	
	
	
	@Autowired
	FinanceDao financeDao;
	
	
	@RequestMapping(value = "/finance/index", method = RequestMethod.GET)
	public ModelAndView findex(HttpServletRequest request,long stockId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("stockId", stockId);
		ModelAndView mv = new ModelAndView("stock/finance", strMap);
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/finance/list", method = RequestMethod.GET)
	public Map<String, Object> flist(Long stockId,int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", financeDao.countByField("stockId", stockId));
		strMap.put("data", financeDao.findByField("stockId", stockId, page, limit));
		strMap.put("code", 0);
		strMap.put("msg", "");
		return strMap;
	}
	
	
	@RequestMapping("/finance/add")
	public ModelAndView fadd(long stockId){
		ModelAndView mv = new ModelAndView("stock/addFinance");
		mv.addObject("stockId",stockId);
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/finance/add.do", method= RequestMethod.POST)
	public String fadd(HttpServletRequest request,long stockId) throws Exception {
		
		
		String year = request.getParameter("year");
		String allget = request.getParameter("allget");
		String fleft = request.getParameter("fleft");
		String realLeft = request.getParameter("realLeft");
		String returnassets = request.getParameter("returnassets");
		String realcash = request.getParameter("realcash");
		String postcash = request.getParameter("postcash");
		String getcash = request.getParameter("getcash");
		
		FinancePo financePo=new FinancePo();
		financePo.setStockId(stockId);
		financePo.setYear(Integer.parseInt(year));
		financePo.setAllget(Double.parseDouble(allget));
		financePo.setFleft(Double.parseDouble(fleft));
		financePo.setRealLeft(Double.parseDouble(realLeft));
		financePo.setReturnassets(Double.parseDouble(returnassets));
		financePo.setRealcash(Double.parseDouble(realcash));
		financePo.setPostcash(Double.parseDouble(postcash));
		financePo.setGetcash(Double.parseDouble(getcash));
		
		financeDao.save(financePo);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/finance/del.do", method= RequestMethod.POST)
	public String fdel(HttpServletRequest request) throws Exception {
		if(request.getParameter("id")!=null){
			financeDao.delete(Long.parseLong(request.getParameter("id")));
		}
		return "success";
	}
	
	//report相关
	@RequestMapping(value = "/report/index", method = RequestMethod.GET)
	public ModelAndView rindex(HttpServletRequest request,long stockId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
        List<FinancePo> financePos=financeDao.findAll();
        Collections.sort(financePos,new Comparator<FinancePo>() {
			@Override
			public int compare(FinancePo o1, FinancePo o2) {
				// TODO Auto-generated method stub
				return o1.getYear()-o2.getYear();
			}
		});
        
        
		List<Integer> baselabels=new ArrayList<Integer>();
		List<Double> allget=new ArrayList<Double>();
		List<Double> fleft=new ArrayList<Double>();
		List<Double> realleft=new ArrayList<Double>();
		List<Double> returnassets=new ArrayList<Double>();
		List<Double> realcash=new ArrayList<Double>();
		List<Double> postcash=new ArrayList<Double>();
		List<Double> getcash=new ArrayList<Double>();
		for (FinancePo financePo : financePos) {
			baselabels.add(financePo.getYear());
			allget.add(financePo.getAllget());
			fleft.add(financePo.getFleft());
			realleft.add(financePo.getRealLeft());
			returnassets.add(financePo.getReturnassets());
			realcash.add(financePo.getRealcash());
			postcash.add(financePo.getPostcash());
			getcash.add(financePo.getGetcash());
		}
		strMap.put("baselabels", baselabels);
		strMap.put("allget", allget);
		strMap.put("fleft", fleft);
		strMap.put("realleft", realleft);
		strMap.put("returnassets", returnassets);
		strMap.put("realcash", realcash);
		strMap.put("postcash", postcash);
		strMap.put("getcash", getcash);
		
		ModelAndView mv = new ModelAndView("stock/allreport", strMap);
		return mv;
	}
	
}
