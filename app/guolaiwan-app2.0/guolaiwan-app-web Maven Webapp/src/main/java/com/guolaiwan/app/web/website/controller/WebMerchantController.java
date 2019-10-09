package com.guolaiwan.app.web.website.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ModularVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.MerModularDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dto.MerchantDTO;
import com.guolaiwan.bussiness.admin.enumeration.MerchantSpecialBusiness;
import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.merchant.car.dao.FlightDAO;
import com.guolaiwan.bussiness.merchant.car.dao.RouteDAO;
import com.guolaiwan.bussiness.merchant.car.enumeration.FlightType;
import com.guolaiwan.bussiness.merchant.car.po.DriverPO;
import com.guolaiwan.bussiness.merchant.car.po.FlightPO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

import javassist.expr.NewArray;
import pub.caterpillar.commons.util.path.PathUtil;
import pub.caterpillar.commons.util.wrapper.HashMapWrapper;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

@Controller
@RequestMapping("/web/merchant")
public class WebMerchantController extends WebBaseControll{
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
	private MerModularDAO conn_merModular;
	
	
	
	
	// 首页搜索
	@RequestMapping(value = "/productList", method= RequestMethod.GET)
	public ModelAndView productSearch(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//商家
		Map<String,Object> strMap =new HashMap<String, Object>();
		String uuid = request.getParameter("merchant");
		
		MerchantPO merchant = conn_merchant.get(uuid);
		
		if(MerchantSpecialBusiness.CONVOYS.equals(merchant.getBusiness())){
			//重定向到车队展示
			StringBufferWrapper pathBuffer = new StringBufferWrapper().append("redirect:/web/merchant/query/route/").append(uuid);
			return new ModelAndView(pathBuffer.toString(), new HashMapWrapper().put("pagecurr", 1).getMap());
		}
		
		//分页
		Map<String, Object> nmap = new HashMap<String, Object>();
		nmap.put("productMerchantID", merchant.getId());
		int pagecurr = 1;
		if(request.getParameter("pagecurr")!=null){
			pagecurr = Integer.parseInt(request.getParameter("pagecurr"));
		}
		int pageSize = 16;
		String url = "productList"; 
		String params = "&merchant="+merchant.getUuid();
		String pcName= request.getParameter("pcName");//关键字
		String pcPriceMin= request.getParameter("pcPriceMin");//价格最小值
		String pcPriceMax= request.getParameter("pcPriceMax");//价格最大值
		String m = request.getParameter("merM");
		if(m!=null&&m.length()>0){
			if(m.equals("other")){
				nmap.put("productMerchantID", merchant.getId());
				nmap.put("merMId", 0l);
				params+="&merM="+m;
			}else{
				MerModularPO merM = conn_merModular.get(m);
				nmap.put("merMId", merM.getId());
				params+="&merM="+m;
			}
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

		//商家下面的产品
		List<ProductPO> productpo = conn_product.getListByPageB(nmap, pagecurr,pageSize);
		List<ProductVO> products=ProductVO.getConverter(ProductVO.class).convert(productpo, ProductVO.class);

		
		//图片路径
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		int count = conn_product.getListByPageBC(nmap);
		
		
		//商品分类字符串
		List<MerModularPO> merMs = merchant.getMerModulars();
		if(merMs!=null&&merMs.size()>0){
			String path = request.getContextPath();
			HashMap<Long, List<MerModularPO>> merMap = queryList(merMs);
			List<MerModularPO> merM0s =  merMap.get(0l);
			StringBuilder sbHtml =new StringBuilder();
			for (MerModularPO merModularPO : merM0s) {
				String fields ="merMId";
				Object values =merModularPO.getId();
				sbHtml.append("<li><a href='"+path+"/web/merchant/productList?merchant="+merchant.getUuid()+"&merM="+merModularPO.getUuid()+"'><cite class='select"+merModularPO.getUuid()+" select'>"+merModularPO.getName()+"("+conn_product.countProduct(fields,values)+")</cite></a></li>");
				sbHtml = dgWString(sbHtml,merModularPO.getId(),merMap,path,merchant);
			}
			sbHtml.append("<li><a href='"+path+"/web/merchant/productList?merchant="+merchant.getUuid()+"&merM=other'><cite class='selectother select'>其他>></cite></a></li>");
			strMap.put("sbHtml", sbHtml);
		}else{
			strMap.put("sbHtml", null);
		}
		strMap.put("merchant", merchant);
		strMap.put("productList", products);
		strMap.put("sysConfig", sysConfig);
		strMap.put("pages", getPages(pagecurr,count,pageSize,url,params));
		strMap.put("pcName", pcName);
		strMap.put("pcPriceMin", pcPriceMin);
		strMap.put("pcPriceMax", pcPriceMax);
		strMap.put("merM", m);
		strMap.put("count", count);
		ModelAndView mav = new ModelAndView("web/merchant/productList",strMap);
		return mav;		
	}




	//产品列表页
	@RequestMapping(value = "/merchantList", method= RequestMethod.GET)
	public ModelAndView productList(HttpServletRequest request) throws Exception {
		ModelAndView mv =null;
		Map<String, Object> nmap=new HashMap<String, Object>();
		Map<String, Object> strMap=new HashMap<String, Object>();
		long comId = getComIdSession(); //分公司Id
		//前台获取参数
		String modularCode= request.getParameter("m");//模块
		String classCode= request.getParameter("mc");//类

		String merName= request.getParameter("merName");//关键字
		int pageSize=16;  //页
		int pagecurr=1;  //页码
		if(request.getParameter("pagecurr")!=null && request.getParameter("pagecurr").length()>0){
			pagecurr=Integer.parseInt(request.getParameter("pagecurr"));
		}
		String url="merchantList";  //url
		String params="";          //参数

		//模块、城市、是否显示、首页显示。审核通过"productModularCode" modularCode
		nmap.put("comId", comId);
		if(modularCode!=null&&modularCode.length()>0){
			nmap.put("productModularCode",modularCode);
			params+="&m="+modularCode;
		}
		if(classCode!=null&&classCode.length()>0){
			nmap.put("productClassCode",classCode);
			params+="&mc="+classCode;
		}
		if(merName!=null&&merName.length()>0){
			merName=encodeStr(merName);
			nmap.put("shopName",merName);
			params+="&merName="+merName;
		}


		//产品List
//		List<MerchantPO> merchantpo = conn_merchant.getListByPageB(nmap, pagecurr,pageSize);
//		List<MerchantVO> merchants=MerchantVO.getConverter(MerchantVO.class).convert(merchantpo, MerchantVO.class);
//		int count = conn_merchant.getListByPageBC(nmap);//产品总数
		List<MerchantDTO> merchants = conn_merchant.getMerchantByPro(nmap, pagecurr,pageSize);
		int count = conn_merchant.getMerchantByProC(nmap);


		//获取模块(显示;无排序)
		List<ModularPO> modulars = conn_modular.findByComIsv(comId);
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
		strMap.put("merName",merName);
		strMap.put("merchants", merchants);
		strMap.put("sysConfig", sysConfig);
		strMap.put("pages", getPages(pagecurr,count,pageSize,url,params));
		mv = new ModelAndView("web/merchant/merchantList",strMap);
		return mv;
	}




}
