package com.guolaiwan.app.web.admin.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.VoteModularDAO;
import com.guolaiwan.bussiness.admin.dao.VoteProductDAO;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.VoteModularPO;
import com.guolaiwan.bussiness.admin.po.VoteProductPO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/admin/vote")
public class VoteController extends BaseController {

	@Autowired
	private VoteModularDAO votemodularDAO;
	@Autowired
	private VoteProductDAO voteproductDAO;
	@Autowired
	private ProductDAO conn_product;
	
	// 显示列表页面
	@RequestMapping(value = "/list")
	public ModelAndView activityList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/vote/list");
		return mv;
	}

	//查询所有标签
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long comId = getLoginInfo().getComId();
		int count = votemodularDAO.countAll();
		List<VoteModularPO>  votemodulars= votemodularDAO.findAll();
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", votemodulars);
		return strMap;
	}
	
	// 添加数据页面
	@RequestMapping(value = "/addv")
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/vote/add");
		return mv;
	}
	
	// 添加数据页面
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String modularName = request.getParameter("modularName");
		String modularDescribe = request.getParameter("modularDescribe");
		VoteModularPO vote=new VoteModularPO();
		vote.setModularName(modularName);
		vote.setModularDescribe(modularDescribe);
		SimpleDateFormat bf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = bf.format(new Date());
		vote.setAddTime(format);
		votemodularDAO.save(vote);
		return "success";
	}
	
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
		votemodularDAO.delete(id);
		return "success";
	}
	
	
	// 编辑数据
	@ResponseBody
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String edit(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		VoteModularPO voteModular = votemodularDAO.get(id);
		String field = request.getParameter("field");
		if (field.equals("modularName")) {
			String modularName = request.getParameter("value");
			voteModular.setModularName(modularName);
			votemodularDAO.save(voteModular);
			return "success";
		}else if(field.equals("modularDescribe")){
			String modularDescribe = request.getParameter("value");
			voteModular.setModularDescribe(modularDescribe);;
			votemodularDAO.save(voteModular);
			return "success";
		}
		return "error";
	}
	
	
	// 展示产品页面
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ModelAndView pList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long modularId = Long.parseLong(request.getParameter("modularId"));
		String content = request.getParameter("content");
		if (content != null && content != "") {
			content = encodeStr(content);
		}
		VoteModularPO voteModular = votemodularDAO.get(modularId);
		strMap.put("voteModular", voteModular);
		strMap.put("content", content);
		ModelAndView mv = new ModelAndView("admin/vote/productList", strMap);
			return mv;
		}
	
	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/productList.do", method = RequestMethod.POST)
	public Map<String, Object> pListdo(int page, int limit, HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		String pName = request.getParameter("pName");
		long moId = Long.parseLong(request.getParameter("moId"));
		String pId = request.getParameter("pId");
		Long pidl = null;
		if (pId != null && pId != "") {
			pidl = Long.parseLong(pId);
		}
		List<VoteProductPO> voteproducts = voteproductDAO.findByAcId(moId, pName, pidl, page, limit);
		int count = voteproductDAO.countByMoId(moId,pName);
		strMap.put("data", voteproducts);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		return strMap;
	}
	
	// 弹出添加产品页面
	@RequestMapping(value = "/bdPro", method = RequestMethod.GET)
	public ModelAndView bdPro(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String moId = request.getParameter("moId");
		strMap.put("moId", moId);
		ModelAndView mv = new ModelAndView("admin/vote/bdPro", strMap);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/proList.do", method = RequestMethod.POST)
	public Map<String, Object> proList(int page, int limit, HttpServletRequest request) throws Exception {
		long comId = getLoginInfo().getComId();

		Map<String, Object> map = new HashMap<String, Object>();
		String pName = request.getParameter("pName");
		String mName = request.getParameter("mName");

		String pId = request.getParameter("pId");
		if (pId != null && pId != "") {
			long pIdLo = Long.parseLong(pId);
			map.put("id", pIdLo);
		}
		map.put("comId", comId);
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
	@RequestMapping(value = "/bdPro.do", method = RequestMethod.POST)
	public String bdProDo(long pId, long acId, String pName) {
		int count = voteproductDAO.countByPro(pId);
		if (count != 0) {
			return "chongfu";
		}
		VoteProductPO voteproduct=new VoteProductPO();
		voteproduct.setModularcode(acId);
		voteproduct.setProductId(pId);
		voteproduct.setProductName(pName);
		voteproductDAO.save(voteproduct);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delRel.do", method = RequestMethod.POST)
	public String bdProDo(long relId) {
		voteproductDAO.delete(relId);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/delAll.do", method = RequestMethod.POST)
	public String delAll(long acId) {
		voteproductDAO.deleteByMoId(acId);
		return "success";
	}
	
	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/gotovoteproductdetails")
	public ModelAndView voteProductDetails(HttpServletRequest request) {
		long productId=Long.parseLong(request.getParameter("productId"));
		ModelAndView mv = new ModelAndView("mobile/vote/voteproductdetails");
		mv.addObject("product", conn_product.get(productId));
		return mv;
	}
	
	// 选择图片 张羽 5/8 新增
	@ResponseBody
	@RequestMapping(value = "/votemodularpic", method = RequestMethod.POST)
	public String voteModularPic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long picId = Long.parseLong(request.getParameter("picId"));
		long id = Long.parseLong(request.getParameter("id"));
		VoteModularPO voteModular = votemodularDAO.get(id);
		voteModular.setPicId(picId);
		voteModular.setSlidepic(pic);
		votemodularDAO.saveOrUpdate(voteModular);
		return "success";
	}
}
