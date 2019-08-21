package com.guolaiwan.app.web.admin.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/activity")
public class ActivityController extends BaseController {

	@Autowired
	private ActivityDAO conn_activity;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	@Autowired
	private ActivityRelDAO conn_activityRel;

	@Autowired
	private ProductDAO conn_product;

	// 查询列表页面刘立强新增测试
	// 同时添加

	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView activityList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("admin/activity/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long comId = getLoginInfo().getComId();
		int count = conn_activity.countByCom(comId);
		List<ActivityPO> findAll = conn_activity.appFindBycomId(comId);
		long index=1;
		for (ActivityPO activity : findAll) {
			if(activity.getSortindex()==0){
				activity.setSortindex(index);
				conn_activity.update(activity);
			}
			index+=1;
		}
		List<ActivityPO> activitys = conn_activity.findByCom(comId, page, limit);
		List<ActivityVO> activityVOs = ActivityVO.getConverter(ActivityVO.class).convert(activitys, ActivityVO.class);

		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", activitys);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/activity/add");
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		long comId = getLoginInfo().getComId();
		String comName = getLoginInfo().getComName();
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String rule = request.getParameter("rule");
		int index = conn_activity.countByCom(comId);
		ActivityPO activity = new ActivityPO();
		activity.setUpdateTime(new Date());
		activity.setName(name);
		activity.setActivityRule(rule);
		activity.setComId(comId);
		activity.setComName(comName);
		activity.setSortindex(index+1);
		activity.setType(ActivityType.fromString(type));
		switch (type) {
		case "MANJIAN":
			long ceil = (long) (Double.parseDouble(request.getParameter("ceil")) * 100);
			long cut = (long) (Double.parseDouble(request.getParameter("cut")) * 100);
			activity.setCeil(ceil);
			activity.setCut(cut);
			break;
		case "DAZHE":
			int discount = Integer.parseInt(request.getParameter("discount"));
			activity.setDiscount(discount);
			break;
		case "JIFEN":
			int ratio = Integer.parseInt(request.getParameter("ratio"));
			activity.setRatio(ratio);
			break;
		case "FIXEDPRICE":
			long fixedPrice = (long) (Double.parseDouble(request.getParameter("fixedPrice")) * 100);
			activity.setFixedPrice(fixedPrice);
			break;
		}
		conn_activity.save(activity);
		return "success";
	}

	// 选择图片
	@ResponseBody
	@RequestMapping(value = "/pic.do", method = RequestMethod.POST)
	public String pic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long picId = Long.parseLong(request.getParameter("picId"));
		long id = Long.parseLong(request.getParameter("id"));
		ActivityPO activity = conn_activity.get(id);
		activity.setPic(pic);
		activity.setPicId(picId);
		conn_activity.saveOrUpdate(activity);
		return "success";
	}

	// 选择活动图片
	@ResponseBody
	@RequestMapping(value = "/rulepic.do", method = RequestMethod.POST)
	public String rulepic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long picId = Long.parseLong(request.getParameter("picId"));
		long id = Long.parseLong(request.getParameter("id"));
		ActivityPO activity = conn_activity.get(id);
		activity.setRulepic(pic);
		activity.setRulepicId(picId);
		conn_activity.saveOrUpdate(activity);
		return "success";
	}	
		
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
		conn_activity.delete(id);
		conn_activityRel.deleteByAcId(id);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/recommend.do", method = RequestMethod.POST)
	public String recommend(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		ActivityPO activityPo = conn_activity.get(id);
		activityPo.setRecommend(1);
		conn_activity.save(activityPo);

		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/cancelrecommend.do", method = RequestMethod.POST)
	public String cancelrecommend(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		ActivityPO activityPo = conn_activity.get(id);
		activityPo.setRecommend(0);
		conn_activity.save(activityPo);
		return "success";
	}

	// 编辑数据
	@ResponseBody
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String edit(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		ActivityPO activity = conn_activity.get(id);

		String field = request.getParameter("field");
		if (field.equals("type")) {
			String type = request.getParameter("type");
			switch (type) {
			case "JIFEN":
				int ratio = Integer.parseInt(request.getParameter("ratio"));
				activity.setRatio(ratio);
				break;
			case "DAZHE":
				int discount = Integer.parseInt(request.getParameter("discount"));
				activity.setDiscount(discount);
				break;
			case "MANJIAN":
				int ceil = (int) (Double.parseDouble(request.getParameter("ceil")) * 100);
				int cut = (int) (Double.parseDouble(request.getParameter("cut")) * 100);
				activity.setCeil(ceil);
				activity.setCut(cut);
				break;
			case "FIXEDPRICE":
				int fixedPrice = (int) (Double.parseDouble(request.getParameter("fixedPrice")) * 100);
				activity.setFixedPrice(fixedPrice);
				
				break;
			}

			conn_activity.save(activity);
			return "success";
		}
		if (field.equals("name")) {
			String name = request.getParameter("value");
			activity.setName(name);
			conn_activity.save(activity);
			return "success";
		}else if(field.equals("activityRule")){
			String activityRule = request.getParameter("value");
			activity.setActivityRule(activityRule);
			conn_activity.save(activity);
			return "success";
		}else if(field.equals("fixedPrice")){
			String fixedPrice = request.getParameter("value");
			activity.setFixedPrice(Long.parseLong(fixedPrice));
			conn_activity.save(activity);
			return "success";
		}

		return "error";

	}

	// 展示产品页面
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ModelAndView pList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long activityId = Long.parseLong(request.getParameter("activityId"));
		String content = request.getParameter("content");
		if (content != null && content != "") {
			content = encodeStr(content);
		}
		ActivityPO activity = conn_activity.get(activityId);
		strMap.put("activity", activity);
		strMap.put("content", content);
		ModelAndView mv = new ModelAndView("admin/activity/productList", strMap);
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/productList.do", method = RequestMethod.POST)
	public Map<String, Object> pListdo(int page, int limit, HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		String pName = request.getParameter("pName");
		long acId = Long.parseLong(request.getParameter("acId"));

		String pId = request.getParameter("pId");
		Long pidl = null;
		if (pId != null && pId != "") {
			pidl = Long.parseLong(pId);
		}

		List<ActivityRelPO> activityRels = conn_activityRel.findByAcId(acId, pName, pidl, page, limit);
		int count = conn_activityRel.countByAcId(acId, pName);
		List<ActivityRelVO> activityRelVOs = ActivityRelVO.getConverter(ActivityRelVO.class).convert(activityRels,
				ActivityRelVO.class);
		strMap.put("data", activityRelVOs);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		return strMap;
	}

	// 弹出添加产品页面
	@RequestMapping(value = "/bdPro", method = RequestMethod.GET)
	public ModelAndView bdPro(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String acId = request.getParameter("acId");
		strMap.put("acId", acId);
		ModelAndView mv = new ModelAndView("admin/activity/bdPro", strMap);
		return mv;
	}

	// 弹出添加产品页面
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String acId = request.getParameter("acId");
		strMap.put("acId", acId);
		ModelAndView mv = new ModelAndView("admin/activity/bdPro", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/proList.do", method = RequestMethod.POST)
	public Map<String, Object> proList(int page, int limit, HttpServletRequest request) throws Exception {
		long comId = getLoginInfo().getComId();

		Map<String, Object> map = new HashMap<String, Object>();
		String pName = request.getParameter("pName");
		String mName = request.getParameter("mName");

		String pId = request.getParameter("pId");
		if (pId != null && pId != "") {
			long pIdLo = Long.parseLong(pId);
			map.put("id", pIdLo);
		}
		map.put("comId", comId);
		map.put("productName", pName);
		map.put("productMerchantName", mName);
		int count = conn_product.getCountByPageE(map);
		List<ProductPO> listpo = conn_product.getListByPageE(map, page, limit);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("data", listvo);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		return strMap;
	}

	@ResponseBody
	@RequestMapping(value = "/bdPro.do", method = RequestMethod.POST)
	public String bdProDo(long pId, long acId, String pName) {
		int count = conn_activityRel.countByPro(pId);
		if (count != 0) {
			return "chongfu";
		}
		ActivityRelPO activityRel = new ActivityRelPO();
		activityRel.setProductName(pName);
		activityRel.setUpdateTime(new Date());
		activityRel.setActivityId(acId);
		activityRel.setProductId(pId);
		conn_activityRel.save(activityRel);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/delRel.do", method = RequestMethod.POST)
	public String bdProDo(long relId) {
		conn_activityRel.delete(relId);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/delAll.do", method = RequestMethod.POST)
	public String delAll(long acId) {
		conn_activityRel.deleteByAcId(acId);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/update.do/{id}" , method = RequestMethod.GET)
	public ModelAndView toUpdate(@PathVariable long id){
		ModelAndView mav = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ActivityRelPO activityRelPO = conn_activityRel.get(id);
		ProductPO product = conn_product.get(activityRelPO.getProductId());
		map.put("product", product);
		map.put("proid", id);
		map.put("rel", activityRelPO);
		map.put("start", DateUtil.format(activityRelPO.getBeginDate(), "yyyy-MM-dd HH:mm:ss"));
		map.put("end", DateUtil.format(activityRelPO.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
		map.put("startTime", DateUtil.format(activityRelPO.getBeginTime(), "HH:mm:ss"));
		map.put("endTime", DateUtil.format(activityRelPO.getEndTime(), "HH:mm:ss"));
		map.put("bStart", DateUtil.format(activityRelPO.getBookBeginTime(), "yyyy-MM-dd HH:mm:ss"));
		map.put("bEnd", DateUtil.format(activityRelPO.getBookEndTime(), "yyyy-MM-dd HH:mm:ss"));
		map.put("expireTime", activityRelPO.getExpireTime());
		mav = new ModelAndView("admin/activity/modify" , map);
		return mav;
	}

	@RequestMapping(value = "/modify.do" , method = RequestMethod.POST)
	public ModelAndView updateProduct(HttpServletRequest request) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		DecimalFormat df2 = new DecimalFormat("0.00");
		String productBeginDate = request.getParameter("productBeginDate");
		String productEnddate = request.getParameter("productEnddate");
		String productBeginTime = request.getParameter("productBeginTime");
		String productEndTime = request.getParameter("productEndTime");
		String productStock = request.getParameter("productStock");
		String proid = request.getParameter("proid");
		String onePerDay=request.getParameter("onePerDay");
		String surpportBuy=request.getParameter("surpportBuy");
		String surpportCount=request.getParameter("surpportCount");
		String dayStock=request.getParameter("dayStock");
		String price=request.getParameter("price");
		String expireTime=request.getParameter("expireTime");
		ActivityRelPO arpo = conn_activityRel.getById(Long.parseLong(proid));
		Date beginDate = sdf.parse(productBeginDate);
		Date endDate = sdf.parse(productEnddate);
		Date beginTime = sdf1.parse(productBeginTime);
		Date endTime = sdf1.parse(productEndTime);
	    Date bookBeginTime=sdf.parse(request.getParameter("bookBegin"));
	    Date bookEndTime=sdf.parse(request.getParameter("bookEnd"));
	    arpo.setBookBeginTime(bookBeginTime);
	    arpo.setBookEndTime(bookEndTime);
		arpo.setBeginDate(beginDate);
		arpo.setEndDate(endDate);
		arpo.setBeginTime(beginTime);
		arpo.setEndTime(endTime);
		arpo.setProductStock(Long.parseLong(productStock));
		arpo.setOnePerDay(Integer.parseInt(onePerDay));
		arpo.setSurpportBuy(Integer.parseInt(surpportBuy));
		arpo.setSurpportCount(Integer.parseInt(surpportCount));
		arpo.setDayStock(Integer.parseInt(dayStock));
		arpo.setPrice(Long.valueOf(price));
		arpo.setExpireTime(Integer.parseInt(expireTime));
		conn_activityRel.saveOrUpdate(arpo);
		Map<String, Object> map = new HashMap<String, Object>();
		
		ProductPO product = conn_product.get(arpo.getProductId());
		map.put("product", product);
		map.put("proid", arpo.getId());
		map.put("rel", arpo);
		map.put("start", DateUtil.format(arpo.getBeginDate(), "yyyy-MM-dd HH:mm:ss"));
		map.put("end", DateUtil.format(arpo.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
		map.put("startTime", DateUtil.format(arpo.getBeginTime(), "HH:mm:ss"));
		map.put("endTime", DateUtil.format(arpo.getEndTime(), "HH:mm:ss"));
		map.put("bStart", DateUtil.format(arpo.getBookBeginTime(), "yyyy-MM-dd HH:mm:ss"));
		map.put("bEnd", DateUtil.format(arpo.getBookEndTime(), "yyyy-MM-dd HH:mm:ss"));
		map.put("expireTime", expireTime);
		ModelAndView mav = new ModelAndView("admin/activity/modify" , map);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/chengesortindex" , method = RequestMethod.POST)
	public Map<String, Object> chengeSortIndex(long activityId, String state){
		Map<String, Object> map = new HashMap<String, Object>();
		long comId = getLoginInfo().getComId();
		if ("T".equals(state)) { // 上架
			ActivityPO activity = conn_activity.get(activityId);
			long index  =  activity.getSortindex();
			ActivityPO activity1 = conn_activity.getByCode(comId,index-1);
			if (activity1 != null){
				activity.setSortindex(index-1);
				conn_activity.saveOrUpdate(activity);
				conn_activity.flush();
				activity1.setSortindex(index);
				conn_activity.saveOrUpdate(activity1);
				conn_activity.flush();
			}else {
				map.put("data", "已是优先显示");
				return  map;
			}
		}else { // 下架
			ActivityPO activity = conn_activity.get(activityId);
			long index  =  activity.getSortindex();
			ActivityPO activity1 = conn_activity.getByCode(comId,index+1);
			if (activity1 != null){
				activity.setSortindex(index+1);
				conn_activity.saveOrUpdate(activity);
				conn_activity.flush();
				activity1.setSortindex(index);
				conn_activity.saveOrUpdate(activity1);
				conn_activity.flush();
			}else {
				map.put("data", "已是最尾");
				return  map;
			}
		}
		map.put("data", "success");
		return map;
	}

}
