package com.guolaiwan.app.web.admin.controller;

import java.lang.reflect.InvocationTargetException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.CarouselVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.CarouselDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyRelDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.RecommendType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.CarouselPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/carousel")
public class CarouselController extends BaseController{
	@Autowired
	private CarouselDAO conn_Carousel;

	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private CompanyRelDAO conn_companyRel;
	
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getCarousel(HttpServletRequest request){
		long comId = getLoginInfo().getComId();
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfigPO = conn_sysConfig.getSysConfig();
		strMap.put("sysConfigPO", sysConfigPO);
		ModelAndView mv =null;

		mv = new ModelAndView("admin/carousel/list",strMap);
		return mv;	

	}


	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="list.do",method = RequestMethod.POST)
	public Map<String,Object> GetList(int page,int limit) throws Exception{
		long comId = getLoginInfo().getComId();
		List<CarouselPO> Carouselpo = conn_Carousel.findByCom(comId, page, limit);
		int count = conn_Carousel.countByCom(comId);
		List<CarouselVO> Carouselvo = CarouselVO.getConverter(CarouselVO.class).convert(Carouselpo, CarouselVO.class);
		
		for (CarouselVO carouselVO2 : Carouselvo) {
			carouselVO2.setReCount(conn_companyRel.countByRelIdS(carouselVO2.getId(),"CAROUSEL"));
		}
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", Carouselvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count",count );
		return map;			
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		CarouselPO carousel = new CarouselPO();
		carousel.setUpdateTime(new Date());
		carousel.setComId(getLoginInfo().getComId());
		carousel.setComName(getLoginInfo().getComName());
		carousel.setClassify("PRODUCT");
		conn_Carousel.save(carousel);
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id =Long.parseLong(request.getParameter("id"));
		conn_Carousel.delete(id);
		conn_companyRel.deleteByRelId(id, "CAROUSEL");
		return "success";
	}

	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String field = request.getParameter("field");
		String value = request.getParameter("value");


		CarouselPO carousel = conn_Carousel.get(id);

		if(field.equals("sort")){
			carousel.setSort(Integer.parseInt(value));
		}else{
			//字符串首字母转成大写
			char[] cs = field.toCharArray();
			cs[0]-=32;
			String.valueOf(cs);
			//反射
			Class<CarouselPO> carClass = CarouselPO.class;

			carClass.getDeclaredMethod("set"+String.valueOf(cs), String.class).invoke(carousel, value);
			//方法名，输入参数的类型，对象，输入参数的值
		}
		conn_Carousel.save(carousel);

		return "success";
	}

	//修改轮播图类别
	@ResponseBody
	@RequestMapping(value="/updateClassify.do",method= RequestMethod.POST)
	public String updateClassify(HttpServletRequest request) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		long id = Long.parseLong(request.getParameter("id"));
		String classify = request.getParameter("classify");
		
		CarouselPO carouselPO = conn_Carousel.get(id);
		carouselPO.setClassify(classify);
		conn_Carousel.saveOrUpdate(carouselPO);
		
		return "success";
	}
	
	//是否显示
	@ResponseBody
	@RequestMapping(value="/changeIsv.do",method= RequestMethod.POST)
	public String changeIsv(HttpServletRequest request) throws Exception {
		long comId = getLoginInfo().getComId();
		long id = Long.parseLong(request.getParameter("id"));
		int value = Integer.parseInt(request.getParameter("value"));
		if (value == 0) {
			if (comId == 1l) {

				CarouselPO carousel = conn_Carousel.get(id);
				carousel.setEnable(value);
				conn_Carousel.saveOrUpdate(carousel);
				return "success";
			}else {
				//子公司 获取推荐的个数
				int relCount = conn_companyRel.countByComIdS(comId, "CAROUSEL");
			}
		}else{
			if(comId==1l){
				//获取总公司显示轮播的个数
				int count = conn_Carousel.countByComEn(comId);
				if(count >= 10){
					return "more";
				}
			}else{
				//子公司 获取推荐的个数
				int relCount = conn_companyRel.countByComIdS(comId, "CAROUSEL");
			}
		}
		
		CarouselPO carousel = conn_Carousel.get(id);
		carousel.setEnable(value);
		conn_Carousel.saveOrUpdate(carousel);
		return "success";
	}

	//选择图片
	@ResponseBody
	@RequestMapping(value="/pic.do",method= RequestMethod.POST)
	public String pic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long picId = Long.parseLong(request.getParameter("picId"));
		long id = Long.parseLong(request.getParameter("id"));
		CarouselPO carousel = conn_Carousel.get(id);
		carousel.setPicId(picId);
		carousel.setSlidepic(pic);
		conn_Carousel.saveOrUpdate(carousel);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkProduct.do/{carid}/{classify}" , method = RequestMethod.GET)
	public ModelAndView updateProductName(@PathVariable long carid,@PathVariable String classify , HttpServletRequest request) throws Exception{
		ModelAndView mav = null;
		mav = new ModelAndView("admin/carousel/prolist");
		request.setAttribute("carid", carid);
		request.setAttribute("classify", classify);
		return mav;
	}
	
	@Autowired
	private ProductDAO conn_product;
	@Autowired
	private MerchantDAO conn_merchant;
	@Autowired
	private ActivityDAO conn_activity;
	
	@ResponseBody
	@RequestMapping(value = "/checkProduct.do/proList.do" , method = RequestMethod.POST)
	public Map<String, Object> getPros(int page , int limit) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ProductPO> polist = conn_product.findByCom(getLoginInfo().getComId(), page, limit);
		List<ProductVO> volist = ProductVO.getConverter(ProductVO.class).convert(polist, ProductVO.class);
		map.put("data", volist);
		map.put("count", conn_product.countByCom(getLoginInfo().getComId()));
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkProduct.do/merList.do" , method = RequestMethod.POST)
	public Map<String, Object> getMers(int page , int limit) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<MerchantPO> polist = conn_merchant.findMerByCom(1l, page, limit);
		List<MerchantVO> volist = MerchantVO.getConverter(MerchantVO.class).convert(polist, MerchantVO.class);
		map.put("data", volist);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkProduct.do/actList.do" , method = RequestMethod.POST)
	public Map<String, Object> getActs(int page , int limit) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<ActivityPO> polist = conn_activity.findByCom(1l, page, limit);
		List<ActivityVO> volist = ActivityVO.getConverter(ActivityVO.class).convert(polist, ActivityVO.class);
		map.put("data", volist);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkProduct.do/selectPro.do/{id}/{carid}/{cla}" , method = RequestMethod.GET)
	public String selectPro(@PathVariable long id , @PathVariable long carid, @PathVariable String cla){
		String name = "";
		switch (cla) {
		case "PRODUCT":
			ProductPO productPO = conn_product.get(id);
			name = productPO.getProductName();
			break;
		case "MERCHANT":
			MerchantPO merchantPO = conn_merchant.get(id);	
			name = merchantPO.getShopName();
			break;
		case "ACTIVITY":
			ActivityPO activityPO = conn_activity.get(id);
			name = activityPO.getName();
			break;
		default:
			break;
		}
		CarouselPO carouselPO = conn_Carousel.get(carid);
		carouselPO.setProductId(id);
		carouselPO.setProductName(name);
		conn_Carousel.saveOrUpdate(carouselPO);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkProduct.do/mohuSelect.do" , method = RequestMethod.POST , produces = "application/json; charset=utf-8")
	public Map<String, Object> getMoHu(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String mohus = request.getParameter("mohus");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		List<ProductPO> polist = conn_product.getMoHu(mohus , Integer.parseInt(page) , Integer.parseInt(limit));
		List<ProductVO> volist = ProductVO.getConverter(ProductVO.class).convert(polist, ProductVO.class);
		map.put("data", volist);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}
	
}