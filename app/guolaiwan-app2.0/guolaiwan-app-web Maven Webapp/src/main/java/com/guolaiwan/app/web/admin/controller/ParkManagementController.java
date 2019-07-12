package com.guolaiwan.app.web.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.AttractionsParkingVO;
import com.guolaiwan.app.web.smartParking.vo.MoneyVo;
import com.guolaiwan.bussiness.Parking.dao.AttractionsDao;
import com.guolaiwan.bussiness.Parking.dao.MoneyDao;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.MoneyPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.thoughtworks.xstream.mapper.Mapper.Null;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/parkmanagement")
public class ParkManagementController extends BaseController {

	@Autowired
	private AttractionsDao attractionsDao; // 停车场表
	@Autowired
	private ParkingPositionDao parkingPositionDao; // 车位表
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private MoneyDao parking_Money;
	// 停车场列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/parkmanagement/list");
		return mv;
	}

	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page, int limit, HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<AttractionsParkingPO> listpo = new ArrayList<AttractionsParkingPO>();
		if (getLoginInfo() != null) {
			listpo = attractionsDao.findByPageC( page, limit);
		} else {
			listpo = attractionsDao.getByAttractionsId(getMerchantInfo().getComId());
		}
		int allcount = attractionsDao.CountByPageC();
		List<AttractionsParkingVO> listvo = AttractionsParkingVO.getConverter(AttractionsParkingVO.class)
				.convert(listpo, AttractionsParkingVO.class);
		for (AttractionsParkingVO attractionsParkingVO : listvo) {
			attractionsParkingVO.setParkingImg( conn_sysConfig.getSysConfig().getWebUrl() +attractionsParkingVO.getParkingImg());
		}
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", allcount);
		map.put("sysConfig", sysConfig);
		return map;
	}
	
	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/money.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getMoney(int page, int limit, HttpServletRequest request) throws Exception {
		List<MoneyPO> listpo = parking_Money.findByPageC( page, limit);
		List<MoneyVo> listvo = MoneyVo.getConverter(MoneyVo.class)
				.convert(listpo, MoneyVo.class);
		String boole = "";
		for (MoneyVo moneyVo : listvo) {
			if ( "".equals(boole)  || !boole.equals(moneyVo.getAttractionsId())) {
				boole = moneyVo.getAttractionsId();
				AttractionsParkingPO Attractions = attractionsDao.getUid(Long.parseLong(moneyVo.getAttractionsId()));	
				if (Attractions!=null) {
					moneyVo.setAttractionsId(Attractions.getParkingName());
				}
			}else {
				AttractionsParkingPO Attractions = attractionsDao.getUid(Long.parseLong(boole));
				moneyVo.setAttractionsId(Attractions.getParkingName());
			}
		}
		int allcount = parking_Money.CountByPageC();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", allcount);
		return map;
	}

	
	// 添加停车场弹出窗口
	@RequestMapping("/addv")
	public String AddView() throws Exception {
		return "admin/parkmanagement/add";
	}

	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public Map<String, Object> add(HttpServletRequest request, AttractionsParkingPO po, MultipartFile file)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Date d = new Date();
		Long fileUserId = null;
		if (getLoginInfo() != null) {
			fileUserId = getLoginInfo().getComId();
		} else {
			fileUserId = getMerchantInfo().getComId();
		}
		String folderName = "/id_" + fileUserId; // 文件名
		String path = conn_sysConfig.getSysConfig().getFolderUrl() + "/parkingPic" + folderName;
		String img = request.getParameter("parkingshopImg");
		po.setParkingImg(img);
		po.setAttractionsId(fileUserId);
		attractionsDao.save(po);
		map.put("code", "0");
		map.put("message", "提交成功");
		return map;
	}
	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/addParking.do", method = RequestMethod.POST)
	public Map<String, Object> addParking(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = request.getParameter("parkingname"); //名称
		String parkingLayer = request.getParameter("parkingLayer");//车位型
		String parkingDistrict = request.getParameter("parkingDistrict");//停车费用
		String fineMultiple = request.getParameter("fineMultiple");//区域
		String cost = request.getParameter("cost");//层数
		MoneyPO listpo = new MoneyPO();
		listpo.setAttractionsId(Long.parseLong(name));
		listpo.setParkingModel(parkingLayer);
		listpo.setMoney(Integer.parseInt(parkingDistrict));
		listpo.setArea(fineMultiple);
		listpo.setTier(cost);
		parking_Money.saveOrUpdate(listpo);;
		map.put("code", "0");
		map.put("message", "提交成功");
		return map;
	}

	// 详情页面
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		AttractionsParkingPO po = attractionsDao.get(uuid);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("po", po);
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("admin/parkmanagement/info");
		mv.addAllObjects(strMap);
		return mv;
	}
	
	// 详情页面
	@RequestMapping("/addMoney")
	public String addMoney(HttpServletRequest request) throws Exception {
		return "admin/parkmanagement/addMoney";
	}

	// 修改页面弹出窗口
	@RequestMapping(value = "/updatev", method = RequestMethod.GET)
	public ModelAndView updateView(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		Map<String, Object> strMap = new HashMap<String, Object>();
		AttractionsParkingPO po = attractionsDao.getBusinessHours(Long.parseLong(uuid)).get(0);
		strMap.put("po", po);
		ModelAndView mv = new ModelAndView("admin/parkmanagement/modify");
		mv.addAllObjects(strMap);
		return mv;
	}
	// 修改页面弹出窗口
	@RequestMapping(value = "/updatevMoney", method = RequestMethod.GET)
	public ModelAndView updatevMoney(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		Map<String, Object> strMap = new HashMap<String, Object>();
		MoneyPO po = parking_Money.findByField("id",Long.parseLong(uuid)).get(0);
		strMap.put("po", po);
		ModelAndView mv = new ModelAndView("admin/parkmanagement/updatevMoney");
		mv.addAllObjects(strMap);
		return mv;
	}
	
	// 删除车位金额
	@ResponseBody
	@RequestMapping(value = "/delMoney.do", method = RequestMethod.POST)
	public String deleteMoney(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		parking_Money.delete(Long.parseLong(uuid));
		return "success";
	}

	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public Map<String, Object> update(HttpServletRequest request, AttractionsParkingPO po, MultipartFile file)
			throws Exception {
		String uuid = request.getParameter("uuid");
		Long id = Long.parseLong(request.getParameter("id"));
		String imgTemp = request.getParameter("imgTemp");
		String img = request.getParameter("shopPicImg");
		po.setUuid(uuid);
		po.setId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		Long fileUserId = null;
		if (getLoginInfo() != null) {
			fileUserId = getLoginInfo().getComId();
		} else {
			fileUserId = getMerchantInfo().getComId();
		}
		if (img != null) { // 修改图片
		/*	Date d = new Date();
			String folderName = "/id_" + fileUserId; // 文件名
			String path = conn_sysConfig.getSysConfig().getFolderUrl() + "/parkimgPic" + folderName;
			// 文件名
			String fileName = file.getOriginalFilename();
			String newName = d.getTime() + fileName.substring(fileName.lastIndexOf(".")); // 时间戳+后缀名
			File folder = new File(path);
			if (folder.exists() == false) { // 如果路径不存在
				if (folder.getParentFile().exists() == false) {
					map.put("code", "1");
					map.put("message", "文件路径错误！");
					return map;
				}
				folder.mkdir();
			}
			// 上传
			File newFile = new File(path + "/" + newName);
			file.transferTo(newFile);
			po.setParkingImg("/parkimgPic" + folderName + "/" + newName);
			// 删除旧照片
			if (imgTemp != null && !"".equals(imgTemp)) {
				File[] filesInFolder = folder.listFiles();
				String[] s = imgTemp.split("/");
				for (File f : filesInFolder) {
					if (s[s.length - 1].equals(f.getName())) {
						f.delete();
					}
				}
			}*/
			po.setParkingImg(img);
		} else { // 未修改赋原值
			po.setParkingImg(imgTemp);
		}
		po.setAttractionsId(fileUserId);
		attractionsDao.saveOrUpdate(po);
		map.put("code", "0");
		map.put("message", "修改成功");
		return map;
	}
	// 修改停车金额
	@ResponseBody
	@RequestMapping(value = "/updateMoney.do", method = RequestMethod.POST)
	public Map<String, Object> updateMoney(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id"); //停车场ID
		String commonParking = request.getParameter("commonParking"); //停车场ID
		String usedParking = request.getParameter("usedParking");// 车位车型
		String position = request.getParameter("position");//费用
		String chargingColumn = request.getParameter("chargingColumn");//区
		String parkingLayer = request.getParameter("parkingLayer");//层
		MoneyPO money = parking_Money.findByField("id",Long.parseLong(id)).get(0);
		if (money!=null) {
			money.setAttractionsId(Long.parseLong(commonParking));
			money.setParkingModel(usedParking);
			money.setMoney(Integer.parseInt(position));
			money.setArea(chargingColumn);
			money.setTier(parkingLayer);
			parking_Money.saveOrUpdate(money);
			map.put("code", "0");
			map.put("message", "修改成功");
		}{
			map.put("code", "1");
			map.put("message", "失败");
		}
		return map;
	}

	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		AttractionsParkingPO po = attractionsDao.getBusinessHours(Long.parseLong(uuid)).get(0);
		Long id = po.getId();
		List<ParkingPositionPO> list = parkingPositionDao.getByPositionId(id);
		LinkedList<Long> ids = new LinkedList<Long>();
		if (list != null && list.size() > 0) {
			for (ParkingPositionPO parkingPositionPO : list) {
				ids.add(parkingPositionPO.getId());
			}
		}
		parkingPositionDao.deleteAllByIds(ids);
		attractionsDao.delete(po);
		return "success";
	}

	/************************* 停车车位 ********************************/
	// 停车车位列表
	@RequestMapping(value = "/parklist", method = RequestMethod.GET)
	public ModelAndView parkList(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("parking");
		AttractionsParkingPO po = attractionsDao.getBusinessHours(Long.parseLong(uuid)).get(0);
		strMap.put("po", po);
		ModelAndView mv = new ModelAndView("admin/parkmanagement/parklist");
		mv.addAllObjects(strMap);
		return mv;
	}

	// 异步单一停车车位列表
	@ResponseBody
	@RequestMapping(value = "/parkpositionlist.do", method = RequestMethod.POST)
	public Map<String, Object> parkPositionList(HttpServletRequest request, int page, int limit) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Long positionId = Long.parseLong(request.getParameter("positionid"));
		String[] field = { "positionId" };
		Object[] value = { positionId };
		int count = parkingPositionDao.countByFields(field, value);
		List<ParkingPositionPO> parkList = parkingPositionDao.pageByPositionId(page, limit, positionId);
		map.put("data", parkList);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	// 添加车弹出窗口
	@RequestMapping("/parkaddv")
	public ModelAndView addParkView(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		Long positionId = Long.parseLong(request.getParameter("positionid"));
		strMap.put("positionid", positionId);
		ModelAndView mv = new ModelAndView("admin/parkmanagement/parkadd", strMap);
		return mv;
	}
	
	// 停车场金额管理
	@RequestMapping("/money")
	public ModelAndView listMoney(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/parkmanagement/moneyList");
		return mv;
	}

	// 停车车位添加
	@ResponseBody
	@RequestMapping(value = "/parkadd", method = RequestMethod.POST)
	public String parkAdd(HttpServletRequest request, ParkingPositionPO po) throws Exception {
		Long positionId = Long.parseLong(request.getParameter("positionid"));
		po.setPositionId(positionId);
		parkingPositionDao.save(po);
		return "success";
	}

	// 验证车位编号是否重复
	@ResponseBody
	@RequestMapping(value = "/verifypositionnumber.do", method = RequestMethod.POST)
	public String IsLoginName(HttpServletRequest request) throws Exception {
		Long positionId = Long.parseLong(request.getParameter("positionId"));
		Integer positionNumber = Integer.parseInt(request.getParameter("positionNumber"));
		String[] fields = { "positionId", "positionNumber" };
		Object[] values = { positionId, positionNumber };
		int countByFields = parkingPositionDao.countByFields(fields, values);
		if (countByFields == 1) {
			return "has";
		}
		return "success";
	}

	// 修改页面弹出窗口
	@RequestMapping(value = "/parkupdatev", method = RequestMethod.GET)
	public ModelAndView parUpdateView(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		ParkingPositionPO po = parkingPositionDao.get(uuid);
		strMap.put("po", po);
		ModelAndView mv = new ModelAndView("admin/parkmanagement/parkmodify");
		mv.addAllObjects(strMap);

		return mv;
	}

	// 停车车位更新
	@ResponseBody
	@RequestMapping(value = "/parkupdate.do", method = RequestMethod.POST)
	public String parkUpdate(HttpServletRequest request, ParkingPositionPO po) throws Exception {
		String uuid = request.getParameter("uuid");
		Long id = Long.parseLong(request.getParameter("id"));
		Long positionId = Long.parseLong(request.getParameter("positionId"));
		po.setPositionId(positionId);
		po.setUuid(uuid);
		po.setId(id);
		parkingPositionDao.update(po);
		return "success";
	}

	// 停车车位删除
	@ResponseBody
	@RequestMapping(value = "/parkdel.do", method = RequestMethod.POST)
	public String parkDel(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		ParkingPositionPO po = parkingPositionDao.get(uuid);
		parkingPositionDao.delete(po);
		return "success";
	}

}
