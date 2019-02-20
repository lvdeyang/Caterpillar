package com.guolaiwan.app.web.admin.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.VoiceVO;
import com.guolaiwan.bussiness.admin.dao.VoiceDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.VoicePO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;


@Controller
@RequestMapping("/admin/ziduan")
public class ZziduanController extends BaseController{

	@Autowired
	private VoiceDAO conn_Voice;
	@Autowired
	private SysConfigDAO conn_sysConfig;

	//同时添加
	@RequestMapping(value="/upload",method= RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		int count = conn_Voice.getCountByPage();
		strMap.put("count",count);
		ModelAndView mv = new ModelAndView("admin/Voice/list",strMap);
		return mv;
	}


}
