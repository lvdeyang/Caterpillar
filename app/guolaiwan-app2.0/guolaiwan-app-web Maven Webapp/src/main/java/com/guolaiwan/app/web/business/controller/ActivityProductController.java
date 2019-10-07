package com.guolaiwan.app.web.business.controller;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.commons.util.binary.Sha1Util;


@RestController
@RequestMapping("/product/activity")
public class ActivityProductController {
	
	@Autowired
	private ProductDAO productDao;
	
    @Autowired
	private ActivityRelDAO activityRelDao;
    
	@RequestMapping(value="/list")
	public Map<String, Object> findActivityProduct(HttpServletRequest request){
		String productMerchantID = request.getParameter("productMerchantID");
		List<ActivityRelPO> list = new ArrayList<ActivityRelPO>();
		List<ProductPO> product = new ArrayList<ProductPO>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> listArray = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("0.00");
		//获取南山的商品
		List<ProductPO> productPOs = productDao.findByMerchantId(Long.parseLong(productMerchantID));	
		//前端判断有无活动商品
		try{	
		//获取当前时间
			Date nowDate = new Date();
		long nowTime = nowDate.getTime();
		//根据商品的id查询活动商品
		for(ProductPO po: productPOs){
		 long ProductId = po.getId();
		  String pic = po.getProductShowPic();
		 if(activityRelDao.getActivityRelByProductId(ProductId) != null){
		    ActivityRelPO  activityRelPO =activityRelDao.getActivityRelByProductId(ProductId);		 
		    if(activityRelPO.getProductStock()>0){
		    list.add(activityRelPO);
		    product.add(po);
		    listArray.add(df.format(Double.parseDouble(activityRelPO.getPrice() + "") / 100));	
		    }
		 }else{ continue;}
     }
		}catch(Exception e){}
		map.put("list", list);
		map.put("product",product );
		map.put("listArray", listArray);
		return map;
	}
	
	/*
	 *活动
	 * 
	 * **/
	@RequestMapping(value="/jump")
	public ModelAndView  jumpSkip(){  
	    ModelAndView mView = new ModelAndView("mobile/business/activitys");
	    mView.addObject("productMerchantID", 198);
		return mView;
	}
  //满减
		@RequestMapping(value = "/Public")
		public ModelAndView businessPublic(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/business/public"); 
			return mv;
		} 
  //折扣
		@RequestMapping(value = "/Discount")
		public ModelAndView businessDiscount(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/business/discount"); 
			return mv;
		}
  //秒杀
		@RequestMapping(value = "/Timelimit")
		public ModelAndView businessTimelimit(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/business/timelimit"); 
			return mv;
		}
  //领券
	@RequestMapping(value = "/Shotgun")
	public ModelAndView businessShotgun(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/Shotgun"); 
		return mv;
	}
  //优惠券
	@RequestMapping(value = "/Voucher")
	public ModelAndView businessVoucher(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/business/Voucher"); 
		return mv;
	} 	
	/*
	 *  活动商品详情页面
	 * 
	 * **/
	@RequestMapping(value="/commodity")
	public ModelAndView commodityPage(HttpServletRequest request){
		String  productId = request.getParameter("productId");
		String  productMerchantID = request.getParameter("productMerchantID");
		DecimalFormat df = new DecimalFormat("0.00");
		//获取活动商品的内容
		ActivityRelPO activityRelPO = activityRelDao.getActivityRelByProductId(Long.parseLong(productId));
		Map<String, Object> map = new HashMap<String, Object>();
		//获取当前时间
		Date nowTime = new Date();
		//对时间进行十分秒处理
		  //获取活动结束日期
		 Date endDate = activityRelPO.getEndDate();
		 long end =endDate.getTime();
		 //获取活动开始日期
		 Date beginDate = activityRelPO.getBeginDate();
		 //当前时间
		 long begin = nowTime.getTime();
		 //对开始和结束的处理
		 SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		 String beginTime  =  sdf.format(beginDate);
		 String endTime  =  sdf.format(endDate);
			     
		      String price = df.format(Double.parseDouble(activityRelPO.getPrice() + "") / 100);
		      	map.put("end",endDate);
		        map.put("activityRelPO",activityRelPO);
		        map.put("productMerchantID", productMerchantID);
		        map.put("beginTime", beginTime);
		        map.put("endTime", endTime);
		        map.put("price", price);
		       return new ModelAndView("mobile/business/activityproduct",map);
	}
	
}
