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
import com.guolaiwan.bussiness.admin.dao.VoteOptionsDao;
import com.guolaiwan.bussiness.admin.dao.VotePicsDao;
import com.guolaiwan.bussiness.admin.dao.VoteProductDAO;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.VoteModularPO;
import com.guolaiwan.bussiness.admin.po.VoteOptionsPo;
import com.guolaiwan.bussiness.admin.po.VotePicsPo;
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
	@Autowired
	private VoteOptionsDao voteoptionsDAO;
	@Autowired
	private VotePicsDao votepicDAO;
	
	// 显示列表页面
	@ResponseBody
	@RequestMapping(value = "/list")
	public ModelAndView activityList(HttpServletRequest request) {
		String optionId=request.getParameter("optionId");
		ModelAndView mv = new ModelAndView("admin/vote/list");
		mv.addObject("optionId", optionId);
		return mv;
	}

	//查询所有标签
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(HttpServletRequest request,int page, int limit) throws Exception {
		String optionId=request.getParameter("optionId");
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = votemodularDAO.getCountByOptionId(Long.parseLong(optionId));
		List<VoteModularPO>  votemodulars= votemodularDAO.getByOptionId(Long.parseLong(optionId));
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", votemodulars);
		return strMap;
	}
	
	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/addv")
	public ModelAndView addv(HttpServletRequest request) {
		String optionId=request.getParameter("optionId");
		ModelAndView mv = new ModelAndView("admin/vote/add");
		mv.addObject("optionId", optionId);
		return mv;
	}
	
	// 添加数据页面
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String optionId = request.getParameter("optionId");
		String modularName = request.getParameter("modularName");
		String modularDescribe = request.getParameter("modularDescribe");
		VoteModularPO vote=new VoteModularPO();
		vote.setModularName(modularName);
		vote.setModularDescribe(modularDescribe);
		vote.setOptionId(Long.parseLong(optionId));
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
		voteproductDAO.deleteByMoId(id);
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
		System.out.println(moId);
		System.out.println(count);
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
	
	//查询所有的商品
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
	
	//模块添加商品
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
	
	//删除数据
	@ResponseBody
	@RequestMapping(value = "/delRel.do", method = RequestMethod.POST)
	public String bdProDo(long relId) {
		voteproductDAO.delete(relId);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/delAll.do", method = RequestMethod.POST)
	public String delAll(long moId) {
		voteproductDAO.deleteByMoId(moId);
		return "success";
	}
	
	// 添加数据页面
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/gotovoteproductdetails")
	public ModelAndView voteProductDetails(HttpServletRequest request) {
		long productId=Long.parseLong(request.getParameter("productId"));
		System.out.println(productId);
		ProductPO productPO = conn_product.get(productId);
		ModelAndView mv = new ModelAndView("mobile/vote/voteproductdetails");
		mv.addObject("product", productPO);
		System.out.println(productPO+"-------"+productPO.getProductIntroduce());
		return mv;
	}
	
	// 选择模块图片
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
	
	// 弹出添加产品页面
	@RequestMapping(value = "/options", method = RequestMethod.GET)
	public ModelAndView goToVoteOption(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/vote/voteoptions");
		return mv;
	}
	
	// 添加或者修改
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/addoption", method = RequestMethod.POST)
	public String addOption(HttpServletRequest request) {
		String judgesvote = request.getParameter("judgesvote");
		String ordervote = request.getParameter("ordervote");
		String pepolevote = request.getParameter("pepolevote");
		System.out.println(judgesvote+"--"+ordervote+"--"+pepolevote+"--");
		return "success";
	}
	
	// 选择logo图片
	@ResponseBody
	@RequestMapping(value = "/voteoptionpic", method = RequestMethod.POST)
	public String voteOptionPic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long picId = Long.parseLong(request.getParameter("picId"));
		long id = Long.parseLong(request.getParameter("id"));
		VoteOptionsPo voteOptionsPo = voteoptionsDAO.get(id);
		voteOptionsPo.setPicId(picId);
		voteOptionsPo.setSlidepic(pic);
		voteoptionsDAO.saveOrUpdate(voteOptionsPo);
		return "success";
	}
	
	//查询所有投票活动
	@ResponseBody
	@RequestMapping(value = "/alloptions", method = RequestMethod.POST)
	public Map<String, Object> allOptions(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = voteoptionsDAO.countAll();
		List<VoteOptionsPo>  voteOptionsPo= voteoptionsDAO.findAll();
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", voteOptionsPo);
		return strMap;
	}
	
	// 添加投票参数页面
	@RequestMapping(value = "/addoptions")
	public ModelAndView addOptions(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/vote/addoptions");
		return mv;
	}
	
	// 添加投票参数数据
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/insertoptions", method = RequestMethod.POST)
	public String insertOptions(HttpServletRequest request) throws Exception {
		String votename = request.getParameter("votename");
		String judgesvote = request.getParameter("judgesvote");
		String ordervote = request.getParameter("ordervote");
		String pepolevote = request.getParameter("pepolevote");
		
		VoteOptionsPo options=new VoteOptionsPo();
		options.setVotename(votename);
		options.setJudgesvote(Integer.parseInt(judgesvote));
		options.setOrdervote(Integer.parseInt(ordervote));
		options.setPepolevote(Integer.parseInt(pepolevote));
		voteoptionsDAO.save(options);
		return "success";
	}
	
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/deloption", method = RequestMethod.POST)
	public String delOption(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		List<VoteModularPO> modulars = votemodularDAO.getByOptionId(id);
		for (VoteModularPO voteModularPO : modulars) {
			voteproductDAO.deleteByMoId(voteModularPO.getId());
			votemodularDAO.delete(voteModularPO);
		}
		votepicDAO.deleteByOptionId(id);
		voteoptionsDAO.delete(id);
		return "success";
	}
	
	// 编辑数据
	@ResponseBody
	@RequestMapping(value = "/editoption", method = RequestMethod.POST)
	public String editOption(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		VoteOptionsPo voteOptions = voteoptionsDAO.get(id);
		String field = request.getParameter("field");
		if (field.equals("votename")) {
			String votename = request.getParameter("value");
			voteOptions.setVotename(votename);
			voteoptionsDAO.save(voteOptions);
			return "success";
		}else if(field.equals("judgesvote")){
			String judgesvote = request.getParameter("value");
			voteOptions.setJudgesvote(Integer.parseInt(judgesvote));
			voteoptionsDAO.save(voteOptions);
			return "success";
		}else if(field.equals("ordervote")){
			String ordervote = request.getParameter("value");
			voteOptions.setOrdervote(Integer.parseInt(ordervote));
			voteoptionsDAO.save(voteOptions);
			return "success";
		}else if(field.equals("pepolevote")){
			String pepolevote = request.getParameter("value");
			voteOptions.setPepolevote(Integer.parseInt(pepolevote));
			voteoptionsDAO.save(voteOptions);
			return "success";
		}
		return "error";
	}
	
	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/optionstatus", method = RequestMethod.POST)
	public String optionStatus(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String value = request.getParameter("value");
		System.out.println(value+"--------");
		VoteOptionsPo voteOptions = voteoptionsDAO.get(id);
		voteOptions.setVotestatustype(value);
		voteoptionsDAO.saveOrUpdate(voteOptions);
		return "success";
	}
	
	// 投票轮播图页面
	@RequestMapping(value = "/gotovotepics")
	public ModelAndView goToVotePics(HttpServletRequest request) {
		String optionId=request.getParameter("optionId");
		ModelAndView mv = new ModelAndView("admin/vote/votepics");
		mv.addObject("optionId", optionId);
		return mv;
	}
	
	// 编辑轮播图数据
	@ResponseBody
	@RequestMapping(value = "/editpic", method = RequestMethod.POST)
	public String editPic(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		VotePicsPo votePics = votepicDAO.get(id);
		String field = request.getParameter("field");
		if (field.equals("ranking")) {
			String ranking = request.getParameter("value");
			votePics.setRanking(Integer.parseInt(ranking));
			votepicDAO.save(votePics);
			return "success";
		}
		return "error";
	}
	
	
	
	//查询所有轮播图
	@ResponseBody
	@RequestMapping(value = "/picslist", method = RequestMethod.POST)
	public Map<String, Object> picsList(HttpServletRequest request,int page, int limit) throws Exception {
		long optionId = Long.parseLong(request.getParameter("optionId"));
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = votepicDAO.getCountByOptionId(optionId);
		List<VotePicsPo> VotePics = votepicDAO.getByOptionId(optionId);
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", VotePics);
		return strMap;
	}
	
	// 添加新的轮播图
	@ResponseBody
	@RequestMapping(value = "/apendpic", method = RequestMethod.POST)
	public String apendPic(HttpServletRequest request) throws Exception {
		long optionId=Long.parseLong(request.getParameter("optionId"));
		System.out.println(optionId+"------");
		VotePicsPo pic=new VotePicsPo();
		pic.setOptionId(optionId);
		votepicDAO.save(pic);
		return "success";
	}
	
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/delpic", method = RequestMethod.POST)
	public String delpic(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		votepicDAO.delete(id);
		return "success";
	}
	
	// 选择logo图片
	@ResponseBody
	@RequestMapping(value = "/votepics", method = RequestMethod.POST)
	public String votePics(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long picId = Long.parseLong(request.getParameter("picId"));
		long id = Long.parseLong(request.getParameter("id"));
		VotePicsPo votePicsPo = votepicDAO.get(id);
		votePicsPo.setPicId(picId);
		votePicsPo.setSlidepic(pic);
		votepicDAO.saveOrUpdate(votePicsPo);
		return "success";
	}
	
	//查询所有轮播图
	@ResponseBody
	@RequestMapping(value = "/allpics")
	public List<VotePicsPo> picsByOptionList(HttpServletRequest request) throws Exception {
		long optionId = Long.parseLong(request.getParameter("optionId"));
		List<VotePicsPo> VotePics = votepicDAO.getByOptionId(optionId);
		return VotePics;
	}
	
}
