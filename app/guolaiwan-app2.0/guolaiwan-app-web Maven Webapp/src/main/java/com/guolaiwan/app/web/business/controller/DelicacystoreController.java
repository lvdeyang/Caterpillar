package com.guolaiwan.app.web.business.controller;



import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.guolaiwan.app.web.admin.vo.MerchantVO;

import com.guolaiwan.app.web.publicnum.vo.LiveMessageVo;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import javassist.bytecode.stackmap.BasicBlock.Catch;
import javassist.expr.NewArray;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@RestController
@RequestMapping("/product/delicacystore")
public class DelicacystoreController extends BaseController {
 
		@Autowired
		 private ProductDAO productDao;
		
		@Autowired
		private MerchantDAO merchantDao;
		
		  /**
		   * 商家详情
		 * @throws Exception 
		   * */
		  @RequestMapping(value="/info.do")
		  public Map<String, Object> getProductInfo(HttpServletRequest request) throws Exception{
			String merchantId=request.getParameter("merchantId");
              Map<String, Object> map = new HashMap<String, Object>();
			  //商家图片集合
			  List<String> mpic = new ArrayList<String>();
			  //根据productMerchantID查询商品信息

			  MerchantPO merch = merchantDao.get(Long.parseLong(merchantId));
			  List<MerchantPO> listpo = new ArrayList<MerchantPO>();
			  listpo.add(merch);
			  List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
			  //获取商户名称
			  String shopName = merch.getShopName();
			  String shopMpic = merch.getShopMpic();
			  String[] strArr = shopMpic.split(",");
			  for(String str :strArr){
				  mpic.add(str); 
			  }
			  map.put("mpic", mpic);
			  map.put("shopName", shopName);
			  map.put("merch",listvo.get(0) );
			  return map;
		  }
		  
		 /**
		  * 类型下所有的商品
		  * */
        @RequestMapping(value="greens", method=RequestMethod.POST)
        public Map<String, Object> getGreens(HttpServletRequest request){
        	//创建Map集合
        	Map<String, Object> map = new HashMap<String, Object>();
        	List<ProductPO> productPO = new ArrayList<ProductPO>();
        	//存储价格数据
        	List<String> priceList = new ArrayList<String>();
        	//获取类型下的对应的菜品
            String classCode = request.getParameter("codeID");            
            String merchantId =request.getParameter("merchantId");
            
            List<ProductPO> productPOs = productDao.findByMerchantId(Long.parseLong(merchantId));
            DecimalFormat df = new DecimalFormat("0.00");
            for(ProductPO poes : productPOs ){
            	if(classCode.equals(poes.getProductClassCode())){
            		productPO.add(poes);
            		 String strPrice = df.format(Double.parseDouble(poes.getProductPrice()+ "") / 100);
            		 priceList.add(strPrice);
            	}            	
            }
                  	
            map.put("productPO", productPO);
            map.put("priceList", priceList); 
        	return map;
        }
	
     /**
      *  热销封装
      * 
      * */
	@RequestMapping(value="/hotsell")
	public Map<String, Object> getHotSell(HttpServletRequest request){
		String merchantId=request.getParameter("merchantId");	
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> priceList = new ArrayList<String>();
		 //根据productMerchantID查询商品信息
		 List<ProductPO> productPOs = productDao.findByMerchantId(Long.parseLong(merchantId));		 		
		 List<ProductPO> productPs = new ArrayList<ProductPO>();
		 DecimalFormat df = new DecimalFormat("0.00");
		     int i = 0;
										 
				 for(ProductPO pss :productPOs){
					 if(i<6){
					 if("家常菜".equals(pss.getProductClassName())){
					    productPs.add(pss);
			            String strPrice = df.format(Double.parseDouble(pss.getProductPrice()+ "") / 100);
			            priceList.add(strPrice); 
			            i++;
					 }
				 }
			 }	 
			 
		 map.put("prd", productPs);
         map.put("prcList", priceList);       
		 return map;	 
	}  
	
	@Autowired	
	private LiveMessageDAO conn_livemsg;
	@Autowired
	private UserInfoDAO conn_user;
	
	/**
	 * 商品评价
	 * 
	 * */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getMerchantMessage", method = RequestMethod.GET)
	public Object getLiveMessage(HttpServletRequest request, Long merchantId) throws Exception {
		try{
		List<LiveMessagePO> messagePOs = conn_livemsg.findByMerchant(merchantId);
		List<LiveMessageVo> vos = new ArrayList<LiveMessageVo>();
		for (LiveMessagePO liveMessagePO : messagePOs) {
			LiveMessageVo vo = new LiveMessageVo();
			vo.setUserName(liveMessagePO.getUserName());
			vo.setContent(liveMessagePO.getMessage());
			vo.setHeadImg(conn_user.get(liveMessagePO.getUserId()).getUserHeadimg());
			vos.add(vo);
		}
		return vos;
	}catch(Exception e){
		conn_livemsg.findAll();
		return "-1";
	}
	}
	
	
}
