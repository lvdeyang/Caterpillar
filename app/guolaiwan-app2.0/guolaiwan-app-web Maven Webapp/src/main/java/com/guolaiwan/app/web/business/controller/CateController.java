package com.guolaiwan.app.web.business.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.Guide.controller.integralControll;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantChildrenDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.MerchantChildrenPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

/**
 * 美食
 */
@RestController
@RequestMapping("/cate")
public class CateController extends BaseController {
	private final int recommendSize = 6;
	@Autowired
	private MerchantDAO conn_merchant;
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private MerchantChildrenDao merchant_Children;
	@Autowired
	private MerchantDAO Merchantdao;
	@Autowired
	private MerchantDAO Mer_chant;
	@Autowired
	private OrderInfoDAO orderInfoDao;
	@Autowired
	 private ProductDAO productDao;
	/**
	 * 二级页面 轮播图（模块）
	 * 美食轮播图
	 * @param modularCode
	 * @return merchants
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getRecommendByModu", method = RequestMethod.GET)
	public List<MerchantVO> getRecommendByMou(HttpServletRequest request, HttpServletResponse response,
			String merchantId) throws Exception {
		List<MerchantChildrenPO> merchantChildren = merchant_Children.getCate(Long.parseLong(merchantId));
		 List<MerchantVO> list = new ArrayList<MerchantVO>();
		for (MerchantChildrenPO merchantChildrenPO : merchantChildren) {
			List<MerchantPO> po = Merchantdao.getMerchantById(merchantChildrenPO.getChildrenId());
			for (MerchantPO merchantPO : po) {
				if ( "0003".equals(merchantPO.getModularCode()) && merchantPO !=null ) {
					MerchantVO name = new MerchantVO();
					name.setId(merchantPO.getId());
					name.setShopName(merchantPO.getShopName());
					name.setShopPic(merchantPO.getShopPic());
					/*List<MerchantVO> Merchant = MerchantVO.getConverter(MerchantVO.class).convert(po, MerchantVO.class);*/
					list.add( name );
				}
			}
		}
		return list;
	}
	
	//按照商品类型所有的商户
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/search")
	public Map<String, Object> search(HttpServletRequest request) throws Exception {
		long merchantId=Long.parseLong(request.getParameter("merchantId"));
		String name=request.getParameter("name");
		String type=request.getParameter("type");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<MerchantPO> merchantlist=new ArrayList<MerchantPO>();
		List<MerchantChildrenPO> merchantChildren = merchant_Children.getCate(merchantId);
		for (MerchantChildrenPO merchantChildrenPO : merchantChildren) {
			List<MerchantPO> allMerchant = Mer_chant.getMerchantById(merchantChildrenPO.getChildrenId());
			for (MerchantPO merchantPO : allMerchant) {
				Boolean status  =   merchantPO.getShopName().contains(name);
			if (status  && merchantPO.getModularCode().equals(type)) {
				merchantlist.add(merchantPO);
			}
		  }	
		}
		List<MerchantVO> merlist = MerchantVO.getConverter(MerchantVO.class).convert(merchantlist, MerchantVO.class);
		hashMap.put("merlist", merlist);
		return hashMap;
	}
	
	
	//菜单名称
	@ResponseBody
	@RequestMapping(value="/order/list")
	public Map<String, Object> orderList(HttpServletRequest request) throws Exception{
		String merchantId = request.getParameter("merchantId");	 
		//根据productMerchantID查询商品信息
		List<ProductPO> productPOs = productDao.findByMerchantId(Long.parseLong(merchantId)); 
		//对商品进行类型的存储
		Map<String, Object> hashMap = new HashMap<String, Object>();
		//遍历商品信息
		List<ProductVO> merlist	=null;
		for(ProductPO po :productPOs){
			merlist	 = ProductVO.getConverter(ProductVO.class).convert(productPOs, ProductVO.class);
		}
		hashMap.put("name", merlist);
		return hashMap ;	  
	}
	
	
				
	
}
