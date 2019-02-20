package com.guolaiwan.app.web.website.controller;

import java.net.URLDecoder;
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
import com.guolaiwan.app.web.admin.vo.DistributorVO;
import com.guolaiwan.app.web.admin.vo.ModularVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.merchant.car.vo.RouteVO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.DistributorDAO;
import com.guolaiwan.bussiness.admin.dao.DistributorProductDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.DistributorPO;
import com.guolaiwan.bussiness.admin.po.DistributorProductPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.merchant.car.dao.RouteDAO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

@Controller
@RequestMapping("/product")
public class WebProductController extends WebBaseControll{
	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private ModularDAO conn_modular;

	@Autowired
	private ModularClassDAO conn_modularClass;

	@Autowired
	private SysConfigDAO conn_sysConfig;
	
	@Autowired
	private RouteDAO conn_route;
	
	@Autowired
	private CommentDAO conn_comment;
	
	@Autowired
	private DistributorDAO conn_distributor;

	@Autowired
	private DistributorProductDAO conn_distributorProduct;
	

	// 首页搜索
	@RequestMapping(value = "/productSearch", method= RequestMethod.GET)
	public ModelAndView productSearch(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String pcName = request.getParameter("pcName");

		pcName = URLDecoder.decode(pcName, "UTF-8");
		response.setContentType("text/xml;charset=utf-8"); 
		int pageSize=8;  //记录数
		int pageNum=1;  //页码

		List<ProductPO> products = conn_product.productSearch(pcName, pageNum, pageSize);
		List<ProductVO> productList=ProductVO.getConverter(ProductVO.class).convert(products, ProductVO.class);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("productList", productList);
		strMap.put("sysConfig", sysConfig);
		ModelAndView mav = new ModelAndView("web/product/productSearch",strMap);
		return mav;
	}
	//产品列表页
	@RequestMapping(value = "/productList", method= RequestMethod.GET)
	public ModelAndView productList(HttpServletRequest request) throws Exception {
		ModelAndView mv =null;
		Map<String, Object> nmap=new HashMap<String, Object>();
		Map<String, Object> strMap=new HashMap<String, Object>();
		//前台获取参数
		String modularCode= request.getParameter("m");//模块
		String classCode= request.getParameter("mc");//类
		
		
		//租车判断
		if(modularCode.equals("zcpc")){
			List<ModularPO> modulars = conn_modular.findByField("modularIsv", 1);
			List<ModularVO> modularsVO=ModularVO.getConverter(ModularVO.class).convert(modulars, ModularVO.class);
			String[] claFields = {"classmodularCode","classIsv"};
			for (ModularVO modular : modularsVO) {
				Object[] claValues={modular.getModularCode(),1};
				//获取模块下的分类(模块、显示;顺序排序)
				List<ModularClassPO> classList = conn_modularClass.findByFields(claFields, claValues);
				modular.setModularClasses(classList);
			}
			SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
			strMap.put("modulars",modularsVO);
			strMap.put("sysConfig", sysConfig);
			strMap.put("mc", modularCode);
			strMap.put("cc", classCode);
			
			
			//路线分页
			int pageSize=16;  //页
			int pagecurr=1;  //页码
			if(request.getParameter("pagecurr")!=null && request.getParameter("pagecurr").length()>0){
				pagecurr=Integer.parseInt(request.getParameter("pagecurr"));
			}
			String url="productList";  //url
			String params="";  
			if(modularCode!=null&&modularCode.length()>0){
				nmap.put("productModularCode",modularCode);
				params+="&m="+modularCode;
			}
			if(classCode!=null&&classCode.length()>0){
				classCode = encodeStr(classCode);
				nmap.put("productClassCode",classCode);
				params+="&mc="+classCode;
			}
			
			int count = conn_route.countAll();
			List<RoutePO> routePOs = conn_route.findAll(pagecurr,pageSize);
			List<RouteVO> routeVOs = RouteVO.getConverter(RouteVO.class).convert(routePOs, RouteVO.class);
			strMap.put("routes", routeVOs);
			strMap.put("pages", getPages(pagecurr,count,pageSize,url,params));
			mv =new ModelAndView("web/car/routeList",strMap);
			return mv;
		}




		long comId = getComIdSession(); //分公司Id
		
		String pcName= request.getParameter("pcName");//关键字
		String pcPriceMin= request.getParameter("pcPriceMin");//价格最小值
		String pcPriceMax= request.getParameter("pcPriceMax");//价格最大值
		int pageSize=16;  //页
		int pagecurr=1;  //页码
		if(request.getParameter("pagecurr")!=null && request.getParameter("pagecurr").length()>0){
			pagecurr=Integer.parseInt(request.getParameter("pagecurr"));
		}
		String url="productList";  //url
		String params="";          //参数

		//模块、城市、是否显示、首页显示。审核通过"productModularCode" modularCode
		nmap.put("comId",comId);
		if(modularCode!=null&&modularCode.length()>0){
			nmap.put("productModularCode",modularCode);
			params+="&m="+modularCode;
		}
		if(classCode!=null&&classCode.length()>0){
			nmap.put("productClassCode",classCode);
			params+="&mc="+classCode;
		}
		if(pcPriceMin!=null&&pcPriceMin.length()>0){
			nmap.put("minProductPrice",100*Long.parseLong(pcPriceMin));
			params+="&pcPriceMin="+pcPriceMin;
		}
		if(pcPriceMax!=null&&pcPriceMax.length()>0){
			nmap.put("maxProductPrice",100*Long.parseLong(pcPriceMax));
			params+="&pcPriceMax="+pcPriceMax;
		}
		if(pcName!=null&&pcName.length()>0){
			pcName=encodeStr(pcName);
			nmap.put("productName",pcName);
			params+="&pcName="+pcName;
		}


		//产品List
		List<ProductPO> productListP = conn_product.getListByPageB(nmap, pagecurr,pageSize);
		List<ProductVO> productList=ProductVO.getConverter(ProductVO.class).convert(productListP, ProductVO.class);
		int count = conn_product.getListByPageBC(nmap);//产品总数


		//获取模块(显示;无排序)
		List<ModularPO> modulars = conn_modular.getModulars();
		List<ModularVO> modularsVO=ModularVO.getConverter(ModularVO.class).convert(modulars, ModularVO.class);
		String[] claFields = {"classmodularCode","classIsv"};
		for (ModularVO modular : modularsVO) {
			Object[] claValues={modular.getModularCode(),1};
			//获取模块下的分类(模块、显示;顺序排序)
			List<ModularClassPO> classList = conn_modularClass.findByFields(claFields, claValues);
			modular.setModularClasses(classList);
		}
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("modulars",modularsVO);
		strMap.put("mc", modularCode);
		strMap.put("cc", classCode);
		strMap.put("pcName",pcName);
		strMap.put("pcPriceMin",pcPriceMin);
		strMap.put("pcPriceMax",pcPriceMax);
		strMap.put("productList", productList);
		strMap.put("sysConfig", sysConfig);
		strMap.put("pages", getPages(pagecurr,count,pageSize,url,params));
		System.out.println(strMap);
		mv = new ModelAndView("web/product/productList",strMap);
		return mv;
	}


