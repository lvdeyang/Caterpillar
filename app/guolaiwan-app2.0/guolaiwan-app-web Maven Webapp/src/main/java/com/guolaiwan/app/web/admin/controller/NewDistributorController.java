package com.guolaiwan.app.web.admin.controller;

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
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.distribute.classify.DistributorApplyStatus;
import com.guolaiwan.bussiness.distribute.dao.DistributorDao;
import com.guolaiwan.bussiness.distribute.dao.RegionDao;
import com.guolaiwan.bussiness.distribute.po.DistributorPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping(value = ("/admin/newDistributor"))
public class NewDistributorController extends BaseController {

	@Autowired
	private DistributorDao conn_distributorDao;

	@Autowired
	private ModularDAO conn_modular;

	@Autowired
	private ModularClassDAO conn_modularClass;
	
	// 跳转到分销商列表页
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		// 公司下的商品的数量
		int count = conn_distributorDao.countByCom(0l);
		// 获取所有模块和子模块
		List<ModularPO> modulars = conn_modular.findAll();
		List<ModularClassPO> Classes = conn_modularClass.findAll();
		strMap.put("count", count);
		strMap.put("modulars", modulars);
		strMap.put("Classes", Classes);
		ModelAndView mv = new ModelAndView("admin/newDistributor/list", strMap);
		return mv;
	}

	// 查询分销商
	@ResponseBody
	@RequestMapping(value = "/protorList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getProList(int page, int limit) throws Exception {
		long comId = getLoginInfo().getComId();
		int count = conn_distributorDao.countByCom(0l);
		List<DistributorPo> listpo = conn_distributorDao.findByCom(0l, page, limit);
		List<DistributorVo> listvo = DistributorVo.getConverter(DistributorVo.class).convert(listpo,
				DistributorVo.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	// 跳转到分销商审核页面
	@Autowired
	SysConfigDAO conn_sys;
	@ResponseBody
	@RequestMapping(value = "/toCheck.do", method = RequestMethod.GET)
	public ModelAndView newcheck(HttpServletRequest request) throws Exception {

		Map<String, Object> strMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		DistributorPo distributorPo = conn_distributorDao.get(Long.parseLong(id));

		DistributorVo vo = new DistributorVo();
		vo.set(distributorPo);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		vo.setContractPicUrl(sysConfigPO.getWebUrl()+vo.getContractPicUrl());
		vo.setContractUrl(sysConfigPO.getWebUrl()+vo.getContractUrl());
		strMap.put("distributor", vo);

		ModelAndView mv = new ModelAndView("admin/newDistributor/check");
		mv.addAllObjects(strMap);

		return mv;
	}
	@Autowired
	DistributorDao conn_distributor;
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