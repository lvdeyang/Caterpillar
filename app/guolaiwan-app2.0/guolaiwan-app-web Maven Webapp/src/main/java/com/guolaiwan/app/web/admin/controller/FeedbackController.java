package com.guolaiwan.app.web.admin.controller;


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

import com.guolaiwan.app.web.coupleback.vo.CoupleBackVo;
import com.guolaiwan.app.web.smartParking.vo.OrderVo;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.coupleback.dao.CoupleBackDao;
import com.guolaiwan.bussiness.coupleback.po.CoupleBackPO;

import pub.caterpillar.mvc.controller.BaseController;


@Controller
@RequestMapping(value ="/admin/feedback")
public class FeedbackController extends BaseController {


	@Autowired
	private CoupleBackDao couple_back;
	@Autowired
	private UserInfoDAO conn_user;

	
	// 查询未回复已回复
		@ResponseBody
		@RequestMapping(value = "/inquire.do", method = RequestMethod.POST)
		public Map<String, Object> getList(HttpServletRequest request , int page, int limit, int type) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(type);
			
			List<CoupleBackPO>   message =	couple_back.getOrderform(page,limit,type);
			List<CoupleBackPO>   length =	couple_back.getOrderform(type);
			List<CoupleBackVo> couple = CoupleBackVo.getConverter(CoupleBackVo.class).convert(message, CoupleBackVo.class);
		
			map.put("count",length.size());
			map.put("data", couple);
			map.put("code", "0");
			map.put("msg", "");
			return map;
		}
		
		// 回复反馈
		@ResponseBody
		@RequestMapping(value = "/replymessage.do", method = RequestMethod.POST)
		public Map<String, Object> replyMessage(HttpServletRequest request ,String reply,long orderid) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			CoupleBackPO replyContent   =	couple_back.getOrder(orderid);
			replyContent.setReplycontent(reply);
			replyContent.setState(1);
			couple_back.saveOrUpdate(replyContent);
			map.put("code", "0");
			map.put("msg", "");
			return map;
		}
		
		// 删除订单
		@ResponseBody
		@RequestMapping(value = "/deleteorder.do", method = RequestMethod.POST)
		public Map<String, Object> deleteOrder(HttpServletRequest request ,long orderid) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			couple_back.delete(orderid);
			map.put("code", "0");
			map.put("msg", "");
			return map;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// 停车场订单页面
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ModelAndView getList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/feedback/message");
		return mv;
	}

}




