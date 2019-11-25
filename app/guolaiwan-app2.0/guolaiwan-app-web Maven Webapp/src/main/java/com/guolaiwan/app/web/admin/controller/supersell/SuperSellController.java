package com.guolaiwan.app.web.admin.controller.supersell;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.supersell.SuperSellDAO;
import com.guolaiwan.bussiness.admin.dao.supersell.SuperSellRelDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.supersell.SuperSellPO;
import com.guolaiwan.bussiness.admin.po.supersell.SuperSellRelPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/supersell")
public class SuperSellController extends BaseController {

	@Autowired
	private SuperSellDAO conn_supersell;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	@Autowired
	private SuperSellRelDAO conn_supersellRel;

	@Autowired
	private ProductDAO conn_product;

	// 查询列表页面刘立强新增测试
	// 同时添加

	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView supersellList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("admin/supersell/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> listdo(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_supersell.countAll();
		List<SuperSellPO> activitys = conn_supersell.findAll(page, limit);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", activitys);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/supersell/add");
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		SuperSellPO superSellPO = new SuperSellPO();
		superSellPO.setName(name);
		conn_supersell.save(superSellPO);
		return "success";
	}

	// 编辑数据
	@ResponseBody
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String edit(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		SuperSellPO superSellPO=conn_supersell.get(id);

		String field = request.getParameter("field");
		
		if (field.equals("name")) {
			String name = request.getParameter("value");
			superSellPO.setName(name);
			conn_supersell.saveOrUpdate(superSellPO);
			return "success";
		}
		return "error";

	}

	// 选择图片
	@ResponseBody
	@RequestMapping(value = "/pic.do", method = RequestMethod.POST)
	public String pic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long id = Long.parseLong(request.getParameter("id"));
		SuperSellPO superSellPO = conn_supersell.get(id);
		superSellPO.setPic(pic);
		conn_supersell.saveOrUpdate(superSellPO);
		return "success";
	}

	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		conn_supersell.delete(id);
		// 删掉关联商品
		return "success";
	}

	// 下面是商品
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ModelAndView pList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long supersellId = Long.parseLong(request.getParameter("supersellId"));
		String content = request.getParameter("content");
		if (content != null && content != "") {
			content = encodeStr(content);
		}
		SuperSellPO superSellPO = conn_supersell.get(supersellId);
		strMap.put("supersell", superSellPO);
		strMap.put("content", content);
		ModelAndView mv = new ModelAndView("admin/supersell/productList", strMap);
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/productList.do", method = RequestMethod.POST)
	public Map<String, Object> pListdo(int page, int limit, HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		String pName = request.getParameter("pName");
		long supersellId = Long.parseLong(request.getParameter("supersellId"));

		String pId = request.getParameter("pId");
		Long pidl = null;
		if (pId != null && pId != "") {
			pidl = Long.parseLong(pId);
		}

		 List<SuperSellRelPO> superSellRelPOs = conn_supersellRel.findBysuperIdAndProduct(supersellId,pName,pidl, page, limit);
		 int count = conn_supersellRel.countBysuperIdAndProduct(supersellId,pName,pidl);
		 strMap.put("data", superSellRelPOs);
		 strMap.put("code", "0");
		 strMap.put("msg", "");
		 strMap.put("count", count);
		 return strMap;
	}

	// 弹出添加产品页面
	@RequestMapping(value = "/bdPro", method = RequestMethod.GET)
	public ModelAndView bdPro(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String supersellId = request.getParameter("supersellId");
		strMap.put("supersellId", supersellId);
		ModelAndView mv = new ModelAndView("admin/supersell/bdPro", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/bdPro.do", method = RequestMethod.POST)
	public String bdProDo(long pId, long supersellId, String pName) {
		int count = conn_supersellRel.countByProAndSellId(pId, supersellId);
		if (count != 0) {
			return "chongfu";
		}
		ProductPO productPO=conn_product.get(pId);
		SuperSellRelPO superSellRelPO=new SuperSellRelPO();
		superSellRelPO.setMerchantName(productPO.getProductMerchantName());
		superSellRelPO.setProductName(productPO.getProductName());
		superSellRelPO.setProductPic(productPO.getProductShowPic());
		superSellRelPO.setProductId(pId);
		superSellRelPO.setSupersellId(supersellId);
		superSellRelPO.setPrice(productPO.getProductPrice());
		conn_supersellRel.save(superSellRelPO);
		return "success";
	}
	

	@ResponseBody
	@RequestMapping(value = "/proList.do", method = RequestMethod.POST)
	public Map<String, Object> proList(int page, int limit, HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		String pName = request.getParameter("pName");
		String mName = request.getParameter("mName");

		map.put("productName", pName);
		map.put("productMerchantName", mName);
		int count = conn_product.getCountByPageE(map);
		List<ProductPO> listpo = conn_product.getListByPageE(map, page, limit);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("data", listvo);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		return strMap;
	}

	@ResponseBody
	@RequestMapping(value = "/delRel.do", method = RequestMethod.POST)
	public String bdProDo(long relId) {
		conn_supersellRel.delete(relId);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/update.do/{id}", method = RequestMethod.GET)
	public ModelAndView toUpdate(@PathVariable long id) {
		ModelAndView mav = null;
		Map<String, Object> map = new HashMap<String, Object>();
		SuperSellRelPO superSellRelPO=conn_supersellRel.get(id);
		map.put("rel", superSellRelPO);
		mav = new ModelAndView("admin/supersell/modify", map);
		return mav;
	}

	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public ModelAndView updateProduct(HttpServletRequest request) throws ParseException {

		Map<String, Object> map = new HashMap<String, Object>();
		long relId=Long.parseLong(request.getParameter("relId"));
		long price=Long.parseLong(request.getParameter("price"));
		long oldPrice=Long.parseLong(request.getParameter("oldPrice"));
		SuperSellRelPO superSellRelPO=conn_supersellRel.get(relId);
		superSellRelPO.setPrice(price);
		superSellRelPO.setOldPrice(oldPrice);
		conn_supersellRel.saveOrUpdate(superSellRelPO);
		map.put("rel", superSellRelPO);
		ModelAndView mav = new ModelAndView("admin/supersell/modify", map);
		return mav;
	}

}
