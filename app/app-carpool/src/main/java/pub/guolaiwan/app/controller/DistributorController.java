package pub.guolaiwan.app.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pub.caterpillar.app.carpool.po.CarPO;
import pub.caterpillar.app.carpool.vo.CarVO;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.guolaiwan.app.dao.DistributorDao;
import pub.guolaiwan.app.dao.RegionDao;
import pub.guolaiwan.app.po.RegionPo;

@Controller
@RequestMapping(value = "/distributor")
public class DistributorController {

	@Autowired
	private DistributorDao conn_distributor;
	@Autowired
	private RegionDao conn_region;
	@RequestMapping(value = "/distribute/index/{regionCode}")
	public ModelAndView distributeIndex(
			HttpServletRequest request,String regionCode) throws Exception{
		ModelAndView mv = null;
        mv = new ModelAndView("mobile/guolaiwan/distribute");
        mv.addObject("regionCode", regionCode);
		return mv;
	}
	@RequestMapping(value = "/apply/index")
	public ModelAndView applyIndex(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
        mv = new ModelAndView("mobile/guolaiwan/apply-distributor");
		return mv;
	}
	@RequestMapping(value = "/purchase/index")
	public ModelAndView purchaseIndex(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
        mv = new ModelAndView("mobile/guolaiwan/purchase");
		return mv;
	}
	@RequestMapping(value = "/product/index")
	public ModelAndView productIndex(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
        mv = new ModelAndView("mobile/guolaiwan/distribute-product");
		return mv;
	}
	@RequestMapping(value = "/pay/index")
	public ModelAndView payIndex(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
        mv = new ModelAndView("mobile/guolaiwan/pay");
		return mv;
	}
	@RequestMapping(value = "/my/index")
	public ModelAndView myIndex(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
        mv = new ModelAndView("mobile/guolaiwan/my-distribute");
		return mv;
	}
	@RequestMapping(value = "/mod/product/index")
	public ModelAndView modProductIndex(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
        mv = new ModelAndView("mobile/guolaiwan/modify-product");
		return mv;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/region", method = RequestMethod.GET)
	public Object queryAllFirstRegion(HttpServletRequest request) throws Exception{
		List<RegionPo> regions = conn_region.queryAllFirstRegion();
		return regions;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/recoments/{regionCode}", method = RequestMethod.GET)
	public Object queryRecomments(HttpServletRequest request,String regionCode) throws Exception{
		return new Object();
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/secrecoments/{regionCode}", method = RequestMethod.GET)
	public Object querySecondRecomments(HttpServletRequest request,String regionCode) throws Exception{
		return new Object();
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/thirdcoments/{regionCode}", method = RequestMethod.GET)
	public Object queryThirdRecomments(HttpServletRequest request,String regionCode) throws Exception{
		return new Object();
	}
	
}
