package com.guolaiwan.app.web.admin.controller;

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
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.RoomDao;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.ArticlePO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.RoomPo;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/stock")
public class StockController extends BaseController {

    @Autowired
    RoomDao conn_room;
    
	// 显示添加页面
	@RequestMapping(value = "/room", method = RequestMethod.GET)
	public ModelAndView stockRoom(HttpServletRequest request,long productId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count=conn_room.countByField("productId", productId);
		ModelAndView mv = new ModelAndView("admin/stock/room", strMap);
		mv.addObject("count",count);
		mv.addObject("productId",productId);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/getRList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(HttpServletRequest request,long productId) throws Exception {
		List<RoomPo> roomList=conn_room.findByField("productId", productId);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", roomList);
		map.put("code", "0");
		map.put("msg", "");
		return map;
	}

	//添加页面
	@RequestMapping("/addv")
	public ModelAndView addRoom(long productId){
		ModelAndView mv = new ModelAndView("admin/stock/addRoom");
		mv.addObject("productId",productId);
		return mv;
	}
	
	//添加数据
	@ResponseBody
	@RequestMapping(value="/addRoom.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request,long productId) throws Exception {
		
		String name = request.getParameter("name").trim();
		RoomPo roomPo=new RoomPo();
		roomPo.setName(name);
		roomPo.setProductId(productId);
        conn_room.save(roomPo);		
		return "success";
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		
		String id = request.getParameter("id");
        conn_room.delete(Long.parseLong(id));		
		return "success";
		
	}

}
