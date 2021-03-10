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
import com.guolaiwan.bussiness.gonghui.dao.VideoDao;
import com.guolaiwan.bussiness.gonghui.po.VideoPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping(value = ("/admin/gonghui/video"))
public class GonghuiVideoController extends BaseController {

	@Autowired
	private VideoDao conn_Video;

	
	
	// 跳转到分销商列表页
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		// 公司下的商品的数量
		int count = conn_Video.countAll();
		// 获取所有模块和子模块
		List<VideoPo> videos = conn_Video.findAll();
		strMap.put("count", count);
		strMap.put("videos", videos);
		ModelAndView mv = new ModelAndView("admin/gonghui/video/list", strMap);
		return mv;
	}

	// 查询分销商
	@ResponseBody
	@RequestMapping(value = "/videoList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getVideoList(int page, int limit) throws Exception {
		int count = conn_Video.countAll();
		List<VideoPo> listpo = conn_Video.findByPage( page, limit);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listpo);
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
		VideoPo videoPo = conn_Video.get(Long.parseLong(id));

		
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		
		strMap.put("video", videoPo);
		strMap.put("webPath", sysConfigPO.getWebUrl());
		ModelAndView mv = new ModelAndView("admin/gonghui/video/check");
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
			VideoPo videoPo=conn_Video.get(Long.parseLong(id));
			if(result==1){
				videoPo.setPassedStr("审核通过");
			}else{
				videoPo.setPassedStr("审核拒绝");
			}
			videoPo.setSend(0);
			videoPo.setPassed(result);
			conn_Video.save(videoPo);
			return "success";
		}

}