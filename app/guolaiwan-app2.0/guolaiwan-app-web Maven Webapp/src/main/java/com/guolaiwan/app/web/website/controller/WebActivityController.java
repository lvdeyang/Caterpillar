package com.guolaiwan.app.web.website.controller;

import java.text.DecimalFormat;
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

import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.enumeration.MerchantSpecialBusiness;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.util.wrapper.HashMapWrapper;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

@Controller
@RequestMapping("/web/activity")
public class WebActivityController extends WebBaseControll{
	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private AddressDAO conn_address;

	@Autowired
	private SysConfigDAO conn_sysConfig;
	
	@Autowired
	private ActivityDAO conn_activity;


	// 首页搜索
	@RequestMapping(value = "/productList", method= RequestMethod.GET)
	public ModelAndView productSearch(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//商家
		Map<String,Object> strMap =new HashMap<String, Object>();
		String uuid = request.getParameter("activity");
		DecimalFormat df = new DecimalFormat("0.00"); 
		ActivityPO activity = conn_activity.get(uuid);

		

		//分页
		Map<String, Object> nmap = new HashMap<String, Object>();
		nmap.put("activityId", activity.getId());
		int pagecurr = 1;
		if(request.getParameter("pagecurr")!=null){
			pagecurr = Integer.parseInt(request.getParameter("pagecurr"));
		}
		int pageSize = 16;
		String url = "productList"; 
		String params = "&activity="+activity.getUuid();
		String pcName= request.getParameter("pcName");//关键字
		if(pcName!=null&&pcName.length()>0){
			pcName=encodeStr(pcName);
			nmap.put("productName",pcName);
			params+="&pcName="+pcName;
		}

		//活动下面的产品
		List<ProductDTO> products = conn_activity.findProsByActJS(nmap, pagecurr,pageSize);
		
		String activitType = activity.getType().getFiled();
		switch (activitType) {
		case "FIXEDPRICE":
			for (ProductDTO productDTO : products) {
				productDTO.setProductPricesStr(df.format((double)activity.getFixedPrice()/100));
			}
			break;
		
		}
		
	

		//图片路径
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		int count = conn_activity.countProsByActJS(nmap);
		strMap.put("productList", products);
		strMap.put("sysConfig", sysConfig);
		strMap.put("pages", getPages(pagecurr,count,pageSize,url,params));
		strMap.put("pcName", pcName);
		strMap.put("count", count);
		strMap.put("activity", activity);
		
		ModelAndView mav = new ModelAndView("web/activity/productList",strMap);
		return mav;		
	}

}
