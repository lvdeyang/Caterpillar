package com.guolaiwan.app.web.merchant.order.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.merchant.dao.ReportOrderDAO;
import com.guolaiwan.bussiness.merchant.po.ReportOrderPO;

import cn.hutool.core.util.IdcardUtil;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/report/order")
public class ReportOrderController extends BaseController{
	@Autowired
	private ReportOrderDAO conn_reportOrder;
	@Autowired
	private MerchantDAO conn_merchant;
	@Autowired
	private ProductDAO conn_pro;

	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView activityList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("merchant/reportorder/list", strMap);
		
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long merId = getMerchantInfo().getMerchantId();
		int count = conn_reportOrder.countByField("merchantId", merId);
	    List<ReportOrderPO> reportOrderPOs=conn_reportOrder.
	    		findByField("merchantId", merId, page, limit);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", reportOrderPOs);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("merchant/reportorder/add");
		long merId = getMerchantInfo().getMerchantId();
		List<ProductPO> productPOs=conn_pro.findByMerchantId(merId);
		mv.addObject("pros",productPOs);
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {

		long merId = getMerchantInfo().getMerchantId();
		String nameStr = request.getParameter("name");
		String typeStr = request.getParameter("type");
		String idCardStr = request.getParameter("idCard");
		String priceStr = request.getParameter("price");
		String countStr = request.getParameter("count");
		String productIdStr = request.getParameter("productId");
		MerchantPO merchantPO=conn_merchant.get(merId);
		ProductPO productPO=conn_pro.get(Long.parseLong(productIdStr));
		ReportOrderPO reportOrderPO=new ReportOrderPO();
		reportOrderPO.setName(nameStr);
		reportOrderPO.setCount(Long.parseLong(countStr));
		reportOrderPO.setPrice(Long.parseLong(priceStr));
		reportOrderPO.setCount(Long.parseLong(countStr));
		reportOrderPO.setAmount(reportOrderPO.getPrice()*reportOrderPO.getCount());
        reportOrderPO.setType(Integer.parseInt(typeStr));
        reportOrderPO.setProductId(productPO.getId());
        reportOrderPO.setProductName(productPO.getProductName());
        reportOrderPO.setMerchantId(merchantPO.getId());
        reportOrderPO.setMerchantName(merchantPO.getShopName());
        reportOrderPO.setIdCard(idCardStr);
        //根据身份证获取年龄，性别，地区
        reportOrderPO.setSex(IdcardUtil.getGenderByIdCard(idCardStr));
        reportOrderPO.setAge(IdcardUtil.getAgeByIdCard(idCardStr));
        reportOrderPO.setRegion(IdcardUtil.getProvinceByIdCard(idCardStr));
        
        if(reportOrderPO.getSex()==1){
        	reportOrderPO.setSexString("男");
        }else{
        	reportOrderPO.setSexString("女");
        }

        if(reportOrderPO.getType()==0){
        	reportOrderPO.setTypeString("线下");
        }else if(reportOrderPO.getType()==1){
        	reportOrderPO.setTypeString("旅行社");
        }else{
        	reportOrderPO.setTypeString("三方OTA");
        }
        
        conn_reportOrder.save(reportOrderPO);
		return "success";
	}

	
			
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
	    conn_reportOrder.delete(id);
		return "success";
	}

	// 添加数据页面
	@RequestMapping(value = "/editv", method = RequestMethod.GET)
	public ModelAndView editv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("merchant/reportorder/Modify");
		long id =Long.parseLong(request.getParameter("id"));
		ReportOrderPO reportOrderPO=conn_reportOrder.get(id);
		mv.addObject("order", reportOrderPO);
		long merId = getMerchantInfo().getMerchantId();
		List<ProductPO> productPOs=conn_pro.findByMerchantId(merId);
		mv.addObject("pros",productPOs);
		return mv;
	}	

	// 编辑数据
	@ResponseBody
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String edit(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		long id = Long.parseLong(request.getParameter("id").toString());
		String nameStr = request.getParameter("name");
		String typeStr = request.getParameter("type");
		String idCardStr = request.getParameter("idCard");
		String priceStr = request.getParameter("price");
		String countStr = request.getParameter("count");
		String productIdStr = request.getParameter("productId");

		ProductPO productPO=conn_pro.get(Long.parseLong(productIdStr));
		ReportOrderPO reportOrderPO=conn_reportOrder.get(id);
		reportOrderPO.setName(nameStr);
		reportOrderPO.setCount(Long.parseLong(countStr));
		reportOrderPO.setPrice(Long.parseLong(priceStr));
		reportOrderPO.setCount(Long.parseLong(countStr));
		reportOrderPO.setAmount(reportOrderPO.getPrice()*reportOrderPO.getCount());
        reportOrderPO.setType(Integer.parseInt(typeStr));
        reportOrderPO.setProductId(productPO.getId());
        reportOrderPO.setProductName(productPO.getProductName());
        reportOrderPO.setIdCard(idCardStr);
        //根据身份证获取年龄，性别，地区
        reportOrderPO.setSex(IdcardUtil.getGenderByIdCard(idCardStr));
        reportOrderPO.setAge(IdcardUtil.getAgeByIdCard(idCardStr));
        reportOrderPO.setRegion(IdcardUtil.getProvinceByIdCard(idCardStr));
        if(reportOrderPO.getSex()==1){
        	reportOrderPO.setSexString("男");
        }else{
        	reportOrderPO.setSexString("女");
        }

        if(reportOrderPO.getType()==0){
        	reportOrderPO.setTypeString("线下");
        }else if(reportOrderPO.getType()==1){
        	reportOrderPO.setTypeString("旅行社");
        }else{
        	reportOrderPO.setTypeString("三方OTA");
        }
        conn_reportOrder.update(reportOrderPO);
		return "success";

	}

	
}
