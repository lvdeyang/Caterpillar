package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.guolaiwan.app.web.admin.vo.MerModularVO;
import com.guolaiwan.bussiness.admin.dao.MerModularDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/merModular")
public class MerModularController extends BaseController{

	@Autowired
	private MerModularDAO conn_merModular;

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private ProductDAO conn_product;

	//添加商家分类
	@RequestMapping(value="/comSel",method= RequestMethod.GET)
	public ModelAndView getComSel(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("admin/merModular/comSel");
		//商家UUId为空返回null
		
		if(request.getParameter("merchant")==null||request.getParameter("merchant").length()==0){
			strMap.put("list","<center>没有商家信息！</center>");
			mv.addAllObjects(strMap);
			return mv;
		}
		long meruuid = Long.parseLong(request.getParameter("merchant"));
		//没有分类返回null
		MerchantPO merchantPO = conn_merchant.get(meruuid);
		if(merchantPO==null){
			strMap.put("list","<center>没有此商家！</center>");
			mv.addAllObjects(strMap);
			return mv;
		}
		List<MerModularPO> merModulars = merchantPO.getMerModulars();
		if(merModulars==null||merModulars.size()<=0){
			strMap.put("list","<center>该商家没有分类，请添加分类！</center>");
			mv.addAllObjects(strMap);
			return mv;
		}

		//将list转化成以等级的map
		HashMap<Long, List<MerModularPO>> merMap = queryList(merModulars);

		StringBuilder sbHtml = mapToString(merMap);
		strMap.put("list", sbHtml.toString());
		mv.addAllObjects(strMap);
		return mv;
	}


	//绑定商家分类
	@RequestMapping(value="/bdMerM",method= RequestMethod.GET)
	public ModelAndView bdMerM(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("admin/merModular/bdMerM");
		//商家UUId为空返回null
		String meruuid = request.getParameter("merchant");
		String produuid = request.getParameter("product");
		if(meruuid==null||meruuid.length()==0||produuid==null||produuid.length()==0){
			strMap.put("list","<center>获取商家分类出错！</center>");
			mv.addAllObjects(strMap);
			return mv;
		}
		MerchantPO merchantPO = conn_merchant.get(meruuid);
		List<MerModularPO> merModulars = merchantPO.getMerModulars();
		if(merModulars==null||merModulars.size()<=0){
			strMap.put("list","<center>该商家没有分类，请先添加分类！</center>");
			mv.addAllObjects(strMap);
			return mv;
		}

		//将list转化成以等级的map
		HashMap<Long, List<MerModularPO>> merMap = queryList(merModulars);
		StringBuilder sbHtml = mapToString(merMap);
		strMap.put("product", produuid);
		strMap.put("list", sbHtml.toString());
		mv.addAllObjects(strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value="/bdMerM.do",method= RequestMethod.POST)
	public String bdMerMDo(HttpServletRequest request){
		String merMName = request.getParameter("merMName");
		String merMId = request.getParameter("merMId");
		String produuid = request.getParameter("product");
		ProductPO product = conn_product.get(produuid);
		if(merMName==null||merMName.length()==0||merMId==null||merMId.length()==0||product==null){
			return "error";
		}
		long merMIdl = Long.parseLong(merMId);

		product.setMerMId(merMIdl);
		product.setMerMName(merMName);
		conn_product.saveOrUpdate(product);
		return "success";
	}

	// 添加商品分类页面
	@RequestMapping(value = "/addClass", method = RequestMethod.GET)
	public ModelAndView addClass(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);
		List<MerModularPO> merModulars = merchant.getMerModulars();
		int has =1;
		if(merModulars.size()==0||merModulars==null){
			has =0;
		}
		strMap.put("merchant", merchant);
		strMap.put("merModulars", merModulars);
		strMap.put("has", has);

		ModelAndView mv =new ModelAndView("admin/merchant/addClass",strMap);
		return mv;
	}


	//绑定、添加商品分类
	@ResponseBody
	@RequestMapping(value = "/addClass.do", method = RequestMethod.POST)
	public String addClassD(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long pId = Integer.parseInt(request.getParameter("pId"));
		String merchantUUID = request.getParameter("merchant");
		String mmName = request.getParameter("mmName");
		MerchantPO merchant = conn_merchant.get(merchantUUID);

		String[] fields = {"name","merchant","parentId"};
		Object[] values = {mmName,merchant,pId};
		List<MerModularPO> merms= conn_merModular.findByFields(fields, values);
		if(!(merms==null||merms.size()==0)){
			return "has";
		}

		MerModularPO mmp = new MerModularPO();
		mmp.setMerchant(merchant);
		mmp.setIsv(1);
		mmp.setName(mmName);
		mmp.setUpdateTime(new Date());
		if(pId==0){
			mmp.setLevel(1);
			mmp.setParentId(0);
			mmp.setParentName(merchant.getShopName());
		}else {
			MerModularPO merModularPO = conn_merModular.get(pId);
			mmp.setParentId(merModularPO.getId());
			mmp.setLevel(merModularPO.getLevel()+1);
			mmp.setParentName(merModularPO.getName());
		}
		conn_merModular.saveOrUpdate(mmp);
		return "success";
	}

	//商品分类列表
	@ResponseBody
	@RequestMapping(value = "/merMList.do", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public String merMList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取参数
		String merchantUUID = request.getParameter("merchant");
		//获取商家下的分类集合
		MerchantPO merchant = conn_merchant.get(merchantUUID);
		List<MerModularPO> merModulars = merchant.getMerModulars();
		List<MerModularVO> merModularVOs = MerModularVO.getConverter(MerModularVO.class).convert(merModulars, MerModularVO.class);
		//获取商家下的分类集合数量
		int count = merModularVOs.size();
		//list集合转成json集合		
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(merModularVOs));
		String jsonString = jsonArray.toString();

