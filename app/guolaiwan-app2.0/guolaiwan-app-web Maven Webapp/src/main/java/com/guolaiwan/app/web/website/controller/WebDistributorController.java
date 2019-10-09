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

import com.guolaiwan.app.interfac.controller.NewLiveController;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.DistributeProductVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.enumeration.MerchantSpecialBusiness;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.distribute.po.DistributorPo;
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

		strMap.put("productList", new ArrayList<DistributeProductVO>());
		strMap.put("sysConfig", sysConfig);
		strMap.put("pages", "");
		strMap.put("pcName", "");
		strMap.put("minPrice", 0);
		strMap.put("maxPrice", 0);
		strMap.put("count", 0);
		strMap.put("distributor", new DistributorPo());

		ModelAndView mav = new ModelAndView("web/distributor/productList",strMap);
		return mav;		
	}
	
	// 分销商列表
	@RequestMapping(value = "/distributorlist", method= RequestMethod.GET)
	public ModelAndView distributorList(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String,Object> strMap =new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("distributors", new ArrayList<DistributorPo>());
		strMap.put("sysConfig", sysConfig);
		strMap.put("pages", "");
		strMap.put("distributorName", "");
		strMap.put("count", 0);
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
        
		String[] pics = {};
		
		ProductVO _product = new ProductVO();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		List<CommentPO> comments = conn_comment.findByProId(0l, 1, 10);
		List<CommentVO> _comments = CommentVO.getConverter(CommentVO.class).convert(comments, CommentVO.class);
		
		strMap.put("product", _product);
		strMap.put("distributorProduct", new DistributeProductVO());
		strMap.put("comments", _comments);
		strMap.put("distributor", new DistributorPo());
		strMap.put("pics", pics);
		strMap.put("sysConfig", sysConfig);
		ModelAndView mav = new ModelAndView("web/distributor/productInfo",strMap);
		return mav;		
	}

}
