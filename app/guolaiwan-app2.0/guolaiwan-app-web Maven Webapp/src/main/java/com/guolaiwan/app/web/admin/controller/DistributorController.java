package com.guolaiwan.app.web.admin.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

import com.guolaiwan.app.web.distribute.vo.DistributorVo;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.distribute.classify.DistributorApplyStatus;
import com.guolaiwan.bussiness.distribute.dao.DistributorDao;
import com.guolaiwan.bussiness.distribute.dao.RegionDao;
import com.guolaiwan.bussiness.distribute.po.DistributorPo;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.controller.BaseController;

/**
 * 分销商Controller
 * @author 一只会写bug的井蛙
 *
 */
@Controller
@RequestMapping("/admin/distributor")
public class DistributorController extends BaseController {

	@Autowired
	private DistributorDao conn_distributor;
	// 商户列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String[] names = {};
		Object[] values = {};
		int allcount = conn_distributor.getCountByPageA(names,values);
		strMap.put("allcount", allcount);
		strMap.put("allpages", GetAllPages(allcount,10));
		ModelAndView mv = new ModelAndView("admin/distributor/list", strMap);
		return mv;
	}
    @Autowired
    private RegionDao conn_region;
	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page,int limit) throws Exception {
		String[] names = {};
		Object[] values = {};
		List<DistributorPo> listpo = conn_distributor.getListByPageA(names, values, page, limit);
		int allcount = conn_distributor.getCountByPageA(names,values);
		List<DistributorVo> listvo = new ArrayList<DistributorVo>();
		for (DistributorPo po : listpo) {
			DistributorVo vo=new DistributorVo();
			vo.set(po);
			vo.setRegionName(conn_region.get(po.getRegion()).getName());
			listvo.add(vo);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", allcount);
		return map;
	}
	
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ModelAndView checkView(HttpServletRequest request) throws Exception {

		Map<String, Object> strMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		DistributorPo distributorPo=conn_distributor.get(Long.parseLong(id));
		

		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DistributorVo vo=new DistributorVo();
		vo.set(distributorPo);
		
		vo.setContractPicUrl(sysConfig.getWebUrl()+vo.getContractPicUrl());
		vo.setContractUrl(sysConfig.getWebUrl()+vo.getContractUrl());
		strMap.put("distributor", vo);
	
		ModelAndView mv = new ModelAndView("admin/distributor/check");
		mv.addAllObjects(strMap);

		return mv;
	}

	// 审核结论
	@ResponseBody
	@RequestMapping(value = "/checkResult.do", method = RequestMethod.POST)
	public String checkResult(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		int result=Integer.parseInt(request.getParameter("result"));
		String reason=request.getParameter("reason");
		DistributorPo distributorPo=conn_distributor.get(Long.parseLong(id));
		distributorPo.setCheckReason(reason);
		if(result==0){
			distributorPo.setStatus(DistributorApplyStatus.NOTPASSED);
		}else{
			distributorPo.setStatus(DistributorApplyStatus.PASSED);
		}
		conn_distributor.save(distributorPo);
		return "success";
	}
	
}
