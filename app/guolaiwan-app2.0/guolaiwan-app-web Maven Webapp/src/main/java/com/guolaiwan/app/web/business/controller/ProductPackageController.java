package com.guolaiwan.app.web.business.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.mvc.controller.BaseController;


@RestController
@RequestMapping("/product/package")
public class ProductPackageController extends BaseController {
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private MerchantDAO merchantDao;
			
	//查询商品套餐信息 
	@RequestMapping(value="/list")
	public Map<String, Object> findPackageName(HttpServletRequest request) throws ServletException, IOException{
		List<ProductPO> list = new ArrayList<ProductPO>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> strArryList = new ArrayList<String>();
	//商户商品的ID
	  String productMerchantID = request.getParameter("productMerchantID");
	//获取分页的页数
	  Map<String, Object> straMap  = new HashMap<String, Object>();
	  String pageNum = request.getParameter("pageNum");
	  int limit = 5;
	  straMap.put("productMerchantID", Long.parseLong(productMerchantID));
	  DecimalFormat df = new DecimalFormat("0");
	//套餐商品的ID集合
	  List<ProductPO> productPOs = productDao.findByPageC(straMap,Integer.valueOf(pageNum),limit);
	  //获取当前时间
	  Date nowTimeDate = new Date();
	  long nowTime = nowTimeDate.getTime();
	  for(ProductPO pos : productPOs){
		  Date end = pos.getProductEnddate();
		  long endTime = end.getTime();
		  long beginTime = pos.getProductBeginDate().getTime();
		  if(pos.getProductStock()>0 && (endTime-nowTime)>0 && (nowTime-beginTime)>0){
			 long productPrice  =pos.getProductPrice();
			String strPrice = df.format(Double.parseDouble(productPrice+ "") / 100);
			  list.add(pos);
			  strArryList.add(strPrice);
		  } 
	  }	 	  
	  if(list.size()>0){
		  map.put("productPOs",list );
		  map.put("strArryList",strArryList );
	  }
	  return map;
	}			
}
