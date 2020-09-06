package com.guolaiwan.app.gonghui.admin.controller;

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
import com.guolaiwan.app.web.admin.vo.VoiceVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.VoiceDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.VoicePO;
import com.guolaiwan.bussiness.gonghui.dao.PeopleVoteDao;
import com.guolaiwan.bussiness.gonghui.po.PeopleVotePo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/pvote")
public class PeopleVoteController extends BaseController {

	@Autowired
	private PeopleVoteDao conn_peoplevote;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	
	// 查询列表页面刘立强新增测试
	// 同时添加

	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView activityList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("gonghui/vote/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long comId = getLoginInfo().getComId();
		int count = conn_peoplevote.countAll();
		List<PeopleVotePo> peopleVotePos = conn_peoplevote.findAll();
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", peopleVotePos);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("gonghui/vote/add");
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		String org = request.getParameter("org");
		String articleFrom = request.getParameter("articleFrom");
		String voice = request.getParameter("voice");
		String article = request.getParameter("article");
	    PeopleVotePo peopleVotePo=new PeopleVotePo();
	    peopleVotePo.setArticle(article);
	    peopleVotePo.setArticleFrom(articleFrom);
	    peopleVotePo.setName(name);
	    peopleVotePo.setOrg(org);
	    //peopleVotePo.setVoice(voice);
		conn_peoplevote.save(peopleVotePo);
		return "success";
	}

	// 选择图片
	@ResponseBody
	@RequestMapping(value = "/pic.do", method = RequestMethod.POST)
	public String pic(HttpServletRequest request) {
		String pic = request.getParameter("pic");
		long id = Long.parseLong(request.getParameter("id"));
		PeopleVotePo peopleVotePo = conn_peoplevote.get(id);
		peopleVotePo.setHeaderimg(pic);
		conn_peoplevote.saveOrUpdate(peopleVotePo);
		return "success";
	}
	
	
	// 选择图片
	@ResponseBody
	@RequestMapping(value = "/voice.do", method = RequestMethod.POST)
	public String voice(HttpServletRequest request) {
		String voice = request.getParameter("voice");
		long id = Long.parseLong(request.getParameter("id"));
		PeopleVotePo peopleVotePo = conn_peoplevote.get(id);
		peopleVotePo.setVoice(voice);
		conn_peoplevote.saveOrUpdate(peopleVotePo);
		return "success";
	}

	
	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
		conn_peoplevote.delete(id);
		return "success";
	}


	// 编辑数据
	@ResponseBody
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String edit(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		PeopleVotePo peopleVotePo = conn_peoplevote.get(id);

		String field = request.getParameter("field");
		if (field.equals("name")) {
			peopleVotePo.setName(request.getParameter("value"));
		}
		if (field.equals("articleFrom")) {
			peopleVotePo.setArticleFrom(request.getParameter("value"));
		}else if(field.equals("article")){
			peopleVotePo.setArticle(request.getParameter("value"));
		}else if(field.equals("org")){
			peopleVotePo.setOrg(request.getParameter("value"));
		}

		conn_peoplevote.save(peopleVotePo);
		return "success";

	}

	
	@RequestMapping(value = "/selectvoice", method = RequestMethod.GET)
	public ModelAndView selectvoice(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("sysConfig", sysConfig);
		strMap.put("id", request.getParameter("id"));
		ModelAndView mv = new ModelAndView("gonghui/vote/selectlist", strMap);
		return mv;
	}
	
	@Autowired
	VoiceDAO conn_voice;
	
	@ResponseBody
	@RequestMapping(value="/voiceList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int pagecurr,int ilimit) throws Exception {
		List<VoicePO> listpo = conn_voice.getVoiceByPage(pagecurr,ilimit);
		List<VoiceVO> listvo = VoiceVO.getConverter(VoiceVO.class).convert(listpo, VoiceVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}

}
