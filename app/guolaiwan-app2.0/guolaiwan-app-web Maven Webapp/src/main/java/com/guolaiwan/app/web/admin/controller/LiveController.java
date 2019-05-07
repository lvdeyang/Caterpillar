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

import com.guolaiwan.app.web.admin.vo.LiveAdvertisementVO;
import com.guolaiwan.bussiness.admin.dao.LiveAdvertisementDAO;
import com.guolaiwan.bussiness.admin.dao.LiveDAO;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.po.LiveAdvertisementPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LivePO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/live")
public class LiveController extends BaseController {
	
	@Autowired
	private LiveAdvertisementDAO conn_liveAdvertisement;
	@Autowired
	private LiveDAO conn_live;
	@Autowired
	private LiveMessageDAO conn_liveMessage;

	// 列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();

		ModelAndView mv = new ModelAndView("admin/live/list", strMap);
		return mv;
	}
	/*
	 * //直播评论删除页面
	 * 
	 * @RequestMapping(value="/delect.do",method= RequestMethod.GET) public
	 * ModelAndView delect(HttpServletRequest request) throws Exception{
	 * Map<String, Object> strMap = new HashMap<String, Object>(); ModelAndView
	 * mv = new ModelAndView("admin/live/liveDelect", strMap); return mv; }
	 */

	// 获取直播信息列表
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<LivePO> lives = conn_live.findAll(page, limit);
		int count = conn_live.countAll();
		strMap.put("data", lives);
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("code", "0");
		return strMap;
	}

	// 获取评论信息列表
	@ResponseBody
	@RequestMapping(value = "/message.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getMessage(HttpServletRequest request) throws Exception {
		long liveId = Long.parseLong(request.getParameter("liveId"));
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<LiveMessagePO> lives = conn_liveMessage.findByLive(liveId);
		int count = conn_liveMessage.countAll();
		strMap.put("data", lives);
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("code", "0");
		return strMap;
	}

	// 修改页面弹出窗口
	@RequestMapping(value = "/liveDelect", method = RequestMethod.GET)
	public ModelAndView liveDelect(HttpServletRequest request) {
		String liveId = request.getParameter("liveId");
		ModelAndView mv = new ModelAndView("admin/live/liveDelect");
		request.setAttribute("liveId", liveId);
		return mv;
	}

	// 删除评论
	@ResponseBody
	@RequestMapping(value = "/delMessage.do", method = RequestMethod.POST)
	public String delMessage(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("Id"));
		conn_liveMessage.delectById(id);
		return "success";
	}

	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		LivePO live = new LivePO();
		live.setUpdateTime(new Date());
		conn_live.save(live);
		return "success";
	}

	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String edit(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String value = request.getParameter("value");

		LivePO live = conn_live.get(id);

		live.setLiveStatusType(LiveStatusType.fromString(value));

		conn_live.save(live);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/edit1.do", method = RequestMethod.POST)
	public String edit1(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String value = request.getParameter("value");
		String field = request.getParameter("field");

		LivePO live = conn_live.get(id);
		if (field.equals("liveName")) {
			live.setLiveName(value);
		} else if (field.equals("leshiyunId")) {
			live.setLeshiyunId(value);
		} else {
			live.setLiveStatusType(LiveStatusType.fromString(value));
		}

		conn_live.save(live);
		return "success";
	}
	
	
		//直播广告显示列表  张羽 5/6 新增
		@RequestMapping(value="/advertisementlist",method= RequestMethod.GET)
		public ModelAndView getAdvertisement(HttpServletRequest request){
			ModelAndView mv =null;
				mv = new ModelAndView("admin/live/advertisementList");
			return mv;
		}
		
		//异步读取列表分页
		@ResponseBody
		@RequestMapping(value="advertisementList.do",method = RequestMethod.POST)
		public Map<String,Object> GetList(int page,int limit) throws Exception{
			List<LiveAdvertisementPO> advertisementpo = conn_liveAdvertisement.GetListbyPage(page, limit);
			int count = conn_liveAdvertisement.countAll();
			List<LiveAdvertisementVO> LiveAdvertisementvo = LiveAdvertisementVO.getConverter(LiveAdvertisementVO.class).convert(advertisementpo, LiveAdvertisementVO.class);
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("data", LiveAdvertisementvo);
			map.put("code", "0");
			map.put("msg", "");
			map.put("count",count );
			return map;			
		}
		
		//添加数据
		@ResponseBody
		@RequestMapping(value="/addAdvertisement.do", method= RequestMethod.POST)
		public String addAdvertisement(HttpServletRequest request) throws Exception {
			LiveAdvertisementPO Advertisements = new LiveAdvertisementPO();
			Advertisements.setUpdateTime(new Date());
			conn_liveAdvertisement.save(Advertisements);
			return "success";
		}
		
		//修改数据
		@ResponseBody
		@RequestMapping(value="/updateAdvertisement.do", method= RequestMethod.POST)
		public String update(HttpServletRequest request) throws Exception {
			long id = Long.parseLong(request.getParameter("id"));
			String field = request.getParameter("field");
			String value = request.getParameter("value");


			LiveAdvertisementPO Advertisement = conn_liveAdvertisement.get(id);

			if(field.equals("sort")){
				Advertisement.setSort(Integer.parseInt(value));
			}else{
				//字符串首字母转成大写
				char[] cs = field.toCharArray();
				cs[0]-=32;
				String.valueOf(cs);
				//反射
				Class<LiveAdvertisementPO> carClass = LiveAdvertisementPO.class;

				carClass.getDeclaredMethod("set"+String.valueOf(cs), String.class).invoke(Advertisement, value);
				//方法名，输入参数的类型，对象，输入参数的值
			}
			conn_liveAdvertisement.save(Advertisement);

			return "success";
		}
		
		
		//删除数据
		@ResponseBody
		@RequestMapping(value="/delAdvertisement.do", method= RequestMethod.POST)
		public String del(HttpServletRequest request) throws Exception {
			long id =Long.parseLong(request.getParameter("id"));
			conn_liveAdvertisement.delete(id);
			return "success";
		}
		
		//选择图片
		@ResponseBody
		@RequestMapping(value="/pic.do",method= RequestMethod.POST)
		public String pic(HttpServletRequest request) {
			String pic = request.getParameter("pic");
			long picId = Long.parseLong(request.getParameter("picId"));
			long id = Long.parseLong(request.getParameter("id"));
			LiveAdvertisementPO advertisement = conn_liveAdvertisement.get(id);
			advertisement.setPicId(picId);
			advertisement.setSlidepic(pic);
			conn_liveAdvertisement.saveOrUpdate(advertisement);
			return "success";
		}
		
		
		
		//是否显示
		@ResponseBody
		@RequestMapping(value="/changeIsv.do",method= RequestMethod.POST)
		public String changeIsv(HttpServletRequest request) throws Exception {
			long comId = getLoginInfo().getComId();
			long id = Long.parseLong(request.getParameter("id"));
			int value = Integer.parseInt(request.getParameter("value"));
			LiveAdvertisementPO advertisement = conn_liveAdvertisement.get(id);
			advertisement.setEnable(value);
			conn_liveAdvertisement.saveOrUpdate(advertisement);
			return "success";
		}
}
			
	

