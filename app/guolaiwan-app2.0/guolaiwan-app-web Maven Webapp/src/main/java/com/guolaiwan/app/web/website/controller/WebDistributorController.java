package com.guolaiwan.app.web.website.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.DistributorProductVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.DistributorDAO;
import com.guolaiwan.bussiness.admin.dao.DistributorProductDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.enumeration.MerchantSpecialBusiness;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.DistributorPO;
import com.guolaiwan.bussiness.admin.po.DistributorProductPO;
import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;
import com.sun.jna.platform.win32.WinDef.LONG;

import pub.caterpillar.commons.util.wrapper.HashMapWrapper;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

@Controller
@RequestMapping("/web/distributor")
public class WebDistributorController extends WebBaseControll{

	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private DistributorProductDAO conn_distributorProduct;
	@Autowired
	private DistributorDAO conn_distributor;
	@Autowired
	private CommentDAO conn_comment;

	// 首页搜索
	@RequestMapping(value = "/productList", method= RequestMethod.GET)
	public ModelAndView productSearch(
			HttpServletRequest request,
			HttpServletResponse response,
			long distributorId) throws Exception {
		Map<String,Object> strMap =new HashMap<String, Object>();
		DecimalFormat df = new DecimalFormat("0.00"); 

		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DistributorPO distributor = conn_distributor.get(distributorId);
		if(distributor == null){
			throw new Exception();
		}

		//分页
		Map<String, Object> nmap = new HashMap<String, Object>();
		nmap.put("distributorId", distributorId);
		int pagecurr = 1;
		if(request.getParameter("pagecurr")!=null){
			pagecurr = Integer.parseInt(request.getParameter("pagecurr"));
		}
		int pageSize = 16;
		String url = "productList"; 
		String params = "&distributorId="+distributor.getId();

		String pcName= request.getParameter("pcName");//关键字
		if(pcName!=null&&pcName.length()>0){
			pcName=encodeStr(pcName);
			nmap.put("productName",pcName);
			params+="&pcName="+pcName;
		}

		String minPrice= request.getParameter("minPrice");//最小价格
		if(minPrice!=null&&minPrice.length()>0){
			long minPricel = (long)Double.parseDouble(minPrice)*100;
			nmap.put("minPrice",minPricel);
			params+="&minPrice="+minPrice;
		}

		String maxPrice= request.getParameter("maxPrice");//最大价格
		if(maxPrice!=null&&maxPrice.length()>0){
			long maxPricel = (long)Double.parseDouble(maxPrice)*100;
			nmap.put("maxPrice",maxPricel);
			params+="&maxPrice="+maxPrice;
		}

		//经销商下面的产品
		List<ProductDTO> products = conn_distributorProduct.findByDistriJS(nmap, pagecurr,pageSize);
		for (ProductDTO productDTO : products) {
			productDTO.setSellPriceStr(df.format((double)productDTO.getSellPrice()/100));
		}

		int count = conn_distributorProduct.countByDistriJS(nmap);
		strMap.put("productList", products);
		strMap.put("sysConfig", sysConfig);
		strMap.put("pages", getPages(pagecurr,count,pageSize,url,params));
		strMap.put("pcName", pcName);
		strMap.put("minPrice", minPrice);
		strMap.put("maxPrice", maxPrice);
		strMap.put("count", count);
		strMap.put("distributor", distributor);

		ModelAndView mav = new ModelAndView("web/distributor/productList",strMap);
		return mav;		
	}
	
	// 分销商列表
	@RequestMapping(value = "/distributorlist", method= RequestMethod.GET)
	public ModelAndView distributorList(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String,Object> strMap =new HashMap<String, Object>();
		DecimalFormat df = new DecimalFormat("0.00"); 
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

		//分页
		Map<String, Object> nmap = new HashMap<String, Object>();
		int pagecurr = 1;
		if(request.getParameter("pagecurr")!=null){
			pagecurr = Integer.parseInt(request.getParameter("pagecurr"));
		}
		int pageSize = 16;
		String url = "distributorlist"; 
		String params = "";

		String distributorName= request.getParameter("distributorName");//关键字
		if(distributorName!=null&&distributorName.length()>0){
			distributorName=encodeStr(distributorName);
			nmap.put("shopName",distributorName);
			params+="&distributorName="+distributorName;
		}
		List<DistributorPO> distributors = conn_distributor.getListByPageB(nmap,pagecurr, pageSize);
		int count = conn_distributor.CountByPageB(nmap);
		strMap.put("distributors", distributors);
		strMap.put("sysConfig", sysConfig);
		strMap.put("pages", getPages(pagecurr,count,pageSize,url,params));
		strMap.put("distributorName", distributorName);
		strMap.put("count", count);
		ModelAndView mav = new ModelAndView("web/distributor/distributorlist",strMap);
		return mav;		
	}
	

	// 分销商列表
	@RequestMapping(value = "/productInfo", method= RequestMethod.GET)
	public ModelAndView productInfo(
			HttpServletRequest request,
			HttpServletResponse response,
			String info) throws Exception {

		Map<String,Object> strMap =new HashMap<String, Object>();
		DecimalFormat df = new DecimalFormat("0.00"); 
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		
		DistributorProductPO distributorProduct = conn_distributorProduct.get(info);
		ProductPO product = distributorProduct.getProduct();
		String[] pics = product.getProductMorePic().split(",");
		DistributorPO distributor = conn_distributor.get(distributorProduct.getDistributorId());
		
		ProductVO _product = new ProductVO().set(product);
		_product.setProductOldPrice(df.format((double)product.getProductOldPrice()/100));
		_product.setProductPrice(df.format((double)product.getProductPrice()/100));
		DistributorProductVO _distributorProduct = new DistributorProductVO().set(distributorProduct);
		_distributorProduct.setDistributorPrice(df.format((double)distributorProduct.getDistributorPrice()/100));
		_distributorProduct.setSellPrice(df.format((double)distributorProduct.getSellPrice()/100));
		
		//评论
		List<CommentPO> comments = conn_comment.findByProId(product.getId(), 1, 10);
		List<CommentVO> _comments = CommentVO.getConverter(CommentVO.class).convert(comments, CommentVO.class);
		
		strMap.put("product", _product);
		strMap.put("distributorProduct", _distributorProduct);
		strMap.put("comments", _comments);
		strMap.put("distributor", distributor);
		strMap.put("pics", pics);
		strMap.put("sysConfig", sysConfig);
		ModelAndView mav = new ModelAndView("web/distributor/productInfo",strMap);
		return mav;		
	}

}