	//产品详情页面
	@RequestMapping(value = "/productInfo", method= RequestMethod.GET)
	public ModelAndView productInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid = request.getParameter("info");
		DecimalFormat df = new DecimalFormat("0.00");
		ProductPO  productp = conn_product.get(uuid);
		ProductVO  product = new ProductVO().set(productp);
		System.out.println("0000000000000--"+productp.getProductMerchantID());
		MerchantPO merchant= conn_merchant.get(productp.getProductMerchantID());
		String morePic = product.getProductMorePic();
		String[] pics = morePic.split(",");
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		//评论
		List<CommentPO> comments = conn_comment.findByProId(productp.getId(), 1, 10);
		List<CommentVO> _comments = CommentVO.getConverter(CommentVO.class).convert(comments, CommentVO.class);
		
	
		
		//经销商Id参数
		String disId = request.getParameter("distributor");
		int isDistributor = 0;
		if(disId!=null){
			DistributorPO distributor = conn_distributor.get(Long.parseLong(disId));
			if(distributor!=null){
				DistributorVO _distributor = new DistributorVO().set(distributor);
				DistributorProductPO distributorProduct = conn_distributorProduct.getByDisAndPro(Long.parseLong(disId), productp);
				_distributor.setDistributorPrice(df.format((double)distributorProduct.getDistributorPrice()/100));;
				strMap.put("distributor", _distributor);
				isDistributor = 1;
			}
		}
		
		//经销商
		List<DistributorVO> _distributors =  new ArrayList<DistributorVO>();
		List<DistributorProductPO> distributorProducts = productp.getDistributorProduct();
		for (DistributorProductPO distributorProductPO : distributorProducts) {
			DistributorPO distributor = conn_distributor.get(distributorProductPO.getDistributorId());
			DistributorVO _distributor = new DistributorVO().set(distributor);
			//经销商价格
			_distributor.setDistributorPrice(df.format((double)distributorProductPO.getDistributorPrice()/100));
			//单图
			_distributor.setShopPic(
					sysConfig.getWebUrl() + _distributor.getShopPic());
			//多图
			_distributor.setShopMpic(split(_distributor.getShopMpic(), sysConfig.getWebUrl()));
			//人均价格
			_distributor.setAveragePrice("无数据");
			
			if(disId!=null&&Long.parseLong(disId)==distributorProductPO.getDistributorId()){
			}else{
				_distributors.add(_distributor);
			}
			
		}
		strMap.put("isDistributor", isDistributor);
		strMap.put("product", product);
		strMap.put("comments", _comments);
		strMap.put("merchant", merchant);
		strMap.put("pics", pics);
		strMap.put("sysConfig", sysConfig);
		strMap.put("distributors", _distributors);
		ModelAndView mv = new ModelAndView("web/product/productInfo",strMap);
		return mv;
	}
	
	//评论接口
	@ResponseBody
	@RequestMapping(value="getComment",method = RequestMethod.GET )
	public Map<String,Object> getComment(
			HttpServletRequest request,
			Long productId,
			int page) throws Exception{
		//评论
		List<CommentPO> comments = conn_comment.findByProId(productId, page, 10);
		List<CommentVO> _comments = CommentVO.getConverter(CommentVO.class).convert(comments, CommentVO.class);
		return  success(_comments);
	}



}
