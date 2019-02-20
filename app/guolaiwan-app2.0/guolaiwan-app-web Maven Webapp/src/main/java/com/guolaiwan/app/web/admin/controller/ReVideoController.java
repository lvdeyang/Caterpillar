package com.guolaiwan.app.web.admin.controller;

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

import com.guolaiwan.app.web.admin.vo.ReVideoVO;
import com.guolaiwan.bussiness.admin.dao.CompanyRelDAO;
import com.guolaiwan.bussiness.admin.dao.ReVideoDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.ReVideoPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/reVideo")
public class ReVideoController extends BaseController{
	@Autowired
	private ReVideoDAO conn_reVideo;

	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private CompanyRelDAO conn_companyRel;
	
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getreVideo(HttpServletRequest request){
		long comId = getLoginInfo().getComId();
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfigPO = conn_sysConfig.getSysConfig();
		strMap.put("sysConfigPO", sysConfigPO);
		ModelAndView mv =null;
		if(comId==1l){
			mv = new ModelAndView("admin/reVideo/list",strMap);
			return mv;	
		}
		mv = new ModelAndView("admin/reVideo/sonList",strMap);
		return mv;
	}


	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="list.do",method = RequestMethod.POST)
	public Map<String,Object> GetList(int page,int limit) throws Exception{
		long comId = getLoginInfo().getComId();
		List<ReVideoPO> ReVideoPOs = conn_reVideo.findByCom(comId, page, limit);
		int count = conn_reVideo.countByCom(comId);
		List<ReVideoVO> reVideovos = ReVideoVO.getConverter(ReVideoVO.class).convert(ReVideoPOs, ReVideoVO.class);
		
		for (ReVideoVO ReVideoVO : reVideovos) {
			ReVideoVO.setReCount(conn_companyRel.countByRelIdS(ReVideoVO.getId(),"RADIO"));
		}
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", reVideovos);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count",count );
		return map;			
	}
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		ReVideoPO reVideo = new ReVideoPO();
		reVideo.setUpdateTime(new Date());
		reVideo.setComId(getLoginInfo().getComId());
		reVideo.setComName(getLoginInfo().getComName());
		conn_reVideo.save(reVideo);
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id =Long.parseLong(request.getParameter("id"));
		conn_reVideo.delete(id);
		conn_companyRel.deleteByRelId(id, "RADIO");
		return "success";
	}

	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String field = request.getParameter("field");
		String value = request.getParameter("value");


		ReVideoPO reVideo = conn_reVideo.get(id);

		if(field.equals("sort")){
			reVideo.setSort(Integer.parseInt(value));
		}else{
			//字符串首字母转成大写
			char[] cs = field.toCharArray();
			cs[0]-=32;
			String.valueOf(cs);
			//反射
			Class<ReVideoPO> carClass = ReVideoPO.class;

			carClass.getDeclaredMethod("set"+String.valueOf(cs), String.class).invoke(reVideo, value);
			//方法名，输入参数的类型，对象，输入参数的值
		}
		conn_reVideo.save(reVideo);

		return "success";
	}

	//是否显示
	@ResponseBody
	@RequestMapping(value="/changeIsv.do",method= RequestMethod.POST)
	public String changeIsv(HttpServletRequest request) {
		long id = Long.parseLong(request.getParameter("id"));
		int value = Integer.parseInt(request.getParameter("value"));
		ReVideoPO reVideo = conn_reVideo.get(id);
		reVideo.setEnable(value);
		conn_reVideo.saveOrUpdate(reVideo);
		return "success";
	}

	
}