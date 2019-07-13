package com.guolaiwan.app.web.business.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.Guide.controller.integralControll;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.controller.BaseController;


@RestController
@RequestMapping("/product/package")
public class ProductPackageController extends BaseController {
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private MerchantDAO merchantDao;
	
	@Autowired
	private ActivityRelDAO conn_acre;
	
    @Autowired
    private OrderInfoDAO conn_orif;
    
    @Autowired
    private CommentDAO conn_comm;
	
	/**
	 * 跳转方法
	 * */
	@RequestMapping(value="/purchase/jump")
	public ModelAndView purchaseJump(HttpServletRequest request){
	   String merchanId	= request.getParameter("merchantId");
	   Map<String, Object> map = new HashMap<String, Object>();
	   map.put("productMerchantID", merchanId);
	   return new ModelAndView("mobile/business/purchase",map);	
	}
	
			
	//查询商品信息 
	@RequestMapping(value="/list")
	public Map<String, Object> findPackageName(HttpServletRequest request) throws NumberFormatException, Exception{		
        //返回封装数据 商品价格   商品封装
		Map<String, Object>  map =new HashMap<String, Object>();
		List<String> pro_pric = new ArrayList<String>();
		List<ProductVO> pro_list = new ArrayList<ProductVO>();
		
		//获取merchantId
		String merhcantId = request.getParameter("merchantId");
		//分页条件封装
		String pageNum = request.getParameter("pageNum");
		int pageSize =5 ; 
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("productMerchantID", Long.parseLong(merhcantId));
		//分页获取所有商品
	    List<ProductVO> pro_vo = new ProductVO().getConverter(ProductVO.class).
		convert( productDao.findByPageC(mapp,Integer.valueOf(pageNum), pageSize), ProductVO.class);
	   //判断活动是否过期
	    boolean is_overtime;
	    long nowDate = new Date().getTime();	    	    
	    //获取活动商品id
	     Map<Long, ActivityRelPO> activ_merchan_id = new HashMap<Long, ActivityRelPO>();
	     List<ActivityRelPO> activ_po = conn_acre.findAll();
	     for(ActivityRelPO po : activ_po){
	    	 if(nowDate> po.getBeginDate().getTime() || nowDate < po.getEndDate().getTime()){
	    		 is_overtime = true;
	    	 }else{is_overtime = false; continue;}
	    	if(is_overtime){
	    		activ_merchan_id.put(po.getProductId(), po);	    		
	    	} 	    		    	  
	     }	     
	     DecimalFormat def = new DecimalFormat("0.00");
	     //判断商品是否为活动商品
	    Set<Long> pro_id = activ_merchan_id.keySet();
		for(ProductVO pro : pro_vo){
			if(pro_id.contains(pro.getId())){
			pro.setProductName(	pro.getProductName()+",act");			
			pro_pric.add(def.format(Double.parseDouble(activ_merchan_id.get(pro.getId()).getPrice()+"")/100));
			pro_list.add(pro);
			}else{
			pro.setProductName(	pro.getProductName()+",comm");	
			pro_pric.add(def.format(Double.parseDouble(pro.getProductPrice()+"")/100));
			pro_list.add(pro);
			}			
		}    
	      map.put("productPOs",pro_list);
	      map.put("strArryList",pro_pric);
	      return map;
	}	
	
	/**
	 *  商家 信息查询
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value="/merInfo",method=RequestMethod.POST)
	public Map<String, Object> getMerInfo(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();	
		String merchantId =  request.getParameter("merchantId");
		//获取商户信息			
		List<MerchantVO> mer_vo  = new MerchantVO().getConverter(MerchantVO.class)
				.convert( merchantDao.findByField("id",Long.parseLong(merchantId) ),MerchantVO.class );
		List<ProductVO> pr_vo  = new ProductVO().getConverter(ProductVO.class)
				.convert(productDao.findByField("productMerchantID",Long.parseLong(merchantId)), ProductVO.class);
		// 判断商品
		for(int i= 0;i<pr_vo.size();i++){			
			if("园林".equals(pr_vo.get(i).getProductClassName())){
			 continue;	
			}else{ pr_vo.remove(i); }				
		}
		map.put("merpos", mer_vo.get(0));
		map.put("prosinfos", pr_vo);
		return map;
	}
	
	/**
	 * 跳转购票详情页面
	 * */
	@RequestMapping(value="/commodity/jump")
	public ModelAndView commodityJump(HttpServletRequest request){	
	 String merchantId = request.getParameter("merchantId");
	 String proId = request.getParameter("proId");
	 String state = request.getParameter("state");
	 Map<String, Object> map = new HashMap<String, Object>();
	 map.put("merchantId", merchantId);
	 map.put("proId", proId);
	 return new ModelAndView("mobile/business/commodity",map);
	}
	
	/**
	 *  单个商品详情
	 * @throws Exception 
	 * 
	 * */
	@RequestMapping(value="/commodity/info",method=RequestMethod.GET)
	public Map<String, Object> getCommodityInfo(Long proId,Long merchantId) throws Exception{
	  //封装前台数据
	   Map<String, Object> map = new HashMap<String, Object>();
       List<MerchantPO> mer_po = new ArrayList<MerchantPO>();
       List<ProductPO> pro_po = new ArrayList<ProductPO>();
      //商户信息 商品信息  订单数量
       mer_po.add(merchantDao.get(merchantId));
       List<MerchantVO> mer_vo = new MerchantVO().getConverter(MerchantVO.class)
    		   .convert(mer_po, MerchantVO.class);
       pro_po.add(productDao.get(proId));
       List<ProductVO> pr_vo =  new ProductVO().getConverter(ProductVO.class)
               .convert(pro_po, ProductVO.class);
       //查询订单数量 
       int orderNumber= 0;
       List<OrderInfoPO> order_infoInfoPOs  = conn_orif.findByField("productId", proId);
       orderNumber= order_infoInfoPOs.size();
      //价格处理
       DecimalFormat def = new DecimalFormat("0.00");
       String form_pric = def.format(Double.parseDouble( pr_vo.get(0).getProductPrice()+"")/100);
      //票种类型
       String pr_name = pr_vo.get(0).getProductName();
       try{   	   
       if(pr_name.contains("票")){
    	   pr_name =  pr_name.split("-")[1];    	   
       }else{
    	   pr_name = "普通票";    	  
       } }catch(Exception e){ System.err.println(e.toString());}  
       
       map.put("merinfo", mer_vo);
       map.put("proinfo", pr_vo);
       map.put("pric", form_pric);
       map.put("pr_name", pr_name);
       map.put("orderNumber", orderNumber);
	   return map;
	
	}
	/**
	 * 商品评论
	 * @throws Exception 
	 * */
	@RequestMapping(value="/comment")
	public Map<String,Object> getProComment(Long proId) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		//该商品的所有评论
		List<CommentVO> comm_vo = new CommentVO().getConverter(CommentVO.class)
		.convert(conn_comm.findByField("proId", proId), CommentVO.class);
		map.put("comm", comm_vo);		
		return map;				
	} 
				
}
