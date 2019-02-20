package com.guolaiwan.app.web.website.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.CarouselVO;
import com.guolaiwan.app.web.admin.vo.CompanyVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ModularVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.admin.vo.ReSpecialVO;
import com.guolaiwan.app.web.admin.vo.ReVideoVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.CarouselDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyRelDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.ReSpecialDAO;
import com.guolaiwan.bussiness.admin.dao.ReVideoDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.CarouselPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.CompanyRelPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.ReSpecialPO;
import com.guolaiwan.bussiness.admin.po.ReVideoPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.merchant.car.dao.RouteDAO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

@Controller
public class WebIndexController extends WebBaseControll {
	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private ModularDAO conn_modular;

	@Autowired
	private ModularClassDAO conn_modularClass;

	@Autowired
	private CarouselDAO conn_Carousel;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	@Autowired
	private RouteDAO conn_route;

	@Autowired
	private MerchantDAO conn_merchant;
	
	@Autowired
	private CompanyDAO conn_company;
	
	@Autowired
	private CompanyRelDAO conn_company_relation;
	
	@Autowired
	private ReVideoDAO conn_revideo;
	
	@Autowired
	private ReSpecialDAO conn_respecial;
	@Autowired
	private ActivityDAO conn_activity;


	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap=new HashMap<String, Object>();
		SysConfigPO sysconfig = conn_sysConfig.getSysConfig();
		DecimalFormat df = new DecimalFormat("0.00"); 
		//城市
		String cityCode = getCityCodeByDomain();//模块列表
		
		setComIdSession();   //将comId存到session中
		long comId = getComIdSession();   //session中获取comId

		//获取模块(显示;有排序)
		List<ModularPO> modulars = conn_modular.findByComIsv(comId);
		List<ModularVO> modularsVO=ModularVO.getConverter(ModularVO.class).convert(modulars, ModularVO.class);

		
		//活动
		List<ActivityPO> activitys = conn_activity.findByCom(comId);
		List<ActivityVO> _activitys = 
				ActivityVO.getConverter(ActivityVO.class).convert(activitys, ActivityVO.class);
		for (ActivityVO activityVO : _activitys) {
			//图片
			activityVO.setPic(sysconfig.getWebUrl() + activityVO.getPic());
			//商品
			List<ProductDTO> _products = conn_activity.findProsByAct(activityVO.getId(),1,10);
			String atype = activityVO.getType();
			switch (atype) {
			case "固定价格":
				for (ProductDTO productDTO : _products) {
					productDTO.setProductShowPic(
							sysconfig.getWebUrl()+productDTO.getProductShowPic()
							);
					productDTO.setProductPricesStr(activityVO.getFixedPrice());
					productDTO.setProductOldPriceStr(df.format((double)productDTO.getProductOldPrice()/100));
					//多图
					productDTO.setProductMorePic(split(productDTO.getProductMorePic(),sysconfig.getWebUrl()));
				}
				activityVO.setProducts(_products);
				break;

			default:
				for (ProductDTO productDTO : _products) {
					productDTO.setProductShowPic(
							sysconfig.getWebUrl()+productDTO.getProductShowPic()
							);
					productDTO.setProductPricesStr(df.format((double)productDTO.getProductPrice()/100));
					productDTO.setProductOldPriceStr(df.format((double)productDTO.getProductOldPrice()/100));
					//多图
					productDTO.setProductMorePic(split(productDTO.getProductMorePic(),sysconfig.getWebUrl()));
				}
				activityVO.setProducts(_products);
				break;
			}
			
		}
		
		//获取热销产品(城市、显示;销量排序)
		List<ProductPO> hotProductsP = conn_product.getListHot(comId,1, 5);
		List<ProductVO> hotProducts=ProductVO.getConverter(ProductVO.class).convert(hotProductsP, ProductVO.class);

