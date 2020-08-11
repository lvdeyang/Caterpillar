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

import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.bussiness.admin.dao.ActivityBundleDAO;
import com.guolaiwan.bussiness.admin.dao.DeviceDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityBundleType;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.ActiveBundlePo;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.DevicePO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.orm.hql.QueryHql;

@Controller
@RequestMapping("/admin/device")
public class DeviceController extends BaseController {
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private DeviceDAO conn_device;
	// 显示添加页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView deviceList(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("admin/device/list", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public Map<String, Object> AddView(int page, int limit) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_device.countAll();

		List<DevicePO> devices = conn_device.queryByPage(page, limit,0);
		
		strMap.put("code", "0");
		strMap.put("msg", "");
		strMap.put("count", count);
		strMap.put("data", devices);
		return strMap;
	}

	// 添加数据页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/device/add");
		return mv;
	}

	// 添加数据页面
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {

		String deviceName = request.getParameter("deviceName");
		String deviceCode=request.getParameter("deviceCode");
		
	    DevicePO devicePO=new DevicePO();
	    devicePO.setDeviceCode(deviceCode);
	    devicePO.setDeviceName(deviceName);
	    conn_device.save(devicePO);
		return "success";
	}
    
	@Autowired
	MerchantDAO conn_merchant;
	
	// 选择图片
	@ResponseBody
	@RequestMapping(value = "/selMechant.do", method = RequestMethod.POST)
	public String pic(HttpServletRequest request) {
		String merchantId = request.getParameter("merchantId");
		long id = Long.parseLong(request.getParameter("id"));
		DevicePO devicePO=conn_device.get(id);
		MerchantPO merchantPO=conn_merchant.get(Long.parseLong(merchantId));
		devicePO.setMerchantId(merchantPO.getId());
		devicePO.setMerchantName(merchantPO.getShopName());
		conn_device.save(devicePO);
		return "success";
	}

	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		// 删除所有推荐的关联表
		conn_device.delete(id);
		return "success";
	}
	
	//新增方法4/23 修改活动集的标题方法
	@ResponseBody
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String edit1(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		DevicePO devicePO = conn_device.get(id);
		if (field.equals("deviceName")) {
			devicePO.setDeviceName(value);
		}else if(field.equals("deviceCode")){
			devicePO.setDeviceCode(value);
		}
		conn_device.save(devicePO);
		return "success";
	}
	
	@RequestMapping(value = "/selMerchantv" , method = RequestMethod.GET)
	public ModelAndView selMerchantv(HttpServletRequest request){
		ModelAndView mav = null;
		mav = new ModelAndView("admin/device/selMechant");
		mav.addObject("deviceId",request.getParameter("id"));
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/merchantlist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getMerchantList(int page, int limit, HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		String mName = request.getParameter("mName");
		strMap.put("shopName", mName);
		strMap.put("modularCode", request.getAttribute("modularCode"));
		strMap.put("shopAuditState", ShopAuditStateType.T);
		List<MerchantPO> listpo = conn_merchant.findMerchant(strMap, page, limit);
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		int count = conn_merchant.countMerchant(strMap);
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}
}
