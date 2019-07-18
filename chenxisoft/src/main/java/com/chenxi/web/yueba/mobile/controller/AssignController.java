package com.chenxi.web.yueba.mobile.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.dao.UserDao;
import com.chenxi.web.po.SysViewRecordPo;
import com.chenxi.web.po.UserPo;
import com.chenxi.web.yueba.admin.dao.AssignDao;
import com.chenxi.web.yueba.admin.dao.OrderDao;
import com.chenxi.web.yueba.admin.po.AssignPo;
import com.chenxi.web.yueba.admin.po.OrderPo;

import javassist.expr.NewArray;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/assign")
public class AssignController {
	@Autowired
	AssignDao conn_assign;
	@Autowired
	OrderDao conn_order;
	
	@RequestMapping(value = "/mobile/wlist", method = RequestMethod.GET)
	public ModelAndView windex(HttpServletRequest request,long orderId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		HttpSession session=request.getSession();
		Object workerId=session.getAttribute("workerId");
		if(orderId==0){
			List<OrderPo> orderPos = conn_order.findOrderByWorkerAndToday(Long.parseLong(workerId+""));
			for (OrderPo orderPo : orderPos) {
				Date endDate=DateUtil.addDay(orderPo.getFromDate(),26);
				if(!DateUtil.compare(new Date(),endDate)&&DateUtil.compare(new Date(),orderPo.getFromDate())){
					orderId=orderPo.getId();
					break;
				}
			}
		}
		if(workerId!=null){
			strMap.put("workerId", workerId);
		}else{
			strMap.put("workerId", 0);
		}
		strMap.put("orderId", orderId);
		ModelAndView mv = new ModelAndView("yuebamobile/assignlist", strMap);
		return mv;
	}
	
	@Autowired
	UserDao conn_user;
	
	@RequestMapping(value = "/mobile/ulist", method = RequestMethod.GET)
	public ModelAndView uindex(HttpServletRequest request,long orderId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		HttpSession session=request.getSession();
		Object userId=session.getAttribute("userId");
		if(orderId==0){
			UserPo userPo=conn_user.get(Long.parseLong(userId+""));
			if(userPo.getPhone()==null||userPo.getPhone().isEmpty()){
				//用户添加手机号页面
				strMap.put("userId", userPo.getId());
				ModelAndView mv = new ModelAndView("yuebamobile/setuserphone", strMap);
				return mv;
			}else{
				List<OrderPo> orderPos=conn_order.findOrderByUser(userPo.getPhone());
				for (OrderPo orderPo : orderPos) {
					Date endDate=DateUtil.addDay(orderPo.getFromDate(),26);
					if(!DateUtil.compare(new Date(),endDate)&&DateUtil.compare(new Date(),orderPo.getFromDate())){
						orderId=orderPo.getId();
						break;
					}
				}
			}
		}
		Object workerId=session.getAttribute("workerId");
		if(workerId!=null){
			strMap.put("workerId", workerId);
		}else{
			strMap.put("workerId", 0);
		}
		strMap.put("orderId", orderId);
		ModelAndView mv = new ModelAndView("yuebamobile/assignlist", strMap);
		return mv;
	}
	
	
	@RequestMapping(value = "/mobile/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request,long assignId,long orderId) throws ParseException {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		if(assignId!=0){
			AssignPo assignPo=conn_assign.get(assignId);
			strMap.put("assignId", assignId);
			strMap.put("assign", assignPo);
			strMap.put("orderId", assignPo.getOrderId());
			strMap.put("canmodify", 0);
		}else{
			strMap.put("orderId", orderId);
			List<AssignPo> assignPos=conn_assign.findbyOrderIdAndToday(orderId);
			if(assignPos!=null&&assignPos.size()>0){
				strMap.put("assign", assignPos.get(0));
				strMap.put("assignId", assignPos.get(0).getId());
			}else{
				strMap.put("assign", new AssignPo());
				strMap.put("assignId", 0);
			}
			strMap.put("canmodify", 1);
		}
		ModelAndView mv = new ModelAndView("yuebamobile/addassign", strMap);
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/dolist", method = RequestMethod.GET)
	public Object list(HttpServletRequest request,Long orderId,int currPage,int pageCount) throws Exception {
	    List<AssignPo> assignPos=conn_assign.findbyOrderIdAndPage(orderId, pageCount, currPage);
	    for (AssignPo assignPo : assignPos) {
			assignPo.setUpdateTimeStr(DateUtil.format(assignPo.getUpdateTime(),"yyyy-MM-dd"));
		}
		return assignPos;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/dosave", method = RequestMethod.POST)
	public Object save(HttpServletRequest request) throws Exception {
		Object orderId=request.getParameter("orderId");
		Object assignId=request.getParameter("assignId");
		Object rufang=request.getParameter("rufang");
		Object elu=request.getParameter("elu");
		Object ruhan=request.getParameter("ruhan");
		
		Object qidai=request.getParameter("qidai");
		Object tunbu=request.getParameter("tunbu");
		Object dabiancishu=request.getParameter("dabiancishu");
		Object xiaobiancishu=request.getParameter("xiaobiancishu");
		
		Object rufanghuli=request.getParameter("rufanghuli");
		Object muyucashen=request.getParameter("muyucashen");
		Object chanhoucao=request.getParameter("chanhoucao");
		
		Object yingerxizao=request.getParameter("yingerxizao");
		Object yingertiwen=request.getParameter("yingertiwen");
		Object yingerfuchu=request.getParameter("yingerfuchu");
		Object qidaichuli=request.getParameter("qidaichuli");
		Object bianhouxitun=request.getParameter("bianhouxitun");
		Object xiaodunaiju=request.getParameter("xiaodunaiju");
		Object xiezhuweiyang=request.getParameter("xiezhuweiyang");
		AssignPo assignPo=null;
		if(assignId.equals("0")){
			assignPo=new AssignPo();
		}else{
			assignPo=conn_assign.get(Long.parseLong(assignId+""));
		}
		assignPo.setOrderId(Long.parseLong(orderId+""));
		assignPo.setBianhouxitun(Integer.parseInt(bianhouxitun+""));
		assignPo.setChanhoucao(Integer.parseInt(chanhoucao+""));
		assignPo.setDabiancishu(dabiancishu+"");
		assignPo.setElu(elu+"");
		assignPo.setMuyucashen(Integer.parseInt(muyucashen+""));
		assignPo.setQidai(qidai+"");
		assignPo.setQidaichuli(Integer.parseInt(qidaichuli+""));
		assignPo.setRufang(rufang+"");
		assignPo.setRufanghuli(Integer.parseInt(rufanghuli+""));
		assignPo.setRuhan(ruhan+"");
		assignPo.setTunbu(tunbu+"");
		assignPo.setXiaobiancishu(xiaobiancishu+"");
		assignPo.setXiaodunaiju(Integer.parseInt(xiaodunaiju+""));
		assignPo.setXiezhuweiyang(Integer.parseInt(xiezhuweiyang+""));
		assignPo.setYingerfuchu(Integer.parseInt(yingerfuchu+""));
		assignPo.setYingertiwen(Integer.parseInt(yingertiwen+""));
		assignPo.setYingerxizao(Integer.parseInt(yingerxizao+""));
		if(assignId.equals("0")){
			conn_assign.save(assignPo);
		}else{
			conn_assign.update(assignPo);
		}
		return "success";
	}
	
	
}
