package com.guolaiwan.app.web.publicnum.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.publicnum.vo.RoomVo;
import com.guolaiwan.bussiness.admin.dao.RoomDao;
import com.guolaiwan.bussiness.admin.dao.RoomStatusDao;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.RoomPo;
import com.guolaiwan.bussiness.admin.po.RoomStatusPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping("/pubnum/stock")
public class PubNumStockController {

	@Autowired
	private RoomDao conn_room;
	@Autowired
	private RoomStatusDao conn_roomstatus;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request,long productId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubadmin/stock");
		mv.addObject("date",DateUtil.format(new Date(),"yyyy-MM-dd"));
		mv.addObject("productId",productId);
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getRooms", method = RequestMethod.GET)
	public Object getRooms(HttpServletRequest request,long productId,String date) throws Exception {
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();
	    List<RoomVo> roomVos=new ArrayList<RoomVo>();
	    List<RoomPo> roomList=conn_room.findByField("productId", productId);
	    for (RoomPo roomPo : roomList) {
			RoomVo roomVo=new RoomVo();
			roomVo.setId(roomPo.getId());
			roomVo.setName(roomPo.getName());
			List<RoomStatusPO> status=conn_roomstatus.findulStatusByRoomAndDate(roomPo.getId(),date);
			List<RoomStatusPO> rstatus=conn_roomstatus.findurStatusByRoomAndDate(roomPo.getId(),date);
			List<RoomStatusPO> cstatus=conn_roomstatus.finducStatusByRoomAndDate(roomPo.getId(),date);
			if(status!=null&&status.size()>0){
				roomVo.setStatus(1);
			}
			if(rstatus!=null&&rstatus.size()>0){
				roomVo.setStatus(1);
			}
			if(cstatus!=null&&cstatus.size()>0){
				roomVo.setStatus(1);
			}
			roomVos.add(roomVo);
		}
		return roomVos;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/changeStatus", method = RequestMethod.GET)
	public Object changeStatus(HttpServletRequest request,Long roomId,int status,String date) throws Exception {
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();
		List<RoomStatusPO> statusPos=conn_roomstatus.findlStatusByRoomAndDate(roomId,date);
		List<RoomStatusPO> rstatusPos=conn_roomstatus.findrStatusByRoomAndDate(roomId,date);
		List<RoomStatusPO> cstatusPos=conn_roomstatus.findcStatusByRoomAndDate(roomId,date);
		RoomStatusPO roomStatus=new RoomStatusPO();
		if(statusPos!=null&&!statusPos.isEmpty()){
			roomStatus=statusPos.get(0);
		}
		if(rstatusPos!=null&&!rstatusPos.isEmpty()){
			roomStatus=rstatusPos.get(0);
		}
		if(cstatusPos!=null&&!cstatusPos.isEmpty()){
			roomStatus=rstatusPos.get(0);
		}
		roomStatus.setRoomId(roomId);
		roomStatus.setStatus(status);
		roomStatus.setStartDate(DateUtil.parse(date+" 00:00:00","yyyy-MM-dd"));
		roomStatus.setEndDate(DateUtil.parse(date+" 23:59:59","yyyy-MM-dd"));
		conn_roomstatus.save(roomStatus);
		return "success";
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/userRooms", method = RequestMethod.GET)
	public Object userRooms(HttpServletRequest request,Long productId,String start,String end) throws Exception {
		List<RoomPo> roomPos=conn_room.findByField("productId", productId);
		List<RoomVo> vos=new ArrayList<RoomVo>();
		for (RoomPo roomPo : roomPos) {
			String startSimple=DateUtil.format(DateUtil.parse(start.replace("T", " "),"yyyy-MM-dd HH:mm"),"yyyy-MM-dd HH:mm:ss");
			String endSimple=DateUtil.format(DateUtil.parse(end.replace("T", " "),"yyyy-MM-dd HH:mm"),"yyyy-MM-dd HH:mm:ss");
			List<RoomStatusPO> lstatusPos=conn_roomstatus.findlStatusByRoomAndUsed(roomPo.getId(),startSimple,endSimple);
			List<RoomStatusPO> rstatusPos=conn_roomstatus.findrStatusByRoomAndUsed(roomPo.getId(),startSimple,endSimple);
			List<RoomStatusPO> cstatusPos=conn_roomstatus.findcStatusByRoomAndUsed(roomPo.getId(),startSimple,endSimple);
			RoomVo vo=new RoomVo();
			vo.setId(roomPo.getId());
			vo.setName(roomPo.getName());
			if(lstatusPos==null||lstatusPos.isEmpty()){
			
			}else{
				vo.setStatus(1);
			}
			if(rstatusPos==null||rstatusPos.isEmpty()){
			
			}else{
				vo.setStatus(1);
			}
			if(cstatusPos==null||cstatusPos.isEmpty()){
	
			}else{
				vo.setStatus(1);
			}
			vos.add(vo);
		}

		return vos;
	}
	
	
}