		for (ModularVO modular : modularsVO) {
			//分类
			List<ModularClassPO> classList = conn_modularClass.findByModular(modular.getModularCode());
			modular.setModularClasses(classList);
			//一元购存入产品
			if(modular.getModularCode().equals("01112")){
				List<ProductPO> productPOs  = conn_product.findByModular(modular.getModularCode(),comId, 1, 10);
				List<ProductVO> products=ProductVO.getConverter(ProductVO.class).convert(productPOs, ProductVO.class);
				modular.setProducts(products);
			//其他模块的存入商家
			}else{
				//获取模块下的产品(城市、显示、模块;无排序);
				List<MerchantPO> merchantPOs = conn_merchant.findByModular(modular.getModularCode(),comId,1,10);
				List<MerchantVO> merchants=MerchantVO.getConverter(MerchantVO.class).convert(merchantPOs, MerchantVO.class);
				
				modular.setMerchants(merchants);
			}
		}

		//轮播图
		List<CarouselPO> carouselsP = null;
		if(comId == 1l){
			carouselsP = conn_Carousel.queryEnableByCompany(comId);
		}else{
			List<CompanyRelPO> relations = conn_company_relation.queryCompanyCarousel(comId);
			List<Long> relaIds = new ArrayList<Long>();
			for(CompanyRelPO relation:relations){
				relaIds.add(relation.getRelaId());
			}
			carouselsP = conn_Carousel.queryEnableById(relaIds);
		}
		List<CarouselVO> carousels=CarouselVO.getConverter(CarouselVO.class).convert(carouselsP, CarouselVO.class);
		

		//租车
		List<RoutePO> routes = conn_route.findAll();

		strMap.put("carousels", carousels);
		strMap.put("activitys", _activitys);
		strMap.put("sysconfig", sysconfig);
		strMap.put("modulars",modularsVO);
		strMap.put("getDomain",getCityCodeByDomain());
		strMap.put("hotProducts",hotProducts);
		strMap.put("routes",routes);
		
		//分公司
		List<CompanyPO> comps = conn_company.findAll();
		List<CompanyVO> view_comps = CompanyVO.getConverter(CompanyVO.class).convert(comps, CompanyVO.class);
		strMap.put("comps", view_comps);

		//视频推荐
		List<ReVideoPO> videos = null;
		if(comId == 1l){
			videos = conn_revideo.queryEnableByCompany(comId);
		}else{
			List<CompanyRelPO> relations = conn_company_relation.queryCompanyRevideo(comId);
			List<Long> relaIds = new ArrayList<Long>();
			for(CompanyRelPO relation:relations){
				relaIds.add(relation.getRelaId());
			}
			videos = conn_revideo.queryEnableById(relaIds);
		}
		List<ReVideoVO> view_revideos = ReVideoVO.getConverter(ReVideoVO.class).convert(videos, ReVideoVO.class);
		strMap.put("videos", view_revideos);
		
		//特别推荐
		List<ReSpecialPO> specials = null;
		if(comId == 1l){
			specials = conn_respecial.queryEnableByCompany(comId);
		}else{
			List<CompanyRelPO> relations = conn_company_relation.queryCompanyRespecial(comId);
			List<Long> relaIds = new ArrayList<Long>();
			for(CompanyRelPO relation:relations){
				relaIds.add(relation.getRelaId());
			}
			specials = conn_respecial.queryEnableById(relaIds);
		}
		List<ReSpecialVO> view_specials = ReSpecialVO.getConverter(ReSpecialVO.class).convert(specials, ReSpecialVO.class);
		strMap.put("specials", view_specials);
		
		ModelAndView mv = new ModelAndView("web/index",strMap);
		mv.addAllObjects(strMap);
		return mv;

	}

	//换一换
	@ResponseBody
	@RequestMapping(value="/changeH.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> changeH(int pagecurr) throws Exception {
		//获取session中色comId
		long comId = getComIdSession();
		//城市
		String cityCode = getCityCodeByDomain();//模块列表
		//获取热销产品(城市、显示;销量排序)
		List<ProductPO> hotProducts = conn_product.getListHot(comId,pagecurr, 5);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("hotProducts", hotProducts);
		return map;
	}

	@RequestMapping(value="/download/app",method= RequestMethod.GET)
	public String  download(HttpServletRequest request){
		return "download/guolaiwan.apk";
	}


}
