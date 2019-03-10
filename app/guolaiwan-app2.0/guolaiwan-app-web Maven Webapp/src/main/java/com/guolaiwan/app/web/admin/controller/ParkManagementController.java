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
import com.guolaiwan.bussiness.Parking.dao.AttractionsDao;
import com.guolaiwan.bussiness.Parking.dao.ParkingPositionDao;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

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
			Long attractionsId = getLoginInfo().getComId();
			String parkingName = request.getParameter("parkingName");
			strMap.put("attractionsId", attractionsId);
			strMap.put("parkingName", parkingName);
			listpo = attractionsDao.findByPageC(strMap, page, limit);
		} else {
			listpo = attractionsDao.getByAttractionsId(getMerchantInfo().getMerchantId());
		}
		int allcount = attractionsDao.CountByPageC(strMap);
		List<AttractionsParkingVO> listvo = AttractionsParkingVO.getConverter(AttractionsParkingVO.class)
				.convert(listpo, AttractionsParkingVO.class);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", allcount);
		map.put("sysConfig", sysConfig);
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
			fileUserId = getMerchantInfo().getMerchantId();
		}
		String folderName = "/id_" + fileUserId; // 文件名
		String path = conn_sysConfig.getSysConfig().getFolderUrl() + folderName;
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
		po.setParkingImg("/lib/images/park" + folderName + "/" + newName);
		po.setAttractionsId(fileUserId);
		attractionsDao.save(po);
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

	// 修改页面弹出窗口
	@RequestMapping(value = "/updatev", method = RequestMethod.GET)
	public ModelAndView updateView(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		Map<String, Object> strMap = new HashMap<String, Object>();
		AttractionsParkingPO po = attractionsDao.get(uuid);
		strMap.put("po", po);
		ModelAndView mv = new ModelAndView("admin/parkmanagement/modify");
		mv.addAllObjects(strMap);
		return mv;
	}

	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public Map<String, Object> update(HttpServletRequest request, AttractionsParkingPO po, MultipartFile file)
			throws Exception {
		String uuid = request.getParameter("uuid");
		Long id = Long.parseLong(request.getParameter("id"));
		String imgTemp = request.getParameter("imgTemp");
		po.setUuid(uuid);
		po.setId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		Long fileUserId = null;
		if (getLoginInfo() != null) {
			fileUserId = getLoginInfo().getComId();
		} else {
			fileUserId = getMerchantInfo().getMerchantId();
		}
		if (file.getSize() > 0) { // 修改图片
			Date d = new Date();
			String folderName = "/id_" + fileUserId; // 文件名
			String path = conn_sysConfig.getSysConfig().getFolderUrl() + folderName;
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
			po.setParkingImg("/lib/images/park" + folderName + "/" + newName);
			// 删除旧照片
			if (imgTemp != null && !"".equals(imgTemp)) {
				File[] filesInFolder = folder.listFiles();
				String[] s = imgTemp.split("/");
				for (File f : filesInFolder) {
					if (s[s.length - 1].equals(f.getName())) {
						f.delete();
					}
				}
			}
		} else { // 未修改赋原值
			po.setParkingImg(imgTemp);
		}
		po.setAttractionsId(fileUserId);
		attractionsDao.saveOrUpdate(po);
		map.put("code", "0");
		map.put("message", "修改成功");
		return map;
	}

	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		AttractionsParkingPO po = attractionsDao.get(uuid);
		Long id = po.getId();
		String parkingImg = po.getParkingImg();
		List<ParkingPositionPO> list = parkingPositionDao.getByPositionId(id);
		LinkedList<Long> ids = new LinkedList<Long>();
		if (list != null && list.size() > 0) {
			for (ParkingPositionPO parkingPositionPO : list) {
				ids.add(parkingPositionPO.getId());
			}
		}
		parkingPositionDao.deleteAllByIds(ids);
		attractionsDao.delete(po);
		if (parkingImg != null && !"".equals(parkingImg)) {
			String[] s = parkingImg.split("/");
			String path = conn_sysConfig.getSysConfig().getFolderUrl() + "/" + s[s.length - 2];
			File folder = new File(path);
			File[] filesInFolder = folder.listFiles();
			for (File f : filesInFolder) {
				if (s[s.length - 1].equals(f.getName())) {
					f.delete();
				}
			}
		}
		return "success";
	}

	/************************* 停车车位 ********************************/
	// 停车车位列表
	@RequestMapping(value = "/parklist", method = RequestMethod.GET)
	public ModelAndView parkList(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("parking");
		AttractionsParkingPO po = attractionsDao.get(uuid);
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