		//layui返回的特殊格式
		String backStr = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+jsonString+"}";
		return backStr;
	}

	//编辑商品分类
	@ResponseBody
	@RequestMapping(value = "/editMerM.do", method = RequestMethod.POST)
	public String editMerM(HttpServletRequest request) throws Exception {

		//获取参数
		String merMId = request.getParameter("merMId");//delMerM.do
		String merMValue = request.getParameter("merMValue");

		//1.商品分类表
		long merMIdl = Long.parseLong(merMId);
		MerModularPO merM = conn_merModular.get(merMIdl);
		if(merM==null){
			return "500";
		}
		merM.setName(merMValue);

		List<MerModularPO> merModulars = conn_merModular.findByField("parentId", merMIdl);
		for (MerModularPO merModularPO : merModulars) {
			merModularPO.setParentName(merMValue);
			conn_merModular.saveOrUpdate(merModularPO);
		}
		conn_merModular.saveOrUpdate(merM);

		//2.商品表
		List<ProductPO> products = conn_product.findByField("merMId", merMIdl);
		for (ProductPO productPO : products) {
			productPO.setMerMId(merMIdl);
			productPO.setMerMName(merMValue);
			conn_product.saveOrUpdate(productPO);
		}
		return "success";
	}


	//删除商品分类
	@ResponseBody
	@RequestMapping(value = "/delMerM.do", method = RequestMethod.POST)
	public String delMerM(HttpServletRequest request) throws Exception {
		//获取参数
		String merMId = request.getParameter("merMId");//delMerM.do

		//1.商品分类表
		long merMIdl = Long.parseLong(merMId);
		List<MerModularPO> merModulars = conn_merModular.findByField("parentId", merMIdl);
		if(!(merModulars==null||merModulars.size()==0)){  //不能删父类
			return "father";
		}
		List<ProductPO> products = conn_product.findByField("merMId",merMIdl);
		for (ProductPO productPO : products) {
			productPO.setMerMId(0);
			productPO.setMerMName(null);
			conn_product.saveOrUpdate(productPO);
		}

		conn_merModular.delete(merMIdl);
		return "success";
	}

	//删除商品分类
	@ResponseBody
	@RequestMapping(value = "/jbfl.do", method = RequestMethod.POST)
	public String jbfl(HttpServletRequest request) throws Exception {
		//获取参数
		String productuuid = request.getParameter("product");//delMerM.do

		//1.商品分类表
		ProductPO product = conn_product.get(productuuid);
		if(product==null){
			return "null";
		}
		product.setMerMId(0l);
		product.setMerMName(null);
		conn_product.saveOrUpdate(product);
		return "success";
	}














	public StringBuilder mapToString(HashMap<Long, List<MerModularPO>> merMap){
		StringBuilder sbHtml=new StringBuilder();
		List<MerModularPO> mMList0 = merMap.get(0l);
		for (MerModularPO merModularPO : mMList0) {
			sbHtml.append("<div class=\"layui-collapse\" lay-accordion=\"\">");
			sbHtml.append("<div class=\"layui-colla-item xuan\" merMId='"+merModularPO.getId()+"' merMName='"+merModularPO.getName()+"'>");

			sbHtml.append("<h2 class=\"layui-colla-title\">"+merModularPO.getName()+"</h2>");
			sbHtml.append("<div class=\"layui-colla-content  layui-show\">");

			List<MerModularPO> mMListc = merMap.get(merModularPO.getId());
			sbHtml = dgString(sbHtml,merModularPO.getId(),merMap);
			sbHtml.append("</div>");
			sbHtml.append("</div>");
			sbHtml.append(" </div>");
		}
		return sbHtml;

	}


	//将list转化成map
	public HashMap<Long, List<MerModularPO>> queryList(List<MerModularPO> list) {
		HashMap<Long, List<MerModularPO>> map = new HashMap<Long,List<MerModularPO>>();

		for (MerModularPO li : list) {
			Long parentId =  li.getParentId();
			if (map.containsKey(parentId)) {
				ArrayList<MerModularPO> templist = (ArrayList<MerModularPO>) map.get(parentId);
				templist.add(li);
			} else {
				ArrayList<MerModularPO> temlist = new ArrayList<MerModularPO>();
				temlist.add(li);
				map.put(parentId, temlist);
			}
		}
		return map;
	}

	//递归函数
	public StringBuilder dgString(StringBuilder sbHtml,Long id,HashMap<Long, List<MerModularPO>> merMap) {
		List<MerModularPO> mMListc = merMap.get(id);
		if(mMListc==null||mMListc.size()<=0){
			return sbHtml;
		}else{
			for (MerModularPO merModularPO : mMListc) {
				sbHtml.append("<div class=\"layui-collapse\" lay-accordion=\"\">");
				sbHtml.append("<div class=\"layui-colla-item xuan\" merMId='"+merModularPO.getId()+"' merMName='"+merModularPO.getName()+"'>");

				//						+"' data-mname='"+modularPO.getModularName()+"' data-ccode='"+classPO.getClassCode()+"' data-cname='"+classPO.getClassName()+"'>");
				sbHtml.append("<h2 class=\"layui-colla-title\">"+merModularPO.getName()+"</h2>");
				sbHtml.append("<div class=\"layui-colla-content  layui-show\">");
				sbHtml = dgString(sbHtml,merModularPO.getId(),merMap);
				sbHtml.append("</div>");
				sbHtml.append("</div>");
				sbHtml.append("</div>");
			}
			return sbHtml;
		}
	}

}
