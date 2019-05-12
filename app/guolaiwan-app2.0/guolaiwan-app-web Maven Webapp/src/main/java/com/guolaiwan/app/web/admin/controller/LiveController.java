package com.guolaiwan.app.web.admin.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.LiveAdvertisementVO;
import com.guolaiwan.app.web.admin.vo.LiveRebroadcastVO;
import com.guolaiwan.app.web.admin.vo.PictureVO;
import com.guolaiwan.bussiness.admin.dao.LiveAdvertisementDAO;
import com.guolaiwan.bussiness.admin.dao.LiveDAO;
import com.guolaiwan.bussiness.admin.dao.LiveGiftDAO;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.LiveRebroadcastDAO;
import com.guolaiwan.bussiness.admin.dao.LiveTipGiftDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.po.ActiveBundlePo;
import com.guolaiwan.bussiness.admin.po.LiveAdvertisementPO;
import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.LiveRebroadcastPO;
import com.guolaiwan.bussiness.admin.po.LiveTipGiftPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

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
	@Autowired
	private LiveRebroadcastDAO conn_liveRebroadcast;
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private LiveGiftDAO conn_liveGift;
	@Autowired
	private LiveTipGiftDAO conn_liveTipGift;
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
		
		//异步读取列表分页  张羽 5/6 新增
		@ResponseBody
		@RequestMapping(value="advertisementList.do",method = RequestMethod.POST)
		public Map<String,Object> GetadvertisementList(int page,int limit) throws Exception{
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
		
		//添加数据   张羽 5/6 新增
		@ResponseBody
		@RequestMapping(value="/addAdvertisement.do", method= RequestMethod.POST)
		public String addAdvertisement(HttpServletRequest request) throws Exception {
			LiveAdvertisementPO Advertisements = new LiveAdvertisementPO();
			Advertisements.setUpdateTime(new Date());
			conn_liveAdvertisement.save(Advertisements);
			return "success";
		}
		
		//修改数据    张羽 5/6 新增
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
		
		
		//删除数据   张羽 5/6 新增
		@ResponseBody
		@RequestMapping(value="/delAdvertisement.do", method= RequestMethod.POST)
		public String del(HttpServletRequest request) throws Exception {
			long id =Long.parseLong(request.getParameter("id"));
			conn_liveAdvertisement.delete(id);
			return "success";
		}
		
		//选择图片   张羽 5/6 新增
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
		
		
		
		//是否显示    张羽 5/6 新增
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
		
		

		
		//同时添加  直播回放5/7 张羽 新增
		@RequestMapping(value="/rebroadcastlist",method= RequestMethod.GET)
		public ModelAndView rebroadcastList(HttpServletRequest request){
			Map<String, Object> strMap=new HashMap<String, Object>();
			int count = conn_liveRebroadcast.getCountByPage();
			strMap.put("count",count);
			ModelAndView mv = new ModelAndView("admin/live/rebroadcastList",strMap);
			return mv;
		}

		// 直播回放上传 5/7 张羽 新增
		@ResponseBody
		@RequestMapping(value="/upload.do",method= RequestMethod.POST)
		public Map<String,Object> uploadRebroadcast(@RequestParam(value = "images") CommonsMultipartFile file) throws Exception{
			Map<String, Object> map= new HashMap<String, Object>();
			//创建日期文件夹
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			String folderName = sdf.format(d);  //文件名
			String path=conn_sysConfig.getSysConfig().getFolderUrl()+folderName;

			//文件名
			String fileName = file.getOriginalFilename();  
			String newName = d.getTime()+fileName.substring(fileName.lastIndexOf(".") ); //时间戳+后缀名

			File folder =new File(path);
			if(folder.exists() ==false){     //如果路径不存在
				if(folder.getParentFile().exists()==false){
					map.put("code", "1");
					map.put("error", "文件路径错误！");
					return map;
				}
				folder.mkdir();
			}
			//上传
			File newFile=new File(path+"/"+newName);
			String config = conn_sysConfig.getSysConfig().getWebUrl()+folderName+"/"+newName;
			file.transferTo(newFile);           //写

			//写数据库
			LiveRebroadcastPO rebroadcast = new LiveRebroadcastPO();


			if(file.getSize()/1024>1024l){
				rebroadcast.setFileSize(new DecimalFormat("###.##").format((double)file.getSize()/1024/1024)+"M"); 
			}else if(file.getSize()/1024/1024>1024l){
				rebroadcast.setFileSize(new DecimalFormat("###.##").format((double)file.getSize()/1024/1024/1024)+"G");
			}else{
				rebroadcast.setFileSize(file.getSize()/1024+"kb") ;
			}
			rebroadcast.setFolde(folderName);
			rebroadcast.setUpdateTime(d);
			rebroadcast.setOldName(fileName);
			rebroadcast.setNewName(newName);
			conn_liveRebroadcast.save(rebroadcast);
			rebroadcast.setWebUrl(config);
			rebroadcast.setIntroduce("上传成功！");
			rebroadcast.setIf_valid(1);
			LiveRebroadcastVO pic = new LiveRebroadcastVO().set(rebroadcast);
			map.put("pic", pic);
			map.put("path", config);
			map.put("code", "0");
			return map;
		}

		// 后台回放获取  张羽 5/7 
 		@ResponseBody
		@RequestMapping(value="/rebroadcastList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
		public Map<String, Object> getRebroadcastList(int pagecurr,int ilimit) throws Exception {
			List<LiveRebroadcastPO> listpo = conn_liveRebroadcast.getRebroadcastByPage(pagecurr,ilimit);
			List<LiveRebroadcastVO> listvo = LiveRebroadcastVO.getConverter(LiveRebroadcastVO.class).convert(listpo, LiveRebroadcastVO.class);
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("list", listvo);
			return map;
		}


		// 回放删除  张羽 5/7
		@ResponseBody
		@RequestMapping(value="rebroadcastdel.do", method= RequestMethod.POST)
		public String rebroadcastDel(HttpServletRequest request) throws Exception {
			String uuid = request.getParameter("uuid");
			conn_liveRebroadcast.deleteByUuid(uuid);
			return "success";
		}

		
		// 礼物页面  张羽  5/8 新增
		@RequestMapping(value = "/giftlist", method = RequestMethod.GET)
		public ModelAndView giftList(HttpServletRequest request) {
			Map<String, Object> strMap = new HashMap<String, Object>();
			SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
			strMap.put("sysConfig", sysConfig);
			ModelAndView mv = new ModelAndView("admin/live/giftList", strMap);
			return mv;
		}
		//礼物获取列表 张羽 5/8新增
		@ResponseBody
		@RequestMapping(value = "/giftList.do", method = RequestMethod.POST)
		public Map<String, Object> AddView(int page, int limit) throws Exception {
			Map<String, Object> strMap = new HashMap<String, Object>();
			int count = conn_liveGift.countAll();
			List<LiveGiftPO> gifts = conn_liveGift.findAll(page, limit);
			strMap.put("code", "0");
			strMap.put("msg", "");
			strMap.put("count", count);
			strMap.put("data", gifts);
			return strMap;
		}
		
		
		//添加礼物数据   张羽 5/8 新增
		@ResponseBody
		@RequestMapping(value="/addgift.do", method= RequestMethod.POST)
		public String addGift(HttpServletRequest request) throws Exception {
			LiveGiftPO gift = new LiveGiftPO();
			gift.setUpdateTime(new Date());
			conn_liveGift.save(gift);
			return "success";
		}
		
		
		//删除数据   张羽 5/8 新增
		@ResponseBody
		@RequestMapping(value="/delgift.do", method= RequestMethod.POST)
		public String delGift(HttpServletRequest request) throws Exception {
			long id =Long.parseLong(request.getParameter("id"));
			conn_liveGift.delete(id);
			return "success";
		}
		
		//修改数据    张羽 5/8 新增
		@ResponseBody
		@RequestMapping(value="/updategift.do", method= RequestMethod.POST)
		public String updateGift(HttpServletRequest request) throws Exception {
			long id = Long.parseLong(request.getParameter("id"));
			String field = request.getParameter("field");
			String value = request.getParameter("value");
			LiveGiftPO gift = conn_liveGift.get(id);
			if(field.equals("sort")){
				gift.setSort(Integer.parseInt(value));
			}else if(field.equals("price")){
				gift.setPrice(Integer.parseInt(value));
			}else{
			
				//字符串首字母转成大写
				char[] cs = field.toCharArray();
				cs[0]-=32;
				String.valueOf(cs);
				//反射
				Class<LiveGiftPO> carClass = LiveGiftPO.class;
				carClass.getDeclaredMethod("set"+String.valueOf(cs), String.class).invoke(gift, value);
				//方法名，输入参数的类型，对象，输入参数的值
			}
			conn_liveGift.save(gift);
			return "success";
		}
		
		//选择图片   张羽 5/8 新增
		@ResponseBody
		@RequestMapping(value="/giftpic.do",method= RequestMethod.POST)
		public String giftPic(HttpServletRequest request) {
			String pic = request.getParameter("pic");
			long picId = Long.parseLong(request.getParameter("picId"));
			long id = Long.parseLong(request.getParameter("id"));
			LiveGiftPO gift = conn_liveGift.get(id);
			gift.setPicId(picId);
			gift.setSlidepic(pic);
			conn_liveGift.saveOrUpdate(gift);
			return "success";
		}
		
		/**
		 * 前页面直播回放列表跳转 张羽
		 * @param request
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping(value = "/rebroadcastslist", method = RequestMethod.GET)
		public ModelAndView rebroadcastsList(HttpServletRequest request,Model model) throws Exception {
			ModelAndView mv = new ModelAndView("admin/live/rebroadcastsList");
			return mv;
		}
		
		@RequestMapping(value = "/pushGift", method = RequestMethod.GET)
		public ModelAndView pushGift(HttpServletRequest request,Model model) throws Exception {
			ModelAndView mv = new ModelAndView("admin/live/pushGift");
			return mv;
		}
		
		/**
		 * 直播打赏记录跳转 张羽
		 * @param request
		 * @param model
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/tipgiftlist", method = RequestMethod.GET)
		public ModelAndView tipGiftList(HttpServletRequest request,Model model) throws Exception {
			ModelAndView mv = new ModelAndView("admin/live/tipGiftList");
			return mv;
		}
		
		
		
		/**
		 * 获取打赏记录  张羽
		 * @param page
		 * @param limit
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping(value = "/tipgiftlist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
		public Map<String, Object> getTipGiftList(int page, int limit) throws Exception {
			Map<String, Object> strMap = new HashMap<String, Object>();
			List<LiveTipGiftPO> lives = conn_liveTipGift.findAll(page, limit);
			int count = conn_live.countAll();
			strMap.put("data", lives);
			strMap.put("msg", "");
			strMap.put("count", count);
			strMap.put("code", "0");
			return strMap;
		}
		
		
		@ResponseBody
		@RequestMapping(value = "/deltipgift.do")
		public Map<String, Object> delTipGift(Long orderId) throws Exception {
			conn_liveTipGift.delete(orderId);
			return success();
		}
		
}
			
	

